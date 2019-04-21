package com.hubin.configs.filter;

import com.alibaba.fastjson.JSON;
import com.hubin.utils.ResponseResult;
import com.hubin.configs.token.JwtToken;
import com.hubin.services.system.AccessService;
import com.hubin.utils.JwtUtils;
import com.hubin.utils.RequestResponseUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * <br>
 *
 * @author hubin
 * @title: JWT过滤器，支持无状态验证
 * @description:
 * @date: 2019/3/22 19:42
 */
public class JwtFilter extends AbstractPathMatchingFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    private static final String STR_EXPIRED = "expiredJwt";

    private StringRedisTemplate redisTemplate;

    private AccessService accessService;

    public JwtFilter(StringRedisTemplate redisTemplate,AccessService accessService) {
        this.redisTemplate = redisTemplate;
        this.accessService = accessService;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        Subject subject = getSubject(request, response);

        //判断是否是JWT认证请求
        boolean isJwt =(Objects.nonNull(subject)&& !subject.isAuthenticated() && isJwtSubmission(request));

        if (isJwt) {
            AuthenticationToken token = createJwtToken(request);
            try {
                subject.login(token);
                if(!checkRoles(subject, mappedValue)){
                    // 告知客户端JWT没有权限访问此资源
                    RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.unAuthor()),response);
                    return Boolean.FALSE;
                }
                return Boolean.TRUE;
            } catch (AuthenticationException authEx) {
                // 如果是JWT过期
                if (STR_EXPIRED.equals(authEx.getMessage())) {
                    // 这里初始方案先抛出令牌过期，之后设计为在Redis中查询当前username对应令牌，其设置的过期时间是JWT的两倍，此作为JWT的refresh时间
                    // 当JWT的有效时间过期后，查询其refresh时间，refresh时间有效即重新派发新的JWT给客户端，
                    // refresh也过期则告知客户端JWT时间过期重新认证

                    // 当存储在redis的JWT没有过期，即refresh time 没有过期
                    String appId = WebUtils.toHttp(request).getHeader("username");
                    String jwt = WebUtils.toHttp(request).getHeader("authorization");
                    String refreshJwt = redisTemplate.opsForValue().get("JWT-SESSION-" + appId);
                    if (null != refreshJwt && refreshJwt.equals(jwt)) {
                        // 重新申请新的JWT
                        // 根据username获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
                        String roles = accessService.getSysUserRole(appId);
                        //seconds为单位,60s
                        String newJwt = JwtUtils.crateToken(UUID.randomUUID().toString(), appId,
                                "token-server", JwtUtils.EXPIRE_TIME_SECOND, roles, null, SignatureAlgorithm.HS512);
                        // 将签发的JWT存储到Redis： {JWT-SESSION-{username} , jwt}
                        redisTemplate.opsForValue().set("JWT-SESSION-" + appId, newJwt, JwtUtils.EXPIRE_TIME_SECOND, TimeUnit.SECONDS);


                        RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.forbid("new token", newJwt)), response);
                        return Boolean.FALSE;
                    } else {
                        // jwt时间失效过期,jwt refresh time失效 返回jwt过期客户端重新登录
                        RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.failed("expired token")), response);
                        return Boolean.FALSE;
                    }
                }
                // 其他的判断为JWT错误无效
                RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.failed("error token")),response);
                return Boolean.FALSE;
            }catch (Exception e) {
                // 其他错误 IpUtil.getIpFromRequest(WebUtils.toHttp(request))
                log.error("--JWT认证失败"+e.getMessage(),e);
                // 告知客户端JWT错误1005,需重新登录申请jwt
                RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.failed("error token")),response);
                return false;
            }
        }else {
            // 请求未携带jwt 判断为无效请求
            RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.failed("error token")),response);
            return Boolean.FALSE;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request,response);

        // 未认证的情况
        if (null == subject || !subject.isAuthenticated()) {
            // 告知客户端JWT认证失败需跳转到登录页面
            RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.failed("不正确的签名")),response);
        }else {
            //  已经认证但未授权的情况
            // 告知客户端JWT没有权限访问此资源
            RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.unAuthor()),response);
        }
        // 过滤链终止
        return false;
    }


    private boolean isJwtSubmission(ServletRequest request) {
        String jwt = RequestResponseUtil.getHeader(request,"authorization");
        String appId = RequestResponseUtil.getHeader(request,"username");
        return (request instanceof HttpServletRequest)
                && !StringUtils.isEmpty(jwt)
                && !StringUtils.isEmpty(appId);
    }

    private boolean checkRoles(Subject subject, Object mappedValue) {
        String[] rolesArray = (String[]) mappedValue;
        return rolesArray == null || rolesArray.length == 0 || Stream.of(rolesArray).anyMatch(role -> subject.hasRole(role.trim()));

    }

    public void setAccountService(AccessService accessService) {
        this.accessService = accessService;
    }

    private AuthenticationToken createJwtToken(ServletRequest request) {
        Map<String,String> maps = RequestResponseUtil.getRequestHeaders(request);
        String username = maps.get("username");
        String ipHost = request.getRemoteAddr();
        String jwt = maps.get("authorization");
        String deviceInfo = maps.get("deviceInfo");

        return new JwtToken(ipHost, deviceInfo, jwt, username);
    }


}

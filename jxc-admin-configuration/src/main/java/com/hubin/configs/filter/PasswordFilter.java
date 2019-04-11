package com.hubin.configs.filter;

import com.alibaba.fastjson.JSON;
import com.hubin.utils.CodeMsgEnum;
import com.hubin.utils.ResponseResult;
import com.hubin.configs.token.PasswordToken;
import com.hubin.utils.CommonUtil;
import com.hubin.utils.IpUtil;
import com.hubin.utils.RequestResponseUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/22 21:07
 */
public class PasswordFilter extends AccessControlFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordFilter.class);

    private StringRedisTemplate redisTemplate;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        Subject subject = getSubject(request,response);
        // 如果其已经登录，再此发送登录请求
        //  拒绝，统一交给 onAccessDenied 处理
        return null != subject && subject.isAuthenticated();
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // 判断若为获取登录注册加密动态秘钥请求
        if (isPasswordTokenGet(request)) {
            //动态生成秘钥，redis存储秘钥供之后秘钥验证使用，设置有效期5秒用完即丢弃
            String tokenKey = CommonUtil.getRandomString(16);
            String userKey = CommonUtil.getRandomString(6);
            try {
                System.err.println("TOKEN_KEY_"+ IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase()+userKey.toUpperCase());
                redisTemplate.opsForValue().set("TOKEN_KEY_"+ IpUtil.getIpFromRequest(WebUtils.toHttp(request)).toUpperCase()+userKey.toUpperCase(),tokenKey,10, TimeUnit.SECONDS);
                // 动态秘钥response返回给前端
                Map<String, Object> responseMap = new HashMap<>();
                responseMap.put("tokenKey", tokenKey);
                responseMap.put("userKey", userKey.toUpperCase());
                RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.success("发放TokenKey成功",
                        responseMap)),response);

            }catch (Exception e) {
                LOGGER.warn("签发动态秘钥失败"+e.getMessage(),e);
                RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.res(CodeMsgEnum.TOKENFAILE.getcode(),
                CodeMsgEnum.TOKENFAILE.getMsg())),response);
            }
            return false;
        }

        // 判断是否是登录请求
        if(isPasswordLoginPost(request)){

            AuthenticationToken authenticationToken = null;
            try {
                authenticationToken = createPasswordToken(request);
            }catch (Exception e) {
                // response 告知无效请求
                RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.res(CodeMsgEnum.LOSEREQUEST.getcode(),
                        CodeMsgEnum.LOSEREQUEST.getMsg())),response);
                return false;
            }

            Subject subject = getSubject(request,response);
            try {
                subject.login(authenticationToken);
                //登录认证成功,进入请求派发json web token url资源内
                return true;
            }catch (AuthenticationException e) {
                LOGGER.warn(authenticationToken.getPrincipal()+"::"+e.getMessage());
                // 返回response告诉客户端认证失败
                RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.failed("登陆失败,密码不正确")),response);
                return false;
            }catch (Exception e) {
                LOGGER.error(authenticationToken.getPrincipal()+"::认证异常::"+e.getMessage(),e);
                // 返回response告诉客户端认证失败
                RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.failed("登陆失败")),response);
                return false;
            }
        }
        // 判断是否为注册请求,若是通过过滤链进入controller注册
        if (isAccountRegisterPost(request)) {
            return true;
        }
        // 之后添加对账户的找回等
        // response 告知无效请求
        RequestResponseUtil.responseWrite(JSON.toJSONString(ResponseResult.res(CodeMsgEnum.LOSEREQUEST.getcode(),
                CodeMsgEnum.LOSEREQUEST.getMsg())),response);
        return false;
    }

    private boolean isPasswordTokenGet(ServletRequest request) {

        String tokenKey = RequestResponseUtil.getParameter(request,"tokenKey");

        return (request instanceof HttpServletRequest)
                && "GET".equals(((HttpServletRequest) request).getMethod().toUpperCase())
                &&  "get".equals(tokenKey);
    }

    private boolean isPasswordLoginPost(ServletRequest request) {

        Map<String ,String> map = RequestResponseUtil.getRequestBodyMap(request);
        String password = map.get("password");
        String timestamp = map.get("timestamp");
        String methodName = map.get("methodName");
        String username = map.get("username");
        return (request instanceof HttpServletRequest)
                && "POST".equals(((HttpServletRequest) request).getMethod().toUpperCase())
                && null != password
                && null != timestamp
                && null != username
                && "login".equals(methodName);
    }

    private boolean isAccountRegisterPost(ServletRequest request) {

        Map<String ,String> map = RequestResponseUtil.getRequestBodyMap(request);
        String uid = map.get("uid");
        String username = map.get("username");
        String methodName = map.get("methodName");
        String password = map.get("password");
        return (request instanceof HttpServletRequest)
                && null != username
                && null != password
                && null != uid
                && "register".equals(methodName);
    }

    private AuthenticationToken createPasswordToken(ServletRequest request) throws Exception {

        Map<String ,String> map = RequestResponseUtil.getRequestBodyMap(request);
        String username = map.get("username");
        String timestamp = map.get("timestamp");
        String password = map.get("password");
        String host = IpUtil.getIpFromRequest(WebUtils.toHttp(request));
        String userKey = map.get("userKey");
        System.err.println("TOKEN_KEY_"+host.toUpperCase()+userKey);
        String tokenKey = redisTemplate.opsForValue().get("TOKEN_KEY_"+host.toUpperCase()+userKey);
        return new PasswordToken(username,password,timestamp,host,tokenKey);
    }

    public void setRedisTemplate(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}

package com.hubin.controller;

import com.hubin.common.ResponseResult;
import com.hubin.dto.system.AccountDTO;
import com.hubin.services.AccessService;
import com.hubin.utils.JwtUtils;
import com.hubin.utils.RequestResponseUtil;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/14 13:48
 */
@RestController
@RequestMapping("/account")
//@CrossOrigin("http://localhost:63343")
public class LoginController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    AccessService accessService;


    //访问login时跳到login.html
//    @RequestMapping("login")
//    public String login() {
//        return "login";
//    }

    @PostMapping("/login")
    public ResponseResult accountLogin(HttpServletRequest request) {
        Map<String, String> params = RequestResponseUtil.getRequestBodyMap(request);
        String appId = params.get("username");
        // 根据appId获取其对应所拥有的角色(这里设计为角色对应资源，没有权限对应资源)
        String roles = accessService.getSysUserRole(appId);
        // 时间以秒计算,token有效刷新时间是token有效过期时间的2倍
        long refreshPeriodTime = 36000L;
        String jwt = JwtUtils.crateToken(UUID.randomUUID().toString(), appId,
                "token-server", refreshPeriodTime >> 1, roles, null, SignatureAlgorithm.HS512);
        // 将签发的JWT存储到Redis： {JWT-SESSION-{appID} , jwt}
        redisTemplate.opsForValue().set("JWT-SESSION-" + appId, jwt, refreshPeriodTime, TimeUnit.SECONDS);
        AccountDTO accountDTO = accessService.accessSysUser(appId);
        if(Objects.isNull(accountDTO)){
            return ResponseResult.failed("登陆失败，用户名、密码不正确");
        }
        accountDTO.setPassword(null);
        accountDTO.setSalt(null);

        Map<String, Object> map = new HashMap<>();
        map.put("jwt", jwt);
        map.put("user", accountDTO);
        return ResponseResult.success("登陆成功", map);
    }

    @RequestMapping("loginOut")
    public ResponseResult loginOut(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return ResponseResult.success("退出成功", "");
    }

    @GetMapping("403")
    public String forbid() {
        return "403";
    }
}

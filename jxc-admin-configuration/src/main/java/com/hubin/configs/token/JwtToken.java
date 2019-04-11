package com.hubin.configs.token;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * <br>
 *
 * @author hubin
 * @title: JWT token
 * @description:
 * @date: 2019/3/22 20:36
 */
@Data
public class JwtToken implements AuthenticationToken {

    private static final long serialVersionUID = -7416322330402970896L;
    /**
     * 用户的标识
     */
    private String appId;
    /**
     * 用户的IP
     */
    private String ipHost;
    /**
     * 设备信息
     */
    private String deviceInfo;
    /**
     * json web token值
     */
    private String jwt;

    public JwtToken(String ipHost, String deviceInfo, String jwt, String appId) {
        this.ipHost = ipHost;
        this.deviceInfo = deviceInfo;
        this.jwt = jwt;
        this.appId = appId;
    }

    @Override
    public Object getPrincipal() {
        return this.appId;
    }

    @Override
    public Object getCredentials() {
        return this.jwt;
    }
}

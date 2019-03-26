package com.hubin.dto.system;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: JWT_DTO
 * @description:
 * @date: 2019/3/21 21:37
 */
@Data
public class JwtDTO implements Serializable {
    private static final long serialVersionUID = -4950209622572507061L;

    /**
     * 令牌id
     */
    private String tokenId;
    /**
     * 客户标识（用户名、账号）
     */
    private String appId;
    /**
     * 签发者(JWT令牌此项有值)
     */
    private String issuer;
    /**
     * 签发时间
     */
    private Date issuedAt;
    /**
     * 接收方(JWT令牌此项有值)
     */
    private String audience;
    /**
     * 访问主张-角色(JWT令牌此项有值)
     */
    private String roles;
    /**
     * 访问主张-资源(JWT令牌此项有值)
     */
    private String perms;
    /**
     * 客户地址
     */
    private String host;

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public void setHost(String host) {
        this.host = host;
    }
}

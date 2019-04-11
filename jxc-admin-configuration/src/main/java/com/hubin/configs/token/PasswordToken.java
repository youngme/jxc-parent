package com.hubin.configs.token;

import com.hubin.utils.AesUtil;
import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/22 21:22
 */
@Data
public class PasswordToken implements AuthenticationToken {
    private static final long serialVersionUID = 7182911671115868249L;
    private String appId;
    private String password;
    private String timestamp;
    private String host;
    private String tokenKey;

    public PasswordToken(String appId, String password, String timestamp, String host,String tokenKey) throws Exception {
        this.appId = appId;
        this.timestamp = timestamp;
        this.host = host;
        this.password = AesUtil.aesDecode(password,tokenKey);
        this.tokenKey = tokenKey;

    }

    @Override
    public Object getPrincipal() {
        return this.appId;
    }

    @Override
    public Object getCredentials() {
        return this.password;
    }

}

package com.hubin.dto.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * <br>
 *
 * @author hubin
 * @title: 用户DTO
 * @description: com.hubin.domain.system.SysUser
 * @date: 2019/3/24 10:22
 */
@Data
@ToString
public class AccountDTO implements Serializable {
    private static final long serialVersionUID = -1619162742292553506L;

    private Long uid;

    private String appId;

    private String password;

    private String salt;


    public AccountDTO(Long uid, String appId, String password, String salt) {
        this.uid = uid;
        this.appId = appId;
        this.password = password;
        this.salt = salt;
    }

}

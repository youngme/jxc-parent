package com.hubin.domain.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统用户
 * @description:
 * @date: 2019/3/23 22:50
 */
@Data
@ToString
public class SysUser implements Serializable {

    private static final long serialVersionUID = -5577907904647536148L;

    private Long uid;

    private String username;

    private String password;

    private String salt;

    private String realName;

    private String avatar;

    private String phone;

    private String email;

    private Byte sex;

    private Byte status;

    private Date createTime;

    private Date updateTime;

    private Byte createWhere;

    public Long getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
}

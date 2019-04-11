package com.hubin.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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

    @TableId(value = "uid",type = IdType.AUTO)
    private Long uid;

    private String username;

    private String password;

    private String salt;

    private String realName;

    private String avatar;

    private String phone;

    private String email;

    private int sex;

    private int status;

    private Date createTime;

    private Date updateTime;

    private Long createWhere;

    private Long roleId;

    private String name;

}

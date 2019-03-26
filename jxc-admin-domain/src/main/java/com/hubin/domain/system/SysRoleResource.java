package com.hubin.domain.system;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: 角色资源关系
 * @description:
 * @date: 2019/3/23 22:49
 */
@Data
@ToString
public class SysRoleResource implements Serializable {

    private static final long serialVersionUID = -6282750113448913298L;

    private Long id;

    private Long roleId;

    private Long resourceId;

    private Date createTime;

    private Date updateTime;


}

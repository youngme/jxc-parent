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
 * @title: 资源Domain
 * @description:
 * @date: 2019/3/23 22:47
 */
@Data
public class SysResource implements Serializable {

    private static final long serialVersionUID = -7005779194736273602L;

    @TableId(type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    private Long parentId;

    private String uri;

    private Short type;

    private String method;

    private String icon;

    private Short status;

    private Date createTime;

    private Date updateTime;

    private Long roleId;

    private String roleName;
}

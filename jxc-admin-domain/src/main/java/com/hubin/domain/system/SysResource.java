package com.hubin.domain.system;

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
@ToString
public class SysResource implements Serializable {

    private static final long serialVersionUID = -7005779194736273602L;

    private Long id;

    private String code;

    private String name;

    private Integer parentId;

    private String uri;

    private Short type;

    private String method;

    private String icon;

    private Short status;

    private Date createTime;

    private Date updateTime;
}

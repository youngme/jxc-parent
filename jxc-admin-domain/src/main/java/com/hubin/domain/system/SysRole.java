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
 * @title: 角色
 * @description:
 * @date: 2019/3/23 22:49
 */
@Data
@ToString
public class SysRole implements Serializable {

    private static final long serialVersionUID = -7346866707669105553L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String code;

    private String name;

    private Short status;

    private Date createTime;

    private Date updateTime;

}

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
 * @title: 用户角色关系
 * @description:
 * @date: 2019/3/23 22:57
 */
@Data
@ToString
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = -3094606878844464421L;

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long roleId;

    private Date createTime;

    private Date updateTime;

}

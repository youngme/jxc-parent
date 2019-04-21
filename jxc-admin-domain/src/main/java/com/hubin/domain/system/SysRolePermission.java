package com.hubin.domain.system;

import lombok.Data;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Set;

/**
 * <br>
 *
 * @author hubin
 * @title: 获取用户的角色权限信息
 * @description:
 * @date: 2019/3/23 22:58
 */
@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 7077901417300561921L;

    /**
     * 资源URL
     */
    private String url;
    /**
     * 访问资源所需要的角色列表，多个列表用逗号间隔
     */
    private String needRoles;


}

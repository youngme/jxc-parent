package com.hubin.configs.support;

import com.hubin.domain.system.SysRolePermission;
import com.hubin.utils.JwtUtils;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * <br>
 *
 * @author hubin
 * @title: 用户角色权限扩展
 * @description:
 * @date: 2019/3/23 23:05
 */
public class RolePermSupport {


    private static final String ANON_ROLE = "role_anon";
    /**
     * description 将url needRoles 转化成shiro可识别的过滤器链：url=jwt[角色1、角色2、角色n]
     *
     * @return java.lang.StringBuilder
     */
    public static StringBuilder toFilterChain(SysRolePermission sysRolePermission) {

        if (Objects.isNull(sysRolePermission.getUrl()) || sysRolePermission.getUrl().isEmpty()) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Set<String> setRole = JwtUtils.split(sysRolePermission.getNeedRoles());

        // 约定若role_anon角色拥有此uri资源的权限,则此uri资源直接访问不需要认证和权限
        if (!StringUtils.isEmpty(sysRolePermission.getNeedRoles()) && setRole.contains(ANON_ROLE)) {
            stringBuilder.append("anon");
        }
        //  其他自定义资源uri需通过jwt认证和角色认证
        if (!StringUtils.isEmpty(sysRolePermission.getNeedRoles()) && !setRole.contains(ANON_ROLE)) {
            stringBuilder.append("jwt" + "[" + sysRolePermission.getNeedRoles() + "]");
        }

        return stringBuilder.length() > 0 ? stringBuilder : null;
    }
}

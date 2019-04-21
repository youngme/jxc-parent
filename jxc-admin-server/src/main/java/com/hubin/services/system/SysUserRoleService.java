package com.hubin.services.system;

import com.hubin.domain.system.SysUserRole;

/**
 * <br>
 *
 * @author hubin
 * @title: 用户角色关系
 * @description:
 * @date: 2019/4/2 10:30
 */
public interface SysUserRoleService {

    boolean saveSysUserRole(SysUserRole sysUserRole);

    boolean updateSysUserRole(SysUserRole sysUserRole);

    boolean removeSysUserRole(Long id);
}

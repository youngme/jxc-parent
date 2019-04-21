package com.hubin.services;

import com.hubin.domain.system.SysRole;
import com.hubin.utils.pages.PageUtils;
import com.hubin.utils.pages.PageParam;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统角色
 * @description:
 * @date: 2019/3/24 10:12
 */
public interface SysRoleService {

    boolean authorityRoleResource(int roleId, int resourceId);

    boolean addRole(SysRole role);

    boolean updateRole(SysRole role);

    boolean deleteRoleByRoleId(Long roleId);

    boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId);

    List<SysRole> getRoleList();

    PageUtils queryPage(PageParam pageParam);


}

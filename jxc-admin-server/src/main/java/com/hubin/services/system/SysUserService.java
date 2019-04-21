package com.hubin.services.system;

import com.hubin.domain.system.SysUser;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.pages.PageUtils;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统用户
 * @description:
 * @date: 2019/3/24 10:13
 */
public interface SysUserService {

    String loadAccountRole(String appId);
    
    List<SysUser> getUserList();

    List<SysUser> getUserListByRoleId(Long roleId);
    
    boolean authorityUserRole(String appId, Long roleId);

    boolean deleteAuthorityUserRole(Long uid, Long roleId);
    
    SysUser getUserByAppId(String appId);
    
    List<SysUser> getNotAuthorityUserListByRoleId(Long roleId);

    PageUtils queryPage(PageParam pageParam);

    boolean saveUser(SysUser sysUser);

    boolean removeUser(Long uid);

    boolean updateUser(SysUser sysUser);

    List<SysUser> getUserByRole();
}

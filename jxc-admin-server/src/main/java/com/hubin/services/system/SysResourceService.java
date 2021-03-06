package com.hubin.services.system;

import com.hubin.domain.system.SysResource;
import com.hubin.domain.system.SysRolePermission;
import com.hubin.dto.system.SysResourceDTO;
import com.hubin.utils.pages.PageUtils;
import com.hubin.utils.pages.PageParam;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 资源服务
 * @description:
 * @date: 2019/3/24 10:09
 */
public interface SysResourceService {
    
    List<SysResource> getAuthorityMenusByUid(Long uid);

    List<SysResource> getMenus();

    Boolean addMenu(SysResource menu);

    Boolean modifyMenu(SysResource menu);

    Boolean deleteMenuByMenuId(Long menuId);

    List<SysResource> getApiTeamList();

    List<SysResource> getApiList();
    
    List<SysResource> getApiListByTeamId(Long teamId);

    List<SysResource> getAuthorityApisByRoleId(Long roleId);
    
    List<SysResource> getAuthorityMenusByRoleId(Long roleId);
    
    List<SysResource> getNotAuthorityApisByRoleId(Long roleId);
    
    List<SysResource> getNotAuthorityMenusByRoleId(Long roleId);

    List<SysRolePermission> selectRoleRules();


    PageUtils queryPageList(PageParam pageParam, SysResourceDTO sysResourceDTO);

}

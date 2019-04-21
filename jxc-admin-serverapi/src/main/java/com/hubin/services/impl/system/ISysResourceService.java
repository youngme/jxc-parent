package com.hubin.services.impl.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysResource;
import com.hubin.domain.system.SysRolePermission;
import com.hubin.domain.system.SysRoleResource;
import com.hubin.dto.system.SysResourceDTO;
import com.hubin.factor.system.SysResourceFactor;
import com.hubin.factor.system.SysRoleResourceFactor;
import com.hubin.services.system.SysResourceService;
import com.hubin.utils.ResultFormat;
import com.hubin.utils.pages.PageUtils;
import com.hubin.utils.pages.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统资源
 * @description:
 * @date: 2019/3/24 10:17
 */
@Service
public class ISysResourceService extends ServiceImpl<SysResourceFactor, SysResource> implements SysResourceService {

    @Autowired
    SysRoleResourceFactor roleResourceFactor;

    @Override
    public List<SysResource> getAuthorityMenusByUid(Long uid) {
        return baseMapper.selectAuthorityMenusByUid(uid);
    }

    @Override
    public List<SysResource> getMenus() {
        return baseMapper.selectMenus();
    }

    @Override
    @Transactional
    public Boolean addMenu(SysResource menu) {
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        if (ResultFormat.format(baseMapper.insert(menu))) {
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setCreateTime(new Date());
            sysRoleResource.setUpdateTime(new Date());
            sysRoleResource.setResourceId(menu.getId());
            sysRoleResource.setRoleId(menu.getRoleId());
            return ResultFormat.format(roleResourceFactor.insert(sysRoleResource));
        }
        return Boolean.FALSE;

    }

    @Override
    public Boolean modifyMenu(SysResource menu) {
        return null;
    }

    @Override
    public Boolean deleteMenuByMenuId(Long menuId) {
        return null;
    }

    @Override
    public List<SysResource> getApiTeamList() {
        return baseMapper.selectApiTeamList();
    }

    @Override
    public List<SysResource> getApiList() {
        return baseMapper.selectApiList();
    }

    @Override
    public List<SysResource> getApiListByTeamId(Long teamId) {
        return baseMapper.selectApiListByTeamId(teamId);
    }

    @Override
    public List<SysResource> getAuthorityApisByRoleId(Long roleId) {
        return baseMapper.selectApisByRoleId(roleId);
    }

    @Override
    public List<SysResource> getAuthorityMenusByRoleId(Long roleId) {
        return baseMapper.selectMenusByRoleId(roleId);
    }

    @Override
    public List<SysResource> getNotAuthorityApisByRoleId(Long roleId) {
        return baseMapper.selectNotAuthorityApisByRoleId(roleId);
    }

    @Override
    public List<SysResource> getNotAuthorityMenusByRoleId(Long roleId) {
        return baseMapper.selectNotAuthorityMenusByRoleId(roleId);
    }

    @Override
    public List<SysRolePermission> selectRoleRules() {
        return baseMapper.selectRoleRules();
    }

    /**
     * 分页
     * @param pageParam
     * @param sysResourceDTO
     * @return
     */
    @Override
    public PageUtils queryPageList(PageParam pageParam, SysResourceDTO sysResourceDTO) {
        return PageUtils
                .build()
                .rows(baseMapper.queryPageList(pageParam, sysResourceDTO))
                .total(baseMapper.getPageTotal(sysResourceDTO));
    }

}

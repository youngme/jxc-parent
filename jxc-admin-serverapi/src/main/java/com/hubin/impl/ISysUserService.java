package com.hubin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysUser;
import com.hubin.domain.system.SysUserRole;
import com.hubin.factor.SysUserFactor;
import com.hubin.services.SysUserRoleService;
import com.hubin.utils.ResultFormat;
import com.hubin.utils.ShiroEnctryption;
import com.hubin.utils.pages.PageParam;
import com.hubin.services.SysUserService;
import com.hubin.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <br>
 *
 * @author hubin
 * @title:系统用户
 * @description:
 * @date: 2019/3/19 14:35
 */
@Service
public class ISysUserService extends ServiceImpl<SysUserFactor, SysUser> implements SysUserService {

    private final static String DEFAULT_PASSWORD = "888888";

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Override
    public PageUtils queryPage(PageParam pageParam) {
        List<SysUser> list = baseMapper.queryPage(pageParam);
        Long total = baseMapper.queryPageTotal();
        return new PageUtils(total, list);
    }

    @Override
    public String loadAccountRole(String appId) {
        return baseMapper.selectUserRoles(appId);
    }

    @Override
    public List<SysUser> getUserList() {
        return baseMapper.selectUserList();
    }

    @Override
    public List<SysUser> getUserListByRoleId(Long roleId) {
        return baseMapper.selectUserListByRoleId(roleId);
    }

    @Override
    public boolean authorityUserRole(String appId, Long roleId) {
        return false;
    }

    @Override
    public boolean deleteAuthorityUserRole(Long uid, Long roleId) {
        return false;
    }

    @Override
    public SysUser getUserByAppId(String appId) {
        return baseMapper.selectByUniqueKey(appId);
    }

    @Override
    public List<SysUser> getNotAuthorityUserListByRoleId(Long roleId) {
        return baseMapper.selectUserListExtendByRoleId(roleId);
    }

    @Override
    @Transactional
    public boolean saveUser(SysUser sysUser) {
        //新增用户
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        sysUser.setSalt(ShiroEnctryption.getSalt());
        sysUser.setCreateWhere(1L);
        sysUser.setPassword(ShiroEnctryption.getPwdShaHash(DEFAULT_PASSWORD));
        System.err.println(sysUser.toString());
        if (ResultFormat.format(baseMapper.insertUser(sysUser))) {
            //新增用户角色关系
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUpdateTime(new Date());
            sysUserRole.setRoleId(sysUser.getRoleId());
            sysUserRole.setUserId(sysUser.getUid());
            System.err.println(sysUserRole.toString());
            return sysUserRoleService.saveSysUserRole(sysUserRole);
        }

        return Boolean.FALSE;
    }

    @Override
    public boolean removeUser(Long uid) {
        return  ResultFormat.format(baseMapper.deleteById(uid));
    }

    @Override
    public boolean updateUser(SysUser sysUser) {
        return ResultFormat.format(baseMapper.updateById(sysUser));
    }

    @Override
    public List<SysUser> getUserByRole() {
        return baseMapper.getUserByRole();
    }
}

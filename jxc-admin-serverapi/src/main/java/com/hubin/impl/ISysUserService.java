package com.hubin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysUser;
import com.hubin.factor.SysUserFactor;
import com.hubin.common.pages.PageParam;
import com.hubin.services.SysUserService;
import com.hubin.utils.PageUtils;
import org.springframework.stereotype.Service;

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
}

package com.hubin.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysRole;
import com.hubin.factor.SysRoleFactor;
import com.hubin.services.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统角色
 * @description:
 * @date: 2019/3/24 10:19
 */
@Service
public class ISysRoleService extends ServiceImpl<SysRoleFactor, SysRole> implements SysRoleService {
    @Override
    public boolean authorityRoleResource(int roleId, int resourceId) {
        return false;
    }

    @Override
    public boolean addRole(SysRole role) {
        return false;
    }

    @Override
    public boolean updateRole(SysRole role) {
        return false;
    }

    @Override
    public boolean deleteRoleByRoleId(Integer roleId) {
        return false;
    }

    @Override
    public boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId) {
        return false;
    }

    @Override
    public List<SysRole> getRoleList() {
        return baseMapper.selectRoles();
    }
}

package com.hubin.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysUserRole;
import com.hubin.factor.SysUserRoleFactor;
import com.hubin.services.SysUserRoleService;
import com.hubin.utils.ResultFormat;
import org.springframework.stereotype.Service;

/**
 * <br>
 *
 * @author hubin
 * @title: 用户角色关系
 * @description:
 * @date: 2019/4/2 10:31
 */
@Service
public class ISysUserRoleService extends ServiceImpl<SysUserRoleFactor, SysUserRole> implements SysUserRoleService {

    @Override
    public boolean saveSysUserRole(SysUserRole sysUserRole) {
        return ResultFormat.format(baseMapper.insert(sysUserRole));
    }

    @Override
    public boolean removeSysUserRole(Long id) {
        return ResultFormat.format(baseMapper.deleteById(id));
    }

    @Override
    public boolean updateSysUserRole(SysUserRole sysUserRole) {
        return ResultFormat.format(baseMapper.updateById(sysUserRole));
    }

}

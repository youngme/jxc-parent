package com.hubin.services.impl.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.system.SysRole;
import com.hubin.factor.system.SysRoleFactor;
import com.hubin.services.system.SysRoleService;
import com.hubin.utils.pages.PageUtils;
import com.hubin.utils.ResultFormat;
import com.hubin.utils.pages.PageParam;
import org.springframework.stereotype.Service;

import java.util.Date;
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
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        return baseMapper.insert(role)>0?Boolean.TRUE:Boolean.FALSE;
    }

    @Override
    public boolean updateRole(SysRole role) {
        return false;
    }

    @Override
    public boolean deleteRoleByRoleId(Long roleId) {
        return ResultFormat.format(baseMapper.deleteById(roleId));
    }

    @Override
    public boolean deleteAuthorityRoleResource(Integer roleId, Integer resourceId) {
        return false;
    }

    @Override
    public List<SysRole> getRoleList() {
        return baseMapper.selectRoles();
    }

    @Override
    public PageUtils queryPage(PageParam pageParam) {
        List<SysRole> list = baseMapper.queryPage(pageParam);
        Long total = baseMapper.queryRolePageTotal();
        return PageUtils.build().rows(list).total(total);
    }
}

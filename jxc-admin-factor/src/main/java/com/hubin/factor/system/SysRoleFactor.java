package com.hubin.factor.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.system.SysRole;
import com.hubin.utils.pages.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统角色
 * @description:
 * @date: 2019/3/24 09:44
 */
@Mapper
public interface SysRoleFactor extends BaseMapper<SysRole> {

    /**
     * 查询角色列表
     * @return
     * @throws DataAccessException
     */
    List<SysRole> selectRoles() throws DataAccessException;

    /**
     * 查询角色分页
     * @param pageParam
     * @return
     */
    List<SysRole> queryPage(PageParam pageParam);

    Long queryRolePageTotal();
}

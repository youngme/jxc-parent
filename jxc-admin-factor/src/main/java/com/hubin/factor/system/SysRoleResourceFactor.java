package com.hubin.factor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.system.SysRoleResource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统角色资源关系
 * @description:
 * @date: 2019/3/24 09:48
 */
@Mapper
public interface SysRoleResourceFactor extends BaseMapper<SysRoleResource> {

    int deleteByUniqueKey(@Param("roleId") Integer roleId, @Param("resourceId") Integer resourceId) throws DataAccessException;
}

package com.hubin.factor.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.system.SysResource;
import com.hubin.domain.system.SysRolePermission;
import com.hubin.dto.system.SysResourceDTO;
import com.hubin.utils.pages.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 资源
 * @description:
 * @date: 2019/3/24 09:35
 */
@Mapper
public interface SysResourceFactor extends BaseMapper<SysResource> {

    List<SysRolePermission> selectRoleRules()  throws DataAccessException;

    List<SysResource> selectAuthorityMenusByUid(Long uid) throws DataAccessException;

    List<SysResource> selectMenus() throws DataAccessException;

    List<SysResource> selectApiTeamList() throws DataAccessException;

    List<SysResource> selectApiList() throws DataAccessException;

    List<SysResource> selectApiListByTeamId(Long teamId) throws DataAccessException;

    List<SysResource> selectApisByRoleId(Long roleId) throws DataAccessException;

    List<SysResource> selectMenusByRoleId(Long roleId) throws DataAccessException;

    List<SysResource> selectNotAuthorityApisByRoleId(Long roleId) throws DataAccessException;

    List<SysResource> selectNotAuthorityMenusByRoleId(Long roleId) throws DataAccessException;


    List<SysResource> queryPageList(@Param("page") PageParam pageParam,@Param("sysResource") SysResourceDTO sysResourceDTO);

    Long getPageTotal(SysResourceDTO sysResourceDTO);
}

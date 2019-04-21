package com.hubin.factor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.system.SysUser;
import com.hubin.utils.pages.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 用户
 * @description:
 * @date: 2019/3/24 09:50
 */
@Mapper
public interface SysUserFactor extends BaseMapper<SysUser> {

    int insertUser(SysUser sysUser);

    List<SysUser> selectUserList() throws DataAccessException;

    SysUser selectByAppId(String username) throws DataAccessException;

    SysUser selectByUserInfo(@Param("username") String username, @Param("password") String password) throws DataAccessException;

    String selectUserRoles(String username) throws DataAccessException;

    List<SysUser> selectUserListByRoleId(Long roleId);

    SysUser selectByUniqueKey(String username);

    List<SysUser> selectUserListExtendByRoleId(Long roleId);

    List<SysUser> queryPage(PageParam pageParam);

    Long queryPageTotal();

    List<SysUser> getUserByRole();
}

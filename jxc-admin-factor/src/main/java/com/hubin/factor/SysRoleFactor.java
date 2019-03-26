package com.hubin.factor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.system.SysRole;
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
     *
     * @return
     * @throws DataAccessException
     */
    List<SysRole> selectRoles() throws DataAccessException;
}

package com.hubin.factor.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.system.SysSalary;
import com.hubin.dto.system.SysSalaryDTO;
import com.hubin.utils.pages.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/4/8 11:51
 */
@Mapper
public interface SysSalaryFactor extends BaseMapper<SysSalary> {

    Integer getCountByMonth();

    List<SysSalary> queryPageList(@Param("page") PageParam pageParam, @Param("salary")SysSalaryDTO salaryDTO);

    Integer salaryProcedure(@Param("id") Long id);

}

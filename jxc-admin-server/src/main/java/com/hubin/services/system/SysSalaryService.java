package com.hubin.services.system;

import com.hubin.domain.system.SysSalary;
import com.hubin.dto.system.SalaryExcelDTO;
import com.hubin.dto.system.SysSalaryDTO;
import com.hubin.utils.pages.PageUtils;
import com.hubin.utils.pages.PageParam;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 薪资单
 * @description:
 * @date: 2019/4/8 11:50
 */
public interface SysSalaryService {

    void createSalary();

    boolean getCountByMonth();

    List<SalaryExcelDTO> getListByMonth(PageParam pageParam, SysSalaryDTO salaryDTO);

    PageUtils queryPage(PageParam pageParam, SysSalaryDTO salaryDTO);

    boolean updateSalary(SysSalary sysSalary);
}

package com.hubin.controller;

import com.hubin.domain.system.SysSalary;
import com.hubin.dto.system.SalaryExcelDTO;
import com.hubin.dto.system.SysSalaryDTO;
import com.hubin.services.system.SysSalaryService;
import com.hubin.utils.ConverterDate;
import com.hubin.utils.ResponseResult;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.poi.ExportExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <br>
 * 薪资单
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/4/8 16:43
 */
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    SysSalaryService sysSalaryService;

    @Autowired
    ConverterDate converterDate;

    /**
     * 分页
     * @param pageParam
     * @return
     */
    @GetMapping("/pageList")
    public ResponseResult pageJson(PageParam pageParam, SysSalaryDTO salaryDTO){
        return ResponseResult.success("数据获取成功",sysSalaryService.queryPage(pageParam,salaryDTO));
    }

    @PostMapping("/updateSalary")
    public ResponseResult updateSalary(SysSalary sysSalary) {
        return ResponseResult.result(sysSalaryService.updateSalary(sysSalary));
    }

    @GetMapping("/getExcel")
    public ResponseResult getExcel(HttpServletRequest request, HttpServletResponse response) {
        String createTime = request.getParameter("createTime");
        List<SalaryExcelDTO> list = sysSalaryService.getListByMonth(new PageParam().setPage(0).setLimit(10),
                new SysSalaryDTO().setCreateTime(converterDate.convert(createTime)));

        try {
            new ExportExcel<SalaryExcelDTO>().exportXlsxExcelForData(list, request, response,"finishRate");
            return ResponseResult.success("导出成功");
        } catch (Exception e) {
            return ResponseResult.failed("导出失败");
        }

    }
}

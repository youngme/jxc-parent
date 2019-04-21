package com.hubin.configs.quartz;

import com.hubin.services.system.SysSalaryService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: 薪资定时任务
 * @description:
 * @date: 2019/4/8 11:24
 */
public class SalaryTask implements Job {

    @Autowired
    SysSalaryService sysSalaryService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("工资单任务创建开始----" + sdf.format(new Date()));
        if (sysSalaryService.getCountByMonth()) {
            System.err.println("工资单任务已经创建");
        }else{
            sysSalaryService.createSalary();
        }
    }
}

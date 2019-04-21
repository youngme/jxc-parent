package com.hubin.dto.system;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <br>
 *
 * @author hubin
 * @title: 薪资ExcelDTO
 * @description:
 * @date: 2019/4/10 11:14
 */
@Getter
@Setter
@Accessors(chain = true)
public class SalaryExcelDTO implements Serializable {
    private static final long serialVersionUID = 6378002737833102629L;
    private String realName;//姓名
    private String attendanceDays;//出勤天数
    private String overtimeDays;//加班天数
    private BigDecimal basePay;//基本工资
    private BigDecimal seniorityPay;//工龄工资
    private BigDecimal allworkPay;//全勤工资
    private BigDecimal telephonePay;//话补
    private BigDecimal carPay;//车补
    private BigDecimal managePay;//店长管理工资
    private BigDecimal assistantPay;//店助
    private BigDecimal exhibitPay;//陈列费
    private BigDecimal overtimePay;//加班费
    private BigDecimal baseSum;//底薪小计
    private BigDecimal salesTask;//销售任务
    private BigDecimal salesFinish;//本月销售
    private String finishRateStr;//完成率百分比
    private BigDecimal finishRate;//完成率
    private BigDecimal performancePay;//绩效提成
    private BigDecimal performanceBonus;//绩效奖金
    private BigDecimal bonusSum;//奖金小计
    private BigDecimal salaryPay;//应发工资
    private BigDecimal deductFund;//扣除款项
    private BigDecimal practicalPay;//实发工资

}

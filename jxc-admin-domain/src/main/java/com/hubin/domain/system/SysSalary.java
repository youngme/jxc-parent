package com.hubin.domain.system;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: 薪资单
 * @description:
 * @date: 2019/4/8 10:28
 */
@Data
@Accessors(chain= true)
public class SysSalary implements Serializable {
    private static final long serialVersionUID = 9001790223709790866L;

    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    private Long uid;//用户ID
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
    private BigDecimal salesTask;//销售任务
    private BigDecimal salesFinish;//本月销售
    private BigDecimal finishRate;//完成率
    private BigDecimal performancePay;//绩效提成
    private BigDecimal performanceBonus;//绩效奖金
    private BigDecimal salaryPay;//应发工资
    private BigDecimal deductFund;//扣除款项
    private BigDecimal practicalPay;//实发工资
    private Date createTime;//创建时间
    private Date updateTime;//更新时间




    public BigDecimal salaryPaysPlus() {
        return this.basePay.add(this.allworkPay).add(this.seniorityPay).add(this.telephonePay).add(this.carPay)
                .add(this.managePay).add(this.assistantPay).add(this.exhibitPay).add(this.performancePay)
                .add(this.performanceBonus).add(this.overtimePay);
    }

    public Long getId() {
        return id;
    }

    public BigDecimal practicalPaysPlus(BigDecimal deduct) {
        return this.salaryPaysPlus().subtract(deduct);
    }
}

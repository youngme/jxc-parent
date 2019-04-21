package com.hubin.domain.finance;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目支出
 * @description:
 * @date: 2019/4/20 09:57
 */
@Data
public class AuthItemCashExpense implements Serializable {
    private static final long serialVersionUID = -7425925722747938002L;

    @TableId(type = IdType.AUTO)
    private Long id;//主键
    private Long itemId;//主项目主键
    private String expenseName;//支出款项名称
    private Date createTime;//创建时间
    private String createPerson;//创建人
    private BigDecimal moneyExpense;//支出金额
    private int expenseType;//支出类型(0现金1欠款)
    private String remark;//备注
}

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
 * @title: 现金收入DO
 * @description:
 * @date: 2019/4/18 22:03
 */
@Data
public class AuthItemCashEarning implements Serializable {
    private static final long serialVersionUID = 54217067752047268L;

    @TableId(type = IdType.AUTO)
    private Long id;//主键
    private Long itemId;//主项目主键
    private String earningName;//入账款项名称
    private Date createTime;//创建时间
    private String createPerson;//创建人
    private BigDecimal moneyEarning;//入账金额
    private int earningType;//入账类型(0现金1欠款)
    private String remark;//备注
}

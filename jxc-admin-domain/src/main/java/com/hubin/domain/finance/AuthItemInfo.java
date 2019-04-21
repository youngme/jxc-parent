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
 * @title: 项目管理DO
 * @description:
 * @date: 2019/4/15 12:18
 */
@Data
public class AuthItemInfo implements Serializable {
    private static final long serialVersionUID = 3742026683385777455L;

    @TableId(type = IdType.AUTO)
    private Long id;
    private String itemName;//项目名称
    private String companyName;//公司名称
    private Date createTime;//创建时间
    private int status;//状态(0完成1进行中)
    private BigDecimal earning;//项目收入
    private BigDecimal expense;//项目支出
}

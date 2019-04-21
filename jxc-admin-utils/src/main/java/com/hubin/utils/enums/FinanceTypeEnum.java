package com.hubin.utils.enums;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目资金入账类型
 * @description:
 * @date: 2019/4/19 22:21
 */
public enum FinanceTypeEnum {
    MONEYIN(0,"现金入账"),
    DEBTIN(1,"欠款入账"),
    MATERIALSOUT(2,"材料采购"),
    REPASTOUT(3,"灶务采购"),
    BACKMANOUT(4,"杂工工资"),
    MANAGEOUT(5,"管理工资"),
    TRAVELOUT(6,"差旅招待"),
    WORKOUT(7,"办公用品"),
    CAROUT(8,"机械消耗"),
    OILCONSUMPTIONOUT(9,"机械耗油"),
    ELSEEXPENSEOUT(10,"其他费用");

    private int value;
    private String title;

    FinanceTypeEnum(int value, String title) {
        this.value = value;
        this.title = title;
    }

    public int getValue() {
        return value;
    }
}

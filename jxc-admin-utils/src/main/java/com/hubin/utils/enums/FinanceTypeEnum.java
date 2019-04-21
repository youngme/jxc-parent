package com.hubin.utils.enums;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目资金入账类型
 * @description:
 * @date: 2019/4/19 22:21
 */
public enum CashTypeEnum {
    MONEY(0),//现金
    DEBT(1);//欠款

    private int value;

    CashTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

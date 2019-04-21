package com.hubin.utils.enums;

/**
 * <br>
 *
 * @author hubin
 * @title: 数据源名称
 * @description:
 * @date: 2019/4/15 10:27
 */
public enum DBTypeEnum {

    defaultSource("jxc"),
    jxcDataSource("jxc"),
    financeDatasource("finance");

    private String value;

    DBTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}

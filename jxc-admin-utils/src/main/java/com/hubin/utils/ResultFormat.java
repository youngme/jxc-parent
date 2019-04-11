package com.hubin.utils;

/**
 * <br>
 *
 * @author hubin
 * @title: 增删改操作结
 * @description:
 * @date: 2019/4/4 10:49
 */
public class ResultFormat {

    public static Boolean format(int result) {
        return result > 0 ? Boolean.TRUE : Boolean.FALSE;
    }
}

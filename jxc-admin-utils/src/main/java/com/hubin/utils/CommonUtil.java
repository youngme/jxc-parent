package com.hubin.utils;

import java.util.Random;

/**
 * <br>
 * 高频方法工具类
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/22 21:21
 */
public class CommonUtil {

    /**
     * description 获取指定位数的随机数
     *
     * @param length 1
     * @return java.lang.String
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}

package com.hubin.common;

import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/16 23:01
 */
public enum CodeMsgEnum {
    SUCCESS(100200, "请求受理成功！"),
    INFO(100204, "请求受理成功，响应数据为空！"),
    UNAUTHENTIC(100401, "未认证，请先登录！"),
    TOKENERROR(100402, "未认证，Token错误！"),
    UNAUTHORIZED(100403, "未授权，权限不足！"),
    NOTFOUND(100404, "服务器未找到资源"),
    ERROR(100500, "服务器发生错误！"),
    LOSEREQUEST(1111,"无效的请求"),
    TOKENFAILE(1000,"签发动态秘钥失败");


    private int code;
    private String msg;

    CodeMsgEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public int getcode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}

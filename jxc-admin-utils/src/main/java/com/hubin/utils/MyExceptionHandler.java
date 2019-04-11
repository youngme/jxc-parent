package com.hubin.utils;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/17 15:22
 */
@ControllerAdvice
public class MyExceptionHandler {
    /**
     * 未认证异常处理
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UnauthenticatedException.class)
    public ResponseResult authenticationException() {
        return ResponseResult.unAuthen();
    }

    /**
     * 未授权异常处理
     *
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseResult authorizationException() {
        return ResponseResult.unAuthor();
    }
}

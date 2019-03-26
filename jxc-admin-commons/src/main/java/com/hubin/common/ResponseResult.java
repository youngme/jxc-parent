package com.hubin.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/16 12:03
 */
@Getter
@Setter
@ToString
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = -4273849570977994915L;
    private Integer code;

    private String msg;

    private Object data;

    private ResponseResult(Integer code,String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success(String msg,Object data){
        ResponseResult result = new ResponseResult(HttpStatus.OK.value(),msg,data);
        return result;
    }

    public static ResponseResult failed(String msg) {
        ResponseResult result = new ResponseResult(HttpStatus.FAILED_DEPENDENCY.value(),msg,"");
        return result;
    }
    public static ResponseResult forbid(String msg,Object data) {
        ResponseResult result = new ResponseResult(HttpStatus.FAILED_DEPENDENCY.value(),msg,data);
        return result;
    }

    public static ResponseResult unAuthor() {
        return new ResponseResult(CodeMsgEnum.UNAUTHORIZED.getcode(),CodeMsgEnum.UNAUTHORIZED.getMsg(),"");
    }

    public static ResponseResult unAuthen() {
        return new ResponseResult(CodeMsgEnum.UNAUTHENTIC.getcode(),CodeMsgEnum.UNAUTHENTIC.getMsg(),"");
    }

    public static ResponseResult res(int code,String msg) {
        ResponseResult result = new ResponseResult(HttpStatus.FAILED_DEPENDENCY.value(),msg,"");
        return result;
    }
}

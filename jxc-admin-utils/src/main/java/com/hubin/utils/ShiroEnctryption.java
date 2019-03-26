package com.hubin.utils;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * <br>
 *
 * @author hubin
 * @title:密码加密
 * @description:
 * @date: 2019/3/11 20:57
 */
public class ShiroEnctryption{

    private static final String SALT = "Mis.WuDi.Is.My.Love.1994.03.27";

    private static String salt=Base64.encodeToString(SALT.getBytes());

    private final int COUNT = 1024;

    private String password;

    public ShiroEnctryption() {
    }


//生成Salt的值


    private ShiroEnctryption(String password) {
        this.password = password;
    }



    public static String getPwdShaHash(String pwd) {
        Object obj = new Sha256Hash(pwd, salt).toHex();
        return String.valueOf(obj);
    }


    public static String getSalt() {
        return salt;
    }


}

package com.hubin.utils.pages;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/9 23:40
 */
@Getter
@Setter
public class PageUser {

    private Long id;

    private String userName;

    private String phoneNumber;

    private String nickname;

    private int sex;

    private Date createTime;

    private String createPerson;

    private int deleteFlag;
}

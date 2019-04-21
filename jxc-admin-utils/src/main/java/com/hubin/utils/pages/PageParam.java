package com.hubin.utils.pages;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <br>
 *
 * @author hubin
 * @title: 分页参数
 * @description:
 * @date: 2019/3/20 08:28
 */
@Setter
@Getter
@Accessors(chain = true)
public class PageParam implements Serializable {

    private static final long serialVersionUID = -6490594103744847740L;
    int limit;

    int page;

    public int getPage() {
        if((page-1)<0)return 0;
        return (page-1)*limit;
    }

}

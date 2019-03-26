package com.hubin.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/3/7 18:24
 */
@Getter
@Setter
public class PageUtils implements Serializable {

    private static final long serialVersionUID = -2667999107807866625L;


    private Long total;

    private List<?> rows;

    public PageUtils(Long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }
}

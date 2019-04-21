package com.hubin.utils.pages;

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

    private static  PageUtils pageUtils;

    private Long total;

    private List<?> rows;

    private PageUtils() {

    }

    public static PageUtils build() {
        pageUtils =new PageUtils();
        return pageUtils;
    }

    public PageUtils total(Long total) {
        pageUtils.setTotal(total);
        return pageUtils;
    }

    public PageUtils rows(List<?> rows) {
        pageUtils.setRows(rows);
        return pageUtils;
    }

}

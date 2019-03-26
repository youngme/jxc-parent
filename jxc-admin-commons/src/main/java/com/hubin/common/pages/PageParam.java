package com.hubin.common.pages;

import lombok.Data;

/**
 * <br>
 *
 * @author hubin
 * @title: 分页参数
 * @description:
 * @date: 2019/3/20 08:28
 */
@Data
public class PageParam {

    int limit;

    int page;

    public int getPage() {
        if((page-1)<0)return 0;
        return page-1;
    }
}

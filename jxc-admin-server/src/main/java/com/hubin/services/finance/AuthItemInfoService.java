package com.hubin.services.finance;

import com.hubin.domain.finance.AuthItemInfo;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.pages.PageUtils;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目管理Service
 * @description:
 * @date: 2019/4/15 12:35
 */
public interface AuthItemInfoService {

    List<AuthItemInfo> getItemList();

    PageUtils queryPage(PageParam pageParam);
}

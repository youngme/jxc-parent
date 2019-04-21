package com.hubin.services.finance;

import com.hubin.domain.finance.AuthItemCashEarning;
import com.hubin.utils.enums.FinanceTypeEnum;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.pages.PageUtils;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目款入账Service
 * @description:
 * @date: 2019/4/18 22:18
 */
public interface AuthItemCashEarningService {

    /**
     * 入账分页
     * @param pageParam
     * @param cash
     * @return
     */
    PageUtils queryPageCash(PageParam pageParam, AuthItemCashEarning cash);

    /**
     * 入账
     * @param authItemCashEarning
     * @return
     */
    Boolean saveCash(AuthItemCashEarning authItemCashEarning);
}

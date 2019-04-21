package com.hubin.services.finance;

import com.hubin.domain.finance.AuthItemCashExpense;
import com.hubin.utils.enums.FinanceTypeEnum;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.pages.PageUtils;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目支出Service
 * @description:
 * @date: 2019/4/20 10:39
 */
public interface AuthItemCashExpenseService {
    /**
     * 支出分页
     * @param pageParam
     * @param cash
     * @return
     */
    PageUtils queryPageExpense(PageParam pageParam, AuthItemCashExpense cash);

    /**
     * 支出保存
     * @param expense
     * @return
     */
    Boolean saveExpense(AuthItemCashExpense expense);



}

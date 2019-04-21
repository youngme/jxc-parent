package com.hubin.services.impl.finance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.finance.AuthItemCashExpense;
import com.hubin.factor.finance.AuthItemCashExpenseFactor;
import com.hubin.services.finance.AuthItemCashExpenseService;
import com.hubin.utils.ResultFormat;
import com.hubin.utils.annotation.DefineDataSourceAnnotation;
import com.hubin.utils.enums.DBTypeEnum;
import com.hubin.utils.enums.FinanceTypeEnum;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.pages.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目财务支出
 * @description:
 * @date: 2019/4/20 11:23
 */
@Service
public class IAuthItemCashExpenseService extends ServiceImpl<AuthItemCashExpenseFactor, AuthItemCashExpense>
        implements AuthItemCashExpenseService {
    /**
     * 支出分页
     * @param pageParam
     * @param expense
     * @return
     */
    @Override
    @DefineDataSourceAnnotation(DBTypeEnum.financeDatasource)
    public PageUtils queryPageExpense(PageParam pageParam, AuthItemCashExpense expense) {
        List<AuthItemCashExpense> list = baseMapper.queryPage(pageParam,expense);
        Long total = baseMapper.queryPageTotal(expense);
        return PageUtils.build().rows(list).total(total);
    }

    /**
     * 支出保存
     * @param expense
     * @return
     */
    @Override
    @DefineDataSourceAnnotation(DBTypeEnum.financeDatasource)
    public Boolean saveExpense(AuthItemCashExpense expense) {
        return ResultFormat.format(baseMapper.insert(expense));
    }
}

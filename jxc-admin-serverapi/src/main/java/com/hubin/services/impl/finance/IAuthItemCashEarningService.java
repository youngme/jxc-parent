package com.hubin.services.impl.finance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.finance.AuthItemCashEarning;
import com.hubin.factor.finance.AuthItemCashEarningFactor;
import com.hubin.services.finance.AuthItemCashEarningService;
import com.hubin.utils.ResultFormat;
import com.hubin.utils.annotation.DefineDataSourceAnnotation;
import com.hubin.utils.enums.FinanceTypeEnum;
import com.hubin.utils.enums.DBTypeEnum;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.pages.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目款入账IService
 * @description:
 * @date: 2019/4/18 22:20
 */
@Service
public class IAuthItemCashEarningService extends ServiceImpl<AuthItemCashEarningFactor, AuthItemCashEarning>
        implements AuthItemCashEarningService {

    /**
     * 现金入账分页
     * @param pageParam
     * @param cash
     * @return
     */
    @Override
    @DefineDataSourceAnnotation(DBTypeEnum.financeDatasource)
    public PageUtils queryPageCash(PageParam pageParam,AuthItemCashEarning cash) {
        List<AuthItemCashEarning> list = baseMapper.queryPage(pageParam,cash);
        Long total = baseMapper.queryPageTotal(cash);
        return PageUtils.build().rows(list).total(total);
    }

    /**
     * 现金入账
     * @param authItemCashEarning
     * @return
     */
    @Override
    @DefineDataSourceAnnotation(DBTypeEnum.financeDatasource)
    public Boolean saveCash(AuthItemCashEarning authItemCashEarning) {
        return ResultFormat.format(baseMapper.insert(authItemCashEarning));
    }

}

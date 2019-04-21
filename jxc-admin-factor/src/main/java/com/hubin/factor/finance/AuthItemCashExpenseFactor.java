package com.hubin.factor.finance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.finance.AuthItemCashExpense;
import com.hubin.utils.pages.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 支出
 * @description:
 * @date: 2019/4/20 10:15
 */
@Mapper
public interface AuthItemCashExpenseFactor extends BaseMapper<AuthItemCashExpense> {

    List<AuthItemCashExpense> queryPage(@Param("page") PageParam pageParam, @Param("expense") AuthItemCashExpense expense);

    Long queryPageTotal(AuthItemCashExpense expense);
}

package com.hubin.factor.finance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.finance.AuthItemCashEarning;
import com.hubin.utils.pages.PageParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 现金收入Factor
 * @description:
 * @date: 2019/4/18 22:11
 */
@Mapper
public interface AuthItemCashEarningFactor extends BaseMapper<AuthItemCashEarning> {

    List<AuthItemCashEarning> queryPage(@Param("page") PageParam pageParam, @Param("cash") AuthItemCashEarning cash);

    Long queryPageTotal(AuthItemCashEarning authItemCashEarning);
}

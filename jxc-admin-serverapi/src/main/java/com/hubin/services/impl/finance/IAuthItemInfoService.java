package com.hubin.services.impl.finance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.finance.AuthItemInfo;
import com.hubin.factor.finance.AuthItemInfoFactor;
import com.hubin.services.finance.AuthItemInfoService;
import com.hubin.utils.annotation.DefineDataSourceAnnotation;
import com.hubin.utils.enums.DBTypeEnum;
import com.hubin.utils.pages.PageParam;
import com.hubin.utils.pages.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目管理IService
 * @description:
 * @date: 2019/4/15 12:36
 */
@Service
public class IAuthItemInfoService extends ServiceImpl<AuthItemInfoFactor, AuthItemInfo> implements AuthItemInfoService {
    @Override
    @DefineDataSourceAnnotation(value = DBTypeEnum.financeDatasource)
    public List<AuthItemInfo> getItemList() {
        return baseMapper.selectItems();
    }


    @Override
    @DefineDataSourceAnnotation(value = DBTypeEnum.financeDatasource)
    public PageUtils queryPage(PageParam pageParam) {
        List<AuthItemInfo> list = baseMapper.queryPage(pageParam);
        Long total = baseMapper.queryPageTotal();
        return PageUtils.build().rows(list).total(total);
    }
}

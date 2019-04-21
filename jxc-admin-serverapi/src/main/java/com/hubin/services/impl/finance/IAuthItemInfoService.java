package com.hubin.impl.finance;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hubin.domain.finance.AuthItemInfo;
import com.hubin.factor.finance.AuthItemInfoFactor;
import com.hubin.services.finance.AuthItemInfoService;
import com.hubin.utils.annotation.DefineDataSourceAnnotation;
import com.hubin.utils.enums.DBTypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.sql.DataSourceDefinition;
import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/4/15 12:36
 */
@Service
public class IAuthItemInfoService extends ServiceImpl<AuthItemInfoFactor, AuthItemInfo> implements AuthItemInfoService {
    @Override
    public List<AuthItemInfo> getItemList() {
        return baseMapper.selectItems();
    }
}

package com.hubin.factor.finance;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hubin.domain.finance.AuthItemInfo;
import com.hubin.utils.pages.PageParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目管理Factor
 * @description:
 * @date: 2019/4/15 12:22
 */
@Mapper
public interface AuthItemInfoFactor extends BaseMapper<AuthItemInfo> {
    List<AuthItemInfo> selectItems();

    List<AuthItemInfo> queryPage(PageParam pageParam);

    Long queryPageTotal();
}

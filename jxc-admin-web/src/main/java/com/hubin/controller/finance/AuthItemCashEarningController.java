package com.hubin.controller.finance;

import com.hubin.domain.finance.AuthItemCashEarning;
import com.hubin.services.finance.AuthItemCashEarningService;
import com.hubin.utils.ResponseResult;
import com.hubin.utils.pages.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目款入账Controller
 * @description:
 * @date: 2019/4/18 23:43
 */
@RestController
@RequestMapping("/item/earning")
public class AuthItemCashEarningController {

    @Autowired
    AuthItemCashEarningService authItemCashEarningService;
    /**
     * 现金入账分页
     * @param pageParam
     * @return
     */
    @GetMapping("/pageList")
    public ResponseResult pageJsonCash(PageParam pageParam,AuthItemCashEarning cash){
        return ResponseResult.success("数据获取成功",
                authItemCashEarningService.queryPageCash(pageParam,cash));
    }

    /**
     * 现金入账
     * @param authItemCashEarning
     * @return
     */
    @PostMapping("/save")
    public ResponseResult saveCash(AuthItemCashEarning authItemCashEarning) {
        return ResponseResult.result(authItemCashEarningService.saveCash(authItemCashEarning));
    }

}

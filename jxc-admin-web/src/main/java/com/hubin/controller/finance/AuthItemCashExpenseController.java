package com.hubin.controller.finance;

import com.hubin.domain.finance.AuthItemCashExpense;
import com.hubin.services.finance.AuthItemCashExpenseService;
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
 * @title: 项目支出Controller
 * @description:
 * @date: 2019/4/20 11:30
 */
@RestController
@RequestMapping("/item/expense")
public class AuthItemCashExpenseController {

    @Autowired
    AuthItemCashExpenseService authItemCashExpenseService;

    /**
     * 材料采购支出分页
     * @param pageParam
     * @param expense
     * @return
     */
    @GetMapping("/pageList")
    public ResponseResult pageJsonMaterials(PageParam pageParam, AuthItemCashExpense expense){
        return ResponseResult.success("数据获取成功",
                authItemCashExpenseService.queryPageExpense(pageParam,expense));
    }

    /**
     * 材料采购支出
     * @param expense
     * @return
     */
    @PostMapping("/save")
    public ResponseResult saveMaterials(AuthItemCashExpense expense) {
        return ResponseResult.result(authItemCashExpenseService.saveExpense(expense));
    }


}

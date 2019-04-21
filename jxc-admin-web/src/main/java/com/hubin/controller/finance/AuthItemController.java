package com.hubin.controller.finance;

import com.hubin.services.finance.AuthItemInfoService;
import com.hubin.utils.ResponseResult;
import com.hubin.utils.pages.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <br>
 *
 * @author hubin
 * @title: 项目信息
 * @description:
 * @date: 2019/4/16 19:04
 */
@RestController
@RequestMapping("/item")
public class AuthItemController {

    @Autowired
    AuthItemInfoService authItemInfoService;

    @GetMapping("/list")
    public ResponseResult getItemList() {
        return ResponseResult.success("获取成功", authItemInfoService.getItemList());
    }


    /**
     * 分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/pageList")
    public ResponseResult pageJson(PageParam pageParam){
        return ResponseResult.success("数据获取成功",authItemInfoService.queryPage(pageParam));
    }

}

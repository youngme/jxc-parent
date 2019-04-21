package com.hubin.controller.system;

import com.hubin.domain.system.SysResource;
import com.hubin.dto.system.SysResourceDTO;
import com.hubin.services.system.SysResourceService;
import com.hubin.utils.ResponseResult;
import com.hubin.utils.pages.PageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title: 系统资源控制器
 * @description:
 * @date: 2019/4/12 16:32
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    SysResourceService resourceService;

    /**
     * 分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/pageList")
    public ResponseResult pageJson(PageParam pageParam, SysResourceDTO sysResourceDTO){
        return ResponseResult.success("数据获取成功",resourceService.queryPageList(pageParam,sysResourceDTO));
    }

    @PostMapping("/save")
    public ResponseResult addPermission(SysResource sysResource) {
        return ResponseResult.result(resourceService.addMenu(sysResource));
    }
}

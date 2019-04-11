package com.hubin.controller;

import com.hubin.domain.system.SysRole;
import com.hubin.services.SysRoleService;
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
 * @title: 角色
 * @description:
 * @date: 2019/3/31 11:08
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    SysRoleService sysRoleService;


    /**
     * 保存用户
     * @param sysRole
     * @return
     */
    @PostMapping(value = "/save")
    public ResponseResult save(SysRole sysRole) {
        return ResponseResult.success("保存成功", sysRoleService.addRole(sysRole));
    }

    /**
     * 分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/pageList")
    public ResponseResult pageJson(PageParam pageParam){
        return ResponseResult.success("数据获取成功",sysRoleService.queryPage(pageParam));
    }

    /**
     * 获取角色列表
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseResult list() {
        return ResponseResult.success("数据获取成功", sysRoleService.getRoleList());
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @PostMapping("/remove")
    public ResponseResult remove(Long roleId) {
        return ResponseResult.result(sysRoleService.deleteRoleByRoleId(roleId));
    }

    /**
     * 更新角色信息
     * @param sysRole
     * @return
     */
    @PostMapping("/update")
    public ResponseResult update(SysRole sysRole) {
        return ResponseResult.result(sysRoleService.updateRole(sysRole));
    }
}

package com.hubin.controller;

import com.hubin.domain.system.SysUser;
import com.hubin.utils.ResponseResult;
import com.hubin.utils.pages.PageParam;
import com.hubin.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * <br>
 *
 * @author hubin
 * @title:用户服务
 * @description:
 * @date: 2019/3/7 11:54
 */
@RestController
@RequestMapping("/user")
//@CrossOrigin("http://localhost:63343")
public class UserController {

    @Autowired
    SysUserService userService;

    @Value("${spring.application.name}")
    private String name;

    @RequestMapping("/user")
    public String userInfo(){
        return this.name;
    }


    /**
     * 分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/pageList")
    public ResponseResult pageJson(PageParam pageParam){
        return ResponseResult.success("数据获取成功",userService.queryPage(pageParam));
    }

    /**
     * 保存
     * @param user
     * @return
     */
    @PostMapping("/save")
    public ResponseResult save(SysUser user) {
        return ResponseResult.result(userService.saveUser(user));
    }


    /**
     * 用户更新
     * @param sysUser
     * @return
     */
    @PostMapping
    public ResponseResult update(SysUser sysUser) {
        return ResponseResult.result(userService.updateUser(sysUser));
    }


    /**
     * 删除
     * @param uid
     * @return
     */
    @PostMapping("/remove")
    public ResponseResult remove(Long uid) {
        return ResponseResult.result(userService.removeUser(uid));

    }

//    @RequestMapping("/get/{id}")
//    public ResponseEntity getUserById(@PathVariable("id") Long id){
//        return ResponseEntity.ok(userService.getById(id));
//    }

}

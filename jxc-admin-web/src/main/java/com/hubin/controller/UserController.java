package com.hubin.controller;

import com.hubin.common.pages.PageParam;
import com.hubin.services.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * <br>
 *
 * @author hubin
 * @title:
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

    @RequestMapping("/user.json")
    public String userInfo(){
        return this.name;
    }

    /**
     * 分页
     * @param pageParam
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseEntity pageJson(PageParam pageParam){
        return ResponseEntity.ok(userService.queryPage(pageParam));
    }
//
//    @RequiresPermissions(value = {"user:add"})
//    @RequestMapping("/save.json")
//    public ResponseEntity save(SysUserDomain user) {
//        return ResponseEntity.ok(userService.save(user));
//    }
//
//    @RequestMapping("/get/{id}")
//    public ResponseEntity getUserById(@PathVariable("id") Long id){
//        return ResponseEntity.ok(userService.getById(id));
//    }

}

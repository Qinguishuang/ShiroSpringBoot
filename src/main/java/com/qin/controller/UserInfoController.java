package com.qin.controller;

import com.qin.entity.UserInfo;
import com.qin.service.UserInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class UserInfoController {
    @Resource
    UserInfoService userInfoService;

    @GetMapping("/userList")
    @RequiresPermissions("userInfo:view")
    public UserInfo findUserInfoByUsername(@RequestParam("username") String usrename) {
        return userInfoService.findByUsername(usrename);
    }

    @PostMapping("/userAdd")
    @RequiresPermissions("userInfo:add")
    public String addUserInfo() {
        return "addUserInfo success!!!";
    }

    @DeleteMapping("/userDelete")
    @RequiresPermissions("userInfo:delete")
    public String deleteUserInfo() {
        return "deleteUserInfo success!!!";
    }

}

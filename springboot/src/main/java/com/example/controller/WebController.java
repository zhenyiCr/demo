package com.example.controller;

import com.example.common.Result;


import com.example.entity.*;
import com.example.exception.CustomerException;
import com.example.service.AdminService;
import com.example.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebController {
    @Resource
    AdminService adminService;
    @Resource
    UserService userService;
    @GetMapping("/E")
    public Result hello() {
        return Result.success("hello world");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Account account) {
        Account dbAccount = null;
        if (account.getRole().equals("ADMIN")) {
            dbAccount = adminService.login(account);
        } else if (account.getRole().equals("USER")) {
            dbAccount = userService.login(account);
        } else {
            throw new CustomerException("用户角色错误");
        }
        return Result.success(dbAccount);
    }
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.register(user);
        return Result.success();
    }

    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestBody PasswordAndAccountWrapper  wrapper) {
        ChangePasswordDTO changePasswordDTO = wrapper.getChangePasswordDTO();
        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new CustomerException("新密码和确认密码不一致");
        }
        Account account = wrapper.getAccount();
        Account dbAccount = null;
        if (account.getRole().equals("ADMIN")) {
            dbAccount = adminService.updatePassword(changePasswordDTO, account);
        } else if (account.getRole().equals("USER")) {
            dbAccount = userService.updatePassword(changePasswordDTO, account);
        } else {
            throw new CustomerException("用户角色错误");
        }
        return Result.success(dbAccount);
    }
}

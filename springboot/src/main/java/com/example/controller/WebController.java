package com.example.controller;

import com.example.common.Result;


import com.example.entity.Admin;
import com.example.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
public class WebController {
    @Resource
    AdminService adminService;
    @GetMapping("/E")
    public Result hello() {
        return Result.success("hello world");
    }

    @PostMapping("/login")
    public Result login(@RequestBody Admin admin) {
        Admin dbAdmin = adminService.login(admin);
        return Result.success(dbAdmin);
    }

}

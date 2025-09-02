package com.example.controller;

import com.example.common.Result;

import com.example.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class WebController {

    @Resource
    AdminService adminService;


    @GetMapping("/E")
    public Result hello() {
        return Result.success("hello world");
    }

    @GetMapping("/admin")
    public Result admin(String name) {
        String admin=adminService.adminName( name);
        return Result.success(admin);
    }
}

package com.example.controller;


import com.example.common.Result;
import com.example.service.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @GetMapping("/selectAll")
    public Result adminName(String name) {
        AdminService adminService = new AdminService();
        return Result.success(adminService.adminName(name));
    }
}

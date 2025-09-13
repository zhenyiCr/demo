package com.example.controller;


import com.example.common.Result;
import com.example.entity.Admin;
import com.example.service.AdminService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    AdminService adminService;

    @PostMapping("/add")
    public Result add(@RequestBody Admin admin) { // @RequestBody 接受前端传来的json数据
        adminService.add(admin);
        return Result.success();
    }

    @GetMapping("/selectAll")
    public Result slectAll() {
        List<Admin> adminList = adminService.selectAll();
        return Result.success(adminList);
    }

    // 分页查询
    // pageNum 当前页数
    // pageSize 每页显示的条数
    @GetMapping("/selectPage")
    public Result slectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            Admin admin) {
        PageInfo<Admin> pageInfo = adminService.selectPage(pageNum,pageSize,admin);
        return Result.success(pageInfo);
    }
}

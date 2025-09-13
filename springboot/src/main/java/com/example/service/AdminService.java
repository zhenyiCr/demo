package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.entity.Admin;
import com.example.exception.CustomerException;
import com.example.mapper.AdminMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;

    public List<Admin> selectAll() {
        return adminMapper.selectAll(null);
    }
    public void add(Admin admin) {
        // 判断用户名是否已存在
        Admin dbAdmin = adminMapper.selectByUsername(admin.getUsername());
        if (dbAdmin != null) {
            throw new CustomerException("用户名已存在");
        }
        if (StrUtil.isBlank(admin.getPassword())) {
            admin.setPassword("admin");
        }
        adminMapper.insert(admin);
    }


    public PageInfo<Admin> selectPage(Integer pageNum, Integer pageSize,Admin admin) {
        // 开始分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Admin> admins = adminMapper.selectAll(admin);
        return PageInfo.of(admins);
    }


}

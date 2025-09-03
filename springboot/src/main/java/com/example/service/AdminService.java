package com.example.service;

import com.example.entity.Admin;
import com.example.exception.CustomerException;
import com.example.mapper.AdminMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Resource
    AdminMapper adminMapper;

    public String adminName(String name) {
        if (name.equals("admin")) return name;
        else {
            throw new CustomerException("用户名错误");
        }

    }

    public List<Admin> selectAll() {
        return adminMapper.selectAll();
    }


}

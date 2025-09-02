package com.example.service;

import com.example.exception.CustomerException;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    public String adminName(String name)
    {
        if (name.equals("admin"))
            return name;
        else {
            throw new CustomerException("用户名错误");
        }

    }
}

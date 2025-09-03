package com.example.mapper;


import com.example.entity.Admin;
import com.example.mapper.AdminMapper;

import java.util.List;

public interface AdminMapper {
    List<Admin> selectAll();
}

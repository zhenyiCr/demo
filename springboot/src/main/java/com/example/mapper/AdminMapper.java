package com.example.mapper;


import com.example.entity.Admin;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface AdminMapper {
    List<Admin> selectAll(Admin admin);

    void insert(Admin admin);

    @Select ("select * from 'admin' where username=#{username}")
    Admin selectByUsername(String username);
}

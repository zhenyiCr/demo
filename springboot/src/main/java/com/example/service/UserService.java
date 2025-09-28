package com.example.service;

import cn.hutool.core.util.StrUtil;
import com.example.entity.Account;
import com.example.entity.User;
import com.example.exception.CustomerException;
import com.example.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public String User(String username) {
        if (username.equals("user"))
            return username;
        else throw new CustomerException("用户名不存在");
    }

    public List<User> selectAll(User user) {
        return userMapper.selectAll(user);
    }

    public void add(User user) {
        // 判断用户名是否已存在
        User dbUser = userMapper.selectByUsername(user.getUsername());
        if (dbUser != null) {
            throw new CustomerException("账号已存在");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword("user");
        }
        if (StrUtil.isBlank(user.getName())) {
            user.setName(user.getUsername());
        }
        user.setRole("USER");
        userMapper.insert(user);
    }


    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize, User user) {
        // 开始分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAll(user);
        return PageInfo.of(users);
    }


    public void update(User user) {
        if (!userMapper.selectUsername(user).equals(user.getUsername())) {
            User dbUser = userMapper.selectByUsername(user.getUsername());
            if (dbUser != null) {
                throw new CustomerException("账号已存在");
            }
        }
        userMapper.update(user);

    }

    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    public void deleteBatch(List<User> list) {
        for (User user : list) {
            this.deleteById(user.getId());
        }
    }

    public User login(Account account) {
        User dbUser = userMapper.selectByUsername(account.getUsername());
        if (dbUser == null) {
            throw new CustomerException("用户名不存在");
        }
        if (!dbUser.getPassword().equals(account.getPassword())) {
            throw new CustomerException("账号或密码错误");
        }
        return dbUser;
    }

    public void register(User user) {
        this.add(user);
    }
}

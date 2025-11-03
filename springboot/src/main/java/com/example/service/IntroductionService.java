package com.example.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.example.entity.Introduction;
import com.example.entity.Notice;
import com.example.exception.CustomerException;
import com.example.mapper.IntroductionMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntroductionService {

    @Resource
    IntroductionMapper introductionMapper;

    public String Introduction(String title) {
        if (title.equals("notice"))
            return title;
        else throw new CustomerException("标题不存在");
    }

    public List<Introduction> selectAll(Introduction introduction) {
        return introductionMapper.selectAll(introduction);
    }

    public void add(Introduction introduction) {
        // 判断用户名是否已存在
        Introduction dbIntroduction = introductionMapper.selectByTitle(introduction.getTitle());
        if (dbIntroduction != null) {
            throw new CustomerException("标题已存在");
        }
        if (StrUtil.isBlank(introduction.getContent())) {
            introduction.setContent("无");
        }
        introduction.setTime(DateUtil.now());
        introductionMapper.insert(introduction);
    }


    public PageInfo<Introduction> selectPage(Integer pageNum, Integer pageSize, Introduction introduction) {
        // 开始分页查询
        PageHelper.startPage(pageNum, pageSize);
        List<Introduction> introductionList = introductionMapper.selectAll(introduction);
        return PageInfo.of(introductionList);
    }


    public void update(Introduction introduction) {
        if (!introductionMapper.selectTitle(introduction).equals(introduction.getTitle())) {
            Introduction dbIntroduction = introductionMapper.selectByTitle(introduction.getTitle());
            if (dbIntroduction != null) {
                throw new CustomerException("标题已存在");
            }
        }
        introduction.setTime(DateUtil.now());
        introductionMapper.update(introduction);

    }

    public void deleteById(String id) {
        introductionMapper.deleteById(id);
    }



    public void deleteBatch(List<Introduction> list) {
        for (Introduction introduction : list) {
            this.deleteById(introduction.getId());
        }
    }


}

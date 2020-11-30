package com.wgw.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.AbstractLambdaWrapper;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wgw.dao.StudentDao;
import com.wgw.entity.Student;
import com.wgw.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author weiguangwei
 */

@Slf4j
@Service
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

//    @Autowired
//    private StudentDao studentDao;

    @Override
    public int add(JSONObject jsonObject) {
        Student stu = new Student();
        stu.setAge(29);
        stu.setUsername("位光伟");
        int insert = baseMapper.insert(stu);
        log.info("...插入学生成功...");
        return 0;
    }
    @Override
    public Student getStu(Student student){

        QueryWrapper<Student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.eq("username",student.getUsername());
        Student stu = baseMapper.selectOne(studentQueryWrapper);
        return stu;
    }

    @Override
    public List<Student> getStuByName(String userName) {
        return baseMapper.getUserByName(userName);
    }
}

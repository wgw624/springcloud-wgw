package com.wgw.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wgw.entity.Student;

/**
 * @author weiguangwei
 */
public interface StudentService extends IService<Student> {
    int add(JSONObject jsonObject);
    Student getStu(Student stu);
}

package com.wgw.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgw.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author weiguangwei
 */
@Mapper
public interface StudentDao extends BaseMapper<Student> {
    /**
     * 获取用户列表
     * @param userName
     * @return
     */
    List<Student> getUserByName(String userName);
}

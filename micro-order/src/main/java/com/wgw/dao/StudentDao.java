package com.wgw.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wgw.entity.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author weiguangwei
 */
@Mapper
public interface StudentDao extends BaseMapper<Student> {
}

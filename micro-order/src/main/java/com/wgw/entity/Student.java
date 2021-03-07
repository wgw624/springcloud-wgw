package com.wgw.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@TableName("student")
public class Student {
    @TableId(value="id")
    private int id;
    private String username;
    private int age;
}

package org.weiyada.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.weiyada.entity.UserInfo;
import org.weiyada.service.UserInfoService;

@RestController
@RequestMapping("userInf")
@Api(tags = "用户控制类")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("/saveOrUpdate")
    public String saveUser(@RequestBody UserInfo userInfo){
        return "ok";
    }
    @PostMapping("/queryUser")
    public String queryAllUser(){
        UserInfo userInfo = userInfoService.getById(1);
        if(!ObjectUtils.isEmpty(userInfo)){
            System.out.println(userInfo.getUserName());
        }
        return "ok";
    }
}

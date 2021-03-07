package com.wgw.controller;

import com.wgw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    public static boolean canCooDB = true;
    @Autowired
    private UserService userService;
    @RequestMapping("/queryUser")
    public String queryUser(){
        String str= userService.queryUser();
        return str;
    }
}

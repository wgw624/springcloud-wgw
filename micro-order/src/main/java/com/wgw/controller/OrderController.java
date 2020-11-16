package com.wgw.controller;

import com.alibaba.fastjson.JSONObject;
import com.wgw.entity.Student;
import com.wgw.entity.User;
import lombok.extern.slf4j.Slf4j;
import netscape.javascript.JSObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {

    @RequestMapping("getOrder")
    public String getOrder(String userId, String userName){
        log.info("========micro-order===queryUser===="+userId+"--->"+userName);
        return "请求到订单服务#################v0.1";
    }
    @RequestMapping("postOrder")
    public String postOrder(@RequestBody String obj){
        log.info("========micro-order===postUser===="+obj.toString());
        JSONObject obj1 = JSONObject.parseObject(obj);
        User user = obj1.getObject("user",User.class);
        log.info("user...."+user.getUserName());
        return "请求到订单服务#################";
    }
}

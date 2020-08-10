package com.wgw.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("order")
@RestController
@Slf4j
public class OrderController {

    @RequestMapping("getOrder")
    public String getOrder(){
        log.info("========micro-order===queryUser");
        return "请求到订单服务#################";
    }
}

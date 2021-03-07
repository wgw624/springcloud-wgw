package cn.codemao.fileupdata.admin.controller;

import cn.codemao.fileupdata.admin.service.GetMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 策略模式测试类
 * @program: springcloud-wgw
 * @classname: StrategyController
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2021/1/20 12:02 下午
 * @Version 1.0
 **/
@Controller
@RequestMapping("push")
public class StrategyController {
    @Autowired
    GetMsgService getMsgService;

    @RequestMapping("test")
    public String testStrategy(String type){
        getMsgService.getMsgContent(type);
        return "success";
    }
}

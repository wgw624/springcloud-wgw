package cn.codemao.fileupdata.admin.service.impl;

import cn.codemao.fileupdata.admin.service.GetMsgService;
import cn.codemao.fileupdata.admin.service.MsgContent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description: 获取消息内容
 * @program: springcloud-wgw
 * @classname: GetMsgService
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2021/1/20 11:47 上午
 * @Version 1.0
 **/

@Service
public class GetMsgServiceImpl implements GetMsgService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void getMsgContent(String contextType) {
        Map<String, MsgContent> map = applicationContext.getBeansOfType(MsgContent.class);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode on = objectMapper.createObjectNode();
        on.put("name","test");
        for(MsgContent mc:map.values()){
            mc.getContent(contextType,on);
        }
        System.out.println(on);
    }
}

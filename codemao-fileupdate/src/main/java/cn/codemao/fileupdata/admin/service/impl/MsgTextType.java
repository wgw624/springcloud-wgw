package cn.codemao.fileupdata.admin.service.impl;

import cn.codemao.fileupdata.admin.service.MsgContent;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

/**
 * @description: 文本类型消息
 * @program: springcloud-wgw
 * @classname: MsgTextType
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2021/1/20 11:30 上午
 * @Version 1.0
 **/
@Service
public class MsgTextType implements MsgContent {
    private String msgType = "text";
    @Override
    public Object getContent(String msgType, ObjectNode objectNode) {
        if(this.msgType.equals(msgType)){
            objectNode.put("text","hello welcome to beijing");
        }
        return null;
    }
}

package cn.codemao.fileupdata.admin.service.impl;

import cn.codemao.fileupdata.admin.service.MsgContent;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

/**
 * @description: 音频消息内容
 * @program: springcloud-wgw
 * @classname: VedioMsgType
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2021/1/20 11:58 上午
 * @Version 1.0
 **/
@Service
public class VedioMsgType implements MsgContent {
    @Override
    public Object getContent(String msgType, ObjectNode on) {
        if("video".equals(msgType)){
            on.put("video","我是音频文件");
        }
        return null;
    }
}

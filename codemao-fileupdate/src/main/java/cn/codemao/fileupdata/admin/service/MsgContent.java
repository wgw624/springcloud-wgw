package cn.codemao.fileupdata.admin.service;


import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * @description: 获取微信推送内容
 * @program: springcloud-wgw
 * @classname: getContent
 * @author: wgw  weiguangwei@codemao.cn
 * @date: 2021/1/20 11:28 上午
 * @Version 1.0
 **/
public interface MsgContent {
     Object getContent(String msgType, ObjectNode on);
}

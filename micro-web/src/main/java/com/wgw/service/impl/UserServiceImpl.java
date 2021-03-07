package com.wgw.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.netflix.ribbon.RequestTemplate;
import com.netflix.ribbon.proxy.annotation.Http;
import com.wgw.service.UserService;
import netscape.javascript.JSObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {

    private static String serviceName="micro-order";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String queryUser() {
        String url = "http://"+serviceName+"/order/getOrder?userId={userId}&userName={userName}";
        Map<String,Object> map = new HashMap<>();
        map.put("userId","1234");
        map.put("userName","位光伟");
        String result = restTemplate.getForObject(url,String.class,map);

        String postUrl = "http://"+serviceName+"/order/postOrder";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

//        HttpEntity httpEntity = new HttpEntity<>(map,httpHeaders);

        MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<>();
//        multiValueMap.add("userId","34567");
//        multiValueMap.add("userName","李梦涵");
//        multiValueMap.add("age",30);
        JSONObject uobj = new JSONObject();
        uobj.put("userId","111114444");
        uobj.put("userName","位光伟");
        JSONObject sObj = new JSONObject();
        sObj.put("userName","李梦涵");
        sObj.put("age",23);
        multiValueMap.set("user",uobj);
        multiValueMap.set("student",sObj);
        HttpEntity httpEntity = new HttpEntity(multiValueMap,httpHeaders);
        JSONObject retObj = new JSONObject();
        retObj.put("user",uobj);
        retObj.put("student",sObj);
        restTemplate.postForObject(postUrl,retObj,String.class);
        
        //restTemplate.postForObject(postUrl,httpEntity,String.class,multiValueMap);
        return result;
    }
}

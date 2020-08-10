package com.wgw.service.impl;

import com.netflix.ribbon.RequestTemplate;
import com.wgw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
public class UserServiceImpl implements UserService {

    private static String serviceName="micro-order";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String queryUser() {
        String url = "http://"+serviceName+"/order/getOrder";
        String result = restTemplate.getForObject(url,String.class);
        return result;
    }
}

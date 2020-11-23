package com.wgw.service;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.annotations.DynamicTemplates;

public class ES_Service {


    public void test1(){
        QueryBuilders.termQuery("123",123);
    }
}

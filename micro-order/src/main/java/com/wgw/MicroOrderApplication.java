package com.wgw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableEurekaClient
@EnableElasticsearchRepositories(basePackages = "com.wgw.dao")
public class MicroOrderApplication {
    public static void main(String  []args){
        SpringApplication.run(MicroOrderApplication.class,args);
    }
}

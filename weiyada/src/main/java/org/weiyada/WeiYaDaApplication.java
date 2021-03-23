package org.weiyada;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 *@description: 启动类
 *@return:
 *@author: weiguangwei
 *@time: 2021/3/18 5:05 下午
 */
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class})

@SpringBootApplication
@EnableSwagger2
public class WeiYaDaApplication {
    public static void main(String []args){
        SpringApplication.run(WeiYaDaApplication.class);
    }
}

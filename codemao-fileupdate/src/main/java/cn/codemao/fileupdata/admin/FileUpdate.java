package cn.codemao.fileupdata.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FileUpdate {
    public static void main(String []args){
        SpringApplication.run(FileUpdate.class);
    }
}

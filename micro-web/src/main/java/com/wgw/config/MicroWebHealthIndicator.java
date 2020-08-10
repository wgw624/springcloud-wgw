package com.wgw.config;

import com.wgw.controller.UserController;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicroWebHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        if(UserController.canCooDB){
            return new Health.Builder(Status.UP).build();
        }else{
            return new Health.Builder(Status.DOWN).build();
        }

    }
}

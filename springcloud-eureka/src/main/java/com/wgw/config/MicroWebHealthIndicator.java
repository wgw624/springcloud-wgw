package com.wgw.config;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class MicroWebHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {

        return null;
    }
}

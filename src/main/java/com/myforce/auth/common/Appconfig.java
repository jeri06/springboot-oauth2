package com.myforce.auth.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Appconfig {

    @Value("${spring.redis.host}")
    private String redisIp;

    @Value("${spring.redis.port}")
    private int redisport;


    public String getRedisIp() {
        return redisIp;
    }

    public void setRedisIp(String redisIp) {
        this.redisIp = redisIp;
    }

    public int getRedisport() {
        return redisport;
    }

    public void setRedisport(int redisport) {
        this.redisport = redisport;
    }
}

package com.myforce.auth.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@PropertySource("application.properties")
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHostName;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        System.out.println("TES"+redisHostName);
        System.out.println("PORTT"+redisPort);
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(redisHostName);
        jedisConFactory.setPort(redisPort);
        return jedisConFactory;
//            return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }



}

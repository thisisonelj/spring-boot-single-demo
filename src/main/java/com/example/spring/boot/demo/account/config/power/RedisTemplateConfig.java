package com.example.spring.boot.demo.account.config.power;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @Author liujun
 * @Date 2023/8/10 16:02
 * @Description
 */
@Configuration
public class RedisTemplateConfig {

    @Bean("redisOperation")
    public RedisTemplate<String, Object> redisOperation(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        GenericJackson2JsonRedisSerializer redisJsonSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(redisJsonSerializer);
        redisTemplate.setHashValueSerializer(redisJsonSerializer);
        return redisTemplate;
    }

}

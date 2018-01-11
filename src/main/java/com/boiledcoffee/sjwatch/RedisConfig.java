package com.boiledcoffee.sjwatch;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * Created by juha on 2018/1/11.
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport{

    @Override
    public KeyGenerator keyGenerator() {
        return (o, method, objects) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(method.getClass().getName());
            sb.append(method.getName());
            for (Object obj : objects) {
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return super.cacheManager();
    }
}

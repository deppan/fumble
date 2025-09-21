package com.tsinsi.console.adapter.out;

import com.tsinsi.console.application.out.CacheRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisCacheRepository implements CacheRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisCacheRepository(RedisTemplate<String, String> restTemplate) {
        this.redisTemplate = restTemplate;
    }

    @Override
    public void set(String key, String value, long ttlSeconds) {
        redisTemplate.opsForValue().set(key, value, ttlSeconds, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}

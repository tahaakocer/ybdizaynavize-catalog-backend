package com.tahaakocer.ybdizaynavize.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis-test")
    public String testRedis() {
        String key = "test-key";
        String value = "Hello, Redis!";
        redisTemplate.opsForValue().set(key, value);
        String retrievedValue = redisTemplate.opsForValue().get(key);
        return "Redis Test Result: " + (value.equals(retrievedValue) ? "Success" : "Failure");
    }
}


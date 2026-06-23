package com.journalapp.journalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.journalapp.journalApp.apiResponse.WeatherResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private static final Logger log = LoggerFactory.getLogger(RedisService.class);
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String key, Class<T> entityClass) {
        try {
            Object o = redisTemplate.opsForValue().get(key);
            if (o == null) {
                return null;
            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), entityClass);
        } catch (Exception e) {
            log.error("Error in RedisService", e);
            return null;
        }
    }
    public void set(String key,Object o,Long ttl ) {
        try {
            ObjectMapper objectMapper =new ObjectMapper();
            String jsonValue= objectMapper.writeValueAsString(o);
           redisTemplate.opsForValue().set(key,jsonValue,ttl, TimeUnit.SECONDS);
            System.out.println("SAVED IN REDIS");
        } catch (Exception e) {
            log.error("Error in RedisService");

        }
    }
}

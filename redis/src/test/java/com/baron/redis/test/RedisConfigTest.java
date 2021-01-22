package com.baron.redis.test;

import com.baron.redis.entry.Address;
import com.baron.redis.entry.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisConfigTest {
    @Resource
    private StringRedisTemplate stringRedisTemplate;   //以String序列化方式保存数据的通用模板类

    @Resource
    private RedisTemplate<String, Person> redisTemplate;   //默认以JDK二进制方式保存数据的通用模板类

    @Test
    public void stringRedisTemplate() {
        Person person = new Person("kobe","byrant");
        person.setAddress(new Address("洛杉矶","美国"));
        //将数据存入redis数据库
        stringRedisTemplate.opsForValue().set("player:srt","kobe byrant",60, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set("player:rt",person,60, TimeUnit.SECONDS);
    }
}

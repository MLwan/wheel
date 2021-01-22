package com.baron.redis.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baron.redis.entry.Address;
import com.baron.redis.entry.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootTest
public class RedisConfigTest4 {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testValueObj() {
        Person person = new Person("boke","byrant");
        person.setAddress(new Address("南京","中国"));
        //向redis数据库保存数据(key,value),数据有效期20秒
        redisTemplate.opsForValue().set("kv:player", JSON.toJSONString(person),60, TimeUnit.SECONDS); //20秒之后数据消失
//        redisTemplate.opsForValue().set("player:1", person,60, TimeUnit.SECONDS); //20秒之后数据消失
        //根据key把数据取出来
        String getBack = (String) redisTemplate.opsForValue().get("kv:player");
        Person p = JSONObject.parseObject(getBack,Person.class);
//        Person getBack = (Person)redisTemplate.opsForValue().get("player:1");

        System.out.println(p);
    }

    @Test
    public void testSetOperation() {
        Person person = new Person("kobe","byrant");
        Person person2 = new Person("curry","stephen");

        redisTemplate.opsForSet().add("set:player",person,person2);  //向Set中添加数据项
        //members获取Redis Set中的所有记录
        Set<Object> result = redisTemplate.opsForSet().members("set:player");
        System.out.println(result);  //包含kobe和curry的数组
    }

    @Test
    public void HashOperations() {
        Person person = new Person("kobe","byrant");
        Person person2 = new Person("kobe2","byrant2");
        Person person3 = new Person("kobe3","byrant3");
        //使用hash的方法存储对象数据（一个属性一个属性的存，下节教大家简单的方法）
        redisTemplate.opsForHash().put("hash:player","firstname",person.getFirstname());
        redisTemplate.opsForHash().put("hash:player","lastname",person.getLastname());
        redisTemplate.opsForHash().put("hash:player","address",person.getAddress());
        redisTemplate.opsForHash().put("hash:player","person2",person2);
        redisTemplate.opsForHash().put("hash:player","person3",person3);
        //取出一个对象的属性值，有没有办法一次将整个对象取出来？有，下节介绍
        String firstName = (String)redisTemplate.opsForHash().get("hash:player","firstname");
        System.out.println(firstName);   //kobe
        log.info(JSON.toJSONString(redisTemplate.opsForHash().get("hash:player","person2")));
    }

    @Test
    public void  ListOperations() {
        //将数据对象放入队列
        redisTemplate.opsForList().leftPush("list:player",new Person("kobe","byrant"));
        redisTemplate.opsForList().leftPush("list:player",new Person("Jordan","Mikel"));
        redisTemplate.opsForList().leftPush("list:player",new Person("curry","stephen"));
        //从左侧存，再从左侧取，所以取出来的数据是后放入的curry
        Person person = (Person) redisTemplate.opsForList().leftPop("list:player");
        System.out.println(person); //curry对象
    }
}

package com.baron.redis.entry;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("person")   //注意这里的person，下文会说明
@Data
public class Person implements Serializable {
//public class Person{

    private static final long serialVersionUID = -8985545025228238754L;

    @Id
    String id;
    String firstname;
    String lastname;
    Address address;   //注意这里，不是基础数据类型

    public Person() {
    }

    public Person(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }
}

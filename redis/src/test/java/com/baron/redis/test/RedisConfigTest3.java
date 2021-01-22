package com.baron.redis.test;

import com.baron.redis.dao.PersonRepository;
import com.baron.redis.entry.Address;
import com.baron.redis.entry.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.hash.HashMapper;
import org.springframework.data.redis.hash.Jackson2HashMapper;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Description: 使用Redis Repository操作数据
 * @Author: zhanwang
 * @Date: 2021/1/22
*/
@Slf4j
@SpringBootTest
public class RedisConfigTest3 {
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations; //以redis的hash类型存储java Object

    @Resource(name="redisTemplate")
    private HashOperations<String, String, Object> jacksonHashOperations;
    //注意这里的false，下文会讲解
    private HashMapper<Object, String, Object> jackson2HashMapper = new Jackson2HashMapper(false);

    @Resource
    PersonRepository personRepository;

    /**
     *操作hash对象的时候是一个属性一个属性设置
    */
    @Test
    public void HashOperations() {
        Person person = new Person("kobe","byrant");
        person.setAddress(new Address("洛杉矶","美国"));
        //使用hash的方法存储对象数据（一个属性一个属性的存，下节教大家简单的方法）
        hashOperations.put("hash:player","firstname",person.getFirstname());
        hashOperations.put("hash:player","lastname",person.getLastname());
        hashOperations.put("hash:player","address",person.getAddress());

        String firstName = (String)hashOperations.get("hash:player","firstname");
        System.out.println(firstName);
    }

    /**
     *使用Jackson2HashMapper存取对象
     * 使用这种方式可以一次性的存取java 对象为redis数据库的hash数据类型。
     * 需要注意的是：执行上面的测试用例，Person和Address一定要有public无参构造方法，在将map转换成Person或Address对象的时候用到，如果没有的话会报错。
    */
    @Test
    public void testHashPutAll(){

        Person person = new Person("kobe","bryant");
        person.setId("kobe");
        person.setAddress(new Address("洛杉矶","美国"));

        //将对象以hash的形式放入redis数据库
        Map<String,Object> mappedHash = jackson2HashMapper.toHash(person);
        jacksonHashOperations.putAll("player:jho" + person.getId(), mappedHash);

        //将对象从数据库取出来
        Map<String,Object> loadedHash = jacksonHashOperations.entries("player:jho" + person.getId());
        Object map = jackson2HashMapper.fromHash(loadedHash);
        Person getback = new ObjectMapper().convertValue(map,Person.class);

        //Junit5,验证放进去的和取出来的数据一致
        assertEquals(person.getFirstname(),getback.getFirstname());
    }

    /**
     *使用RedisRepository的对象操作
     * 下面为大家介绍使用RedisRepository进行redis数据操作，它不只是能简单的存取数据，还可以做很多的CURD操作。
     * 使用起来和我们用JPA进行关系型数据库的单表操作，几乎是一样的。
     * 首先，我们需要在需要操作的java实体类上面加上@RedisHash注解，并使用@Id为该实体类指定id。是不是和JPA挺像的？
    */
    @Test
    public void test(){

        Person rand = new Person("zimug", "汉神");
        rand.setAddress(new Address("杭州", "中国"));
        personRepository.save(rand);  //存

        Optional<Person> op = personRepository.findById(rand.getId()); //取
        Person p2 = op.get();

        log.info("统计Person的数量[{}]",personRepository.count());; //统计Person的数量
        //personRepository.delete(rand);  //删除person对象rand

    }
}

package com.baron.redis.cachetest;

import com.baron.redis.entry.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 将通配url对应的返回值缓存
 * @Author: zhanwang
 * @Date: 2021/1/22
*/
@Slf4j
@RestController
public class SpringCacheTest {


//    @Cacheable(value="article")
//    @GetMapping( "/article/{id}")
//    public @ResponseBody String getArticle(@PathVariable String id) {
//        log.info("人间走一回");
//        return "8866";
//    }


    public static final String CACHE_OBJECT = "person";  //缓存名称
    @GetMapping( "/article/{id}")
    @Cacheable(value = CACHE_OBJECT,key = "#id")   //这里的value和key参考下面的redis数据库截图理解
    public Person getPersion(@PathVariable String id) {
        log.info("人间走一回");
        return new Person("baron",id);
    }
}

package com.baron.java8.foreach;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

@RestController
@RequestMapping(path="/java8")
public class ForEach {


    @RequestMapping(path="/foreach")
    public void forEach() {
//        1.1. Iterable.forEach()方法
//        我们可以通过这个方法去遍历除了Map之外的所有集合类。

//        1.1.1遍历LIst
        List<String> names = Arrays.asList("Alex","Brian","Charles");
        names.forEach(System.out::println);

//        我们可以通过实现Consumer接口的accept方法，实现自己对集合元素需要做的自定义操作。比如：下面的代码是实现集合中字符串转大写并打印出来的操作。
//        Consumer类型是集合中元素类型
        Consumer<String> makeUpperCase = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }
        };
        names.forEach(makeUpperCase);

//        1.1.2遍历Map
        Map<String,String> map = new HashMap<String,String>();
        map.put("A","Alex");
        map.put("B","Brian");
        map.put("C","Charles");

        map.forEach((k,v)->{
            System.out.println("Key = " + k + ", Value = " + v);
        });

//        与List等集合类遍历类似，我们可以自定义一个biconsumer action去处理key-value键值对.
        BiConsumer<String,String> forAction = (k,v)->{
            System.out.println("Key is " + k + ", Value is " + v);
        };
        map.forEach(forAction);

//        1)、Map entry
       Consumer<Map.Entry<String,String>> forEntry = System.out::println;
       map.entrySet().forEach(forEntry);
//        2)、Map keys
        Consumer<String> forKeys = System.out::println;
        map.keySet().forEach(forKeys);
//        3)、Map values
        Consumer<String> forValues = e->{System.out.println(e + "对Map的值进行遍历或处理");};
        map.values().forEach(forValues);
    }
}

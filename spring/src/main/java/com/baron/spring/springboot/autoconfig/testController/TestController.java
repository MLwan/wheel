package com.baron.spring.springboot.autoconfig.testController;

import com.baron.spring.springboot.autoconfig.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
//@ConfigurationProperties(prefix="taco.orders")
public class TestController {
    /*
        获取yml配置属性
     */
//    private int pageSize = 20;
//
//    public void setPageSize(int pageSize) {
//        this.pageSize = pageSize;
//    }
    /*
        从配置Bean中 获取yml配置属性
     */
    @Autowired
    Book book2;

    @GetMapping("/a")
    public String test(@RequestBody @Valid Book book, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println("校验失败："+ bindingResult.getFieldError().getDefaultMessage());
            return bindingResult.getFieldError().getDefaultMessage();
        }

//        System.out.println("pageSize =="+pageSize);

        System.out.println("book_name =="+book.getName());
        System.out.println("book_pageSize =="+book.getPageSize());

        System.out.println("book2_pageSize =="+book2.getPageSize());


        return "test WebMvcTest";
    }

}

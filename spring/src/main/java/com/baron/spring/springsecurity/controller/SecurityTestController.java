package com.baron.spring.springsecurity.controller;

import com.baron.spring.springsecurity.domain.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @RequestMapping("/user/test2")
    public ResponseResult test2(){
        System.out.println("/user/test2");
        return new ResponseResult(200,"test2");
    }
}

package com.baron.spring.springsecurity.controller;


import com.baron.spring.springsecurity.domain.ResponseResult;
import com.baron.spring.springsecurity.domain.User;
import com.baron.spring.springsecurity.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        //登录
        return loginServcie.login(user);
    }

    @RequestMapping("/user/logout")
    public ResponseResult logout(){
        return loginServcie.logout();
    }

    @RequestMapping("/user/test")
    public ResponseResult test1(){
        System.out.println("/user/test");
        return new ResponseResult(200,"test");
    }
}

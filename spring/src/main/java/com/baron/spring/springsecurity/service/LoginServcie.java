package com.baron.spring.springsecurity.service;


import com.baron.spring.springsecurity.domain.ResponseResult;
import com.baron.spring.springsecurity.domain.User;

public interface LoginServcie {
    ResponseResult login(User user);

    ResponseResult logout();

}

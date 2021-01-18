package com.baron.servlet.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * @Description: listener\filter测试入口
 * @Author: zhanwang
 * @Date: 2021/1/18
*/

@Slf4j
@RequestMapping("/servlet")
@RestController
public class RunTest {

    @RequestMapping("/test")
    public String test(HttpServletRequest req, HttpSession ses){

        //操作Attribute
        req.setAttribute("reqAttribute","one");
        req.setAttribute("reqAttribute","two");
        req.removeAttribute("reqAttribute");

        //操作session
        ses.setAttribute("seqAttribute","three");
        ses.getAttribute("seqAttribute");
        ses.invalidate();

        //
        return "ok";
    }
}

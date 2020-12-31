package com.baron.nacos.center;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path="/nacos")
public class NacosCenterTest {
    @Autowired
    private JavaMailSenderImpl mailSenderImpl;

//    @NacosValue(value = "${spring.mail.host}", autoRefreshed = true)
    @Value("${spring.mail.host}")
    private String testProperties;
    /**
     * @Description : 查询全部
     * @Author : zhanwang
     * @Date : 2020/6/6
    */
    @RequestMapping("/center")
    public void plusSelectAll() {
        System.out.println("ddddddddddddddddddddddddddddddddd{}"+testProperties);

    }


}

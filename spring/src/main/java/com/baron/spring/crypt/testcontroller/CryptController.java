package com.baron.spring.crypt.testcontroller;

import com.baron.spring.crypt.common.Decrypt;
import com.baron.spring.crypt.common.Encrypt;
import com.baron.spring.crypt.common.RespBean;
import com.baron.spring.crypt.domain.CryptUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CryptController {
    @GetMapping("/crypt")
    @Encrypt
    public RespBean getUser() {
        CryptUser user = new CryptUser();
        user.setId((long) 99);
        user.setUsername("javaboy");
        return RespBean.ok("ok", user);
    }

    @PostMapping("/crypt")
    public RespBean addUser(@RequestBody @Decrypt CryptUser user) {
        System.out.println("user = " + user);
        return RespBean.ok("ok", user);
    }
}

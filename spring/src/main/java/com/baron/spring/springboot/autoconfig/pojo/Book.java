package com.baron.spring.springboot.autoconfig.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;

@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
public class Book {
    @NotBlank(message = "姓名不能为空")
    private String name;

    private int pageSize = 10;
}

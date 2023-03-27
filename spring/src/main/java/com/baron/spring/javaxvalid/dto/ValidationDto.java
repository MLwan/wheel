package com.baron.spring.javaxvalid.dto;

import com.baron.spring.javaxvalid.annotation.Xss;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import javax.validation.constraints.NotBlank;

@Component
@Data
@ConfigurationProperties(prefix = "taco.orders")
public class ValidationDto {
    @NotBlank(message = "姓名不能为空")
    @Xss(message = "自定义校验,不允许包含特殊字符")
    private String name;

    private int pageSize = 10;
}

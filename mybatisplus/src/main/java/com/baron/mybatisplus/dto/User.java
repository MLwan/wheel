package com.baron.mybatisplus.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private LocalDate createDate;
}

package com.baron.spring.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baron.spring.springsecurity.domain.Menu;

import java.util.List;

public interface MenuMapper extends BaseMapper<Menu> {

    List<String> selectPermsByUserId(Long userid);
}

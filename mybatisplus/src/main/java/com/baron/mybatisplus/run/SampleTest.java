package com.baron.mybatisplus.run;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baron.mybatisplus.dto.User;
import com.baron.mybatisplus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(path="/accumulate")
public class SampleTest {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description : 查询全部
     * @Author : zhanwang
     * @Date : 2020/6/6
    */
    @RequestMapping("/plusSelectAll")
    public void plusSelectAll() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        //Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    /**
     * @Description : 查询一条
     * @Author : zhanwang
     * @Date : 2020/6/6
    */
    @RequestMapping("/plusSelectOne")
    public void plusSelectOne() {
        System.out.println(("----- selectOne method test ------"));
        QueryWrapper<User> userWapper = new QueryWrapper<User>();
        User user = User.builder()
                .age(28)
                .name("Tom")
                .build();
        userWapper.setEntity(user);
        User selectOneUser = userMapper.selectOne(userWapper);
        log.info("all most usefull select serious shuxing of object_{}",selectOneUser);
    }

    /**
     * @Description : 查询时间条件记录
     * @Author : zhanwang
     * @Date : 2020/6/6
     */
    @RequestMapping("/plusSelectDate")
    public void plusSelectDate() {
        System.out.println(("----- selectDate------"));
        QueryWrapper<User> userWapper = new QueryWrapper<User>();
        LocalDate localDate = LocalDate.now();
        User user = User.builder()
                .createDate(localDate)
                .build();
        userWapper.setEntity(user);
        List<User> users = userMapper.selectList(userWapper);
        log.info("all most usefull select serious shuxing of object_{}", JSON.toJSONString(users));

        System.out.println(("----- selectDateTime------"));

    }

    @RequestMapping("/plusSelectDateTime")
    public String plusSelectDateTime() {
        System.out.println(("----- selectDateTime------"));
        QueryWrapper<User> userWapper = new QueryWrapper<User>();
        //方式一
        //userWapper.apply("UNIX_TIMESTAMP(datetime) >= UNIX_TIMESTAMP('" + "2020-06-05 00:00:00" + "')");

        //方式二(使用与mybatis、mybatis plus)
        userWapper.apply("date_format (datetime,'%Y-%m-%d') >= date_format('" + "2020-06-02" + "','%Y-%m-%d')")
                  .apply("date_format (datetime,'%Y-%m-%d') <= date_format('" + "2020-06-03" + "','%Y-%m-%d')");
        //不管用
        //userWapper.eq("datetime","2020-06-05");
        List<User> users = userMapper.selectList(userWapper);
        log.info("SelectDateTime_{}", JSON.toJSONString(users));
        return JSON.toJSONString(users);
    }

    /**
     * @Description : 插入一条
     * @Author : zhanwang
     * @Date : 2020/6/6
    */
    @RequestMapping("/plusInsert")
    public void plusInsert() {
        System.out.println(("----- selectOne method test ------"));
        User user = User.builder()
                .id(8L)
                .age(28)
                .name("zw")
                .email("rcvbtf@163.com")
                .build();
        int insert = userMapper.insert(user);
        log.info("insert line_{}",insert);
    }

    /**
     * @Description : 根据ID更新
     * @Author : zhanwang
     * @Date : 2020/6/6
    */
    @RequestMapping("/plusUpdateById")
    public void plusUpdateById() {
        System.out.println(("----- selectOne method test ------"));
        User user = User.builder()
                .id(1L)
                .age(27)
                .name("xy")
                .email("xy@163.com")
                .build();
        int insert = userMapper.updateById(user);
        log.info("updateById",insert);
    }

    /**
     * @Description : 根据条件更新
     * @Author : zhanwang
     * @Date : 2020/6/6
     */
    @RequestMapping("/plusUpdate")
    public void plusUpdate() {
        System.out.println(("----- selectOne method test ------"));
        //条件
        QueryWrapper<User> userWapper = new QueryWrapper<User>();
        userWapper.eq("name","xy");
        //更新内容
        User user = User.builder()
                .email("xka@163.com")
                .build();

        int insert = userMapper.update(user,userWapper);
        log.info("plusUpdate_{}",insert);
    }



}
package com.baron.test;

import com.baron.service.DistrictPercentage;
import com.baron.service.DistrictPerformance;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 测试类
 */
public class SpringAnnotationTypeFilterTest {
    public static void main(String[] args) {
        //1.创建容器
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext("config");
        //2.获取对象
        DistrictPerformance districtPerformance =
                ac.getBean("districtPerformance", DistrictPerformance.class);
        //3.执行方法
        districtPerformance.calcPerformance("SUV");

        DistrictPercentage districtPercentage = ac.getBean("districtPercentage",DistrictPercentage.class);
        //3.执行方法
        districtPercentage.salePercentage("CAR"); }
}

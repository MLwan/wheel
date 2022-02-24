package com.baron.service.northimpl;

import com.baron.annotations.District;
import com.baron.service.DistrictPercentage;
import org.springframework.stereotype.Service;

/**
 * 华北区销售分成具体实现
 * @author 黑马程序员
 * @Company http://www.itheima.com */
@Service("districtPercentage")
@District("north")
public class NorthDistrictPercentage implements DistrictPercentage {
    @Override
    public void salePercentage(String carType) {
        if ("SUV".equalsIgnoreCase(carType)) {
            System.out.println("华北区" + carType + "提成1%");
        } else if ("CAR".equalsIgnoreCase(carType)) {
            System.out.println("华北区" + carType + "提成0.5%");
        }
    }
}
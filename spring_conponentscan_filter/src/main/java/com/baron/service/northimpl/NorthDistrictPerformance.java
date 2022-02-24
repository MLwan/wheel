package com.baron.service.northimpl;

import com.baron.annotations.District;
import com.baron.service.DistrictPerformance;
import org.springframework.stereotype.Component;

/**
 * 华北区销售绩效具体实现
 */
@Component("districtPerformance")
@District("north")
public class NorthDistrictPerformance implements DistrictPerformance {
    @Override
    public void calcPerformance(String carType){

        if("SUV".equalsIgnoreCase(carType)) {
            System.out.println("华北区"+carType+"绩效3"); }
        else if("CAR".equalsIgnoreCase(carType)){
            System.out.println("华北区"+carType+"绩效5"); }
    }
}
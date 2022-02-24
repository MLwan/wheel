package com.baron.service.southwestimpl;

import com.baron.annotations.District;
import com.baron.service.DistrictPerformance;
import org.springframework.stereotype.Service;

/**
 * 西南区绩效计算具体实现
 */
@Service("districtPerformance")
@District("southwest")
public class SouthwestDistrictPerformance implements DistrictPerformance {
    @Override
    public void calcPerformance(String carType) {
        if ("SUV".equalsIgnoreCase(carType)) {
            System.out.println("西南区" + carType + "绩效5");
        } else if ("CAR".equalsIgnoreCase(carType)) {
            System.out.println("西南区" + carType + "绩效3");
        }
    }
}
package com.baron.service.southwestimpl;

import com.baron.annotations.District;
import com.baron.service.DistrictPercentage;
import org.springframework.stereotype.Service;

/**
 * 西南区销售分成具体实现
 */
@Service("districtPercentage")
@District("southwest")
public class SouthwestDistrictPercentage implements DistrictPercentage {
    @Override
    public void salePercentage(String carType) {
        if ("SUV".equalsIgnoreCase(carType)) {
            System.out.println("西南区" + carType + "提成1.5%");
        } else if ("CAR".equalsIgnoreCase(carType)) {
            System.out.println("西南区" + carType + "提成0.5%");
        }
    }
}
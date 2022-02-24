package com.baron.service;

/**
 * 绩效计算桥接接口
 * @author 黑马程序员
 * @Company http://www.itheima.com */
public interface DistrictPerformance {
    /**
     * 根据不同车辆类型计算绩效
     *  类型：
     *     CAR
     *     SUV
     * @param carType
     */
    void calcPerformance(String carType);
}

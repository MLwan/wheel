package com.baron.service;

/**
 * 销售分成的桥接接口(桥接模式的桥梁)？？？
 * @author 黑马程序员
 * @Company http://www.itheima.com */
public interface DistrictPercentage {
    /**
     * 不同车型提成
     * @param carType */
    void salePercentage(String carType);
}
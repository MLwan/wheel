package com.baron.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 区域的注解
 * @author 黑马程序员
 * @Company http://www.itheima.com */

@Retention(RetentionPolicy.RUNTIME) //生命周期？？？
@Target(ElementType.TYPE)   //出现的位置？？？
public @interface District {
    /**
     * 指定区域的名称 * @return
     */
    String value()  default "";
}

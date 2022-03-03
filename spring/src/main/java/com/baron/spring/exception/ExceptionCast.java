package com.baron.spring.exception;

/**
 * @Description : 手动抛出自定义空异常，如果使用JudgeEmptyUtil则无需使用该方法
 * @Author :
 * @Date : 2020/5/17
*/
public class ExceptionCast {
    public static void throwParamNullExt(ResultCode resultCode){
        throw new ParamNullException(resultCode);
    }
}

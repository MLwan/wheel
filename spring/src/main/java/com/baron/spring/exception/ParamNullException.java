package com.baron.spring.exception;

import lombok.Getter;

/**
 * @Description : 自定义对象或字符串空异常
 * @Author : zhanwang
 * @Date : 2020/5/17
*/
@Getter
public class ParamNullException extends RuntimeException{

    private ResultCode resultCode;

    public ParamNullException(ResultCode resultCode){
        //异常信息为错误代码——异常信息
        super("errorCode：" + resultCode.code() + "  errorMessage：" + resultCode.message());
        this.resultCode = resultCode;
    }
}

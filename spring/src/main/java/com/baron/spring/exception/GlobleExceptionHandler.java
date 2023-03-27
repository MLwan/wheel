package com.baron.spring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description :  ResponseResult不是很好，参考如下全局异常处理类
 * package com.baron.spring.javaxvalid.javaxValidExt.GlobalExceptionHandler;
 * @Author : zhanwang
 * @Date : 2020/5/17 20230327
*/
@Slf4j
@ControllerAdvice
public class GlobleExceptionHandler {
    /**
     * 参数空异常
    */
    @ExceptionHandler(value = ParamNullException.class)
    @ResponseBody
    public ResponseResult paramNullException(ParamNullException e){
        log.error("catchException : {}\r\nexception: ",e.getMessage(), e);
        ResultCode resultCode = e.getResultCode();
        ResponseResult responseResult = new ResponseResult(resultCode);
        return responseResult;
    }
//    /**
//     * 未知异常
//     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ResponseResult globleException(Exception e){
//        log.error("catchException : {}\r\nexception: ",e.getMessage(), e);
//        ResponseResult responseResult = new ResponseResult(CommonCode.FAIL);
//        return responseResult;
//    }

}

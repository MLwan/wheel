package com.baron.spring.javaxvalid.javaxValidExt;

import com.baron.spring.exception.CommonCode;
import com.baron.spring.exception.ParamNullException;
import com.baron.spring.exception.ResponseResult;
import com.baron.spring.exception.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: 全局异常捕获
 * @author zhanwang
 * @version 1.0
 * @Date 2023-03-24
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 请求校验异常
     * 通过全局异常捕获获取参数校验异常,并且可同时获取多个参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {

        String requestURI = request.getRequestURI();
        log.error("请求地址'{}',请求参数校验失败.", requestURI, e.getMessage(), e);

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String message = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList()).toString();

        return ResultUtil.error("校验失败：" + message);
    }

//    /**
//     * 参数空异常
//     */
//    @ExceptionHandler(value = ParamNullException.class)
//    @ResponseBody
//    public ResponseResult paramNullException(ParamNullException e) {
//        log.error("catchException : {}\r\nexception: ", e.getMessage(), e);
//        ResultCode resultCode = e.getResultCode();
//        ResponseResult responseResult = new ResponseResult(resultCode);
//        return responseResult;
//    }
//
//    /**
//     * 未知异常
//     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ResponseResult globleException(Exception e) {
//        log.error("catchException : {}\r\nexception: ", e.getMessage(), e);
//        ResponseResult responseResult = new ResponseResult(CommonCode.FAIL);
//        return responseResult;
//    }

}

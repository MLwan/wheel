package com.baron.spring.exception;

/**
 * @Description :
 * @Author : zhanwang
 * @Date : 2020/5/17
*/
public interface ResultCode {
    //操作是否成功,true为成功，false操作失败
    boolean success();
    //操作代码
    String code();
    //提示信息
    String message();

}

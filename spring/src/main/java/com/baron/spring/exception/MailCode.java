package com.baron.spring.exception;

import lombok.ToString;

/**
 * @Description : 发送右键错误码
 * @Author : zhanwang
 * @Date : 2020/5/17
*/
@ToString
public enum MailCode implements ResultCode{
    MAIL_ID_NOTEXISTS(false,"0001","mail param id is null"),
    MAIL_MAIL_NOTEXISTS(false,"0002","mail param templateId is null");


    //操作结果
    boolean success;
    //操作代码
    String code;
    //提示信息
    String message;

    private MailCode(boolean success, String code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}

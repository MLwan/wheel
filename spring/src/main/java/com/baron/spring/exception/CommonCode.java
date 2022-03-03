package com.baron.spring.exception;

import lombok.ToString;

/**
 * @Author: zhanwang.
 * @Description:
 * @Date:Created in 2020/03/06 10:00.
 * @Modified By:
 */

@ToString
public enum CommonCode implements ResultCode{
    INVALIDPARAM(false,"0003","非法参数！"),
    SUCCESS(true,"0000","操作成功！"),
    FAIL(false,"9999","操作失败！");


    //操作是否成功
    boolean success;
    //操作代码
    String code;
    //提示信息
    String message;

    private CommonCode(boolean success, String code, String message){
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

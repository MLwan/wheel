package com.baron.spring.exception;

import org.apache.commons.lang3.StringUtils;


/**
 * @Description : 判断对象或字符串为空，为空返回异常给前端response
 * @Author :
 * @Date : 2020/5/17
*/
public class JudgeEmptyUtil {

    /**
     * 判断对象为空
     */
    public static void judgeObjectEmpty(Object obj, ResultCode resultCode) {
        if (obj == null) {
            throw new ParamNullException(resultCode);
        }
    }

    public static void judgeStringEmpty(String str, ResultCode resultCode) {
        if (!StringUtils.isNotBlank(str)) {
            //throw new ParamNullException(resultCode);
            ExceptionCast.throwParamNullExt(resultCode);
        }
    }
}

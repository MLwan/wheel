package com.baron.spring.javaxvalid.javaxValidExt;

public class ResultUtil {

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T object) {
        Result<T> resultVO = new Result();
        resultVO.setData(object);
        resultVO.setSucc(Boolean.TRUE);
        resultVO.setErrCode(0);
        resultVO.setErrMsg("success");
        return resultVO;
    }

    public static Result error(String msg) {
        Result resultVO = new Result();
        resultVO.setErrCode(1001);
        resultVO.setErrMsg(msg);
        resultVO.setSucc(Boolean.FALSE);
        return resultVO;
    }

    public static Result error(Integer code, String msg) {
        Result resultVO = new Result();
        resultVO.setErrCode(code);
        resultVO.setErrMsg(msg);
        resultVO.setSucc(Boolean.FALSE);
        return resultVO;
    }

    public static Result error(Integer code, String msg, String data) {
        Result resultVO = new Result();
        resultVO.setErrCode(code);
        resultVO.setErrMsg(msg);
        resultVO.setData(data);
        resultVO.setSucc(Boolean.FALSE);
        return resultVO;
    }
}

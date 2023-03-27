package com.baron.spring.javaxvalid.javaxValidExt;


import lombok.Data;

/**
 * @author wiley.wang@huolala.cn
 * @date 2019/6/2 18:36
 */
@Data
public class Result<T> {
    private T data;
    private boolean succ;
    private Integer errCode;
    private String errMsg;
}

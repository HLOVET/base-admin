package com.yaraculture.resource.common.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回对象
 */

@Data
public class Result<T> implements Serializable {
    /**
     * 通信数据
     */
    private T data;
    /**
     * 通信状态
     */
    private boolean success = true;
    /**
     * 通信描述
     */
    private String msg = "操作成功";

    /**
     * 错误码
     */
    private String errorCode = "0";

    /**
     * 通过静态方法获取实例
     */
    public static <T> Result<T> of(T data) {
        return new Result<>(data,true);
    }

    public static <T> Result<T> of(String errorCode,String msg) {
        return new Result<>(errorCode,msg);
    }

    public static <T> Result<T> of(T data, boolean flag) {
        return new Result<>(data, flag);
    }

    public static <T> Result<T> of(T data, boolean flag, String msg) {
        return new Result<>(data, flag, msg);
    }

    private Result(String errorCode,String msg) {
        this.errorCode = errorCode;
        this.msg= msg;
    }

    private Result(T data, boolean flag) {
        this.data = data;
        this.success = flag;
    }

    private Result(T data, boolean flag, String msg) {
        this.data = data;
        this.success = flag;
        this.msg = msg;
    }

}

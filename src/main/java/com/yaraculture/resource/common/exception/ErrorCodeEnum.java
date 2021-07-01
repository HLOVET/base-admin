package com.yaraculture.resource.common.exception;


public enum ErrorCodeEnum {

    //系统问题
    SYS_ERROR("1000", "系统错误,请联系管理员"),



    //资源模块
    TARGET_NOT_EXSIT("TARGET_NOT_EXSIT","资源不存在");

    private String errorCode;
    private String errorMes;
    private String[] params;

    ErrorCodeEnum(String errorCode, String errorMes) {
        this.errorCode = errorCode;
        this.errorMes = errorMes;
    }

    public ErrorCodeEnum withParams(String... params) {
        this.params = params;
        return this;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        if (params != null) {
            return String.format(errorMes, params);
        }
        return errorMes;
    }

}

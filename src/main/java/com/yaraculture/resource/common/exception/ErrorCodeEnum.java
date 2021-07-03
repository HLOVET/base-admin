package com.yaraculture.resource.common.exception;


public enum ErrorCodeEnum {

    //系统问题
    SYS_ERROR("1000", "系统错误,请联系管理员"),
    //文件上传失败
    IMPORT_FILE_FAILED("IMPORT_FILE_FAILED","文件上传失败"),
    IMPORT_FILE_OVERSIZE("IMPORT_FILE_OVERSIZE","导入文件过大"),

    //人员已存在
    STAR_INFO_EXIST("STAR_INFO_EXIST","达人已经存在"),

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

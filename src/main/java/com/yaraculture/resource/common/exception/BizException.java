package com.yaraculture.resource.common.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;


/**
 * 自定义异常  code+msg
 */
@Data
@Accessors(chain = true)
public class BizException extends RuntimeException{

    /**
     * 错误码
     */
    protected String errorCode;
    /**
     * 错误信息
     */
    protected String errorMsg;


    public BizException() {
    }

    public BizException(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public static BizException build(ErrorCodeEnum errorCodeEnum) {
        return new BizException(errorCodeEnum.getErrorCode(),errorCodeEnum.getMessage());
    }

}

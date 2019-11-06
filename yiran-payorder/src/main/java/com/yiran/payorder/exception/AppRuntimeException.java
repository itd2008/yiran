package com.yiran.payorder.exception;

import com.yiran.paychannel.enums.ErrorCode;

/**
 *
 * <p>运行时异常</p>
 */
public class AppRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -5982000416422002899L;

    public AppRuntimeException() {
        super();
    }

    public AppRuntimeException(String message) {
        super(message);
    }

    public AppRuntimeException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }

    public AppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppRuntimeException(Throwable cause) {
        super(cause);
    }

    ErrorCode errorCode = ErrorCode.RUNTIME_EXCEPTION;

    public String getCode(){
        return this.errorCode.getErrorCode();
    }

}

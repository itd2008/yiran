package com.yiran.paychannel.exception;

import com.yiran.paychannel.enums.ErrorCode;

/**
 * 
 * <p>checked exception 基类</p>
 */
public class  AppCheckedException extends Exception {

    private static final long serialVersionUID = 7240166912823355206L;

    public AppCheckedException() {
        super();
    }

    public AppCheckedException(String message) {
        super(message);
    }
    
    public AppCheckedException(ErrorCode errorCode,String message) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public AppCheckedException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.errorCode = errorCode;
    }
    
    public AppCheckedException(ErrorCode errorCode,String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public AppCheckedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppCheckedException(Throwable cause) {
        super(cause);
    }
    
    ErrorCode errorCode = ErrorCode.EXCEPTION;
    
    public String getCode(){
        return this.errorCode.getErrorCode();
    }
}

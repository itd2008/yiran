package com.yiran.payorder.exception;

import com.yiran.paychannel.enums.ErrorCode;
import com.yiran.paychannel.exception.AppCheckedException;

/**
 * 
 * <p>通讯连接异常</p>
 */
public class CommunicateException extends AppCheckedException {

    private static final long serialVersionUID = -3178853059992439281L;
    
    public CommunicateException() {
        super();
    }

    public CommunicateException(String message) {
        super(message);
    }

    public CommunicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommunicateException(Throwable cause) {
        super(cause);
    }
    
    ErrorCode errorCode = ErrorCode.COMMUNICATE_ERROR;
    @Override
    public String getCode() {
        return this.errorCode.getErrorCode();
    }

}

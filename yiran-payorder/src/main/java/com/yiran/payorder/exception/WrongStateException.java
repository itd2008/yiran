package com.yiran.payorder.exception;

import com.yiran.paychannel.enums.ErrorCode;
import com.yiran.paychannel.exception.AppCheckedException;

public class WrongStateException extends AppCheckedException {
    
	private static final long serialVersionUID = 4222756819476461443L;

	public WrongStateException(ErrorCode errorCode) {
        super(errorCode);
    }

    public WrongStateException(ErrorCode errorCode,String message) {
        super(errorCode,message);
    }

    public WrongStateException(ErrorCode errorCode,String message, Throwable cause) {
        super(errorCode,message, cause);
    }

    public WrongStateException(Throwable cause) {
        super(cause);
    }
}

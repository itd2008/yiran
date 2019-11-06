package com.yiran.paychannel.exception;

import com.yiran.paychannel.enums.ErrorCode;

public class RouteChannelException extends AppCheckedException{

    private static final long serialVersionUID = 6311943434224219868L;
    
    public RouteChannelException(ErrorCode errorCode) {
        super(errorCode);
    }

    public RouteChannelException(ErrorCode errorCode,String message) {
        super(errorCode,message);
    }

    public RouteChannelException(ErrorCode errorCode,String message, Throwable cause) {
        super(errorCode,message, cause);
    }

    public RouteChannelException(Throwable cause) {
        super(cause);
    }
}

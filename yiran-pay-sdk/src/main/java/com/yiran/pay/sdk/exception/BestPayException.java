package com.yiran.pay.sdk.exception;

import com.yiran.pay.sdk.enums.BestPayResultEnum;
public class BestPayException extends RuntimeException {

    private Integer code;

    public BestPayException(BestPayResultEnum resultEnum) {
        super(resultEnum.getMsg());
        code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}

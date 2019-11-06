package com.yiran.payorder.response;

import java.io.Serializable;

import com.yiran.payorder.domain.ReturnInfo;


public interface BaseResponse extends Serializable {
    public ReturnInfo getReturnInfo();
    public void setReturnInfo(ReturnInfo returnInfo);
}

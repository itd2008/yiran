package com.yiran.payorder.response;

import com.yiran.payorder.domain.ReturnInfo;

public class InstOrderResultResponse implements BaseResponse{

	private static final long serialVersionUID = -5698088319219706028L;

	private String instReturnOrderNo;

	protected ReturnInfo returnInfo;

	public ReturnInfo getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(ReturnInfo returnInfo) {
		this.returnInfo = returnInfo;
	}
	public String getInstReturnOrderNo() {
		return instReturnOrderNo;
	}
	public void setInstReturnOrderNo(String instReturnOrderNo) {
		this.instReturnOrderNo = instReturnOrderNo;
	}

}

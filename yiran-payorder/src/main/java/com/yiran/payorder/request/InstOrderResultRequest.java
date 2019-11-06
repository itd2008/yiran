package com.yiran.payorder.request;

import java.io.Serializable;

public class InstOrderResultRequest  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5420583460578980392L;
	/**
	 * 退款订单号
	 */
	private String orgiInstOrderNo;
	/**
	 * API类型
	 */
	private String apiType;
	public String getOrgiInstOrderNo() {
		return orgiInstOrderNo;
	}
	public void setOrgiInstOrderNo(String orgiInstOrderNo) {
		this.orgiInstOrderNo = orgiInstOrderNo;
	}
	public String getApiType() {
		return apiType;
	}
	public void setApiType(String apiType) {
		this.apiType = apiType;
	}
}

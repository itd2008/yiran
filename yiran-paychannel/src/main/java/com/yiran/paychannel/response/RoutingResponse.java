package com.yiran.paychannel.response;

import java.io.Serializable;

public class RoutingResponse implements Serializable{

	private static final long serialVersionUID = 7232217664577966813L;
	/**
	 * 渠道编码
	 */
	private String fundChannelCode;

	public String getFundChannelCode() {
		return fundChannelCode;
	}

	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}

	
}

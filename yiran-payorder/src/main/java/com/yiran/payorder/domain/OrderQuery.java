package com.yiran.payorder.domain;

import java.util.Date;

import com.netfinworks.biz.common.util.QueryBase;

public class OrderQuery extends QueryBase {

	private static final long serialVersionUID = -1554058695231783334L;
	private String status;
	private String orderType;
	private String instOrderNo;
	private String fundChannelCode;
	private String fundChannelApi;
	private Date gmtCreateBegin;
	private Date gmtCreateEnd;

	public String getFundChannelCode() {
		return fundChannelCode;
	}

	public void setFundChannelCode(String fundChannelCode) {
		this.fundChannelCode = fundChannelCode;
	}

	public String getFundChannelApi() {
		return fundChannelApi;
	}

	public void setFundChannelApi(String fundChannelApi) {
		this.fundChannelApi = fundChannelApi;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getInstOrderNo() {
		return instOrderNo;
	}

	public void setInstOrderNo(String instOrderNo) {
		this.instOrderNo = instOrderNo;
	}

	public Date getGmtCreateBegin() {
		return gmtCreateBegin;
	}

	public void setGmtCreateBegin(Date gmtCreateBegin) {
		this.gmtCreateBegin = gmtCreateBegin;
	}

	public Date getGmtCreateEnd() {
		return gmtCreateEnd;
	}

	public void setGmtCreateEnd(Date gmtCreateEnd) {
		this.gmtCreateEnd = gmtCreateEnd;
	}

}

package com.yiran.pay.sdk.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.yiran.pay.sdk.enums.BestPayTypeEnum;

/**
 * 支付时请求参数
 */
public class PayFundRequest {

    /**
     * 支付方式.
     */
    private BestPayTypeEnum payTypeEnum;

    /**
     * 订单号.
     */
    private String orderId;

    /**
     * 订单金额.
     */
    private Double orderAmount;

    /**
     * 订单名字.
     */
    private String orderName;

    /**
     * 微信openid, 仅微信支付时需要
     */
    private String openid;

    /**
     * 客户端访问Ip  外部H5支付时必传，需要真实Ip
     */
    private String spbillCreateIp;
    /**
     * api接口url
     */
    private String payApiUrl;
    
    /** 扩展信息 */
    protected Map<String, String> extension        = new ConcurrentHashMap<String, String>();
    
    
	public Map<String, String> getExtension() {
		return extension;
	}

	public void setExtension(Map<String, String> extension) {
		this.extension = extension;
	}

	public String getPayApiUrl() {
		return payApiUrl;
	}

	public void setPayApiUrl(String payApiUrl) {
		this.payApiUrl = payApiUrl;
	}

	public BestPayTypeEnum getPayTypeEnum() {
		return payTypeEnum;
	}

	public void setPayTypeEnum(BestPayTypeEnum payTypeEnum) {
		this.payTypeEnum = payTypeEnum;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSpbillCreateIp() {
		return spbillCreateIp;
	}

	public void setSpbillCreateIp(String spbillCreateIp) {
		this.spbillCreateIp = spbillCreateIp;
	}
    
}

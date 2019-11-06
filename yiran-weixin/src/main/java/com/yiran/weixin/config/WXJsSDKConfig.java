package com.yiran.weixin.config;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 微信JS-SDK配置
 * @author pandaa
 *
 */
public class WXJsSDKConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8561518491439725473L;
	/**
	 * 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
	 */
	private boolean debug = true;
	/**
	 * 必填，公众号的唯一标识
	 */
	private String appId;
	/**
	 * 必填，生成签名的时间戳
	 */
	private String timestamp;
	
	/**
	 * 必填，生成签名的随机串
	 */
	private String nonceStr;
	/**
	 * 必填，签名
	 */
	private String signature;

	/**
	 * 必填，需要使用的JS接口列表
	 */
	private String[] jsApiList;

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String[] getJsApiList() {
		return jsApiList;
	}

	public void setJsApiList(String[] jsApiList) {
		this.jsApiList = jsApiList;
	}
	
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appId", getAppId())
            .append("timestamp", getTimestamp())
            .append("debug", isDebug())
            .append("nonceStr",getNonceStr())
            .append("signature", getSignature())
            .append("jsApiList", getJsApiList())
            .toString();
    }
}

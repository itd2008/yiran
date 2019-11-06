package com.yiran.weixin.servlet;
import com.yiran.weixin.token.AccessToken;
import com.yiran.weixin.token.JsApiTicket;
import com.yiran.weixin.token.WXApiTicket;

public class WeixinContext {
	private String appId;
	private String appSecurt;
	private String baseUrl;
	private String token;
	private AccessToken accessToken;
	private JsApiTicket jsApiTicket;
	private WXApiTicket wxApiTicket;
	private static WeixinContext wc;
	
	private WeixinContext(){}
	public static WeixinContext getInstance() {
		if(wc==null) wc = new WeixinContext();
		return wc;
	}
	
	
	public AccessToken getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecurt() {
		return appSecurt;
	}
	public void setAppSecurt(String appSecurt) {
		this.appSecurt = appSecurt;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public JsApiTicket getJsApiTicket() {
		return jsApiTicket;
	}
	public void setJsApiTicket(JsApiTicket jsApiTicket) {
		this.jsApiTicket = jsApiTicket;
	}
	public WXApiTicket getWxApiTicket() {
		return wxApiTicket;
	}
	public void setWxApiTicket(WXApiTicket wxApiTicket) {
		this.wxApiTicket = wxApiTicket;
	}
	
}

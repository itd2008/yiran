package com.yiran.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yiran.weixin.entity.WeixinFinalValue;
import com.yiran.weixin.kit.WeixinBasicKit;
import com.yiran.weixin.servlet.WeixinContext;
import com.yiran.weixin.token.AccessToken;
import com.yiran.weixin.token.JsApiTicket;
import com.yiran.weixin.token.WXApiTicket;
import com.yiran.weixin.utils.WeiXinJsonUtil;

/**
 * 刷新token定时任务
 * @author pandaa
 *
 */
@Component("refreshAccessTokenTask")
public class RefreshAccessTokenTask {
	private static final Logger logger = LoggerFactory.getLogger(RefreshAccessTokenTask.class);
	public void refreshToken() {
		String url = WeixinFinalValue.ACCESS_TOKEN_URL;
		logger.info("Token url is------>>>>【{}】",url);
		url = url.replaceAll("APPID", WeixinContext.getInstance().getAppId());
		url = url.replaceAll("APPSECRET", WeixinContext.getInstance().getAppSecurt());
		String content = WeixinBasicKit.sendGet(url);
		logger.info("RefreshAccessTokenTask is content:【{}】",content);
		if(WeixinBasicKit.checkRequestSucc(content)) {
			AccessToken at = (AccessToken)WeiXinJsonUtil.getInstance().json2obj(content, AccessToken.class);
			logger.info("AccessToken is :{}",at);
			WeixinContext.getInstance().setAccessToken(at);
			//根据access_token获取jsapi_ticket
			//https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi
			String jsapi_ticket_url = WeixinFinalValue.JSAPI_TICKET_URL;
			jsapi_ticket_url = jsapi_ticket_url.replaceAll("ACCESS_TOKEN", at.getAccess_token());
			logger.info("Jsapi Ticket url is ------>>>>【{}】",jsapi_ticket_url);
			String jtString = WeixinBasicKit.sendGet(jsapi_ticket_url);
			logger.info("Refresh Jsapi Ticket is content:【{}】",jtString);
			JsApiTicket jt = (JsApiTicket)WeiXinJsonUtil.getInstance().json2obj(jtString, JsApiTicket.class);
			logger.info("Jsapi Ticket is :{}",jt);
			WeixinContext.getInstance().setJsApiTicket(jt);
			
			String wxapi_ticket_url = WeixinFinalValue.WX_CARD_API_TICKET_URL;
			wxapi_ticket_url = wxapi_ticket_url.replaceAll("ACCESS_TOKEN", at.getAccess_token());
			logger.info("Wx card api Ticket url is ------>>>>【{}】",wxapi_ticket_url);
			String wxString = WeixinBasicKit.sendGet(wxapi_ticket_url);
			logger.info("Refresh Wx card api Ticket is content:【{}】",wxString);
			WXApiTicket wxcard = (WXApiTicket)WeiXinJsonUtil.getInstance().json2obj(wxString, WXApiTicket.class);
			logger.info("Wx card api Ticket is :{}",wxcard);
			WeixinContext.getInstance().setWxApiTicket(wxcard);
		} else {
			refreshToken();
		}
	}
}

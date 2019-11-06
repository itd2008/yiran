package com.yiran.weixin.service.impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yiran.weixin.entity.WUser;
import com.yiran.weixin.entity.WeixinFinalValue;
import com.yiran.weixin.kit.WeixinBasicKit;
import com.yiran.weixin.service.IWUserService;
import com.yiran.weixin.servlet.WeixinContext;
import com.yiran.weixin.utils.WeiXinJsonUtil;
@Service
public class WUserService implements IWUserService {
	private static final Logger logger = LoggerFactory.getLogger(WUserService.class);
	@Override
	public WUser queryByOpenid(String openid) {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.USER_QUERY);
		url = url.replace("OPENID", openid);
		logger.info("------------------------>>>>>>用户基本信息URL："+url);
		String json = WeixinBasicKit.sendGet(url);
		logger.info("------------------------>>>>>>用户基本信息json："+json);
		WUser uUser =(WUser)WeiXinJsonUtil.getInstance().json2obj(json, WUser.class);
		
		logger.info("------------------------>>>>>>用户基本信息json："+uUser);
		return uUser;
	}

	@Override
	public String queryOpenidByCode(String code)  {
		try {
			String url = WeixinFinalValue.AUTH_GET_OID;
			url = url.replace("APPID", WeixinContext.getInstance().getAppId())
			   .replace("SECRET",WeixinContext.getInstance().getAppSecurt())
			   .replace("CODE", code);
			String json = WeixinBasicKit.sendGet(url);
			String openid = WeiXinJsonUtil.getMapper().readTree(json).get("openid").asText();
			return openid;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}

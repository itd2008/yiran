package com.yiran.web.controller.weixin;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.yiran.common.utils.StringUtils;
import com.yiran.framework.web.base.BaseController;
import com.yiran.weixin.config.WXJsSDKConfig;
import com.yiran.weixin.constant.JsApiTypeConstant;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.entity.CardList;
import com.yiran.weixin.entity.WeiXinOAuthCodeTokenVO;
import com.yiran.weixin.entity.WeixinCard;
import com.yiran.weixin.entity.WeixinCardExt;
import com.yiran.weixin.kit.WeixinBasicKit;
import com.yiran.weixin.service.impl.WeixinSettingServiceImpl;
import com.yiran.weixin.servlet.WeixinContext;
import com.yiran.weixin.utils.ApplicationContextUtil;
import com.yiran.weixin.utils.JsSignUtils;
import com.yiran.weixin.utils.WeiXinJsonUtil;
import com.yiran.weixin.utils.SignUtils;

import cn.hutool.core.lang.UUID;
/**
 * 微信Js-SDK
 */
@Controller
@RequestMapping("/public/jssdk/weixin")
public class WeixinJsSdkController  extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(WeixinJsSdkController.class);

	/**
	 * 打开相机
	 * @param request
	 * @param response
	 * @param session
	 * @param mmap
	 * @return
	 */
	@GetMapping("/openCamera")
	public String openCamera(HttpServletRequest request, HttpServletResponse response,HttpSession session, ModelMap mmap) {
		logger.info("JS-SDK Demo - 打开相机");
		WeixinSettingServiceImpl weixinSettingService = ApplicationContextUtil.getBean(WeixinSettingServiceImpl.class);
		//1.判断这个用户是否注册，如果注册将用户信息存入session，跳转到主菜单页面
		String openId = null;
		//获取appid
		String appid = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPID).getWeixinValue();
		logger.info("JS-SDK Demo 微信appId:"+appid);
		//获取秘钥
		String appsecret = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPSECRET).getWeixinValue();
		logger.info("JS-SDK Demo 微信秘钥appsecret:"+appsecret);
		String access_token = WeixinContext.getInstance().getAccessToken().getAccess_token();
		logger.info("JS-SDK Demo 微信token凭证access_token："+access_token);
		openId = (String) session.getAttribute("openId");
		logger.info("【session中的openId】："+openId);
		//授权后重定向的回调链接地址
		String redirect_url = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_REDIRECT_URI).getWeixinValue();
		logger.info("授权后重定向的回调链接地址："+redirect_url);
		if(StringUtils.isBlank(openId) ){//session中存在
			logger.info("---------->>>>>>>>session不存在openId<<<<<<<--------------");
			String code = request.getParameter("code");
			logger.info("JS-SDK Demo 获取的code值是："+code);
			//获取通过code换取access_token以及OpenID API URL
			String codeToOpenIdAPI = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_CODE_OPENID).getWeixinValue();
			logger.info("JS-SDK Demo 获取通过code换取access_token以及OpenID的 API URL:"+codeToOpenIdAPI);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("APPID", appid);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("SECRET", appsecret);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("CODE", code);
			codeToOpenIdAPI = codeToOpenIdAPI.replaceAll("&amp;", "&");
			logger.info("最终codeToOpenIdAPI ："+codeToOpenIdAPI);
			//发送请求
			String json = WeixinBasicKit.sendGet(codeToOpenIdAPI);
			logger.info("发送请求后获取json对象："+json);
			if(WeixinBasicKit.checkRequestSucc(json)){//成功
				logger.info("开始将json转换为对象.......");
				WeiXinOAuthCodeTokenVO oAuthCodeTokenVO = (WeiXinOAuthCodeTokenVO) WeiXinJsonUtil.getInstance().json2obj(json,WeiXinOAuthCodeTokenVO.class);
				logger.info("将结果转换为json："+oAuthCodeTokenVO);
				openId = oAuthCodeTokenVO.getOpenid();
				session.setAttribute("openId", openId);
				logger.info("【根据code获取openId】 :"+openId);
			}
		}
		
		//构建微信JS-SDK对象
		WXJsSDKConfig wc = new WXJsSDKConfig();
		wc.setAppId(appid);
		wc.setDebug(false);
		StringBuffer requestURL = request.getRequestURL();
		logger.info(requestURL.toString() + "==");
		String queryString = request.getQueryString();
		String url2 = "http://" + request.getServerName() // 服务器地址
				+ request.getContextPath() // 项目名称
				+ request.getServletPath(); // 请求页面或其他地址
		if (queryString != null) {
			url2 += "?" + queryString;
		}
		logger.info("【请求URL】 :"+url2);
		//获取原签名
		Map<String, String> signDate = SignUtils.getJsapiSignDate(WeixinContext.getInstance().getJsApiTicket().getTicket(), url2);
		//签名
		Map<String, String> sign = SignUtils.sign(signDate.get("signDate"));
		wc.setNonceStr(signDate.get("nonceStr"));
		wc.setSignature(sign.get("signature"));
		wc.setTimestamp(signDate.get("timestamp"));
		//需要使用的JS接口列表'chooseImage','previewImage','uploadImage','downloadImage'
		String[] jsApiList ={JsApiTypeConstant.CHOOSE_IMAGE,JsApiTypeConstant.PREVIEW_IMAGE,
				JsApiTypeConstant.UPLOAD_IMAGE,JsApiTypeConstant.DOWNLOAD_IMAGE,
				JsApiTypeConstant.OPEN_LOCATION,JsApiTypeConstant.CLOSE_WINDOW,
				JsApiTypeConstant.GET_LOCATION,JsApiTypeConstant.START_RECORD,
				JsApiTypeConstant.STOP_RECORD,JsApiTypeConstant.PLAY_VOICE,
				JsApiTypeConstant.PAUSE_VOICE,JsApiTypeConstant.STOP_VOICE,
				JsApiTypeConstant.UPLOAD_VOICE,JsApiTypeConstant.DOWNLOAD_VOICE,
				JsApiTypeConstant.TRANSLATE_VOICE,JsApiTypeConstant.SCAN_QRCODE,
				JsApiTypeConstant.UPDATE_APP_MESSAGE_SHARE_DATA,JsApiTypeConstant.UPDATE_TIMELINE_SHARE_DATA,
				JsApiTypeConstant.ON_MENU_SHARE_WEIBO,JsApiTypeConstant.ON_MENU_SHARE_TIMELINE,
				JsApiTypeConstant.ON_MENU_SHARE_APP_MESSAGE};
		wc.setJsApiList(jsApiList);
		logger.info("微信JS-SDK 配置信息:{}",wc);
		mmap.put("wc", wc);
		String prefix = "weixin/jssdkDemo/demos";
		return prefix + "/buttons";
	}
	
	
	@GetMapping("/weixinCard")
	public String weixinCard(HttpServletRequest request, HttpServletResponse response,HttpSession session, ModelMap mmap) {
		logger.info("JS-SDK Demo - 微信卡券");
		WeixinSettingServiceImpl weixinSettingService = ApplicationContextUtil.getBean(WeixinSettingServiceImpl.class);
		//1.判断这个用户是否注册，如果注册将用户信息存入session，跳转到主菜单页面
		String openId = null;
		//获取appid
		String appid = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPID).getWeixinValue();
		logger.info("JS-SDK Demo 微信appId:"+appid);
		//获取秘钥
		String appsecret = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPSECRET).getWeixinValue();
		logger.info("JS-SDK Demo 微信秘钥appsecret:"+appsecret);
		String access_token = WeixinContext.getInstance().getAccessToken().getAccess_token();
		logger.info("JS-SDK Demo 微信token凭证access_token："+access_token);
		openId = (String) session.getAttribute("openId");
		logger.info("【session中的openId】："+openId);
		//授权后重定向的回调链接地址
		String redirect_url = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_REDIRECT_URI).getWeixinValue();
		logger.info("授权后重定向的回调链接地址："+redirect_url);
		if(StringUtils.isBlank(openId) ){//session中存在
			logger.info("---------->>>>>>>>session不存在openId<<<<<<<--------------");
			String code = request.getParameter("code");
			logger.info("JS-SDK Demo 获取的code值是："+code);
			//获取通过code换取access_token以及OpenID API URL
			String codeToOpenIdAPI = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_CODE_OPENID).getWeixinValue();
			logger.info("JS-SDK Demo 获取通过code换取access_token以及OpenID的 API URL:"+codeToOpenIdAPI);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("APPID", appid);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("SECRET", appsecret);
			codeToOpenIdAPI = codeToOpenIdAPI.replace("CODE", code);
			codeToOpenIdAPI = codeToOpenIdAPI.replaceAll("&amp;", "&");
			logger.info("最终codeToOpenIdAPI ："+codeToOpenIdAPI);
			//发送请求
			String json = WeixinBasicKit.sendGet(codeToOpenIdAPI);
			logger.info("发送请求后获取json对象："+json);
			if(WeixinBasicKit.checkRequestSucc(json)){//成功
				logger.info("开始将json转换为对象.......");
				WeiXinOAuthCodeTokenVO oAuthCodeTokenVO = (WeiXinOAuthCodeTokenVO) WeiXinJsonUtil.getInstance().json2obj(json,WeiXinOAuthCodeTokenVO.class);
				logger.info("将结果转换为json："+oAuthCodeTokenVO);
				openId = oAuthCodeTokenVO.getOpenid();
				session.setAttribute("openId", openId);
				logger.info("【根据code获取openId】 :"+openId);
			}
		}
		
		//构建微信JS-SDK对象
		WXJsSDKConfig wc = new WXJsSDKConfig();
		wc.setAppId(appid);
		wc.setDebug(false);
		StringBuffer requestURL = request.getRequestURL();
		logger.info(requestURL.toString() + "==");
		String queryString = request.getQueryString();
		String url2 = "http://" + request.getServerName() // 服务器地址
				+ request.getContextPath() // 项目名称
				+ request.getServletPath(); // 请求页面或其他地址
		if (queryString != null) {
			url2 += "?" + queryString;
		}
		logger.info("【请求URL】 :"+url2);
		//获取原签名
		Map<String, String> signDate = SignUtils.getJsapiSignDate(WeixinContext.getInstance().getJsApiTicket().getTicket(), url2);
		//签名
		Map<String, String> sign = SignUtils.sign(signDate.get("signDate"));
		wc.setNonceStr(signDate.get("nonceStr"));
		wc.setSignature(sign.get("signature"));
		wc.setTimestamp(signDate.get("timestamp"));
		//需要使用的JS接口列表'chooseImage','previewImage','uploadImage','downloadImage'
		String[] jsApiList ={JsApiTypeConstant.CHOOSE_IMAGE,JsApiTypeConstant.PREVIEW_IMAGE,
				JsApiTypeConstant.UPLOAD_IMAGE,JsApiTypeConstant.DOWNLOAD_IMAGE,
				JsApiTypeConstant.OPEN_LOCATION,JsApiTypeConstant.CLOSE_WINDOW,
				JsApiTypeConstant.GET_LOCATION,JsApiTypeConstant.START_RECORD,
				JsApiTypeConstant.STOP_RECORD,JsApiTypeConstant.PLAY_VOICE,
				JsApiTypeConstant.PAUSE_VOICE,JsApiTypeConstant.STOP_VOICE,
				JsApiTypeConstant.UPLOAD_VOICE,JsApiTypeConstant.DOWNLOAD_VOICE,
				JsApiTypeConstant.TRANSLATE_VOICE,JsApiTypeConstant.SCAN_QRCODE,
				JsApiTypeConstant.UPDATE_APP_MESSAGE_SHARE_DATA,JsApiTypeConstant.UPDATE_TIMELINE_SHARE_DATA,
				JsApiTypeConstant.ON_MENU_SHARE_WEIBO,JsApiTypeConstant.ON_MENU_SHARE_TIMELINE,
				JsApiTypeConstant.ON_MENU_SHARE_APP_MESSAGE};
		wc.setJsApiList(jsApiList);
		logger.info("微信JS-SDK 配置信息:{}",wc);
		mmap.put("wc", wc);
		//WeixinCard
		WeixinCard wxcard = new WeixinCard();
		//随机字符
		wxcard.setNonceStr(SignUtils.create_nonce_str());
		//时间戳
		wxcard.setTimestamp(SignUtils.create_timestamp());
		wxcard.setSignType("SHA1");
		//获取原签名数据
		String sign_data = SignUtils.genSignData(JSON.parseObject(JSON.toJSONString(wxcard)));
		logger.info("微信Card 原签名数据：{}",sign_data);
		Map<String, String> wxcard_sign = SignUtils.sign(sign_data);
		String signature = wxcard_sign.get("signature");
		logger.info("微信Card 签名数据：{}",signature);
		wxcard.setCardSign(signature);
		mmap.put("wxcard", wxcard);
		
		//添加卡券
		WeixinCardExt we =new WeixinCardExt();
		we.setCode(SignUtils.create_nonce_str());
		we.setOpenid(openId);
		we.setNonce_str(wc.getNonceStr());
		we.setTimestamp(wc.getTimestamp());
		we.setApi_ticket(WeixinContext.getInstance().getWxApiTicket().getTicket());
		we.setCard_id(UUID.randomUUID().toString().replaceAll("-", ""));
		//签名
		//签名原串
		String yuan_sign_data = SignUtils.getWeiXinCardData(we);
		logger.info("添加卡券-签名原串，yuan_sign_data：{}",yuan_sign_data);
		Map<String, String> sign2 = SignUtils.sign(yuan_sign_data);
		logger.info("添加卡券-签名，sign：{}",sign2.get("signature"));
		we.setSignature(sign2.get("signature"));
		CardList cardList = new CardList();
		cardList.setCardId(UUID.randomUUID().toString());
		cardList.setCardExt(JSON.toJSONString(we));
		mmap.put("cardList", cardList);
		String prefix = "weixin/jssdkDemo/demos";
		return prefix + "/buttons_card";
	}
}

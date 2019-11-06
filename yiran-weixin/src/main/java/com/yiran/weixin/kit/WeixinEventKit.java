package com.yiran.weixin.kit;

import java.io.IOException;
import java.util.Map;

import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.weixin.entity.WUser;
import com.yiran.weixin.entity.WeixinQr;
import com.yiran.weixin.service.IWeixinQrService;
import com.yiran.weixin.servlet.BeanFactoryContext;
import com.yiran.weixin.utils.ApplicationContextUtil;
import com.yiran.weixin.conversion.ConversionObject;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.service.impl.WUserService;
import com.yiran.weixin.service.impl.WeixinMenuServiceImpl;
import com.yiran.weixin.service.impl.WeixinSettingServiceImpl;
import com.yiran.weixin.service.impl.WeixinUserServiceImpl;
public class WeixinEventKit {
	private static final Logger logger = LoggerFactory.getLogger(WeixinEventKit.class);
	public static String handlerEventMsg(Map<String, String> msgMap) throws IOException {
		String event = msgMap.get("Event");
		String clientIp = msgMap.get("clientIp");
		if ("CLICK".equals(event)) {
			// 单击事件
			return handlerClickEvent(msgMap);
		} else if ("SCAN".equals(event)) {
			// 扫描
			return handlerScanEvent(msgMap, clientIp);

		} else if ("subscribe".equals(event)) {
			// 用户关注事件
			return handlerSubscribeEvent(msgMap, clientIp);

		} else if ("unsubscribe".equals(event)) {
			// 取消关注事件
			return handlerUnsubscribeEvent(msgMap);

		}
		return null;
	}

	/**
	 * 扫描事件
	 * 
	 * @param msgMap
	 * @return
	 * @throws IOException
	 */
	private static String handlerScanEvent(Map<String, String> msgMap, Object obj) throws IOException {
		String snum = getSence(msgMap, false);
		logger.info("扫描二维码获取场景值:" + snum);
		String openid = msgMap.get("FromUserName");
		IWeixinQrService weixinQrService = (IWeixinQrService) BeanFactoryContext.getBean("weixinQrService");
		// 获得微信二维码对象
		WeixinQr wq = weixinQrService.loadBySnum(Integer.parseInt(snum));
		String type = wq.getType();
		logger.info("扫描二维码获取游戏类型：" + type);
		String mesg = "";
		return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, mesg));
	}

	/**
	 * 取消事件
	 * 
	 * @param msgMap
	 * @return
	 */
	private static String handlerUnsubscribeEvent(Map<String, String> msgMap) {

		return null;
	}

	/**
	 * 关注事件
	 * 
	 * @param msgMap
	 * @return
	 * @throws IOException
	 */
	private static String handlerSubscribeEvent(Map<String, String> msgMap, Object obj) throws IOException {

		String openid = msgMap.get("FromUserName");
		logger.info("关注事件获取openID:" + openid);
		//ApplicationContextUtil
		WUserService wUserService = ApplicationContextUtil.getBean(WUserService.class);
		//根据openId获取微信用户信息
		WUser wUser = wUserService.queryByOpenid(openid);
		logger.info("根据OpenId获取微信用户信息:" + wUser);
		WeixinUserServiceImpl weixinUserService = ApplicationContextUtil.getBean(WeixinUserServiceImpl.class);
		//对象转换
		WeixinUser weixinUser = ConversionObject.conversionToWeixinUser(wUser);
		//根据openId到sys_weixin_user表查询数据，如果数据存在则修改，不存在就保存数据
		WeixinUser wxu = weixinUserService.selectWeixinUserByOpenId(openid);
		logger.info("根据OpenId到sys_weixin_user表获取微信用户信息:" + wxu);
		if(wxu!=null){
			wxu.setNickname(wUser.getNickname());
			wxu.setCity(wUser.getCity());
			wxu.setProvince(wUser.getProvince());
			wxu.setCountry(wUser.getCountry());
			wxu.setHeadimgurl(wUser.getHeadimgurl());
			weixinUserService.updateWeixinUser(wxu);
		}else{
			weixinUserService.insertWeixinUser(weixinUser);
		}
		WeixinSettingServiceImpl weixinSettingService = ApplicationContextUtil.getBean(WeixinSettingServiceImpl.class);
		//响应信息
		String mesg = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_RETURN_TEXT).getWeixinValue();
		
		return WeixinMessageKit.map2xml(MessageCreateKit.createTextMsg(msgMap, mesg));
	}

	
	/**
	 * 点击事件
	 * 
	 * @param msgMap
	 * @return
	 * @throws IOException
	 */
	private static String handlerClickEvent(Map<String, String> msgMap) throws IOException {
		//WeixinMenuServiceImpl
		String keyCode = msgMap.get("EventKey");
		String event = msgMap.get("Event");
		String content = "";
		if("CLICK".equals(event)){
			if(!StringUtil.isBlank(keyCode)){
				WeixinMenuServiceImpl weixinMenuService = ApplicationContextUtil.getBean(WeixinMenuServiceImpl.class);
				content = weixinMenuService.selectMenuByWeixinMenuType(keyCode);
			}
		}
		Map<String, Object> map = MessageCreateKit.createTextMsg(msgMap, content);
		return WeixinMessageKit.map2xml(map);
	}

	/**
	 * 根据扫描的eventKey获得场景值snum
	 * 
	 * @param msgMap
	 * @param subscribe
	 * @return
	 */
	private static String getSence(Map<String, String> msgMap, boolean subscribe) {
		String key = msgMap.get("EventKey");
		if (key == null || "".equals(key))
			return null;
		if (subscribe)
			return key.split("_")[1];
		else
			return key;
	}

}

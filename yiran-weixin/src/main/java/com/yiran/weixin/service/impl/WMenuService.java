package com.yiran.weixin.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yiran.weixin.entity.WeixinFinalValue;
import com.yiran.weixin.entity.WeixinMenuDto;
import com.yiran.weixin.kit.WeixinBasicKit;
import com.yiran.weixin.service.IWMenuService;
import com.yiran.weixin.utils.WeiXinJsonUtil;
// TODO需要修改
@Service("wMenuService")
public class WMenuService implements IWMenuService {
	private static final Logger logger = LoggerFactory.getLogger(WMenuService.class);
	//@Autowired
	//private AgentWeixinMenuService weixinMenuService;
	
	public int publishMenu() {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MENU_ADD);
		logger.info("发布菜单API接口："+url);
		//List<WeixinMenuDto> wmds = weixinMenuService.generateWeixinMenuDto();
		List<WeixinMenuDto> wmds = null;
		Map<String,List<WeixinMenuDto>> maps = new HashMap<String,List<WeixinMenuDto>>();
		maps.put("button", wmds);
		String json =  WeiXinJsonUtil.getInstance().obj2json(maps);
		logger.info("发布菜单："+json);
		//除去"sub_button": [],
		json=json.replaceAll("\"sub_button\":\\[\\],", "");
		logger.info("除去sub_button: [],发布菜单："+json);
		String rel = WeixinBasicKit.sendJsonPost(url,json);
		logger.info("菜单发布返回信息："+rel);
		int flag=1;//默认成功
		if(!WeixinBasicKit.checkRequestSucc(rel)) {
			flag=0;
			logger.info("发布菜单失败："+WeixinBasicKit.getRequestMsg(rel));
		}
		return flag;
	}

	public String queryMenu() {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MENU_QUERY);
		return WeixinBasicKit.sendGet(url);
	}

}

package com.yiran.weixin.service.impl;
/*package com.yiran.project.weixin.service;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thinkgem.jeesite.modules.weixin.entity.WeixinFinalValue;
import com.thinkgem.jeesite.modules.weixin.kit.WeixinBasicKit;
import com.thinkgem.jeesite.modules.weixin.utils.JsonUtil;


@Service("wqrService")
public class WqrService implements IWqrService {
	private static Logger logger = Logger.getLogger(WqrService.class);
	@Override
	public String loadTicketByBaseQr(int snum) {
		try {
			String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.QR_GET);
			String json = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+snum+"}}}";
			String rjson = WeixinBasicKit.sendJsonPost(url, json);
			logger.info("----------->>>>loadTicketByBaseQr rjson:"+rjson);
			if(WeixinBasicKit.checkRequestSucc(json)) {
				return JsonUtil.getMapper().readTree(rjson).get("ticket").asText();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String loadTicketByTempQr(int snum) {
		try {
			String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.QR_GET);
			String json = "{\"expire_seconds\": 180, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+snum+"}}}";
			String rjson = WeixinBasicKit.sendJsonPost(url, json);
			logger.info("----------->>>>loadTicketByTempQr rjson:"+rjson);
			if(WeixinBasicKit.checkRequestSucc(json)) {
				return JsonUtil.getMapper().readTree(rjson).get("ticket").asText();
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
*/
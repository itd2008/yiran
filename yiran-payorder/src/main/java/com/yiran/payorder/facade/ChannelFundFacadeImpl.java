package com.yiran.payorder.facade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yiran.common.utils.HttpEncoding;
import com.yiran.common.utils.http.WebHttpClient;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.exception.CommunicateException;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.utils.InstCovertUtil;

@Service
public class ChannelFundFacadeImpl implements ChannelFundFacade{
	private static final Logger            logger = LoggerFactory.getLogger(ChannelFundFacadeImpl.class);
	
	@Override
	public ChannelFundResult apply(ChannelRequest request) throws CommunicateException {
		 if (logger.isInfoEnabled()) {
	            logger.info("[ChannelPayOrder->Channel]渠道请求信息: instOrderNo=" + request.getInstOrderNo()
	                        + ", apiType=" + request.getApiType().getCode() + ", apiUri="
	                        + request.getApiUrl() + ", request=" + request);
	        }
		ChannelFundResult result = null;
		try {
			String jsonRequest = InstCovertUtil.convert(request);
			String url = request.getApiUrl();
			String responseJson = WebHttpClient.postRequest(jsonRequest,url,HttpEncoding.UTF8, null, null);
			logger.info("渠道响应结果:"+responseJson);
			Map<String,String> map = MapUtil.jsonToMap(responseJson);
			result = JSON.parseObject(map.get("data"), ChannelFundResult.class);
		} catch (Exception e) {
            logger.error(
                "[ChannelPayOrder<-Channel]渠道请求异常: instOrderNo=" + request.getInstOrderNo() + ", apiType="
                        + request.getApiType().getCode() + ", apiUri=" + request.getApiUrl(), e);
				throw new CommunicateException(e);
        }
		if (logger.isInfoEnabled()) {
            logger.info("[ChannelPayOrder<-Channel]渠道返回结果: instOrderNo=" + request.getInstOrderNo()
                        + ", apiType=" + request.getApiType().getCode() + ", result=" + result);
        }
		return result;
	}

}

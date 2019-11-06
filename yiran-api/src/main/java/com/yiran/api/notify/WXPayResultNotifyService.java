package com.yiran.api.notify;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.api.constants.LLPAYFundChannelKey;
import com.yiran.api.constants.ReturnCode;
import com.yiran.api.constants.WXPAYFundChannelKey;
import com.yiran.api.facade.ResultNotifyFacade;
import com.yiran.api.server.PropertyHelper;
import com.yiran.api.utils.ChannelHelper;
import com.yiran.api.utils.EBankVerifySignUtil;
import com.yiran.api.utils.LLPayUtil;
import com.yiran.api.vo.VerifySignRequestVo;
import com.yiran.pay.sdk.config.WxPayH5Config;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.service.impl.BestPayServiceImpl;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.ResponseType;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.request.ChannelRequest;
/**
 * 4.4 异步通知接口
 * @author xupan
 *
 */
@Service
public class WXPayResultNotifyService {
	 private Logger logger = LoggerFactory.getLogger(ResultNotifyFacade.class);
	 @Autowired
	 private PropertyHelper propertyHelper;
	 @Autowired
	 private BestPayServiceImpl bestPayService;
	 public ChannelFundResult notify(ChannelRequest channelRequest) {
	        String operInfo = getInfo(channelRequest);
	        try {
	            Properties properties = propertyHelper.getProperties(channelRequest.getFundChannelCode());
	            Map<String, String> respData = channelRequest.getExtension();
	            logger.info(operInfo + "【通知数据】:" + respData);
	            initBestPayService(properties);
	            PayFundResponse fundResponse = bestPayService.asyncNotify(respData.get("notifyMsg"));
	            ChannelFundResult channelResult = new ChannelFundResult(); 		
	            //支付异步通知
				if("SUCCESS".equals(fundResponse.getResultCode())) {
             		String apiResultCode = "{\"return_code\":\"SUCCESS\",\"return_msg\":\"OK\"}";
             		channelResult = buildVerifySignResult(
             				respData, 
             				fundResponse.getOrderId(), 
             				fundResponse.getOrderAmount().toString(),
             				"0000", 
             				fundResponse.getOutTradeNo(), 
             				null,
             				ResponseType.RETURN_SERVER,
             				apiResultCode ,
             				null,
             				FundChannelApiType.NOTIFY.getCode());
             	} else {
             		return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.EXCEPTION, "系统异常！");
             	}
				
        	//}
			logger.info("【异步通知结果】："+JSON.toJSONString(channelResult));
			return channelResult;
	         } catch (Exception e) {
	            e.printStackTrace();
	            String instRetMsg="异步通知处理出错：" + e.getMessage();
	            logger.error(operInfo + instRetMsg);
	            return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.EXCEPTION, instRetMsg);
	        }
	  
	        
	    }
	 /**
	     * 返回验签结果对象
	     * @param requestMap
	     * @param instOrderNo
	     * @param amount
	     * @param apiResultCode
	     * @param instReturnOrderNo
	     * @param userPayIP
	     * @param type
	     * @param responseData
	     * @param domain
	     * @param apiType
	     * @return
	     * @throws Exception
	     */
	    public static ChannelFundResult buildVerifySignResult(Map<String, String> requestMap,
	                                                          String instOrderNo, 
	                                                          String amount,
	                                                          String apiResultCode,
	                                                          String instReturnOrderNo, 
	                                                          String userPayIP,
	                                                          ResponseType type, 
	                                                          String responseData,
	                                                          String domain,
	                                                          String apiType) throws Exception {
	        Assert.notNull(amount, "金额不能为空");
	        Assert.notNull(instOrderNo, "订单号不能为空");
	        Assert.notNull(apiResultCode, "返回码不能为空");
	        ChannelFundResult fundResult = new ChannelFundResult();
	        fundResult.setInstOrderNo(instOrderNo);
	        fundResult.setInstReturnOrderNo(instReturnOrderNo == null ? StringUtils.EMPTY
	            : instReturnOrderNo);
	        fundResult.setApiResultCode(apiResultCode);
	        if("SP".equals(apiType)){
	        	fundResult.setApiResultMessage("支付成功");
	        	fundResult.setApiType(FundChannelApiType.SINGLE_PAY);
	        }else if("SR".equals(apiType)){
	        	fundResult.setApiResultMessage("退款成功");
	        	fundResult.setApiType(FundChannelApiType.SINGLE_REFUND);
	        }else if("NT".equals(apiType)){
	        	fundResult.setApiResultMessage("异步通知支付成功");
	        	fundResult.setApiType(FundChannelApiType.NOTIFY);
	        }
	        fundResult.setApiResultSubCode("SUCCESS");
	        fundResult.setProcessTime(new Date());
	        fundResult.setRealAmount(new Money(amount));

	        ChannelHelper helper = new ChannelHelper();
	        helper.setUserPayDomain(domain);
	        helper.setUserPayIP(userPayIP);
	        helper.setInstReturnData((java.util.Map<String, String>) requestMap);
	        helper.setResponseType(type == null ? ResponseType.FORWARD : type);
	        if (responseData != null)
	            helper.setResponseToInstData(responseData);
	        fundResult.setExtension(MapUtil.mapToJson(helper.build()));
	        fundResult.setSuccess(true);
	        return fundResult;
	    }
	    
	    
	   
	    /**
	     * 返回接口操作信息
	     * @param request
	     * @return
	     */
	    protected String getInfo(ChannelRequest request){
	    	StringBuffer sb = new StringBuffer();
	    	sb.append("FundChannelApi=").append(request.getFundChannelCode())
	    	.append("-").append(request.getApiType().getCode())
	    	.append(",InstOrderNo=").append(request.getInstOrderNo());
	    	return sb.toString();
		}
	    
	    private void initBestPayService(Properties properties) {
			WxPayH5Config wxPayH5Config = new WxPayH5Config();
			String appId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_APPID);
			logger.info("【微信支付配置】->【微信appID】："+appId);
			wxPayH5Config.setAppId(appId);
			String appSecret = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_APPSECRET);
			logger.info("【微信支付配置】->【微信秘钥appSecret】："+appSecret);
			wxPayH5Config.setAppSecret(appSecret);
			String mchId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHID);
			logger.info("【微信支付配置】->【微信商户ID】："+mchId);
			wxPayH5Config.setMchId(mchId);
			String mchSecretKey = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEY);
			logger.info("【微信支付配置】->【微信商户秘钥】："+mchSecretKey);
			wxPayH5Config.setMchKey(mchSecretKey);
			String mchSecretKeyPath = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEYPATH);
			logger.info("【微信支付配置】->【微信商户证书路径】："+mchSecretKeyPath);
			wxPayH5Config.setKeyPath(mchSecretKeyPath);
			String notifyUrl = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_NOTIFYURL);
			logger.info("【微信支付配置】->【异步通知URL】："+notifyUrl);
			wxPayH5Config.setNotifyUrl(notifyUrl);
			bestPayService.setWxPayH5Config(wxPayH5Config);
		}
}

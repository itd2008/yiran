package com.yiran.api.notify;

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
import com.yiran.api.facade.ResultNotifyFacade;
import com.yiran.api.server.PropertyHelper;
import com.yiran.api.utils.ChannelHelper;
import com.yiran.api.utils.EBankVerifySignUtil;
import com.yiran.api.utils.LLPayUtil;
import com.yiran.api.vo.VerifySignRequestVo;
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
public class LLNewAuthPayResultNotifyService {
	 private Logger logger = LoggerFactory.getLogger(ResultNotifyFacade.class);
	 @Autowired
	 private PropertyHelper propertyHelper;
	 
	 public ChannelFundResult notify(ChannelRequest channelRequest) {
	        String operInfo = getInfo(channelRequest);
	        logger.info(operInfo + "【异步通知请求】：" + channelRequest);
	        try {
	            Properties properties = propertyHelper.getProperties(channelRequest.getFundChannelCode());
	            Map<String, String> respData = channelRequest.getExtension();
	            logger.info(operInfo + "【通知数据】:" + respData);
	            //boolean a=LLPayUtil.checkSign(respData.get("notifyMsg"),properties.getProperty(LLPAYFundChannelKey.PUBLIC_KEY),null);
	            //if(a){
					VerifySignRequestVo verifySignRequestVo=JSON.parseObject(respData.get("notifyMsg"),VerifySignRequestVo.class);
					ChannelFundResult channelResult = new ChannelFundResult();
					if(StringUtil.isNotBlank(verifySignRequestVo.getResult_pay()) && StringUtil.isBlank(verifySignRequestVo.getSta_refund())){
	            		//支付异步通知
						if("SUCCESS".equals(verifySignRequestVo.getResult_pay())) {
		             		String apiResultCode = "{\"ret_code\":\"0000\",\"ret_msg\":\"交易成功\"}";
		             		
		             		channelResult = buildVerifySignResult(
		             				respData, 
		             				verifySignRequestVo.getNo_order(), 
		             				verifySignRequestVo.getMoney_order(),
		             				"0000", 
		             				verifySignRequestVo.getOid_paybill(), 
		             				null,
		             				ResponseType.RETURN_SERVER,
		             				apiResultCode ,
		             				null,
		             				FundChannelApiType.NOTIFY.getCode());
		             	} else {
		             		return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.EXCEPTION, "系统异常！");
		             	}
						
	            	//}
					if(StringUtil.isNotBlank(verifySignRequestVo.getSta_refund()) && StringUtil.isBlank(verifySignRequestVo.getResult_pay())){
	            		//退款异步通知
						if("2".equals(verifySignRequestVo.getSta_refund())) {
		             		String apiResultCode = "{\"ret_code\":\"0000\",\"ret_msg\":\"交易成功\"}";
		             		
		             		channelResult = buildVerifySignResult(
		             				respData,
		             				verifySignRequestVo.getNo_refund(),
		             				verifySignRequestVo.getMoney_refund(),
		             				"0000",
		             				verifySignRequestVo.getOid_refundno(),
		             				null, 
		             				ResponseType.RETURN_SERVER,
		             				apiResultCode , 
		             				null,
		             				FundChannelApiType.SINGLE_REFUND.getCode());
		             	}else if("3".equals(verifySignRequestVo.getSta_refund())) {
		             		return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.EXCEPTION, "退款失败！");
		             	} else {
		             		return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.EXCEPTION, "系统异常！");
		             	}
	            	}
					logger.info("【异步通知结果】："+JSON.toJSONString(channelResult));
					return channelResult;
	            }else{
	            	logger.info(operInfo + " 验签未通过" );
	            	return EBankVerifySignUtil.buildFaildVerifyResult(ReturnCode.FAILED, "验签未通过");
	            }
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
}

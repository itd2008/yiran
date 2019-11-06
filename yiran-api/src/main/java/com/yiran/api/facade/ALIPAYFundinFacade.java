package com.yiran.api.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSON;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.api.constants.ALIPAYFundChannelKey;
import com.yiran.api.constants.ReturnCode;
import com.yiran.api.mock.MockResultData;
import com.yiran.api.server.PropertyHelper;
import com.yiran.common.base.ResultWrapper;
import com.yiran.pay.sdk.config.AliDirectPayConfig;
import com.yiran.pay.sdk.config.AlipayConfig;
import com.yiran.pay.sdk.config.SignType;
import com.yiran.pay.sdk.enums.BestPayTypeEnum;
import com.yiran.pay.sdk.model.OrderQueryRequest;
import com.yiran.pay.sdk.model.OrderQueryResponse;
import com.yiran.pay.sdk.model.PayFundRequest;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.service.impl.BestPayServiceImpl;
import com.yiran.pay.sdk.utils.JsonUtil;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.QueryOrderResult;
import com.yiran.payorder.facade.IResultNotifyFacade;
import com.yiran.payorder.request.ChannelFundRequest;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.QueryRequest;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.InstitutionResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 支付宝支付
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/channelpay/alipay")
@Api(value="支付宝支付接口",description="支付宝支付接口")
public class ALIPAYFundinFacade extends BasePayFundinFacade{
	private Logger logger = LoggerFactory.getLogger(ALIPAYFundinFacade.class);
	@Autowired
	private PropertyHelper propertyHelper;
	@Autowired
    private BestPayServiceImpl bestPayService;
	
	@Autowired
    private InstitutionResultService   institutionResultService;
	@Autowired
	private IResultNotifyFacade resultNotifyFacade;
	@Autowired
   	private IPayInstOrderService payInstOrderService;
	
	
	@PostMapping("/pay")
	@ApiOperation(value = "支付宝发起支付")
	public ResultWrapper<Map<String,Object>>  fundin(@RequestBody String request) {
		logger.info("PayChannelOrder->Channel支付宝支付渠道请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		ChannelFundRequest req = JSON.parseObject(request, ChannelFundRequest.class);
		logger.info("PayChannelOrder->Channel支付宝支付渠道请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
      //判断mock开关是否打开，是否要返回mock数据
        String mock_switch = properties.getProperty(ALIPAYFundChannelKey.MOCK_SWITCH);
        if("true".equals(mock_switch)){//开关开启返回mock数据
        	result.setApiType(req.getApiType());
        	result.setRealAmount(req.getAmount());
 			result.setInstOrderNo(req.getInstOrderNo());
 			result.setProcessTime(new Date());
        	result = MockResultData.mockResule(result);
        	logger.info("注意这是mock数据！");
        	return ResultWrapper.ok().putData(result);
        }
        try {
        	initBestPayService(properties);
        	PayFundRequest payRequest = new PayFundRequest();
            payRequest.setOrderAmount(req.getAmount().getAmount().doubleValue());
            payRequest.setOrderId(req.getInstOrderNo());
            String orderName = req.getExtension().get("orderName");
            payRequest.setOrderName(orderName);
            payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_PC);
            payRequest.setExtension(req.getExtension());
            payRequest.setPayApiUrl(properties.getProperty(ALIPAYFundChannelKey.ALIPAY_TRADE_PAGE_PAY));
            logger.info("【支付宝支付】发起支付, request={}", JsonUtil.toJson(payRequest));

            PayFundResponse payResponse = bestPayService.pay(payRequest);
            logger.info("【支付宝支付】发起支付,响应结果, response={}", JsonUtil.toJson(payResponse));
            
            if("FAIL".equals(payResponse.getResultCode())){
            	result.setApiResultCode(payResponse.getErrCode());
                result.setApiResultMessage(payResponse.getErrCodeDes());
                result.setResultMessage(payResponse.getReturnMsg());
                result.setSuccess(false);
    			result.setRealAmount(req.getAmount());
    			result.setProcessTime(new Date());
    	    	result.setFundChannelCode(req.getFundChannelCode());
    	    	result.setApiType(FundChannelApiType.DEBIT);
    	    	result.setExtension(JSON.toJSONString(payResponse));
    	    	result.setInstOrderNo(req.getInstOrderNo());
    	    	logger.info("返回支付平台结果:"+JSON.toJSONString(result));
            	return ResultWrapper.ok().putData(result);
            }else{
            	result.setApiResultCode(payResponse.getReturnCode());
                result.setApiResultMessage(payResponse.getReturnMsg());
                result.setResultMessage(payResponse.getReturnMsg());
                result.setSuccess(true);
    			result.setRealAmount(req.getAmount());
    			result.setProcessTime(new Date());
    	    	result.setFundChannelCode(req.getFundChannelCode());
    	    	result.setApiType(FundChannelApiType.DEBIT);
    	    	result.setExtension(JSON.toJSONString(payResponse));
    	    	result.setInstOrderNo(req.getInstOrderNo());
    	    	result.setInstReturnOrderNo(payResponse.getOutTradeNo());
    	    	result.setFromHtml(payResponse.getHtmlText());
    	    	logger.info("返回支付平台结果:"+JSON.toJSONString(result));
            	return ResultWrapper.ok().putData(result);
            }
        	
        }catch (Exception e) {
        	logger.error("资金源[" + req.getFundChannelCode() + "]支付异常", e);
        	Map<String, String> map = new HashMap<String,String>();
            map.put("fundsChannel", req.getFundChannelCode());
            result.setExtension(JSON.toJSONString(map));
            result = builFalidFundinResponse(req, "支付异常", ReturnCode.FAILED, ReturnCode.FAILED,
                StringUtil.EMPTY_STRING);
            ResultWrapper.error().putData(result);
        }
		return null;
	}

	
	private void initBestPayService(Properties properties) {
		AlipayConfig payConfig = new AlipayConfig();
		String appId = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_APPID);
		payConfig.setAppId(appId);
		String appRSAPrivateKey = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_APPRSAPRIVATEKEY);
		payConfig.setAppRSAPrivateKey(appRSAPrivateKey);
		String alipayRSAPublicKey = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_ALIPAYRSAPUBLICKEY);
		payConfig.setAlipayRSAPublicKey(alipayRSAPublicKey);
		if(SignType.RSA2.name().equals(properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_SIGNTYPE))){
			payConfig.setSignType(SignType.RSA2);
		}else if(SignType.RSA.name().equals(properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_SIGNTYPE))){
			payConfig.setSignType(SignType.RSA);
		}
		String notifyUrl = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_NOTIFYURL);
		payConfig.setNotifyUrl(notifyUrl);
		
		payConfig.check();
		bestPayService.setAlipayConfig(payConfig);
		
		/*AliDirectPayConfig aliDirectPayConfig = new AliDirectPayConfig();
		String partnerId = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_PARTNERID);
		aliDirectPayConfig.setPartnerId(partnerId);
		
		String appRSAPrivateKey = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_APPRSAPRIVATEKEY);
		aliDirectPayConfig.setPartnerRSAPrivateKey(appRSAPrivateKey);
		String alipayRSAPublicKey = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_ALIPAYRSAPUBLICKEY);
		aliDirectPayConfig.setAlipayRSAPublicKey(alipayRSAPublicKey);
		if(SignType.RSA2.name().equals(properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_SIGNTYPE))){
			aliDirectPayConfig.setSignType(SignType.RSA2);
		}else if(SignType.RSA.name().equals(properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_SIGNTYPE))){
			aliDirectPayConfig.setSignType(SignType.RSA);
		}
		String notifyUrl = properties.getProperty(ALIPAYFundChannelKey.KEY_ALIPAY_NOTIFYURL);
		aliDirectPayConfig.setNotifyUrl(notifyUrl);
		
		aliDirectPayConfig.check();
		bestPayService.setAliDirectPayConfig(aliDirectPayConfig);*/
		
	}


	@PostMapping("/query")
	@ApiOperation(value = "支付宝支付结果查询")
	public ResultWrapper<Map<String,Object>>  query(@RequestBody String request) {
		
		logger.info("PayChannelOrder->Channel支付宝支付结果查询请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		QueryRequest req = JSON.parseObject(request, QueryRequest.class);
		result.setApiType(req.getApiType());
		logger.info("PayChannelOrder->Channel支付宝支付结果查询请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
        try {
        	String mock_switch = properties.getProperty(ALIPAYFundChannelKey.MOCK_SWITCH);
            if("true".equals(mock_switch)){//开关开启返回mock数据
            	result.setFundChannelCode(req.getFundChannelCode());
    			result.setInstOrderNo(req.getInstOrderNo());
    			result.setSuccess(true);
    			result.setApiType(req.getApiType());
    			result.setRealAmount(req.getAmount());
    			result.setInstOrderNo(req.getInstOrderNo());
    			result.setApiResultCode("0000");
    			result.setApiResultSubCode("SUCCESS");
    			result.setApiResultMessage("注意：当前为mock数据！：查询成功");
    			result.setResultMessage("注意：当前为mock数据！：交易成功");
    			result.setApiResultSubMessage("注意：当前为mock数据！：交易成功");
            	logger.info("注意这是mock数据！");
            	return ResultWrapper.ok().putData(result);
            }
        	initBestPayService(properties);
        	OrderQueryRequest queryRequest = new OrderQueryRequest();
        	queryRequest.setOrderId(req.getInstOrderNo());
        	queryRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
        	OrderQueryResponse queryResult = bestPayService.query(queryRequest);
        	logger.info("微信平台返回结果对象:"+JSON.toJSONString(queryResult));
        	if("FAIL".equals(queryResult.getResultCode())){
        		result.setFundChannelCode(req.getFundChannelCode());
        		result.setInstOrderNo(req.getInstOrderNo());
        		result.setApiResultCode(queryResult.getErrCode());
            	result.setRealAmount(req.getAmount());
            	result.setApiResultMessage(queryResult.getErrCodeDes());
    			result.setResultMessage(queryResult.getErrCodeDes());
            	result.setSuccess(false);
            	result.setExtension(JSON.toJSONString(queryResult));
            	logger.info("查询响应结果:"+JSON.toJSONString(result));
            	return ResultWrapper.ok().putData(result);
            }else{
            	if("SUCCESS".equals(queryResult.getResultCode()) && "SUCCESS".equals(queryResult.getTradeState())){
                	result.setFundChannelCode(req.getFundChannelCode());
                	result.setInstOrderNo(req.getInstOrderNo());
                	result.setApiResultCode(queryResult.getResultCode());
                	result.setRealAmount(req.getAmount());
                	result.setApiResultSubCode(queryResult.getTradeState());
                	result.setResultMessage(queryResult.getReturnMsg());
                	result.setApiResultMessage(queryResult.getReturnMsg());
                	result.setApiResultSubMessage(queryResult.getTradeStateDesc());
                	result.setSuccess(true);
                	result.setInstReturnOrderNo(queryResult.getTransactionId());
                	result.setExtension(JSON.toJSONString(queryResult));
                	logger.info("查询响应结果:"+JSON.toJSONString(result));
                	return ResultWrapper.ok().putData(result);
                }else{
                	result.setFundChannelCode(req.getFundChannelCode());
                	result.setInstOrderNo(req.getInstOrderNo());
                	result.setApiResultCode(queryResult.getResultCode());
                	result.setRealAmount(req.getAmount());
                	result.setApiResultSubCode(queryResult.getTradeState());
                	result.setResultMessage(queryResult.getReturnMsg());
                	result.setApiResultMessage(queryResult.getReturnMsg());
                	result.setApiResultSubMessage(queryResult.getTradeStateDesc());
                	result.setSuccess(true);
                	result.setInstReturnOrderNo(queryResult.getTransactionId());
                	result.setExtension(JSON.toJSONString(queryResult));
                	logger.info("查询响应结果:"+JSON.toJSONString(result));
                	return ResultWrapper.ok().putData(result);
                }
            }
        }catch (Exception ex) {
            logger.error("查询异常", ex);
            result = buildFaildChannelFundResult("签约支付异常", ReturnCode.FAILED, FundChannelApiType.SINGLE_QUERY);
           return ResultWrapper.error().putData(result);
        }
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
    
   

   
    
    
    /**
     * 构造查询错误的返回响应
     * 
     * @param apiResultMessage  api结果码说明
     * @param apiResultCode  api结果码
     * @param apiType   api类型
     * @return
     */
    public static ChannelFundResult buildFaildChannelFundResult(String apiResultMessage, String apiResultCode,
                                                                FundChannelApiType apiType) {
        ChannelFundResult response = new ChannelFundResult();
        response.setApiType(apiType);
        response.setApiResultCode(apiResultCode);
        response.setApiResultMessage(apiResultMessage);
        response.setProcessTime(new Date());
        response.setSuccess(false);
        return response;
    }
    
    @PostMapping("/notify/{fundChannelCode}")
	@ApiOperation(value = "异步通知")
	public Object  notify(@PathVariable("fundChannelCode") String fundChannelCode,@RequestBody String data) {
    	logger.info("通知数据："+data);
    	logger.info("fundChannelCode："+fundChannelCode);
    	ChannelRequest channelRequest = new ChannelRequest();
    	channelRequest.setFundChannelCode(fundChannelCode);
    	channelRequest.setApiType(FundChannelApiType.DEBIT);
    	channelRequest.getExtension().put("notifyMsg", data);
    	//ChannelFundResult result = wxPayResultNotifyService.notify(channelRequest);
    	ChannelFundResult result = null;
    	
    	PayInstOrderResult instOrderResult = null;
        if (result != null) {
        	
            instOrderResult = institutionResultService.process(payInstOrderService.loadByInstOrderNo(result.getInstOrderNo()),
            		result,false);
        } else {
            return new QueryOrderResult(InstOrderStatus.FAILURE,"渠道返回结果为空");
        }
        if (instOrderResult == null) {
            return new QueryOrderResult(InstOrderStatus.FAILURE,"查询异常");
        }
        //通知业务系统
        resultNotifyFacade.notifyBiz(instOrderResult.getInstOrderNo());
        //String return_result = setXml("SUCCESS", "OK");
        String return_result = null;
        return return_result;
    }
    
   
    
}

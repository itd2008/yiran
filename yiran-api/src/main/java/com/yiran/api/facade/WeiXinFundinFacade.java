package com.yiran.api.facade;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

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
import com.netfinworks.common.util.DateUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.api.constants.BankPropertyKeyBase;
import com.yiran.api.constants.LLPAYFundChannelKey;
import com.yiran.api.constants.LLPayConstant;
import com.yiran.api.constants.ReturnCode;
import com.yiran.api.constants.WXPAYFundChannelKey;
import com.yiran.api.filedown.WinXinFileDown;
import com.yiran.api.mock.MockResultData;
import com.yiran.api.notify.LLNewAuthPayResultNotifyService;
import com.yiran.api.notify.WXPayResultNotifyService;
import com.yiran.api.request.AuthFuninRequestVo;
import com.yiran.api.request.AuthenticateRequest;
import com.yiran.api.request.RiskItem;
import com.yiran.api.response.SendAuthCodeResponse;
import com.yiran.api.server.PropertyHelper;
import com.yiran.api.utils.CheckUtils;
import com.yiran.api.utils.SignTool;
import com.yiran.api.vo.PayQueryRequestVo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.base.ResultWrapper;
import com.yiran.common.utils.HttpEncoding;
import com.yiran.common.utils.http.WebHttpClient;
import com.yiran.message.domain.SendAuthCodeRequest;
import com.yiran.message.service.ISmsSendService;
import com.yiran.pay.sdk.config.WxPayH5Config;
import com.yiran.pay.sdk.enums.BestPayTypeEnum;
import com.yiran.pay.sdk.model.OrderQueryRequest;
import com.yiran.pay.sdk.model.OrderQueryResponse;
import com.yiran.pay.sdk.model.PayFundRequest;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.service.impl.BestPayServiceImpl;
import com.yiran.pay.sdk.utils.JsonUtil;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.PayResultNotify;
import com.yiran.payorder.domain.PayResultNotifyLog;
import com.yiran.payorder.domain.QueryOrderResult;
import com.yiran.payorder.enums.NotifyEventCode;
import com.yiran.payorder.enums.NotifyStatus;
import com.yiran.payorder.facade.IResultNotifyFacade;
import com.yiran.payorder.request.BankCardRequest;
import com.yiran.payorder.request.ChannelFundRequest;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.QueryRequest;
import com.yiran.payorder.response.BankCardResponse;
import com.yiran.payorder.service.IBankCardService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.InstitutionResultService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 微信支付
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/channelpay/wxpay")
@Api(value="微信支付接口",description="微信支付接口")
public class WeiXinFundinFacade extends BasePayFundinFacade{
	private Logger logger = LoggerFactory.getLogger(WeiXinFundinFacade.class);
	@Autowired
	private PropertyHelper propertyHelper;
	@Autowired
    private BestPayServiceImpl bestPayService;
	@Autowired
	private WXPayResultNotifyService wxPayResultNotifyService;
	@Autowired
    private InstitutionResultService   institutionResultService;
	@Autowired
	private IResultNotifyFacade resultNotifyFacade;
	@Autowired
   	private IPayInstOrderService payInstOrderService;
	@Autowired
	private WinXinFileDown winXinFileDown;
	
	@PostMapping("/pay")
	@ApiOperation(value = "微信发起支付")
	public ResultWrapper<Map<String,Object>>  fundin(@RequestBody String request) {
		logger.info("PayChannelOrder->Channel微信支付渠道请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		ChannelFundRequest req = JSON.parseObject(request, ChannelFundRequest.class);
		logger.info("PayChannelOrder->Channel微信支付渠道请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
      //判断mock开关是否打开，是否要返回mock数据
        String mock_switch = properties.getProperty(WXPAYFundChannelKey.MOCK_SWITCH);
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
        	String openId = req.getExtension().get("openId");
            payRequest.setOpenid(openId);
            payRequest.setOrderAmount(req.getAmount().getAmount().doubleValue());
            payRequest.setOrderId(req.getInstOrderNo());
            String orderName = req.getExtension().get("orderName");
            payRequest.setOrderName(orderName);
            payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
            //TODO: 需要修改目前是NATIVE支付方式
            //payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);
            logger.info("【微信支付】发起支付, request={}", JsonUtil.toJson(payRequest));

            PayFundResponse payResponse = bestPayService.pay(payRequest);
            logger.info("【微信支付】发起支付,响应结果, response={}", JsonUtil.toJson(payResponse));
            
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
	
	@PostMapping("/query")
	@ApiOperation(value = "微信支付结果查询")
	public ResultWrapper<Map<String,Object>>  query(@RequestBody String request) {
		
		logger.info("PayChannelOrder->Channel微信支付结果查询请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		QueryRequest req = JSON.parseObject(request, QueryRequest.class);
		result.setApiType(req.getApiType());
		logger.info("PayChannelOrder->Channel微信支付结果查询请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
        try {
        	String mock_switch = properties.getProperty(LLPAYFundChannelKey.MOCK_SWITCH);
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
	
	
	@PostMapping("/downloadBill")
	@ApiOperation(value = "微信账单下载")
	public ResultWrapper<Map<String,Object>>  downloadBill(@RequestBody String request) {
		logger.info("PayChannelOrder->Channel微信支付账单请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		ChannelFundRequest req = JSON.parseObject(request, ChannelFundRequest.class);
		logger.info("PayChannelOrder->Channel微信支付账单渠道请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
      //判断mock开关是否打开，是否要返回mock数据
        String mock_switch = properties.getProperty(LLPAYFundChannelKey.MOCK_SWITCH);
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
        	Map<String, String> extension = req.getExtension();
        	/***配置文件中/ ***/
        	String bill_dowload_url = properties.getProperty(WXPAYFundChannelKey.KEY_BILL_DOWLOAD_URL);
        	logger.info("【微信支付配置】->【对账单下载】："+bill_dowload_url);
        	String appId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_APPID);
     		logger.info("【微信支付配置】->【微信appID】："+appId);
     		String mchId = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHID);
     		logger.info("【微信支付配置】->【微信商户ID】："+mchId);
     		String appSecret = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEY);
     		logger.info("【微信支付配置】->【微信商户秘钥】："+appSecret);
     		String mchSecretKeyPath = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_MCHSECRETKEYPATH);
     		logger.info("【微信支付配置】->【微信商户证书路径】："+mchSecretKeyPath);
     		String billType = properties.getProperty(WXPAYFundChannelKey.KEY_WEIXIN_BILL_TYPE);
     		// 对账类型： ALL，返回当日所有订单信息，默认值 SUCCESS，返回当日成功支付的订单  REFUND，返回当日退款订单
     		logger.info("【微信支付配置】->【微信对账类型】："+billType);
     		String billDirPath = properties.getProperty(WXPAYFundChannelKey.KEY_BILL_DIR_PATH);
     		logger.info("【微信支付配置】->【对账文件路径】："+billDirPath);
     		
     		Map<String,String> map = new HashMap<String,String>();
     		map.put("bill_dowload_url", bill_dowload_url);
     		map.put("billDate", extension.get("billDate"));
     		map.put("billDirPath", billDirPath);
     		map.put("bill_type", billType);
     		map.put("appid", appId);
     		map.put("mch_id", mchId);
     		map.put("appSecret", appSecret);
     		
     		File file = winXinFileDown.fileDown(map);
     		result.setSuccess(true);
     		Map<String, String> extensionMap = new HashMap<String, String>();
     		String bill_file = file.getCanonicalPath();
     		extensionMap.put("bill_file", bill_file);
     		result.setExtension(JSON.toJSONString(extensionMap));
     		result.setFundChannelCode(req.getFundChannelCode());
        	result.setInstOrderNo(req.getInstOrderNo());
        	result.setApiResultCode("0000");
        	result.setRealAmount(req.getAmount());
        	result.setResultMessage("对账文件下载成功");
        	result.setApiResultMessage("对账文件下载成功");
        	result.setSuccess(true);
        	return ResultWrapper.ok().putData(result);
        }catch (Exception e) {
        	logger.error("资金源[" + req.getFundChannelCode() + "]账单下载异常", e);
        	Map<String, String> map = new HashMap<String,String>();
            map.put("fundsChannel", req.getFundChannelCode());
            result.setExtension(JSON.toJSONString(map));
            result = builFalidFundinResponse(req, "账单下载异常", ReturnCode.FAILED, ReturnCode.FAILED,
                StringUtil.EMPTY_STRING);
            ResultWrapper.error().putData(result);
        }
		return null;
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
    	ChannelFundResult result = wxPayResultNotifyService.notify(channelRequest);
    	
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
        String return_result = setXml("SUCCESS", "OK");
        return return_result;
    }
    
    /**
     * 通过xml 发给微信消息
     * @param returnCode
     * @param returnMsg
     * @return
     */
  	public static String setXml(String returnCode, String returnMsg) {
  		SortedMap<String, String> parameters = new TreeMap<String, String>();
  		parameters.put("return_code", returnCode);
  		parameters.put("return_msg", returnMsg);
  		return "<xml><return_code><![CDATA[" + returnCode + "]]>" + 
  				"</return_code><return_msg><![CDATA[" + returnMsg + "]]></return_msg></xml>";
  	}
    
}

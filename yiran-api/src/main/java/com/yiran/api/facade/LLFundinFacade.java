package com.yiran.api.facade;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
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
import com.netfinworks.common.util.money.Money;
import com.yiran.api.constants.BankPropertyKeyBase;
import com.yiran.api.constants.LLPAYFundChannelKey;
import com.yiran.api.constants.LLPayConstant;
import com.yiran.api.constants.ReturnCode;
import com.yiran.api.mock.MockResultData;
import com.yiran.api.notify.LLNewAuthPayResultNotifyService;
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
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.QueryOrderResult;
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
 * 连连支付
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/channelpay/llpay")
@Api(value="连连支付接口",description="连连支付接口")
public class LLFundinFacade extends BasePayFundinFacade{
	private Logger logger = LoggerFactory.getLogger(LLFundinFacade.class);
	private static final String OUR_SUCCESS_CODE="S0001";
	@Autowired
	private PropertyHelper propertyHelper;
	@Autowired
	private ISmsSendService smsSendService;
	@Autowired
	private IBankCardService bankCardService;
	@Autowired
	private LLNewAuthPayResultNotifyService llNewAuthPayResultNotifyService;
	@Autowired
   	private IPayInstOrderService payInstOrderService;
	@Autowired
    private InstitutionResultService   institutionResultService;
	@Autowired
	private IResultNotifyFacade resultNotifyFacade;
	@PostMapping("/authFundin")
	@ApiOperation(value = "银行卡签约支付")
	public ResultWrapper<Map<String,Object>>  fundin(@RequestBody String request) {
		logger.info("PayChannelOrder->Channel连连支付渠道请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		ChannelFundRequest req = JSON.parseObject(request, ChannelFundRequest.class);
		logger.info("PayChannelOrder->Channel连连支付渠道请求参数转换对象："+req);
		Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
		String operInfo = getInfo(req);
        String resJson = null;
        String reqJson =null;
        String url = null;
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
        	url=properties.getProperty(LLPAYFundChannelKey.AUTH_FUNDIN_URL);
            AuthFuninRequestVo authFundinRequestVo=new AuthFuninRequestVo();
            //根据银行卡号，签约渠道号，签约状态获取签约协议号，会员Id
            String cardNo = req.getExtension().get("cardNo");
            //acctName账户名
            String acctName = req.getExtension().get("accountName");
            //身份证idNo
            String idNo = req.getExtension().get("idNo");
            //根据银行卡号，签约渠道号，签约状态获取签约协议号，会员Id
            BankCardRequest bcq = new BankCardRequest();
            bcq.setBankCard(cardNo);
            bcq.setCardName(acctName);
            bcq.setIdNo(idNo);
            bcq.setFundChannelCode(req.getFundChannelCode());
            logger.info("Channel调CMF中获取签约信息接口参数"+JSON.toJSONString(bcq));
            
            String respone = bankCardService.findBankCardInfo(JSON.toJSONString(bcq));
            BankCardResponse  bcr = JSON.parseObject(respone, BankCardResponse.class);
            logger.info("Channel调CMF中获取签约信息接口返回结果对象："+respone);
            //根据银行卡号，商户号号判断是否签约
            String pay_signed = bcr.getReturnInfo().getReturnCode();
            logger.info("根据银行卡号，商户号号判断是否签约pay_signed："+pay_signed);
            //1. 如果signed 是Y ，并且该商户号在签约表已经签约true，那么直接扣款
            //2. 如果signed 是N,并且该商户号在签约表已经有签约数据true，那么自己包短信，签约推荐
            //3. 如果signed 是Y ，并该商户号在且签约表没有签约数据false，那么支付并签约，连连发短息
            //4. 如果signed 是N,并且该商户号在签约表已经有签约数据false，那么支付并签约，连连发短息
            
            String signed=req.getExtension().get("signed");
            logger.info("signed:["+signed+"]");
            if ("y".equalsIgnoreCase(signed) && "true".equalsIgnoreCase(pay_signed)){//如果signed 是Y ，并且该商户号在签约表已经签约true，那么直接扣款
                req.getExtension().put("signNo", bcr.getChannelSignNo());
            	req.getExtension().put("memberId", bcr.getMemberId());
            	//根据协议号直接代扣
            	 url=properties.getProperty(LLPAYFundChannelKey.AUTH_FUNDIN_URL);
            	 //准备请求参数
                authFindinVoFilling(authFundinRequestVo,req,properties);
                reqJson = JSON.toJSONString(authFundinRequestVo);
                logger.info(operInfo + "已经签约，签约支付请求：" + reqJson);
                //发送请求
                resJson = WebHttpClient.postRequest(reqJson,url,HttpEncoding.UTF8, null, null);
                logger.info(operInfo + "已经签约，签约支付响应：" + resJson);
                logger.info("========================================");
                Map<String,String> respMap=(Map<String, String>) JSON.parseObject(resJson, TreeMap.class);
                if (CheckUtils.hasError(respMap)){
                    String customError=CheckUtils.getCustomError(respMap);
                    if (customError!=null){
                        result.setApiResultMessage(customError);
                        result.setResultMessage(customError);
                        result.setApiResultCode(ReturnCode.FAILED);
                    }else{
                        result.setApiResultMessage(CheckUtils.getRetMsg(respMap));
                        result.setResultMessage(CheckUtils.getRetMsg(respMap));
                        result.setApiResultCode(CheckUtils.getRetCode(respMap));
                    }
                    logger.error("资金源[" + req.getFundChannelCode() + "]签约支付异常01");
                    result.setSuccess(false);
                    result.setRealAmount(req.getAmount());
                    Map<String, String> map = new HashMap<String,String>();
                    map.put("fundsChannel", req.getFundChannelCode());
                    result.setExtension(JSON.toJSONString(map));
                }else {
                    result.setApiResultCode(respMap.get("ret_code"));
                    result.setApiResultMessage(respMap.get("ret_msg"));
                    result.setResultMessage(respMap.get("ret_msg"));
                    result.setSuccess(true);
                    result.setApiResultSubCode(respMap.get("result_pay"));
                    authFindinResponseVoFilling(req,result,respMap,properties,false);
        			result.setRealAmount(req.getAmount());
                }
            }else if(("y".equalsIgnoreCase(signed) && "false".equalsIgnoreCase(pay_signed)) || 
            		("n".equalsIgnoreCase(signed) && "false".equalsIgnoreCase(pay_signed))){//未签约，支付并签约
            	//3. 如果signed 是Y ，并该商户号在且签约表没有签约数据false，那么支付并签约，连连发短息
                //4. 如果signed 是N,并且该商户号在签约表已经有签约数据false，那么支付并签约，连连发短息
                authFindinVoFilling(authFundinRequestVo,req,properties);
                reqJson = JSON.toJSONString(authFundinRequestVo);
                logger.info(operInfo + "未签约，支付并签约：" + reqJson);
                //发送请求
                resJson = WebHttpClient.postRequest(reqJson,url,HttpEncoding.GBK, null, null);
                logger.info(operInfo + "未签约，支付并签约：" + resJson);
                logger.info("========================================");
                Map<String,String> respMap=JSON.parseObject(resJson, TreeMap.class);
                if (CheckUtils.hasError(respMap)){
                    String customError=CheckUtils.getCustomError(respMap);
                    if (customError!=null){
                        result.setApiResultMessage(customError);
                        result.setResultMessage(customError);
                        result.setApiResultCode(ReturnCode.FAILED);
                    }else{
                        result.setApiResultMessage(CheckUtils.getRetMsg(respMap));
                        result.setResultMessage(CheckUtils.getRetMsg(respMap));
                        result.setApiResultCode(CheckUtils.getRetCode(respMap));
                    }
                    Map<String, String> map = new HashMap<String,String>();
                    map.put("fundsChannel", req.getFundChannelCode());
                    result.setExtension(JSON.toJSONString(map));
                    result.setSuccess(false);
                }else {
                    result.setApiResultCode(respMap.get("ret_code"));
                    result.setApiResultMessage(respMap.get("ret_msg"));
                    result.setResultMessage(respMap.get("ret_msg"));
                    result.setSuccess(true);
                    result.setApiResultSubCode(respMap.get("result_pay"));
                    authFindinResponseVoFilling(req,result,respMap,properties,true);
                    result.setRealAmount(req.getAmount());
                }
                result.setRealAmount(req.getAmount());
            }else if("n".equalsIgnoreCase(signed) && "true".equalsIgnoreCase(pay_signed)){//如果signed 是N,并且该商户号在签约表已经有签约数据true，那么自己包短信，签约推荐
            	AuthenticateRequest authenticateRequest =new AuthenticateRequest();
            	authenticateRequest.getExtension().put("memberId", bcr.getMemberId());
            	String mobileNo =req.getExtension().get("mobileNo");
            	//判断手机号和
            	if(!bcr.getMobileNo().equals(mobileNo)){//手机号四要素不匹配
            		result.setApiResultCode("1114");
                	result.setApiResultMessage("支付失败 ");
                    result.setResultCode("1114");
                    result.setResultMessage("支付失败");
                    Map<String, String> map = new HashMap<String,String>();
                    map.put("needadvance", "N");
                    map.put("fundsChannel", req.getFundChannelCode());
                	String JsonStr=JSON.toJSONString(map);
                	result.setExtension(JsonStr);
                    result.setSuccess(false);
                    return ResultWrapper.ok().putData(result);
            	}
            	authenticateRequest.setMobilePhoneNo(mobileNo);
            	//发送短信
                SendAuthCodeResponse response = sendAuthCode(authenticateRequest,properties);
                if(OUR_SUCCESS_CODE.equals(response.getResultCode())){
                	result.setApiResultCode(ReturnCode.SUCCESS);
                	result.setApiResultMessage("支付申请成功，待支付推进 ");
                    result.setResultCode(ReturnCode.SUCCESS);
                    result.setResultMessage("支付申请成功，待支付推进  ");
                    Map<String, String> map = new HashMap<String,String>();
                    map.put("needadvance", "Y");
                    map.put("fundsChannel", req.getFundChannelCode());
                	String JsonStr=JSON.toJSONString(map);
                	result.setExtension(JsonStr);
                    result.setSuccess(true);
                }
                logger.info("已签约:平台发送短信");
            }
            logger.info(operInfo + "渠道响应结果：" + JSON.toJSONString(result));
            return ResultWrapper.ok().putData(result);
        	
        }catch (Exception e) {
        	logger.error("资金源[" + req.getFundChannelCode() + "]签约支付异常", e);
        	Map<String, String> map = new HashMap<String,String>();
            map.put("fundsChannel", req.getFundChannelCode());
            result.setExtension(JSON.toJSONString(map));
            result = builFalidFundinResponse(req, "签约支付异常", ReturnCode.FAILED, ReturnCode.FAILED,
                StringUtil.EMPTY_STRING);
            ResultWrapper.error().putData(result);
        }
		return null;
	}
	
	@PostMapping("/query")
	@ApiOperation(value = "银行卡支付结果查询")
	public ResultWrapper<Map<String,Object>>  query(@RequestBody String request) {
		
		logger.info("PayChannelOrder->Channel连连支付结果查询请求参数："+request);
		ChannelFundResult result = new ChannelFundResult();
		QueryRequest req = JSON.parseObject(request, QueryRequest.class);
		logger.info("PayChannelOrder->Channel连连支付结果查询请求参数转换对象："+req);
		String operInfo = getInfo(req);
        try {
            Properties properties = propertyHelper.getProperties(req.getFundChannelCode());
            //封装请求参数
            PayQueryRequestVo payQueryRequestVo=buildPayQueryRequestVo(req, properties);
            logger.info(operInfo+"支付结果查询请求："+payQueryRequestVo.toString());
          //判断mock开关是否打开，是否要返回mock数据
            String mock_switch = properties.getProperty(LLPAYFundChannelKey.MOCK_SWITCH);
            if("true".equals(mock_switch)){//开关开启返回mock数据
    			result.setInstOrderNo(req.getInstOrderNo());
    			result.setSuccess(true);
    			result.setApiType(req.getApiType());
    			result.setRealAmount(req.getAmount());
    			result.setApiResultCode("0000");
    			result.setApiResultSubCode("SUCCESS");
    			result.setApiResultMessage("注意：当前为mock数据！：查询成功");
    			result.setResultMessage("注意：当前为mock数据！：交易成功");
    			result.setApiResultSubMessage("注意：当前为mock数据！：交易成功");
            	logger.info("注意这是mock数据！");
            	return ResultWrapper.ok().putData(result);
            }
            String postUrl=properties.getProperty(LLPAYFundChannelKey.PAY_ORDER_QUERY_URL);
            String reqJson=JSON.toJSONString(payQueryRequestVo);
            String resJson= WebHttpClient.postRequest(reqJson,postUrl,HttpEncoding.UTF8, null, null);
            logger.info("结果报文为："+resJson);
            Map<String,String> respData=JSON.parseObject(resJson,TreeMap.class);
            logger.info(operInfo+"响应数据："+respData);
            if (CheckUtils.hasError(respData)){
                String customError=CheckUtils.getCustomError(respData);
                if (customError!=null){
                    result.setApiResultMessage(customError);
                    result.setResultMessage(customError);
                    result.setApiResultCode(ReturnCode.FAILED);
                }else{
                    result.setApiResultMessage(CheckUtils.getRetMsg(respData));
                    result.setResultMessage(CheckUtils.getRetMsg(respData));
                    result.setApiResultCode(CheckUtils.getRetCode(respData));
                }
                result.setSuccess(false);
            }
            else{
            	result.setApiResultCode(respData.get("ret_code"));
            	result.setRealAmount(new Money(respData.get("money_order")));
            	result.setApiResultMessage(respData.get("ret_msg"));
            	result.setResultMessage(respData.get("ret_msg"));
                result.setApiResultSubCode(respData.get("result_pay"));
            	result.setSuccess(true);
            	result.setExtension(JSON.toJSONString(respData));
            }
            logger.info("查询响应结果:"+JSON.toJSONString(result));
            return ResultWrapper.ok().putData(result);
        }catch (Exception ex) {
            logger.error("查询异常", ex);
            result = buildFaildChannelFundResult("签约支付异常", ReturnCode.FAILED, FundChannelApiType.SINGLE_QUERY);
           return ResultWrapper.error().putData(result);
        }
	}
	
	private SendAuthCodeResponse sendAuthCode(AuthenticateRequest authenticateRequest,Properties properties) {
		SendAuthCodeRequest request = new SendAuthCodeRequest();
		request.setTemplateId(properties.getProperty(LLPAYFundChannelKey.SMS_TEMPLATE_ID));
		request.setPhone(authenticateRequest.getMobilePhoneNo());
		AjaxResult result = smsSendService.sendAuthCode(request);
		SendAuthCodeResponse sendAuthCodeResponse = new SendAuthCodeResponse();
		if("0000".equals(result.get("code"))){
			sendAuthCodeResponse.setResultCode(OUR_SUCCESS_CODE);
		}
		return sendAuthCodeResponse;
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
	 * 封装请求参数
	 * @param authFundinRequestVo
	 * @param req
	 * @param properties
	 */
    private void authFindinVoFilling(AuthFuninRequestVo authFundinRequestVo,ChannelFundRequest req,Properties properties){
    	Map<String, String> map = req.getExtension();
    	//获取主商户号 平台来源标示  TODO:签共享信息协议才能加这个属性，等通知2018-06-21
      	/*String platform = properties.getProperty(LLPAYFundChannelKey.MAIN_MERCHANT_NO);
      	authFundinRequestVo.setPlatform(platform);*/
        authFundinRequestVo.setUser_id(map.get("memberId"));
        authFundinRequestVo.setOid_partner(properties.getProperty(BankPropertyKeyBase.MERCHANT_NO));
        authFundinRequestVo.setSign_type(properties.getProperty(LLPAYFundChannelKey.SIGN_TYPE));
        authFundinRequestVo.setBusi_partner(LLPayConstant.VIRTUAL_BUSI_PARTNER);
        authFundinRequestVo.setApi_version(properties.getProperty(LLPAYFundChannelKey.PAY_API_VERSION));
        authFundinRequestVo.setNo_order(req.getInstOrderNo());
        Date date=req.getInstOrderSubmitTime();
        String dateStr=new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        authFundinRequestVo.setDt_order(dateStr);
        String goodsName=map.get("goodsName");
        if (StringUtil.isEmpty(goodsName)){
            authFundinRequestVo.setName_goods("汽车商品");
        }else {
            authFundinRequestVo.setName_goods(map.get("goodsName"));
        }
        authFundinRequestVo.setMoney_order(req.getAmount().getAmount().toString());
        authFundinRequestVo.setNotify_url(req.getCallbackServerUrl());
        //证件号码
        String certNo = map.get("idNo");
        //银行卡号
        String cardNo=map.get("cardNo");
        cardNo = cardNo.replaceAll(" ", "");
        //绑定手机号
        String mobileNo =map.get("mobileNo");
        authFundinRequestVo.setAcct_name(map.get("accountName"));
        String risk_item=properties.getProperty("risk_item");
        RiskItem riskItem=JSON.parseObject(risk_item,RiskItem.class);
        riskItem.setUser_info_mercht_userno(map.get("memberId"));
        riskItem.setUser_info_full_name(map.get("accountName"));
        riskItem.setUser_info_id_no(certNo);
        riskItem.setFrms_bank_name(map.get("accountName"));
        riskItem.setUser_info_bind_phone(mobileNo);
        authFundinRequestVo.setRisk_item(JSON.toJSONString(riskItem));
        authFundinRequestVo.setPay_type(LLPayConstant.NEW_PAY_TYPE);
        String no_agree=map.get("signNo");
        if(no_agree!=null&&!no_agree.equals("")){
        	authFundinRequestVo.setNo_agree(no_agree);
        }else{
        	authFundinRequestVo.setId_no(certNo);
        	authFundinRequestVo.setBind_mob(mobileNo);
        	authFundinRequestVo.setId_type(LLPayConstant.ID_TYPE);
        	authFundinRequestVo.setCard_no(cardNo);
        }
        authFundinRequestVo.setBank_code(req.getTargetInstCode());
        //获得私钥
  		/*String rsa_private = properties.getProperty(LLPAYFundChannelKey.RSA_PRIVATE_KEY);
  		String md5_key = properties.getProperty(LLPAYFundChannelKey.TRADER_MD5_KEY);
        String sign = LLPayUtil.addSign(JSON.parseObject(JSON.toJSONString(authFundinRequestVo)), rsa_private, md5_key);*/
        authFundinRequestVo.setSign(SignTool.genSign(JSON.parseObject(JSON
                .toJSONString(authFundinRequestVo)),properties));
        //authFundinRequestVo.setSign(sign);
        logger.info("~~~~~~银行卡签约支付请求参数："+JSON.toJSONString(authFundinRequestVo));
    }


    private void authFindinResponseVoFilling(ChannelFundRequest req,ChannelFundResult result,
			Map<String, String> respMap, Properties properties,boolean flag) {
    	result.setRealAmount(req.getAmount());
    	result.setProcessTime(new Date());
    	result.setFundChannelCode(req.getFundChannelCode());
    	result.setApiType(FundChannelApiType.DEBIT);
    	result.setInstReturnOrderNo(respMap.get("oid_paybill"));
    	Map<String,String> map=new HashMap<String, String>();
    	map.put("ret_code", respMap.get("ret_code"));
    	map.put("ret_msg", respMap.get("ret_msg"));
    	map.put("signNo", respMap.get("no_agree"));
    	map.put("token", respMap.get("token"));
    	map.put("sign_type", respMap.get("sign_type"));
    	map.put("sign", respMap.get("sign"));
    	map.put("oid_partner", respMap.get("oid_partner"));
    	map.put("no_order", respMap.get("no_order"));
    	map.put("dt_order", respMap.get("dt_order"));
    	map.put("money_order", respMap.get("money_order"));
    	map.put("oid_paybill", respMap.get("oid_paybill"));
    	map.put("sms_flag", respMap.get("sms_flag"));
    	map.put("result_pay", respMap.get("result_pay"));
    	map.put("info_order", respMap.get("info_order"));
    	map.put("no_agree", respMap.get("no_agree"));
    	map.put("partnerId", properties.getProperty(LLPAYFundChannelKey.MAIN_MERCHANT_NO));
    	map.put("fundsChannel", req.getFundChannelCode());
    	if(flag){
    		map.put("needadvance", "Y");
    	}else{
    		map.put("needadvance", "N");
    	}
    	
    	String JsonStr=JSON.toJSONString(map);
    	result.setExtension(JsonStr);
	}
    
    /**
     * 封装请求参数
     * @param request
     * @param properties
     * @return
     */
    private PayQueryRequestVo buildPayQueryRequestVo(QueryRequest request,Properties properties){
    	PayQueryRequestVo payQueryRequestVo=new PayQueryRequestVo();
    	String merId=properties.getProperty(BankPropertyKeyBase.MERCHANT_NO);
    	payQueryRequestVo.setOid_partner(merId);
    	payQueryRequestVo.setSign_type(properties.getProperty(LLPAYFundChannelKey.SIGN_TYPE));
    	payQueryRequestVo.setNo_order(request.getInstOrderNo());
    	//获得私钥
  		/*String rsa_private = properties.getProperty(LLPAYFundChannelKey.RSA_PRIVATE_KEY);
  		String md5_key = properties.getProperty(LLPAYFundChannelKey.TRADER_MD5_KEY);
        String sign = LLPayUtil.addSign(JSON.parseObject(JSON.toJSONString(payQueryRequestVo)), rsa_private, md5_key);*/
        payQueryRequestVo.setSign(SignTool.genSign(JSON.parseObject(JSON
                .toJSONString(payQueryRequestVo)),properties));
    	return payQueryRequestVo;
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
    	
    	ChannelFundResult result = llNewAuthPayResultNotifyService.notify(channelRequest);
    	//跃迁
    	
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
        Map<String, String> map = MapUtil.jsonToMap(result.getExtension());
        //通知业务系统
        resultNotifyFacade.notifyBiz(instOrderResult.getInstOrderNo());
		return map.get("responseToInstData");
    }
    
    
}

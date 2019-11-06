package com.yiran.web.controller.payorder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiran.payorder.request.CreatOrderForm;
import com.yiran.payorder.request.PayOrderRequest;
import com.yiran.payorder.response.PayFundResult;
import com.yiran.payorder.service.IFundRequestService;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.service.IWeixinSettingService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.utils.MapUtil;
import com.alibaba.fastjson.JSON;
import com.netfinworks.common.domain.Extension;
import com.yiran.common.base.AjaxResult;

/**
 * 支付测试

 */
@Controller
@RequestMapping("/payorder/paytest")
public class PayTestController extends BaseController
{
	private Logger logger = LoggerFactory.getLogger(PayTestController.class);
    private String prefix = "payorder/paytest";
    @Autowired
	private IFundRequestService fundRequestService;
    @Autowired
	private IWeixinSettingService weixinSettingService;
	/**
	 * 新增签约
	 */
	@GetMapping("/test")
	public String add()
	{
	    return prefix + "/testPay";
	}
	
	@PostMapping("/creatOrder")
	@ResponseBody
	public AjaxResult creatWeiXinOrder(@RequestBody String data)
	{		
		logger.info("创建订单请求参数:{}",data);
		CreatOrderForm from = JSON.parseObject(data, CreatOrderForm.class);
		logger.info("转换CreatOrderForm对象:{}",JSON.toJSONString(from));
		       
        PayOrderRequest request = new PayOrderRequest();
    	double amount = Double.valueOf(from.getOrderPrice());
		BigDecimal money = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN);
		request.setAmount(money);
		request.setBizTime(new Date());
		request.setBizType(BizType.FUNDIN);
		request.setCurrencyCode("CNY");
		request.setInstCode("WXPAY");
		//会员ID 目前写死（会员系统还未完成）
		request.setMemberId("100001605065");
		//支付方式
		request.setPayMode(PayMode.TRUSTCOLLECT);
		//支付编码
		request.setPaymentCode("1001");
		//支付订单号
		request.setPaymentSeqNo(from.getOrderNo());
		//产品编码
		request.setProductCode("20040001");
		//清结算ID 目前没有清结算系统暂不写
		//request.setSettlementId("2019082020617002");
		Extension ext = new Extension();
		ext.add("orderName", "测试扫码支付");
		ext.add("idType", "IC");
		ext.add("verifyFlag", "Y");
		ext.add("signed", "N");
		ext.add("COMPANY_OR_PERSONAL", "C");
		ext.add("DBCR", "DC");
		ext.add("ACCESS_CHANNEL", "WEB");
		ext.add("smsSender", "PLATFORM");
		ext.add("payeeId", "innerMember");
		//TODO:商户号可配置，目前写死
		ext.add("partnerId", "20000126505");
		ext.add("clientId", "payment");
		ext.add("PAGE_URL", weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_NOTIFYURL).getWeixinValue());
		request.setExtension(ext);
		
		PayFundResult result = fundRequestService.apply(request);
		logger.info("调用支付渠道返回结果："+JSON.toJSONString(result));
		PayFundResponse payResponse = converterPayResponse(result.getExtension());
		logger.info("发起微信支付返回结果："+JSON.toJSONString(payResponse));
		
		return AjaxResult.success(payResponse);
	}
	
	@PostMapping("/creatAliPayOrder")
	@ResponseBody
	public AjaxResult creatAliPayOrder(@RequestBody String data)
	{
		logger.info("创建支付宝订单请求参数:{}",data);
		CreatOrderForm from = JSON.parseObject(data, CreatOrderForm.class);
		logger.info("转换CreatOrderForm对象:{}",JSON.toJSONString(from));
		
		PayOrderRequest request = new PayOrderRequest();
    	double amount = Double.valueOf(from.getOrderPrice());
		BigDecimal money = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN);
		request.setAmount(money);
		request.setBizTime(new Date());
		request.setBizType(BizType.FUNDIN);
		request.setCurrencyCode("CNY");
		request.setInstCode("ALIPAY");
		//会员ID 目前写死（会员系统还未完成）
		request.setMemberId("100001605065");
		//支付方式
		request.setPayMode(PayMode.TRUSTCOLLECT);
		//支付编码
		request.setPaymentCode("1001");
		//支付订单号
		request.setPaymentSeqNo(from.getOrderNo());
		//产品编码
		request.setProductCode("20040001");
		//清结算ID 目前没有清结算系统暂不写
		//request.setSettlementId("2019082020617002");
		Extension ext = new Extension();
		ext.add("orderName", "PC场景下单并支付");
		//ext.add("qrPayMode", "2");
		ext.add("idType", "IC");
		ext.add("verifyFlag", "Y");
		ext.add("signed", "N");
		ext.add("COMPANY_OR_PERSONAL", "C");
		ext.add("DBCR", "DC");
		ext.add("ACCESS_CHANNEL", "WEB");
		ext.add("smsSender", "PLATFORM");
		ext.add("payeeId", "innerMember");
		//TODO:商户号可配置，目前写死
		ext.add("partnerId", "20000126506");
		ext.add("clientId", "payment");
		ext.add("PAGE_URL", "http://192.168.79.128:8087/api/yiran/channelpay/notify/resultNotify");
		request.setExtension(ext);
		
		PayFundResult result = fundRequestService.apply(request);
		logger.info("调用支付渠道返回结果："+JSON.toJSONString(result));
		PayFundResponse resp = new PayFundResponse();
		String formHtml = result.getFormHtml();
		logger.info("支付宝返回from表单："+formHtml);
		resp.setHtmlText(formHtml);
		return AjaxResult.success(resp);
	}
	private PayFundResponse converterPayResponse(Extension ext) {
		PayFundResponse resp = new PayFundResponse();
		resp.setAppId(ext.getValue("appId"));
		resp.setNonceStr(ext.getValue("nonceStr"));
		resp.setPackAge(ext.getValue("packAge"));
		resp.setPaySign(ext.getValue("paySign"));
		resp.setReturnCode(ext.getValue("returnCode"));
		resp.setReturnMsg(ext.getValue("returnMsg"));
		resp.setSignType(ext.getValue("signType"));
		resp.setTimeStamp(ext.getValue("timeStamp"));
		resp.setCodeUrl(ext.getValue("codeUrl"));
		return resp;
	}
	
}

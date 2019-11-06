package com.yiran.pay.sdk.service.impl;

import org.apache.commons.lang3.StringUtils;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.yiran.pay.sdk.config.AlipayConfig;
import com.yiran.pay.sdk.config.SignType;
import com.yiran.pay.sdk.model.OrderQueryRequest;
import com.yiran.pay.sdk.model.OrderQueryResponse;
import com.yiran.pay.sdk.model.OrderRefundQueryRequest;
import com.yiran.pay.sdk.model.OrderRefundQueryResponse;
import com.yiran.pay.sdk.model.PayFundRequest;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.pay.sdk.model.RePayRequest;
import com.yiran.pay.sdk.model.RePayResponse;
import com.yiran.pay.sdk.service.BestPayService;
import com.yiran.pay.sdk.utils.JsonUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

/**
 * 支付宝PC端支付(即时到账)
 * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7386797.0.0.0NDYyr&treeId=62&articleId=103566&docType=1
 * Created by null on 2017/2/14.
 */
class AlipayPCServiceImpl extends AbstractComponent implements BestPayService {

	private AlipayConfig alipayConfig;
	//数据格式
	private static final String FORMAT = "json";
	//字符编码
	private static final String CHARSET = "utf-8";
	
    public AlipayPCServiceImpl(AlipayConfig alipayConfig) {
        Objects.requireNonNull(alipayConfig, "aliDirectPayConfig is null.");
        this.alipayConfig = alipayConfig;
    }

    @Override
    public PayFundResponse pay(PayFundRequest request) {
    	//扩展信息
    	Map<String, String> map = request.getExtension();
    	
        this.logger.info("【支付宝PC端支付】request={}", JsonUtil.toJson(request));
        String serverUrl = request.getPayApiUrl();
        String appId = this.alipayConfig.getAppId();
        String privateKey = this.alipayConfig.getAppRSAPrivateKey();
        String alipayPublicKey = this.alipayConfig.getAlipayRSAPublicKey();
        String signType = this.alipayConfig.getSignType().name();
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,appId,privateKey,FORMAT,CHARSET,alipayPublicKey,signType);
        AlipayTradePagePayRequest req = new AlipayTradePagePayRequest();
        req.setNotifyUrl(alipayConfig.getNotifyUrl());
        
        //请求参数的集合
        Map<String,String> paramMap = new TreeMap<>();
        //商户订单号,64个字符以内、可包含字母、数字、下划线；需保证在商户端不重复
        paramMap.put("out_trade_no", request.getOrderId());
        //销售产品码，与支付宝签约的产品码名称。 注：目前仅支持FAST_INSTANT_TRADE_PAY
        paramMap.put("product_code", "FAST_INSTANT_TRADE_PAY");
        //订单总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]。
        paramMap.put("total_amount", String.valueOf(request.getOrderAmount()));
        //订单标题
        paramMap.put("subject", request.getOrderName());
        //订单描述
        if(!StringUtils.isBlank(map.get("body"))){
        	paramMap.put("body", map.get("body"));
        }
        //绝对超时时间，格式为yyyy-MM-dd HH:mm:ss
        if(!StringUtils.isBlank(map.get("timeExpire"))){
        	paramMap.put("time_expire", map.get("timeExpire"));
        }
        //订单包含的商品列表信息，json格式，其它说明详见商品明细说明
        if(!StringUtils.isBlank(map.get("goodsDetail"))){
        	paramMap.put("goods_detail", map.get("goodsDetail"));
        }
        //公用回传参数，如果请求时传递了该参数，则返回给商户时会回传该参数。支付宝只会在同步返回（包括跳转回商户网站）和异步通知时将该参数原样返回。本参数必须进行UrlEncode之后才可以发送给支付宝。
        if(!StringUtils.isBlank(map.get("passbackParams"))){
        	paramMap.put("passback_params", map.get("passbackParams"));
        }
        //业务扩展参数
        if(!StringUtils.isBlank(map.get("extendParams"))){
        	paramMap.put("extend_params", map.get("extendParams"));
        }
        //商品主类型 :0-虚拟类商品,1-实物类商品 注：虚拟类商品不支持使用花呗渠道
        if(!StringUtils.isBlank(map.get("goodsType"))){
        	paramMap.put("goods_type", map.get("goodsType"));
        }
        //该笔订单允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。 该参数数值不接受小数点， 如 1.5h，可转换为 90m
        if(!StringUtils.isBlank(map.get("timeoutExpress"))){
        	paramMap.put("timeout_express", map.get("timeoutExpress"));
        }
        //优惠参数 注：仅与支付宝协商后可用 {"storeIdType":"1"}
        if(!StringUtils.isBlank(map.get("promoParams"))){
        	paramMap.put("promo_params", map.get("promoParams"));
        }
        //royalty_info描述分账信息，json格式，详见分账参数说明
        if(!StringUtils.isBlank(map.get("royaltyInfo"))){
        	paramMap.put("royalty_info", map.get("royaltyInfo"));
        }
        //间连受理商户信息体，当前只对特殊银行机构特定场景下使用此字段
        if(!StringUtils.isBlank(map.get("subMerchant"))){
        	paramMap.put("sub_merchant", map.get("subMerchant"));
        }
        //商户原始订单号，最大长度限制32位
        if(!StringUtils.isBlank(map.get("merchantOrderNo"))){
        	paramMap.put("merchant_order_no", map.get("merchantOrderNo"));
        }
        //可用渠道,用户只能在指定渠道范围内支付，多个渠道以逗号分割 注，与disable_pay_channels互斥 渠道列表：https://docs.open.alipay.com/common/wifww7
        if(!StringUtils.isBlank(map.get("enablePayChannels"))){
        	paramMap.put("enable_pay_channels", map.get("enablePayChannels"));
        }
        //商户门店编号
        if(!StringUtils.isBlank(map.get("storeId"))){
        	paramMap.put("store_id", map.get("storeId"));
        }
        //禁用渠道,用户不可用指定渠道支付，多个渠道以逗号分割 注，与enable_pay_channels互斥 渠道列表：https://docs.open.alipay.com/common/wifww7
        if(!StringUtils.isBlank(map.get("disablePayChannels"))){
        	paramMap.put("disable_pay_channels", map.get("disablePayChannels"));
        }
        //PC扫码支付的方式，支持前置模式和 
		//跳转模式。 
		//前置模式是将二维码前置到商户 
		//的订单确认页的模式。需要商户在 
		//自己的页面中以 iframe 方式请求 
		//支付宝页面。具体分为以下几种： 
		//0：订单码-简约前置模式，对应 iframe 宽度不能小于600px，高度不能小于300px； 
		//1：订单码-前置模式，对应iframe 宽度不能小于 300px，高度不能小于600px； 
		//3：订单码-迷你前置模式，对应 iframe 宽度不能小于 75px，高度不能小于75px； 
		//4：订单码-可定义宽度的嵌入式二维码，商户可根据需要设定二维码的大小。 
		//
		//跳转模式下，用户的扫码界面是由支付宝生成的，不在商户的域名下。 
		//2：订单码-跳转模式
        if(!StringUtils.isBlank(map.get("qrPayMode"))){
        	paramMap.put("qr_pay_mode", map.get("qrPayMode"));
        }
        //商户自定义二维码宽度 注：qr_pay_mode=4时该参数生效
        if(!StringUtils.isBlank(map.get("qrcodeWidth"))){
        	paramMap.put("qrcode_width", map.get("qrcodeWidth"));
        }
        //描述结算信息，json格式，详见结算参数说明
        if(!StringUtils.isBlank(map.get("settleInfo"))){
        	paramMap.put("settle_info", map.get("settleInfo"));
        }
        //invoice_info 开票信息
        if(!StringUtils.isBlank(map.get("invoiceInfo"))){
        	paramMap.put("invoice_info", map.get("invoiceInfo"));
        }
        //签约参数，支付后签约场景使用
        if(!StringUtils.isBlank(map.get("agreementSignParams"))){
        	paramMap.put("agreement_sign_params", map.get("agreementSignParams"));
        }
        //请求后页面的集成方式。 取值范围： 1. ALIAPP：支付宝钱包内 2. PCWEB：PC端访问  默认值为PCWEB。
        if(!StringUtils.isBlank(map.get("integrationType"))){
        	paramMap.put("integration_type", map.get("integrationType"));
        }
        //请求来源地址。如果使用ALIAPP的集成方式，用户中途取消支付会返回该地址。
        if(!StringUtils.isBlank(map.get("requestFromUrl"))){
        	paramMap.put("request_from_url", map.get("requestFromUrl"));
        }
        //商户传入业务信息，具体值要和支付宝约定，应用于安全，营销等参数直传场景，格式为json格式  {"data":"123"}
        if(!StringUtils.isBlank(map.get("businessParams"))){
        	paramMap.put("business_params", map.get("businessParams"));
        }
        //外部指定买家
        if(!StringUtils.isBlank(map.get("extUserInfo"))){
        	paramMap.put("ext_user_info", map.get("extUserInfo"));
        }
        
        String bizContent = JsonUtil.toJson(paramMap);
        req.setBizContent(bizContent);
        this.logger.info("【支付宝PC端支付】业务请求参数={}", bizContent);
        PayFundResponse resp = null;
		try {
			AlipayTradePagePayResponse response = alipayClient.pageExecute(req);
			String body = response.getBody();
			this.logger.info("【支付宝PC端支付】阿里返回结果={}", body);
			this.logger.info("【支付宝PC端支付】阿里返回结果={}", JsonUtil.toJson(response));
			resp = new PayFundResponse();
			resp.setCode("0000");
			resp.setMsg("支付表单创建完毕");
			resp.setSubCode("SUCCESS");
			resp.setSubMsg("支付表单创建完毕");
			//Map<String,String> map1 = new HashMap<String,String>();
			//map1.put("body", body);
			//resp.setExtension(map1);
			resp.setHtmlText(body);
		} catch (AlipayApiException e) {
			 throw new IllegalArgumentException("支付宝PC端支付失败", e);
		}
		
		 this.logger.info("【支付宝PC端支付】封装结果={}", JsonUtil.toJson(resp));
        return resp;
    }



	@Override
	public PayFundResponse syncNotify(HttpServletRequest request) {
		//TODO 待完善
		return null;
	}

	@Override
	public PayFundResponse asyncNotify(String notifyData) {
		//TODO 待完善
		return null;
	}

	@Override
	public RePayResponse refund(RePayRequest request) {
		//TODO 待完善
		return null;
	}

	@Override
	public OrderQueryResponse query(OrderQueryRequest request) {
		//TODO 待完善
		return null;
	}

	@Override
	public OrderRefundQueryResponse refundQuery(OrderRefundQueryRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean verify(Map<String, String> toBeVerifiedParamMap, SignType signType, String sign) {
		// TODO Auto-generated method stub
		return false;
	}

}

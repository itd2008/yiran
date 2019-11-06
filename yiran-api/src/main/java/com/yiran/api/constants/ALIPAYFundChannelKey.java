package com.yiran.api.constants;


public class ALIPAYFundChannelKey extends BankPropertyKeyBase {
	
	/**
	 * 应用id, 支付宝新版App支付, Wap支付的统一ID.
	 */
	public static String KEY_ALIPAY_APPID="ALIPAY_APPID";
	
	public static String KEY_ALIPAY_PARTNERID="MERCHANT_ID";
	
	/**
     * 支付完成后的异步通知地址.
     */
	public static String  KEY_ALIPAY_NOTIFYURL="ALIPAY_NOTIFYURL";

    /**
     * 支付完成后的同步返回地址.
     */
	public static String KEY_ALIPAY_RETURNURL="ALIPAY_RETURNURL";
	/**
	 * 商户自己的私钥
	 */
	public static String KEY_ALIPAY_APPRSAPRIVATEKEY="ALIPAY_APPRSAPRIVATEKEY";

	/**
	 * 支付宝公钥
	 */
	public static String KEY_ALIPAY_ALIPAYRSAPUBLICKEY="ALIPAY_ALIPAYRSAPUBLICKEY";
    
    /**
     * 签名方式: RSA, RSA2两个值可选, 必须大写.
     */
	public static String KEY_ALIPAY_SIGNTYPE="ALIPAY_SIGNTYPE";
	
	public static String ALIPAY_TRADE_PAGE_PAY ="ALIPAY_TRADE_PAGE_PAY";
	
    
}

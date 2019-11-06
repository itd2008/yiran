package com.yiran.api.constants;
/**
 * 常量
 * @author xu pan
 *
 */
public class LLPayConstant {

	//支付产品标识 
	public static final String FLAG_PAY_PRODUCT = "8";// 8：分期付
	//应用渠道
	public static final String FLAG_CHNL_ANDROID = "0";//0：SDK-android
	public static final String FLAG_CHNL_IOS = "1";//1：SDK-ios
	public static final String FLAG_CHNL_WAP = "3";//3：WAP
	public static final String FLAG_CHNL_WEB = "2";//3：WAP
	
	//证件类型
	public static final String ID_TYPE = "0";//0 ：身份证
	//认证方式  D：认证支付（借记卡）
	public static final String PAY_TYPE ="D";
	//新认证
	public static final String NEW_PAY_TYPE="P";
	//偏移量
	public static final String OFFSET = "0";
	//商户业务类型  虚拟商品销售：101001   实物商品销售：109001
	public static final String VIRTUAL_BUSI_PARTNER="101001";
	public static final String PHYSICAL_BUSI_PARTNER="109001";
	//短信验证码
	public static final String VERIFY_CODE="verificationCode";
	//签约状态
	public static final String AUTH_STATUS = "S";
	
	//签约mock开关
	public static final String SIGNING_MOCK = "SIGNING_MOCK";	
	
}

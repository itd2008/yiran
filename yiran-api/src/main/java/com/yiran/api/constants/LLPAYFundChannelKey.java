package com.yiran.api.constants;


public class LLPAYFundChannelKey extends BankPropertyKeyBase {
	//版本
    public static final String AUTH_API_VERSION = "auth_api_version";
    public static final String PAY_API_VERSION = "pay_api_version";
    //签名方式
    public static final String SIGN_TYPE = "sign_type";
    //商户号
    public static final String MERCHANT_NO ="MERCHANT_NO";
    //风险控制参数
    public static final String RISK_ITEM ="risk_item";
    //银行卡签约申请接口
    public static final String SIGN_CREATE_BILL_ADDRESS ="sign_create_bill_address";
    //银行卡签约验证
    public static final String BIND_BANDCARDVERFY_URL ="BIND_BANDCARDVERFY_URL";
    //银行卡解约
    public static final String CANCEL_AUTH_URL = "CANCEL_AUTH_URL";
    //短信重发
    public static final String AUTH_SAND_SMS_URL="AUTH_SAND_SMS_URL";
    //商户支付结果查询服务
    public static final String PAY_ORDER_QUERY_URL = "PAY_ORDER_QUERY_URL";
    //签约支付验证
    public static final String AUTH_FUNDIN_REAL_URL = "AUTH_FUNDIN_REAL_URL";
  //获取签约渠道编号
    public static final String AUTH_FUNDCHANNEL_CODE ="AUTH_FUNDCHANNEL_CODE";
    
    //私钥
    public static final String RSA_PRIVATE_KEY = "PRIVATE_KEY";
    //MD5秘钥
    public static final String TRADER_MD5_KEY = "TRADER_MD5_KEY";
    
    //银行卡签约支付URL
    public static final String AUTH_FUNDIN_URL = "AUTH_FUNDIN_URL";
    //用户签约信息查询
    public static final String AUTH_QUERY_URL ="AUTH_QUERY_URL";
    //本地缓存路径
    public static final String TRANS_PATH ="TRANS_PATH";
    //sftp服务器地址
    public static final String SFTP_HOST ="SFTP_HOST";
    //sftp服务器端口
    public static final String SFTP_PORT ="SFTP_PORT";
    //sftp用户名
    public static final String SFTP_USERNAME ="SFTP_USERNAME";
    //sftp密码
    public static final String SFTP_PASSWORD = "SFTP_PASSWORD";
    //sftp下载目录
    public static final String DIRECTORY = "directory";
    //sftp下载文件名
    public static final String DOWNLOAD_FILE ="downloadFile";
    //主商户号
    public static final String MAIN_MERCHANT_NO ="MAIN_MERCHANT_NO";
    public static final String MAIN_MERCHANT_NO2 ="MAIN_MERCHANT_NO2";
    //永达异步通知服务地址
    public static final String NOTIFY_URL = "notify_url";
    //是否需要平台发短息(渠道参数配置Y需要 N不需要)
    public static final String IS_NEED_SEND_SMS = "IS_NEED_SEND_SMS";
    //退款申请URL
    public static final String REFUND_APPLICATION_URL = "REFUND_APPLICATION_URL";
    
    public static final String SMS_TEMPLATE_ID ="SMS_TEMPLATE_ID";
    
}

package com.yiran.paychannel.enums;

/**
 *
 * <p>扩展键值对中key的取值</p>
 *
 */
public enum ExtensionKey {
    //公共部分
	SOURCE_CODE("sourceCode"), //请求来源标识.
	TICKET("ticket"),//加密字符串
	//支付流水号，PE传入
	PAYMENT_SEQ_NO("paymentSeqNo"),
    MEMBER_ID("memberId"),
    COMPANY_OR_PERSONAL("companyOrPersonal"),
    ORGI_INST_SEQ_NO("orgiInstSeqNo"),//入款银行返回流水号
    PRODUCT_ORDER_NO("productOrderNo"),  //网关-产品订单号，出款、 入款（积分渠道）时都会用到。
    PT_ID("ptId"),//PT账户号,用于兼容使用
    PAY_CHANNEL("payChannel"), //支付渠道；(从新渠道编号)兼容老渠道编号时使用。30:手机综合网银;   28积分;   27预付费卡;    26:一点充;
                                //23:网银;    24:信用卡大额;    25:一点充;    22:网银;    99:锦书临时编码;    21:手机账单支付;
                                //20:贷记卡支付;    19:借记卡支付;    18:手机卡支付;    17:现金账户线下充值;    16:请朋友充值;
                                //15:银联快捷;    14:现金账户余额支付;    13:大额B2C;    12:B2B;    11:PT币支付;    10:全付通计费;
                                //9:全付通划帐;    7:银行卡绑定支付;    8:手机银行支付;    6:EZ－PT币支付;    4:银行卡支付;
                                //3:点卡Web方式;    2:点卡终端方式;    1:Esales支付;
    FC_CODE("fcCode"),//fund channel code
    CHANNEL_RETURN_CODE("channelReturnCode"), //渠道返回码
    CHANNEL_RETURN_MESSAGE("channelReturnMessage"), //渠道返回消息
    CHANNEL_RETURN_MESSAGE_DISPLAY("channelReturnMessageDisplay"), //渠道返回消息是否可以直接显示，0表示可以。
    PAYMENT_ORDER_NO("paymentOrderNo"),

    //出款部分
    BANK_CODE("bankCode"),
    BANK_NAME("bankName"),
    BANK_BRANCH("bankBranch"),
    BANK_PROVINCE("bankProvince"),
    BANK_CITY("bankCity"),
    AREA_CODE("areaCode"),
    ACCOUNT_TYPE("accountType"),
    ACCOUNT_NAME("accountName"),
    ACCOUNT_NO("accountNo"),
    AGREEMENT_NO("agreementNo"),
    EXPECT_TIME("expectTime"),
    PURPOSE("purpose"),
    PAYEE_ACCOUNT("payeeAccount"),
    PAYEE_NAME("payeeName"),
    PAYEE_ID("payeeId"),
    MEMO("memo"),//备注
    
    //充退部分
    ORGI_FUNDIN_ORDER_NO("orgiFundinOrderNo"), //原始PE订单号
    ORGI_SETTLEMENT_ID("orgiSettlementId"), //原始PE结算ID
    SETTLEMENT_ID("settlementId"), //结算ID
    
    GATE_ORDER_NO("gateOrderNo"), //交易服务订单号
    MAS_ORDER_NO("masOrderNo"), //交易服务订单号
    PRODUCT_ORDER_DATE("productOrderDate"), //网关-产品订单日期; 需要格式为yyyy-MM-dd HH:mm:ss

    FUNDIN_ORDER_NO("fundinOrderNo"),
    FUNDIN_REAL_AMOUNT("fundinRealAmount"),
    FUNDIN_DATE("fundinDate"),
    GMT_SUBMIT("gmtSubmit"),
    REFUND_REAL_AMOUNT("refundRealAmount"),
    REFUND_CHANNEL("refundChannel"), //实际充退渠道
    CHANNEL_REFUND_NO("channelRefundNo"), //渠道充退时的流水号，需要会给PE

    //入款部分 --- 公共
    IS_RISK_CONFIRM("isRiskConfirm"), //风险订单：确认是无风险入款; 取值：true
    INST_ORDER_NO("instOrderNo"), //由CMF产生的机构订单号，发给渠道，若是有风险，还会发给风控系统.
    INST_AMOUNT("instAmount"),//支付金额
    CARD_TYPE("cardType"),//卡类型
    DBCR("DBCR"),//借记,贷记
    ORDER_IP("payerIp"),  //付款方IP地址
    CONTRACT_NO("contractNo"), //协议号
    WEB_DOMAIN("webDomain"), //订单出口域名
    CARD_INFO("cardInfo"), //手机卡、预付费卡卡信息（加密串）
    
    //微信支付部分 
    OPEN_ID("openId"), //微信openid
    ORDER_NAME("orderName"), //支付订单名称
    //入款 --- 手机卡
    CARD_PRICE("cardPrice"), //卡单价
    CHANNEL_TYPE("channelType"), //手机卡类型
    //银联快捷
    BANKSEC("bankSec"), //安捷安全控件加密后的银行卡号密文
    BANKDYNAMICSEC("bankDynamicSec"), //安捷安全控件动态加密后的银行卡号密文
    MOBILENO("mobileNo"), //用户手机号
    IDCARD("idCard"), //用户身份证编号
    TRUENAME("trueName"), //用户真实姓名
    FPBANKCODE("fpBankCode"), //银联快捷支付银行
    netfinworks_MERCHANT_ID("netfinworksMerID"), //商户号
    netfinworks_MERCHANT_NAME("netfinworksMerName"), //商户名称

    //B2C
    PAGE_URL("pageUrl"), //B2C入款用
    PAGE_URL_FOR_SIGN("PAGE_URL"), //B2C入款用,不能用小写,给OPS用
    ACCESS_CHANNEL("accessChannel"), //B2C使用，手机B2C。
    PRODUCT_TYPE("productType"),//产品类型
    IS_NO_CARD("isNoCard"),//是否无卡支付

    CARD_AMOUNT("cardAmount"), //手机卡渠道使用
    FUNDS_CHANNEL("fundsChannel"), //CMF自己使用. PE传入的原始渠道编号
    RETURN_CHANNEL_RESULT_NO("returnChannelResultNo"),
    WEIBO_PAY_USER_PAY_DOMAIN("weiboPayUserPayDomain"), //EBank渠道返回给CMF的userDomain
    WEIBO_PAY_USER_PAY_IP("weiboPayUserPayIp"), //EBank渠道返回给CMF的userIP, real user IP
    BANK_EXT_INFO("bankExtInfo"), //EBank渠道返回给CMF的真实银行信息，冲退时需要返回给渠道.
    GOODS_CODE("goodsCode"), //商品编号
	GOODS_NAME("goodsName"), //商品名称
    PAY_MODE("payMode"),

    //产品码，PE传入
    PRODUCT_CODE("productCode"),

    BANK_LINE_NO("bankLineNo"),

    //渠道交易时间
    CHANNEL_TRANS_TIME("channelTransTime"),//毫秒数

    //POS使用
    POS_CODE("posCode"),
    POS_KEY("posKey"),
    WORK_KEY("workKey"),
    BATCH_NO("batchNo"),
    AMOUNT("amount"),
    INST_CODE("instCode"),
    OUT_TERMINAL_NO("terminalNo"),
    INST_TERMINAL_NO("instTerminalNo"),
    OUT_MERCHANT_ID("merchantId"),
    INST_MERCHANT_ID("instMerchantId"),
    NEED_OUT_CONVERT("needOutConvert"),
    INST_BATCH_NO("instBatchNo"),
    MERCHANT_ID("merchantId"),
    PRE_REQUEST_NO("preRequestNo"),
    IS_PRE_AUTH("isPreAuth"),


    /** 风控使用 */
    RMS_RISK_VERIFY_RESULT("rmsRiskVerifyResult"),
    riskMsg("riskMsg"),
    riskValue("riskValue"),

    
    IS_REAL_TIME("isRealTime"),
    
    /** 出款卡号 */
    CARD_NO("cardNo"),
    CARD_SP_ID("cardSpId"),
    CVV2("cvv2"),
    ID_CARD("idCard"),
    ID_TYPE("idType"),
    ID_NO("idNo"),
    VALID_DATE("validDate"),
    CER_KEY("cerKey"),
    CERT_TYPE("certType"),
    END_DATE("endDate"),
    START_DATE("startDate"),
    CERT_NO("certNo"),
    SIGN_NO("signNo"),
    SIGN_DATE("signDate"),
    NAME("name"),
    VERIFICATION_CODE("verificationCode"),
    SIGN_ID("signId"),
    SIGN_URL("signUrl"),
    TRANS_CODE("transCode"),
    CHANNEL_RETURN_CARD_TYPE("channelReturnCardType"),
    
    RESULT_MESSAGE("resultMessage"),
    
    BALANCE("balance"),
    CLIENT_ID("clientId"),
    IS_BANK_RISK("isBankRisk"),
    EBANK_CHARSET("ebankCharset"),
    UNITY_RESULT_CODE("unityResultCode"),
    UNITY_RESULT_MESSAGE("unityResultMessage"),
    WHITE_CHANNEL_CODE("whiteChannelCode"),
    SOURCE_ORDER("sourceOrder"),
    INST_SEQ_NO("instSeqNo"),
    TRADE_VOUCHER_NOS("tradeVoucherNos"),
    RETURN_CHANNEL_CODE("returnChannelCode"),
    BIND_FINISH("bindFinish"),
    BIZ_PARTY("bizParty"),//达分期2.0增加 业务主体 financing:融资租赁，loan：小贷
    YD_MERCHANT_NO("partnerId"),
    IS_CHINA_PAY("isChinaPay"),
    
    //支付宝参数PC支付
    BODY("body"),
    TIME_EXPIRE("timeExpire"),
    GOODS_DETAIL("goodsDetail"),
    PASSBACK_PARAMS("passbackParams"),
    EXTEND_PARAMS("extendParams"),
    GOODS_TYPE("goodsType"),
    TIMEOUT_EXPRESS("timeoutExpress"),
    PROMO_PARAMS("promoParams"),
    ROYALTY_INFO("royaltyInfo"),
    SUB_MERCHANT("subMerchant"),
    MERCHANT_ORDER_NO("merchantOrderNo"),
    ENABLE_PAY_CHANNELS("enablePayChannels"),
    STORE_ID("storeId"),
    DISABLE_PAY_CHANNELS("disablePayChannels"),
    QR_PAY_MODE("qrPayMode"),
    QRCODE_WIDTH("qrcodeWidth"),
    SETTLE_INFO("settleInfo"),
    INVOICE_INFO("invoiceInfo"),
    AGREEMENT_SIGN_PARAMS("agreementSignParams"),
    INTEGRATION_TYPE("integrationType"),
    REQUEST_FROM_URL("requestFromUrl"),
    BUSINESS_PARAMS("businessParams"),
    EXT_USER_INFO("extUserInfo")
    ; 


    ExtensionKey(String key){
        this.key = key;
    }

    public final String key;
    
}



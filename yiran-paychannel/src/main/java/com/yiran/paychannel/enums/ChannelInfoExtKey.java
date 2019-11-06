package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

public enum ChannelInfoExtKey {
    TARGET_INST("targetInst","目标机构"),
    CARD_TYPE("cardType","卡类型"), //借记卡、贷记卡、综合
    DBCR("dbcr", "借记贷记"),
    AMOUNT("amount","金额"),
    CURRENCY("currency","币种"),
    MAX_AMOUNT("maxAmount","单笔最大金额 限制"),
    MIN_AMOUNT("minAmount","单笔最小金额"),
    AMOUNT_LIMIT("amountLimit", "大笔金额限制"),//
    DISCOUNT("discount"," 折扣率"),
    SUPPORTED_ACCESS_CHANNELS("accessChannel","支持的访问方式"), //web，wap
    COMPANY_OR_PERSONAL("companyOrPersonal", "对公对私"),
    BIZ_TYPE("bizType","业务类型"),
    ONE_SITE_PAY("onesitepay","一站支付"),
    VALID_FROM("validFrom","一天中渠道业务可用的起始时间"), //转换规则为小时+分钟组成String后转换为Integer类型
    VALID_TO("validTo","一天中渠道业务结束时间"),//转换规则为小时+分钟组成String后转换为Integer类型
    TIME("time","当前系统时间"), //转换规则为小时+分钟组成String后转换为Integer类型
    CHANNEL_TYPE("channelType","手机卡类型"), //移动、联通、电信
    CARD_PRICE("cardPrice","面值"), //50、100
    ORDER_TYPE("orderType","业务类型"),
    FUND_SOURCE_ID("fundSourceId","资金源ID"),
    CHANNEL_INFO("channel","资金源信息"),
    API_INFO("api","资金源信息"),
    CHANNEL_INST("instCode","渠道机构"),
    PRODUCT_TYPE("productType","产品类型"),//产品类型
    PRODUCT_CODE("productCode","产品编码"),//产品编码
    PAY_MODE("payMode","支付方式"),
    NOT_NEED_VALIDATE_AMOUNT("notValidateAmount","不必校验银行金额参数"),//银行返回处理结果的金额，是否需要校验. 配置在API的扩展属性上. 取值为true
    CALCULATE_INPROCESS_ADMOUNT("calculateInProcessAmount","统计处理中订单金额"),//银企直联--统计处理中订单金额，是否需要校验.取值为true
    AUTO_REFUND_DATE_LIMIT("autoRefundDateLimit","自动充退天数限制"),//自动充退天数限制, autoRefundDateLimit=30表示30天内可以自动充退，否则优先使用手工充退.
    NEED_WAIT("needWait","是否需要等待后面任务发送"),//是否需要等待后面任务发送
    ORGI_FUNDIN_DATE("orgiFundinDate","入款订单时间"),
    AUTO_REFUND_AMOUNT_LIMIT("autoRefundAmountLimit", "退款最大金额限制"),//
    REFUND_HOUR_LIMIT("refundHourLimit", "退款发送时间限制"),// 按小时区分,如9-18
    API_TYPE("apiType","接口类型"),
    NEED_CHECK_BALANCE("needCheckBalance","是否需要校验余额"),
    CASH_ACCOUNT("cashAccount","现金账户"),
    CASH_LIMIT("cashLimit","限额"),
    EXPECT_TIME("expectTime","期望到账时间"),
    IS_SYNCHRONIZED("isSynchronized","是否实时到账"),
    IS_NEED_REVERSAL("isNeedReversal","是否需要冲正"),
    IS_NEED_RETRY("isNeedRetry","是否需要重试"),
    IS_INNER_BANK("isInnerBank","是否跨行"),
    NEED_RISK_CHECK("needRiskCheck","是否需要风控校验"),
    NEED_NOTIFY_INSTORDER_RESULT("needNotifyInstorderResult","是否需要风控校验"),
    NOT_NEED_FILTER_AMOUNT("notNeedFilterAmount","路由时不需要金额校验"), //针对出款拆分使用
    SIGN_NO("signNo","协议号"),
    NOT_SUPPORT_SPLIT("notSupportSplit","不支持拆分"),
    SUPPORT_PART_REFUND_QUERY("supportPartRefundQuery","支持部分退款查询"),
    SERVICETIME("serviceTime","服务时间"),
    TANSFER_DELAY_TIME("tansferDelayTime","自动充退分钟限制"),
    IS_ONE_BATCH_FUNDOUT("isOneBatchFundout","是否单批次出款"),
    SMS_SENDER("smsSender","短信发送方"),
    IS_CHINA_PAY("isChinaPay","是否银联支付")
    ;

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ChannelInfoExtKey(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static ChannelInfoExtKey getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (ChannelInfoExtKey type : ChannelInfoExtKey.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

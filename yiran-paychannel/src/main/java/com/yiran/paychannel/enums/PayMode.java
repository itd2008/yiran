package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

public enum PayMode {
    NETBANK("ONLINE_BANK", "网银支付"),

    DIRECTDEBT("DIRECTDEBT", "一点充支付"),

    BALANCE("BALANCE", "余额支付"),

    MPCARD("MOBILE_CARD", "手机充值卡支付"),

    MPEXPENSE("MOBILE_BALANCE", "手机话费支付"),

    TEL("PHONE_PAY", "固话支付"),

    PREPAIDCARD("PRE_CARD", "预付费卡支付"),

    ROYALPOINTS("ROYAL_POINT", "积分支付"),

    UNIONPAYEXPRESS("CP_FAST_PAY", "银联快捷支付"),

    OFFLINE("OFFLINE", "线下支付"),

    BROADBAND("BROADBAND_PAY", "宽带支付"),

    COUPON("COUPON", "红包"),

    PPC("PPC", "磁条卡支付"),

    POSC("POSC", "无磁无密支付"),

    POS("POS", "POS支付"),

    EPAY("EPAY", "无卡支付"),

    QUICKPAY("QPAY", "快捷支付"),

    BANKSIGNQUICKPAY("BSQPAY", "银签快捷支付"),

    CREDITPAY("CRPAY", "信用支付"),

    CASH("CASH", "现金支付"),

    CONFIRMEDFUND("CFMFUND", "确定性入款"),

    TRUSTCOLLECT("TRUST_COLLECT", "代扣"),
    
    FUNDSHAREPAY("FUNDSHARE_PAY", "基金支付"),
	
	YS_POS("YS_POS","银商POS支付")
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
    PayMode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取ENUM
     * @param code
     * @return
     */
    public static PayMode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (PayMode mode : values()) {
            if (StringUtils.equals(mode.getCode(), code)) {
                return mode;
            }
        }

        return null;
    }

    /**
     * 根据枚举名称获取ENUM
     * @param name
     * @return
     */
    public static PayMode getByName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }

        for (PayMode mode : values()) {
            if (StringUtils.equals(mode.name(), name)) {
                return mode;
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
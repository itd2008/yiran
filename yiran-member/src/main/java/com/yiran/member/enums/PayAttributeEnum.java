package com.yiran.member.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>支付属性</p>
 */
public enum PayAttributeEnum {
    QUICKPAY("qpay", "快捷支付"), 
    BANKSIGNQUICKPAY("bsqpay", "小快捷"), 
    TRUST_COLLECT("trust_collect", "代扣"),
    UMPPAY("umppay", "联动优势"), 
    NORMAL("normal","普通卡"),
    CERTPAY("certpay","打卡认证"),
    NAME_CARDNUM_CERT("name_cardnum_cert","两要素验证卡");
    
    private final String code;
    private final String message;

    private PayAttributeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PayAttributeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (PayAttributeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}

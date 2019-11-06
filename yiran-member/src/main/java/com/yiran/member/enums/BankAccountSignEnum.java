package com.yiran.member.enums;

import org.jsoup.helper.StringUtil;

/**
 * 
 * <p>银行卡是否签约</p>
 */
public enum BankAccountSignEnum {
    
    SIGN("Y", "已签约"), UN_SIGN("N", "未签约");
    
    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    BankAccountSignEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static BankAccountSignEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (BankAccountSignEnum at : BankAccountSignEnum.values()) {
            if (at.getCode().equals(code)) {
                return at;
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

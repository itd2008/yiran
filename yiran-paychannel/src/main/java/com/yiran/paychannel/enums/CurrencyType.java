package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * <p>货币类型</p>
 */
public enum CurrencyType {
    CNY("CNY", "人民币"),

    USD("USD", "美元");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    CurrencyType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static CurrencyType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (CurrencyType type : CurrencyType.values()) {
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

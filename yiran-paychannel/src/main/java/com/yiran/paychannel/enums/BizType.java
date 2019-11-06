package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * <p>订单类型</p>
 */
public enum BizType {
    FUNDIN("I", "入款"),

    REFUND("B", "退款"),

    FUNDOUT("O", "出款");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    BizType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static BizType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (BizType type : BizType.values()) {
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

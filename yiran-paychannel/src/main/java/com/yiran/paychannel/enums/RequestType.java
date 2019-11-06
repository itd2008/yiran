package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * <p>请求类型</p>
 */
public enum RequestType {
    FUND_IN("FUND_IN", "入款"),

    PRE_AUTH_DONE("PRE_AUTH_DONE", "预授权完成"),

    REFUND("REFUND", "退款"),

    FUND_OUT("FUND_OUT", "出款");

    /** 代码 */
    private final String code;

    /** 描述信息 */
    private final String message;

    RequestType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static RequestType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (RequestType type : RequestType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

}

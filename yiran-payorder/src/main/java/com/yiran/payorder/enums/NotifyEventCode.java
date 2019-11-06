package com.yiran.payorder.enums;

import org.apache.commons.lang.StringUtils;

public enum NotifyEventCode {
	PAY_SUCCESS("PAY_SUCCESS", "支付成功"),

	PAY_FAIL("PAY_FAIL", "支付失败"),

	PAY_AWAIT("PAY_AWAIT", "支付处理中");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    NotifyEventCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static NotifyEventCode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (NotifyEventCode type : NotifyEventCode.values()) {
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

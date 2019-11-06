package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 用于codedTxn交互接口的事务编码
 *
 */
public enum TxnCode {
	TEST("100", "test");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    TxnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static TxnCode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (TxnCode type : TxnCode.values()) {
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

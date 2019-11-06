package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>银行卡-状态</p>
 *
 */
public enum BankCardStatus {
    VALID("A", "有效"),

    INVALID("I", "无效");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    BankCardStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static BankCardStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (BankCardStatus type : BankCardStatus.values()) {
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

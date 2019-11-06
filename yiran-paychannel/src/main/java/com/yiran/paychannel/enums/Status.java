package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

public enum Status {
    VALID("VALID", "可用"),

    INVALID("INVALID", "不可用");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    Status(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static Status getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (Status type : Status.values()) {
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

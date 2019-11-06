package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-通讯类型</p>
 */
public enum InstOrderCommunicateType {
    SINGLE("S", "单笔通信"),

    BATCH("B", "批量通信");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    InstOrderCommunicateType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static InstOrderCommunicateType getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (InstOrderCommunicateType type : InstOrderCommunicateType.values()) {
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

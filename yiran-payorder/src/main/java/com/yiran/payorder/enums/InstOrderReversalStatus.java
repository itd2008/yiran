package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

public enum InstOrderReversalStatus {
	IN_PROCESS("I", "处理中"),

    FAILED_SEND("F", "发送请求失败"),

    SUCCESSFUL("S", "处理完成");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    InstOrderReversalStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static InstOrderReversalStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (InstOrderReversalStatus type : InstOrderReversalStatus.values()) {
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

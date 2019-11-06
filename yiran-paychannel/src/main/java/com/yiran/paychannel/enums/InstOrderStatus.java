package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

public enum InstOrderStatus {
	IN_PROCESS("I", "处理中"),

    SUCCESSFUL("S", "成功"),

    RISK("R", "成功但有风险"),

    FAILURE("F", "失败");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    InstOrderStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static InstOrderStatus getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (InstOrderStatus type : InstOrderStatus.values()) {
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

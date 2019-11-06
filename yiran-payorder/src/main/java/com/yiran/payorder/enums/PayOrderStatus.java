package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

public enum PayOrderStatus {
	AWAITING("A", "待处理"),

	CANCEL("C", "已撤销"),

    IN_PROCESS("I", "处理中"),

    SUCCESSFUL("S", "成功"),

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
    PayOrderStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static PayOrderStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (PayOrderStatus type : PayOrderStatus.values()) {
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

package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-风险订单状态</p>
 *
 */
public enum OrderRiskStatus {
    IN_PROCESS("I", "等待审核结果"),

    FAILED_SEND("F", "发送请求失败"),

    PASS("P", "审核通过"),

    REJECT("R", "审核拒绝");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    OrderRiskStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static OrderRiskStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (OrderRiskStatus type : OrderRiskStatus.values()) {
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

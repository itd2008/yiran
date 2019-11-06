package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>CMF订单-审核状态</p>
 */
public enum PayOrderConfirmStatus {
    NOT_NEED("N", "不复核"),

    AWAITING("A", "待审核"),

    PASS("P", "通过"),

    REJECT("R", "驳回"),
    
    SUBMIT("S","提交审核"),
    
    FAIL("F","提交审核失败");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    PayOrderConfirmStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static PayOrderConfirmStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (PayOrderConfirmStatus type : PayOrderConfirmStatus.values()) {
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

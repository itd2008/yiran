package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-入款同步counter流水状态</p>
 *
 */
public enum NotifyBankOrderStatus {
    WATING("I", "同步中"),

    FAIL("F","同步失败(须补单)"),

    SUCCESSFUL("S", "同步成功");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    NotifyBankOrderStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static NotifyBankOrderStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (NotifyBankOrderStatus type : NotifyBankOrderStatus.values()) {
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

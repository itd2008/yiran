package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>签约-状态</p>
 *
 */
public enum SignStatus {
    AWAITING("A", "待签约推进"),
    SUCCESS("S", "签约完成"),
    FAIL("F", "签约失败"),
    CANCEL("C", "取消签约"),
    TERMINAL("T", "解约"),
    EXCEPTION("E", "签约待确认");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    SignStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static SignStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (SignStatus type : SignStatus.values()) {
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

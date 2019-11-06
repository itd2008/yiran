package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>锁类型.</p>
 */
public enum LockType {
    SYNCHRON("S", "同步锁"),
    EXCLUSION("E", "互斥锁");

    /** 代码 */
    private final String  code;
    /** 信息 */
    private final String  message;

    private LockType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取ENUM
     * @param code
     * @return
     */
    public static LockType getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (LockType lockType : LockType.values()) {
            if (lockType.getCode().equals(code)) {
                return lockType;
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

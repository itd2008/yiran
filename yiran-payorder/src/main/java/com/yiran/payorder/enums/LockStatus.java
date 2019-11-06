/**
 *
 */
package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>注释</p>
 */
public enum LockStatus {
    EXCUTE("E", "执行"),
    FINISH("F", "完成");

    /** 代码 */
    private final String  code;
    /** 信息 */
    private final String  message;

    private LockStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取ENUM
     * @param code
     * @return
     */
    public static LockStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (LockStatus lockStatus : LockStatus.values()) {
            if (lockStatus.getCode().equals(code)) {
                return lockStatus;
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

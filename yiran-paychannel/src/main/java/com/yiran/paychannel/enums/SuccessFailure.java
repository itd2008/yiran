package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * <p>枚举:是-否</p>
 */
public enum SuccessFailure {
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
    SuccessFailure(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static SuccessFailure getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (SuccessFailure type : SuccessFailure.values()) {
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

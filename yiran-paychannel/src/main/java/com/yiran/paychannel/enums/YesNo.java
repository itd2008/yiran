package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * <p>枚举:是-否</p>
 */
public enum YesNo {
    YES("YES", "是"),

    NO("NO", "否");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    YesNo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static YesNo getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (YesNo type : YesNo.values()) {
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

package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 路由评分选择类型
 */
public enum SelectMode {
    PERCENT("P", "百分比选取"),
    HEIGHT("H", "最高分选取");
    
    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    SelectMode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static SelectMode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (SelectMode type : SelectMode.values()) {
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

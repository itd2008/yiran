package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>路由值比较类型</p>
 */
public enum MatchType {
    BETWEEN("BETWEEN", "区间"),
    GREETER("GREETER","大于"),
    LOWER("LOWER","小于"),
    IN("IN", "包含"),
    NOTNULL("NOTNULL","不为空"),
    NULL("NULL","空"),
    NOTIN("NOTIN","不包含")
    ;

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    MatchType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static MatchType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (MatchType type : MatchType.values()) {
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

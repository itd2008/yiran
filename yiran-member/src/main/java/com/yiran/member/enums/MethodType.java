package com.yiran.member.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>操作类型</p>
 * @author wangbin.ben
 * @version $Id: MethodType.java, v 0.1 2013-4-10 下午3:27:12 User Exp $
 */
public enum MethodType {
    UPDATE("U", "更新"),

    INSERT("I", "新增");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    MethodType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static MethodType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (MethodType type : MethodType.values()) {
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

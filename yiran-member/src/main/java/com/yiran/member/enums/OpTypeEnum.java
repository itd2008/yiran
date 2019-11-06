package com.yiran.member.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>操作类型</p>
 */
public enum OpTypeEnum {
    LOGIN("login", "登陆");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    OpTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static OpTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (OpTypeEnum type : OpTypeEnum.values()) {
            if (type.getCode().equals(code.toLowerCase())) {
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

package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 对公对私
 * @author pandaa
 *
 */
public enum CompanyOrPersonal {
    COMPANY("B", "对公"),

    PERSONAL("C", "对私");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    CompanyOrPersonal(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static CompanyOrPersonal getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (CompanyOrPersonal type : CompanyOrPersonal.values()) {
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

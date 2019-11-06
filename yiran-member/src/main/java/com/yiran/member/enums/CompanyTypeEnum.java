package com.yiran.member.enums;

/**
 * <p>企业类型枚举</p>
 */
public enum CompanyTypeEnum {
    DEFAULT(0, "企业"), 
    OTHER(1, "其他"),
    INSTITUTION(2, "事业单位"),
	PERSONAL_MERCHANT(3, "个体工商户"),
	PRIVATE_ORGANIZATION(4, "民办非企业");

    /** 代码 */
    private final Integer code;
    /** 信息 */
    private final String  message;

    CompanyTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static CompanyTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (CompanyTypeEnum ce : CompanyTypeEnum.values()) {
            if (ce.getCode().equals(code)) {
                return ce;
            }
        }

        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

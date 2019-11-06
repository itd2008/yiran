package com.yiran.member.enums;

/**
 * <p>会员账户属性枚举</p>
 */
public enum AccountAttributeEnum {
    PERSONAL_ACCOUNT(1L, "对私"),
    INSTUTION_ACCOUNT(2L, "对公"),
    INTENAL_ACCOUNT(9L, "内部");

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    AccountAttributeEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static AccountAttributeEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (AccountAttributeEnum aa : AccountAttributeEnum.values()) {
            if (aa.getCode().equals(code)) {
                return aa;
            }
        }

        return null;
    }
    
    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static AccountAttributeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (AccountAttributeEnum aa : AccountAttributeEnum.values()) {
            if (aa.getCode().toString().equals(code)) {
                return aa;
            }
        }

        return null;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

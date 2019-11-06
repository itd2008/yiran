package com.yiran.member.enums;

public enum OrganizationTypeEnum {
	DEFAULT(1, "默认");
    

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;

    OrganizationTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static OrganizationTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (OrganizationTypeEnum lnt : OrganizationTypeEnum.values()) {
            if (lnt.getCode().equals(code)) {
                return lnt;
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

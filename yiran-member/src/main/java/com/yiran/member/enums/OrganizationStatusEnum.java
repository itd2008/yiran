package com.yiran.member.enums;

public enum OrganizationStatusEnum {
	CANCEL(0, "注销"),
	NORMAL(1, "正常");
    

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;

    OrganizationStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static OrganizationStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (OrganizationStatusEnum lnt : OrganizationStatusEnum.values()) {
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

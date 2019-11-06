package com.yiran.member.enums;

public enum OrganizationNodeStatusEnum {
	UNAVAILABLE(0, "不可用"),
	AVAILABLE(1, "可用");
    

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;

    OrganizationNodeStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static OrganizationNodeStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (OrganizationNodeStatusEnum lnt : OrganizationNodeStatusEnum.values()) {
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

package com.yiran.member.enums;

/**
 * <p>联系人信息类型枚举</p>
 */
public enum MemberContactTypeEnum {
    SYSTEM(0L, "系统联系人"),
    BANK(1L, "账户联系人")
    ;

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    MemberContactTypeEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static MemberContactTypeEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (MemberContactTypeEnum ce : MemberContactTypeEnum.values()) {
            if (ce.getCode().equals(code)) {
                return ce;
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
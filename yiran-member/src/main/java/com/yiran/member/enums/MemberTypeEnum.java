package com.yiran.member.enums;

/**
 * <p>会员类型枚举</p>
 */
public enum MemberTypeEnum {
    PERSONAL(1L, "个人"), COMPANY(2L, "企业"),SPECIAL(3L, "特约商户"),VIRTUAL(9L, "虚拟商户");

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    MemberTypeEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static MemberTypeEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (MemberTypeEnum memberType : MemberTypeEnum.values()) {
            if (memberType.getCode().equals(code)) {
                return memberType;
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

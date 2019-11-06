package com.yiran.member.enums;

/**
 * <p>联系人信息排序字枚举</p>
 */
public enum MemberContactOrderByTypeEnum {
    DEFAULT(0, "默认"),
    PINYIN(1,"账户名拼音");

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;

    MemberContactOrderByTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static MemberContactOrderByTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (MemberContactOrderByTypeEnum lnt : MemberContactOrderByTypeEnum.values()) {
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

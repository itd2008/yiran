package com.yiran.member.enums;

/**
 * <p>联系人信息快速查询关键字枚举</p>
 */
public enum MemberContactFastQueryTypeEnum {
    DEFAULT(0, "默认"),
    NAME(1, "快捷通账户名"), 
    IDENTITY(2, "快捷通账号"),
    ACCOUNT_NAME(3,"银行账户开户名"),
    ACCOUNT_NO(4, "银行账户账号"),
    BANK_NAME(5,"开户银行名称"),
    PINYIN(6,"账户名拼音");

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;

    MemberContactFastQueryTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static MemberContactFastQueryTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (MemberContactFastQueryTypeEnum lnt : MemberContactFastQueryTypeEnum.values()) {
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

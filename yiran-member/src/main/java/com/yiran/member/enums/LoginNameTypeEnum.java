package com.yiran.member.enums;

/**
 * <p>登录名类型类型枚举</p>
 */
public enum LoginNameTypeEnum {
    EMAIL(1, "邮箱"), 
    CELL_PHONE(2, "手机号"),
    COMMON_CHAR(3,"普通文字账号");

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;

    LoginNameTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static LoginNameTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (LoginNameTypeEnum lnt : LoginNameTypeEnum.values()) {
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

package com.yiran.member.enums;

/**
 * <p>认证状态</p>
 */
public enum VerifyStatusEnum {
    INVALID(-1, "被删除"),
    UNAUTHENTICATED(0, "未认证未绑定"),
    AUTHENTICATED(1, "已认证已绑定");
    

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String    message;

    VerifyStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static VerifyStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (VerifyStatusEnum lnt : VerifyStatusEnum.values()) {
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

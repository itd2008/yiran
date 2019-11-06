package com.yiran.member.enums;

/**
 * <p>功能描述：密码锁标记枚举</p>
 */
public enum PassWordLockFlagEnum {

    //所属标志（0登录密码，1支付密码）
    LOGIN_PASSWORD_FLAG(0, "登录密码"),
    PAY_PASSWORD_FLAG(1, "支付密码"),
    PAY_MPASSWORD_FLAG(2, "app端支付密码") ;

    /** 代码 */
    private final int    code;
    /** 信息 */
    private final String message;

    private PassWordLockFlagEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static PassWordLockFlagEnum getByCode(int code) {
        for (PassWordLockFlagEnum item : PassWordLockFlagEnum.values()) {
            if (item.code == code) {
                return item;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}

package com.yiran.member.enums;
/**
 * 
 * 功能描述：会员标识类型枚举
 */
public enum  IdentityTypeEnum {
    
    PUID(0, "外部平台用户ID"),
    EMAIL(1,"邮箱"), 
    CELL_PHONE(2,"手机号"),
    COMMON_CHAR(3,"普通文字账号"), 
    MERCHANT_ID(5,"商户号"),
    MERCHANT_APP(6,"商户业务应用标识号");

    /** 代码 */
    private final int   code;
    /** 信息 */
    private final String message;

    private IdentityTypeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static IdentityTypeEnum getByCode(int code) {
        for (IdentityTypeEnum item : IdentityTypeEnum.values()) {
            if (item.code == code ) {
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

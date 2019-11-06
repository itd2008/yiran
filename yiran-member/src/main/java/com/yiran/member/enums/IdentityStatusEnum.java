package com.yiran.member.enums;

/**
 * 
 * 功能描述：会员标识状态
 */
public enum  IdentityStatusEnum {
    
    VALID(1, "有效"), INVALID(0,"无效"); 

    /** 代码 */
    private final int   code;
    /** 信息 */
    private final String message;

    private IdentityStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static IdentityStatusEnum getByCode(int code) {
        for (IdentityStatusEnum item : IdentityStatusEnum.values()) {
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

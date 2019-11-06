/**
 * 
 */
package com.yiran.member.enums;

/**
 * <p>批量查询会员账户标志</p>
 */
public enum BlukQueryFlagEnum {
    QUERY_ALL("0", "查询会员与账户状态"), QUERY_MEMBER("1", "查询会员状态"), QUERY_ACCOUNT("2", "查询账户状态");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    BlukQueryFlagEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static BlukQueryFlagEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (BlukQueryFlagEnum statusEnum : BlukQueryFlagEnum.values()) {
            if (statusEnum.getCode().equals(code)) {
                return statusEnum;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

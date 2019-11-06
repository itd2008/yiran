/**
 * 
 */
package com.yiran.member.enums;

/**
 * <p>批量查询项返回编码</p>
 */
public enum BulkReturnCodeEnum {

    SUCCESS("0", "成功"), 
    MEMBER_NOT_EXISTS("1", "会员不存在"), 
    ACCOUNT_NOT_EXISTS("2", "账户不存在"),
    MEMBER_ACCOUNT_NOT_EXISTS("3", "会员账户都不存在"), 
    MEMBER_ACCOUNT_NOT_MATCH("4","会员账户不匹配");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    BulkReturnCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static BulkReturnCodeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        for (BulkReturnCodeEnum statusEnum : BulkReturnCodeEnum.values()) {
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

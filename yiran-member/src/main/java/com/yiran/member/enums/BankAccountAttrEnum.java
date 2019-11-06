/**
 * 
 */
package com.yiran.member.enums;

/**
 * <p>银行账号属性</p>
 */
public enum BankAccountAttrEnum {
    PERSONAL(1, "对私"), COMPANY(0, "对公");
    
    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;

    BankAccountAttrEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static BankAccountAttrEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (BankAccountAttrEnum at : BankAccountAttrEnum.values()) {
            if (at.getCode().equals(code)) {
                return at;
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

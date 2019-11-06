/**
 * 
 */
package com.yiran.member.enums;

import org.jsoup.helper.StringUtil;

/**
 * <p>账户类别</p>
 */
public enum AccountCategoryEnum {
    DPM("DPM", "储值账户"), CREDIT("CREDIT", "信用账户");
    
    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    AccountCategoryEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static AccountCategoryEnum getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (AccountCategoryEnum at : AccountCategoryEnum.values()) {
            if (at.getCode().equals(code)) {
                return at;
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

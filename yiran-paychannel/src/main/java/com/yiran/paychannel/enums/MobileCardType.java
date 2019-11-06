
package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

public enum MobileCardType {


    /**
     * 中国移动
     * 
     */
    CM("CM","中国移动"),

    /**
     * 中国联通
     * 
     */
    UC("UC","中国联通"),

    /**
     * 中国电信
     * 
     */
    CT("CT","中国电信");

    public String value() {
        return name();
    }

    public static MobileCardType fromValue(String v) {
        return valueOf(v);
    }
    
    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    MobileCardType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static MobileCardType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (MobileCardType type : MobileCardType.values()) {
            if (type.getCode().equals(code)) {
                return type;
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

package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;
/**
 * 接入方式
 * @author pandaa
 *
 */
public enum AccessChannel {
    WEB("WEB", "WEB渠道"),

    WAP("WAP", "WAP渠道"),

    CLIENT("CLIENT", "客户端");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    AccessChannel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static AccessChannel getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (AccessChannel type : AccessChannel.values()) {
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

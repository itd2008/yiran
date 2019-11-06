package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

public enum Dbcr {
	DC("DC", "借记"),
	GC("GC", "综合"),
	CC("CC", "贷记");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    Dbcr(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static Dbcr getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (Dbcr type : Dbcr.values()) {
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

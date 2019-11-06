package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-流水同步状态</p>
 */
public enum InstOrderGlideStatus {
    NONEED("N", "不同步"),

    WATING("W", "待同步"),
    
    FAIL("F","提交同步失败"),

    SUCCESSFUL("S", "同步成功");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    InstOrderGlideStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static InstOrderGlideStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (InstOrderGlideStatus type : InstOrderGlideStatus.values()) {
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

package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-归档状态</p>
 */
public enum InstOrderArchiveStatus {
    AWAITING("A", "待归档，分批开始"),

    GENERATED("G", "文件生成||归档完成"),

    SUBMMITED("S", "已经提交"),

    RECEIVED("R", "已经返回"),

    FAILURE("F", "提交失败"),

    PAUSE("P", "暂停");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    InstOrderArchiveStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static InstOrderArchiveStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (InstOrderArchiveStatus type : InstOrderArchiveStatus.values()) {
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

package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * <p>机构订单-状态</p>
 */
public enum InstOrderResultStatus {
    AWAITING ("A", "待处理"), //由于批量文件处理时，结果也落地在‘机构订单结果表‘里，这些中间结果记录须待处理.

	SUCCESSFUL("S", "成功"),

    RISK("R", "成功但有风险"),

    FAILURE("F", "失败"),
    
    NONEXISTS("N","不存在"), //用于退款和出款查询失败时使用

    DELETED("D", "已废除"),

    QUESTIONABLE("Q","可疑");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    InstOrderResultStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static InstOrderResultStatus getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (InstOrderResultStatus type : InstOrderResultStatus.values()) {
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

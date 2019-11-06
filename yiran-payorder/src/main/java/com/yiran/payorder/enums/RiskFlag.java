package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * 
 * <p>风险标识</p>
 */
public enum RiskFlag {
    
    NON_RISK("nonRisk", "无风险"),

    BANK_CHECK("bankCheck", "银行结果校验有风险"),

    RMS_CHECK("rmsCheck", "风控校验有风险");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    RiskFlag(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static RiskFlag getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (RiskFlag type : RiskFlag.values()) {
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

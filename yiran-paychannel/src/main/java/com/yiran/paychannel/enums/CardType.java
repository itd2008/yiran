package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * <p>卡类型</p>
 */
public enum CardType {
    CREDIT_CARD("CC", "贷记卡"),

    DEBIT_CARD("DC", "借记卡"),
    
    PASSBOOK("PB", "存折");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    CardType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static CardType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (CardType type : CardType.values()) {
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

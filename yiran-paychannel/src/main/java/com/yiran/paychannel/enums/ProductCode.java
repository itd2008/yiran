package com.yiran.paychannel.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * 产品编码
 *
 */
public enum ProductCode {
	FUNDTRANSFER("10020004", "资金调拨"),
	CREDITCARDPAYMENT("30040001","信用卡还款");
    
    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ProductCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static ProductCode getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (ProductCode type : ProductCode.values()) {
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

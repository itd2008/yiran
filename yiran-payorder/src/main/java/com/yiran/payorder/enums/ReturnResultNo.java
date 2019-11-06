package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

/**
 * 返回PE/通知counter订单类型
 */
public enum ReturnResultNo {
	
	CHANNEL_RESULT("channel", "渠道订单号"),
	GATE_ORDER_NO("gate", "网关订单号"),
	INST_ORDER_NO("cmf", "cmf机构订单号"),
	ORGI_INST_ORDER_NO("orgiInstOrder","入款机构订单号") //退款回传入款订单号
	;
	/** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ReturnResultNo(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static ReturnResultNo getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (ReturnResultNo type : ReturnResultNo.values()) {
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

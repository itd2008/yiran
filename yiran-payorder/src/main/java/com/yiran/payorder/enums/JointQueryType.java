package com.yiran.payorder.enums;

import com.netfinworks.common.lang.StringUtil;

public enum JointQueryType {
	Q1001("1001", "机构订单ID"),
	Q1002("1002", "提交机构订单号"),
	Q1003("1003", "机构返回流水号"),
	Q1004("1004", "支付流水号"),
	Q1005("1005", "支付凭证号"),
	Q1006("1006", "交易原始凭证号"),
	Q1007("1007", "清算请求号"),
	Q1008("1008", "交易凭证号");

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    JointQueryType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取
     * @param code
     * @return
     */
    public static JointQueryType getByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }

        for (JointQueryType type : JointQueryType.values()) {
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

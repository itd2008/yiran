package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 产品类型枚举,用于判断是否支持大额
 */
public enum ProductType {
    DUMMY("D", "虚拟"),//不允许使用贷记卡大额

    PRACTICAL("P", "实物"),
    
    BAMBOOK("B", "BAMBOOK");// bambook产品特殊使用

    /** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    ProductType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static ProductType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (ProductType type : ProductType.values()) {
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

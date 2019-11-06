package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <p>值类型</p>
 */
public enum ValueType {
    STRING("String", java.lang.String.class),

    BOOLEAN("Boolean", java.lang.Boolean.class),
    
    
    LONG("Long", java.lang.Long.class),
    
    INTEGER("Integer", java.lang.Integer.class),
    
    BIGDECIMAL("BigDecimal", java.math.BigDecimal.class),
    ;

    /** 代码 */
    private final String code;
    /** 信息 */
    private final Class claz;

    /**
     * 构造
     * @param code
     * @param message
     */
    ValueType(String code, Class claz) {
        this.code = code;
        this.claz = claz;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static ValueType getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (ValueType type : ValueType.values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getCode() {
        return code;
    }

    public Class getClaz() {
        return claz;
    }
    
}

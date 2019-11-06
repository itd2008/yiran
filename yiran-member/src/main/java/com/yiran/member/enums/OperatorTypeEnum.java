package com.yiran.member.enums;

/**
 * <p>操作员类型枚举</p>
 */
public enum OperatorTypeEnum {
    PERSONAL(1L, "个人"), COMPANY(2L, "企业"), SPECIAL(3L, "特约商户"), PER_MERCHANT(4L, "个人商户"), VIRTUAL_MERCHANT(9L, "虚拟商户");

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    OperatorTypeEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static OperatorTypeEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (OperatorTypeEnum ot : OperatorTypeEnum.values()) {
            if (ot.getCode().equals(code)) {
                return ot;
            }
        }

        return null;
    }

    public Long getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

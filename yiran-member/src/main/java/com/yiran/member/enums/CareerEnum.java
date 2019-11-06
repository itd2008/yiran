package com.yiran.member.enums;

/**
 * <p>职业类型枚举</p>
 */
public enum CareerEnum {
    DEFAULT(1L, "默认职业");

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    CareerEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static CareerEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (CareerEnum ce : CareerEnum.values()) {
            if (ce.getCode().equals(code)) {
                return ce;
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

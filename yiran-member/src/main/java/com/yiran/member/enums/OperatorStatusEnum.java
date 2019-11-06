package com.yiran.member.enums;

/**
 * <p>操作员状态枚举</p>
 */
public enum OperatorStatusEnum {
    UNACTIVE(0L, "未激活"), NORMAL(1L, "正常"), CANCEL(2L, "注销");

    /** 代码 */
    private final Long   code;
    /** 信息 */
    private final String message;

    OperatorStatusEnum(Long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static OperatorStatusEnum getByCode(Long code) {
        if (code == null) {
            return null;
        }

        for (OperatorStatusEnum ose : OperatorStatusEnum.values()) {
            if (ose.getCode().equals(code)) {
                return ose;
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

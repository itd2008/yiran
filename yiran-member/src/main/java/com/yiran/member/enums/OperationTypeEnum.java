/**
 * 
 */
package com.yiran.member.enums;

/**
 * <p>操作类型</p>
 */
public enum OperationTypeEnum {
    ADD(1, "新增"), MODIFY(2, "修改");

    /** 代码 */
    private final Integer code;
    /** 信息 */
    private final String  message;

    OperationTypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static OperationTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (OperationTypeEnum ose : OperationTypeEnum.values()) {
            if (ose.getCode().equals(code)) {
                return ose;
            }
        }

        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

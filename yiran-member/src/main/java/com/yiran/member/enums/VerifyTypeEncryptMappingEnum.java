package com.yiran.member.enums;

/**
 * <p>认证类型加密映射枚举</p>
 */
public enum VerifyTypeEncryptMappingEnum {
    ID_CARD(1, "身份证", "idCard"),
    PASSPORT(3, "护照", "passPort"),
    HKMT_PASS(5,"港澳台通行证","hkmtPass"),
    CELL_PHONE(11, "手机号", "cellPhone"),
    FAREN_ID_CARD(14, "法人身份证", "idCard"),//用于企业法人身份认证
    WEITUO_ID_CARD(15, "委托人身份证", "idCard"),//用于企业委托人身份认证
    EMAIL(12, "邮箱", "email");
    

    /** 代码 */
    private final Integer   code;
    /** 信息 */
    private final String message;
    
    private final String fieldName;

    VerifyTypeEncryptMappingEnum(Integer code, String message, String fieldName) {
        this.code = code;
        this.message = message;
        this.fieldName = fieldName;
    }

    /**
     * 通过代码获取枚举项
     * @param code
     * @return
     */
    public static VerifyTypeEncryptMappingEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }

        for (VerifyTypeEncryptMappingEnum lnt : VerifyTypeEncryptMappingEnum.values()) {
            if (lnt.getCode().equals(code)) {
                return lnt;
            }
        }

        return null;
    }
    
    public static VerifyTypeEncryptMappingEnum getByFieldName(String fieldName) {
        if (fieldName == null) {
            return null;
        }

        for (VerifyTypeEncryptMappingEnum lnt : VerifyTypeEncryptMappingEnum.values()) {
            if (lnt.getFieldName().equals(fieldName)) {
                return lnt;
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

    public String getFieldName() {
        return fieldName;
    }
}

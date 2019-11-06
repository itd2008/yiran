package com.yiran.member.enums;

/**
 * <p>解密类型枚举</p>
 */
public enum DeciphedTypeEnum {
	 	ID_CARD(1, "身份证", "idCard"),
	    CELL_PHONE(2, "手机号", "cellPhone"),
	    EMAIL(3, "邮箱", "email"),
	 	BANK_CARD(4, "银行卡号", "bankAccountNo"),
	 	TRUE_NAME(5,"真实姓名","trueName"),
	 	LEGAL_NAME(6,"法人姓名","legalName"),
	    LEGAL_MOBILE(7,"法人手机","legalMobile"),
	 	CONTACT_NAME(8,"联系人姓名","contactName"),
	    CONTACT_EMAIL(9,"联系人邮箱","contactEmail"),
	    CONTACT_MOBILE(10,"联系人手机","contactMobile"),
	    PASSPORT(11, "护照", "passPort"),
	    HKMT_PASS(12,"港澳台通行证","hkmtPass"),
	    LEGAL_IDCARD(14,"法人身份证","legalPersonId"),
	    PROXY_IDCARD(15,"代理人身份证","proxyPersonId"),
	    PROXY_NAME(16,"代理人姓名","proxyPersonName")
	 	;

	    /** 代码 */
	    private final Integer   code;
	    /** 信息 */
	    private final String message;
	    
	    private final String fieldName;

	    DeciphedTypeEnum(Integer code, String message, String fieldName) {
	        this.code = code;
	        this.message = message;
	        this.fieldName = fieldName;
	    }

	    /**
	     * 通过代码获取枚举项
	     * @param code
	     * @return
	     */
	    public static DeciphedTypeEnum getByCode(Integer code) {
	        if (code == null) {
	            return null;
	        }

	        for (DeciphedTypeEnum lnt : DeciphedTypeEnum.values()) {
	            if (lnt.getCode().equals(code)) {
	                return lnt;
	            }
	        }

	        return null;
	    }
	    
	    public static DeciphedTypeEnum getByFieldName(String fieldName) {
	        if (fieldName == null) {
	            return null;
	        }

	        for (DeciphedTypeEnum lnt : DeciphedTypeEnum.values()) {
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

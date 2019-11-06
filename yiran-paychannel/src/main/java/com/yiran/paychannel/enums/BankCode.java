package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;
/**
 * 银行映射
 * @author pandaa
 *
 */
public enum BankCode {
	COMM("COMM","03010000"),
	ICBC("ICBC","01020000"),
	BOC("BOC","01040000"),
	CCB("CCB","01050000"),
	CIB("CIB","03090000"),
	CITIC("CITIC","03020000"),
	SPDB("SPDB","03100000"),
	GDB("GDB","03060000"),
	SZPAB("SZPAB","03070000"),
	HCCB("HCCB","04233310"),
	BCCB("BCCB","04031000"),
	CZB("CZB","03160000"),
	BOS("BOS","04012900"),
	CEB("CEB","03030000"),
	ABC("ABC","01030000"),
	PSBC("PSBC","01000000"),
	CMB("CMB","03080000"),
	CMBC("CMBC","03050000"),
	NBCB("NBCB","04083320"),
	HXB("HXB","03040000");
	
	/** 代码 */
    private final String code;
    /** 信息 */
    private final String message;

    /**
     * 构造
     * @param code
     * @param message
     */
    BankCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据代码获取
     * @param code
     * @return
     */
    public static BankCode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (BankCode type : BankCode.values()) {
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

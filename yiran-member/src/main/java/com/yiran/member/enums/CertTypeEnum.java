package com.yiran.member.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * <p>证件类型</p>
 * @author wangbin.ben
 * @version $Id: CertTypeEnum.java, v 0.1 2013-4-10 下午2:57:59 User Exp $
 */
public enum CertTypeEnum {
    ID_CARD("IC", "身份证", VerifyTypeEncryptMappingEnum.ID_CARD),

    PASSPORT("PP", "护照", VerifyTypeEncryptMappingEnum.PASSPORT),

    HK_MC_PASS("HMP", "港澳居民来往内地通行证", VerifyTypeEncryptMappingEnum.HKMT_PASS);
    
//    TEMP_ID_CARD("TIC", "临时身份证", null),
    
//    SOLDIER_CARD("SC", "士兵证", null),
//
//    ARMY_OFFICER_CARD("AOC", "军官证", null),
//
//    ARMY_CIVILIAN_CADRE("ACC", "军人文职干部证", null),
//
//    POLICE_OFFICER_CARD("POC", "警官证", null),
//
//    ARMED_POLICE_CARD("APC", "武警证", null),


//    RESIDENCE_BOOKLET("RB", "户口簿", null),
//
//    TW_PASS("TWP", "台湾居民来往大陆通行证/台胞证", null),
//
//    TW_RETURN("TWR", "台湾回乡证", null),
//
//    FOREIGN_PASSPORT("FPP", "外国护照", null),

//    FOREIGNER_RESIDENCE("FR", "外国人永久居留证", null);

    private final String                       code;
    private final String                       message;
    private final VerifyTypeEncryptMappingEnum verifyTypeEnum;

    public VerifyTypeEncryptMappingEnum getVerifyTypeEnum() {
        return verifyTypeEnum;
    }

    private CertTypeEnum(String code, String message, VerifyTypeEncryptMappingEnum verifyTypeEnum) {
        this.code = code;
        this.message = message;
        this.verifyTypeEnum = verifyTypeEnum;
    }

    public static CertTypeEnum getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        for (CertTypeEnum type : values()) {
            if (type.getCode().equals(code)) {
                return type;
            }
        }

        return null;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}

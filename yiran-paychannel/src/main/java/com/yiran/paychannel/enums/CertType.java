package com.yiran.paychannel.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 证件类型
 * @author pandaa
 *
 */
public enum CertType
{
  ID_CARD("IC", "身份证"), 

  TEMP_ID_CARD("TIC", "临时身份证"), 

  PASSPORT("PP", "护照"), 

  SOLDIER_CARD("SC", "士兵证"), 

  ARMY_OFFICER_CARD("AOC", "军官证"), 

  ARMY_CIVILIAN_CADRE("ACC", "军人文职干部证"), 

  POLICE_OFFICER_CARD("POC", "警官证"), 

  ARMED_POLICE_CARD("APC", "武警证"), 

  HK_MC_PASS("HMP", "港澳居民来往内地通行证"), 

  RESIDENCE_BOOKLET("RB", "户口簿"), 

  TW_PASS("TWP", "台湾居民来往大陆通行证/台胞证"), 

  TW_RETURN("TWR", "台湾回乡证"), 

  FOREIGN_PASSPORT("FPP", "外国护照"), 

  FOREIGNER_RESIDENCE("FR", "外国人永久居留证");

  private final String code;
  private final String message;

  private CertType(String code, String message)
  {
    this.code = code;
    this.message = message;
  }

  public static CertType getByCode(String code)
  {
    if (StringUtils.isBlank(code)) {
      return null;
    }

    for (CertType type : values()) {
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


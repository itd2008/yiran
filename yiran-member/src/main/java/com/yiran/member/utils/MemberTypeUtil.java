package com.yiran.member.utils;

import com.yiran.member.enums.MemberTypeEnum;

/**
 * <p>判断会员类型工具类</p>
 */
public class MemberTypeUtil {
    //工具类处理
    public static boolean isCompanyMemberType(Integer memberType) {
        return MemberTypeEnum.COMPANY.getCode().intValue() == memberType || MemberTypeEnum.SPECIAL.getCode().intValue() == memberType ;
    }
    
    public static boolean isPersonMemberType(Integer memberType) {
        return   MemberTypeEnum.PERSONAL.getCode().intValue() == memberType;
    }
    
    public static boolean isSpecialMemberType(Integer memberType) {
        return   MemberTypeEnum.SPECIAL.getCode().intValue() == memberType;
    }
    
    public static boolean isVirtualMemberType(Integer memberType) {
        return   MemberTypeEnum.VIRTUAL.getCode().intValue() == memberType;
    }
    
    public static MemberTypeEnum getPersonMemberType(){
        return MemberTypeEnum.PERSONAL;
    }
    
    public static MemberTypeEnum getCompanyMemberType(){
        return MemberTypeEnum.COMPANY;
    }
    
    public static MemberTypeEnum getSpecialMemberType(){
        return MemberTypeEnum.SPECIAL;
    }
}

package com.yiran.member.validator;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.yiran.member.constant.FieldLength;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.domain.MemberTdAccountConfig;
import com.yiran.member.enums.AccountCategoryEnum;
import com.yiran.member.enums.ContactVerifyStatusEnum;
import com.yiran.member.enums.IdentityTypeEnum;
import com.yiran.member.enums.LoginNameTypeEnum;
import com.yiran.member.enums.MemberTypeEnum;
import com.yiran.member.enums.MemberVerifyLevelEnum;
import com.yiran.member.enums.PassWordStatusEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.utils.Utils;
import com.yiran.member.utils.ValidateUtils;
import com.yiran.member.utils.VerifyDomainUtil;

/**
 * <p>验证公共参数类</p>
 * @author chenfei
 * @version $Id: CommonFacadeValidator.java, v 0.1 2013-1-7 上午10:37:42 cf Exp $
 */
public class CommonFacadeValidator {

    /**
     * @param memberId 会员Id
     * @param ismust 是否必填
     */
    public static void checkMemberId(String memberId, boolean ismust) {
        if (ismust && StringUtils.isBlank(memberId)) {
            throw new MaIllegalArgumentException("会员编号不能为空");
        }
        if (memberId != null && memberId.length() > FieldLength.MEMBER_ID) {
            throw new MaIllegalArgumentException("会员编号长度不能大于" + FieldLength.MEMBER_ID + "位");
        }
    }

    /**
     * 
     * @param operatorId 操作员Id
     * @param ismust 是否必填
     */
    public static void checkOperatorId(String operatorId, boolean ismust) {
        if (ismust && StringUtils.isBlank(operatorId)) {
            throw new MaIllegalArgumentException("操作员ID不能为空");
        } 
        
        if (operatorId != null && operatorId.length() > FieldLength.OPERATOR_ID) {
            throw new MaIllegalArgumentException("操作员编号长度不能大于" + FieldLength.OPERATOR_ID + "位");
        }
    }

    /**
     * @param accountId 账户Id
     * @param ismust 是否必填
     */
    public static void checkAccountId(String accountId, boolean ismust) {
        if (ismust && StringUtils.isBlank(accountId)) {
            throw new MaIllegalArgumentException("账户Id不能为空");
        } 
        if (accountId != null && accountId.length() > FieldLength.ACCOUNT_ID) {
            throw new MaIllegalArgumentException("账户Id长度不能大于" + FieldLength.ACCOUNT_ID + "位");
        }
    }
    
    /**
     * 验证账户类型是否存在
     * @param accountType
     */
    public static void checkAccountType(Long accountType, boolean ismust ) {
        if (ismust && null == accountType) {
            throw new MaIllegalArgumentException("账户类型不能为空");
        } 
        if (accountType != null) {
            //TODO: 到缓存中 验证账户类型是否存在
        	
        }
    }
    
    public static void checkContactVerifyStatus(Long status, boolean ismust ) {
        if (ismust && null == status) {
            throw new MaIllegalArgumentException("认证状态不能为空");
        } 
        if (status != null) {
           ContactVerifyStatusEnum statusEnum = ContactVerifyStatusEnum.getByCode(status.intValue());
            if (statusEnum == null) {
                throw new MaIllegalArgumentException("不存在的状态类型:" + status);
            }
        }
    }
    
    public static void checkAccountType(String accountType, boolean ismust, AccountCategoryEnum cat ) {
        if (ismust && StringUtils.isBlank(accountType)) {
            throw new MaIllegalArgumentException("账户类型不能为空");
        } 
        if (accountType != null && accountType.length() > FieldLength.ACCOUNT_TYPE) {
            throw new MaIllegalArgumentException("账户类型长度不能大于" + FieldLength.ACCOUNT_TYPE + "位");
        }
        if(StringUtils.isNotBlank(accountType) && AccountCategoryEnum.DPM.equals(cat)){
            //TODO: 到缓存中 验证账户类型是否存在
           /* AccountConfig acctConfig = AccountConfigCacheUtil.getByCode(accountType);
            if (acctConfig == null) {
                throw new MaIllegalArgumentException("不存在的账户类型:" + accountType);
            }*/
        }
    }
    
    public static void checkAccountType(List<String> accountTypes, boolean ismust ) {
        if (ismust && CollectionUtils.isEmpty(accountTypes)) {
            throw new MaIllegalArgumentException("账户类型不能为空");
        }
        if(!CollectionUtils.isEmpty(accountTypes)){
            for(String item : accountTypes){
                if(StringUtils.isBlank(item)){
                    throw new MaIllegalArgumentException("账户类型不能为空字符串");
                }
            }
        }
    }
    
    public static void checkAccountIds(List<String> accountIds, boolean ismust ) {
        if (ismust && CollectionUtils.isEmpty(accountIds)) {
            throw new MaIllegalArgumentException("账户号不能为空");
        }
        if(!CollectionUtils.isEmpty(accountIds)){
            for(String item : accountIds){
                if(StringUtils.isBlank(item)){
                    throw new MaIllegalArgumentException("账号不能为空字符串");
                }
            }
        }
    }
    
    public static AccountCategoryEnum checkAccountCategory(String category, boolean ismust ) {
        if (ismust && StringUtils.isBlank(category)) {
            throw new MaIllegalArgumentException("账户分类不能为空");
        }
        AccountCategoryEnum catEnum = null;
        if(StringUtils.isNotBlank(category)){
            catEnum = AccountCategoryEnum.getByCode(category);
            if(catEnum == null){
                throw new MaIllegalArgumentException("不存在的账户分类:" + category);
            }
        }
        return catEnum;
    }
    

    /**
     * @param LoginPwd 登陆密码
     * @param ismust 是否必填
     */
    public static void checkLoginPwd(String LoginPwd, boolean ismust) {
        if (ismust && StringUtils.isBlank(LoginPwd)) {
            throw new MaIllegalArgumentException("密码不能为空");
        } 
        if (LoginPwd != null && LoginPwd.length() > FieldLength.LOGIN_PASSWORD) {
            throw new MaIllegalArgumentException("密码长度不能大于" + FieldLength.LOGIN_PASSWORD + "位");
        }
    }
    
    public static void checkPwdStatus(Long pwdStatus,boolean ismust) {
        if (ismust && pwdStatus == null) {
            throw new MaIllegalArgumentException("密码状态不能为空");
        } 
        PassWordStatusEnum status = PassWordStatusEnum.getByCode(pwdStatus.intValue());
        if(status == null){
        	throw new MaIllegalArgumentException("密码状态只能是初始/正常");
        }
    }
    

    /**
     * 验证会员标识
     * @param identity
     * @param ismust
     */
    public static void checkIdentity(String identity, boolean ismust) {
        if (ismust && StringUtils.isBlank(identity)) {
            throw new MaIllegalArgumentException("会员标识不能为空");
        }
        if (Utils.getByteLen(identity) > FieldLength.IDENTITY) {
            throw new MaIllegalArgumentException("会员标识长度不能大于" + FieldLength.IDENTITY + "位");
        }
    }
    
    /**
     * 外部平台用户标识
     * @param identity
     * @param ismust
     */
    public static void checkPlatformUserId(String identity, boolean ismust) {
        if (ismust && StringUtils.isBlank(identity)) {
            throw new MaIllegalArgumentException("外部平台用户标识不能为空");
        }
        if (Utils.getByteLen(identity) > FieldLength.IDENTITY) {
            throw new MaIllegalArgumentException("外部平台用户标识不能大于" + FieldLength.IDENTITY + "位");
        }
    }

    /**
     * 检查创建会员时pid：平台类型
     * 
     * @param pid
     */
    public static void checkPlatFormType(String pid) {
        checkPlatFormType(pid,true);
    }
    
    /**
     * 检查创建会员时pid：平台类型
     * 
     * @param pid
     */
    public static void checkLoginPlatFormType(String pid) {
    	//到缓存中检查平台类型
      /*SysConfig config = SysConfigCacheUtil.getByTypeAndValue(MaConstant.TYPE_MEMBER_IDENTITY_PID,pid);
      if(!MaConstant.PLAT_TYPE_CAN_LOGIN_UID.equals(config.getExt1())){
          throw new MaIllegalArgumentException("该平台类型暂不支持钱包");
      }*/
    }
    /**
     * 检查平台类型
     * @param pid
     * @param isMust
     */
    public static void checkPlatFormType(String pid,boolean isMust) {
        if (isMust && StringUtils.isBlank(pid)) {
            throw new MaIllegalArgumentException("平台类型不能为空");
        }
        if (Utils.getByteLen(pid) > FieldLength.IDENTITY_PID) {
            throw new MaIllegalArgumentException("平台类型长度不能大于" + FieldLength.IDENTITY_PID + "位");
        }
       
    }
    
    public static void checkSalt(String salt) {
        if (StringUtils.isBlank(salt)) {
            throw new MaIllegalArgumentException("验证盐值不能为空");
        }
    }
    
    public static void checkOperatorContact(String contact,boolean isMust) {
        if (isMust && StringUtils.isBlank(contact)) {
            throw new MaIllegalArgumentException("联系方式不能为空");
        }
    }

    public static void checkPayPassword(String payPwd) {
        checkPayPassword(payPwd,true);
    }
    
    public static void checkPayPassword(String payPwd, boolean isMust) {
        if (isMust && StringUtils.isBlank(payPwd)) {
            throw new MaIllegalArgumentException("支付密码不能为空");
        }
        if (payPwd != null && payPwd.length() > FieldLength.PAY_PASSWORD_MAX) {
            throw new MaIllegalArgumentException("支付密码长度不能大于" + FieldLength.PAY_PASSWORD_MAX + "位");
        }
    }

    /**
     * 验证登录名
     * @param loginName
     */
    public static void checkLoginName(String loginName) {
        if (StringUtils.isBlank(loginName)) {
            throw new MaIllegalArgumentException("登录名不能为空");
        }
        if (Utils.getByteLen(loginName) > FieldLength.LOGIN_NAME) {
            throw new MaIllegalArgumentException("登录名长度不能大于" + FieldLength.LOGIN_NAME + "位");
        }
    }
    
    /**
     * 登录名类型
     * @param loginNameType
     */
    public static void checkLoginNameType(Integer loginNameType) {
        if (loginNameType == null) {
            throw new MaIllegalArgumentException("登录名类型不能为空");
        }
        int loginType = loginNameType.intValue();
        if (loginType != LoginNameTypeEnum.EMAIL.getCode().intValue()
            && loginType != LoginNameTypeEnum.CELL_PHONE.getCode().intValue()
            && loginType != LoginNameTypeEnum.COMMON_CHAR.getCode().intValue()) {
            throw new MaIllegalArgumentException("登录名类型只能是手机/邮箱/通用文字");
        }
    }

    /**
     * 验证会员类型
     * @param memberType
     */
    public static void checkMemberTypeForCreate(Integer memberType) {
        if (memberType == null) {
            throw new MaIllegalArgumentException("会员类型不能为空");
        }
        long loginType = memberType.longValue();
        if (null == MemberTypeEnum.getByCode(loginType)) {
            throw new MaIllegalArgumentException("会员类型只能是个人/企业/特约商户");
        }
    }
    
    /**
     * 验证会员类型
     * @param memberType
     */
    public static void checkMemberType(String memberType, boolean isMust) {
        if (isMust && memberType == null) {
            throw new MaIllegalArgumentException("会员类型不能为空");
        }
        if (memberType != null) {
            String[] memberTypeArr = memberType.split(",");
            for(String type : memberTypeArr) {
                if(null == MemberTypeEnum.getByCode(Long.parseLong(type))) {
                    throw new MaIllegalArgumentException("会员类型只能是个人/企业/特约商户");
                }
            }
        }
    }
    
    /**
     * 验证身份证
     * @param memberType
     */
    public static void checkIDCard(String IDCard, boolean isMust) {
        if (isMust && IDCard == null) {
            throw new MaIllegalArgumentException("身份证号不能为空");
        }
        if (IDCard != null && !ValidateUtils.isIdCard(IDCard)) {
            throw new MaIllegalArgumentException("身份证号无效");
        }
    }

    /**
     * 校验登录名格式
     * @param loginName
     * @param loginType
     */
    public static void checkLoginNameFormat(String loginName, int loginType) {
        if (LoginNameTypeEnum.EMAIL.getCode().intValue() == loginType) {
            if (!ValidateUtils.isMail(loginName)) {
                throw new MaIllegalArgumentException("邮箱格式非法:" + loginName);
            }
        } else if (LoginNameTypeEnum.CELL_PHONE.getCode().intValue() == loginType) {
        	// 由hry默认创建的kjt是临时名字所以这里要替换 而邮件则不需要
        	String rstLoginName = VerifyDomainUtil.replaceHrySign(loginName);
            if (!ValidateUtils.isMobile(VerifyDomainUtil.replaceHrySign(rstLoginName))) {
                throw new MaIllegalArgumentException("手机格式非法:" + rstLoginName);
            }
        }
    }
    
    /**
     * 校验会员标识格式
     * @param identity
     * @param identityType
     */
    public static void checkIdentityFormat(String identity, int identityType) {
        if (IdentityTypeEnum.EMAIL.getCode() == identityType) {
            if (!ValidateUtils.isMail(identity)) {
                throw new MaIllegalArgumentException("邮箱格式非法:" + identity);
            }
        } else if (IdentityTypeEnum.CELL_PHONE.getCode() == identityType) {
        	  if (!ValidateUtils.isMobile(identity)) {
                throw new MaIllegalArgumentException("手机格式非法:" + identity);
        	  }
        }
    }
    
    public static void checkIdentityType(Integer identityType,boolean isMust){
        if (isMust && identityType == null) {
            throw new MaIllegalArgumentException("会员标识类型不能为空");
        }
        if (identityType != null && IdentityTypeEnum.getByCode(identityType) == null) {
            throw new MaIllegalArgumentException("会员标识类型非法");
        }
    }
    
    /**
     * 验证企业会员类型
     * @param memberType
     */
    public static void checkCompanyMemberType(Integer memberType) {
        if (memberType == null) {
            throw new MaIllegalArgumentException("会员类型不能为空");
        }
        long loginType = memberType.longValue();
        if (null == MemberTypeEnum.getByCode(loginType) || MemberTypeEnum.PERSONAL == MemberTypeEnum.getByCode(loginType)) {
            throw new MaIllegalArgumentException("会员类型只能是企业/特约商户");
        }
    }
    
    /**
     * 验证认证等级
     * @param verifyLevel
     * @param ismust
     */
    public static void checkVerifyLevel(Integer verifyLevel, boolean ismust) {
        if (ismust && verifyLevel == null) {
            throw new MaIllegalArgumentException("认证等级不能为空");
        }
        if (verifyLevel != null && null == MemberVerifyLevelEnum.getByCode(verifyLevel)) {
            throw new MaIllegalArgumentException("认证等级非法");
        }
    }
    
    /**
     * 验证注册来源
     * @param verifyLevel
     * @param ismust
     */
    public static void checkRegisterSource(String registerSource, boolean ismust) {
        if (ismust && registerSource == null) {
            throw new MaIllegalArgumentException("注册来源不能为空");
        }
    }

}

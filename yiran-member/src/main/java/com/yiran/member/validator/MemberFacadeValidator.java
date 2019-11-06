package com.yiran.member.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.FieldLength;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.domain.MemberTrCompanyMember;
import com.yiran.member.domain.VerifyFacadeValidator;
import com.yiran.member.domain.VerifyInfo;
import com.yiran.member.enums.BlukQueryFlagEnum;
import com.yiran.member.enums.CareerEnum;
import com.yiran.member.enums.CompanyTypeEnum;
import com.yiran.member.enums.GenderEnum;
import com.yiran.member.enums.IdentityTypeEnum;
import com.yiran.member.enums.LockEnum;
import com.yiran.member.enums.LoginNameTypeEnum;
import com.yiran.member.enums.MemberAccountStatusEnum;
import com.yiran.member.enums.PositionEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.request.AccountQueryRequest;
import com.yiran.member.request.ActivatePersonalRequest;
import com.yiran.member.request.BulkAcctQueryRequest;
import com.yiran.member.request.CompanyMemberQueryRequest;
import com.yiran.member.request.CreateMemberInfoRequest;
import com.yiran.member.request.CreateVirtualMerchantRequest;
import com.yiran.member.request.IntegratedCompanyRequest;
import com.yiran.member.request.IntegratedPersonalRequest;
import com.yiran.member.request.MemberIdentityRequest;
import com.yiran.member.request.MemberIntegratedIdRequest;
import com.yiran.member.request.MemberIntegratedRequest;
import com.yiran.member.request.MemberLoginNameQueryRequest;
import com.yiran.member.request.MobileIdentityRequest;
import com.yiran.member.request.PersonalMemberInfoRequest;
import com.yiran.member.request.PersonalMemberQueryRequest;
import com.yiran.member.request.PersonalMemberRequest;
import com.yiran.member.request.UpdateMemberLockStatusRequest;
import com.yiran.member.utils.MemberDomainUtil;
import com.yiran.member.utils.Utils;


/**
 * <p>会员类接口入参验证</p>
 */
public class MemberFacadeValidator {

    
    /**
     * 验证查询个人信息
     * @param request
     */
    public static void validator(PersonalMemberQueryRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询个人信息请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
    }
    /**
     * 验证设置个人会员
     * @param request
     */
    public static void validator(PersonalMemberInfoRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("设置个人会员请求未提供");
        }
        //会员id
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);

        //会员名称
        checkMemberName(request.getMemberName(), false);
        //真实姓名
        checkRealName(request.getRealName(), false);
        //生日
        checkBirthDay(request.getBirthDay(), false);
        //职业类型
        checkCareer(request.getCareer(), false);
        //性别类型
        checkGender(request.getGender(), false);
        //职位类型
        checkPosition(request.getPosition(), false);
    }

    /**
     * 验证查询企业信息
     * @param request
     */
    public static void validator(CompanyMemberQueryRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询企业信息请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
    }

    /**
     * 验证查询会员综合信息请求
     * @param request
     */
    public static void validator(MemberIntegratedRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询会员综合信息请求未提供");
        }

        CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType(), false);
        AccountQueryRequest query = request.getAccountRequest();
        if (query != null) {
            List<Long> rest = query.getAccountTypes();
            if (query.isRequireAccountInfos() && !(rest == null || rest.isEmpty())) {
                for (Long type : rest) {
                    CommonFacadeValidator.checkAccountType(type, false);
                }
            }
        }
    }

    /**
     * 查询会员综合信息请求查询企业信息请求
     * @param request
     */
    public static void validator(MemberIntegratedIdRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询会员综合信息请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        AccountQueryRequest query = request.getAccountRequest();
        if (query != null) {
            List<Long> rest = query.getAccountTypes();
            if (query.isRequireAccountInfos() && !(rest == null || rest.isEmpty())) {
                for (Long type : rest) {
                    CommonFacadeValidator.checkAccountType(type, false);
                }
            }
        }
    }

    /**
     * 验证集成创建个人会员
     * @param request
     */
    public static void validator(IntegratedPersonalRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("个人集成创建请求未提供");
        }
        //个人会员信息
        checkPersonalMember(request.getPersonalRequest());
        //外部平台用户id
        CommonFacadeValidator.checkPlatformUserId(request.getPlatformUserId(), false);
        //平台类型
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType(), false);
        //开户标志
        if (request.getMemberAccountFlag() != null
            && MemberAccountStatusEnum.getByCode(request.getMemberAccountFlag()) == null) {
            throw new MaIllegalArgumentException("个人集成创建会员状态与账户标志非法");
        }
        //认证信息
        if (!(request.getVerifys() == null || request.getVerifys().isEmpty())) {
            List<Integer> verifyTypeList = new ArrayList<Integer>(request.getVerifys().size());
            for (VerifyInfo verify : request.getVerifys()) {
                VerifyFacadeValidator.validatorCreateVerify(verify);
                if (verifyTypeList.contains(verify.getVerifyType())) {
                    throw new MaIllegalArgumentException("个人集成创建一个会员不能有相同类型的认证信息");
                } else {
                    verifyTypeList.add(verify.getVerifyType());
                }
            }
        }
        
        String registerSource = MemberDomainUtil.getResisterSource(request.getExtention());
        //验证注册来源
        CommonFacadeValidator.checkRegisterSource(registerSource, false);;
    }

    /**
     * 验证会员创建
     * @param request
     */
    public static void validator(CreateMemberInfoRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("创建会员请求未提供");
        }
        //会员类型
        CommonFacadeValidator.checkMemberTypeForCreate(request.getMemberType());
        //校验登录名
        CommonFacadeValidator.checkLoginName(request.getLoginName());
        //校验登录名类型
        CommonFacadeValidator.checkLoginNameType(request.getLoginNameType());
        //登录名平台类型 20131118
        CommonFacadeValidator.checkPlatFormType(request.getLoginNamePlatformType(), true);
        //校验登录名格式
        CommonFacadeValidator.checkLoginNameFormat(StringUtils.trim(request.getLoginName()), request
            .getLoginNameType().intValue());
        //登录密码
        CommonFacadeValidator.checkLoginPwd(request.getLoginPassword(), false);
        //外部平台用户id
        CommonFacadeValidator.checkPlatformUserId(request.getPlatformUserId(), false);
        //平台类型
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType(), false);
        //会员名称
        checkMemberName(request.getMemberName(), false);
        //检查注册来源
        CommonFacadeValidator.checkRegisterSource(getResisterSource(request.getExtention()), false);
    }
    /**
     * 获取注册来源
     * @param extension
     *          扩展字段  
     * @return
     */
    public static String getResisterSource(String extension) {
        if(StringUtils.isNotEmpty(extension)) {
            JSONObject json = JSONObject.parseObject(extension);
            String registerSource = json.getString(MaConstant.REGISTER_SOURCE);
            return registerSource;
        } else {
            return null;
        }
    }
    
    /**
     * 验证特约会员创建
     * 基本会员信息
     * @param request
     */
    public static void validator(IntegratedCompanyRequest request) {
        //验证会员类型
        CommonFacadeValidator.checkCompanyMemberType(request.getMemberType());
        //验证基本请求
        validator((CreateMemberInfoRequest)request);
        //验证企业信息
        if(null != request.getCompanyInfo()){
            validatorCompanyInfo(request.getCompanyInfo());
        }
        //验证商户信息
        if(null != request.getMerchantInfo()){
            MerchantFacadeValidator.validator(request.getMerchantInfo());
        }
        //验证注册来源
        CommonFacadeValidator.checkRegisterSource(MemberDomainUtil.getResisterSource(request.getExtention()), false);
    }

    /**
     * 验证设置个人会员
     * @param request
     */
    /*public static void validator(PersonalMemberInfoRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("设置个人会员请求未提供");
        }
        //会员id
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);

        //会员名称
        checkMemberName(request.getMemberName(), false);
        //真实姓名
        checkRealName(request.getRealName(), false);
        //生日
        checkBirthDay(request.getBirthDay(), false);
        //职业类型
        checkCareer(request.getCareer(), false);
        //性别类型
        checkGender(request.getGender(), false);
        //职位类型
        checkPosition(request.getPosition(), false);
    }*/

    private static void checkPersonalMember(PersonalMemberRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("个人信息请求未提供");
        }
        //校验登录名
        CommonFacadeValidator.checkLoginName(request.getLoginName());
        //校验登录名类型
        CommonFacadeValidator.checkLoginNameType(request.getLoginNameType());
        //校验登录名格式
        CommonFacadeValidator.checkLoginNameFormat(StringUtil.trim(request.getLoginName()), request
            .getLoginNameType().intValue());
        //登录名平台类型 20131118
        CommonFacadeValidator.checkPlatFormType(request.getLoginNamePlatformType(), true);
        //登录密码
        CommonFacadeValidator.checkLoginPwd(request.getLoginPassword(), false);

        //会员名称
        checkMemberName(request.getMemberName(), false);
        //真实姓名
        checkRealName(request.getRealName(), false);
        //生日
        checkBirthDay(request.getBirthDay(), false);
        //职业类型
        checkCareer(request.getCareer(), false);
        //性别类型
        checkGender(request.getGender(), false);
        //职位类型
        checkPosition(request.getPosition(), false);
    }

    private static void checkMemberName(String memberName, boolean isMust) {
        if (isMust && StringUtils.isEmpty(memberName)) {
            throw new MaIllegalArgumentException("会员名称不能为空");
        }
        if (Utils.getByteLen(memberName) > FieldLength.MEMBER_NAME) {
            throw new MaIllegalArgumentException("会员名称长度不能大于" + FieldLength.MEMBER_NAME + "位");
        }
    }

    private static void checkRealName(String realName, boolean isMust) {
        if (isMust && StringUtils.isEmpty(realName)) {
            throw new MaIllegalArgumentException("真实姓名不能为空");
        }
        if (Utils.getByteLen(realName) > FieldLength.TRUE_NAME) {
            throw new MaIllegalArgumentException("真实姓名长度不能大于" + FieldLength.TRUE_NAME + "位");
        }
    }

    private static void checkBirthDay(String birthDay, boolean isMust) {
        if (isMust && StringUtils.isEmpty(birthDay)) {
            throw new MaIllegalArgumentException("生日不能为空");
        }
        if (Utils.getByteLen(birthDay) > FieldLength.BIRTHDAY) {
            throw new MaIllegalArgumentException("生日长度不能大于" + FieldLength.BIRTHDAY + "位");
        }

    }

    private static void checkCareer(Long career, boolean isMust) {
        if (isMust && career == null) {
            throw new MaIllegalArgumentException("职业类型未提供");
        }
        if (career != null && CareerEnum.getByCode(career) == null) {
            throw new MaIllegalArgumentException("职业类型非法:" + career);
        }
    }

    private static void checkGender(Long career, boolean isMust) {
        if (isMust && career == null) {
            throw new MaIllegalArgumentException("性别类型未提供");
        }
        if (career != null && GenderEnum.getByCode(career) == null) {
            throw new MaIllegalArgumentException("性别类型非法:" + career);
        }
    }

    private static void checkPosition(Long position, boolean isMust) {
        if (isMust && position == null) {
            throw new MaIllegalArgumentException("职位类型未提供");
        }
        if (position != null && PositionEnum.getByCode(position) == null) {
            throw new MaIllegalArgumentException("职位类型非法:" + position);
        }
    }
    
    private static void checkCompanyType(Integer type, boolean isMust) {
        if (isMust && type == null) {
            throw new MaIllegalArgumentException("企业类型未提供");
        }
        if (type != null && CompanyTypeEnum.getByCode(type) == null) {
            throw new MaIllegalArgumentException("企业类型非法:" + type);
        }
    }

    /**
     * 验证设置企业信息
     * @param company
     */
    /*public static void validator(CompanyInfo company) {
        if (company == null) {
            throw new MaIllegalArgumentException("企业信息请求未提供");
        }

        CommonFacadeValidator.checkMemberId(company.getMemberId(), true);

        validatorCompanyInfo(company);
    }*/

   public static void validatorCompanyInfo(MemberTrCompanyMember company) {
        checkCompanyType(company.getCompanyType(),false);

        if (Utils.getByteLen(company.getCompanyName()) > FieldLength.COMPANY_NAME) {
            throw new MaIllegalArgumentException("企业会员名称不能大于" + FieldLength.COMPANY_NAME + "位");
        }

        if (Utils.getByteLen(company.getAddress()) > FieldLength.ADDRESS) {
            throw new MaIllegalArgumentException("企业地址不能大于" + FieldLength.ADDRESS + "位");
        }

        if (Utils.getByteLen(company.getBusinessScope()) > FieldLength.BUSINESS_SCOPE) {
            throw new MaIllegalArgumentException("营业范围不能大于" + FieldLength.BUSINESS_SCOPE + "位");
        }

        if (Utils.getByteLen(company.getClearingAccountPath()) > FieldLength.CLEARING_ACCOUNT_PATH) {
            throw new MaIllegalArgumentException("单位银行结算账户开户许可证附件地址不能大于"
                                                 + FieldLength.CLEARING_ACCOUNT_PATH + "位");
        }

        if (Utils.getByteLen(company.getIcpLicensePath()) > FieldLength.ICP_LICENSE_PATH) {
            throw new MaIllegalArgumentException("ICP备案许可附件地址不能大于" + FieldLength.ICP_LICENSE_PATH
                                                 + "位");
        }

        if (Utils.getByteLen(company.getInstitutionLicensePath()) > FieldLength.INSTITUTION_LICENSE_PATH) {
            throw new MaIllegalArgumentException("机构信用代码证地址不能大于"
                                                 + FieldLength.INSTITUTION_LICENSE_PATH + "位");
        }

        if (Utils.getByteLen(company.getIndustryLicensePath()) > FieldLength.INDUSTRY_LICENSE_PATH) {
            throw new MaIllegalArgumentException("行业许可证附件地址不能大于"
                                                 + FieldLength.INDUSTRY_LICENSE_PATH + "位");
        }

        if (Utils.getByteLen(company.getLegalPerson()) > FieldLength.LEGAL_PERSON) {
            throw new MaIllegalArgumentException("企业法人不能大于" + FieldLength.LEGAL_PERSON + "位");
        }

        if (Utils.getByteLen(company.getLegalPersonPhone()) > FieldLength.LEGAL_PERSON_PHONE) {
            throw new MaIllegalArgumentException("法人手机号码不能大于" + FieldLength.LEGAL_PERSON_PHONE
                                                 + "位");
        }

        if (Utils.getByteLen(company.getLicenseAddress()) > FieldLength.LICENSE_ADDRESS) {
            throw new MaIllegalArgumentException("营业执照所在地不能大于" + FieldLength.LICENSE_ADDRESS + "位");
        }

        if (Utils.getByteLen(company.getLicenseNo()) > FieldLength.LICENSE_NO) {
            throw new MaIllegalArgumentException("执照号不能大于" + FieldLength.LICENSE_NO + "位");
        }

        if (Utils.getByteLen(company.getLicenseNoPath()) > FieldLength.LICENSE_NO_PATH) {
            throw new MaIllegalArgumentException("企业营业执照附件地址不能大于" + FieldLength.LICENSE_NO_PATH
                                                 + "位");
        }

        if (Utils.getByteLen(company.getOrganizationNo()) > FieldLength.ORGANIZATION_NO) {
            throw new MaIllegalArgumentException("组织机构代码不能大于" + FieldLength.ORGANIZATION_NO + "位");
        }

        if (Utils.getByteLen(company.getOrganizationNoPath()) > FieldLength.ORGANIZATION_NO_PATH) {
            throw new MaIllegalArgumentException("组织机构代码证附件地址不能大于"
                                                 + FieldLength.ORGANIZATION_NO_PATH + "位");
        }

        if (Utils.getByteLen(company.getSummary()) > FieldLength.SUMMARY) {
            throw new MaIllegalArgumentException("企业简介不能大于" + FieldLength.SUMMARY + "位");
        }

        if (Utils.getByteLen(company.getTaxNoPath()) > FieldLength.TAX_NO_PATH) {
            throw new MaIllegalArgumentException("税务登记证不能大于" + FieldLength.TAX_NO_PATH + "位");
        }

        if (Utils.getByteLen(company.getTelephone()) > FieldLength.TELEPHONE) {
            throw new MaIllegalArgumentException("联系电话不能大于" + FieldLength.TELEPHONE + "位");
        }

        if (Utils.getByteLen(company.getWebsite()) > FieldLength.WEBSITE) {
            throw new MaIllegalArgumentException("企业网址不能大于" + FieldLength.WEBSITE + "位");
        }
        
        if (Utils.getByteLen(company.getLicenseName()) > FieldLength.LICENSE_NAME) {
            throw new MaIllegalArgumentException("登记证号名称不能大于" + FieldLength.LICENSE_NAME + "位");
        }
        if (Utils.getByteLen(company.getBusinessWebsite()) > FieldLength.BUSINESS_WEBSITE) {
            throw new MaIllegalArgumentException("经营网址不能大于" + FieldLength.BUSINESS_WEBSITE + "位");
        }
        if (Utils.getByteLen(company.getProxyPerson()) > FieldLength.PROXY_PERSON) {
            throw new MaIllegalArgumentException("代理人名称不能大于" + FieldLength.PROXY_PERSON + "位");
        }
    }

    /**
     * 验证激活企业会员
     * @param request
     */
    /*public static void validator(ActivateCompanyRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("激活企业信息请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        CommonFacadeValidator.checkPayPassword(request.getPayPassword(), false);
    }*/

    /**
     * 验证激活个人会员
     * @param request
     */
    public static void validator(ActivatePersonalRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("激活企业信息请求未提供");
        }
        CommonFacadeValidator.checkPayPassword(request.getPayPassword(), false);
        validator(request.getPersonalMemberInfo());
    }

    /**
     * 验证查询新付通会员请求
     * @param request
     */
    /*public static void validator(XMemberRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询新付通会员请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
    }*/

    /**
     *
     * @param request
     */
    /*public static void validator(DecipherInfoRequest request) {
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        if (CollectionUtils.isEmpty(request.getColumnList())) {
            throw new MaIllegalArgumentException("解密信息请求信息未提供");
        }

    }*/

    /**
     * 验证会员锁定状态请求
     * @param request
     */
    public static void validator(UpdateMemberLockStatusRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("设置会员锁定状态请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        if (request.getLockStatus() == null) {
            throw new MaIllegalArgumentException("会员锁定状态未提供");
        }

        if (LockEnum.getByCode(request.getLockStatus()) == null) {
            throw new MaIllegalArgumentException("会员锁定状态非法");
        }
    }

    /**
     * 验证会员标识
     * @param request
     */
    /*public static void validator(MemberIdentityRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("增加会员标识请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType(), true);
        CommonFacadeValidator.checkIdentityType(request.getIdentityType(), true);
        CommonFacadeValidator.checkIdentityFormat(request.getMemberIdentity(),
            request.getIdentityType());
    }*/

    /**
     * 验证手机号标识
     * @param request
     */
    public static void validator(MobileIdentityRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("增加手机号请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(),true);
        CommonFacadeValidator.checkIdentity(request.getMobileNo(), true);
        CommonFacadeValidator.checkIdentityFormat(request.getMobileNo(),IdentityTypeEnum.CELL_PHONE.getCode());
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType(), true);
    }


    /**
     * 验证会员标识
     * @param request
     */

    public static void validatorRemove(MemberIdentityRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("移除会员标识请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
        CommonFacadeValidator.checkPlatFormType(request.getPlatformType(), true);
    }

    /**
     * 验证批量查询会员账户状态接口
     * @param request
     */
    public static void validator(BulkAcctQueryRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("批量查询会员账户状态请求未提供");
        }
        if (StringUtils.isBlank(request.getQueryFlag())) {
            throw new MaIllegalArgumentException("查询标志未提供");
        } else {
            if (BlukQueryFlagEnum.getByCode(request.getQueryFlag()) == null) {
                throw new MaIllegalArgumentException("查询标志非法");
            }
        }
        if (CollectionUtils.isEmpty(request.getQueryItems())) {
            throw new MaIllegalArgumentException("查询项未提供");
        }

    }
    
    /**
     * 验证查询登陆账号
     * @param request
     */
    public static void validator(MemberLoginNameQueryRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("会员帐号查询请求未提供");
        }
        if(StringUtils.isBlank(request.getMail()) &&
                StringUtils.isBlank(request.getPhone()) &&StringUtils.isBlank(request.getIDCard())) {
            throw new MaIllegalArgumentException("手机号、邮箱、身份证不能都为空");
        }
        CommonFacadeValidator.checkMemberType(request.getMemberType(), false);
        if(StringUtils.isNotBlank(request.getPhone())) {
            CommonFacadeValidator.checkLoginNameFormat(request.getPhone(), LoginNameTypeEnum.CELL_PHONE.getCode());
        }
        if(StringUtils.isNotBlank(request.getMail())) {
            CommonFacadeValidator.checkLoginNameFormat(request.getMail(), LoginNameTypeEnum.EMAIL.getCode());
        }
        CommonFacadeValidator.checkIDCard(request.getIDCard(), false);
    }

	public static void validator(CreateVirtualMerchantRequest request) {
		if (request == null) {
            throw new MaIllegalArgumentException("虚拟商户信息未提供");
        }
		CommonFacadeValidator.checkMemberId(request.getParentMemberId(),true);
		CommonFacadeValidator.checkIdentity(request.getIdentify(), true);
		CommonFacadeValidator.checkIdentityType(request.getIdentifyType(), true);
		CommonFacadeValidator.checkPlatFormType(request.getIdentifyPlatformType(), true);
	}

}

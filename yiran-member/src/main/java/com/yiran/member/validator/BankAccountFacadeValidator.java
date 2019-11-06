package com.yiran.member.validator;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.FieldLength;
import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.enums.BankAccountAttrEnum;
import com.yiran.member.enums.BankAccountSignEnum;
import com.yiran.member.enums.BankAccountStatusEnum;
import com.yiran.member.enums.BankAccountTypeEnum;
import com.yiran.member.enums.BankAccountVerifiedEnum;
import com.yiran.member.enums.CertTypeEnum;
import com.yiran.member.enums.PayAttributeEnum;
import com.yiran.member.enums.YesNoEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.request.BankAccInfoRequest;
import com.yiran.member.request.BankAccountRequest;
import com.yiran.member.utils.Utils;
import com.yiran.member.utils.ValidateUtils;

/**
 * <p>
 * 银行卡验证相关接口
 * </p>
 * 
 */
public class BankAccountFacadeValidator {

    /**
     * 验证查询会员银行绑定请求参数
     * 
     * @param request
     */
    public static void validator(BankAccountRequest request) {

        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        checkCardType(request.getCardType());
        checkCardAttribute(request.getCardAttribute(), false);
        checkIsVerified(request.getIsVerified());
        BankAccountStatusEnum status = checkStatus(request.getStatus());
        checkIsSigning(request.getIsSigning());
        checkPayAttribute(request.getPayAttribute(), false);
        checkBankAccountNo(request.getBankAccountNum(), false);
        if (null != status) {
            // 只能查询 normal ,locked 的卡
            if (!(status == BankAccountStatusEnum.NORMAL || status == BankAccountStatusEnum.LOCKED)) {
                throw new MaIllegalArgumentException("银行账号卡状态非法");
            }
        }
    }

    /**
     * 验证根据四要素（信用卡五要素）查询快捷绑定银行卡的请求参数
     * 
     * @param request
     */
   /* public static void validatorQPayAccount(BankQPayAccountRequest request) {
        checkBankAccountNo(request.getBankAccountNum(), false);
        if (StringUtil.isEmpty(request.getCertNo())) {
            throw new MaIllegalArgumentException("认证号码未提供");
        }

        if (StringUtil.isNotEmpty(request.getMobileNo())) {
            if (!ValidateUtils.isMobile(request.getMobileNo())) {
                throw new MaIllegalArgumentException("手机格式非法");
            }
        }
        else {
            throw new MaIllegalArgumentException("手机号码未提供");
        }

        if (StringUtil.isEmpty(request.getBankAccountName())) {
            throw new MaIllegalArgumentException("开户姓名未提供");
        }
    }*/

    /**
     * 验证绑定银行卡信息
     * 
     * @param request
     */
    public static void validatorAddBankAccount(BankAccInfoRequest request) {
        checkBankAccInfoRequest(request);
        CommonFacadeValidator.checkMemberId(request.getBankInfo().getMemberId(), true);
        validateBankAccount(request.getBankInfo(), true);

        if (StringUtil.isEmpty(request.getBankInfo().getIsSigning())) {
            request.getBankInfo().setIsSigning(BankAccountSignEnum.UN_SIGN.getCode());
        }
        if (request.getBankInfo().getIsVerified() == null) {
            request.getBankInfo().setIsVerified(BankAccountVerifiedEnum.UN_VERIFIED.getCode());
        }
        if (request.getBankInfo().getStatus() == null) {
            request.getBankInfo().setStatus(BankAccountStatusEnum.NORMAL.getCode());
        }
        // 不能新增 disabled 的卡
        if (BankAccountStatusEnum.DISABLED.getCode().equals(request.getBankInfo().getStatus())) {
            throw new MaIllegalArgumentException("银行账号卡状态非法");
        }
    }

    /**
     * 验证绑定唯一银行卡
     * 
     * @param request
     */
    /*public static void validatorAddBankAccountDeleteOther(BankAccInfo2Request request) {
        checkBankAccInfoRequest(request);
        CommonFacadeValidator.checkMemberId(request.getBankInfo().getMemberId(), true);

        // request.getCardAttribute()如果为空
        boolean cardAttr = false;
        if (request.getBankInfo().getCardAttribute() == null) {
            cardAttr = true;
            request.getBankInfo().setCardAttribute(BankAccountAttrEnum.COMPANY.getCode());
        }
        validateBankAccount(request.getBankInfo(), true);
        // request.getCardAttribute()如果为空
        if (cardAttr) {
            request.getBankInfo().setCardAttribute(null);
        }

        if (StringUtil.isEmpty(request.getBankInfo().getIsSigning())) {
            request.getBankInfo().setIsSigning(BankAccountSignEnum.UN_SIGN.getCode());
        }
        if (request.getBankInfo().getIsVerified() == null) {
            request.getBankInfo().setIsVerified(BankAccountVerifiedEnum.UN_VERIFIED.getCode());
        }
        if (request.getBankInfo().getStatus() == null) {
            request.getBankInfo().setStatus(BankAccountStatusEnum.NORMAL.getCode());
        }
        // 不能新增 disabled 的卡
        if (BankAccountStatusEnum.DISABLED.getCode().equals(request.getBankInfo().getStatus())) {
            throw new MaIllegalArgumentException("银行账号卡状态非法");
        }
    }*/

    /**
     * 验证更新银行卡信息
     * 
     * @param request
     */
    public static void validatorUpdateBankAccount(BankAccInfoRequest request) {
        checkBankAccInfoRequest(request);
        checkBankId(request.getBankInfo().getId().longValue());
        CommonFacadeValidator.checkMemberId(request.getBankInfo().getMemberId(), true);
        validateBankAccount(request.getBankInfo(), false);
    }

    /**
     * 验证
     * 
     * @param request
     */
    /*public static Long validator(BankAccRemoveRequest request) {
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        return checkBankId(request.getBankcardId());
    }*/

    private static void validateBankAccount(MemberTrBankAccount request, boolean isMust) {

        if (isMust && StringUtil.isEmpty(request.getBankId())) {
            throw new MaIllegalArgumentException("银行编号未提供");
        }
        if(isMust && StringUtil.isEmpty(request.getBankAccountNo())){
        	throw new MaIllegalArgumentException("银行卡号未提供");
        }
        if (Utils.getByteLen(request.getBankId()) > FieldLength.BANK_ID) {
            throw new MaIllegalArgumentException("银行编号长度不能大于" + FieldLength.BANK_ID + "位");
        }

        if (isMust && StringUtil.isEmpty(request.getBankName())) {
            throw new MaIllegalArgumentException("银行名称未提供");
        }
        if (Utils.getByteLen(request.getBankName()) > FieldLength.BANK_NAME) {
            throw new MaIllegalArgumentException("银行名称长度不能大于" + FieldLength.BANK_NAME + "位");
        }

        if (Utils.getByteLen(request.getBankBranch()) > FieldLength.BANK_BRANCH) {
            throw new MaIllegalArgumentException("支行长度不能大于" + FieldLength.BANK_BRANCH + "位");
        }

        if (Utils.getByteLen(request.getAlias()) > FieldLength.BANK_ALIAS) {
            throw new MaIllegalArgumentException("银行卡别名不能大于" + FieldLength.BANK_ALIAS + "位");
        }

        if (Utils.getByteLen(request.getCardSkin()) > FieldLength.BANK_CARD_SKIN) {
            throw new MaIllegalArgumentException("银行卡皮肤属性不能大于" + FieldLength.BANK_CARD_SKIN + "位");
        }

        if (Utils.getByteLen(request.getBankAccountNo()) > FieldLength.BANK_ACCOUNT_NO) {
            throw new MaIllegalArgumentException("银行账号长度不能大于" + FieldLength.BANK_ACCOUNT_NO + "位");
        }


        if (Utils.getByteLen(request.getProvince()) > FieldLength.BANK_PROVINCE) {
            throw new MaIllegalArgumentException("省份长度不能大于" + FieldLength.BANK_PROVINCE + "位");
        }

        if (Utils.getByteLen(request.getCity()) > FieldLength.BANK_CITY) {
            throw new MaIllegalArgumentException("城市长度不能大于" + FieldLength.BANK_CITY + "位");
        }

        if (Utils.getByteLen(request.getBankAccountName()) > FieldLength.REAL_NAME) {
            throw new MaIllegalArgumentException("户名长度不能大于" + FieldLength.REAL_NAME + "位");
        }

        checkIsSigning(request.getIsSigning());
        checkCardType(request.getCardType());
        checkCardAttribute(request.getCardAttribute(), isMust);
        checkIsVerified(request.getIsVerified());
        checkStatus(request.getStatus());
        checkPayAttribute(request.getPayAttribute(), isMust);

        if (Utils.getByteLen(request.getSignNo()) > FieldLength.BANK_SIGN_NO) {
            throw new MaIllegalArgumentException("外部协议号长度不能大于" + FieldLength.BANK_SIGN_NO + "位");
        }
        if (Utils.getByteLen(request.getSignId()) > FieldLength.BANK_SIGN_ID) {
            throw new MaIllegalArgumentException("内部协议号长度不能大于" + FieldLength.BANK_SIGN_ID + "位");
        }

        if (StringUtil.isNotEmpty(request.getCertType())) {
            if (null == CertTypeEnum.getByCode(request.getCertType())) {
                throw new MaIllegalArgumentException("证件类型非法");
            }
        }

        if (Utils.getByteLen(request.getCertNo()) > FieldLength.BANK_CERT_NO) {
            throw new MaIllegalArgumentException("证件号长度不能大于" + FieldLength.BANK_CERT_NO + "位");
        }

        if (Utils.getByteLen(request.getCvNo()) > FieldLength.BANK_CV_NO) {
            throw new MaIllegalArgumentException("银行卡CVV2长度不能大于" + FieldLength.BANK_CV_NO + "位");
        }
        if (Utils.getByteLen(request.getCardValidDate()) > FieldLength.BANK_CARD_VALID_DATE) {
            throw new MaIllegalArgumentException("银行卡有效期长度不能大于" + FieldLength.BANK_CARD_VALID_DATE
                    + "位");
        }

        if (StringUtil.isNotEmpty(request.getMobileNo())) {
            if (!ValidateUtils.isMobile(request.getMobileNo())) {
                throw new MaIllegalArgumentException("手机格式非法");
            }
        }

        if (StringUtil.isNotEmpty(request.getIsFillCertNo())) {
            if (null == YesNoEnum.getByCode(request.getIsFillCertNo())) {
                throw new MaIllegalArgumentException("是否补全证件号码值非法");
            }
        }
        if (StringUtil.equals(YesNoEnum.YES.getCode(), request.getIsFillCertNo())
                && StringUtil.isBlank(request.getCertType())) {
            throw new MaIllegalArgumentException("需要填充证件号时证件类型不能为空字符串");
        }

        if (Utils.getByteLen(request.getChannelCode()) > FieldLength.BANK_CHANNEL_CODE) {
            throw new MaIllegalArgumentException("渠道编码长度不能大于" + FieldLength.BANK_CHANNEL_CODE + "位");
        }

        YesNoEnum yesEnum = StringUtil.isNotBlank(request.getIsFillCertNo()) ? YesNoEnum.getByCode(request.getIsFillCertNo())
                : YesNoEnum.NO;
        if (StringUtil.isNotBlank(request.getCertType()) && YesNoEnum.NO == yesEnum
                && StringUtil.isBlank(request.getCertNo())) {
            throw new MaIllegalArgumentException("证件号码非法");
        }

        if (StringUtil.isNotBlank(request.getCertNo())
                && StringUtil.isBlank(request.getCertType())) {
            throw new MaIllegalArgumentException("证件类型非法");
        }
    }

   public static void checkBankAccInfoRequest(BankAccInfoRequest request) {
        if (null == request) {
            throw new MaIllegalArgumentException("银行卡信息不能为空");
        }
        if (null == request.getBankInfo()) {
            throw new MaIllegalArgumentException("银行卡信息不能为空");
        }
    }

    public static void checkBankId(Long bankId) {
        if (null == bankId) {
            throw new MaIllegalArgumentException("银行卡Id未提供");
        }
    }

    public static Long checkBankId(String bankId) {
        if (StringUtil.isBlank(bankId)) {
            throw new MaIllegalArgumentException("银行卡Id未提供");
        }

        try {
            return Long.parseLong(bankId);
        }
        catch (NumberFormatException e) {
            throw new MaIllegalArgumentException("银行卡Id非法");
        }

    }

    public static void checkIsSigning(String isSingning) {
        if (Utils.getByteLen(isSingning) > FieldLength.BANK_IS_SIGNING) {
            throw new MaIllegalArgumentException("银行卡是否签约不能大于" + FieldLength.BANK_IS_SIGNING + "位");
        }

        if (StringUtil.isNotEmpty(isSingning) && null == BankAccountSignEnum.getByCode(isSingning)) {
            throw new MaIllegalArgumentException("银行卡是否签约标识非法");
        }
    }

    public static void checkCardType(Integer cardType) {
        if (cardType != null && BankAccountTypeEnum.getByCode(cardType) == null) {
            throw new MaIllegalArgumentException("银行账号卡类型非法");
        }
    }

    public static void checkCardAttribute(Integer CardAttribute, boolean isMust) {
        if (isMust && CardAttribute == null) {
            throw new MaIllegalArgumentException("银行账号卡属性不能为空");
        }
        if (CardAttribute != null && BankAccountAttrEnum.getByCode(CardAttribute) == null) {
            throw new MaIllegalArgumentException("银行账号卡属性非法");
        }
    }

    public static void checkIsVerified(Integer IsVerified) {
        if (IsVerified != null && BankAccountVerifiedEnum.getByCode(IsVerified) == null) {
            throw new MaIllegalArgumentException("银行账号卡认证非法");
        }
    }

    public static BankAccountStatusEnum checkStatus(Integer status) {
        if (status != null) {
            BankAccountStatusEnum statusEnum = BankAccountStatusEnum.getByCode(status);
            if (null == statusEnum) {
                throw new MaIllegalArgumentException("银行账号卡状态非法");
            }
            return statusEnum;
        }
        return null;
    }

    public static void checkPayAttribute(String payAttr, boolean isMust) {
        if (isMust && StringUtil.isEmpty(payAttr)) {
            throw new MaIllegalArgumentException("支付属性不能为空");
        }

        if (StringUtil.isNotEmpty(payAttr) && null == PayAttributeEnum.getByCode(payAttr)) {
            throw new MaIllegalArgumentException("支付属性非法");
        }
    }

    public static void checkBankAccountNo(String bankAccountNo, boolean isMust) {
        if (isMust && StringUtil.isEmpty(bankAccountNo)) {
            throw new MaIllegalArgumentException("银行账号不能为空");
        }

        if (Utils.getByteLen(bankAccountNo) > FieldLength.BANK_ACCOUNT_NO) {
            throw new MaIllegalArgumentException("银行账号长度不能大于" + FieldLength.BANK_ACCOUNT_NO + "位");
        }
    }

    /*public static void validator(BankAccUnbindRequest request) {
        if (StringUtil.isBlank(request.getSignNum()) && StringUtil.isBlank(request.getSignId())) {
            throw new MaIllegalArgumentException("外部协议号和内部协议号不能同时为空");
        }
        if (Utils.getByteLen(request.getSignNum()) > FieldLength.BANK_SIGN_NO) {
            throw new MaIllegalArgumentException("外部协议号长度不能大于" + FieldLength.BANK_SIGN_NO + "位");
        }
        if (Utils.getByteLen(request.getSignId()) > FieldLength.BANK_SIGN_ID) {
            throw new MaIllegalArgumentException("内部协议号长度不能大于" + FieldLength.BANK_SIGN_ID + "位");
        }
        if (StringUtil.isBlank(request.getChannelCode())) {
            throw new MaIllegalArgumentException("渠道编码不能为空");
        }
        if (Utils.getByteLen(request.getChannelCode()) > FieldLength.BANK_CHANNEL_CODE) {
            throw new MaIllegalArgumentException("渠道编码长度不能大于" + FieldLength.BANK_CHANNEL_CODE + "位");
        }
    }
*/
    /*public static void validator(BankAccDetailRequest request) {
        if (StringUtil.isBlank(request.getSignNum()) && StringUtil.isBlank(request.getSignId())) {
            throw new MaIllegalArgumentException("外部协议号和内部协议号不能同时为空");
        }
        if (Utils.getByteLen(request.getSignNum()) > FieldLength.BANK_SIGN_NO) {
            throw new MaIllegalArgumentException("外部协议号长度不能大于" + FieldLength.BANK_SIGN_NO + "位");
        }
        if (Utils.getByteLen(request.getSignId()) > FieldLength.BANK_SIGN_ID) {
            throw new MaIllegalArgumentException("内部协议号长度不能大于" + FieldLength.BANK_SIGN_ID + "位");
        }
//        if (StringUtil.isBlank(request.getChannelCode())) {
//            throw new MaIllegalArgumentException("渠道编码不能为空");
//        }
//        if (Utils.getByteLen(request.getChannelCode()) > FieldLength.BANK_CHANNEL_CODE) {
//            throw new MaIllegalArgumentException("渠道编码长度不能大于" + FieldLength.BANK_CHANNEL_CODE + "位");
//        }
    }*/
    
    public static void checkSignNum(String signNum){
        if (StringUtil.isBlank(signNum)) {
            throw new MaIllegalArgumentException("外部协议号不能为空");
        }
        if (Utils.getByteLen(signNum) > FieldLength.BANK_SIGN_NO) {
            throw new MaIllegalArgumentException("外部协议号长度不能大于" + FieldLength.BANK_SIGN_NO + "位");
        }
    }
    

//    public static void validator(BankChannelRequest request) {
//        if (StringUtil.isBlank(request.getChannelCode())) {
//            throw new MaIllegalArgumentException("渠道编码不能为空");
//        }
//        if (Utils.getByteLen(request.getChannelCode()) > FieldLength.BANK_CHANNEL_CODE) {
//            throw new MaIllegalArgumentException("渠道编码长度不能大于" + FieldLength.BANK_CHANNEL_CODE + "位");
//        }
//        if (StringUtil.isEmpty(request.getBankCode())) {
//            throw new MaIllegalArgumentException("银行编号未提供");
//        }
//        if (Utils.getByteLen(request.getBankCode()) > FieldLength.BANK_ID) {
//            throw new MaIllegalArgumentException("银行编号长度不能大于" + FieldLength.BANK_ID + "位");
//        }
//        if (StringUtil.isEmpty(request.getBankAccountNum())) {
//            throw new MaIllegalArgumentException("银行账号不能为空");
//        }
//
//        if (Utils.getByteLen(request.getBankAccountNum()) > FieldLength.BANK_ACCOUNT_NO) {
//            throw new MaIllegalArgumentException("银行账号长度不能大于" + FieldLength.BANK_ACCOUNT_NO + "位");
//        }
//        
//    }
}

/**
 * 
 */
package com.yiran.member.validator;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.FieldLength;
import com.yiran.member.domain.MemberTmMerchant;
import com.yiran.member.enums.MerchantStatusEnum;
import com.yiran.member.enums.MerchantTypeEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.utils.Utils;

/**
 * <p>MerchantFacade 入参验证</p>
 */
public class MerchantFacadeValidator {

    /**
     * 新增商户时验证请求参数
     * @param request
     */
    /*public static void validator(MerchantAddRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("商户信息请求未提供");
        }
        validatorMerchantId(request.getMerchantId(), false);
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        validatorMerchantType(request.getMerchantType(), true);
        validatorMerchantName(request.getMerchantName(), true);
        validatorMerchantStatus(request.getMerchantStatus(), false);
    }
*/
    /**
     * 新增商户时验证请求参数
     * @param request
     */
    public static void validator(MemberTmMerchant info) {
        if (info == null) {
            throw new MaIllegalArgumentException("商户信息请求未提供");
        }
        validatorMerchantId(info.getMerchantId(), false);
        CommonFacadeValidator.checkMemberId(info.getMemberId(), false);
        validatorMerchantType(info.getMerchantType().longValue(), true);
        validatorMerchantName(info.getMerchantName(), true);
        validatorMerchantStatus(info.getStatus().longValue(), false);
    }
    /**
     * 查询商户时验证请求参数
     * @param request
     */
    /*public static void validator(MerchantIdQueryRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询商户信息请求未提供");
        }
        
        validatorMerchantId(request.getMerchantId(), true);
    }*/
    
    /**
     * 查询商户时验证请求参数
     * @param request
     */
   /* public static void validator(MerchantQueryRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询商户信息请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
    }*/

    /*
     * 
     */
    private static void validatorMerchantId(String merchantId, boolean isMust) {
        if (isMust && StringUtil.isBlank(merchantId)) {
            throw new MaIllegalArgumentException("商户编号不能为空");
        } 
        if (Utils.getByteLen(merchantId) > FieldLength.MERCHANT_ID) {
            throw new MaIllegalArgumentException("商户编号长度不能超过" + FieldLength.MERCHANT_ID + "位");
        }
    }

    /*
     * 
     */
    private static void validatorMerchantType(Long merchantType, boolean isMust) {
        if (isMust && merchantType == null) {
            throw new MaIllegalArgumentException("商户类型未提供");
        } 
        if (merchantType != null && MerchantTypeEnum.getByCode(merchantType) == null) {
            throw new MaIllegalArgumentException("商户类型错误");
        }
    }

    /*
     * 
     */
    private static void validatorMerchantName(String merchantName, boolean isMust) {
        if (isMust && StringUtil.isEmpty(merchantName)) {
            throw new MaIllegalArgumentException("商户名称不能为空");
        }
        if (Utils.getByteLen(merchantName) > FieldLength.MERCHANT_NAME) {
            throw new MaIllegalArgumentException("商户名称长度不能超过" + FieldLength.MERCHANT_NAME + "位");
        }
    }
    
    private static void validatorMerchantStatus(Long status , boolean isMust){
        if(isMust && status == null){
            throw new MaIllegalArgumentException("商户状态不能为空");
        }
        if(status != null && MerchantStatusEnum.getByCode(status) == null){
            throw new MaIllegalArgumentException("商户状态非法");
        }
    }
    
    /**
     * 
     * @param request
     */
    /*public static void validator(MerchantUpdateRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("修改商户信息请求未提供");
        }
        validatorMerchantId(request.getMerchantId(), true);
        if (request.getMerchantType() == null && StringUtil.isEmpty(request.getMerchantName())
            && request.getMerchantStatus() == null) {
            throw new MaIllegalArgumentException("修改商户信息未提供，至少需要提交一项需要修改的信息");
        }
        validatorMerchantType(request.getMerchantType(), false);
        validatorMerchantName(request.getMerchantName(), false);
        validatorMerchantStatus(request.getMerchantStatus(), false);
    }*/

}

/**
 * 
 */
package com.yiran.member.domain;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.FieldLength;
import com.yiran.member.enums.VerifyStatusEnum;
import com.yiran.member.enums.VerifyTypeEncryptMappingEnum;
import com.yiran.member.enums.VerifyTypeEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.request.VerifyBindQueryRequest;
import com.yiran.member.request.VerifyInputRequest;
import com.yiran.member.request.VerifyQueryRequest;
import com.yiran.member.utils.Utils;
import com.yiran.member.utils.ValidateUtils;
import com.yiran.member.utils.VerifyDomainUtil;
import com.yiran.member.validator.CommonFacadeValidator;

/**
 * <p>认证接口请求验证</p>
 */
public class VerifyFacadeValidator {

    /**
     * 新增认证信息时验证请求参数
     * @param request
     */
    public static void validatorCreate(VerifyInputRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("创建认证请求未提供");
        }
        VerifyInfo verify = request.getVerifyInfo();
        if (verify == null) {
            throw new MaIllegalArgumentException("认证信息未提供");
        }
        
        CommonFacadeValidator.checkMemberId(verify.getMemberId(), true);
        
        validatorCreateVerify(verify);
    }
    
    public static void validatorCreateVerify(VerifyInfo verify){
        validatorVerifyEntityAndType(verify.getVerifyEntity(), verify.getVerifyType(), true);
        
        validatorVerifyEntityAndTypeFormat(verify.getVerifyEntity(), verify.getVerifyType());

        validatorImgPath(verify.getImgPath(), false);

        validatorStatus(verify.getStatus(), false);

        validatorChannel(verify.getChannel(), false);
    }

    /**
     * 修改认证信息时验证请求参数
     * @param request
     */
    public static void validatorUpdate(VerifyInputRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("修改认证请求未提供");
        }
        VerifyInfo verify = request.getVerifyInfo();
        if (verify == null) {
            throw new MaIllegalArgumentException("认证信息未提供");
        }
        validatorVerifyId(verify.getVerifyId());
        
        CommonFacadeValidator.checkMemberId(verify.getMemberId(), false);

        validatorVerifyEntityAndType(verify.getVerifyEntity(), verify.getVerifyType(), false);
        
        validatorVerifyEntityAndTypeFormat(verify.getVerifyEntity(), verify.getVerifyType());

        validatorImgPath(verify.getImgPath(), false);

        validatorStatus(verify.getStatus(), false);

        validatorChannel(verify.getChannel(), false);
    }
    
    /**
     * 验证认证id
     * @param verifyId
     */
    public static void validatorVerifyId(Long verifyId) {
        if (verifyId == null) {
            throw new MaIllegalArgumentException("认证ID未提供");
        }
    }
    
    /**
     * 验证认证查询请求对象
     * @param request
     */
    public static void validator(VerifyQueryRequest request){
        if (request == null) {
            throw new MaIllegalArgumentException("查询认证请求未提供");
        }
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
    }
    
    /**
     * 验证认证成功查询请求对象
     * @param request
     */
    public static void validator(VerifyBindQueryRequest request) {
        if (request == null) {
            throw new MaIllegalArgumentException("查询认证成功的认证信息请求未提供");
        }
        validatorVerifyEntityAndType(request.getVerifyEntity(),request.getVerifyType(),true);
    }

    private static void validatorVerifyEntityAndType(String verifyEntity, Integer verifyType,
                                                     boolean isMust) {
        if (isMust && StringUtil.isEmpty(verifyEntity)) {
            throw new MaIllegalArgumentException("认证实体未提供");
        } else {
            //如果修改时提供了认证类型必须提供认证实体
            if (verifyType != null && StringUtil.isEmpty(verifyEntity)) {
                throw new MaIllegalArgumentException("认证实体未提供");
            }
        }

        if (Utils.getByteLen(verifyEntity) > FieldLength.VERIFY_ENTITY) {
            throw new MaIllegalArgumentException("认证实体长度不能大于" + FieldLength.VERIFY_ENTITY + "位");
        }

        if (isMust && verifyType == null) {
            throw new MaIllegalArgumentException("认证类型未提供");
        } else {
            //如果修改时提供了认证实体必须提供类型
            if (StringUtil.isNotEmpty(verifyEntity) && verifyType == null) {
                throw new MaIllegalArgumentException("认证类型未提供");
            }
        }

    }
    
    private static void validatorVerifyEntityAndTypeFormat(String verifyEntity, Integer verifyType){
      //验证实体的格式
        if (verifyType != null && StringUtil.isNotEmpty(verifyEntity)) {
            if (verifyType == VerifyTypeEnum.CELL_PHONE.getCode()) {
            	verifyEntity = VerifyDomainUtil.replaceHrySign(verifyEntity);
                if (!ValidateUtils.isMobile(verifyEntity)) {
                    throw new MaIllegalArgumentException("手机格式非法" + verifyEntity);
                }
            } else if (verifyType == VerifyTypeEnum.EMAIL.getCode()) {
                if (!ValidateUtils.isMail(verifyEntity)) {
                    throw new MaIllegalArgumentException("邮箱格式非法:" + verifyEntity);
                }
            } else if (verifyType == VerifyTypeEncryptMappingEnum.ID_CARD.getCode()) {
////                 由于港澳台的身份证，暂时不做校验，
//                if (!ValidateUtils.isIdCard(verifyEntity)) {
//                    throw new MaIllegalArgumentException("身份证格式非法:" + verifyEntity);
//                }
            }
        }
    }

    private static void validatorImgPath(String imgPath, boolean isMust) {
        if (isMust && StringUtil.isEmpty(imgPath)) {
            throw new MaIllegalArgumentException("图片路径未提供");
        }
        if (Utils.getByteLen(imgPath) > FieldLength.IMG_PATH) {
            throw new MaIllegalArgumentException("图片路径长度不能大于" + FieldLength.IMG_PATH + "位");
        }

    }

    private static void validatorStatus(Integer status, boolean isMust) {
        if (isMust && status == null) {
            throw new MaIllegalArgumentException("认证状态未提供");
        }
        if (status != null) {
            VerifyStatusEnum statusEnum = VerifyStatusEnum.getByCode(status);
            if (statusEnum == null || VerifyStatusEnum.INVALID == statusEnum) {
                throw new MaIllegalArgumentException("认证状态非法:" + status);
            }
        }
    }

    private static void validatorChannel(Integer channel, boolean isMust) {
        if (isMust && channel == null) {
            throw new MaIllegalArgumentException("认证渠道未提供");
        }
    }

    

}

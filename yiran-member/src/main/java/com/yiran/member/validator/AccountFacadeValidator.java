package com.yiran.member.validator;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.FieldLength;
import com.yiran.member.enums.ActivateStatusEnum;
import com.yiran.member.enums.FrozenStatusEnum;
import com.yiran.member.enums.LifeCycleStatusEnum;
import com.yiran.member.enums.OrderModeEnum;
import com.yiran.member.enums.TxnTypeEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.request.AccBalanceListRequest;
import com.yiran.member.request.AccRelationRequest;
import com.yiran.member.request.AccountRequest;
import com.yiran.member.request.GetBalanceListRequestParam;
import com.yiran.member.request.OpenAccountRequest;
import com.yiran.member.request.QueryBasicAccountRequest;
import com.yiran.member.request.UpdateAccountFreezeStatusRequest;
import com.yiran.member.utils.Utils;

/**
 * <p>账户类接口入参验证</p>
 */
public class AccountFacadeValidator {
    
   /* *//**
     * 验证查询会员账户余额请求参数
     * @param request
     */
    public static void validator(QueryBasicAccountRequest request){
        if(StringUtil.isBlank(request.getMemberId()) && StringUtil.isBlank(request.getMemberIdentity())){
            throw new MaIllegalArgumentException("会员编号和会员标识不能同时为空");
        }
        if(StringUtil.isBlank(request.getMemberId())){
            //会员编号不输入时标识和类型必须传入
            CommonFacadeValidator.checkIdentity(request.getMemberIdentity(), true);
            CommonFacadeValidator.checkPlatFormType(request.getPlatformType(), false);
        } else {
            CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        }
    }
    
    /**
     * 验证查询会员基本户请求参数
     * @param request
     */
    public static void validator(AccountRequest request){
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        //兼容老接口accountType 可以为空
        CommonFacadeValidator.checkAccountType(request.getAccountType(),false);
    }
    
    
    /**
     * 验证查询账户余额收支明细请求参数
     * @param request
     */
    public static void validator(AccBalanceListRequest request) {
        if (StringUtil.isBlank(request.getAccountId()) && StringUtil.isBlank(request.getMemberId())) {
            throw new MaIllegalArgumentException("账户Id与会员Id不能都为空");
        }
        if (StringUtil.isBlank(request.getAccountId())
            && StringUtil.isNotBlank(request.getMemberId())) {
            if (request.getAccountType() == null) {
                throw new MaIllegalArgumentException("账户类型不能都为空");
            }
        }
        
        CommonFacadeValidator.checkMemberId(request.getMemberId(), false);
        CommonFacadeValidator.checkAccountId(request.getAccountId(), false);
        CommonFacadeValidator.checkAccountType(request.getAccountType(), false);
        GetBalanceListRequestParam param =request.getBalanceRequest();
        if (null == param) {
            throw new MaIllegalArgumentException("查询信息不能都为空");
        }
        
        if(param.getEndTime() == null ||  param.getStartTime() == null){
            throw new MaIllegalArgumentException("查询时间不能为空");
        }
        
        if(param.getStartTime().after(param.getEndTime())){
            throw new MaIllegalArgumentException("查询起始时间不能大于结束时间");
        }
        
        if(param.getPageId() == null){
            throw new MaIllegalArgumentException("查询参数页码不能为空");
        }else if(param.getPageId() <= 0 ){
            throw new MaIllegalArgumentException("查询参数页码不能小于1");
        }
        
        if(param.getPageSize() == null){
            throw new MaIllegalArgumentException("查询参数页大小不能为空");
        }else if(param.getPageSize() == null){
            throw new MaIllegalArgumentException("查询参数页大小不能小于1");
        }
        
        Integer txnType = param.getTxnType();
        if(txnType != null && TxnTypeEnum.getByCode(txnType)== null){
            throw new MaIllegalArgumentException("交易类型非法");
        }
        Integer order = param.getOrderMode();
        if(order != null && OrderModeEnum.getByCode(order)==null){
            throw new MaIllegalArgumentException("排序方式非法");
        }
    }
    
    
	/**
     * 验证查询会员账户关系请求参数
     * @param request
     */
    public static void validator(AccRelationRequest request){
        CommonFacadeValidator.checkMemberId(request.getMemberId(), false);
        CommonFacadeValidator.checkAccountId(request.getAccountId(), false);
        CommonFacadeValidator.checkAccountType(request.getAccountType(),false);
        if(StringUtil.isBlank(request.getAccountId()) && StringUtil.isBlank(request.getMemberId())){
            throw new MaIllegalArgumentException("账户Id与会员Id不能都为空");
        }
    }
    
    /**
     * 验证更新储值账户状态请求参数
     * @param request
     */
    public static void validator(UpdateAccountFreezeStatusRequest request) {
        CommonFacadeValidator.checkMemberId(request.getMemberId(), false);
        CommonFacadeValidator.checkAccountId(request.getAccountId(), false);
        CommonFacadeValidator.checkAccountType(request.getAccountType(),false);
        if (StringUtil.isBlank(request.getAccountId()) && StringUtil.isBlank(request.getMemberId())) {
            throw new MaIllegalArgumentException("账户Id与会员Id不能都为空");
        }
        if (StringUtil.isBlank(request.getAccountId())
            && StringUtil.isNotBlank(request.getMemberId())) {
            if (request.getAccountType() == null) {
                throw new MaIllegalArgumentException("账户类型不能都为空");
            }
        }
        if (request.getFreezeStatus() == null){
            throw new MaIllegalArgumentException("账户状态冻结不能为空");
        } else{
            FrozenStatusEnum frozenStatus = FrozenStatusEnum.getByCode(request.getFreezeStatus());
            if (frozenStatus == null) {
                throw new MaIllegalArgumentException("非法的账户冻结状态:" + request.getFreezeStatus());
            }
        }
    }
    
    /**
     * 创建储值账户请求验证
     * @param request
     */
    public static void validator(OpenAccountRequest request) {
        CommonFacadeValidator.checkMemberId(request.getMemberId(), true);
        CommonFacadeValidator.checkAccountType(request.getAccountType(), true);

        if (Utils.getByteLen(request.getAlias()) > FieldLength.ALIAS) {
            throw new MaIllegalArgumentException("账户名称长度不能大于" + FieldLength.ALIAS + "位");
        }
        if (request.getActivateStatus() == null) {
            throw new MaIllegalArgumentException("激活状态不能为空");
        } else {
            ActivateStatusEnum active = ActivateStatusEnum.getByCode(new Long(request.getActivateStatus()));
            if (active == null) {
                throw new MaIllegalArgumentException("激活状态标志非法");
            }
        }
    }
}

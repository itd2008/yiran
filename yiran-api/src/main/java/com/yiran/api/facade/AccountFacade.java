package com.yiran.api.facade;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiran.common.base.ResultWrapper;
import com.yiran.member.base.Response;
import com.yiran.member.request.AccBalanceListRequest;
import com.yiran.member.request.AccRelationRequest;
import com.yiran.member.request.AccountRequest;
import com.yiran.member.request.OpenAccountRequest;
import com.yiran.member.request.QueryBasicAccountRequest;
import com.yiran.member.request.UpdateAccountFreezeStatusRequest;
import com.yiran.member.response.AccBalanceListResponse;
import com.yiran.member.response.AccRelationResponse;
import com.yiran.member.response.AccountInfoResponse;
import com.yiran.member.response.AccountResponse;
import com.yiran.member.response.OpenAccountResponse;
import com.yiran.member.service.IMemberTrMemberAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 账户类接口
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/account")
@Api(value="账户类接口",description="账户类接口")
public class AccountFacade {
	private Logger        logger = LoggerFactory.getLogger(AccountFacade.class);
	@Autowired
	private IMemberTrMemberAccountService memberTrMemberAccountService;
	/** 
     * 查询会员账户关系信息
     * @param enviromentm
     * @param request
     * @return
     */
	@PostMapping("/queryAccountRelation")
    @ApiOperation(value = "查询会员账户关系信息",notes="查询会员账户关系信息")
    public ResultWrapper<Map<String,Object>>  queryAccountRelation(@RequestBody AccRelationRequest request) {

        try {
            AccRelationResponse response = memberTrMemberAccountService.queryAccountRelation( request);
            if (logger.isInfoEnabled()) {
                logger.info("查询会员账户关系信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("查询会员账户关系信息异常 : ", e);
            throw new RuntimeException("调用queryAccountRelation接口异常", e);
        }

    }
	
	/** 
    * 根据账户id 查询会员账户信息
    * @param enviromentm
    * @param accountId
    * @return
    */
	@PostMapping("/queryAccountById")
    @ApiOperation(value = "根据账户id 查询会员账户信息",notes="根据账户id 查询会员账户信息")
    public ResultWrapper<Map<String,Object>> queryAccountById(String accountId) {

        try {
            AccountInfoResponse response = memberTrMemberAccountService.queryAccountById( accountId);
            if (logger.isInfoEnabled()) {
                logger.info("根据账户id查询会员账户信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("根据账户id查询会员账户信息异常 : ", e);
            throw new RuntimeException("调用queryAccountById接口异常");
        }
    }
	
	/** 
    * 根据会员号和账户类型查询账户信息
    * @param enviromentm
    * @param request
    * @return
    */
	@PostMapping("/queryAccountByMemberId")
    @ApiOperation(value = "根据会员号和账户类型查询账户信息",notes="根据会员号和账户类型查询账户信息")
    public ResultWrapper<Map<String,Object>> queryAccountByMemberId(@RequestBody AccountRequest request) {
        try {
            AccountResponse response = memberTrMemberAccountService.queryAccountByMemberId(request);
            if (logger.isInfoEnabled()) {
                logger.info("根据会员号和账户类型查询账户信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("根据会员号和账户类型查询账户信息异常 : ", e);
            throw new RuntimeException("调用queryAccountByMemberId接口异常");
        }
    }
	
	/**
     * 设置账户冻结状态
     * @param environment
     * @param request
     * @return
     */
	@PostMapping("/updateAccountFreezeStatus")
    @ApiOperation(value = "设置账户冻结状态",notes="设置账户冻结状态")
    public ResultWrapper<Map<String,Object>> updateAccountFreezeStatus(UpdateAccountFreezeStatusRequest request) {
        try {
            Response response = memberTrMemberAccountService.updateAccountFreezeStatus(request);
            if (logger.isInfoEnabled()) {
                logger.info("设置账户状态返回对象：" + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("查询账户余额收支明细异常 : ", e);
            throw new RuntimeException("调用updateAccountStatus接口异常");
        }
    }
	/**
     * 开储值账户
     * @param environment
     * @param request
     * @return
     */
	@PostMapping("/openAccount")
    @ApiOperation(value = "开储值账户",notes="开储值账户")
    public ResultWrapper<Map<String,Object>> openAccount(@RequestBody OpenAccountRequest request){

        try {
            OpenAccountResponse response = memberTrMemberAccountService.openAccount(request);
            if (logger.isInfoEnabled()) {
                logger.info("开储值账户返回对象：" + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("开储值账户异常 : ", e);
            throw new RuntimeException("调用openAccount接口异常");
        }
    
    }
	
	/**
     * 查询储值基本户
     * @param environment
     * @param request
     * @return
     */
	@PostMapping("/queryBasicAccountByMember")
    @ApiOperation(value = "查询储值基本户",notes="查询储值基本户")
    public ResultWrapper<Map<String,Object>> queryBasicAccountByMember(QueryBasicAccountRequest request){
        try {
            AccountInfoResponse response = memberTrMemberAccountService.queryBasicAccountByMember(request);
            if (logger.isInfoEnabled()) {
                logger.info("查询储值基本户返回对象：" + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("查询储值基本户异常 : ", e);
            throw new RuntimeException("查询储值基本户接口异常");
        }
    
    }
	
}

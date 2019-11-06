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
import com.yiran.member.request.BankAccInfoRequest;
import com.yiran.member.request.BankAccountRequest;
import com.yiran.member.response.BankAccInfoResponse;
import com.yiran.member.response.BankAccountInfoResponse;
import com.yiran.member.response.BankAccountResponse;
import com.yiran.member.service.IMemberTrBankAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 银行卡接口
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/bankaccount")
@Api(value="银行卡接口",description="银行卡接口")
public class BankAccountFacade {
	
	private Logger             logger = LoggerFactory.getLogger(BankAccountFacade.class);
	@Autowired
	private IMemberTrBankAccountService memberTrBankAccountService;
	
	/** 
     * 查询会员绑定的银行卡信息
     * @param environment
     * @param request
     * @return
     */
	@PostMapping("/queryBankAccount")
    @ApiOperation(value = "查询会员账户关系信息",notes="查询会员账户关系信息")
    public ResultWrapper<Map<String,Object>> queryBankAccount(@RequestBody BankAccountRequest request) {
        try {
            BankAccountResponse response = memberTrBankAccountService.queryBankAccount(request);
            if (logger.isInfoEnabled()) {
                logger.info("查询会员绑定的银行卡信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("查询会员绑定的银行卡信息异常 : ", e);
            throw new RuntimeException("调用queryBankAccount接口异常", e);
        }

    }
	
	/** 
     * 查询银行卡详细信息
     * @param environment
     * @param bankId
     * @return
     */
	@PostMapping("/queryBankAccountDetail")
    @ApiOperation(value = "查询银行卡详细信息",notes="查询银行卡详细信息")
    public ResultWrapper<Map<String,Object>> queryBankAccountDetail(@RequestBody String bankAccountNo) {
        try {
            BankAccountInfoResponse response = memberTrBankAccountService.queryBankAccountDetail( bankAccountNo);
            if (logger.isInfoEnabled()) {
                logger.info("查询银行卡详细信息返回对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("查询银行卡详细信息异常 : ", e);
            throw new RuntimeException("调用queryBankAccountDetail接口异常", e);
        }
    }
	
	/** 
    * 绑定会员银行卡信息
    * @param environment
    * @param request
    * @return
    */
	@PostMapping("/addBankAccount")
    @ApiOperation(value = "绑定会员银行卡信息",notes="绑定会员银行卡信息")
    public ResultWrapper<Map<String,Object>> addBankAccount(@RequestBody BankAccInfoRequest request) {

        try {
            BankAccInfoResponse response = memberTrBankAccountService.addBankAccount(request);
            if (logger.isInfoEnabled()) {
                logger.info("绑定会员银行卡信息对象 : " + response.toString());
            }
            return ResultWrapper.ok().putData(response);
        } catch (Exception e) {
            logger.error("绑定会员银行卡异常 : ", e);
            throw new RuntimeException("调用addBankAccount接口异常", e);
        }

    }

    /** 
     * 修改会员银行卡信息
     * @param environment
     * @param request
     * @return
     */
	@PostMapping("/updateBankAccount")
    @ApiOperation(value = "修改会员银行卡信息",notes="修改会员银行卡信息")
     public ResultWrapper<Map<String,Object>>  updateBankAccount(@RequestBody BankAccInfoRequest request) {
         try {
             BankAccInfoResponse response = memberTrBankAccountService.updateBankAccount(request);
             if (logger.isInfoEnabled()) {
                 logger.info("修改会员银行卡返回对象 : " + response.toString());
             }
             return ResultWrapper.ok().putData(response);
         } catch (Exception e) {
             logger.error("修改会员银行卡异常 : ", e);
             throw new RuntimeException("调用updateBankAccount接口异常", e);
         }
     }
}

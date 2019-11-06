package com.yiran.api.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiran.member.base.Response;
import com.yiran.member.request.LoginPwdRequest;
import com.yiran.member.request.OperatorLoginPwdByIdRequest;
import com.yiran.member.request.OperatorLoginPwdLockRequest;
import com.yiran.member.request.OperatorLoginPwdRequest;
import com.yiran.member.request.PersonalLoginPwdRequest;
import com.yiran.member.response.LoginPwdResponse;
import com.yiran.member.service.IMemberTrPasswordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 登陆密码类
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/loginPwd")
@Api(value=" 登陆密码类接口",description=" 登陆密码类接口")
public class LoginPwdFacade {

	private Logger logger = LoggerFactory.getLogger(LoginPwdFacade.class); 
	@Autowired
	private IMemberTrPasswordService memberTrPasswordService;
	/**
     * 设置操作员的登录密码,如果登录密码被锁定，应先解锁再设置密码。
     * @param request
     * @return
     */
	@PostMapping("/setLoginPwd")
    @ApiOperation(value = "设置操作员的登录密码",notes="设置操作员的登录密码,如果登录密码被锁定，应先解锁再设置密码。")
    public Response setLoginPwd(LoginPwdRequest request) {
        try {
            Response response = memberTrPasswordService.setLoginPwd(request);
            if (logger.isInfoEnabled()) {
                logger.info("设置操作员的登录密码返回对象 : " + response.toString());
            }
            return response;
        } catch (Exception e) {
            logger.error("设置操作员的登录密码异常 : ", e);
            throw new RuntimeException("调用setLoginPwd接口异常");
        }

    }
	
	/**
     * 验证操作员登陆密码，本接口只适用企业会员，个人商户
     * @param request
     * @return
     */
	@PostMapping("/checkOperatorLoginPwd")
    @ApiOperation(value = "验证操作员登陆密码，本接口只适用企业会员，个人商户",notes="验证操作员登陆密码，本接口只适用企业会员，个人商户")
    public LoginPwdResponse checkOperatorLoginPwd(OperatorLoginPwdRequest request) {
        try {
            LoginPwdResponse response = memberTrPasswordService.checkOperatorLoginPwd(request);
            if (logger.isInfoEnabled()) {
                logger.info("验证操作员登陆密码返回对象 : " + response.toString());
            }
            return response;
        } catch (Exception e) {
            logger.error("验证操作员登陆密码异常 : ", e);
            throw new RuntimeException("调用checkOperatorLoginPwd接口异常");
        }

    }
	
	 /**
     * 验证操作员登陆密码，本接口只适用企业会员，个人商户
     * @param request
     * @return
     */
	@PostMapping("/checkOperatorLoginPwdById")
    @ApiOperation(value = "验证操作员登陆密码，本接口只适用企业会员，个人商户",notes="验证操作员登陆密码，本接口只适用企业会员，个人商户")
    public LoginPwdResponse checkOperatorLoginPwdById(OperatorLoginPwdByIdRequest request) {
        try {
            LoginPwdResponse response = memberTrPasswordService.checkOperatorLoginPwdById(request);
            if (logger.isInfoEnabled()) {
                logger.info("验证操作员登陆密码返回对象 : " + response.toString());
            }
            return response;
        } catch (Exception e) {
            logger.error("验证操作员登陆密码异常 : ", e);
            throw new RuntimeException("调用checkOperatorLoginPwd接口异常");
        }

    }
	
	/**
     * 验证个人会员登陆密码
     * @param request
     * @return
     */
    public LoginPwdResponse checkPersonalLoginPwd(PersonalLoginPwdRequest request) {
        try {
            LoginPwdResponse response = memberTrPasswordService.checkPersonalLoginPwd(request);
            if (logger.isInfoEnabled()) {
                logger.info("验证个人会员登陆密码返回对象 : " + response.toString());
            }
            return response;
        } catch (Exception e) {
            logger.error("验证个人会员登陆密码异常 : ", e);
            throw new RuntimeException("调用checkPersonalLoginPwd接口异常");
        }

    }
       
	
}

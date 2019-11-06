package com.yiran.message.service;

import com.yiran.common.base.AjaxResult;
import com.yiran.message.domain.SendAuthCodeRequest;

/**
 * 短息发送和验证服务
 * @author pandaa
 *
 */
public interface ISmsSendService {
	/**
	 * 发送验证码
	 * @param request
	 * @return
	 */
	public AjaxResult sendAuthCode(SendAuthCodeRequest request); 
	/**
	 * 验证手机号和验证码
	 * @param request
	 * @return
	 */
	public AjaxResult verifyMobileAuthCode(SendAuthCodeRequest request);

}

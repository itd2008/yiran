package com.yiran.api.facade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

/**
 * 支付密码
 * @author pandaa
 *
 */
@RestController
@RequestMapping("/api/yiran/payPwd")
@Api(value="支付密码接口",description="支付密码接口")
public class PayPwdFacade {

	private Logger             logger = LoggerFactory.getLogger(PayPwdFacade.class);
	
	
}

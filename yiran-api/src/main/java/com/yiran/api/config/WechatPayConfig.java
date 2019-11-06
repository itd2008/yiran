package com.yiran.api.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.yiran.pay.sdk.service.impl.BestPayServiceImpl;

/**
 * 支付配置
 */
@Component
public class WechatPayConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(WechatPayConfig.class);

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        return bestPayService;
    }

}

package com.yiran.framework.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.yiran.license.config.LicenseConfig;
import org.yiran.license.use.LicenseVerify;
import org.yiran.license.use.LicenseVerifyParam;

import com.alibaba.fastjson.JSON;

/**
 * 在项目启动时安装证书
 *
 * @date 2018/4/24
 * @since 1.0.0
 */
@Component
@Order(value=2)
public class LicenseCheckListener implements CommandLineRunner {
    private static Logger logger = LogManager.getLogger(LicenseCheckListener.class);

    /**
     * 证书subject
     */
    @Value("${license.subject}")
    private String subject;

    /**
     * 公钥别称
     */
    @Value("${license.publicAlias}")
    private String publicAlias;

    /**
     * 访问公钥库的密码
     */
    @Value("${license.storePass}")
    private String storePass;

    /**
     * 证书生成路径
     */
    @Value("${license.licensePath}")
    private String licensePath;

    /**
     * 密钥库存储路径
     */
    @Value("${license.publicKeysStorePath}")
    private String publicKeysStorePath;   
    
    
	@Override
	public void run(String... args) throws Exception {
		
		if(StringUtils.isNotBlank(licensePath)){
            logger.info("++++++++ 开始安装证书 ++++++++");

            LicenseVerifyParam param = new LicenseVerifyParam();
            param.setSubject(subject);
            param.setPublicAlias(publicAlias);
            param.setStorePass(storePass);
            param.setLicensePath(licensePath);
            param.setPublicKeysStorePath(publicKeysStorePath);
            logger.info("安装证书 参数：{}",JSON.toJSONString(param));
            LicenseVerify licenseVerify = new LicenseVerify();
            //安装证书
            licenseVerify.install(param);
            
            logger.info("++++++++ 证书安装结束 ++++++++");
        }
		
	}
}

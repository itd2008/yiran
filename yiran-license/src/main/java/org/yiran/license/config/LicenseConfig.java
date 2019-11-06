package org.yiran.license.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LicenseConfig {
	 /**
     * 证书subject
     */
    @Value("${license.subject}")
    public String subject;

    /**
     * 公钥别称
     */
    @Value("${license.publicAlias}")
    public String publicAlias;

    /**
     * 访问公钥库的密码
     */
    @Value("${license.storePass}")
    public String storePass;

    /**
     * 证书生成路径
     */
    @Value("${license.licensePath}")
    public String licensePath;

    /**
     * 密钥库存储路径
     */
    @Value("${license.publicKeysStorePath}")
    public String publicKeysStorePath;

    
}

package com.yiran.message.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 系统邮件相关配置
 */
@Configuration
public class EmailConfig {
	
	/** 缺省的发送方地址 */
	@Value("${email.sysFrom}")
	public String sysFrom;
	
	/** 缺省的发送方地址别名 */
	@Value("${email.alias}")
	public String alias;
	
	/** SMTP服务器名称  */
	@Value("${email.smtp}")
	public String smtp;
	
	/** SMTP端口  */
	@Value("${email.smtpPort}")
	public int smtpPort;
	
	/** 是否使用SSL  */
	@Value("${email.ssl}")
	public boolean ssl;
	
	/** SSL端口  */
	@Value("${email.sslPort}")
	public String sslPort;
	
	/** 认证用户名  */
	@Value("${email.loginName}")
	public String loginName;
	
	/** 认证用户密码  */
	@Value("${email.password}")
	public String password;
}

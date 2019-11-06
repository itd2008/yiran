package com.yiran.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.yiran.framework.interceptor.LicenseCheckInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * MVC配置
 *
 */
@Configuration
public class WebMvcConfig  implements WebMvcConfigurer {
	private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LicenseCheckInterceptor licenseCheckInterceptor;
    @Autowired
    private JwtApiInterceptor jwtApiInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	List<String> patterns = new ArrayList<String>();
    	//TODO:这里添加要证书验证的路径
    	//patterns.add("/login");
    	//patterns.add("/index");
    	patterns.add("/activiti/task/*");
    	//patterns.add("/public/weixin/*");
        registry.addInterceptor(licenseCheckInterceptor)
        .addPathPatterns(patterns);
        
        //TODO: 添加token拦截  暂时关闭
        /*registry.addInterceptor(jwtApiInterceptor)
        .addPathPatterns("/api/yiran/**")
        .excludePathPatterns("/api/yiran/loginUser");*/
        logger.info("Register FirstIndexInterceptor and SecondIndexInterceptor onto InterceptorRegistry");
    }
    
    /**
     * 异步线程池
     * @return
     */
    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);
        taskExecutor.setMaxPoolSize(20);
        taskExecutor.setQueueCapacity(20);
        taskExecutor.setThreadNamePrefix("webAsyncTask-");
        return taskExecutor;
    }

}
package com.yiran.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.yiran.common.config.Global;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2的接口配置
 * 
 * @author yiran
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    /**
     * 创建API
     */
    @Bean
    public Docket createRestApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                // 详细定制
                .apiInfo(apiInfo())
                .select()
                // 指定当前包路径
                .apis(RequestHandlerSelectors.basePackage("com.yiran.api.facade"))
                // 扫描所有 @Api注解类下的@ApiOperation注解方法
                //.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 添加摘要信息
     */
    private ApiInfo apiInfo()
    {
        // 用ApiInfoBuilder进行定制
        return new ApiInfoBuilder()
                .title("依然快速开发平台_接口文档")
                .description("依然快速开发平台接口,具体包括会员管理模块,订单模块,支付模块,对账模块...")
                .contact(new Contact(Global.getName(), null, null))
                .version("版本号:" + Global.getVersion())
                .build();
    }
}

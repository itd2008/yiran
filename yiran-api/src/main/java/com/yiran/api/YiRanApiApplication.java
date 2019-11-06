package com.yiran.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 启动程序
 * 
 * @author yiran
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class  })
@MapperScan("com.yiran.*.mapper")
@ComponentScan("com.yiran.*")
public class YiRanApiApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YiRanApiApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  依然快速开发平台API接口系统启动成功   ლ(´ڡ`ლ)ﾞ  ");
    }
}
package com.yiran;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 启动程序
 * @author yiran
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class,SecurityAutoConfiguration.class  })
@MapperScan("com.yiran.*.mapper")
@EnableRabbit
public class YiRanApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(YiRanApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  依然快速开发平台启动成功   ლ(´ڡ`ლ)");
    }
}
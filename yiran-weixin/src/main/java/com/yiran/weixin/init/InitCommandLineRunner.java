package com.yiran.weixin.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.yiran.weixin.kit.WeixinBasicKit;


/**
 * 项目启动初始化数据
 * @author pandaa
 *
 */
@Component
@Order(value=1)
public class InitCommandLineRunner implements CommandLineRunner{
	private static final Logger logger = LoggerFactory.getLogger(InitCommandLineRunner.class);

	@Override
    public void run(String... var1) throws Exception{
		logger.info(">>>>>>>>>>>>>>>服务启动执行，初始化相关数据<<<<<<<<<<<<<");
		WeixinBasicKit.setWeixinContext();
        logger.info(">>>>>>>>>>>>>>>服务启动执行，初始化相关数据完成<<<<<<<<<<<<<");
    }

    
}

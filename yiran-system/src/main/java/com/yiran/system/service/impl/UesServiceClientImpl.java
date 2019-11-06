package com.yiran.system.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.common.enums.EncryptType;
import com.yiran.common.exception.base.BaseException;
import com.yiran.common.utils.encrypt.AESEncryptUtil;
import com.yiran.common.utils.encrypt.DESEncryptUtil;
import com.yiran.system.domain.UesEnData;
import com.yiran.system.service.IUesEnDataService;
import com.yiran.system.service.UesServiceClient;

import cn.hutool.core.util.RandomUtil;
/**
 * UES客户端
 * @author pandaa
 *
 */
@Service
public class UesServiceClientImpl implements UesServiceClient {

	private Logger    logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUesEnDataService uesEnDataService;
	
	@Override
	public String encryptData(String plainText,EncryptType encryptType) throws Exception {
		if (StringUtils.isBlank(plainText)) {
            return null;
        }
		try {
			//远程调用计时开始
	        long beginTime = System.currentTimeMillis();
			//随机生成ticket票据
			StringBuffer sb = new StringBuffer("P");	
			sb.append(RandomUtil.randomNumbers(9));
			//生成随机加密密码
			String password = RandomUtil.randomString(32);
			//加密获取密文
			String encryptData = null;
			switch (encryptType) {
			case DES:
				encryptData = DESEncryptUtil.encrypt(plainText, password);
				break;
			case AES:
				encryptData = AESEncryptUtil.encrypt(plainText, password);
				break;
			}
			
			UesEnData uesEnData = new UesEnData();
			uesEnData.setTicket(sb.toString());
			uesEnData.setEncryptKey(password);
			uesEnData.setEncryptData(encryptData);
			uesEnData.setVersion("V10001");
			uesEnData.setEncryptType(encryptType.name());
			//保存到数据库
			uesEnDataService.insertUesEnData(uesEnData);
			//远程调用计时结束
            long consumeTime = System.currentTimeMillis() - beginTime;
            //log远程调用耗时和响应
            logger.info("远程调用：{} 耗时: {} (ms)", new Object[] {"uesServiceClient#encryptData", consumeTime });
			return sb.toString();
		} catch (Exception e) {
			logger.error("调用ues 加密出错", e);
            throw new RuntimeException(e);
		}
	}
	
	@Override
	public String getDataByTicket(String ticket,EncryptType encryptType) {
		if (StringUtils.isBlank(ticket)) {
            return null;
        }
		try {
			//原文
			String data = null;
			//远程调用计时开始
	        long beginTime = System.currentTimeMillis();
			//根据ticket票据获取加密对象
	        UesEnData uesEnData = uesEnDataService.selectUesEnDataByTicket(ticket);
	        if(uesEnData == null){
	        	return "根据ticket票据没有获取到加密数据，请确认票据是否正确";
	        }
			//获取加密密码
			String password = uesEnData.getEncryptKey();
			//获取加密密文
			String encryptData = uesEnData.getEncryptData();
			switch (encryptType) {
			case DES:
				data = DESEncryptUtil.decrypt(encryptData, password);
				break;
			case AES:
				data = AESEncryptUtil.decrypt(encryptData, password);
				break;
			}
			//远程调用计时结束
            long consumeTime = System.currentTimeMillis() - beginTime;
            //log远程调用耗时和响应
            logger.info("远程调用：{} 耗时: {} (ms)", new Object[] {"uesServiceClient#getDataByTicket", consumeTime });
			return data;
		} catch (Exception e) {
			logger.error("调用ues 加密出错", e);
            throw new RuntimeException(e);
		}
	}

	

}

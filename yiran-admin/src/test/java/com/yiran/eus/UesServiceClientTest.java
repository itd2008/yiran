/*package com.yiran.eus;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.common.enums.EncryptType;
import com.yiran.system.domain.SysOperLog;
import com.yiran.system.service.UesServiceClient;

public class UesServiceClientTest extends BaseJunit{
	
	@Autowired
	private UesServiceClient uesServiceClient;
	*//**
	 * 加密
	 *//*
	//@Test
	public void encryptData(){
		try {
			String ticket = uesServiceClient.encryptData("许盼", EncryptType.AES);
			System.out.println("加密后的票据："+ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	*//**
	 * 解密
	 *//*
	//@Test
	public void getDataByTicket(){
		
		//P441288844
		String data = uesServiceClient.getDataByTicket("P441288844", EncryptType.AES);
		System.out.println(""+data);
		
	}

}
*/
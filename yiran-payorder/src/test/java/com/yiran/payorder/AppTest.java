package com.yiran.payorder;

import java.util.Date;

import com.yiran.paychannel.enums.BizType;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

/**
 * Unit test for simple App.
 */
public class AppTest 
    
{
    public static void main(String[] args) {
		
    	//BizType bizType = BizType.FUNDIN;
    	//System.out.println(bizType.name());
    	//String format = DateUtil.format(new Date(), "yyyyMMddHHmmss");
    	//System.out.println(format);
    	System.out.println(RandomUtil.randomNumbers(6));
	}
}

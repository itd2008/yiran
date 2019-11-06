package com.yiran.testmain;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.system.domain.SysOperLog;

import cn.hutool.core.lang.UUID;

public class TestMain {
	
	public static void main(String[] args) {
		/*String uuid = UUID.randomUUID().toString();
		//System.out.println(uuid.replace("-", ""));
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("name","张三");
		dataMap.put("age","20");
		dataMap.put("sex","男");
		System.out.println(JSON.toJSONString(dataMap));
		Map<String, String> map =new HashMap<String, String>();
		try {
			map = MapUtil.jsonToMap("asdadaafadskfaksfasfhajkfhahjfsasjkfhasjhfasjjadfjfa");
		} catch (Exception e) {
			System.out.println("不是JSON数据");
		}
		System.out.println(map.get("age"));*/
		
		String cardNo="6216610800003331957";
		System.out.println(cardNo.substring(6, 8));
		File file = new File("D:/opt/soa/bill/WXPAY10102/20190919_success1.txt");
		try {
			System.out.println(file.getCanonicalPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

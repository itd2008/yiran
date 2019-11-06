package com.yiran.weChat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.base.BaseJunit;
import com.yiran.payorder.domain.QueryOrderResult;
import com.yiran.payorder.facade.InstOrderProcessFacade;
import com.yiran.wechat.domain.Province;
import com.yiran.wechat.service.IBsProvinceService;

public class ProvinceTest extends BaseJunit{

	@Autowired
	private IBsProvinceService bsProvinceService;
	@Test
	public void provinceTest(){
		List<Province> list = bsProvinceService.findProvinceList();
	}
}

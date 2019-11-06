package com.yiran.weixin.conversion;

import com.yiran.weixin.entity.WUser;
import com.yiran.weixin.domain.WeixinUser;

import cn.hutool.core.util.RandomUtil;

/**
 * 对象转换类
 * @author pandaa
 *
 */
public class ConversionObject {
	
	public static WeixinUser conversionToWeixinUser (WUser wUser){
		WeixinUser weixinUser = new WeixinUser();
		//UUID生成id
		weixinUser.setId(RandomUtil.simpleUUID());
		weixinUser.setSubscribe(wUser.getSubscribe());
		weixinUser.setOpenid(wUser.getOpenid());
		weixinUser.setNickname(wUser.getNickname());
		weixinUser.setSex(wUser.getSex());
		weixinUser.setCity(wUser.getCity());
		weixinUser.setCountry(wUser.getCountry());
		weixinUser.setProvince(wUser.getProvince());
		weixinUser.setLanguage(wUser.getLanguage());
		weixinUser.setHeadimgurl(wUser.getHeadimgurl());
		weixinUser.setSubscribeTime(String.valueOf(wUser.getSubscribe_time()));
		weixinUser.setUnionid(wUser.getUnionid());
		weixinUser.setRemark(wUser.getRemark());
		weixinUser.setGroupid(String.valueOf(wUser.getGroupid()));
		return weixinUser;
	}
	
	public static void main(String[] args) {
		System.out.println(RandomUtil.simpleUUID());
	}

}

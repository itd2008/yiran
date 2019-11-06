package com.yiran.weixin.service;

import com.yiran.weixin.entity.WUser;

public interface IWUserService {
	public WUser queryByOpenid(String openid);
	public String queryOpenidByCode(String code);
}

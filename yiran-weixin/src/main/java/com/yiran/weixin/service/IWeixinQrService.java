package com.yiran.weixin.service;

import java.util.List;

import com.yiran.weixin.entity.WeixinQr;


public interface IWeixinQrService {
	public WeixinQr insert(WeixinQr wq);
	
	public void delete(String id);
	
	public WeixinQr load(String id);
	
	public void update(WeixinQr wq);
	
	public List<WeixinQr> listBaseQr(WeixinQr wq);
	
	public List<WeixinQr> listTempQr();
	
	public WeixinQr loadBySnum(int snum);
	public Integer getWeixinQrByuserId(String userId);
	public Integer getBandAgentByUid(String userId);
	//public Page<WeixinQr> findlistBaseQrPage(Page<WeixinQr> page, WeixinQr wq);
}

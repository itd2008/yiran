package com.yiran.weixin.domain;

import java.util.Date;

import com.yiran.common.annotation.Excel;
import com.yiran.common.base.BaseEntity;

/**
 * 微信基本设置表 sys_weixin_setting
 * 
 * @date 2018-07-17
 */
public class WeixinSetting extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	//微信商家openId
	public static final String KEY_WECHAT_STORE_OPENID = "WECHAT_STORE_OPENID";

	public static final String KEY_WEIXIN_MINE_LINK = "WEIXIN_MINE_LINK";
	
	//微信公众号秘钥
	public static String KEY_WEIXIN_APPSECRET="WEIXIN.APPSECRET";
	//微信公众号appID
	public static String KEY_WEIXIN_APPID="WEIXIN.APPID";
	//微信小程序秘钥
	public static String KEY_WEIXIN_SMALL_PROGRAM_APPSECRET="WEIXIN.SMALL.PROGRAM.APPSECRE";
	//微信小程序appID
	public static String KEY_WEIXIN_SMALL_PROGRAM_APPID="WEIXIN.SMALL.PROGRAM.APPIDD";
	//微信商户ID
	public static String KEY_WEIXIN_MCHID ="WEIXIN.MCHID";
	//微信商户秘钥
	public static String KEY_WEIXIN_MCHSECRETKEY ="WEIXIN.MCHSECRETKEY";
	//微信秘钥路径
	public static String KEY_WEIXIN_MCHSECRETKEYPATH ="WEIXIN.MCHSECRETKEYPATH";
	//支付通知地址
	public static String KEY_WEIXIN_NOTIFYURL = "WEIXIN.NOTIFYURL";
	//微信token
	public static String KEY_WEIXIN_WEIXIN_TOKEN="WEIXIN.WEIXIN.TOKEN";
	//微信回复URL
	public static String KEY_WEIXIN_BASE_URL="WEIXIN.BASE.URL";
	//关注微信公众号返回信息
	public static String KEY_WEIXIN_RETURN_TEXT ="WEIXIN.RETURN.TEXT";
	//获取通过code换取access_token以及OpenID API URL
	public static String KEY_WEIXIN_CODE_OPENID ="WEIXIN.CODE.OPENID";
	//REDIRECT.URI
	public static String KEY_WEIXIN_REDIRECT_URI ="REDIRECT.URI";
	
	public static String KEY_WEIXIN_TEMPLATE_LINK = "WEIXIN_TEMPLATE_LINK";
	
	public static String KEY_WEIXIN_REFUN_REDIRECT_URI ="WEIXIN.REFUN.REDIRECT.URI";
	public static String KEY_WEIXIN_REFUN_APPROVAL_REDIRECT_URI ="WEIXIN.REFUN.APPROVAL.REDIRECT.URI";
	public static String KEY_WEIXIN_SCOPE_SNSAPI_USER = "SCOPE.SNSAPI.USER";
	/** 主键 */
	 @Excel(name = "序号")
	private Integer id;
	/** 名称 */
	 @Excel(name = "名称")
	private String name;
	/** 键 */
	 @Excel(name = "键")
	private String weixinKey;
	/** 值 */
	 @Excel(name = "值")
	private String weixinValue;
	/**
	 * 应用类型 0 公众号 1小程序
	 */
	private Integer appType;
	
	/** 删除标志（0代表存在 2代表删除） */
	private String delFlag;

	
	public Integer getAppType() {
		return appType;
	}

	public void setAppType(Integer appType) {
		this.appType = appType;
	}

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：主键
	 */
	public Integer getId() 
	{
		return id;
	}
	
	/**
	 * 设置：名称
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：名称
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：键
	 */
	public void setWeixinKey(String weixinKey) 
	{
		this.weixinKey = weixinKey;
	}
	
	/**
	 * 获取：键
	 */
	public String getWeixinKey() 
	{
		return weixinKey;
	}
	
	/**
	 * 设置：值
	 */
	public void setWeixinValue(String weixinValue) 
	{
		this.weixinValue = weixinValue;
	}
	
	/**
	 * 获取：值
	 */
	public String getWeixinValue() 
	{
		return weixinValue;
	}
	
	
	/**
	 * 设置：删除标志（0代表存在 2代表删除）
	 */
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}
	
	/**
	 * 获取：删除标志（0代表存在 2代表删除）
	 */
	public String getDelFlag() 
	{
		return delFlag;
	}
	
}

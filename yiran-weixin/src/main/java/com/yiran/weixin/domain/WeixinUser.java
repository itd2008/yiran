package com.yiran.weixin.domain;

import com.yiran.common.base.BaseEntity;

/**
 * 公众号微信用户表 sys_weixin_user
 * 
 * @author yiran
 * @date 2018-08-19
 */
public class WeixinUser extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private String id;
	/** 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息，只有openid和UnionID（在该公众号绑定到了微信开放平台账号时才有）。 */
	private Integer subscribe;
	/** 用户的标识，对当前公众号唯一 */
	private String openid;
	/** 用户的昵称 */
	private String nickname;
	/** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
	private Integer sex;
	/** 用户所在城市 */
	private String city;
	/** 用户所在国家 */
	private String country;
	/** 用户所在省份 */
	private String province;
	/** 用户的语言，简体中文为zh_CN */
	private String language;
	/** 	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */
	private String headimgurl;
	/** 用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间 */
	private String subscribeTime;
	/** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制） */
	private String unionid;
	/** 公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注 */
	private String remark;
	/** 用户所在的分组ID */
	private String groupid;
	/**  */
	private String tagidList;
	/** 绑定标识 0 未绑定 1已经绑定 */
	private Integer bind;
	/** 登录名  是openId */
	private String username;
	/** 密码 */
	private String password;
	/** 名字 */
	private String name;
	/**  */
	private String phone;
	/** 地址 */
	private String address;

	/**
	 * 设置：主键
	 */
	public void setId(String id) 
	{
		this.id = id;
	}
	
	/**
	 * 获取：主键
	 */
	public String getId() 
	{
		return id;
	}
	
	/**
	 * 设置：用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息，只有openid和UnionID（在该公众号绑定到了微信开放平台账号时才有）。
	 */
	public void setSubscribe(Integer subscribe) 
	{
		this.subscribe = subscribe;
	}
	
	/**
	 * 获取：用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息，只有openid和UnionID（在该公众号绑定到了微信开放平台账号时才有）。
	 */
	public Integer getSubscribe() 
	{
		return subscribe;
	}
	
	/**
	 * 设置：用户的标识，对当前公众号唯一
	 */
	public void setOpenid(String openid) 
	{
		this.openid = openid;
	}
	
	/**
	 * 获取：用户的标识，对当前公众号唯一
	 */
	public String getOpenid() 
	{
		return openid;
	}
	
	/**
	 * 设置：用户的昵称
	 */
	public void setNickname(String nickname) 
	{
		this.nickname = nickname;
	}
	
	/**
	 * 获取：用户的昵称
	 */
	public String getNickname() 
	{
		return nickname;
	}
	
	/**
	 * 设置：用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public void setSex(Integer sex) 
	{
		this.sex = sex;
	}
	
	/**
	 * 获取：用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
	public Integer getSex() 
	{
		return sex;
	}
	
	/**
	 * 设置：用户所在城市
	 */
	public void setCity(String city) 
	{
		this.city = city;
	}
	
	/**
	 * 获取：用户所在城市
	 */
	public String getCity() 
	{
		return city;
	}
	
	/**
	 * 设置：用户所在国家
	 */
	public void setCountry(String country) 
	{
		this.country = country;
	}
	
	/**
	 * 获取：用户所在国家
	 */
	public String getCountry() 
	{
		return country;
	}
	
	/**
	 * 设置：用户所在省份
	 */
	public void setProvince(String province) 
	{
		this.province = province;
	}
	
	/**
	 * 获取：用户所在省份
	 */
	public String getProvince() 
	{
		return province;
	}
	
	/**
	 * 设置：用户的语言，简体中文为zh_CN
	 */
	public void setLanguage(String language) 
	{
		this.language = language;
	}
	
	/**
	 * 获取：用户的语言，简体中文为zh_CN
	 */
	public String getLanguage() 
	{
		return language;
	}
	
	/**
	 * 设置：	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	public void setHeadimgurl(String headimgurl) 
	{
		this.headimgurl = headimgurl;
	}
	
	/**
	 * 获取：	用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
	 */
	public String getHeadimgurl() 
	{
		return headimgurl;
	}
	
	/**
	 * 设置：用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public void setSubscribeTime(String subscribeTime) 
	{
		this.subscribeTime = subscribeTime;
	}
	
	/**
	 * 获取：用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间
	 */
	public String getSubscribeTime() 
	{
		return subscribeTime;
	}
	
	/**
	 * 设置：只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	 */
	public void setUnionid(String unionid) 
	{
		this.unionid = unionid;
	}
	
	/**
	 * 获取：只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。详见：获取用户个人信息（UnionID机制）
	 */
	public String getUnionid() 
	{
		return unionid;
	}
	
	/**
	 * 设置：公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	 */
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}
	
	/**
	 * 获取：公众号运营者对粉丝的备注，公众号运营者可在微信公众平台用户管理界面对粉丝添加备注
	 */
	public String getRemark() 
	{
		return remark;
	}
	
	/**
	 * 设置：用户所在的分组ID
	 */
	public void setGroupid(String groupid) 
	{
		this.groupid = groupid;
	}
	
	/**
	 * 获取：用户所在的分组ID
	 */
	public String getGroupid() 
	{
		return groupid;
	}
	
	/**
	 * 设置：
	 */
	public void setTagidList(String tagidList) 
	{
		this.tagidList = tagidList;
	}
	
	/**
	 * 获取：
	 */
	public String getTagidList() 
	{
		return tagidList;
	}
	
	/**
	 * 设置：绑定标识 0 未绑定 1已经绑定
	 */
	public void setBind(Integer bind) 
	{
		this.bind = bind;
	}
	
	/**
	 * 获取：绑定标识 0 未绑定 1已经绑定
	 */
	public Integer getBind() 
	{
		return bind;
	}
	
	/**
	 * 设置：登录名  是openId
	 */
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	/**
	 * 获取：登录名  是openId
	 */
	public String getUsername() 
	{
		return username;
	}
	
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	/**
	 * 获取：密码
	 */
	public String getPassword() 
	{
		return password;
	}
	
	/**
	 * 设置：名字
	 */
	public void setName(String name) 
	{
		this.name = name;
	}
	
	/**
	 * 获取：名字
	 */
	public String getName() 
	{
		return name;
	}
	
	/**
	 * 设置：
	 */
	public void setPhone(String phone) 
	{
		this.phone = phone;
	}
	
	/**
	 * 获取：
	 */
	public String getPhone() 
	{
		return phone;
	}
	
	/**
	 * 设置：地址
	 */
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	/**
	 * 获取：地址
	 */
	public String getAddress() 
	{
		return address;
	}

	@Override
	public String toString() {
		return "WeixinUser [id=" + id + ", subscribe=" + subscribe + ", openid=" + openid + ", nickname=" + nickname
				+ ", sex=" + sex + ", city=" + city + ", country=" + country + ", province=" + province + ", language="
				+ language + ", headimgurl=" + headimgurl + ", subscribeTime=" + subscribeTime + ", unionid=" + unionid
				+ ", remark=" + remark + ", groupid=" + groupid + ", tagidList=" + tagidList + ", bind=" + bind
				+ ", username=" + username + ", password=" + password + ", name=" + name + ", phone=" + phone
				+ ", address=" + address + "]";
	}
	
	
}

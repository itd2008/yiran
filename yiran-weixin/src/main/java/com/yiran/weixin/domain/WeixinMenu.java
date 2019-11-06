package com.yiran.weixin.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yiran.common.base.BaseEntity;

/**
 * 微信菜单表 sys_weixin_menu
 * 
 * @author yiran
 * @date 2018-07-18
 */
public class WeixinMenu extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 菜单ID */
	private Integer menuId;
	/** 菜单名称 */
	private String menuName;
	/** 父菜单名称 */
    private String parentName;
	/** 父菜单ID */
	private Integer parentId;
	/** 显示顺序 */
	private Integer orderNum;
	/** 请求地址 */
	private String url;
	/** 微信菜单类型 */
	private String weixinMenuType;
	/** 菜单类型（M目录 C菜单 F按钮） */
	private String menuType;
	/** 菜单状态（0显示 1隐藏） */
	private String visible;
	/** 权限标识 */
	private String perms;
	/** 菜单图标 */
	private String icon;
	/** 资源类型 */
	private String respType;
	/** 类型(view,click) */
	private String type;
	 /** 子菜单 */
    private List<WeixinMenu> children = new ArrayList<WeixinMenu>();
    private String content;
	/**
	 * 设置：菜单ID
	 */
	public void setMenuId(Integer menuId) 
	{
		this.menuId = menuId;
	}
	
	/**
	 * 获取：菜单ID
	 */
	public Integer getMenuId() 
	{
		return menuId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置：菜单名称
	 */
	public void setMenuName(String menuName) 
	{
		this.menuName = menuName;
	}
	
	/**
	 * 获取：菜单名称
	 */
	public String getMenuName() 
	{
		return menuName;
	}
	
	/**
	 * 设置：父菜单ID
	 */
	public void setParentId(Integer parentId) 
	{
		this.parentId = parentId;
	}
	
	/**
	 * 获取：父菜单ID
	 */
	public Integer getParentId() 
	{
		return parentId;
	}
	
	/**
	 * 设置：显示顺序
	 */
	public void setOrderNum(Integer orderNum) 
	{
		this.orderNum = orderNum;
	}
	
	/**
	 * 获取：显示顺序
	 */
	public Integer getOrderNum() 
	{
		return orderNum;
	}
	
	/**
	 * 设置：请求地址
	 */
	public void setUrl(String url) 
	{
		this.url = url;
	}
	
	/**
	 * 获取：请求地址
	 */
	public String getUrl() 
	{
		return url;
	}
	
	/**
	 * 设置：微信菜单类型
	 */
	public void setWeixinMenuType(String weixinMenuType) 
	{
		this.weixinMenuType = weixinMenuType;
	}
	
	/**
	 * 获取：微信菜单类型
	 */
	public String getWeixinMenuType() 
	{
		return weixinMenuType;
	}
	
	/**
	 * 设置：菜单类型（M目录 C菜单 F按钮）
	 */
	public void setMenuType(String menuType) 
	{
		this.menuType = menuType;
	}
	
	/**
	 * 获取：菜单类型（M目录 C菜单 F按钮）
	 */
	public String getMenuType() 
	{
		return menuType;
	}
	
	/**
	 * 设置：菜单状态（0显示 1隐藏）
	 */
	public void setVisible(String visible) 
	{
		this.visible = visible;
	}
	
	/**
	 * 获取：菜单状态（0显示 1隐藏）
	 */
	public String getVisible() 
	{
		return visible;
	}
	
	/**
	 * 设置：权限标识
	 */
	public void setPerms(String perms) 
	{
		this.perms = perms;
	}
	
	/**
	 * 获取：权限标识
	 */
	public String getPerms() 
	{
		return perms;
	}
	
	/**
	 * 设置：菜单图标
	 */
	public void setIcon(String icon) 
	{
		this.icon = icon;
	}
	
	/**
	 * 获取：菜单图标
	 */
	public String getIcon() 
	{
		return icon;
	}
	
	
	/**
	 * 设置：资源类型
	 */
	public void setRespType(String respType) 
	{
		this.respType = respType;
	}
	
	/**
	 * 获取：资源类型
	 */
	public String getRespType() 
	{
		return respType;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<WeixinMenu> getChildren() {
		return children;
	}

	public void setChildren(List<WeixinMenu> children) {
		this.children = children;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	
	
}

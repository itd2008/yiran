package com.yiran.weixin.service;
public interface IWMenuService {
	/**
	 * 发布微信菜单
	 * @return
	 */
	public int publishMenu();
	/**
	 * 查询已发布的菜单
	 * @return
	 */
	public String queryMenu();
}

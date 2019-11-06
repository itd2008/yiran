package com.yiran.weixin.service;

import com.yiran.system.domain.SysMenu;
import com.yiran.system.domain.SysRole;
import com.yiran.weixin.domain.WeixinMenu;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 微信菜单 服务层
 * 
 * @author yiran
 * @date 2018-07-18
 */
public interface IWeixinMenuService 
{
	/**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<WeixinMenu> selectMenusByUserId(Long userId);

    /**
     * 查询系统菜单列表
     * 
     * @return 菜单列表
     */
    public List<WeixinMenu> selectMenuList(WeixinMenu menu);

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    public List<WeixinMenu> selectMenuAll();

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Map<String, Object>> roleMenuTreeData(SysRole role);

    /**
     * 查询所有菜单信息
     * 
     * @return 菜单列表
     */
    public List<Map<String, Object>> menuTreeData();

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    public Map<String, String> selectPermsAll();

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int deleteMenuById(Long menuId);

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    public WeixinMenu selectMenuById(Long menuId);

    /**
     * 查询菜单数量
     * 
     * @param parentId 菜单父ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Long parentId);

    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    public int selectCountRoleMenuByMenuId(Long menuId);

    /**
     * 保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int saveMenu(WeixinMenu menu);

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public String checkMenuNameUnique(WeixinMenu menu);
    /**
     * 发布菜单到微信公众号
     * @return
     */
    public int publishMenu();
    /**
     * 查询公众号已经发布的菜单
     * @return
     */
	public String queryMenu();
	/**
     * 根据weixinMenuType获取count
     * @param weixinMenuType
     * @return
     */
    public String selectMenuByWeixinMenuType(String weixinMenuType);
    
    /**
     * 修改保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(WeixinMenu menu);

	
}

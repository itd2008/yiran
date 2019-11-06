package com.yiran.weixin.mapper;

import com.yiran.weixin.domain.WeixinMenu;
import java.util.List;	

/**
 * 微信菜单 数据层
 * 
 * @author yiran
 * @date 2018-07-18
 */
public interface WeixinMenuMapper 
{
	/**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    public List<WeixinMenu> selectMenusByUserId(Long userId);

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    public List<String> selectPermsByUserId(Long userId);

    /**
     * 根据角色ID查询菜单
     * 
     * @param roleId 角色ID
     * @return 菜单列表
     */
    public List<String> selectMenuTree(Long roleId);
    
    /**
     * 查询系统菜单列表
     * 
     * @return 菜单列表
     */
    public List<WeixinMenu> selectMenuList(WeixinMenu weixinMenu);

    /**
     * 查询系统所有菜单
     * 
     * @return 菜单列表
     */
    public List<WeixinMenu> selectMenuAll();

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
     * 根据weixinMenuType获取count
     * @param weixinMenuType
     * @return
     */
    public String selectMenuByWeixinMenuType(String weixinMenuType);
    
    /**
     * 查询菜单数量
     * 
     * @param parentId 菜单父ID
     * @return 结果
     */
    public int selectCountMenuByParentId(Long parentId);

    /**
     * 新增菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int insertMenu(WeixinMenu weixinMenu);
    
    /**
     * 修改菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    public int updateMenu(WeixinMenu weixinMenu);
    
    /**
     * 校验菜单名称是否唯一
     * 
     * @param menuName 菜单名称
     * @return 结果
     */
    public WeixinMenu checkMenuNameUnique(String menuName);
	
}
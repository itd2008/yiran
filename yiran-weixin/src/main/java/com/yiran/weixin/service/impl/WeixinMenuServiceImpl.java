package com.yiran.weixin.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.common.utils.StringUtils;
import com.yiran.system.domain.SysRole;
import com.yiran.system.mapper.SysRoleMenuMapper;
import com.yiran.weixin.domain.WeixinMenu;
import com.yiran.weixin.entity.WeixinFinalValue;
import com.yiran.weixin.entity.WeixinMenuDto;
import com.yiran.weixin.kit.WeixinBasicKit;
import com.yiran.weixin.mapper.WeixinMenuMapper;
import com.yiran.weixin.service.IWeixinMenuService;
import com.yiran.weixin.utils.WeiXinJsonUtil;
import com.yiran.weixin.utils.TreeUtils;
import com.yiran.common.constant.UserConstants;
import com.yiran.common.support.Convert;

/**
 * 微信菜单 服务层实现
 * 
 * @author yiran
 * @date 2018-07-18
 */
@Service
public class WeixinMenuServiceImpl implements IWeixinMenuService 
{
	private static final Logger logger = LoggerFactory.getLogger(WeixinMenuServiceImpl.class);
	public static final String PREMISSION_STRING = "perms[\"{0}\"]";

    @Autowired
    private WeixinMenuMapper weixinMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 根据用户ID查询菜单
     * 
     * @param userId 用户ID
     * @return 菜单列表
     */
    @Override
    public List<WeixinMenu> selectMenusByUserId(Long userId)
    {
        List<WeixinMenu> menus = weixinMenuMapper.selectMenusByUserId(userId);
        return TreeUtils.getWeixinMenuChildPerms(menus, 0);
    }

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    @Override
    public List<WeixinMenu> selectMenuList(WeixinMenu menu)
    {
        return weixinMenuMapper.selectMenuList(menu);
    }

    /**
     * 查询菜单集合
     * 
     * @return 所有菜单信息
     */
    @Override
    public List<WeixinMenu> selectMenuAll()
    {
        return weixinMenuMapper.selectMenuAll();
    }

    /**
     * 根据用户ID查询权限
     * 
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectPermsByUserId(Long userId)
    {
        List<String> perms = weixinMenuMapper.selectPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * 根据角色ID查询菜单
     * 
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> roleMenuTreeData(SysRole role)
    {
        Long roleId = role.getRoleId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<WeixinMenu> menuList = weixinMenuMapper.selectMenuAll();
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleMenuList = weixinMenuMapper.selectMenuTree(roleId);
            trees = getTrees(menuList, true, roleMenuList, true);
        }
        else
        {
            trees = getTrees(menuList, false, null, true);
        }
        return trees;
    }

    /**
     * 查询所有菜单
     * 
     * @param role 角色对象
     * @return 菜单列表
     */
    @Override
    public List<Map<String, Object>> menuTreeData()
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<WeixinMenu> menuList = weixinMenuMapper.selectMenuAll();
        trees = getTrees(menuList, false, null, false);
        return trees;
    }

    /**
     * 查询系统所有权限
     * 
     * @return 权限列表
     */
    @Override
    public LinkedHashMap<String, String> selectPermsAll()
    {
        LinkedHashMap<String, String> section = new LinkedHashMap<>();
        List<WeixinMenu> permissions = weixinMenuMapper.selectMenuAll();
        if (StringUtils.isNotEmpty(permissions))
        {
            for (WeixinMenu menu : permissions)
            {
                section.put(menu.getUrl(), MessageFormat.format(PREMISSION_STRING, menu.getPerms()));
            }
        }
        return section;
    }

    /**
     * 对象转菜单树
     * 
     * @param menuList 菜单列表
     * @param isCheck 是否需要选中
     * @param roleMenuList 角色已存在菜单列表
     * @param permsFlag 是否需要显示权限标识
     * @return
     */
    public List<Map<String, Object>> getTrees(List<WeixinMenu> menuList, boolean isCheck, List<String> roleMenuList,
            boolean permsFlag)
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (WeixinMenu menu : menuList)
        {
            Map<String, Object> deptMap = new HashMap<String, Object>();
            deptMap.put("id", menu.getMenuId());
            deptMap.put("pId", menu.getParentId());
            deptMap.put("name", transMenuName(menu, roleMenuList, permsFlag));
            deptMap.put("title", menu.getMenuName());
            if (isCheck)
            {
                deptMap.put("checked", roleMenuList.contains(menu.getMenuId() + menu.getPerms()));
            }
            else
            {
                deptMap.put("checked", false);
            }
            trees.add(deptMap);
        }
        return trees;
    }

    public String transMenuName(WeixinMenu menu, List<String> roleMenuList, boolean permsFlag)
    {
        StringBuffer sb = new StringBuffer();
        sb.append(menu.getMenuName());
        if (permsFlag)
        {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;" + menu.getPerms() + "</font>");
        }
        return sb.toString();
    }

    /**
     * 删除菜单管理信息
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int deleteMenuById(Long menuId)
    {
        //ShiroUtils.clearCachedAuthorizationInfo();
        return weixinMenuMapper.deleteMenuById(menuId);
    }

    /**
     * 根据菜单ID查询信息
     * 
     * @param menuId 菜单ID
     * @return 菜单信息
     */
    @Override
    public WeixinMenu selectMenuById(Long menuId)
    {
        return weixinMenuMapper.selectMenuById(menuId);
    }

    /**
     * 查询子菜单数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountMenuByParentId(Long parentId)
    {
        return weixinMenuMapper.selectCountMenuByParentId(parentId);
    }

    /**
     * 查询菜单使用数量
     * 
     * @param menuId 菜单ID
     * @return 结果
     */
    @Override
    public int selectCountRoleMenuByMenuId(Long menuId)
    {
        return sysRoleMenuMapper.selectCountRoleMenuByMenuId(menuId);
    }

    /**
     * 保存菜单信息
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public int saveMenu(WeixinMenu menu)
    {
        Integer menuId = menu.getMenuId();
        if (StringUtils.isNotNull(menuId))
        {
            //menu.setUpdateBy(ShiroUtils.getLoginName());
            //ShiroUtils.clearCachedAuthorizationInfo();
            return weixinMenuMapper.updateMenu(menu);
        }
        else
        {
        	if(menu.getType().equals("click")){
        		menu.setWeixinMenuType("KEY_"+System.currentTimeMillis());
        		menu.setRespType("1");
    		}else{
    			menu.setWeixinMenuType("");
    			menu.setRespType("0");
    		}
            //menu.setCreateBy(ShiroUtils.getLoginName());
           //ShiroUtils.clearCachedAuthorizationInfo();
            return weixinMenuMapper.insertMenu(menu);
        }
    }

    /**
     * 校验菜单名称是否唯一
     * 
     * @param menu 菜单信息
     * @return 结果
     */
    @Override
    public String checkMenuNameUnique(WeixinMenu menu)
    {
        if (menu.getMenuId() == null)
        {
            menu.setMenuId(-1);
        }
        Integer menuId = menu.getMenuId();
        WeixinMenu info = weixinMenuMapper.checkMenuNameUnique(menu.getMenuName());
        if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getMenuId())
                && info.getMenuId().longValue() != menuId.longValue())
        {
            return UserConstants.MENU_NAME_NOT_UNIQUE;
        }
        return UserConstants.MENU_NAME_UNIQUE;
    }

	@Override
	public int publishMenu() {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MENU_ADD);
		logger.info("发布菜单API接口："+url);
		List<WeixinMenuDto> wmds = generateWeixinMenuDto();
		Map<String,List<WeixinMenuDto>> maps = new HashMap<String,List<WeixinMenuDto>>();
		maps.put("button", wmds);
		String json =  WeiXinJsonUtil.getInstance().obj2json(maps);
		logger.info("发布菜单："+json);
		//除去"sub_button": [],
		json=json.replaceAll("\"sub_button\":\\[\\],", "");
		logger.info("除去sub_button: [],发布菜单："+json);
		String rel = WeixinBasicKit.sendJsonPost(url,json);
		logger.info("菜单发布返回信息："+rel);
		int flag=1;//默认成功
		if(!WeixinBasicKit.checkRequestSucc(rel)) {
			flag=0;
			logger.info("发布菜单失败："+WeixinBasicKit.getRequestMsg(rel));
		}
		return flag;
	}

	@Override
	public String queryMenu() {
		String url = WeixinBasicKit.replaceAccessTokenUrl(WeixinFinalValue.MENU_QUERY);
		String result = WeixinBasicKit.sendGet(url);
		logger.info("已发布微信公众号菜单："+result);
		return result;
	}
	
	
	public List<WeixinMenuDto> generateWeixinMenuDto(){
		List<WeixinMenu> menus = this.selectMenuAll();
		List<WeixinMenuDto> menuDtos = new ArrayList<WeixinMenuDto>();
		System.out.println("从数据库获取所有的菜单："+WeiXinJsonUtil.getInstance().obj2json(menus));
		WeixinMenuDto wmd = null;
		for(WeixinMenu wm:menus) {
			wmd = new WeixinMenuDto();
			wmd.setKey(wm.getWeixinMenuType());
			wmd.setName(wm.getMenuName());
			wmd.setType(wm.getType());
			wmd.setId(String.valueOf(wm.getMenuId()));
			wmd.setUrl(wm.getUrl());
			if(wm.getParentId() == 0) {
				if(wmd.getSub_button()==null) {
					wmd.setSub_button(new ArrayList<WeixinMenuDto>());
				}
				menuDtos.add(wmd);
			} else {
				WeixinMenuDto twmd = findById(String.valueOf(wm.getParentId()), menuDtos);
				if(twmd!=null) {
					twmd.getSub_button().add(wmd);
				}
			}
		}
		return menuDtos;
	}
	
	private WeixinMenuDto findById(String pid,List<WeixinMenuDto> wmds) {
		if(wmds!=null){
			for(WeixinMenuDto wmd:wmds) {
				if(wmd.getId().equals(pid)) {
					return wmd;
				}
			}
		}
		return null;
	}

	@Override
	public String selectMenuByWeixinMenuType(String weixinMenuType) {
		
		return weixinMenuMapper.selectMenuByWeixinMenuType(weixinMenuType);
	}

	@Override
	public int updateMenu(WeixinMenu menu) {
		return weixinMenuMapper.updateMenu(menu);
	}
}

package com.yiran.web.controller.weixin;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yiran.common.annotation.Log;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.enums.BusinessType;
import com.yiran.framework.util.ShiroUtils;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.SysMenu;
import com.yiran.system.domain.SysRole;
import com.yiran.weixin.domain.WeixinMenu;
import com.yiran.weixin.service.IWeixinMenuService;
import com.yiran.weixin.utils.JSONFormatUtil;

/**
 * 微信菜单 信息操作处理
 * 
 * @date 2018-07-18
 */
@Controller
@RequestMapping("/weixin/weixinMenu")
public class WeixinMenuController extends BaseController
{
    private String prefix = "weixin/weixinMenu";
	
	@Autowired
	private IWeixinMenuService weixinMenuService;
	
	@RequiresPermissions("weixin:weixinMenu:view")
	@GetMapping()
	public String weixinMenu()
	{
	    return prefix + "/weixinMenu";
	}
	
	@RequiresPermissions("weixin:weixinMenu:list")
    @GetMapping("/list")
    @ResponseBody
    public List<WeixinMenu> list(WeixinMenu menu)
    {
        List<WeixinMenu> menuList = weixinMenuService.selectMenuList(menu);
        return menuList;
    }

    /**
     * 删除菜单
     */
    @Log(title = "微信菜单管理", businessType = BusinessType.DELETE)
    @RequiresPermissions("weixin:weixinMenu:remove")
    @PostMapping("/remove/{menuId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("menuId") Long menuId)
    {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
        if (weixinMenuService.selectCountMenuByParentId(menuId) > 0)
        {
            return error(1, "存在子菜单,不允许删除");
        }
        /*if (weixinMenuService.selectCountRoleMenuByMenuId(menuId) > 0)
        {
            return error(1, "菜单已分配,不允许删除");
        }*/
        if (weixinMenuService.deleteMenuById(menuId) > 0)
        {
            return success();
        }
        return error();
    }

    /**
     * 修改菜单
     */
    @Log(title = "微信菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("weixin:weixinMenu:edit")
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable("menuId") Long menuId, ModelMap mmap)
    {
    	WeixinMenu menu = weixinMenuService.selectMenuById(menuId);
    	mmap.put("menu", menu);
        return prefix + "/edit";
    }

    /**
     * 修改保存微信菜单
     */
    @Log(title = "微信菜单管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("weixin:weixinMenu:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WeixinMenu menu)
    {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
        menu.setUpdateBy(ShiroUtils.getLoginName());
        ShiroUtils.clearCachedAuthorizationInfo();
        return toAjax(weixinMenuService.updateMenu(menu));
    }
    /**
     * 新增
     */
    @Log(title = "微信菜单管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("weixin:weixinMenu:add")
    @GetMapping("/add/{parentId}")
    public String add(@PathVariable("parentId") Long parentId, ModelMap mmap)
    {
    	WeixinMenu menu = null;
        if (0L != parentId)
        {
            menu = weixinMenuService.selectMenuById(parentId);
        }
        else
        {
        	menu = new WeixinMenu();
            menu.setMenuId(0);
            menu.setMenuName("主目录");
        }
        mmap.put("menu", menu);
        return prefix + "/add";
    }

    /**
     * 保存菜单
     */
    @Log(title = "微信菜单管理", businessType = BusinessType.INSERT)
    @RequiresPermissions("weixin:weixinMenu:save")
    @PostMapping("/save")
    @ResponseBody
    public AjaxResult save(WeixinMenu menu)
    {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
    	ShiroUtils.clearCachedAuthorizationInfo();
        if (weixinMenuService.saveMenu(menu) > 0)
        {
            return success();
        }
        return error();
    }

    /**
     * 选择菜单图标
     */
    @GetMapping("/icon")
    public String icon()
    {
        return prefix + "/icon";
    }

    /**
     * 校验菜单名称
     */
    @PostMapping("/checkMenuNameUnique")
    @ResponseBody
    public String checkMenuNameUnique(WeixinMenu menu)
    {
        String uniqueFlag = "0";
        if (menu != null)
        {
            uniqueFlag = weixinMenuService.checkMenuNameUnique(menu);
        }
        return uniqueFlag;
    }

    /**
     * 加载角色菜单列表树
     */
    @GetMapping("/roleMenuTreeData")
    @ResponseBody
    public List<Map<String, Object>> roleMenuTreeData(SysRole role)
    {
        List<Map<String, Object>> tree = weixinMenuService.roleMenuTreeData(role);
        return tree;
    }

    /**
     * 加载所有菜单列表树
     */
    @GetMapping("/menuTreeData")
    @ResponseBody
    public List<Map<String, Object>> menuTreeData(SysRole role)
    {
        List<Map<String, Object>> tree = weixinMenuService.menuTreeData();
        return tree;
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree/{menuId}")
    public String selectMenuTree(@PathVariable("menuId") Long menuId, ModelMap mmap)
    {
    	mmap.put("menu", weixinMenuService.selectMenuById(menuId));
        return prefix + "/tree";
    }
    
    @GetMapping("/queryPublishMenu")
    @RequiresPermissions("weixin:weixinMenu:query")
	public String queryPublish(Model model) {
		model.addAttribute("ms", JSONFormatUtil.formatJson(weixinMenuService.queryMenu()));
		return prefix +"/publishWeixinMenu";
	}
	
    @GetMapping("/publishMenu")
    @RequiresPermissions("weixin:weixinMenu:publishMenu")
	@ResponseBody
	public AjaxResult publishMenu() {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		int flag = weixinMenuService.publishMenu();
		if(flag ==1){
			return success();
		}else{
			return error();
		}
	}
	
}

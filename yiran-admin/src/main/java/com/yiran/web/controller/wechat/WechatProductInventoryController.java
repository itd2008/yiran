package com.yiran.web.controller.wechat;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.wechat.domain.WechatProductInventory;
import com.yiran.wechat.service.IWechatProductInventoryService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品进销存 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-25
 */
@Controller
@RequestMapping("/wechat/wechatProductInventory")
public class WechatProductInventoryController extends BaseController
{
    private String prefix = "wechat/wechatProductInventory";
	
	@Autowired
	private IWechatProductInventoryService wechatProductInventoryService;
	
	@RequiresPermissions("wechat:wechatProductInventory:view")
	@GetMapping()
	public String wechatProductInventory()
	{
	    return prefix + "/wechatProductInventory";
	}
	
	/**
	 * 查询商品进销存列表
	 */
	@RequiresPermissions("wechat:wechatProductInventory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductInventory wechatProductInventory)
	{
		startPage();
        List<WechatProductInventory> list = wechatProductInventoryService.selectWechatProductInventoryList(wechatProductInventory);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品进销存列表
	 */
	@RequiresPermissions("wechat:wechatProductInventory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductInventory wechatProductInventory)
    {
    	List<WechatProductInventory> list = wechatProductInventoryService.selectWechatProductInventoryList(wechatProductInventory);
        ExcelUtil<WechatProductInventory> util = new ExcelUtil<WechatProductInventory>(WechatProductInventory.class);
        return util.exportExcel(list, "wechatProductInventory");
    }
	
	/**
	 * 新增商品进销存
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品进销存
	 */
	@RequiresPermissions("wechat:wechatProductInventory:add")
	@Log(title = "商品进销存", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductInventory wechatProductInventory)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductInventoryService.insertWechatProductInventory(wechatProductInventory));
	}

	/**
	 * 修改商品进销存
	 */
	@GetMapping("/edit/{productInventoryId}")
	public String edit(@PathVariable("productInventoryId") Integer productInventoryId, ModelMap mmap)
	{
		WechatProductInventory wechatProductInventory = wechatProductInventoryService.selectWechatProductInventoryById(productInventoryId);
		mmap.put("wechatProductInventory", wechatProductInventory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品进销存
	 */
	@RequiresPermissions("wechat:wechatProductInventory:edit")
	@Log(title = "商品进销存", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductInventory wechatProductInventory)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductInventoryService.updateWechatProductInventory(wechatProductInventory));
	}
	
	/**
	 * 删除商品进销存
	 */
	@RequiresPermissions("wechat:wechatProductInventory:remove")
	@Log(title = "商品进销存", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{	
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductInventoryService.deleteWechatProductInventoryByIds(ids));
	}
	
}

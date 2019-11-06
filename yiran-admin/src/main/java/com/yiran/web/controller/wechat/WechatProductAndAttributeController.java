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
import com.yiran.wechat.domain.WechatProductAndAttribute;
import com.yiran.wechat.service.IWechatProductAndAttributeService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品与属性关联 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductAndAttribute")
public class WechatProductAndAttributeController extends BaseController
{
    private String prefix = "wechat/wechatProductAndAttribute";
	
	@Autowired
	private IWechatProductAndAttributeService wechatProductAndAttributeService;
	
	@RequiresPermissions("wechat:wechatProductAndAttribute:view")
	@GetMapping()
	public String wechatProductAndAttribute()
	{
	    return prefix + "/wechatProductAndAttribute";
	}
	
	/**
	 * 查询商品与属性关联列表
	 */
	@RequiresPermissions("wechat:wechatProductAndAttribute:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductAndAttribute wechatProductAndAttribute)
	{
		startPage();
        List<WechatProductAndAttribute> list = wechatProductAndAttributeService.selectWechatProductAndAttributeList(wechatProductAndAttribute);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品与属性关联列表
	 */
	@RequiresPermissions("wechat:wechatProductAndAttribute:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductAndAttribute wechatProductAndAttribute)
    {
    	List<WechatProductAndAttribute> list = wechatProductAndAttributeService.selectWechatProductAndAttributeList(wechatProductAndAttribute);
        ExcelUtil<WechatProductAndAttribute> util = new ExcelUtil<WechatProductAndAttribute>(WechatProductAndAttribute.class);
        return util.exportExcel(list, "wechatProductAndAttribute");
    }
	
	/**
	 * 新增商品与属性关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品与属性关联
	 */
	@RequiresPermissions("wechat:wechatProductAndAttribute:add")
	@Log(title = "商品与属性关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductAndAttribute wechatProductAndAttribute)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndAttributeService.insertWechatProductAndAttribute(wechatProductAndAttribute));
	}

	/**
	 * 修改商品与属性关联
	 */
	@GetMapping("/edit/{productAndAttributeId}")
	public String edit(@PathVariable("productAndAttributeId") Integer productAndAttributeId, ModelMap mmap)
	{
		WechatProductAndAttribute wechatProductAndAttribute = wechatProductAndAttributeService.selectWechatProductAndAttributeById(productAndAttributeId);
		mmap.put("wechatProductAndAttribute", wechatProductAndAttribute);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品与属性关联
	 */
	@RequiresPermissions("wechat:wechatProductAndAttribute:edit")
	@Log(title = "商品与属性关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductAndAttribute wechatProductAndAttribute)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndAttributeService.updateWechatProductAndAttribute(wechatProductAndAttribute));
	}
	
	/**
	 * 删除商品与属性关联
	 */
	@RequiresPermissions("wechat:wechatProductAndAttribute:remove")
	@Log(title = "商品与属性关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndAttributeService.deleteWechatProductAndAttributeByIds(ids));
	}
	
}

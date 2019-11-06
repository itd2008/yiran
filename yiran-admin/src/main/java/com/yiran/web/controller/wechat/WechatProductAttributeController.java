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
import com.yiran.wechat.domain.WechatProductAttribute;
import com.yiran.wechat.domain.WechatProductBrand;
import com.yiran.wechat.service.IWechatProductAttributeService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品属性 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductAttribute")
public class WechatProductAttributeController extends BaseController
{
    private String prefix = "wechat/wechatProductAttribute";
	
	@Autowired
	private IWechatProductAttributeService wechatProductAttributeService;
	
	@RequiresPermissions("wechat:wechatProductAttribute:view")
	@GetMapping()
	public String wechatProductAttribute()
	{
	    return prefix + "/wechatProductAttribute";
	}
	
	/**
	 * 查询商品属性列表
	 */
	@RequiresPermissions("wechat:wechatProductAttribute:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductAttribute wechatProductAttribute)
	{
		startPage();
        List<WechatProductAttribute> list = wechatProductAttributeService.selectWechatProductAttributeList(wechatProductAttribute);
		return getDataTable(list);
	}
	
	@GetMapping("/findProductAttrByCategoryId/{categoryId}")
	@ResponseBody
	public AjaxResult findProductAttrByCategoryId(@PathVariable("categoryId") String categoryId)
	{
		AjaxResult result = new AjaxResult();
		List<WechatProductAttribute> list = wechatProductAttributeService.selectWechatProductAttrdListByCategoryId(categoryId);
	    return result.success(list);
	}
	/**
	 * 导出商品属性列表
	 */
	@RequiresPermissions("wechat:wechatProductAttribute:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductAttribute wechatProductAttribute)
    {
    	List<WechatProductAttribute> list = wechatProductAttributeService.selectWechatProductAttributeList(wechatProductAttribute);
        ExcelUtil<WechatProductAttribute> util = new ExcelUtil<WechatProductAttribute>(WechatProductAttribute.class);
        return util.exportExcel(list, "wechatProductAttribute");
    }
	
	/**
	 * 新增商品属性
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品属性
	 */
	@RequiresPermissions("wechat:wechatProductAttribute:add")
	@Log(title = "商品属性", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductAttribute wechatProductAttribute)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAttributeService.insertWechatProductAttribute(wechatProductAttribute));
	}

	/**
	 * 修改商品属性
	 */
	@GetMapping("/edit/{productAttributeId}")
	public String edit(@PathVariable("productAttributeId") Integer productAttributeId, ModelMap mmap)
	{
		WechatProductAttribute wechatProductAttribute = wechatProductAttributeService.selectWechatProductAttributeById(productAttributeId);
		mmap.put("wechatProductAttribute", wechatProductAttribute);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品属性
	 */
	@RequiresPermissions("wechat:wechatProductAttribute:edit")
	@Log(title = "商品属性", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductAttribute wechatProductAttribute)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAttributeService.updateWechatProductAttribute(wechatProductAttribute));
	}
	
	/**
	 * 删除商品属性
	 */
	@RequiresPermissions("wechat:wechatProductAttribute:remove")
	@Log(title = "商品属性", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAttributeService.deleteWechatProductAttributeByIds(ids));
	}
	
}

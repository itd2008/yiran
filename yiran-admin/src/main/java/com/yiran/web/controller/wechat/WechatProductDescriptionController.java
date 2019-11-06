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
import com.yiran.wechat.domain.WechatProductDescription;
import com.yiran.wechat.service.IWechatProductDescriptionService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品描述内容 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductDescription")
public class WechatProductDescriptionController extends BaseController
{
    private String prefix = "wechat/wechatProductDescription";
	
	@Autowired
	private IWechatProductDescriptionService wechatProductDescriptionService;
	
	@RequiresPermissions("wechat:wechatProductDescription:view")
	@GetMapping()
	public String wechatProductDescription()
	{
	    return prefix + "/wechatProductDescription";
	}
	
	/**
	 * 查询商品描述内容列表
	 */
	@RequiresPermissions("wechat:wechatProductDescription:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductDescription wechatProductDescription)
	{
		startPage();
        List<WechatProductDescription> list = wechatProductDescriptionService.selectWechatProductDescriptionList(wechatProductDescription);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品描述内容列表
	 */
	@RequiresPermissions("wechat:wechatProductDescription:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductDescription wechatProductDescription)
    {
    	List<WechatProductDescription> list = wechatProductDescriptionService.selectWechatProductDescriptionList(wechatProductDescription);
        ExcelUtil<WechatProductDescription> util = new ExcelUtil<WechatProductDescription>(WechatProductDescription.class);
        return util.exportExcel(list, "wechatProductDescription");
    }
	
	/**
	 * 新增商品描述内容
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品描述内容
	 */
	@RequiresPermissions("wechat:wechatProductDescription:add")
	@Log(title = "商品描述内容", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductDescription wechatProductDescription)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductDescriptionService.insertWechatProductDescription(wechatProductDescription));
	}

	/**
	 * 修改商品描述内容
	 */
	@GetMapping("/edit/{productDescriptionId}")
	public String edit(@PathVariable("productDescriptionId") Integer productDescriptionId, ModelMap mmap)
	{
		WechatProductDescription wechatProductDescription = wechatProductDescriptionService.selectWechatProductDescriptionById(productDescriptionId);
		mmap.put("wechatProductDescription", wechatProductDescription);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品描述内容
	 */
	@RequiresPermissions("wechat:wechatProductDescription:edit")
	@Log(title = "商品描述内容", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductDescription wechatProductDescription)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductDescriptionService.updateWechatProductDescription(wechatProductDescription));
	}
	
	/**
	 * 删除商品描述内容
	 */
	@RequiresPermissions("wechat:wechatProductDescription:remove")
	@Log(title = "商品描述内容", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductDescriptionService.deleteWechatProductDescriptionByIds(ids));
	}
	
}

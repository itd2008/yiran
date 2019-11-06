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
import com.yiran.wechat.domain.WechatProductAndSpecification;
import com.yiran.wechat.service.IWechatProductAndSpecificationService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 商品与商品规格关联 信息操作处理
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Controller
@RequestMapping("/wechat/wechatProductAndSpecification")
public class WechatProductAndSpecificationController extends BaseController
{
    private String prefix = "wechat/wechatProductAndSpecification";
	
	@Autowired
	private IWechatProductAndSpecificationService wechatProductAndSpecificationService;
	
	@RequiresPermissions("wechat:wechatProductAndSpecification:view")
	@GetMapping()
	public String wechatProductAndSpecification()
	{
	    return prefix + "/wechatProductAndSpecification";
	}
	
	/**
	 * 查询商品与商品规格关联列表
	 */
	@RequiresPermissions("wechat:wechatProductAndSpecification:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WechatProductAndSpecification wechatProductAndSpecification)
	{
		startPage();
        List<WechatProductAndSpecification> list = wechatProductAndSpecificationService.selectWechatProductAndSpecificationList(wechatProductAndSpecification);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品与商品规格关联列表
	 */
	@RequiresPermissions("wechat:wechatProductAndSpecification:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WechatProductAndSpecification wechatProductAndSpecification)
    {
    	List<WechatProductAndSpecification> list = wechatProductAndSpecificationService.selectWechatProductAndSpecificationList(wechatProductAndSpecification);
        ExcelUtil<WechatProductAndSpecification> util = new ExcelUtil<WechatProductAndSpecification>(WechatProductAndSpecification.class);
        return util.exportExcel(list, "wechatProductAndSpecification");
    }
	
	/**
	 * 新增商品与商品规格关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品与商品规格关联
	 */
	@RequiresPermissions("wechat:wechatProductAndSpecification:add")
	@Log(title = "商品与商品规格关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WechatProductAndSpecification wechatProductAndSpecification)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndSpecificationService.insertWechatProductAndSpecification(wechatProductAndSpecification));
	}

	/**
	 * 修改商品与商品规格关联
	 */
	@GetMapping("/edit/{productAndSpecificationId}")
	public String edit(@PathVariable("productAndSpecificationId") Integer productAndSpecificationId, ModelMap mmap)
	{
		WechatProductAndSpecification wechatProductAndSpecification = wechatProductAndSpecificationService.selectWechatProductAndSpecificationById(productAndSpecificationId);
		mmap.put("wechatProductAndSpecification", wechatProductAndSpecification);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品与商品规格关联
	 */
	@RequiresPermissions("wechat:wechatProductAndSpecification:edit")
	@Log(title = "商品与商品规格关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WechatProductAndSpecification wechatProductAndSpecification)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndSpecificationService.updateWechatProductAndSpecification(wechatProductAndSpecification));
	}
	
	/**
	 * 删除商品与商品规格关联
	 */
	@RequiresPermissions("wechat:wechatProductAndSpecification:remove")
	@Log(title = "商品与商品规格关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(wechatProductAndSpecificationService.deleteWechatProductAndSpecificationByIds(ids));
	}
	
}

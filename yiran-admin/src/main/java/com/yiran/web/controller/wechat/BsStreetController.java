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
import com.yiran.wechat.domain.BsStreet;
import com.yiran.wechat.service.IBsStreetService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 街道设置 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Controller
@RequestMapping("/wechat/bsStreet")
public class BsStreetController extends BaseController
{
    private String prefix = "wechat/bsStreet";
	
	@Autowired
	private IBsStreetService bsStreetService;
	
	@RequiresPermissions("wechat:bsStreet:view")
	@GetMapping()
	public String bsStreet()
	{
	    return prefix + "/bsStreet";
	}
	
	/**
	 * 查询街道设置列表
	 */
	@RequiresPermissions("wechat:bsStreet:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BsStreet bsStreet)
	{
		startPage();
        List<BsStreet> list = bsStreetService.selectBsStreetList(bsStreet);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出街道设置列表
	 */
	@RequiresPermissions("wechat:bsStreet:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsStreet bsStreet)
    {
    	List<BsStreet> list = bsStreetService.selectBsStreetList(bsStreet);
        ExcelUtil<BsStreet> util = new ExcelUtil<BsStreet>(BsStreet.class);
        return util.exportExcel(list, "bsStreet");
    }
	
	/**
	 * 新增街道设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存街道设置
	 */
	@RequiresPermissions("wechat:bsStreet:add")
	@Log(title = "街道设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BsStreet bsStreet)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsStreetService.insertBsStreet(bsStreet));
	}

	/**
	 * 修改街道设置
	 */
	@GetMapping("/edit/{streetId}")
	public String edit(@PathVariable("streetId") Integer streetId, ModelMap mmap)
	{
		BsStreet bsStreet = bsStreetService.selectBsStreetById(streetId);
		mmap.put("bsStreet", bsStreet);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存街道设置
	 */
	@RequiresPermissions("wechat:bsStreet:edit")
	@Log(title = "街道设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BsStreet bsStreet)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsStreetService.updateBsStreet(bsStreet));
	}
	
	/**
	 * 删除街道设置
	 */
	@RequiresPermissions("wechat:bsStreet:remove")
	@Log(title = "街道设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsStreetService.deleteBsStreetByIds(ids));
	}
	
}

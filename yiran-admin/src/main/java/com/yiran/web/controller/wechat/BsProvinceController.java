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

import com.alibaba.fastjson.JSON;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.wechat.domain.BsProvince;
import com.yiran.wechat.service.IBsProvinceService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 省份设置 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Controller
@RequestMapping("/wechat/bsProvince")
public class BsProvinceController extends BaseController
{
    private String prefix = "wechat/bsProvince";
	
	@Autowired
	private IBsProvinceService bsProvinceService;
	
	@RequiresPermissions("wechat:bsProvince:view")
	@GetMapping()
	public String bsProvince()
	{
	    return prefix + "/bsProvince";
	}
	
	/**
	 * 查询省份设置列表
	 */
	@RequiresPermissions("wechat:bsProvince:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BsProvince bsProvince)
	{
		startPage();
        List<BsProvince> list = bsProvinceService.selectBsProvinceList(bsProvince);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出省份设置列表
	 */
	@RequiresPermissions("wechat:bsProvince:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsProvince bsProvince)
    {
    	List<BsProvince> list = bsProvinceService.selectBsProvinceList(bsProvince);
        ExcelUtil<BsProvince> util = new ExcelUtil<BsProvince>(BsProvince.class);
        return util.exportExcel(list, "bsProvince");
    }
	
	/**
	 * 新增省份设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存省份设置
	 */
	@RequiresPermissions("wechat:bsProvince:add")
	@Log(title = "省份设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BsProvince bsProvince)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsProvinceService.insertBsProvince(bsProvince));
	}

	/**
	 * 修改省份设置
	 */
	@GetMapping("/edit/{provinceId}")
	public String edit(@PathVariable("provinceId") Integer provinceId, ModelMap mmap)
	{
		BsProvince bsProvince = bsProvinceService.selectBsProvinceById(provinceId);
		mmap.put("bsProvince", bsProvince);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存省份设置
	 */
	@RequiresPermissions("wechat:bsProvince:edit")
	@Log(title = "省份设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BsProvince bsProvince)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsProvinceService.updateBsProvince(bsProvince));
	}
	
	/**
	 * 删除省份设置
	 */
	@RequiresPermissions("wechat:bsProvince:remove")
	@Log(title = "省份设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsProvinceService.deleteBsProvinceByIds(ids));
	}
	
}

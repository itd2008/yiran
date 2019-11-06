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
import com.yiran.wechat.domain.BsCity;
import com.yiran.wechat.service.IBsCityService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 城市设置 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Controller
@RequestMapping("/wechat/bsCity")
public class BsCityController extends BaseController
{
    private String prefix = "wechat/bsCity";
	
	@Autowired
	private IBsCityService bsCityService;
	
	@RequiresPermissions("wechat:bsCity:view")
	@GetMapping()
	public String bsCity()
	{
	    return prefix + "/bsCity";
	}
	
	/**
	 * 查询城市设置列表
	 */
	@RequiresPermissions("wechat:bsCity:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BsCity bsCity)
	{
		startPage();
        List<BsCity> list = bsCityService.selectBsCityList(bsCity);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出城市设置列表
	 */
	@RequiresPermissions("wechat:bsCity:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsCity bsCity)
    {
    	List<BsCity> list = bsCityService.selectBsCityList(bsCity);
        ExcelUtil<BsCity> util = new ExcelUtil<BsCity>(BsCity.class);
        return util.exportExcel(list, "bsCity");
    }
	
	/**
	 * 新增城市设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存城市设置
	 */
	@RequiresPermissions("wechat:bsCity:add")
	@Log(title = "城市设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BsCity bsCity)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsCityService.insertBsCity(bsCity));
	}

	/**
	 * 修改城市设置
	 */
	@GetMapping("/edit/{cityId}")
	public String edit(@PathVariable("cityId") Integer cityId, ModelMap mmap)
	{
		BsCity bsCity = bsCityService.selectBsCityById(cityId);
		mmap.put("bsCity", bsCity);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存城市设置
	 */
	@RequiresPermissions("wechat:bsCity:edit")
	@Log(title = "城市设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BsCity bsCity)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsCityService.updateBsCity(bsCity));
	}
	
	/**
	 * 删除城市设置
	 */
	@RequiresPermissions("wechat:bsCity:remove")
	@Log(title = "城市设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsCityService.deleteBsCityByIds(ids));
	}
	
}

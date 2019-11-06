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
import com.yiran.wechat.domain.BsArea;
import com.yiran.wechat.service.IBsAreaService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 地区设置 信息操作处理
 * 
 * @author yiran
 * @date 2019-07-06
 */
@Controller
@RequestMapping("/wechat/bsArea")
public class BsAreaController extends BaseController
{
    private String prefix = "wechat/bsArea";
	
	@Autowired
	private IBsAreaService bsAreaService;
	
	@RequiresPermissions("wechat:bsArea:view")
	@GetMapping()
	public String bsArea()
	{
	    return prefix + "/bsArea";
	}
	
	/**
	 * 查询地区设置列表
	 */
	@RequiresPermissions("wechat:bsArea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BsArea bsArea)
	{
		startPage();
        List<BsArea> list = bsAreaService.selectBsAreaList(bsArea);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出地区设置列表
	 */
	@RequiresPermissions("wechat:bsArea:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BsArea bsArea)
    {
    	List<BsArea> list = bsAreaService.selectBsAreaList(bsArea);
        ExcelUtil<BsArea> util = new ExcelUtil<BsArea>(BsArea.class);
        return util.exportExcel(list, "bsArea");
    }
	
	/**
	 * 新增地区设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存地区设置
	 */
	@RequiresPermissions("wechat:bsArea:add")
	@Log(title = "地区设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BsArea bsArea)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsAreaService.insertBsArea(bsArea));
	}

	/**
	 * 修改地区设置
	 */
	@GetMapping("/edit/{areaId}")
	public String edit(@PathVariable("areaId") Integer areaId, ModelMap mmap)
	{
		BsArea bsArea = bsAreaService.selectBsAreaById(areaId);
		mmap.put("bsArea", bsArea);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存地区设置
	 */
	@RequiresPermissions("wechat:bsArea:edit")
	@Log(title = "地区设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BsArea bsArea)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsAreaService.updateBsArea(bsArea));
	}
	
	/**
	 * 删除地区设置
	 */
	@RequiresPermissions("wechat:bsArea:remove")
	@Log(title = "地区设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(bsAreaService.deleteBsAreaByIds(ids));
	}
	
}

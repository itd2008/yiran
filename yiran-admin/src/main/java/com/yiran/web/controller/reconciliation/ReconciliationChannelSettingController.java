package com.yiran.web.controller.reconciliation;

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
import com.yiran.reconciliation.domain.ReconciliationChannelSetting;
import com.yiran.reconciliation.service.IReconciliationChannelSettingService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 对账渠道设置 信息操作处理
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Controller
@RequestMapping("/reconciliation/reconciliationChannelSetting")
public class ReconciliationChannelSettingController extends BaseController
{
    private String prefix = "reconciliation/reconciliationChannelSetting";
	
	@Autowired
	private IReconciliationChannelSettingService reconciliationChannelSettingService;
	
	@RequiresPermissions("reconciliation:reconciliationChannelSetting:view")
	@GetMapping()
	public String reconciliationChannelSetting()
	{
	    return prefix + "/reconciliationChannelSetting";
	}
	
	/**
	 * 查询对账渠道设置列表
	 */
	@RequiresPermissions("reconciliation:reconciliationChannelSetting:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReconciliationChannelSetting reconciliationChannelSetting)
	{
		startPage();
        List<ReconciliationChannelSetting> list = reconciliationChannelSettingService.selectReconciliationChannelSettingList(reconciliationChannelSetting);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出对账渠道设置列表
	 */
	@RequiresPermissions("reconciliation:reconciliationChannelSetting:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationChannelSetting reconciliationChannelSetting)
    {
    	List<ReconciliationChannelSetting> list = reconciliationChannelSettingService.selectReconciliationChannelSettingList(reconciliationChannelSetting);
        ExcelUtil<ReconciliationChannelSetting> util = new ExcelUtil<ReconciliationChannelSetting>(ReconciliationChannelSetting.class);
        return util.exportExcel(list, "reconciliationChannelSetting");
    }
	
	/**
	 * 新增对账渠道设置
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存对账渠道设置
	 */
	@RequiresPermissions("reconciliation:reconciliationChannelSetting:add")
	@Log(title = "对账渠道设置", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReconciliationChannelSetting reconciliationChannelSetting)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationChannelSettingService.insertReconciliationChannelSetting(reconciliationChannelSetting));
	}

	/**
	 * 修改对账渠道设置
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ReconciliationChannelSetting reconciliationChannelSetting = reconciliationChannelSettingService.selectReconciliationChannelSettingById(id);
		mmap.put("reconciliationChannelSetting", reconciliationChannelSetting);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存对账渠道设置
	 */
	@RequiresPermissions("reconciliation:reconciliationChannelSetting:edit")
	@Log(title = "对账渠道设置", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReconciliationChannelSetting reconciliationChannelSetting)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationChannelSettingService.updateReconciliationChannelSetting(reconciliationChannelSetting));
	}
	
	/**
	 * 删除对账渠道设置
	 */
	@RequiresPermissions("reconciliation:reconciliationChannelSetting:remove")
	@Log(title = "对账渠道设置", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationChannelSettingService.deleteReconciliationChannelSettingByIds(ids));
	}
	
}

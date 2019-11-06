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
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;
import com.yiran.reconciliation.service.IReconciliationAccountCheckMistakeService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 对账差错 信息操作处理
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Controller
@RequestMapping("/reconciliation/reconciliationAccountCheckMistake")
public class ReconciliationAccountCheckMistakeController extends BaseController
{
    private String prefix = "reconciliation/reconciliationAccountCheckMistake";
	
	@Autowired
	private IReconciliationAccountCheckMistakeService reconciliationAccountCheckMistakeService;
	
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:view")
	@GetMapping()
	public String reconciliationAccountCheckMistake()
	{
	    return prefix + "/reconciliationAccountCheckMistake";
	}
	
	/**
	 * 查询对账差错列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{
		startPage();
        List<ReconciliationAccountCheckMistake> list = reconciliationAccountCheckMistakeService.selectReconciliationAccountCheckMistakeList(reconciliationAccountCheckMistake);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出对账差错列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
    {
    	List<ReconciliationAccountCheckMistake> list = reconciliationAccountCheckMistakeService.selectReconciliationAccountCheckMistakeList(reconciliationAccountCheckMistake);
        ExcelUtil<ReconciliationAccountCheckMistake> util = new ExcelUtil<ReconciliationAccountCheckMistake>(ReconciliationAccountCheckMistake.class);
        return util.exportExcel(list, "reconciliationAccountCheckMistake");
    }
	
	/**
	 * 新增对账差错
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存对账差错
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:add")
	@Log(title = "对账差错", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckMistakeService.insertReconciliationAccountCheckMistake(reconciliationAccountCheckMistake));
	}

	/**
	 * 修改对账差错
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ReconciliationAccountCheckMistake reconciliationAccountCheckMistake = reconciliationAccountCheckMistakeService.selectReconciliationAccountCheckMistakeById(id);
		mmap.put("reconciliationAccountCheckMistake", reconciliationAccountCheckMistake);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存对账差错
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:edit")
	@Log(title = "对账差错", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckMistakeService.updateReconciliationAccountCheckMistake(reconciliationAccountCheckMistake));
	}
	
	/**
	 * 删除对账差错
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistake:remove")
	@Log(title = "对账差错", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckMistakeService.deleteReconciliationAccountCheckMistakeByIds(ids));
	}
	
}

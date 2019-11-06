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
import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiran.reconciliation.service.IReconciliationAccountCheckBatchService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 对账批次 信息操作处理
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Controller
@RequestMapping("/reconciliation/reconciliationAccountCheckBatch")
public class ReconciliationAccountCheckBatchController extends BaseController
{
    private String prefix = "reconciliation/reconciliationAccountCheckBatch";
	
	@Autowired
	private IReconciliationAccountCheckBatchService reconciliationAccountCheckBatchService;
	
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:view")
	@GetMapping()
	public String reconciliationAccountCheckBatch()
	{
	    return prefix + "/reconciliationAccountCheckBatch";
	}
	
	/**
	 * 查询对账批次列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{
		startPage();
        List<ReconciliationAccountCheckBatch> list = reconciliationAccountCheckBatchService.selectReconciliationAccountCheckBatchList(reconciliationAccountCheckBatch);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出对账批次列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
    {
    	List<ReconciliationAccountCheckBatch> list = reconciliationAccountCheckBatchService.selectReconciliationAccountCheckBatchList(reconciliationAccountCheckBatch);
        ExcelUtil<ReconciliationAccountCheckBatch> util = new ExcelUtil<ReconciliationAccountCheckBatch>(ReconciliationAccountCheckBatch.class);
        return util.exportExcel(list, "reconciliationAccountCheckBatch");
    }
	
	/**
	 * 新增对账批次
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存对账批次
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:add")
	@Log(title = "对账批次", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckBatchService.insertReconciliationAccountCheckBatch(reconciliationAccountCheckBatch));
	}

	/**
	 * 修改对账批次
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ReconciliationAccountCheckBatch reconciliationAccountCheckBatch = reconciliationAccountCheckBatchService.selectReconciliationAccountCheckBatchById(id);
		mmap.put("reconciliationAccountCheckBatch", reconciliationAccountCheckBatch);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存对账批次
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:edit")
	@Log(title = "对账批次", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReconciliationAccountCheckBatch reconciliationAccountCheckBatch)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckBatchService.updateReconciliationAccountCheckBatch(reconciliationAccountCheckBatch));
	}
	
	/**
	 * 删除对账批次
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckBatch:remove")
	@Log(title = "对账批次", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckBatchService.deleteReconciliationAccountCheckBatchByIds(ids));
	}
	
}

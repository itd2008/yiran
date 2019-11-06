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
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistakeScratchPool;
import com.yiran.reconciliation.service.IReconciliationAccountCheckMistakeScratchPoolService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 差错暂存池 信息操作处理
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Controller
@RequestMapping("/reconciliation/reconciliationAccountCheckMistakeScratchPool")
public class ReconciliationAccountCheckMistakeScratchPoolController extends BaseController
{
    private String prefix = "reconciliation/reconciliationAccountCheckMistakeScratchPool";
	
	@Autowired
	private IReconciliationAccountCheckMistakeScratchPoolService reconciliationAccountCheckMistakeScratchPoolService;
	
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistakeScratchPool:view")
	@GetMapping()
	public String reconciliationAccountCheckMistakeScratchPool()
	{
	    return prefix + "/reconciliationAccountCheckMistakeScratchPool";
	}
	
	/**
	 * 查询差错暂存池列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistakeScratchPool:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool)
	{
		startPage();
        List<ReconciliationAccountCheckMistakeScratchPool> list = reconciliationAccountCheckMistakeScratchPoolService.selectReconciliationAccountCheckMistakeScratchPoolList(reconciliationAccountCheckMistakeScratchPool);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出差错暂存池列表
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistakeScratchPool:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool)
    {
    	List<ReconciliationAccountCheckMistakeScratchPool> list = reconciliationAccountCheckMistakeScratchPoolService.selectReconciliationAccountCheckMistakeScratchPoolList(reconciliationAccountCheckMistakeScratchPool);
        ExcelUtil<ReconciliationAccountCheckMistakeScratchPool> util = new ExcelUtil<ReconciliationAccountCheckMistakeScratchPool>(ReconciliationAccountCheckMistakeScratchPool.class);
        return util.exportExcel(list, "reconciliationAccountCheckMistakeScratchPool");
    }
	
	/**
	 * 新增差错暂存池
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存差错暂存池
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistakeScratchPool:add")
	@Log(title = "差错暂存池", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckMistakeScratchPoolService.insertReconciliationAccountCheckMistakeScratchPool(reconciliationAccountCheckMistakeScratchPool));
	}

	/**
	 * 修改差错暂存池
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, ModelMap mmap)
	{
		ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool = reconciliationAccountCheckMistakeScratchPoolService.selectReconciliationAccountCheckMistakeScratchPoolById(id);
		mmap.put("reconciliationAccountCheckMistakeScratchPool", reconciliationAccountCheckMistakeScratchPool);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存差错暂存池
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistakeScratchPool:edit")
	@Log(title = "差错暂存池", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReconciliationAccountCheckMistakeScratchPool reconciliationAccountCheckMistakeScratchPool)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckMistakeScratchPoolService.updateReconciliationAccountCheckMistakeScratchPool(reconciliationAccountCheckMistakeScratchPool));
	}
	
	/**
	 * 删除差错暂存池
	 */
	@RequiresPermissions("reconciliation:reconciliationAccountCheckMistakeScratchPool:remove")
	@Log(title = "差错暂存池", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(reconciliationAccountCheckMistakeScratchPoolService.deleteReconciliationAccountCheckMistakeScratchPoolByIds(ids));
	}
	
}

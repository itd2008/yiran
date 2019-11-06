package com.yiran.web.controller.activiti;

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
import com.yiran.activiti.domain.ApprovalLeave;
import com.yiran.activiti.service.IApprovalLeaveService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;

/**
 * 请假审批 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/activiti/approvalLeave")
public class ApprovalLeaveController extends BaseController
{
    private String prefix = "activiti/approvalLeave";
	
	@Autowired
	private IApprovalLeaveService approvalLeaveService;
	
	@RequiresPermissions("activiti:approvalLeave:view")
	@GetMapping()
	public String approvalLeave()
	{
	    return prefix + "/approvalLeave";
	}
	
	/**
	 * 查询请假审批列表
	 */
	@RequiresPermissions("activiti:approvalLeave:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ApprovalLeave approvalLeave)
	{
		startPage();
        List<ApprovalLeave> list = approvalLeaveService.selectApprovalLeaveList(approvalLeave);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出请假审批列表
	 */
	@RequiresPermissions("activiti:approvalLeave:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ApprovalLeave approvalLeave)
    {
    	List<ApprovalLeave> list = approvalLeaveService.selectApprovalLeaveList(approvalLeave);
        ExcelUtil<ApprovalLeave> util = new ExcelUtil<ApprovalLeave>(ApprovalLeave.class);
        return util.exportExcel(list, "approvalLeave");
    }
	
	/**
	 * 新增请假审批
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存请假审批
	 */
	@RequiresPermissions("activiti:approvalLeave:add")
	@Log(title = "请假审批", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ApprovalLeave approvalLeave)
	{		
		return toAjax(approvalLeaveService.insertApprovalLeave(approvalLeave));
	}

	/**
	 * 修改请假审批
	 */
	@GetMapping("/edit/{auditId}")
	public String edit(@PathVariable("auditId") String auditId, ModelMap mmap)
	{
		ApprovalLeave approvalLeave = approvalLeaveService.selectApprovalLeaveById(auditId);
		mmap.put("approvalLeave", approvalLeave);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存请假审批
	 */
	@RequiresPermissions("activiti:approvalLeave:edit")
	@Log(title = "请假审批", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ApprovalLeave approvalLeave)
	{		
		return toAjax(approvalLeaveService.updateApprovalLeave(approvalLeave));
	}
	
	/**
	 * 删除请假审批
	 */
	@RequiresPermissions("activiti:approvalLeave:remove")
	@Log(title = "请假审批", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(approvalLeaveService.deleteApprovalLeaveByIds(ids));
	}
	
}

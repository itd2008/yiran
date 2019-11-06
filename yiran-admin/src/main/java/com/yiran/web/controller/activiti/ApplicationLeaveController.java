package com.yiran.web.controller.activiti;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.task.Task;
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
import com.yiran.activiti.constant.ActivitiConstant;
import com.yiran.activiti.domain.ApplicationLeave;
import com.yiran.activiti.domain.ApprovalLeave;
import com.yiran.activiti.domain.ProcessDefinitionDto;
import com.yiran.activiti.service.ActProcessService;
import com.yiran.activiti.service.ActTaskService;
import com.yiran.activiti.service.IApplicationLeaveService;
import com.yiran.activiti.service.IApprovalLeaveService;
import com.yiran.framework.util.ShiroUtils;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.SysUser;
import com.yiran.system.service.ISysUserService;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.yiran.common.page.TableDataInfo;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.utils.poi.ExcelUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 请假申请 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-02
 */
@Controller
@RequestMapping("/activiti/applicationLeave")
public class ApplicationLeaveController extends BaseController
{
	private static Logger logger = LoggerFactory.getLogger(ApplicationLeaveController.class);
    private String prefix = "activiti/applicationLeave";
    @Autowired
	private IApprovalLeaveService approvalLeaveService;
	@Autowired
	private IApplicationLeaveService applicationLeaveService;
	@Autowired
	private ActProcessService actProcessService;
	@Autowired
    TaskService taskService;
	@Autowired
	RuntimeService runtimeService;
	@Autowired
	ActTaskService actTaskService;
	@Autowired
	ISysUserService sysUserService;
	@RequiresPermissions("activiti:applicationLeave:view")
	@GetMapping()
	public String applicationLeave()
	{
	    return prefix + "/applicationLeave";
	}
	
	/**
	 * 查询请假申请列表
	 */
	@RequiresPermissions("activiti:applicationLeave:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ApplicationLeave applicationLeave)
	{
		startPage();
        List<ApplicationLeave> list = applicationLeaveService.selectApplicationLeaveList(applicationLeave);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出请假申请列表
	 */
	@RequiresPermissions("activiti:applicationLeave:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ApplicationLeave applicationLeave)
    {
    	List<ApplicationLeave> list = applicationLeaveService.selectApplicationLeaveList(applicationLeave);
        ExcelUtil<ApplicationLeave> util = new ExcelUtil<ApplicationLeave>(ApplicationLeave.class);
        return util.exportExcel(list, "applicationLeave");
    }
	
	/**
	 * 新增请假申请
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap,ProcessDefinitionDto processDefinitionDto)
	{
		List<ProcessDefinitionDto> rows = (List<ProcessDefinitionDto>) actProcessService.selectProcessDefinitionList(processDefinitionDto).getRows();
		mmap.put("pdList",rows);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存请假申请
	 */
	@RequiresPermissions("activiti:applicationLeave:add")
	@Log(title = "请假申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ApplicationLeave applicationLeave)
	{	
		logger.info("请假申请参数：{}",JSONUtil.toJsonStr(applicationLeave));
		SysUser user = ShiroUtils.getSysUser();
		logger.info("当前登录用户信息:{}",JSONUtil.toJsonStr(user));
		String formId = UUID.randomUUID().toString();
		String procDefId = applicationLeave.getProcessInstanceId();
		logger.info("流程定义ID：{}",procDefId);
		String businessTable =ActivitiConstant.LEAVE_BUSINESS_TABLE;
		logger.info("业务表表名：{}",businessTable);
		String businessId = formId;
		logger.info("业务表编号：{}",businessId);
		String applicationDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String title="EHR-请假申请审批流程--"+user.getUserName()+"--"+applicationDate;
		logger.info("流程标题：{}",title);
		Map<String, Object> vars = new HashMap<String, Object>();
		//applyUserId departmentManager hr deptLeaderApproved == 'true' reApply == 'true' hrApproved == 'true' 
		vars.put("applyUserId", user.getUserName());
		if (user.getParentUserId() != null 
				|| !ActivitiConstant.ROOT_PARENT_ID.equals(user.getParentUserId()) 
				||!ActivitiConstant.ADMIN_PARENT_ID.equals(user.getParentUserId()) ) {
			SysUser parentUser = sysUserService.selectUserById(user.getParentUserId());
			logger.info("根据父Id【{}】获取user对象:{}",user.getParentUserId(),JSONUtil.toJsonStr(parentUser));
			vars.put("departmentManager", parentUser.getUserName());
		}else{
			return AjaxResult.error("最大领导不用请假申请!");
		}
		vars.put("deptLeaderApproved", "true");
		//获取Hr人力资源审批(hr下所有的用户)
		List<SysUser> list = sysUserService.selectUserListByPostCode(ActivitiConstant.POST_CODE_HR);
		StringBuffer hrs = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			hrs.append(list.get(i).getUserName());
			if(i!=list.size()-1){
				hrs.append(",");
			}
		}
		vars.put("hr", hrs.toString());
		vars.put("hrApproved", "true");
		vars.put("reApply", "true");
		logger.info("流程参数：{}",JSONUtil.toJsonStr(vars));
		//启动流程变量
		Task task = actTaskService.startProcess(procDefId, businessTable, businessId, title,user.getUserName(), vars);
		//完成请假申请任务
		actTaskService.completeTask(task.getId(), null);
		//数据保存到业务表
		applicationLeave.setProcessInstanceId(task.getProcessInstanceId());
		applicationLeave.setFormId(formId);
		applicationLeave.setCreateBy(user.getUserName());
		applicationLeave.setUserId(user.getUserId().intValue());
		applicationLeave.setTitle(title);
		//流程状态：0申请，1审批中，2已完成
		applicationLeave.setProcessStatus(0);
		int flag = applicationLeaveService.insertApplicationLeave(applicationLeave);
		//添加审批表数据
		ApprovalLeave approvalLeave = new ApprovalLeave();
		approvalLeave.setAuditId(UUID.randomUUID().toString());//审批ID
		approvalLeave.setAuditResult(1);//审批结果：0不通过，1通过
		approvalLeave.setAuditTime(new Date());//审批时间
		approvalLeave.setComment("提交申请");//意见
		approvalLeave.setProcessInstanceId(task.getProcessInstanceId());//activiti流程实例ID
		approvalLeave.setTaskId(task.getId());//taskID
		approvalLeave.setUserId(user.getUserId().intValue());//userId
		approvalLeave.setCreateBy(user.getUserName());
		int leave = approvalLeaveService.insertApprovalLeave(approvalLeave);
		return toAjax(flag);
	}

	/**
	 * 审批请假申请页面
	 */
	@GetMapping("/approvalLeave/{formId}")
	public String approvalLeave(@PathVariable("formId") String formId, ModelMap mmap)
	{
		//获取请假信息
		ApplicationLeave applicationLeave = applicationLeaveService.selectApplicationLeaveById(formId);
		mmap.put("applicationLeave", applicationLeave);
		//用户基本信息
		SysUser user = sysUserService.selectUserById(applicationLeave.getUserId().longValue());
		mmap.put("user", user);
		//取审批详情
		List<ApprovalLeave> approvalLeaves= approvalLeaveService.selectApprovalLeavesByProcessInstanceId(applicationLeave.getProcessInstanceId());
		mmap.put("approvalLeaves", approvalLeaves);    
	    return prefix + "/approval";
	}
	
	/**
	 * 审批请假申请页面
	 */
	@GetMapping("/viewApproval/{formId}")
	public String viewApproval(@PathVariable("formId") String formId, ModelMap mmap)
	{
		//获取请假信息
		ApplicationLeave applicationLeave = applicationLeaveService.selectApplicationLeaveById(formId);
		mmap.put("applicationLeave", applicationLeave);
		//用户基本信息
		SysUser user = sysUserService.selectUserById(applicationLeave.getUserId().longValue());
		mmap.put("user", user);
		//取审批详情
		List<ApprovalLeave> approvalLeaves= approvalLeaveService.selectApprovalLeavesByProcessInstanceId(applicationLeave.getProcessInstanceId());
		mmap.put("approvalLeaves", approvalLeaves);    
	    return prefix + "/viewApproval";
	}
	/**
	 * 请假申请审批通过
	 */
	@Log(title = "请假申请审批通过", businessType = BusinessType.INSERT)
	@PostMapping("/approvalLeaveComplete/agree")
	@ResponseBody
	public AjaxResult approvalLeaveComplete(ApplicationLeave applicationLeave)
	{		
		logger.info("请假申请审批通过请求参数：{}",JSONUtil.toJsonStr(applicationLeave));
		SysUser user = ShiroUtils.getSysUser();
		if ("admin".equalsIgnoreCase(ShiroUtils.getLoginName())) {
			return AjaxResult.error("管理员不能审批!");
		}
		//获取请假信息
		ApplicationLeave leave = applicationLeaveService.selectApplicationLeaveById(applicationLeave.getFormId());
		if(ActivitiConstant.POST_CODE_HR.equals(sysUserService.selectPostCodeByUserId(user.getUserId().toString()))){//hr审批通过
			leave.setProcessStatus(2);//审批中
		}else{
			leave.setProcessStatus(1);//审批中
		}
		
		//Only select tasks foe the given business key 
		Task task = taskService.createTaskQuery().processInstanceId(leave.getProcessInstanceId()).singleResult();
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("deptLeaderApproved", "true");
		vars.put("hrApproved", "true");
		vars.put("reApply", "true");
		//完成审批
		actTaskService.completeTask(task.getId(), vars);
		//更新状态
		applicationLeaveService.updateApplicationLeave(leave);
		//添加审批表数据
		ApprovalLeave approvalLeave = new ApprovalLeave();
		approvalLeave.setAuditId(UUID.randomUUID().toString());//审批ID
		approvalLeave.setAuditResult(1);//审批结果：0不通过，1通过
		approvalLeave.setAuditTime(new Date());//审批时间
		approvalLeave.setComment(applicationLeave.getRemark());//意见
		approvalLeave.setProcessInstanceId(task.getProcessInstanceId());//activiti流程实例ID
		approvalLeave.setTaskId(task.getId());//taskID
		approvalLeave.setUserId(user.getUserId().intValue());//userId
		approvalLeave.setCreateBy(user.getUserName());
		int appleave = approvalLeaveService.insertApprovalLeave(approvalLeave);
		return toAjax(appleave);
	}
	
	/**
	 * 请假申请审批驳回
	 */
	@Log(title = "请假申请审批驳回", businessType = BusinessType.INSERT)
	@PostMapping("/rejectApprovalLeave/reject")
	@ResponseBody
	public AjaxResult rejectApprovalLeave(ApplicationLeave applicationLeave)
	{
		logger.info("请假申请审批驳回请求参数：{}",JSONUtil.toJsonStr(applicationLeave));
		SysUser user = ShiroUtils.getSysUser();
		if ("admin".equalsIgnoreCase(ShiroUtils.getLoginName())) {
			return AjaxResult.error("管理员不能审批!");
		}
		//获取请假信息
		ApplicationLeave leave = applicationLeaveService.selectApplicationLeaveById(applicationLeave.getFormId());
		leave.setProcessStatus(1);//审批中
		Task task = taskService.createTaskQuery().processInstanceId(leave.getProcessInstanceId()).singleResult();
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("deptLeaderApproved", "false");
		vars.put("hrApproved", "false");
		vars.put("reApply", "false");
		//完成审批
		actTaskService.completeTask(task.getId(), vars);
		//更新状态
		applicationLeaveService.updateApplicationLeave(leave);
		//添加审批表数据
		ApprovalLeave approvalLeave = new ApprovalLeave();
		approvalLeave.setAuditId(UUID.randomUUID().toString());//审批ID
		approvalLeave.setAuditResult(0);//审批结果：0不通过，1通过
		approvalLeave.setAuditTime(new Date());//审批时间
		approvalLeave.setComment(applicationLeave.getRemark());//意见
		approvalLeave.setProcessInstanceId(task.getProcessInstanceId());//activiti流程实例ID
		approvalLeave.setTaskId(task.getId());//taskID
		approvalLeave.setUserId(user.getUserId().intValue());//userId
		approvalLeave.setCreateBy(user.getUserName());
		int appleave = approvalLeaveService.insertApprovalLeave(approvalLeave);
		return toAjax(appleave);
	}
	/**
	 * 拾取任务
	 * @param formId
	 * @param mmap
	 * @return
	 */
	@GetMapping("/pickupTask/{formId}")
	@ResponseBody
	public AjaxResult pickupTask(@PathVariable("formId") String formId){
		logger.info("拾取任务业务ID:{}",formId);
		SysUser user = ShiroUtils.getSysUser();
		//获取请假信息
		ApplicationLeave leave = applicationLeaveService.selectApplicationLeaveById(formId);
		Task task = taskService.createTaskQuery().processInstanceId(leave.getProcessInstanceId()).singleResult();
		taskService.claim(task.getId(), user.getUserName());//拾取任务
		return AjaxResult.success("任务已经拾取，请到代办任务中办理任务");
	}
	
	/**
	 * 删除请假申请
	 */
	@RequiresPermissions("activiti:applicationLeave:remove")
	@Log(title = "请假申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
		return toAjax(applicationLeaveService.deleteApplicationLeaveByIds(ids));
	}
	
	
	/**
	 * 修改请假申请
	 */
	@GetMapping("/edit/{formId}")
	public String edit(@PathVariable("formId") String formId, ModelMap mmap,ProcessDefinitionDto processDefinitionDto)
	{
		ApplicationLeave applicationLeave = applicationLeaveService.selectApplicationLeaveById(formId);
		List<ProcessDefinitionDto> rows = (List<ProcessDefinitionDto>) actProcessService.selectProcessDefinitionList(processDefinitionDto).getRows();
		mmap.put("pdList",rows);
		mmap.put("applicationLeave", applicationLeave);
		//用户基本信息
		SysUser user = sysUserService.selectUserById(applicationLeave.getUserId().longValue());
		mmap.put("user", user);
		//取审批详情
		List<ApprovalLeave> approvalLeaves= approvalLeaveService.selectApprovalLeavesByProcessInstanceId(applicationLeave.getProcessInstanceId());
		mmap.put("approvalLeaves", approvalLeaves);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存请假申请
	 */
	@RequiresPermissions("activiti:applicationLeave:edit")
	@Log(title = "请假申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ApplicationLeave applicationLeave)
	{		
		return toAjax(applicationLeaveService.updateApplicationLeave(applicationLeave));
	}
	
	/**
	 * 调整申请结束任务
	 */
	@Log(title = "调整申请结束任务", businessType = BusinessType.INSERT)
	@PostMapping("/endApprovalLeave/endLeave")
	@ResponseBody
	public AjaxResult endApprovalLeave(ApplicationLeave applicationLeave)
	{
		logger.info("调整申请结束任务请求参数：{}",JSONUtil.toJsonStr(applicationLeave));
		SysUser user = ShiroUtils.getSysUser();
		if ("admin".equalsIgnoreCase(ShiroUtils.getLoginName())) {
			return AjaxResult.error("管理员不能审批!");
		}
		//获取请假信息
		ApplicationLeave leave = applicationLeaveService.selectApplicationLeaveById(applicationLeave.getFormId());
		leave.setProcessStatus(2);//审批完成
		Task task = taskService.createTaskQuery().processInstanceId(leave.getProcessInstanceId()).singleResult();
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("reApply", "false");
		//完成审批
		actTaskService.completeTask(task.getId(), vars);
		//更新状态
		applicationLeaveService.updateApplicationLeave(leave);
		//添加审批表数据
		ApprovalLeave approvalLeave = new ApprovalLeave();
		approvalLeave.setAuditId(UUID.randomUUID().toString());//审批ID
		approvalLeave.setAuditResult(0);//审批结果：0不通过，1通过
		approvalLeave.setAuditTime(new Date());//审批时间
		approvalLeave.setComment("结束流程");//意见
		approvalLeave.setProcessInstanceId(task.getProcessInstanceId());//activiti流程实例ID
		approvalLeave.setTaskId(task.getId());//taskID
		approvalLeave.setUserId(user.getUserId().intValue());//userId
		approvalLeave.setCreateBy(user.getUserName());
		int appleave = approvalLeaveService.insertApprovalLeave(approvalLeave);
		return toAjax(appleave);
	}
	
	/**
	 * 调整申请操作
	 */
	@Log(title = "调整申请操作", businessType = BusinessType.INSERT)
	@PostMapping("/reApplecationLeave")
	@ResponseBody
	public AjaxResult reApplecationLeave(ApplicationLeave applicationLeave)
	{
		logger.info("调整申请操作请求参数：{}",JSONUtil.toJsonStr(applicationLeave));
		SysUser user = ShiroUtils.getSysUser();
		if ("admin".equalsIgnoreCase(ShiroUtils.getLoginName())) {
			return AjaxResult.error("管理员不能审批!");
		}
		//获取请假信息
		ApplicationLeave leave = applicationLeaveService.selectApplicationLeaveById(applicationLeave.getFormId());
		leave.setProcessStatus(1);//审批完成
		Task task = taskService.createTaskQuery().processInstanceId(leave.getProcessInstanceId()).singleResult();
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("reApply", "true");
		//完成审批
		actTaskService.completeTask(task.getId(), vars);
		leave.setBeginDate(applicationLeave.getBeginDate());
		leave.setEndDate(applicationLeave.getEndDate());
		leave.setDays(applicationLeave.getDays());
		leave.setReason(applicationLeave.getReason());
		//更新状态
		applicationLeaveService.updateApplicationLeave(leave);
		//添加审批表数据
		ApprovalLeave approvalLeave = new ApprovalLeave();
		approvalLeave.setAuditId(UUID.randomUUID().toString());//审批ID
		approvalLeave.setAuditResult(1);//审批结果：0不通过，1通过
		approvalLeave.setAuditTime(new Date());//审批时间
		approvalLeave.setComment("重新提交流程申请");//意见
		approvalLeave.setProcessInstanceId(task.getProcessInstanceId());//activiti流程实例ID
		approvalLeave.setTaskId(task.getId());//taskID
		approvalLeave.setUserId(user.getUserId().intValue());//userId
		approvalLeave.setCreateBy(user.getUserName());
		int appleave = approvalLeaveService.insertApprovalLeave(approvalLeave);
		return toAjax(appleave);
	}
	
}

package com.yiran.web.controller.activiti;


import com.yiran.activiti.domain.ApplicationLeave;
import com.yiran.activiti.domain.ApprovalLeave;
import com.yiran.activiti.domain.TaskVO;
import com.yiran.activiti.service.ActTaskService;
import com.yiran.activiti.service.IApplicationLeaveService;
import com.yiran.activiti.service.IApprovalLeaveService;
import com.yiran.common.annotation.Log;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.enums.BusinessType;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.util.ShiroUtils;
import com.yiran.framework.web.base.BaseController;
import com.yiran.system.domain.SysUser;
import com.yiran.system.service.ISysUserService;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

/***
 * 代办任务
 */
@RequestMapping("activiti/task")
@Controller
public class TaskController extends BaseController {

    @Autowired
    ActTaskService actTaskService;
    @Autowired
	HistoryService historyService;
    @Autowired
    ISysUserService sysUserService;
    
    private String prefix = "activiti/task";
    /**
     * 进入代办任务页
     * @return
     */
    @RequiresPermissions("activiti:task:view")
    @GetMapping
    public   String task() {
        return prefix + "/tasks";
    }

    /**
     * 查看我的任务  admin 查看所有任务
     * @return
     */
    @RequiresPermissions("activiti:task:view")
    @RequestMapping("/list")
    @ResponseBody
    TableDataInfo list(TaskVO taskVO) {
        startPage(taskVO);
        if ("admin".equalsIgnoreCase(ShiroUtils.getLoginName())) {
            List<TaskVO> taskVOS = actTaskService.selectTaskList(taskVO);
            List<TaskVO> taskVOs = actTaskService.selectArchivedTask(taskVO);
            taskVOS.addAll(taskVOs);//合并结合
            List<TaskVO> processTask = actTaskService.selectProcessTask(null);
            taskVOS.removeAll(processTask);
            taskVOS.addAll(processTask);
            //删除taskId相同的任务
            return getDataTable(taskVOS);
        }
        taskVO.setAssignee(ShiroUtils.getSysUser().getUserName());
        List<TaskVO> taskVOS = actTaskService.selectTaskList(taskVO);
        taskVO.setOwner(ShiroUtils.getSysUser().getUserName());
        List<TaskVO> taskVOs = actTaskService.selectArchivedTask(taskVO);
        taskVOS.addAll(taskVOs);//合并结合
        List<TaskVO> processTask = actTaskService.selectProcessTask(String.valueOf(ShiroUtils.getSysUser().getUserId()));
        taskVOS.removeAll(processTask);
        taskVOS.addAll(processTask);//合并结合
        TableDataInfo dataTable = getDataTable(taskVOS);
        dataTable.setTotal(taskVO.getCount());
        return dataTable;
    }
    

    @RequiresPermissions("activiti:task:add")
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 申请页面
     * @return
     */
    @GetMapping("/application")
    public String application() {
        return prefix + "/application";
    }
    

    @RequiresPermissions("activiti:task:edit")
    @PostMapping("/edit/{taskId}")
    @ResponseBody
    public String edit(@PathVariable("taskId") String taskId, ModelMap map) {
        TaskVO taskVO = actTaskService.selectOneTask(taskId);
        map.put("task", taskVO);
        return prefix + "/edit";
    }


    @Log(title = "更新任务", businessType = BusinessType.OTHER)
    @RequiresPermissions("activiti:task:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TaskVO task, ModelMap map) {
        actTaskService.completeTask(task.getId(), map);
        return toAjax(1);
    }
    
    /**
     * 代办任务
     * @param task
     * @param map
     * @return
     */
    @PostMapping("/todo")
    @ResponseBody
    public TableDataInfo todo(TaskVO task,ModelMap map) {
        startPage(task);
        task.setAssignee(ShiroUtils.getSysUser().getUserName());
        List<TaskVO> taskVOs = actTaskService.selectTodoTask(task);
        TableDataInfo dataTable = getDataTable(taskVOs);
        dataTable.setTotal(task.getCount());
        return dataTable;
    }
    /**
     * 受邀任务
     * @param task
     * @param map
     * @return
     */
    @PostMapping("/involved")
    @ResponseBody
    public TableDataInfo selectInvolvedTask(TaskVO task,ModelMap map) {
        startPage(task);
        //task.setInvolvedUser(ShiroUtils.getSysUser().getUserName());
        task.setCandidateUser(ShiroUtils.getSysUser().getUserName());
        List<TaskVO> taskVOs = actTaskService.selectInvolvedTask(task);
        TableDataInfo dataTable = getDataTable(taskVOs);
        dataTable.setTotal(task.getCount());
        return dataTable;
    }
    /**
     * 归档任务
     * @param task
     * @param map
     * @return
     */
    @PostMapping("/archived")
    @ResponseBody
    public TableDataInfo selectArchivedTask(TaskVO task,ModelMap map) {
        startPage(task);
        task.setOwner(ShiroUtils.getSysUser().getUserName());
        List<TaskVO> taskVOs = actTaskService.selectArchivedTask(task);
        TableDataInfo dataTable = getDataTable(taskVOs);
        dataTable.setTotal(task.getCount());
        return dataTable;
    }

    /**
     * 查询完成的任务
     * @param task
     * @param map
     * @return
     */
    @PostMapping("/finishedTask")
    @ResponseBody
    public TableDataInfo finishedTask(TaskVO task, ModelMap map) {
        startPage(task);
        task.setAssignee(ShiroUtils.getSysUser().getUserName());
        task.setOwner(ShiroUtils.getSysUser().getUserName());
        List<TaskVO> taskVOs = actTaskService.selectFinishedTask(task);
        TableDataInfo dataTable = getDataTable(taskVOs);
        dataTable.setTotal(task.getCount());
        return dataTable;
    }

    /**
     * 读取带跟踪的图片
     */
    @RequestMapping(value = "/trace/photo/{procDefId}/{execId}")
    public void traceTaskPhoto(@PathVariable("procDefId") String procDefId, @PathVariable("execId") String execId, HttpServletResponse response) throws Exception {
        InputStream imageStream = actTaskService.traceTaskPhoto(procDefId, execId);
        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }


}

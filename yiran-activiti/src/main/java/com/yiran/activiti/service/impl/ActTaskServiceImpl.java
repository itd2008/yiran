package com.yiran.activiti.service.impl;

import com.yiran.activiti.domain.ApplicationLeave;
import com.yiran.activiti.domain.TaskVO;
import com.yiran.activiti.mapper.ApplicationLeaveMapper;
import com.yiran.activiti.service.ActTaskService;
import com.yiran.activiti.service.IApplicationLeaveService;
import com.yiran.common.utils.StringUtils;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.HistoricProcessInstanceEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 */
@Service
public class ActTaskServiceImpl implements ActTaskService {

    @Autowired
    TaskService taskService;
    @Autowired
    IdentityService identityService;
    @Autowired
    RuntimeService runtimeService;

    @Autowired
    FormService formService;
    @Autowired
    RepositoryService repositoryService;

    @Autowired
	private ApplicationLeaveMapper applicationLeaveMapper;
    @Autowired
    private ProcessEngineFactoryBean processEngineFactory;

    @Autowired
    IApplicationLeaveService applicationLeaveService;
    
    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private HistoryService historyService;

    @Override
    public List<TaskVO> selectTaskList(TaskVO task) {
        int[] pageing = task.paging();
        TaskQuery taskQuery = taskService.createTaskQuery();
        if (task.getId() != null) {
            taskQuery.taskId(task.getId());
        }
        if (task.getAssignee() != null) {
            taskQuery.taskAssignee(task.getAssignee());
        }
        if (task.getName() != null) {
            taskQuery.taskName(task.getName());
        }
        if (task.getDescription() != null) {
            taskQuery.taskDescription(task.getDescription());
        }

        if (task.getPriority() != null) {
            taskQuery.taskPriority(task.getPriority());
        }

        if (task.getAssignee() != null) {
            taskQuery.taskAssignee(task.getAssignee());
        }

        if (task.getOwner() != null) {
            taskQuery.taskOwner(task.getOwner());
        }

        if (task.getUnassigned() != null) {
            taskQuery.taskUnassigned();
        }
        if (task.getDelegationState() != null) {
            DelegationState state = getDelegationState(task.getDelegationState());
            if (state != null) {
                taskQuery.taskDelegationState(state);
            }
        }
        if (task.getCandidateUser() != null) {
            taskQuery.taskCandidateUser(task.getCandidateUser());
        }
        if (task.getInvolvedUser() != null) {
            taskQuery.taskInvolvedUser(task.getInvolvedUser());
        }
        if (task.getCandidateGroup() != null) {
            taskQuery.taskCandidateGroup(task.getCandidateGroup());
        }
        if (task.getCandidateGroupIn() != null) {
            taskQuery.taskCandidateGroupIn(task.getCandidateGroupIn());
        }
        if (task.getProcessInstanceId() != null) {
            taskQuery.processInstanceId(task.getProcessInstanceId());
        }
        if (task.getProcessInstanceIdIn() != null) {
            taskQuery.processInstanceIdIn(task.getProcessInstanceIdIn());
        }
        if (task.getProcessInstanceBusinessKey() != null) {
            taskQuery.processInstanceBusinessKey(task.getProcessInstanceBusinessKey());
        }
        if (task.getExecutionId() != null) {
            taskQuery.executionId(task.getExecutionId());
        }
        if (task.getCreatedOn() != null) {
            taskQuery.taskCreatedOn(task.getCreatedOn());
        }
        if (task.getCreatedBefore() != null) {
            taskQuery.taskCreatedBefore(task.getCreatedBefore());
        }
        if (task.getCreatedAfter() != null) {
            taskQuery.taskCreatedAfter(task.getCreatedAfter());
        }

        if (task.getTaskDefinitionKey() != null) {
            taskQuery.taskDefinitionKey(task.getTaskDefinitionKey());
        }
        if (task.getActive() != null) {
            if (task.getActive().booleanValue()) {
                taskQuery.active();
            } else {
                taskQuery.suspended();
            }
        }

        if (task.getProcessDefinitionId() != null) {
            taskQuery.processDefinitionId(task.getProcessDefinitionId());
        }

        if (task.getProcessDefinitionKey() != null) {
            taskQuery.processDefinitionKey(task.getProcessDefinitionKey());
        }

        List<TaskVO> tasks = taskQuery
                .orderByTaskId()
                .desc()
                .listPage(pageing[0], pageing[1]).stream().map(TaskVO::new)
                .collect(Collectors.toList());
        task.setCount(taskQuery.count());
        for (TaskVO taskVO : tasks) {
        	//根据流程编号获取业务编码
            ExecutionEntity result = (ExecutionEntity) runtimeService.createExecutionQuery().processInstanceId(taskVO.getProcessId())
           		.singleResult();
           //根据业务编码获取业务关联数据
           ApplicationLeave applicationLeave = applicationLeaveMapper.selectApplicationLeaveById(result.getBusinessKey());
           taskVO.setBusinessId(applicationLeave.getFormId());
           taskVO.setTitle(applicationLeave.getTitle());
		}
        return tasks;
    }

    @Override
    public TaskVO selectOneTask(String taskId) {
        return new TaskVO(taskService.createTaskQuery().taskId(taskId).singleResult());
    }

    /**
     * 提交任务, 并保存意见
     *
     * @param taskId    任务ID
     * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
     * @param comment   任务提交意见的内容
     * @param title     流程标题，显示在待办任务标题
     * @param vars      任务变量
     */
    @Override
    public void complete(String taskId, String procInsId, String comment, String title, Map<String, Object> vars) {
        // 添加意见
        if (StringUtils.isNotBlank(procInsId) && StringUtils.isNotBlank(comment)) {
            taskService.addComment(taskId, procInsId, comment);
        }
        // 设置流程变量
        if (vars == null) {
            vars = new HashMap<>();
        }
        // 设置流程标题
        if (StringUtils.isNotBlank(title)) {
            vars.put("title", title);
        }
        // 提交任务
        taskService.complete(taskId, vars);
    }


    /**
     * 启动流程
     *
     * @param procDefId    流程定义ID
     * @param businessTable 业务表表名
     * @param businessId    业务表编号
     * @param title         流程标题，显示在待办任务标题
     * @param vars          流程变量
     * @return task 任务对象
     */
    @Override
    public Task startProcess(String procDefId, String businessTable, String businessId, String title, String userId, Map<String, Object> vars) {
        //String userId = ShiroUtils.getUser().getUsername();//ObjectUtils.toString(UserUtils.getUser().getId())

        // 用来设置启动流程的人员ID，引擎会自动把用户ID保存到activiti:initiator中
        identityService.setAuthenticatedUserId(userId);

        // 设置流程变量
        if (vars == null) {
            vars = new HashMap();
        }

        // 设置流程标题
        if (StringUtils.isNotBlank(title)) {
            vars.put("title", title);
        }

        // 启动流程
        ProcessInstance procIns = runtimeService.startProcessInstanceById(procDefId, businessId, vars);
      //流程实例启动后，流程会跳转到请假申请节点
        Task task = taskService.createTaskQuery().processInstanceId(procIns.getId()).singleResult();
        return task;
    }

    /**
     * 获取流程表单（首先获取任务节点表单KEY，如果没有则取流程开始节点表单KEY）
     *
     * @return
     */
    @Override
    public String getFormKey(String procDefId, String taskDefKey) {
        String formKey = "";
        if (StringUtils.isNotBlank(procDefId)) {
            if (StringUtils.isNotBlank(taskDefKey)) {
                try {
                    formKey = formService.getTaskFormKey(procDefId, taskDefKey);
                } catch (Exception e) {
                    formKey = "";
                }
            }
            if (StringUtils.isBlank(formKey)) {
                formKey = formService.getStartFormKey(procDefId);
            }
            if (StringUtils.isBlank(formKey)) {
                formKey = "/404";
            }
        }
        return formKey;
    }

    @Override
    public void completeTask(String taskId, Map<String, Object> variables) {
        if (variables == null) {
            taskService.complete(taskId);
            return;
        }
        taskService.complete(taskId, variables);
    }

    /**
     * 代办任务
     */
    @Override
    public List<TaskVO> selectTodoTask(TaskVO taskVO) {
        int[] paging = taskVO.paging();
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskVO.setCount(taskQuery.count());
         List<TaskVO> list = taskQuery.taskAssignee(taskVO.getAssignee())
                .active()
                .orderByTaskId()
                .desc()
                .listPage(paging[0], paging[1])
                .stream()
                .map(TaskVO::new)
                .collect(Collectors.toList());
         for (TaskVO task : list) {
         	//根据流程编号获取业务编码
              ExecutionEntity result = (ExecutionEntity) runtimeService.createExecutionQuery().processInstanceId(task.getProcessId())
             		.singleResult();
             //根据业务编码获取业务关联数据
             ApplicationLeave applicationLeave = applicationLeaveMapper.selectApplicationLeaveById(result.getBusinessKey());
             task.setBusinessId(applicationLeave.getFormId());
             task.setTitle(applicationLeave.getTitle());			
 		}
         return list;
    }

    /**
     * 受邀任务
     */
    @Override
    public List<TaskVO> selectInvolvedTask(TaskVO taskVO) {
        int[] paging = taskVO.paging();
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskVO.setCount(taskQuery.count());
       /* List<TaskVO> list = taskQuery.taskInvolvedUser(taskVO.getInvolvedUser())
                .active()
                .orderByTaskId()
                .desc()
                .listPage(paging[0], paging[1])
                .stream()
                .map(TaskVO::new)
                .collect(Collectors.toList());*/
        List<TaskVO> list = taskQuery.taskCandidateUser(taskVO.getCandidateUser())
                .active()
                .orderByTaskId()
                .desc()
                .listPage(paging[0], paging[1])
                .stream()
                .map(TaskVO::new)
                .collect(Collectors.toList());
        for (TaskVO task : list) {
        	//根据流程编号获取业务编码
             ExecutionEntity result = (ExecutionEntity) runtimeService.createExecutionQuery().processInstanceId(task.getProcessId())
            		.singleResult();
            //根据业务编码获取业务关联数据
            ApplicationLeave applicationLeave = applicationLeaveMapper.selectApplicationLeaveById(result.getBusinessKey());
            task.setBusinessId(applicationLeave.getFormId());
            task.setTitle(applicationLeave.getTitle());			
		}
        
        return list;
    }

    /**
     * 归档任务
     */
    @Override
    public List<TaskVO> selectArchivedTask(TaskVO taskVO) {
        int[] paging = taskVO.paging();
        List<TaskVO> taskVOS = new ArrayList<>();
        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
        query.startedBy(taskVO.getOwner()).finished();
        taskVO.setCount(query.count());
        List<HistoricProcessInstance> historicTaskInstances = query
                .listPage(paging[0], paging[1]);
        for (HistoricProcessInstance instance : historicTaskInstances) {
            TaskVO task = new TaskVO();
            task.setId(instance.getId());
            task.setName("流程结束");
            task.setProcessInstanceId(instance.getId());
            task.setTaskDefinitionKey(instance.getProcessDefinitionKey());
            task.setProcessDefinitionId(instance.getId());
            task.setProcessId(instance.getId());
            task.setFormKey(instance.getBusinessKey());
            task.setKey(instance.getBusinessKey());
            task.setAssignee(instance.getStartUserId());
            task.setExecutionId(instance.getId());
           //根据流程编号获取业务编码
            HistoricProcessInstance result = historyService.createHistoricProcessInstanceQuery()
            		.processInstanceId(instance.getId())
            		.singleResult();
            //根据业务编码获取业务关联数据
            ApplicationLeave applicationLeave = applicationLeaveMapper.selectApplicationLeaveById(result.getBusinessKey());
            task.setBusinessId(applicationLeave.getFormId());
            task.setTitle(applicationLeave.getTitle());
            taskVOS.add(task);
        }
        return taskVOS;

    }

    /**
     * 已完成任务
     */
    @Override
    public List<TaskVO> selectFinishedTask(TaskVO taskVO) {
        int[] paging = taskVO.paging();
        List<TaskVO> taskVOS = new ArrayList<>();
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
        query.taskAssignee(taskVO.getAssignee()).finished();
        taskVO.setCount(query.count());
        List<HistoricTaskInstance> historicTaskInstances = query
                .listPage(paging[0], paging[1]);
        for (HistoricTaskInstance instance : historicTaskInstances) {
            TaskVO task = new TaskVO();
            task.setId(instance.getId());
            task.setName(instance.getName());
            task.setProcessInstanceId(instance.getProcessInstanceId());
            task.setTaskDefinitionKey(instance.getTaskDefinitionKey());
            task.setProcessDefinitionId(instance.getProcessDefinitionId());
            task.setProcessId(instance.getProcessInstanceId());
            task.setFormKey(instance.getFormKey());
            task.setKey(instance.getTaskDefinitionKey());
            task.setAssignee(instance.getAssignee());
            task.setExecutionId(instance.getExecutionId());
            //根据流程编号获取业务编码
            HistoricProcessInstance result = historyService.createHistoricProcessInstanceQuery()
            		.processInstanceId(instance.getProcessInstanceId())
            		.singleResult();
            //根据业务编码获取业务关联数据
            ApplicationLeave applicationLeave = applicationLeaveMapper.selectApplicationLeaveById(result.getBusinessKey());
            task.setBusinessId(applicationLeave.getFormId());
            task.setTitle(applicationLeave.getTitle());
            taskVOS.add(task);
        }
        return taskVOS;
    }
    @Override
    public Map<String, List<TaskVO>> selectGroupQueueTask(TaskVO taskVO) {
        List<String> groups = identityService.createGroupQuery().list().stream().map(Group::getId).collect(Collectors.toList());
        TaskQuery taskQuery = taskService.createTaskQuery();
        HashMap<String, List<TaskVO>> map = new HashMap<>();
        for (String group : groups) {
            map.put(group,taskQuery.taskCandidateGroup(group).taskUnassigned().orderByTaskId().asc()
                    .list()
                    .stream()
                    .map(TaskVO::new)
                    .collect(Collectors.toList()));
        }
        return map;
    }


    @Override
    public InputStream traceTaskPhoto(String xx, String pProcessInstanceId) {

        //  获取历史流程实例
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(pProcessInstanceId).singleResult();

        if (historicProcessInstance != null) {
            // 获取流程定义
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());

            // 获取流程历史中已执行节点，并按照节点在流程中执行先后顺序排序
            List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(pProcessInstanceId).orderByHistoricActivityInstanceId().asc().list();

            // 已执行的节点ID集合
            List<String> executedActivityIdList = new ArrayList<String>();
            int index = 1;
            //获取已经执行的节点ID
            for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
                executedActivityIdList.add(activityInstance.getActivityId());
                index++;
            }

            // 已执行的线集合
            List<String> flowIds = new ArrayList<String>();
            // 获取流程走过的线
            flowIds = getHighLightedFlows(processDefinition, historicActivityInstanceList);


            BpmnModel bpmnModel = repositoryService
                    .getBpmnModel(historicProcessInstance.getProcessDefinitionId());
            // 获取流程图图像字符流
            ProcessDiagramGenerator pec = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();
            //配置字体

            InputStream imageStream = pec.generateDiagram(bpmnModel, "png", executedActivityIdList, flowIds, "宋体", "微软雅黑", "黑体", null, 2.0);
            return imageStream;
        }
        return null;
    }


    /**
     * 获取需要高亮的线
     *
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {
        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }

    protected DelegationState getDelegationState(String delegationState) {
        DelegationState state = null;
        if (delegationState != null) {
            if (DelegationState.RESOLVED.name().toLowerCase().equals(delegationState)) {
                return DelegationState.RESOLVED;
            } else if (DelegationState.PENDING.name().toLowerCase().equals(delegationState)) {
                return DelegationState.PENDING;
            } else {
                throw new ActivitiIllegalArgumentException("Illegal value for delegationState: " + delegationState);
            }
        }
        return state;
    }

	@Override
	public List<TaskVO> selectProcessTask(String userId) {
		ApplicationLeave applicationLeave = new ApplicationLeave();
		if(!StringUtils.isBlank(userId)){
			applicationLeave.setUserId(Integer.parseInt(userId));
		}
		applicationLeave.setProcessStatus(2);//取不成功的
		List<ApplicationLeave> leaveList = applicationLeaveService.selectProcessApplicationLeaveList(applicationLeave);
		List<TaskVO> list = new ArrayList<TaskVO>();
		for (ApplicationLeave leave : leaveList) {
			TaskQuery taskQuery = taskService.createTaskQuery();
			taskQuery.processInstanceId(leave.getProcessInstanceId());
			Task task2 = taskQuery.singleResult();
			TaskVO task = new TaskVO();
			task.setId(task2.getId());
            task.setName(task2.getName());
            task.setProcessInstanceId(task2.getProcessInstanceId());
            task.setTaskDefinitionKey(task2.getTaskDefinitionKey());
            task.setProcessDefinitionId(task2.getProcessDefinitionId());
            task.setProcessId(task2.getProcessInstanceId());
            task.setFormKey(task2.getFormKey());
            task.setKey(task2.getTaskDefinitionKey());
            task.setAssignee(task2.getAssignee());
            task.setExecutionId(task2.getExecutionId());
            task.setBusinessId(leave.getFormId());
            task.setTitle(leave.getTitle());
			list.add(task);
		}
		return list;
	}


}


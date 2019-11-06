package com.yiran.activiti.domain;

import org.activiti.engine.task.Task;

import com.yiran.common.base.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 任务封装
 */
public class TaskVO extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7907574121242663537L;


	public TaskVO() {

    }

    public TaskVO(Task task) {
        this.setId(task.getId());
        this.setKey(task.getTaskDefinitionKey());
        this.setName(task.getName());
        this.setDescription(task.getDescription());
        this.setAssignee(task.getAssignee());
        this.setFormKey(task.getFormKey());
        this.setProcessId(task.getProcessInstanceId());
        this.setProcessDefinitionId(task.getProcessDefinitionId());
        this.setExecutionId(task.getExecutionId());
    }

    /**
     * 任务编号
     */
    private String id;
    /**
     * 任务名称
     */
    private String name;

    private Boolean active;

    private String key;

    private String description;

    private String formKey;

    private String assignee;

    private String processId;

    private String processDefinitionId;

    private String executionId;

    private Integer priority;

    private String owner;

    private Boolean unassigned;

    private String delegationState;

    private String candidateUser;

    private String candidateGroup;

    private List<String> candidateGroupIn;

    private String involvedUser;

    private String processInstanceId;

    private String processInstanceBusinessKey;

    private List<String> processInstanceIdIn;

    private String processDefinitionKey;

    private Date createdOn;
    private Date createdBefore;
    private Date createdAfter;

    private String taskDefinitionKey;

    /**
     * 标题
     */
    private String title;
    /**
     * 业务ID
     */
    private String businessId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFormKey() {
        return formKey;
    }

    public void setFormKey(String formKey) {
        this.formKey = formKey;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Boolean getUnassigned() {
        return unassigned;
    }

    public void setUnassigned(Boolean unassigned) {
        this.unassigned = unassigned;
    }

    public String getDelegationState() {
        return delegationState;
    }

    public void setDelegationState(String delegationState) {
        this.delegationState = delegationState;
    }

    public String getCandidateUser() {
        return candidateUser;
    }

    public void setCandidateUser(String candidateUser) {
        this.candidateUser = candidateUser;
    }

    public String getCandidateGroup() {
        return candidateGroup;
    }

    public void setCandidateGroup(String candidateGroup) {
        this.candidateGroup = candidateGroup;
    }

    public List<String> getCandidateGroupIn() {
        return candidateGroupIn;
    }

    public void setCandidateGroupIn(List<String> candidateGroupIn) {
        this.candidateGroupIn = candidateGroupIn;
    }

    public String getInvolvedUser() {
        return involvedUser;
    }

    public void setInvolvedUser(String involvedUser) {
        this.involvedUser = involvedUser;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceBusinessKey() {
        return processInstanceBusinessKey;
    }

    public void setProcessInstanceBusinessKey(String processInstanceBusinessKey) {
        this.processInstanceBusinessKey = processInstanceBusinessKey;
    }


    public List<String> getProcessInstanceIdIn() {
        return processInstanceIdIn;
    }

    public void setProcessInstanceIdIn(List<String> processInstanceIdIn) {
        this.processInstanceIdIn = processInstanceIdIn;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCreatedBefore() {
        return createdBefore;
    }

    public void setCreatedBefore(Date createdBefore) {
        this.createdBefore = createdBefore;
    }

    public Date getCreatedAfter() {
        return createdAfter;
    }

    public void setCreatedAfter(Date createdAfter) {
        this.createdAfter = createdAfter;
    }


    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }
    
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	@Override
	public String toString() {
		return "TaskVO [id=" + id + ", name=" + name + ", active=" + active + ", key=" + key + ", description="
				+ description + ", formKey=" + formKey + ", assignee=" + assignee + ", processId=" + processId
				+ ", processDefinitionId=" + processDefinitionId + ", executionId=" + executionId + ", priority="
				+ priority + ", owner=" + owner + ", unassigned=" + unassigned + ", delegationState=" + delegationState
				+ ", candidateUser=" + candidateUser + ", candidateGroup=" + candidateGroup + ", candidateGroupIn="
				+ candidateGroupIn + ", involvedUser=" + involvedUser + ", processInstanceId=" + processInstanceId
				+ ", processInstanceBusinessKey=" + processInstanceBusinessKey + ", processInstanceIdIn="
				+ processInstanceIdIn + ", processDefinitionKey=" + processDefinitionKey + ", createdOn=" + createdOn
				+ ", createdBefore=" + createdBefore + ", createdAfter=" + createdAfter + ", taskDefinitionKey="
				+ taskDefinitionKey + ", title=" + title + ", businessId=" + businessId + "]";
	}

	@Override
	public boolean equals(Object obj) {
		TaskVO task = (TaskVO) obj;
		if(id.equals(task.getId())){
			return true;
		}
		return false;
	}

	
}

package com.yiran.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;

/**
 * 请假审批表 biz_approval_leave
 * 
 * @author yiran
 * @date 2019-03-02
 */
public class ApprovalLeave extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 审批ID */
	private String auditId;
	/** activiti流程实例ID */
	private String processInstanceId;
	/** activiti任务ID */
	private String taskId;
	/** 用户ID */
	private Integer userId;
	/** 审批结果：0不通过，1通过 */
	private Integer auditResult;
	/** 审批意见 */
	private String comment;
	/** 审批日期 */
	private Date auditTime;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createTime;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateTime;
	/** 备注 */
	private String remark;
	/**
	 * 审批人
	 */
	private String userName;
	/**
	 * 审批人所在部门
	 */
	private String deptName;

	public void setAuditId(String auditId) 
	{
		this.auditId = auditId;
	}

	public String getAuditId() 
	{
		return auditId;
	}
	public void setProcessInstanceId(String processInstanceId) 
	{
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceId() 
	{
		return processInstanceId;
	}
	public void setTaskId(String taskId) 
	{
		this.taskId = taskId;
	}

	public String getTaskId() 
	{
		return taskId;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setAuditResult(Integer auditResult) 
	{
		this.auditResult = auditResult;
	}

	public Integer getAuditResult() 
	{
		return auditResult;
	}
	public void setComment(String comment) 
	{
		this.comment = comment;
	}

	public String getComment() 
	{
		return comment;
	}
	public void setAuditTime(Date auditTime) 
	{
		this.auditTime = auditTime;
	}

	public Date getAuditTime() 
	{
		return auditTime;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("auditId", getAuditId())
            .append("processInstanceId", getProcessInstanceId())
            .append("taskId", getTaskId())
            .append("userId", getUserId())
            .append("auditResult", getAuditResult())
            .append("comment", getComment())
            .append("auditTime", getAuditTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
    
    
}

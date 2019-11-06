package com.yiran.activiti.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 * 请假申请表 biz_application_leave
 * 
 * @author yiran
 * @date 2019-03-02
 */
public class ApplicationLeave extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 表单ID */
	private String formId;
	/** activiti流程实例ID */
	private String processInstanceId;
	/** 用户ID */
	private Integer userId;
	/**
	 * 标题
	 */
	private String title;
	/** 请假天数 */
	private Integer days;
	/** 开始日期 */
	private Date beginDate;
	/** 结束日期 */
	private Date endDate;
	/** 请假类型：0年假，1病假，2事情 */
	private String vacationType;
	/** 请假事由 */
	private String reason;
	/** 流程状态：0申请，1审批中，2已完成 */
	private Integer processStatus;
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
	
	public void setFormId(String formId) 
	{
		this.formId = formId;
	}

	public String getFormId() 
	{
		return formId;
	}
	public void setProcessInstanceId(String processInstanceId) 
	{
		this.processInstanceId = processInstanceId;
	}

	public String getProcessInstanceId() 
	{
		return processInstanceId;
	}
	public void setUserId(Integer userId) 
	{
		this.userId = userId;
	}

	public Integer getUserId() 
	{
		return userId;
	}
	public void setDays(Integer days) 
	{
		this.days = days;
	}

	public Integer getDays() 
	{
		return days;
	}
	public void setBeginDate(Date beginDate) 
	{
		this.beginDate = beginDate;
	}

	public Date getBeginDate() 
	{
		return beginDate;
	}
	public void setEndDate(Date endDate) 
	{
		this.endDate = endDate;
	}

	public Date getEndDate() 
	{
		return endDate;
	}
	public void setVacationType(String vacationType) 
	{
		this.vacationType = vacationType;
	}

	public String getVacationType() 
	{
		return vacationType;
	}
	public void setReason(String reason) 
	{
		this.reason = reason;
	}

	public String getReason() 
	{
		return reason;
	}
	public void setProcessStatus(Integer processStatus) 
	{
		this.processStatus = processStatus;
	}

	public Integer getProcessStatus() 
	{
		return processStatus;
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


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("formId", getFormId())
            .append("processInstanceId", getProcessInstanceId())
            .append("userId", getUserId())
            .append("title",getTitle())
            .append("days", getDays())
            .append("beginDate", getBeginDate())
            .append("endDate", getEndDate())
            .append("vacationType", getVacationType())
            .append("reason", getReason())
            .append("processStatus", getProcessStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}

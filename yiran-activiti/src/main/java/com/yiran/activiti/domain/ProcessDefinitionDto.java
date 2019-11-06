package com.yiran.activiti.domain;

import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;

import com.yiran.common.base.BaseActDto;

/**
 * 流程实体数据对象
 * 
 * @author ruoyi
 */
public class ProcessDefinitionDto extends BaseActDto
{
    private static final long serialVersionUID = 1L;

    /**
     * 流程编号
     */
    private String processId;

    /**
     * 流程名称
     */
    private String name;

    /**
     * 部署编号
     */
    private String deploymentId;

    /**
     * 流程命名空间（该编号就是流程文件targetNamespace的属性值）
     */
    private String category;

    /**
     * 流程编号（该编号就是流程文件process元素的id属性值）
     */
    private String key;

    /**
     * 资源文件名称
     */
    private String resourceName;

    /**
     * 图片资源文件名称
     */
    private String diagramResourceName;

    /**
     * 版本号
     */
    private int version;

    private Boolean suspended;
    private Boolean latest;
    private String tenantId;
    private String startableByUser;

    /**
     * 描述信息
     */
    private String description;

    public ProcessDefinitionDto(Deployment processDefinition)
    {
        this.setProcessId(processDefinition.getId());
        this.name = processDefinition.getName();
    }

    public ProcessDefinitionDto(ProcessDefinition processDefinition)
    {
        this.setProcessId(processDefinition.getId());
        this.name = processDefinition.getName();
        this.deploymentId = processDefinition.getDeploymentId();
        this.category = processDefinition.getCategory();
        this.key = processDefinition.getKey();
        this.resourceName = processDefinition.getResourceName();
        this.diagramResourceName = processDefinition.getDiagramResourceName();
        this.version = processDefinition.getVersion();
        this.description = processDefinition.getDescription();
        this.suspended = processDefinition.isSuspended();
        this.tenantId = processDefinition.getTenantId();
        this.description = processDefinition.getDescription();
    }

    public ProcessDefinitionDto()
    {

    }

    public String getProcessId()
    {
        return processId;
    }

    public void setProcessId(String processId)
    {
        this.processId = processId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDeploymentId()
    {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId)
    {
        this.deploymentId = deploymentId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getResourceName()
    {
        return resourceName;
    }

    public void setResourceName(String resourceName)
    {
        this.resourceName = resourceName;
    }

    public String getDiagramResourceName()
    {
        return diagramResourceName;
    }

    public void setDiagramResourceName(String diagramResourceName)
    {
        this.diagramResourceName = diagramResourceName;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    public Boolean getSuspended()
    {
        return suspended;
    }

    public void setSuspended(Boolean suspended)
    {
        this.suspended = suspended;
    }

    public Boolean getLatest()
    {
        return latest;
    }

    public void setLatest(Boolean latest)
    {
        this.latest = latest;
    }

    public String getTenantId()
    {
        return tenantId;
    }

    public void setTenantId(String tenantId)
    {
        this.tenantId = tenantId;
    }

    public String getStartableByUser()
    {
        return startableByUser;
    }

    public void setStartableByUser(String startableByUser)
    {
        this.startableByUser = startableByUser;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

	@Override
	public String toString() {
		return "ProcessDefinitionDto [processId=" + processId + ", name=" + name + ", deploymentId=" + deploymentId
				+ ", category=" + category + ", key=" + key + ", resourceName=" + resourceName
				+ ", diagramResourceName=" + diagramResourceName + ", version=" + version + ", suspended=" + suspended
				+ ", latest=" + latest + ", tenantId=" + tenantId + ", startableByUser=" + startableByUser
				+ ", description=" + description + "]";
	}
    
    
}

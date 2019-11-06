package com.yiran.activiti.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yiran.common.base.BaseActDto;

/**
 * 模型实体数据对象
 * 
 * @author yiran
 */
public class ModelEntityDto extends BaseActDto
{
    private static final long serialVersionUID = 1L;

    /**
     * 模型编号
     */
    protected String id;

    /**
     * 模型名称
     */
    protected String name;

    /**
     * 模型标识
     */
    protected String key;

    /**
     * 流程命名空间（该编号就是流程文件targetNamespace的属性值）
     */
    protected String category;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 最后更新时间
     */
    protected Date lastUpdateTime;

    /**
     * 版本号
     */
    protected Integer version = 1;

    /**
     * 标签信息
     */
    protected String metaInfo;

    /**
     * 部署编号
     */
    protected String deploymentId;

    /**
     * ID：act_ge_bytearray表
     */
    protected String editorSourceValueId;

    /**
     * ID：act_ge_bytearray表，编辑后保存的
     */
    protected String editorSourceExtraValueId;

    public Object getPersistentState()
    {
        Map<String, Object> persistentState = new HashMap<String, Object>();
        persistentState.put("name", this.name);
        persistentState.put("key", key);
        persistentState.put("category", this.category);
        persistentState.put("createTime", this.createTime);
        persistentState.put("lastUpdateTime", lastUpdateTime);
        persistentState.put("version", this.version);
        persistentState.put("metaInfo", this.metaInfo);
        persistentState.put("deploymentId", deploymentId);
        persistentState.put("editorSourceValueId", this.editorSourceValueId);
        persistentState.put("editorSourceExtraValueId", this.editorSourceExtraValueId);
        return persistentState;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getVersion()
    {
        return version;
    }

    public void setVersion(Integer version)
    {
        this.version = version;
    }

    public String getMetaInfo()
    {
        return metaInfo;
    }

    public void setMetaInfo(String metaInfo)
    {
        this.metaInfo = metaInfo;
    }

    public String getDeploymentId()
    {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId)
    {
        this.deploymentId = deploymentId;
    }

    public String getEditorSourceValueId()
    {
        return editorSourceValueId;
    }

    public void setEditorSourceValueId(String editorSourceValueId)
    {
        this.editorSourceValueId = editorSourceValueId;
    }

    public String getEditorSourceExtraValueId()
    {
        return editorSourceExtraValueId;
    }

    public void setEditorSourceExtraValueId(String editorSourceExtraValueId)
    {
        this.editorSourceExtraValueId = editorSourceExtraValueId;
    }

    public boolean hasEditorSource()
    {
        return this.editorSourceValueId != null;
    }

    public boolean hasEditorSourceExtra()
    {
        return this.editorSourceExtraValueId != null;
    }
}

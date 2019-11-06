package com.yiran.activiti.service;

import java.io.InputStream;
import org.activiti.engine.repository.Model;

import com.yiran.activiti.domain.ProcessDefinitionDto;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.page.TableDataInfo;

/**
 * 流程管理 服务层
 * 
 * @author ruoyi
 */
public interface ActProcessService
{
    /**
     * 将流程定义转换成模型
     * 
     * @param processId 流程编号
     * @return 模型数据
     * @throws Exception
     */
    public Model convertToModel(String processId) throws Exception;

    /**
     * 使用部署对象ID查看流程图
     * 
     * @param deploymentId 部署id
     * @param imageName 资源文件名
     * @return 文件流
     */
    public InputStream findImageStream(String deploymentId, String imageName) throws Exception;

    /**
     * 查询流程定义
     * 
     * @param processDefinition 流程信息
     * @return 流程集合
     */
    public TableDataInfo selectProcessDefinitionList(ProcessDefinitionDto processDefinition);

    /**
     * 部署流程定义
     * 
     * @param is 文件流
     * @param fileName 文件名称
     * @param category 类型
     * @return 结果
     */
    public AjaxResult saveNameDeplove(InputStream is, String fileName, String category);

    /**
     * 根据流程部署id，删除流程定义
     * 
     * @param ids 部署ids
     * @return 结果
     */
    public boolean deleteProcessDefinitionByDeploymentIds(String ids);
}

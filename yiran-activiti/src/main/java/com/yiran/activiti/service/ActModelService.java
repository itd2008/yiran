package com.yiran.activiti.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yiran.activiti.domain.ModelEntityDto;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.page.TableDataInfo;

public interface ActModelService
{
    /**
     * 查询模型列表
     * 
     * @param modelEntityDto 模型信息
     * @return 模型集合
     */
    public TableDataInfo selectModelList(ModelEntityDto modelEntityDto);

    /**
     * 查询模型编辑器
     * 
     * @param modelId 模型ID
     * @return json信息
     */
    public ObjectNode selectWrapModelById(String modelId);

    /**
     * 查询模型信息
     * 
     * @param modelId 模型ID
     * @return 模型信息
     */
    public Model selectModelById(String modelId);

    /**
     * 创建模型
     * 
     * @param model 模型信息
     * @return 模型ID
     * @throws UnsupportedEncodingException
     */
    public String createModel(Model model) throws UnsupportedEncodingException;

    /**
     * 修改模型信息
     * 
     * @param model 模型信息
     * @param json_xml json参数
     * @param svg_xml xml参数
     * @throws IOException
     * @throws TranscoderException
     */
    public void update(Model model, String json_xml, String svg_xml) throws IOException, TranscoderException;

    /**
     * 批量删除模型信息
     * 
     * @param ids 需要删除的数据ID
     * @return
     */
    public boolean deleteModelIds(String ids);

    /**
     * 发布模型为流程定义
     * 
     * @param modelId 模型ID
     * @return
     * @throws Exception
     */
    public AjaxResult deployProcess(String modelId) throws IOException;

    /**
     * 获取资源文件信息
     * 
     * @param modelId 模型ID
     * @return 资源文件信息
     */
    public byte[] getModelEditorSource(String modelId);

}

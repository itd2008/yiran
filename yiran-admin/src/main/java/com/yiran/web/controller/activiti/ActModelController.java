package com.yiran.web.controller.activiti;

import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_DESCRIPTION;
import static org.activiti.editor.constants.ModelDataJsonConstants.MODEL_NAME;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletResponse;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.repository.Model;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.yiran.activiti.domain.ModelEntityDto;
import com.yiran.activiti.service.ActModelService;
import com.yiran.common.annotation.Log;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.Global;
import com.yiran.common.enums.BusinessType;
import com.yiran.common.page.TableDataInfo;
import com.yiran.framework.web.base.BaseController;

/**
 * 模型管理 操作处理
 * 
 * @author yiran
 */
@Controller
@RequestMapping("/activiti/model")
public class ActModelController extends BaseController
{
    private String prefix = "activiti/model";

    @Autowired
    private ActModelService actModelService;

    @Autowired
    private ObjectMapper objectMapper;

    @RequiresPermissions("activiti:model:view")
    @GetMapping
    public String model()
    {
        return prefix + "/model";
    }

    @RequiresPermissions("activiti:model:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ModelEntityDto modelEntityDto)
    {
        return actModelService.selectModelList(modelEntityDto);
    }

    @GetMapping("/add")
    public String add() throws UnsupportedEncodingException
    {
        ModelEntity model = new ModelEntity();
        String name = "new-process";
        String description = "新的流程";
        int revision = 1;
        String key = "process";
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, name);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);
        model.setName(name);
        model.setKey(key);
        model.setMetaInfo(modelNode.toString());
        String modelId = actModelService.createModel(model);
        return redirect("/modeler.html?modelId=" + modelId);
    }

    @GetMapping("/{modelId}/json")
    @ResponseBody
    public ObjectNode getEditorJson(@PathVariable String modelId)
    {
        ObjectNode modelNode = actModelService.selectWrapModelById(modelId);
        return modelNode;
    }

    @GetMapping("/edit/{modelId}")
    public String edit(@PathVariable("modelId") String modelId)
    {
        return redirect("/modeler.html?modelId=" + modelId);
    }

    @GetMapping(value = "/editor/stencilset", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String getStencilset() throws IOException
    {
        InputStream stencilsetStream = this.getClass().getClassLoader().getResourceAsStream("stencilset.json");
        return IOUtils.toString(stencilsetStream, "utf-8");
    }

    @Log(title = "删除模型", businessType = BusinessType.DELETE)
    @RequiresPermissions("activiti:model:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
    	if(DEMOENABLED.equals(Global.isDemoEnabled())){
			return error("当前模式是演示模式不能修改数据");
		}
        return toAjax(actModelService.deleteModelIds(ids));
    }

    /**
     * 发布流程
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Log(title = "发布流程", businessType = BusinessType.UPDATE)
    @RequiresPermissions("activiti:model:deploy")
    @GetMapping("/deploy/{modelId}")
    @ResponseBody
    public AjaxResult deploy(@PathVariable("modelId") String modelId) throws Exception
    {
        return actModelService.deployProcess(modelId);
    }

    @Log(title = "模型管理", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/{modelId}/save")
    @ResponseBody
    public void save(@PathVariable String modelId, String name, String description, String json_xml,
            String svg_xml) throws IOException, TranscoderException
    {
        Model model = actModelService.selectModelById(modelId);
        ObjectNode modelJson = (ObjectNode) objectMapper.readTree(model.getMetaInfo());
        modelJson.put(MODEL_NAME, name);
        modelJson.put(MODEL_DESCRIPTION, description);
        model.setMetaInfo(modelJson.toString());
        model.setName(name);
        actModelService.update(model, json_xml, svg_xml);

    }

    @Log(title = "导出指定模型", businessType = BusinessType.EXPORT)
    @RequiresPermissions("activiti:model:export")
    @RequestMapping("/export/{id}")
    public void exportToXml(@PathVariable("id") String id, HttpServletResponse response)
    {
        try
        {
            org.activiti.engine.repository.Model modelData = actModelService.selectModelById(id);
            BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
            JsonNode editorNode = new ObjectMapper().readTree(actModelService.getModelEditorSource(modelData.getId()));
            BpmnModel bpmnModel = jsonConverter.convertToBpmnModel(editorNode);
            BpmnXMLConverter xmlConverter = new BpmnXMLConverter();
            byte[] bpmnBytes = xmlConverter.convertToXML(bpmnModel);

            ByteArrayInputStream in = new ByteArrayInputStream(bpmnBytes);
            IOUtils.copy(in, response.getOutputStream());
            String filename = bpmnModel.getMainProcess().getId() + ".bpmn20.xml";
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.flushBuffer();
        }
        catch (Exception e)
        {
            throw new ActivitiException("导出model的xml文件失败，模型ID=" + id, e);
        }
    }
}

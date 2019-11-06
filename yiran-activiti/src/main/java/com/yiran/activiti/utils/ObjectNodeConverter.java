package com.yiran.activiti.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.DelegationState;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ObjectNodeConverter
{

    @Autowired
    protected ObjectMapper objectMapper;

    /**
     * 转换流程定义
     *
     * @param processDefinition
     * @return
     */
    public ObjectNode processDefinition2ObjectNode(ProcessDefinition processDefinition)
    {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("name", processDefinition.getName());
        objectNode.put("deploymentId", processDefinition.getDeploymentId());
        objectNode.put("key", processDefinition.getKey());
        objectNode.put("version", processDefinition.getVersion());
        objectNode.put("resourceName", processDefinition.getResourceName());
        objectNode.put("category", processDefinition.getCategory());
        objectNode.put("id", processDefinition.getId());
        objectNode.put("description", processDefinition.getDescription());
        objectNode.put("diagramResourceName", processDefinition.getDiagramResourceName());
        objectNode.put("tenantId", processDefinition.getTenantId());
        return objectNode;
    }

    public ArrayNode processDefinitions2ArrayNode(List<ProcessDefinition> processDefinitions)
    {
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (ProcessDefinition processDefinition : processDefinitions)
        {
            arrayNode.add(processDefinition2ObjectNode(processDefinition));
        }
        return arrayNode;
    }

    /**
     * 转换任务属性
     * 
     * @param task
     * @return
     */
    public ObjectNode task2ObjectNode(Task task)
    {
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("assignee", task.getAssignee());
        objectNode.put("id", task.getId());
        Date dueDate = task.getDueDate();
        objectNode.put("dueDate", dueDate != null ? date2String(dueDate) : "");
        objectNode.put("owner", task.getOwner());
        DelegationState delegationState = task.getDelegationState();
        ObjectNode delegationStateObject = objectMapper.createObjectNode();
        delegationStateObject.put("name", delegationState.name());
        delegationStateObject.put("ordinal", delegationState.ordinal());
        objectNode.set("delegationState", delegationStateObject);
        objectNode.put("name", task.getName());
        objectNode.put("category", task.getCategory());
        Date createTime = task.getCreateTime();
        objectNode.put("createTime", createTime != null ? date2String(createTime) : "");
        objectNode.put("description", task.getDescription());
        objectNode.put("executionId", task.getExecutionId());
        objectNode.put("formKey", task.getFormKey());
        objectNode.put("priority", task.getPriority());
        objectNode.put("processDefinitionId", task.getProcessDefinitionId());
        objectNode.put("processInstanceId", task.getProcessInstanceId());
        objectNode.put("taskDefinitionKey", task.getTaskDefinitionKey());
        objectNode.put("tenantId", task.getTenantId());
        objectNode.put("parentTaskId", task.getParentTaskId());
        objectNode.set("processVariables", map2objectNode(task.getProcessVariables()));
        objectNode.set("taskLocalVariables", map2objectNode(task.getTaskLocalVariables()));
        return objectNode;
    }

    public ArrayNode tasks2ArrayNode(List<Task> tasks)
    {
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for (Task task : tasks)
        {
            arrayNode.add(task2ObjectNode(task));
        }
        return arrayNode;
    }

    private String date2String(Date date)
    {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
    }

    private ObjectNode map2objectNode(Map<?, ?> map)
    {
        return objectMapper.convertValue(map, ObjectNode.class);
    }

    public static void main(String... args)
    {
        Map<String, Object> processVariables = new HashMap<>();
        processVariables.put("name", "matosiki");
        processVariables.put("age", 22);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode jsonNodes = objectMapper.convertValue(processVariables, ObjectNode.class);
        System.out.println(jsonNodes.toString());
    }
}

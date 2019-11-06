package com.yiran.activiti.service;



import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.activiti.engine.task.Task;

import com.yiran.activiti.domain.TaskVO;

/**
 */
public interface ActTaskService {

    List<TaskVO> selectTaskList(TaskVO taskVO);

    List<TaskVO> selectTodoTask(TaskVO taskVO);

    List<TaskVO>  selectInvolvedTask(TaskVO taskVO);

    List<TaskVO>  selectArchivedTask(TaskVO taskVO);
    /**
     * 运行中的任务
     * @param userId
     * @return
     */
    List<TaskVO> selectProcessTask(String userId);

    /** key 组名  value 代办任务*/
    Map<String, List<TaskVO>> selectGroupQueueTask(TaskVO taskVO);

    TaskVO  selectOneTask(String taskId);

    void completeTask(String taskId, Map<String, Object> variables);

    /**
     * 提交任务, 并保存意见
     *
     * @param taskId    任务ID
     * @param procInsId 流程实例ID，如果为空，则不保存任务提交意见
     * @param comment   任务提交意见的内容
     * @param title     流程标题，显示在待办任务标题
     * @param vars      任务变量
     */
    void complete(String taskId, String procInsId, String comment, String title, Map<String, Object> vars);

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
    Task startProcess(String procDefId, String businessTable, String businessId, String title, String userId, Map<String, Object> vars);

    /**
     * 获取流程表单（首先获取任务节点表单KEY，如果没有则取流程开始节点表单KEY）
     *
     * @return
     */
    String getFormKey(String procDefId, String taskDefKey);

    InputStream traceTaskPhoto(String processDefinitionId, String executionId);


    List<TaskVO> selectFinishedTask(TaskVO task);
}

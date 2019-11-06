/*package com.yiran.activiti;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.activiti.domain.TaskVO;
import com.yiran.activiti.service.ActTaskService;
import com.yiran.base.BaseJunit;
import com.yiran.framework.util.ShiroUtils;

import cn.hutool.json.JSONUtil;

*//**
 * 查询完成的任务
 * @author pandaa
 *
 *//*
public class FinishedTaskTest extends BaseJunit{
	@Autowired
    ActTaskService actTaskService;
	@Autowired
	HistoryService historyService;
	@Test
	public void test(){
		//TaskVO task =new TaskVO();
		//task.setAssignee("张三");
		//HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery();
        //query.taskAssignee(task.getAssignee()).finished();
        //task.setCount(query.count());
        //List<HistoricTaskInstance> historicTaskInstances = query.list();
        //for (HistoricTaskInstance hi : historicTaskInstances) {
        	//System.out.println("~~~~~~~~~~~~~~~~~~taskId:"+hi.getId());
		//}
		List<TaskVO> taskVOs = actTaskService.selectFinishedTask(task);
		for (TaskVO taskVO : taskVOs) {
			System.out.println(taskVO);
		}
        
        HistoricProcessInstance result = historyService.createHistoricProcessInstanceQuery().processInstanceId("20005").singleResult();
		System.out.println("业务ID:"+result.getBusinessKey());
	}
	
	*//**
     * 历史活动查询
     *//*
    @Test
    public void historyActInstanceList(){
        List<HistoricActivityInstance>  list=historyService // 历史相关Service
            .createHistoricActivityInstanceQuery() // 创建历史活动实例查询
            .orderByHistoricActivityInstanceEndTime().desc()
            .processInstanceId("20005") // 执行流程实例id
            //.taskAssignee("张三")
            .finished()
            .list();
        List<HistoricActivityInstance>  list1 = deleteStartAndEnd(list);
        for(HistoricActivityInstance hai:list1){
            System.out.println("活动ID:"+hai.getId());
            System.out.println("流程实例ID:"+hai.getProcessInstanceId());
            System.out.println("活动名称："+hai.getActivityName());
            System.out.println("办理人："+hai.getAssignee());
            System.out.println("开始时间："+hai.getStartTime());
            System.out.println("结束时间："+hai.getEndTime());
            System.out.println("=================================");
        }
    }
    
    private List<HistoricActivityInstance> deleteStartAndEnd(List<HistoricActivityInstance> list) {
    	Iterator<HistoricActivityInstance> it = list.iterator();
    	while(it.hasNext()){
    		HistoricActivityInstance hai = it.next();
    	    if(hai.getAssignee() == null){
    	        it.remove();
    	    }
    	}
		return list;
	}

    @Test
    public void myTask(){
    	TaskVO taskVO = new TaskVO();
    	//taskVO.setAssignee("张洁");
        taskVO.setCandidateUser("张洁");
        List<TaskVO> taskVOS = actTaskService.selectTaskList(taskVO);
        for (TaskVO taskVO2 : taskVOS) {
			System.out.println("#################:"+taskVO2);
		}
    }
    
    
    
	*//**
     * 历史任务查询
     *//*
    @Test
    public void historyTaskList(){
        List<HistoricTaskInstance> list= historyService // 历史相关Service
            .createHistoricTaskInstanceQuery() // 创建历史任务实例查询
            //.processInstanceId("20005") // 用流程实例id查询
            .taskAssignee("张三")
            .finished() // 查询已经完成的任务
            .list(); 
        for(HistoricTaskInstance hti:list){
            System.out.println("任务ID:"+hti.getId());
            System.out.println("流程实例ID:"+hti.getProcessInstanceId());
            System.out.println("任务名称："+hti.getName());
            System.out.println("办理人："+hti.getAssignee());
            System.out.println("开始时间："+hti.getStartTime());
            System.out.println("结束时间："+hti.getEndTime());
            System.out.println("=================================");
        }
    }

}
*/
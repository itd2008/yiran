package com.yiran.activiti.service;

import com.yiran.activiti.domain.ApplicationLeave;
import java.util.List;

/**
 * 请假申请 服务层
 * 
 * @author yiran
 * @date 2019-03-02
 */
public interface IApplicationLeaveService 
{
	/**
     * 查询请假申请信息
     * 
     * @param formId 请假申请ID
     * @return 请假申请信息
     */
	public ApplicationLeave selectApplicationLeaveById(String formId);
	
	/**
     * 查询请假申请列表
     * 
     * @param applicationLeave 请假申请信息
     * @return 请假申请集合
     */
	public List<ApplicationLeave> selectApplicationLeaveList(ApplicationLeave applicationLeave);
	
	/**
     * 新增请假申请
     * 
     * @param applicationLeave 请假申请信息
     * @return 结果
     */
	public int insertApplicationLeave(ApplicationLeave applicationLeave);
	
	/**
     * 修改请假申请
     * 
     * @param applicationLeave 请假申请信息
     * @return 结果
     */
	public int updateApplicationLeave(ApplicationLeave applicationLeave);
		
	/**
     * 删除请假申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApplicationLeaveByIds(String ids);
	/**
	 * 根据用户ID和状态查询这个用户所有的处理中的任务
	 * @param applicationLeave
	 * @return
	 */
	public List<ApplicationLeave> selectProcessApplicationLeaveList(ApplicationLeave applicationLeave);
	
	
	
}

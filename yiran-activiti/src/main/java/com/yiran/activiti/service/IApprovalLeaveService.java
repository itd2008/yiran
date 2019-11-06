package com.yiran.activiti.service;

import com.yiran.activiti.domain.ApprovalLeave;
import java.util.List;

/**
 * 请假审批 服务层
 * 
 * @author yiran
 * @date 2019-03-02
 */
public interface IApprovalLeaveService 
{
	/**
     * 查询请假审批信息
     * 
     * @param auditId 请假审批ID
     * @return 请假审批信息
     */
	public ApprovalLeave selectApprovalLeaveById(String auditId);
	
	/**
     * 查询请假审批列表
     * 
     * @param approvalLeave 请假审批信息
     * @return 请假审批集合
     */
	public List<ApprovalLeave> selectApprovalLeaveList(ApprovalLeave approvalLeave);
	
	/**
     * 新增请假审批
     * 
     * @param approvalLeave 请假审批信息
     * @return 结果
     */
	public int insertApprovalLeave(ApprovalLeave approvalLeave);
	
	/**
     * 修改请假审批
     * 
     * @param approvalLeave 请假审批信息
     * @return 结果
     */
	public int updateApprovalLeave(ApprovalLeave approvalLeave);
		
	/**
     * 删除请假审批信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteApprovalLeaveByIds(String ids);

	/**
	 * 根据流程实例ID获取所有的审批记录
	 * @param processInstanceId
	 * @return
	 */
	public List<ApprovalLeave> selectApprovalLeavesByProcessInstanceId(String processInstanceId);
	
}

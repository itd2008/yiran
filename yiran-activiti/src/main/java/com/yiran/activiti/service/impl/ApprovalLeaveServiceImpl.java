package com.yiran.activiti.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.activiti.mapper.ApprovalLeaveMapper;
import com.yiran.activiti.domain.ApprovalLeave;
import com.yiran.activiti.service.IApprovalLeaveService;
import com.yiran.common.support.Convert;

/**
 * 请假审批 服务层实现
 * 
 * @author yiran
 * @date 2019-03-02
 */
@Service
public class ApprovalLeaveServiceImpl implements IApprovalLeaveService 
{
	@Autowired
	private ApprovalLeaveMapper approvalLeaveMapper;

	/**
     * 查询请假审批信息
     * 
     * @param auditId 请假审批ID
     * @return 请假审批信息
     */
    @Override
	public ApprovalLeave selectApprovalLeaveById(String auditId)
	{
	    return approvalLeaveMapper.selectApprovalLeaveById(auditId);
	}
	
	/**
     * 查询请假审批列表
     * 
     * @param approvalLeave 请假审批信息
     * @return 请假审批集合
     */
	@Override
	public List<ApprovalLeave> selectApprovalLeaveList(ApprovalLeave approvalLeave)
	{
	    return approvalLeaveMapper.selectApprovalLeaveList(approvalLeave);
	}
	
    /**
     * 新增请假审批
     * 
     * @param approvalLeave 请假审批信息
     * @return 结果
     */
	@Override
	public int insertApprovalLeave(ApprovalLeave approvalLeave)
	{
	    return approvalLeaveMapper.insertApprovalLeave(approvalLeave);
	}
	
	/**
     * 修改请假审批
     * 
     * @param approvalLeave 请假审批信息
     * @return 结果
     */
	@Override
	public int updateApprovalLeave(ApprovalLeave approvalLeave)
	{
	    return approvalLeaveMapper.updateApprovalLeave(approvalLeave);
	}

	/**
     * 删除请假审批对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteApprovalLeaveByIds(String ids)
	{
		return approvalLeaveMapper.deleteApprovalLeaveByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<ApprovalLeave> selectApprovalLeavesByProcessInstanceId(String processInstanceId) {
		return approvalLeaveMapper.selectApprovalLeavesByProcessInstanceId(processInstanceId);
	}
	
}

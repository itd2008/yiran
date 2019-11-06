package com.yiran.activiti.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.activiti.mapper.ApplicationLeaveMapper;
import com.yiran.activiti.domain.ApplicationLeave;
import com.yiran.activiti.service.IApplicationLeaveService;
import com.yiran.common.support.Convert;

import cn.hutool.core.lang.UUID;

/**
 * 请假申请 服务层实现
 * 
 * @author yiran
 * @date 2019-03-02
 */
@Service
public class ApplicationLeaveServiceImpl implements IApplicationLeaveService 
{
	@Autowired
	private ApplicationLeaveMapper applicationLeaveMapper;

	/**
     * 查询请假申请信息
     * 
     * @param formId 请假申请ID
     * @return 请假申请信息
     */
    @Override
	public ApplicationLeave selectApplicationLeaveById(String formId)
	{
	    return applicationLeaveMapper.selectApplicationLeaveById(formId);
	}
	
	/**
     * 查询请假申请列表
     * 
     * @param applicationLeave 请假申请信息
     * @return 请假申请集合
     */
	@Override
	public List<ApplicationLeave> selectApplicationLeaveList(ApplicationLeave applicationLeave)
	{
	    return applicationLeaveMapper.selectApplicationLeaveList(applicationLeave);
	}
	
    /**
     * 新增请假申请
     * 
     * @param applicationLeave 请假申请信息
     * @return 结果
     */
	@Override
	public int insertApplicationLeave(ApplicationLeave applicationLeave)
	{
		
	    return applicationLeaveMapper.insertApplicationLeave(applicationLeave);
	}
	
	/**
     * 修改请假申请
     * 
     * @param applicationLeave 请假申请信息
     * @return 结果
     */
	@Override
	public int updateApplicationLeave(ApplicationLeave applicationLeave)
	{
	    return applicationLeaveMapper.updateApplicationLeave(applicationLeave);
	}

	/**
     * 删除请假申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteApplicationLeaveByIds(String ids)
	{
		return applicationLeaveMapper.deleteApplicationLeaveByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<ApplicationLeave> selectProcessApplicationLeaveList(ApplicationLeave applicationLeave) {
		return applicationLeaveMapper.selectProcessApplicationLeaveList(applicationLeave);
	}
	
}

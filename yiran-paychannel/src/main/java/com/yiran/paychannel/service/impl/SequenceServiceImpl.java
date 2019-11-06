package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.SequenceMapper;
import com.yiran.paychannel.domain.Sequence;
import com.yiran.paychannel.service.ISequenceService;
import com.yiran.common.support.Convert;

/**
 * 序列 服务层实现
 * 
 * @author yiran
 * @date 2019-07-26
 */
@Service
public class SequenceServiceImpl implements ISequenceService 
{
	@Autowired
	private SequenceMapper sequenceMapper;

	/**
     * 查询序列信息
     * 
     * @param name 序列ID
     * @return 序列信息
     */
    @Override
	public Sequence selectSequenceById(String name)
	{
	    return sequenceMapper.selectSequenceById(name);
	}
	
	/**
     * 查询序列列表
     * 
     * @param sequence 序列信息
     * @return 序列集合
     */
	@Override
	public List<Sequence> selectSequenceList(Sequence sequence)
	{
	    return sequenceMapper.selectSequenceList(sequence);
	}
	
    /**
     * 新增序列
     * 
     * @param sequence 序列信息
     * @return 结果
     */
	@Override
	public int insertSequence(Sequence sequence)
	{
	    return sequenceMapper.insertSequence(sequence);
	}
	
	/**
     * 修改序列
     * 
     * @param sequence 序列信息
     * @return 结果
     */
	@Override
	public int updateSequence(Sequence sequence)
	{
	    return sequenceMapper.updateSequence(sequence);
	}

	/**
     * 删除序列对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteSequenceByIds(String ids)
	{
		return sequenceMapper.deleteSequenceByIds(Convert.toStrArray(ids));
	}

	@Override
	public int setval(String name, int currentValue) {
		return sequenceMapper.setval(name, currentValue);
	}

	@Override
	public int currval(String name) {
		return sequenceMapper.currval(name);
	}

	@Override
	public int nextval(String name) {
		return sequenceMapper.nextval(name);
	}
	
}

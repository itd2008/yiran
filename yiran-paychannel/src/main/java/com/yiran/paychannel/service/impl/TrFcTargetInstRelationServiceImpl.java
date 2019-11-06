package com.yiran.paychannel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TrFcTargetInstRelationMapper;
import com.yiran.paychannel.domain.TrFcTargetInstRelation;
import com.yiran.paychannel.service.ITrFcTargetInstRelationService;
import com.yiran.common.support.Convert;

/**
 * 目标机构 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TrFcTargetInstRelationServiceImpl implements ITrFcTargetInstRelationService 
{
	@Autowired
	private TrFcTargetInstRelationMapper trFcTargetInstRelationMapper;

	/**
     * 查询目标机构信息
     * 
     * @param id 目标机构ID
     * @return 目标机构信息
     */
    @Override
	public TrFcTargetInstRelation selectTrFcTargetInstRelationById(Integer id)
	{
	    return trFcTargetInstRelationMapper.selectTrFcTargetInstRelationById(id);
	}
	
	/**
     * 查询目标机构列表
     * 
     * @param trFcTargetInstRelation 目标机构信息
     * @return 目标机构集合
     */
	@Override
	public List<TrFcTargetInstRelation> selectTrFcTargetInstRelationList(TrFcTargetInstRelation trFcTargetInstRelation)
	{
	    return trFcTargetInstRelationMapper.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
	}
	
    /**
     * 新增目标机构
     * 
     * @param trFcTargetInstRelation 目标机构信息
     * @return 结果
     */
	@Override
	public int insertTrFcTargetInstRelation(TrFcTargetInstRelation trFcTargetInstRelation)
	{
	    return trFcTargetInstRelationMapper.insertTrFcTargetInstRelation(trFcTargetInstRelation);
	}
	
	/**
     * 修改目标机构
     * 
     * @param trFcTargetInstRelation 目标机构信息
     * @return 结果
     */
	@Override
	public int updateTrFcTargetInstRelation(TrFcTargetInstRelation trFcTargetInstRelation)
	{
	    return trFcTargetInstRelationMapper.updateTrFcTargetInstRelation(trFcTargetInstRelation);
	}

	/**
     * 删除目标机构对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTrFcTargetInstRelationByIds(String ids)
	{
		return trFcTargetInstRelationMapper.deleteTrFcTargetInstRelationByIds(Convert.toStrArray(ids));
	}

	@Override
	public int insertBatchTrFcTargetInstRelations(List<TrFcTargetInstRelation> list) {
		return trFcTargetInstRelationMapper.insertBatchTrFcTargetInstRelations(list);
	}
	
}

package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.TrFcTargetInstRelation;
import java.util.List;	

/**
 * 目标机构 数据层
 * 
 * @author yiran
 * @date 2019-04-19
 */
public interface TrFcTargetInstRelationMapper 
{
	/**
     * 查询目标机构信息
     * 
     * @param id 目标机构ID
     * @return 目标机构信息
     */
	public TrFcTargetInstRelation selectTrFcTargetInstRelationById(Integer id);
	
	/**
     * 查询目标机构列表
     * 
     * @param trFcTargetInstRelation 目标机构信息
     * @return 目标机构集合
     */
	public List<TrFcTargetInstRelation> selectTrFcTargetInstRelationList(TrFcTargetInstRelation trFcTargetInstRelation);
	
	/**
     * 新增目标机构
     * 
     * @param trFcTargetInstRelation 目标机构信息
     * @return 结果
     */
	public int insertTrFcTargetInstRelation(TrFcTargetInstRelation trFcTargetInstRelation);
	
	/**
     * 修改目标机构
     * 
     * @param trFcTargetInstRelation 目标机构信息
     * @return 结果
     */
	public int updateTrFcTargetInstRelation(TrFcTargetInstRelation trFcTargetInstRelation);
	
	/**
     * 删除目标机构
     * 
     * @param id 目标机构ID
     * @return 结果
     */
	public int deleteTrFcTargetInstRelationById(Integer id);
	
	/**
     * 批量删除目标机构
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteTrFcTargetInstRelationByIds(String[] ids);

	public int insertBatchTrFcTargetInstRelations(List<TrFcTargetInstRelation> list);
	
}
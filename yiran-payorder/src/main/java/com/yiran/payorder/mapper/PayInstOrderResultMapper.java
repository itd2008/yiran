package com.yiran.payorder.mapper;

import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domaindo.PayInstOrderResultDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 机构订单结果 数据层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface PayInstOrderResultMapper 
{
	/**
     * 查询机构订单结果信息
     * 
     * @param resultId 机构订单结果ID
     * @return 机构订单结果信息
     */
	public PayInstOrderResultDO selectPayInstOrderResultById(Integer resultId);
	
	/**
     * 查询机构订单结果列表
     * 
     * @param payInstOrderResult 机构订单结果信息
     * @return 机构订单结果集合
     */
	public List<PayInstOrderResultDO> selectPayInstOrderResultList(PayInstOrderResultDO payInstOrderResult);
	
	/**
     * 新增机构订单结果
     * 
     * @param payInstOrderResult 机构订单结果信息
     * @return 结果
     */
	public int insertPayInstOrderResult(PayInstOrderResultDO payInstOrderResult);
	
	/**
     * 修改机构订单结果
     * 
     * @param payInstOrderResult 机构订单结果信息
     * @return 结果
     */
	public int updatePayInstOrderResult(PayInstOrderResultDO payInstOrderResult);
	
	/**
     * 删除机构订单结果
     * 
     * @param resultId 机构订单结果ID
     * @return 结果
     */
	public int deletePayInstOrderResultById(Integer resultId);
	
	/**
     * 批量删除机构订单结果
     * 
     * @param resultIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePayInstOrderResultByIds(String[] resultIds);

	/**
	 * 根据instOrderId获取订单结果
	 * @param instOrderId
	 * @return
	 */

	public List<PayInstOrderResultDO> loadPayInstOrderResultByInstOrderId(Integer instOrderId);

	public int updateRiskFlagById(@Param("riskFlag")String riskFlag, @Param("resultId")Integer resultId);

	public int updateOperateStatusById(@Param("operateStatus") String operateStatus, @Param("resultId") Integer resultId);

	public PayInstOrderResultDO loadRealResultByInstSeqNo(String instSeqNo);
	
}
package com.yiran.payorder.service;

import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domaindo.PayInstOrderResultDO;
import com.yiran.payorder.enums.InstResultOperateStatus;
import com.yiran.payorder.enums.RiskFlag;

import java.util.List;

/**
 * 机构订单结果 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IPayInstOrderResultService 
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
     * 删除机构订单结果信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayInstOrderResultByIds(String ids);

	/**
	 * 根据机构订单ID加载领域对象列表，不包括 instStatus='Q'的记录如果存在多条,则按以下顺序返回 1.优先返回实际影响机构订单结果的一条 2.返回有明确结果的 3.随机返回一条
	 * @param instOrderId
	 * @return
	 */
	public PayInstOrderResult loadRealResultByOrder(Integer instOrderId);

	public int store(PayInstOrderResult instResult);

	public int updateRiskFlagById(RiskFlag riskFlag, Integer resultId);

	public int updateOperateStatusById(InstResultOperateStatus operateStatus, Integer resultId);

	public PayInstOrderResult loadRealResultByInstSeqNo(String orderNo);

	public PayInstOrderResult getLastResult(Integer instOrderId);
	
}

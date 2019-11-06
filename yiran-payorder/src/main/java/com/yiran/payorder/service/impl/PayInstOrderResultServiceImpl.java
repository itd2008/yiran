package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.payorder.mapper.PayInstOrderResultMapper;
import com.yiran.payorder.converter.InstOrderResultConverter;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domaindo.PayInstOrderResultDO;
import com.yiran.payorder.enums.BatchType;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.InstResultOperateStatus;
import com.yiran.payorder.enums.RiskFlag;
import com.yiran.payorder.service.IPayInstOrderResultService;
import com.yiran.common.support.Convert;

/**
 * 机构订单结果 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class PayInstOrderResultServiceImpl implements IPayInstOrderResultService 
{
	@Autowired
	private PayInstOrderResultMapper payInstOrderResultMapper;

	/**
     * 查询机构订单结果信息
     * 
     * @param resultId 机构订单结果ID
     * @return 机构订单结果信息
     */
    @Override
	public PayInstOrderResultDO selectPayInstOrderResultById(Integer resultId)
	{
	    return payInstOrderResultMapper.selectPayInstOrderResultById(resultId);
	}
	
	/**
     * 查询机构订单结果列表
     * 
     * @param payInstOrderResult 机构订单结果信息
     * @return 机构订单结果集合
     */
	@Override
	public List<PayInstOrderResultDO> selectPayInstOrderResultList(PayInstOrderResultDO payInstOrderResult)
	{
	    return payInstOrderResultMapper.selectPayInstOrderResultList(payInstOrderResult);
	}
	
    /**
     * 新增机构订单结果
     * 
     * @param payInstOrderResult 机构订单结果信息
     * @return 结果
     */
	@Override
	public int insertPayInstOrderResult(PayInstOrderResultDO payInstOrderResult)
	{
	    return payInstOrderResultMapper.insertPayInstOrderResult(payInstOrderResult);
	}
	
	/**
     * 修改机构订单结果
     * 
     * @param payInstOrderResult 机构订单结果信息
     * @return 结果
     */
	@Override
	public int updatePayInstOrderResult(PayInstOrderResultDO payInstOrderResult)
	{
	    return payInstOrderResultMapper.updatePayInstOrderResult(payInstOrderResult);
	}

	/**
     * 删除机构订单结果对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayInstOrderResultByIds(String ids)
	{
		return payInstOrderResultMapper.deletePayInstOrderResultByIds(Convert.toStrArray(ids));
	}

	@Override
	public PayInstOrderResult loadRealResultByOrder(Integer instOrderId) {
		List<PayInstOrderResultDO> results = payInstOrderResultMapper.loadPayInstOrderResultByInstOrderId(instOrderId);
        if (results == null || results.isEmpty()) {
            return null;
        }

        List<PayInstOrderResult> orders = InstOrderResultConverter.convert(results);

        if (orders != null && orders.size() == 1) {
        	return orders.get(0);
        } else {
        	  //R_C:渠道结果会有多条，所以从结果中随机取一条的逻辑会有问题
        	PayInstOrderResult result = getRealOperateResult(orders);
            return result;
        }
		
	}

	/**
     * 获取真实处理的结果
     * 1.默认返回第一条
     * 2.当存在操作状态为成功的,直接返回
     * 3.优先返回状态为最终结果的数据
     * @param results
     * @return
     */
	private PayInstOrderResult getRealOperateResult(List<PayInstOrderResult> orders) {
		PayInstOrderResult result = null;
        for (PayInstOrderResult tempResult : orders) {
        	result = tempResult;
            if (isLastStatus(tempResult)){
            	return tempResult;
            }
                
        }
        return result;
	}

	/**
     * 判断结果状态是否为最终结果
     * @param result
     * @return
     */
	private boolean isLastStatus(PayInstOrderResult tempResult) {
		return InstOrderResultStatus.FAILURE.equals(tempResult.getStatus())
	               || InstOrderResultStatus.SUCCESSFUL.equals(tempResult.getStatus());
	}

	@Override
	public int store(PayInstOrderResult instResult) {
		PayInstOrderResultDO resultDO = InstOrderResultConverter.convert(instResult);
        int id = payInstOrderResultMapper.insertPayInstOrderResult(resultDO); // 存机构订单结果表
        instResult.setResultId(id);
		return id;
	}

	@Override
	public int updateRiskFlagById(RiskFlag riskFlag, Integer resultId) {
		return payInstOrderResultMapper.updateRiskFlagById(riskFlag.getCode(),resultId);
	}

	@Override
	public int updateOperateStatusById(InstResultOperateStatus operateStatus, Integer resultId) {
		return payInstOrderResultMapper.updateOperateStatusById(operateStatus.getCode(),resultId);
	}

	@Override
	public PayInstOrderResult loadRealResultByInstSeqNo(String instSeqNo) {
		return InstOrderResultConverter.convert(payInstOrderResultMapper.loadRealResultByInstSeqNo(instSeqNo));
	}

	@Override
	public PayInstOrderResult getLastResult(Integer instOrderId) {
		List<PayInstOrderResultDO> list = payInstOrderResultMapper.loadPayInstOrderResultByInstOrderId(instOrderId);;
        for (PayInstOrderResultDO instOrderResultDO : list) {
            PayInstOrderResult result = InstOrderResultConverter.convert(instOrderResultDO);
            /** 单个 */
            if (result.getArchiveBatchId() == null) {
                if (result.getOperateStatus() != InstResultOperateStatus.FAILURE) {
                    return result;
                }
            } else {
                if (result.getBatchType() != BatchType.CHECK
                    && result.getOperateStatus() != InstResultOperateStatus.FAILURE) {
                    return result;
                }
            }
        }
        return null;
	}
	
}

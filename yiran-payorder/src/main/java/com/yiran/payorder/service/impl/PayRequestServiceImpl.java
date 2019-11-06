package com.yiran.payorder.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiran.payorder.mapper.PayRequestMapper;
import com.yiran.payorder.domain.PayRequest;
import com.yiran.payorder.service.IPayRequestService;
import com.yiran.common.support.Convert;

/**
 * 请求，用于控制唯一 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class PayRequestServiceImpl implements IPayRequestService 
{
	@Autowired
	private PayRequestMapper payRequestMapper;

	/**
     * 查询请求，用于控制唯一信息
     * 
     * @param paymentSeqNo 请求，用于控制唯一ID
     * @return 请求，用于控制唯一信息
     */
    @Override
	public PayRequest selectPayRequestById(Integer paymentSeqNo)
	{
	    return payRequestMapper.selectPayRequestById(paymentSeqNo);
	}
	
	/**
     * 查询请求，用于控制唯一列表
     * 
     * @param payRequest 请求，用于控制唯一信息
     * @return 请求，用于控制唯一集合
     */
	@Override
	public List<PayRequest> selectPayRequestList(PayRequest payRequest)
	{
	    return payRequestMapper.selectPayRequestList(payRequest);
	}
	
    /**
     * 新增请求，用于控制唯一
     * 
     * @param payRequest 请求，用于控制唯一信息
     * @return 结果
     */
	@Override
	public int insertPayRequest(PayRequest payRequest)
	{
	    return payRequestMapper.insertPayRequest(payRequest);
	}
	
	/**
     * 修改请求，用于控制唯一
     * 
     * @param payRequest 请求，用于控制唯一信息
     * @return 结果
     */
	@Override
	public int updatePayRequest(PayRequest payRequest)
	{
	    return payRequestMapper.updatePayRequest(payRequest);
	}

	/**
     * 删除请求，用于控制唯一对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayRequestByIds(String ids)
	{
		return payRequestMapper.deletePayRequestByIds(Convert.toStrArray(ids));
	}

	@Override
	public PayRequest lockedById(String paymentSeqNo, String settlementId) {
		return payRequestMapper.lockedById(paymentSeqNo, settlementId);
	}

	@Override
	public void updateStatusById(String canRetry, String paymentSeqNo, String settlementId) {

		payRequestMapper.updateStatusById(canRetry, paymentSeqNo, settlementId);
	}
	
	
}

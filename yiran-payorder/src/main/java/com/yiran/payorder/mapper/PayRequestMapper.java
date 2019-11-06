package com.yiran.payorder.mapper;

import com.yiran.payorder.domain.PayRequest;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 请求，用于控制唯一 数据层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface PayRequestMapper 
{
	/**
     * 查询请求，用于控制唯一信息
     * 
     * @param paymentSeqNo 请求，用于控制唯一ID
     * @return 请求，用于控制唯一信息
     */
	public PayRequest selectPayRequestById(Integer paymentSeqNo);
	
	/**
     * 查询请求，用于控制唯一列表
     * 
     * @param payRequest 请求，用于控制唯一信息
     * @return 请求，用于控制唯一集合
     */
	public List<PayRequest> selectPayRequestList(PayRequest payRequest);
	
	/**
     * 新增请求，用于控制唯一
     * 
     * @param payRequest 请求，用于控制唯一信息
     * @return 结果
     */
	public int insertPayRequest(PayRequest payRequest);
	
	/**
     * 修改请求，用于控制唯一
     * 
     * @param payRequest 请求，用于控制唯一信息
     * @return 结果
     */
	public int updatePayRequest(PayRequest payRequest);
	
	/**
     * 删除请求，用于控制唯一
     * 
     * @param paymentSeqNo 请求，用于控制唯一ID
     * @return 结果
     */
	public int deletePayRequestById(Integer paymentSeqNo);
	
	/**
     * 批量删除请求，用于控制唯一
     * 
     * @param paymentSeqNos 需要删除的数据ID
     * @return 结果
     */
	public int deletePayRequestByIds(String[] paymentSeqNos);

	/**
	 * 
	 * @param paymentSeqNo
	 * @param settlementId
	 * @return
	 */
	public PayRequest lockedById(@Param("paymentSeqNo")String paymentSeqNo,@Param("settlementId")String settlementId);

	/**
	 * 通过ID更新状态
	 * @param string
	 * @param paymentSeqNo
	 * @param settlementId
	 */
	public void updateStatusById(String canRetry, String paymentSeqNo, String settlementId);
	
}
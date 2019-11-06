package com.yiran.payorder.service;

import com.yiran.payorder.domain.PayRequest;
import java.util.List;

/**
 * 请求，用于控制唯一 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IPayRequestService 
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
     * 删除请求，用于控制唯一信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayRequestByIds(String ids);

	public PayRequest lockedById(String paymentSeqNo, String settlementId);

	public void updateStatusById(String string, String paymentSeqNo, String settlementId);
	
}

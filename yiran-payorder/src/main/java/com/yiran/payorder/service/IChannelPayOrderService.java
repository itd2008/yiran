package com.yiran.payorder.service;

import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domaindo.ChannelPayOrderDO;
import com.yiran.payorder.enums.PayOrderConfirmStatus;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.exception.DuplicateKeyException;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * 渠道支付订单 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IChannelPayOrderService 
{
	/**
     * 查询渠道支付订单信息
     * 
     * @param paySeqNo 渠道支付订单ID
     * @return 渠道支付订单信息
     */
	public ChannelPayOrderDO selectChannelPayOrderById(String paySeqNo);
	
	/**
     * 查询渠道支付订单列表
     * 
     * @param channelPayOrder 渠道支付订单信息
     * @return 渠道支付订单集合
     */
	public List<ChannelPayOrderDO> selectChannelPayOrderList(ChannelPayOrderDO channelPayOrder);
	
	/**
     * 新增渠道支付订单
     * 
     * @param channelPayOrder 渠道支付订单信息
     * @return 结果
     */
	public int insertChannelPayOrder(ChannelPayOrderDO channelPayOrder);
	
	/**
     * 修改渠道支付订单
     * 
     * @param channelPayOrder 渠道支付订单信息
     * @return 结果
     */
	public int updateChannelPayOrder(ChannelPayOrderDO channelPayOrder);
		
	/**
     * 删除渠道支付订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteChannelPayOrderByIds(String ids);

	/**
	 * 通过支付流水号加载
	 * @param orgiPaymentSeqNo
	 * @param orgiSettlementId
	 * @return
	 */
	public ChannelPayOrder loadByPaymentSeqNo(String paymentSeqNo, String settlementId);

	/**
	 * 保存渠道订单
	 * @param channelPayOrder
	 * @return
	 */
	public String store(ChannelPayOrder channelPayOrder)  throws DuplicateKeyException;

	/**
	 * 更新机构订单ID
	 * @param instOrderId
	 * @param paySeqNo
	 */
	public void updateInstById(Integer instOrderId, String paySeqNo);

	/**
	 * 更新订单状态
	 * @param channelPayOrder
	 * @param awaiting
	 */
	public boolean updateCmfOrderStatus(ChannelPayOrder channelPayOrder, PayOrderStatus awaiting);

	public ChannelPayOrder loadByPaySeqNo(String paySeqNo);

	/**
	 * 更新订单审核状态
	 * @param awaiting
	 * @param paySeqNo
	 */
	public int updateConfirmStatusById(PayOrderConfirmStatus awaiting, String paySeqNo);

	/**
	 * 根据instOrderId获取订单信息
	 * @param instOrderId
	 * @return
	 */
	public ChannelPayOrder loadByInstOrderId(Integer instOrderId);

	public ChannelPayOrder loadByPaymentSeqNo(String orderNo);

	public ChannelPayOrder loadByOrgiPaymentSeqNo(String orgiPaymentSeqNo);

	public int updatePaymentNotifyStatusById(String paymentNotifyStatus,String paySeqNo);

	public List<ChannelPayOrderDO> selectChannelPayOrderListIsNotS();
	
}

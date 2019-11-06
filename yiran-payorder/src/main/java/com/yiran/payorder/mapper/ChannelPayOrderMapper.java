package com.yiran.payorder.mapper;

import com.yiran.payorder.domaindo.ChannelPayOrderDO;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 渠道支付订单 数据层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface ChannelPayOrderMapper 
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
     * 删除渠道支付订单
     * 
     * @param paySeqNo 渠道支付订单ID
     * @return 结果
     */
	public int deleteChannelPayOrderById(String paySeqNo);
	
	/**
     * 批量删除渠道支付订单
     * 
     * @param paySeqNos 需要删除的数据ID
     * @return 结果
     */
	public int deleteChannelPayOrderByIds(String[] paySeqNos);

	/**
	 * 通过支付流水号加载
	 * @param orgiPaymentSeqNo
	 * @param orgiSettlementId
	 * @return
	 */
	public ChannelPayOrderDO loadByPaymentSeqNo(@Param("paymentSeqNo")String paymentSeqNo,@Param("settlementId") String settlementId);

	public void updateInstById(@Param("instOrderId") Integer instOrderId,@Param("paySeqNo") String paySeqNo);

	public int updateConfirmStatusById(@Param("confirmStatus") String confirmStatus, @Param("paySeqNo") String paySeqNo);

	public int updateStatusById(@Param("status") String status, @Param("paySeqNo") String paySeqNo);

	public List<ChannelPayOrderDO> loadByInstOrderId(Integer instOrderId);

	public ChannelPayOrderDO selectByPaymentSeqNo(String paymentSeqNo);

	public ChannelPayOrderDO loadByOrgiPaymentSeqNo(String orgiPaymentSeqNo);

	public int updatePaymentNotifyStatusById(@Param("paymentNotifyStatus")String paymentNotifyStatus, @Param("paySeqNo")String paySeqNo);

	public List<ChannelPayOrderDO> selectChannelPayOrderListIsNotS();
	
}
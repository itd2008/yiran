package com.yiran.payorder.mapper;

import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domaindo.PayInstOrderDO;
import com.yiran.payorder.enums.OrderFlag;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;	

/**
 * 提交机构订单 数据层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface PayInstOrderMapper 
{
	/**
     * 查询提交机构订单信息
     * 
     * @param instOrderId 提交机构订单ID
     * @return 提交机构订单信息
     */
	public PayInstOrderDO selectPayInstOrderById(Integer instOrderId);
	
	/**
     * 查询提交机构订单列表
     * 
     * @param payInstOrder 提交机构订单信息
     * @return 提交机构订单集合
     */
	public List<PayInstOrderDO> selectPayInstOrderList(PayInstOrderDO payInstOrder);
	
	/**
     * 新增提交机构订单
     * 
     * @param payInstOrder 提交机构订单信息
     * @return 结果
     */
	public int insertPayInstOrder(PayInstOrderDO payInstOrder);
	
	/**
     * 修改提交机构订单
     * 
     * @param payInstOrder 提交机构订单信息
     * @return 结果
     */
	public int updatePayInstOrder(PayInstOrderDO payInstOrder);
	
	/**
     * 删除提交机构订单
     * 
     * @param instOrderId 提交机构订单ID
     * @return 结果
     */
	public int deletePayInstOrderById(Integer instOrderId);
	
	/**
     * 批量删除提交机构订单
     * 
     * @param instOrderIds 需要删除的数据ID
     * @return 结果
     */
	public int deletePayInstOrderByIds(String[] instOrderIds);

	/**
	 * 根据机构订单号获取机构订单对象
	 * @param instOrderNo
	 * @return
	 */
	public PayInstOrderDO loadByInstOrderNo(String instOrderNo);

	/**
	 * 风险订单状态
	 * @param riskStatus
	 * @param instOrderId
	 * @return
	 */
	public int updateRiskStatus(@Param("riskStatus")String riskStatus, @Param("instOrderId")Integer instOrderId);

	public int updateCommunicateStatusById(@Param("communicateStatus")String communicateStatus, @Param("instOrderId")Integer instOrderId);

	public void updateExtensionById(@Param("extension") String extension, @Param("instOrderId") Integer instOrderId);

	public int updateStatusById(@Param("status")String status, @Param("instOrderId")Integer instOrderId);

	public List<PayInstOrderDO> loadByPaySeq(String paySeqNo);

	public int updateMemoById(@Param("memo")String memo, @Param("instOrderId")Integer instOrderId);

	public int updateChannelInfoById(@Param("fundChannelCode")String fundChannelCode, @Param("fundChannelApi")String fundChannelApi, @Param("instOrderId")Integer instOrderId);

	public List<PayInstOrderDO> loadByInstOrderNos(@Param("instOrderNos") List<String> instOrderNos);

	public List<PayInstOrderDO> selectPayInstOrderListByDate(@Param("gmtCreateBegin") Date gmtCreateBegin, @Param("gmtCreateEnd") Date gmtCreateEnd);

	public int updateFlag(
			@Param("endTime")Date endTime, 
			@Param("startTime")Date startTime, 
			@Param("newFlag")String newFlag, 
			@Param("oldFlag")String oldFlag, 
			@Param("fundChannelCode")String fundChannelCode,
			@Param("communicateStatusList") List<String> communicateStatusList);

	public List<PayInstOrderDO> loadOrderIdForDurationQueryResultPaging(
			@Param("fundChannelCode")String fundChannelCode,
			@Param("communicateStatusList")List<String> communicateStatusList, 
			@Param("startTime")Date startTime, 
			@Param("endTime")Date endTime, 
			@Param("flag")String flag, 
			@Param("pageSize")int pageSize);

	public int updateFlagWithOrderId(
			@Param("instOrderId")Integer instOrderId, 
			@Param("flag")String flag);

	public List<PayInstOrderDO> listPaymentRecord(Map<String, Object> paramMap);

	
}
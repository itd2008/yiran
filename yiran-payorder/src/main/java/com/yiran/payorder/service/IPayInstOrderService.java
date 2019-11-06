package com.yiran.payorder.service;

import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.payorder.domain.InstOrderVO;
import com.yiran.payorder.domain.OrderQuery;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domaindo.PayInstOrderDO;
import com.yiran.payorder.enums.CommunicateStatus;
import com.yiran.payorder.enums.OrderFlag;
import com.yiran.payorder.enums.OrderRiskStatus;
import com.yiran.payorder.exception.WrongStateException;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 提交机构订单 服务层
 * 
 * @author yiran
 * @date 2019-07-13
 */
public interface IPayInstOrderService 
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
     * 删除提交机构订单信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deletePayInstOrderByIds(String ids);

	/**
	 * 根据insertOrderId获取对象
	 * @param instOrderId
	 * @return
	 */
	public PayInstOrder loadById(Integer instOrderId);

	/**
	 * 根据instOrderNo获取instOrder对象
	 * @param instOrderNo
	 * @return
	 */
	public PayInstOrder loadByInstOrderNo(String instOrderNo);

	/**
	 * 更新风险订单状态
	 * @param riskStatus
	 * @param instOrderId
	 */
	public int updateRiskStatus(OrderRiskStatus riskStatus, Integer instOrderId);

	/**
	 * 批量存储机构订单
	 * @param instOrderList
	 */
	public void store(List<PayInstOrder> instOrderList);

	/**
	 * 更新机构订单状态
	 * @param communicateStatus
	 * @param instOrderId
	 */
	public int updateCommunicateStatusById(CommunicateStatus communicateStatus, Integer instOrderId);

	/**
	 * 保存扩展信息
	 * @param instOrder
	 * @return
	 */
	public void storeExtension(PayInstOrder instOrder);

	public int updateStatusById(InstOrderStatus status, Integer instOrderId);

	public boolean isCompleteSuccess(PayInstOrder instOrder)  throws WrongStateException;

	public int updateMemoById(String memo, Integer instOrderId);

	public int updateChannelInfoById(String fundChannelCode, String apiCode, Integer instOrderId);

	public InstOrderVO queryFundoutOrder(String instOrderNo);

	public List<PayInstOrder> loadByInstOrderNos(List<String> instOrderNos);

	public List<PayInstOrderDO> selectPayInstOrderListByDate(Date gmtCreateBegin, Date gmtCreateEnd);

	public int updateFlag(Date endTime, Date startTime, OrderFlag newFlag, OrderFlag oldFlag,
            String fundChannelCode, List<String> communicateStatusList);

	public List<PayInstOrder> loadOrderIdForDurationQueryResultPaging(String fundChannelCode,
            List<String> communicateStatusList,
            Date startTime, Date endTime,
            OrderFlag flag, int pageSize);

	public int updateFlagWithOrderId(Integer InstOrderId, OrderFlag flag);

	public PayInstOrder loadWithOrderId(Integer InstOrderId);

	
	public List<PayInstOrder> listPaymentRecord(Map<String, Object> paramMap);



	
}

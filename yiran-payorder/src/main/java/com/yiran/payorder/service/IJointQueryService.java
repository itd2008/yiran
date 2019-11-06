package com.yiran.payorder.service;

import java.util.List;

import com.yiran.payorder.domaindo.VInstOrder;
import com.yiran.payorder.domaindo.VInstOrderResult;
import com.yiran.payorder.domaindo.VOrder;
import com.yiran.payorder.domaindo.VPayOrder;

/**
 * 联合查询
 * @author pandaa
 *
 */
public interface IJointQueryService {
	/**
	 * 查询渠道支付订单
	 * @param vPayOrder
	 * @return
	 */
	public List<VPayOrder> selectChannelPayOrderList(VPayOrder vPayOrder);
	/**
	 * 获取机构订单
	 * @param vInstOrder
	 * @return
	 */
	public List<VInstOrder> selectInstOrderList(VInstOrder vInstOrder);
	/**
	 * 机构订单结果
	 * @param vInstOrderResult
	 * @return
	 */
	public List<VInstOrderResult> selectInstOrderResultList(VInstOrderResult vInstOrderResult);
	/**
	 * 订单信息
	 * @param order
	 * @return
	 */
	public List<VOrder> selectInstOrderList(VOrder order);

}

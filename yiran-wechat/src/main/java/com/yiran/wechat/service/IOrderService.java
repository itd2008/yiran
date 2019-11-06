package com.yiran.wechat.service;

import java.util.List;
import java.util.Map;

import com.yiran.wechat.domain.OrderRequest;
import com.yiran.wechat.domain.WeChatOrderResponse;
import com.yiran.wechat.domain.WechatOrder;
import com.yiran.wechat.domain.WechatOrderDetail;
import com.yiran.wechat.domain.WechatOrderProductComment;

/**
 * 订单接口
 * @author pandaa
 *
 */
public interface IOrderService {
	/**
	 * 创建订单
	 * @param orderRequest
	 * @return
	 */
	public int createOrder(OrderRequest orderRequest);

	/**
	 * 获取订单信息（全部订单，未支付订单，代发货，待收货，待评价）
	 * @param openId
	 * @return
	 */
	public Map<String, Object> getOrder(String openId);

	/**
	 * 根据订单号查询
	 * @param orderNo
	 * @return
	 */
	public WeChatOrderResponse getOrdersByOrderNo(String orderNo,String openId);

	/**
	 * 发布评论
	 * @param comment
	 */
	public int saveOrderProductComment(WechatOrderProductComment comment);

	/**
	 * 更新订单状态为完成
	 * @return
	 */
	public int updateOrderStatustranSactionCompleted(String orderId);

	/**
	 * 更新明细中的是否品论状态
	 * @param orderId
	 */
	public int updateOrderDetilIsCommentStatus(String orderId,String productId);

	public List<WechatOrderDetail> selectWechatOrderDetailList(WechatOrderDetail orderDetail);

	/**
	 * 更新订单状态
	 * @param orderNo
	 * @param openId
	 * @param code
	 * @return
	 */
	public int updateOrderStatus(String orderNo, String openId, String code);

	/**
	 *根据订单id获取订单信息
	 * @param orderId
	 * @return
	 */
	public WechatOrder selectOrderByOrderId(int orderId);

	public int updateOrder(WechatOrder weChatorder);

	/**
	 * 根据订单号获取订单信息
	 * @param orderNo
	 * @return
	 */
	public WechatOrder selectOrderByOrderNo(String orderNo);

	public WeChatOrderResponse getOrdersByOrderId(int orderId);
	/**
	 * 获取已支付未发货订单数量
	 * @return
	 */
	public int getOrderNumsUnshipped();

}

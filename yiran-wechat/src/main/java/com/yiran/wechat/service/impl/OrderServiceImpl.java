package com.yiran.wechat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yiran.wechat.domain.OrderRequest;
import com.yiran.wechat.domain.ProductCatVO;
import com.yiran.wechat.domain.WeChatOrderResponse;
import com.yiran.wechat.domain.WechatOrder;
import com.yiran.wechat.domain.WechatOrderDetail;
import com.yiran.wechat.domain.WechatOrderLogistics;
import com.yiran.wechat.domain.WechatOrderProductComment;
import com.yiran.wechat.domain.WechatReceivingAddress;
import com.yiran.wechat.enums.OrderStatus;
import com.yiran.wechat.service.IOrderService;
import com.yiran.wechat.service.IWechatOrderDetailService;
import com.yiran.wechat.service.IWechatOrderLogisticsService;
import com.yiran.wechat.service.IWechatOrderProductCommentService;
import com.yiran.wechat.service.IWechatOrderService;
import com.yiran.wechat.service.IWechatProductDescriptionService;
import com.yiran.wechat.service.IWechatProductService;
import com.yiran.wechat.service.IWechatReceivingAddressService;
import com.yiran.wechat.service.IWechatShoppingCartService;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
@Service
public class OrderServiceImpl implements IOrderService {
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	@Autowired
	private IWechatOrderService wechatOrderService;
	@Autowired
	private IWechatOrderDetailService wechatOrderDetailService;
	@Autowired
	private IWechatProductService wechatProductService;
	@Autowired
	private IWechatProductDescriptionService wechatProductDescriptionService;
	@Autowired
	private IWechatOrderLogisticsService wechatOrderLogisticsService;
	@Autowired
	private IWechatReceivingAddressService wechatReceivingAddressService;
	@Autowired
	private IWechatShoppingCartService wechatShoppingCartService;
	@Autowired
	private IWechatOrderProductCommentService wechatOrderProductCommentService;
	@Override
	public int createOrder(OrderRequest orderRequest) {
		try {
			//1.创建订单保存到wechat_order
			//1.1 转换订单信息
			WechatOrder wechatOrder = convertOrder(orderRequest);
			logger.info("订单order对象:"+JSON.toJSONString(wechatOrder));
			//TODO:保存订单数据
			wechatOrderService.insertWechatOrder(wechatOrder);
			//2.保存明细到wechat_order_detail
			List<WechatOrderDetail> orderDetailList = converOrderDetail(orderRequest,wechatOrder.getOrderId());
			logger.info("订单orderDetail对象:"+JSON.toJSONString(orderDetailList));
			for (WechatOrderDetail wechatOrderDetail : orderDetailList) {
				wechatOrderDetailService.insertWechatOrderDetail(wechatOrderDetail);
			}
			
			//3.订单物流表wechat_order_logistics
			WechatOrderLogistics orderLogistics = converOrderLogistics(orderRequest,wechatOrder.getOrderId());
			wechatOrderLogisticsService.insertWechatOrderLogistics(orderLogistics);
			
			//4.删除当前用户购物车中的订单
			//需要删除的productId
			List<Integer> productIds = converProductIds(orderRequest);
			wechatShoppingCartService.deleteWechatShoppingCartByIdAndUserId(productIds,orderRequest.getOpenId());
			return wechatOrder.getOrderId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
		
	}

	/**
	 * 产品ID集合
	 * @param orderRequest
	 * @return
	 */
	private List<Integer> converProductIds(OrderRequest orderRequest) {
		List<Integer> list = new ArrayList<Integer>();
		List<ProductCatVO> productCatList = orderRequest.getProductCatList();
		for (ProductCatVO vo : productCatList) {
			list.add(vo.getProductId());
		}
		return list;
	}

	/**
	 * 转换订单物流
	 * @param orderRequest
	 * @param orderId
	 * @return
	 */
	private WechatOrderLogistics converOrderLogistics(OrderRequest orderRequest, Integer orderId) {
		WechatOrderLogistics orderLogistics = new WechatOrderLogistics();
		try {
			orderLogistics.setOrderId(orderId);
			//根据收件人地址id获取收件人信息
			WechatReceivingAddress address = wechatReceivingAddressService.selectWechatReceivingAddressById(orderRequest.getAddressId());
			orderLogistics.setConsigneeRealname(address.getRealname());
			orderLogistics.setConsigneeTelphone(address.getTelephone());
			orderLogistics.setConsigneeAddress(address.getProvince()+address.getCity()+address.getArea()+address.getDetailedAddress());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderLogistics;
	}

	/**
	 * 转换订单明细列表
	 * @param orderRequest
	 * @return
	 */
	private List<WechatOrderDetail> converOrderDetail(OrderRequest orderRequest,int orderId) {
		List<WechatOrderDetail> list = new ArrayList<WechatOrderDetail>(); 
		List<ProductCatVO> productCatList = orderRequest.getProductCatList();
		try {
			for (ProductCatVO vo : productCatList) {
				WechatOrderDetail detail = new WechatOrderDetail();
				detail.setOrderId(String.valueOf(orderId));
				detail.setProductId(vo.getProductId());
				detail.setProductName(vo.getTitle());
				detail.setProductPrice(vo.getTotalPrice());
				detail.setNumber(vo.getTotalNum());
				detail.setProductModeDesc(vo.getAttr());
				detail.setProductModeParams(vo.getAttr());
				detail.setSubtotal(vo.getTotalPrice());
				detail.setImgUrl(vo.getPictureUrl());
				list.add(detail);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 转换订单对象
	 * @param orderRequest
	 * @return
	 */
	private WechatOrder convertOrder(OrderRequest orderRequest) {
		WechatOrder order = new WechatOrder();
		try {
			List<ProductCatVO> productCatList = orderRequest.getProductCatList();
			//订单号  由yyyyMMddhhmmss+6位随机数
			StringBuffer bf = new StringBuffer(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
			bf.append(RandomUtil.randomNumbers(6));
			order.setOrderNo(bf.toString());
			//订单状态:0未付款,1已付款,2已发货,3已签收,4退货申请,5退货中,6已退货,7取消交易
			order.setOrderStatus(OrderStatus.UNPAID.getCode());
			//商品数量:商品项目数量，不是商品 
			String productCount = String.valueOf(productCatList.size());
			order.setProductCount(productCount);
			// 商品总价 
			String productAmountTotal = getProductAmountTotal(productCatList);
			order.setProductAmountTotal(productAmountTotal);
			//订单金额:实际付款金额=总金额-优惠券
			String logisticsFee = String.valueOf(Double.parseDouble(productAmountTotal)-Double.parseDouble(orderRequest.getCouponValue()));
			order.setLogisticsFee(logisticsFee);
			//收货地址编号 
			String addressId = String.valueOf(orderRequest.getAddressId());
			order.setAddressId(addressId);
			//客户编号 
			String userId = orderRequest.getOpenId();
			order.setUserId(userId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return order;
	}

	/**
	 * 获取订单总金额
	 * @param productCatList
	 * @return
	 */
	private String getProductAmountTotal(List<ProductCatVO> productCatList) {
		double productAmountTotal = 0;
		for (ProductCatVO vo : productCatList) {
			productAmountTotal += Double.parseDouble(vo.getTotalPrice());
		}
		return String.valueOf(productAmountTotal);
	}

	/**
	 * 获取订单信息（全部订单，未支付订单，待发货，待收货，待评价）
	 */
	@Override
	public Map<String, Object> getOrder(String openId) {
		Map<String, Object> map = new HashMap<String,Object>();
		List<WeChatOrderResponse> allList = getAllOrders(openId);
		logger.info("所有订单信息:"+JSON.toJSONString(allList));
		map.put("allList", allList);
		//待付款
		List<WeChatOrderResponse> unpaidList = new ArrayList<WeChatOrderResponse>();
		//待发货
		List<WeChatOrderResponse> tobeShippedList = new ArrayList<WeChatOrderResponse>();
		//待收货
		List<WeChatOrderResponse> tobeReceivedList = new ArrayList<WeChatOrderResponse>();
		//待评价
		List<WeChatOrderResponse> tobeEvaluatedList = new ArrayList<WeChatOrderResponse>();
		for (WeChatOrderResponse orderResponse : allList) {
			if(OrderStatus.UNPAID.getCode().equals(orderResponse.getOrderStatus())){
				unpaidList.add(orderResponse);
			}
			if(OrderStatus.PAYMENT.getCode().equals(orderResponse.getOrderStatus())){
				tobeShippedList.add(orderResponse);
			}
			if(OrderStatus.SHIPPED.getCode().equals(orderResponse.getOrderStatus())){
				tobeReceivedList.add(orderResponse);
			}
			if(OrderStatus.ALREADY_SIGNED.getCode().equals(orderResponse.getOrderStatus())){
				tobeEvaluatedList.add(orderResponse);
			}
		}
		
		logger.info("未支付订单信息:"+JSON.toJSONString(unpaidList));
		map.put("unpaidList", unpaidList);
		logger.info("待发货订单信息:"+JSON.toJSONString(tobeShippedList));
		map.put("tobeShippedList", tobeShippedList);
		logger.info("待收货订单信息:"+JSON.toJSONString(tobeReceivedList));
		map.put("tobeReceivedList", tobeReceivedList);
		logger.info("待评价订单信息:"+JSON.toJSONString(tobeEvaluatedList));
		map.put("tobeEvaluatedList", tobeEvaluatedList);
		
		return map;
	}


	/**
	 * 获取所有的订单
	 * @param openId
	 * @return
	 */
	private List<WeChatOrderResponse> getAllOrders(String openId) {
		List<WeChatOrderResponse> allList = new ArrayList<WeChatOrderResponse>();
		WechatOrder wechatOrder = new WechatOrder();
		wechatOrder.setUserId(openId);
		List<WechatOrder> orderList = wechatOrderService.selectWechatOrderList(wechatOrder);
		for (WechatOrder order : orderList) {
			
			WeChatOrderResponse orderResponse = new WeChatOrderResponse();
			orderResponse.setOrderNo(order.getOrderNo());
			orderResponse.setOrderStatus(order.getOrderStatus());
			orderResponse.setOrderId(order.getOrderId());
			List<ProductCatVO> productList = new ArrayList<ProductCatVO>();
			WechatOrderDetail orderDetail = new WechatOrderDetail();
			orderDetail.setOrderId(String.valueOf(order.getOrderId()));
			int totalNum = 0;
			double totalPrice = 0;
			List<WechatOrderDetail> detailList = wechatOrderDetailService.selectWechatOrderDetailList(orderDetail);
			for (WechatOrderDetail d : detailList) {
				ProductCatVO vo = new ProductCatVO();
				vo.setTitle(d.getProductName());
				vo.setAttr(d.getProductModeDesc());
				vo.setPictureUrl(d.getImgUrl());
				vo.setProductId(d.getProductId());
				vo.setTotalNum(d.getNumber());
				vo.setTotalPrice(d.getSubtotal());
				vo.setIsComment(d.getIsComment());
				productList.add(vo);
				
				totalNum += Integer.parseInt(d.getNumber());
				totalPrice +=Double.parseDouble(d.getSubtotal());
				
			}
			orderResponse.setProductCatList(productList);
			orderResponse.setProductCount(String.valueOf(totalNum));
			orderResponse.setProductAmountTotal(String.valueOf(totalPrice));
			allList.add(orderResponse);
		}
		
		return allList;
	}

	@Override
	public WeChatOrderResponse getOrdersByOrderNo(String orderNo,String openId) {
		List<WeChatOrderResponse> allList = new ArrayList<WeChatOrderResponse>();
		WechatOrder wechatOrder = new WechatOrder();
		wechatOrder.setUserId(openId);
		wechatOrder.setOrderNo(orderNo);
		List<WechatOrder> orderList = wechatOrderService.selectWechatOrderList(wechatOrder);
		if(orderList.size() > 0){
			for (WechatOrder order : orderList) {
				
				WeChatOrderResponse orderResponse = new WeChatOrderResponse();
				orderResponse.setOrderNo(order.getOrderNo());
				orderResponse.setOrderStatus(order.getOrderStatus());
				orderResponse.setOrderId(order.getOrderId());
				List<ProductCatVO> productList = new ArrayList<ProductCatVO>();
				WechatOrderDetail orderDetail = new WechatOrderDetail();
				orderDetail.setOrderId(String.valueOf(order.getOrderId()));
				int totalNum = 0;
				double totalPrice = 0;
				List<WechatOrderDetail> detailList = wechatOrderDetailService.selectWechatOrderDetailList(orderDetail);
				for (WechatOrderDetail d : detailList) {
					ProductCatVO vo = new ProductCatVO();
					vo.setTitle(d.getProductName());
					vo.setAttr(d.getProductModeDesc());
					vo.setPictureUrl(d.getImgUrl());
					vo.setProductId(d.getProductId());
					vo.setTotalNum(d.getNumber());
					vo.setTotalPrice(d.getSubtotal());
					vo.setIsComment(d.getIsComment());
					productList.add(vo);
					
					totalNum += Integer.parseInt(d.getNumber());
					totalPrice +=Double.parseDouble(d.getSubtotal());
					
				}
				orderResponse.setProductCatList(productList);
				orderResponse.setProductCount(String.valueOf(totalNum));
				orderResponse.setProductAmountTotal(String.valueOf(totalPrice));
				allList.add(orderResponse);
			}
			return allList.get(0);
		}else{
			return null;
		}
		
		
	}

	@Override
	public int saveOrderProductComment(WechatOrderProductComment comment) {
		int productComment = wechatOrderProductCommentService.insertWechatOrderProductComment(comment);
		return productComment;
	}

	@Override
	public int updateOrderStatustranSactionCompleted(String orderId) {
		wechatOrderService.updateOrderStatustranSactionCompleted(orderId);
		return 0;
	}

	@Override
	public int updateOrderDetilIsCommentStatus(String orderId,String productId) {
		
		return wechatOrderDetailService.updateOrderDetilIsCommentStatus(orderId,productId);
	}

	@Override
	public List<WechatOrderDetail> selectWechatOrderDetailList(WechatOrderDetail orderDetail) {
		return  wechatOrderDetailService.selectWechatOrderDetailList(orderDetail);
	}

	@Override
	public int updateOrderStatus(String orderNo, String openId, String orderStatus) {
		return wechatOrderService.updateOrderStatus(orderNo, openId, orderStatus);
	}

	@Override
	public WechatOrder selectOrderByOrderId(int orderId) {
		return wechatOrderService.selectWechatOrderById(orderId);
	}

	@Override
	public int updateOrder(WechatOrder weChatorder) {
		return wechatOrderService.updateWechatOrder(weChatorder);
	}

	@Override
	public WechatOrder selectOrderByOrderNo(String orderNo) {
		return wechatOrderService.selectOrderByOrderNo(orderNo);
	}

	@Override
	public WeChatOrderResponse getOrdersByOrderId(int orderId) {
		List<WeChatOrderResponse> allList = new ArrayList<WeChatOrderResponse>();
		WechatOrder wechatOrder = new WechatOrder();
		wechatOrder.setOrderId(orderId);
		List<WechatOrder> orderList = wechatOrderService.selectWechatOrderList(wechatOrder);
		if(orderList.size() > 0){
			for (WechatOrder order : orderList) {
				
				WeChatOrderResponse orderResponse = new WeChatOrderResponse();
				orderResponse.setOrderNo(order.getOrderNo());
				orderResponse.setOrderStatus(order.getOrderStatus());
				orderResponse.setOrderId(order.getOrderId());
				List<ProductCatVO> productList = new ArrayList<ProductCatVO>();
				WechatOrderDetail orderDetail = new WechatOrderDetail();
				orderDetail.setOrderId(String.valueOf(order.getOrderId()));
				int totalNum = 0;
				double totalPrice = 0;
				List<WechatOrderDetail> detailList = wechatOrderDetailService.selectWechatOrderDetailList(orderDetail);
				for (WechatOrderDetail d : detailList) {
					ProductCatVO vo = new ProductCatVO();
					vo.setTitle(d.getProductName());
					vo.setAttr(d.getProductModeDesc());
					vo.setPictureUrl(d.getImgUrl());
					vo.setProductId(d.getProductId());
					vo.setTotalNum(d.getNumber());
					vo.setTotalPrice(d.getSubtotal());
					vo.setIsComment(d.getIsComment());
					productList.add(vo);
					
					totalNum += Integer.parseInt(d.getNumber());
					totalPrice +=Double.parseDouble(d.getSubtotal());
					
				}
				orderResponse.setProductCatList(productList);
				orderResponse.setProductCount(String.valueOf(totalNum));
				orderResponse.setProductAmountTotal(String.valueOf(totalPrice));
				allList.add(orderResponse);
			}
			return allList.get(0);
		}else{
			return null;
		}
	}

	@Override
	public int getOrderNumsUnshipped() {
		
		return wechatOrderService.getOrderNumsUnshipped();
	}

	
}

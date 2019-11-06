package com.yiran.api.facade;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiran.api.enums.ResultEnum;
import com.yiran.api.vo.ResultReturnVO;
import com.yiran.common.exception.base.BaseException;
import com.yiran.common.utils.DateUtils;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.enums.NotifyEventCode;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.wechat.domain.WechatOrder;
import com.yiran.wechat.domain.WechatOrderDetail;
import com.yiran.wechat.domain.WechatProduct;
import com.yiran.wechat.enums.OrderStatus;
import com.yiran.wechat.service.IOrderService;
import com.yiran.wechat.service.IWechatProductService;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.service.IPushMessageService;
import com.yiran.weixin.service.IWeixinSettingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/yiran/channelpay/notify")
@Api(value="处理异步通知接口",description="处理异步通知接口")
public class ResultNotifyFacade {
	private Logger logger = LoggerFactory.getLogger(ResultNotifyFacade.class);
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IWechatProductService wechatProductService;
	@Autowired
	private IPushMessageService pushMessageService;
	@Autowired
	private IWeixinSettingService weixinSettingService;
	@PostMapping("/resultNotify")
	@ApiOperation(value = "异步通知接口",notes="异步通知接口")
	public ResultReturnVO returnResult(@RequestBody @ApiParam(name="data" ,value="请求参数JSON数据格式",required=true)String data){
		logger.info("微信订单业务通知ResultNotifyFacade-请求参数："+data);
		Map<String, String> map = MapUtil.jsonToMap(data);
		//NotifyEventCode.PAY_SUCCESS.getCode()
		String orderNo = map.get("order_id");//业务系统订单号
		logger.info("微信订单业务通知ResultNotifyFacade-业务系统订单号:【{}】",orderNo);
		String tradeStatus = map.get("trade_status");
		logger.info("微信订单业务通知ResultNotifyFacade-交易状态:【{}】",tradeStatus);
		String instOrderNo = map.get("inst_order_no");
		logger.info("微信订单业务通知ResultNotifyFacade-机构订单:【{}】",instOrderNo);
		String fundChannelCode = map.get("fund_channel_code");
		logger.info("微信订单业务通知ResultNotifyFacade-支付渠道编号:【{}】",fundChannelCode);
		
		//根据订单号获取订单信息
		WechatOrder order = orderService.selectOrderByOrderNo(orderNo);
		logger.info("根据订单No获取订单信息:{}",JSON.toJSONString(order));
    	if (order == null) {
            throw new BaseException("微信支付", ResultEnum.ORDER_NOT_EXIST.getMessage());
        }
		//更新订单支付状态 ，支付渠道
		WechatOrder weChatorder = new WechatOrder();
		weChatorder.setOrderId(order.getOrderId());
		if(tradeStatus.equals(NotifyEventCode.PAY_AWAIT.getCode())){
			weChatorder.setPayStatus(PayOrderStatus.IN_PROCESS.getCode());
		}else if(tradeStatus.equals(NotifyEventCode.PAY_SUCCESS.getCode())){
			weChatorder.setPayStatus(PayOrderStatus.SUCCESSFUL.getCode());
		}else if(tradeStatus.equals(NotifyEventCode.PAY_FAIL.getCode())){
			weChatorder.setPayStatus(PayOrderStatus.FAILURE.getCode());
		}
		weChatorder.setPayChannel(fundChannelCode);
		weChatorder.setPayTime(new Date());
		weChatorder.setOrderStatus(OrderStatus.PAYMENT.getCode());
		orderService.updateOrder(weChatorder);
		WechatOrderDetail orderDetail = new WechatOrderDetail();
		orderDetail.setOrderId(String.valueOf(order.getOrderId()));
		List<WechatOrderDetail> detailList = orderService.selectWechatOrderDetailList(orderDetail);
		StringBuffer sb = new StringBuffer();
		for (WechatOrderDetail od : detailList) {
			WechatProduct pro = wechatProductService.selectWechatProductById(od.getProductId());
			WechatProduct product = new WechatProduct();
			product.setProductId(od.getProductId());
			int temp = Integer.parseInt(pro.getStock())-Integer.parseInt(od.getNumber());
			product.setStock(String.valueOf(temp));
			wechatProductService.updateWechatProduct(product);
			sb.append("标题："+od.getProductName());
			sb.append("\n");
			sb.append("规格："+od.getProductModeDesc());
			sb.append("\n");
			sb.append("数量："+od.getNumber());
			sb.append("\n");
			sb.append("价格："+od.getProductPrice());
			sb.append("\n");
		}
		
		String url = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_TEMPLATE_LINK).getWeixinValue();
		Map<String,String> mapMessage = new HashMap<String,String>();
		mapMessage.put("openId", order.getWeixinUser().getOpenid());
		mapMessage.put("buyerName", order.getWeixinUser().getNickname());
		mapMessage.put("amount", order.getLogisticsFee());
		mapMessage.put("orderNo", order.getOrderNo());
		mapMessage.put("content",sb.toString());
		mapMessage.put("url",url);
		
		//消息推送支付成功
		pushMessageService.pushPayMessage(MapUtil.mapToJson(mapMessage));
		
		//新订单通知 ->推送给商家   WECHAT_STORE_OPENID	
		String store_openId = weixinSettingService.getValueByKey(WeixinSetting.KEY_WECHAT_STORE_OPENID).getWeixinValue();
		Map<String,String> newMap = new HashMap<String,String>();
		newMap.put("openId", store_openId);
		newMap.put("tradeDateTime", DateUtils.format(order.getCreateTime(), DateUtils.newFormat));
		newMap.put("orderType", "商品交易");
		newMap.put("customerInfo", order.getWeixinUser().getCity()+"  "+order.getWeixinUser().getNickname());
		newMap.put("orderItemName", "商品信息");
		newMap.put("orderItemData", sb.toString());
		//获取已支付未发货订单数量
		int orderNum = orderService.getOrderNumsUnshipped();
		newMap.put("orderNum", String.valueOf(orderNum));
		pushMessageService.pushNewOrderNotification(MapUtil.mapToJson(newMap));
		
		ResultReturnVO vo = new ResultReturnVO();
		vo.setRet_code("0000");
		vo.setRet_msg("交易成功");
		return vo;
		
	}
	
	
	
	
}

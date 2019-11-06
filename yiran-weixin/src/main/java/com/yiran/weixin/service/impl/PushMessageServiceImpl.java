package com.yiran.weixin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.weixin.domain.WeixinTemplate;
import com.yiran.weixin.mapper.WeixinTemplateMapper;
import com.yiran.weixin.service.IPushMessageService;
import com.yiran.weixin.utils.WeiXinJsonUtil;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

/**
 * 发送模板消息
 * @author pandaa
 *
 */
@Service
public class PushMessageServiceImpl implements IPushMessageService {
	private static final Logger logger = LoggerFactory.getLogger(PushMessageServiceImpl.class);
	//订单支付成功
	private final static String ORDER_PAYMENT_SUCCESSFULLY = "order_payment_successfully";
	//退款成功
	private final static String REFUND_NOTICE = "refund_notice";
	//新订单通知
	private final static String NEW_ORDER_NOTIFICATION = "new_order_notification";
	//商品已发出通知
	private final static String COMMODIT_HAS_BEEN_NOTIFIED = "commodit_has_been_notified";
	//订单包裹跟踪通知
	private final static String PARCEL_TRACKING = "parcel_tracking";
	@Autowired
    private WxMpService wxMpService ;
	
	@Autowired
	private WeixinTemplateMapper weixinTemplateMapper;
	
	@Override
	public void pushPayMessage(String jsonStr) {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		WeixinTemplate  weixinTemplate = weixinTemplateMapper.selectWeixinTemplateByTemplateKey(ORDER_PAYMENT_SUCCESSFULLY);
        //模板ID
		templateMessage.setTemplateId(weixinTemplate.getTemplateId());
		
		Map<String, String> map = WeiXinJsonUtil.jsonToMap(jsonStr);
		
		//接收消息openId
        templateMessage.setToUser(map.get("openId"));
        
        List<WxMpTemplateData> data = new ArrayList<WxMpTemplateData>();
        data.add(new WxMpTemplateData("first", "【"+map.get("buyerName")+"】,您的订单已支付成功！"));
        data.add(new WxMpTemplateData("orderMoneySum", "￥" + map.get("amount"),"red"));
        data.add(new WxMpTemplateData("orderProductName", "\n订单号:"+map.get("orderNo")+"\n订单信息:\n"+map.get("content")));
        data.add(new WxMpTemplateData("Remark", "\n\n如有问题请直接在微信留言，我们将第一时间为您服务！欢迎再次光临！"));
        templateMessage.setData(data);
        templateMessage.setUrl(map.get("url"));
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
        	logger.error("【微信模版消息】发送失败, {}", e);
        }
		
		
	}

	@Override
	public void pushRefundMessage(String jsonStr) {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		WeixinTemplate  weixinTemplate = weixinTemplateMapper.selectWeixinTemplateByTemplateKey(REFUND_NOTICE);
        //模板ID
		templateMessage.setTemplateId(weixinTemplate.getTemplateId());
		
		Map<String, String> map = WeiXinJsonUtil.jsonToMap(jsonStr);
		//接收消息openId
        templateMessage.setToUser(map.get("openId"));
        
        List<WxMpTemplateData> data = new ArrayList<WxMpTemplateData>();
        data.add(new WxMpTemplateData("first", "【"+map.get("buyerName")+"】,您的订单已退款成功！"));
        data.add(new WxMpTemplateData("refund", "￥" + map.get("amount"),"red"));
        data.add(new WxMpTemplateData("reason", "取消订单"));
        data.add(new WxMpTemplateData("remark", "\n\n备注：如有疑问，请致电13260695888联系我们，或回复M来了解详情。"));
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
        	logger.error("【微信模版消息】发送失败, {}", e);
        }
	}

	/**
	 * 发送新订单通知
	 * 
	 * 您收到了一条新的订单。

		提交时间：02月18日 01时05分
		订单类型：询价订单
		客户信息：广州 王俊
		兴趣车型：骐达 2011款 1.6 CVT 舒适版

		截止24日09:39分,您尚有10个订单未处理。
		详情
		
		
		{{first.DATA}}

		提交时间：{{tradeDateTime.DATA}}
		订单类型：{{orderType.DATA}}
		客户信息：{{customerInfo.DATA}}
		{{orderItemName.DATA}}：{{orderItemData.DATA}}
		{{remark.DATA}}
	 */
	@Override
	public void pushNewOrderNotification(String jsonStr) {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		WeixinTemplate  weixinTemplate = weixinTemplateMapper.selectWeixinTemplateByTemplateKey(NEW_ORDER_NOTIFICATION);
        //模板ID
		templateMessage.setTemplateId(weixinTemplate.getTemplateId());
		
		Map<String, String> map = WeiXinJsonUtil.jsonToMap(jsonStr);
		
		//接收消息openId
        templateMessage.setToUser(map.get("openId"));
        
        List<WxMpTemplateData> data = new ArrayList<WxMpTemplateData>();
        data.add(new WxMpTemplateData("first", "您收到了一条新的订单,请及时处理!"));
        data.add(new WxMpTemplateData("tradeDateTime", map.get("tradeDateTime")));
        data.add(new WxMpTemplateData("orderType", map.get("orderType")));
        data.add(new WxMpTemplateData("customerInfo", map.get("customerInfo")));
        data.add(new WxMpTemplateData("orderItemName", map.get("orderItemName")));
        data.add(new WxMpTemplateData("orderItemData", map.get("orderItemData")));
        data.add(new WxMpTemplateData("remark", "\n\n截止"+map.get("tradeDateTime")+",您尚有"+map.get("orderNum")+"个订单未处理"));
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
        	logger.error("【微信模版消息】发送失败, {}", e);
        }
		
	}

	/**
	 * 商品已发出通知
	 * 亲，宝贝已经启程了，好想快点来到你身边

	      快递公司：顺丰快递
	     快递单号：3291987391
	     备注：如果疑问，请在微信服务号中输入“KF”，**将在第一时间为您服务！
	     
	     
	     {{first.DATA}} 

		快递公司：{{delivername.DATA}}
		快递单号：{{ordername.DATA}}
		{{remark.DATA}}
	 */
	@Override
	public void pushCommoditHasBeenNotified(String jsonStr) {
		WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
		WeixinTemplate  weixinTemplate = weixinTemplateMapper.selectWeixinTemplateByTemplateKey(COMMODIT_HAS_BEEN_NOTIFIED);
        //模板ID
		templateMessage.setTemplateId(weixinTemplate.getTemplateId());
		
		Map<String, String> map = WeiXinJsonUtil.jsonToMap(jsonStr);
		
		//接收消息openId
        templateMessage.setToUser(map.get("openId"));
        
        List<WxMpTemplateData> data = new ArrayList<WxMpTemplateData>();
        data.add(new WxMpTemplateData("first", "亲，宝贝已经启程了，好想快点来到你身边"));
        data.add(new WxMpTemplateData("delivername", map.get("delivername")));
        data.add(new WxMpTemplateData("ordername", map.get("trackingNumber")));
        data.add(new WxMpTemplateData("remark", "\n\n如有问题请直接在微信留言，我们将第一时间为您服务！欢迎再次光临！"));
        templateMessage.setData(data);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        }catch (WxErrorException e) {
        	logger.error("【微信模版消息】发送失败, {}", e);
        }
		
	}

	

}

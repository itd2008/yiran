package com.yiran.api.facade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.netfinworks.common.domain.Extension;
import com.yiran.api.enums.ResultEnum;
import com.yiran.api.enums.VerifyResultEnum;
import com.yiran.api.vo.VerifyResult;
import com.yiran.common.base.ResultWrapper;
import com.yiran.common.exception.base.BaseException;
import com.yiran.pay.sdk.model.PayFundResponse;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.request.PayOrderRequest;
import com.yiran.payorder.response.PayFundResult;
import com.yiran.payorder.service.IFundRequestService;
import com.yiran.wechat.domain.OrderRequest;
import com.yiran.wechat.domain.WeChatOrderResponse;
import com.yiran.wechat.domain.WechatOrder;
import com.yiran.wechat.domain.WechatOrderDetail;
import com.yiran.wechat.domain.WechatOrderProductComment;
import com.yiran.wechat.domain.WechatProductCoupon;
import com.yiran.wechat.domain.WechatReceivingAddress;
import com.yiran.wechat.enums.OrderStatus;
import com.yiran.wechat.service.IOrderService;
import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.service.IWeixinSettingService;
import com.yiran.weixin.service.IWeixinUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/yiran/weChatMall/order")
@Api(value="微信商城订单接口",description="微信商城订单接口")
public class WechatMallOrderFacade {
	private static final Logger logger = LoggerFactory.getLogger(WechatMallOrderFacade.class);
	
	@Autowired
	private IOrderService orderService;
	@Autowired
	private IWeixinUserService weixinUserService;
	@Autowired
	private IWeixinSettingService weixinSettingService;
	@Autowired
	private IFundRequestService fundRequestService;
	@PostMapping("/createOrder")
    @ApiOperation(value = "创建订单",notes="创建订单")
    public ResultWrapper<Map<String,Object>> createOrder(@RequestBody @ApiParam(name="orderJson",value="请求参数json数据")String orderJson) {
        try {
        	logger.info("创建订单参数:{}",orderJson);
        	OrderRequest orderRequest = JSON.parseObject(orderJson, OrderRequest.class);
        	logger.info("json转换WechatReceivingAddress对象后数据:"+JSON.toJSONString(orderRequest));
    		int orderId = orderService.createOrder(orderRequest);
        	return ResultWrapper.ok().putData(orderId);
        } catch (Exception e) {
            logger.error("创建订单信息异常 : ", e);
            throw new RuntimeException("调用createOrder接口异常", e);
        }

    }
	
	@PostMapping("/createPay/{orderId}")
    @ApiOperation(value = "发起支付订单",notes="发起支付订单")
    public ResultWrapper<Map<String,Object>> createPay(@ApiParam(required = true, name = "orderId", value = "订单号orderId") @PathVariable int orderId) {
        try {
        	logger.info("发起支付订单参数:{}",orderId);
        	Map<String,Object> map = new HashMap<String,Object>();
        	//根据订单Id获取订单信息
        	WechatOrder order = orderService.selectOrderByOrderId(orderId);
        	logger.info("根据订单Id获取订单信息:{}",JSON.toJSONString(order));
        	if (order == null) {
                throw new BaseException("微信支付", ResultEnum.ORDER_NOT_EXIST.getMessage());
            }
        	//获取配置的
    		String returnUrl = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_REFUN_REDIRECT_URI).getWeixinValue();
    		map.put("returnUrl", returnUrl);
    		logger.info("支付成功后跳转页面地址：{}",returnUrl);
    		
        	if(!StringUtils.isBlank(order.getExtend()) && OrderStatus.UNPAID.getCode().equals(order.getOrderStatus())){//第一次支付么有支付成功，只是创建支付订单，并没有真正的支付
        		PayFundResponse payResponse = converterPayResponse(MapUtil.jsonToMap(order.getExtend()));
        		map.put("result", payResponse);
        		return ResultWrapper.ok().putData(map);
        	}
        	
        	PayOrderRequest request = new PayOrderRequest();
        	double amount = Double.parseDouble(order.getLogisticsFee());
    		BigDecimal money = new BigDecimal(amount).setScale(2, BigDecimal.ROUND_DOWN);
    		request.setAmount(money);
    		request.setBizTime(new Date());
    		request.setBizType(BizType.FUNDIN);
    		request.setCurrencyCode("CNY");
    		request.setInstCode("WXPAY");
    		//会员ID 目前写死（会员系统还未完成）
    		request.setMemberId("100001605065");
    		//支付方式
    		request.setPayMode(PayMode.TRUSTCOLLECT);
    		//支付编码
    		request.setPaymentCode("1001");
    		//支付订单号
    		request.setPaymentSeqNo(order.getOrderNo());
    		//产品编码
    		request.setProductCode("20040001");
    		//清结算ID 目前没有清结算系统暂不写
    		//request.setSettlementId("2019082020617002");
    		Extension ext = new Extension();
    		ext.add("openId", order.getWeixinUser().getOpenid());
    		ext.add("orderName", order.getOrderNo());
    		ext.add("idType", "IC");
    		ext.add("verifyFlag", "Y");
    		ext.add("signed", "N");
    		ext.add("COMPANY_OR_PERSONAL", "C");
    		ext.add("DBCR", "DC");
    		ext.add("ACCESS_CHANNEL", "WEB");
    		ext.add("smsSender", "PLATFORM");
    		ext.add("payeeId", "innerMember");
    		//TODO:商户号可配置，目前写死
    		ext.add("partnerId", "20000126505");
    		ext.add("clientId", "payment");
    		ext.add("PAGE_URL", weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_NOTIFYURL).getWeixinValue());
    		request.setExtension(ext);
    		
    		PayFundResult result = fundRequestService.apply(request);
    		logger.info("调用支付渠道返回结果："+JSON.toJSONString(result));
    		PayFundResponse payResponse = converterPayResponse(result.getExtension());
    		logger.info("发起微信支付返回结果："+JSON.toJSONString(payResponse));
    		map.put("result", payResponse);
    		//更新订单相关信息  --支付状态，支付渠道，支付订单号，支付时间
    		WechatOrder weChatorder = new WechatOrder();
    		weChatorder.setOrderId(orderId);
    		weChatorder.setPayStatus(PayOrderStatus.IN_PROCESS.getCode());
    		weChatorder.setPayChannel(result.getFundsChannel());
    		weChatorder.setEscrowTradeNo(result.getInstOrderNo());
    		weChatorder.setPayTime(result.getInstPayTime());
    		weChatorder.setExtend(JSON.toJSONString(payResponse));
    		orderService.updateOrder(weChatorder);
    		
        	return ResultWrapper.ok().putData(map);
        } catch (Exception e) {
            logger.error("获取订单信息异常 : ", e);
            throw new RuntimeException("调用createPay接口异常", e);
        }

    }
	
	private PayFundResponse converterPayResponse(Extension ext) {
		PayFundResponse resp = new PayFundResponse();
		resp.setAppId(ext.getValue("appId"));
		resp.setNonceStr(ext.getValue("nonceStr"));
		resp.setPackAge(ext.getValue("packAge"));
		resp.setPaySign(ext.getValue("paySign"));
		resp.setReturnCode(ext.getValue("returnCode"));
		resp.setReturnMsg(ext.getValue("returnMsg"));
		resp.setSignType(ext.getValue("signType"));
		resp.setTimeStamp(ext.getValue("timeStamp"));
		return resp;
	}
	
	private PayFundResponse converterPayResponse(Map<String,String> ext) {
		PayFundResponse resp = new PayFundResponse();
		resp.setAppId(ext.get("appId"));
		resp.setNonceStr(ext.get("nonceStr"));
		resp.setPackAge(ext.get("packAge"));
		resp.setPaySign(ext.get("paySign"));
		resp.setReturnCode(ext.get("returnCode"));
		resp.setReturnMsg(ext.get("returnMsg"));
		resp.setSignType(ext.get("signType"));
		resp.setTimeStamp(ext.get("timeStamp"));
		return resp;
	}

	@PostMapping("/getOrders/{openId}")
    @ApiOperation(value = "获取订单信息",notes="获取订单信息")
    public ResultWrapper<Map<String,Object>> getOrders(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId) {
        try {
    		Map<String,Object> map = orderService.getOrder(openId);
        	return ResultWrapper.ok().putData(map);
        } catch (Exception e) {
            logger.error("获取订单信息异常 : ", e);
            throw new RuntimeException("调用getOrders接口异常", e);
        }

    }
	
	@PostMapping("/getOrdersByOrderNo/{orderNo}/{openId}")
    @ApiOperation(value = "根据订单号获取订单信息",notes="根据订单号获取订单信息")
	public ResultWrapper<Map<String,Object>> getOrdersByOrderNo(@ApiParam(required = true, name = "orderNo", value = "订单号") @PathVariable String orderNo,
			@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		logger.info("根据订单号获取订单信息请求参数->订单号："+orderNo+",微信OpenId:"+openId);
		WeChatOrderResponse orderResponse = orderService.getOrdersByOrderNo(orderNo,openId);
		return ResultWrapper.ok().putData(orderResponse);
	}
	
	@PostMapping("/getOrdersByOrderId/{orderId}")
    @ApiOperation(value = "根据订单ID获取订单信息",notes="根据订单ID获取订单信息")
	public ResultWrapper<Map<String,Object>> getOrdersByOrderId(@ApiParam(required = true, name = "orderId", value = "订单ID") @PathVariable int orderId){
		logger.info("根据订单ID获取订单信息请求参数->订单号："+orderId);
		WeChatOrderResponse orderResponse = orderService.getOrdersByOrderId(orderId);
		return ResultWrapper.ok().putData(orderResponse);
	}
	/**
	 * 确认收货
	 * @param orderNo
	 * @return
	 */
	@PostMapping("/receipt/{orderNo}/{openId}")
    @ApiOperation(value = "根据订单号确认收货",notes="根据订单号确认收货")
	public ResultWrapper<Map<String,Object>> receipt(@ApiParam(required = true, name = "orderNo", value = "订单号") @PathVariable String orderNo,
			@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		logger.info("根据订单号确认收货请求参数->订单号："+orderNo+",微信OpenId:"+openId);
		int isok = orderService.updateOrderStatus(orderNo,openId,OrderStatus.ALREADY_SIGNED.getCode());
		return ResultWrapper.ok().putData(isok);
	}
	
	/**
	 * 删除订单
	 * @param orderNo
	 * @return
	 */
	@PostMapping("/delete/{orderNo}/{openId}")
    @ApiOperation(value = "删除订单",notes="根据订单号删除订单，将订单置为已取消状态（软删除）")
	public ResultWrapper<Map<String,Object>> delete(@ApiParam(required = true, name = "orderNo", value = "订单号") @PathVariable String orderNo,
			@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		logger.info("删除订单请求参数->订单号："+orderNo+",微信openId:"+openId);
		int isok = orderService.updateOrderStatus(orderNo,openId,OrderStatus.CANCEL_TRANSACTION.getCode());
		return ResultWrapper.ok().putData(isok);
	}
	
	@PostMapping("/saveOrderProductComment")
    @ApiOperation(value = "发布评论",notes="【请求参数】:  \n"
			+ "orderId:订单ID  \n"
			+ "productId:产品ID   \n"
			+ "comment:评论内容   \n"
			+ "commentary_score:评论分数   \n"
			+ "openId:   \n"
			)
    public ResultWrapper<Map<String,Object>> saveOrderProductComment(@RequestBody @ApiParam(name="commentJson",value="请求参数json数据")String commentJson) {
        try {
        	logger.info("发布评论参数:{}",commentJson);
        	WechatOrderProductComment comment = JSON.parseObject(commentJson, WechatOrderProductComment.class);
        	logger.info("json转换WechatOrderProductComment对象后数据:"+JSON.toJSONString(comment));
        	//验证参数是否为空
        	VerifyResult verifyResult = checkParameter(comment);
        	if(!verifyResult.isSuccess()){
        		return ResultWrapper.newInstance(verifyResult.getCode(), verifyResult.getMsg());
        	}
        	//获取openId TODO:从session中获取
    		String openId = comment.getOpenId();
    		//根据openId查询用户的昵称
    		WeixinUser weixinUser = weixinUserService.selectWeixinUserByOpenId(openId);
    		comment.setUserNick(weixinUser.getNickname());
    		orderService.saveOrderProductComment(comment);	
    		//更新订单明细中是否评论字段
    		orderService.updateOrderDetilIsCommentStatus(comment.getOrderId(),comment.getProductId());
        	
    		WechatOrderDetail orderDetail = new WechatOrderDetail();
			orderDetail.setIsComment("0");//未评论
			orderDetail.setOrderId(comment.getOrderId());
			List<WechatOrderDetail> detailList = orderService.selectWechatOrderDetailList(orderDetail);
    		int redrectFlag = 0;//评论全部完成跳转到all_list
			if(detailList.size()>0){
				redrectFlag = 1;//还有评论没有完成
			}else{
				//修改订单状态为完成
	    		orderService.updateOrderStatustranSactionCompleted(comment.getOrderId());
			}
    		
            return ResultWrapper.ok().putData(redrectFlag);
        } catch (Exception e) {
            logger.error("保存收件人地址信息异常 : ", e);
            throw new RuntimeException("调用saveRealnameAddress接口异常", e);
        }

    }

	private VerifyResult checkParameter(WechatOrderProductComment comment) {
		
		VerifyResult verifyResult = new VerifyResult();
		if(StringUtils.isBlank(comment.getOrderId())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("订单ID为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		if(StringUtils.isBlank(comment.getProductId())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("产品ID为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		if(StringUtils.isBlank(comment.getComment())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("评论内容为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		if(StringUtils.isBlank(comment.getCommentaryScore())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("评论分数为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		verifyResult.setSuccess(true);
		return verifyResult;
	}
}

package com.yiran.payorder.facade;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.netfinworks.biz.common.util.BaseResult;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.common.utils.HttpEncoding;
import com.yiran.common.utils.http.WebHttpClient;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.converter.InstOrderResultConverter;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.PayResultNotify;
import com.yiran.payorder.domain.PayResultNotifyLog;
import com.yiran.payorder.domain.ReturnInfo;
import com.yiran.payorder.enums.NotifyEventCode;
import com.yiran.payorder.enums.NotifyStatus;
import com.yiran.payorder.notify.OrderResult;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IPayResultNotifyLogService;
import com.yiran.payorder.service.IPayResultNotifyService;
import com.yiran.payorder.service.InstitutionResultService;

import cn.hutool.core.lang.UUID;
@Service
public class DefaultResultNotifyFacade implements IResultNotifyFacade {

	Logger  logger =  LoggerFactory.getLogger(DefaultResultNotifyFacade.class);

	@Autowired
	private IPayInstOrderService payInstOrderService;
    
    @Autowired
	private IChannelPayOrderService channelPayOrderService;
    @Autowired
    private InstitutionResultService      institutionResultService;

    @Autowired
    private IPayResultNotifyLogService payResultNotifyLogService;
    @Autowired
    private IPayResultNotifyService payResultNotifyService;

    @Override
    public ReturnInfo notify(OrderResult result) {
        if (logger.isInfoEnabled()) {
            logger.info("机构通知结果" + result.toString());
        }
        ReturnInfo returnInfo = new ReturnInfo();
        try {
            if (result.getOrderResult() != null) {
                PayInstOrderResult instResult = result.getOrderResult();
                PayInstOrder instOrder = payInstOrderService.loadByInstOrderNo(instResult.getInstOrderNo());
                if (instOrder == null || instOrder.getInstOrderId() == null) {
                    logger.warn("[渠道回调通知]cmf处理失败[" + instResult.getInstOrderNo() + "],机构订单不存在");
                    returnInfo.setReturnCode("-1");
                    returnInfo.setReturnMsg("cmf处理失败[" + instResult.getInstOrderNo() + "],机构订单不存在");
                    return returnInfo;
                }
                if (!validteAmount(instResult, instOrder)) {
                    logger.warn("[渠道回调通知]cmf处理失败[" + instOrder.getInstOrderNo() + "],金额校验不通过,返回金额["
                                + instResult.getRealAmount() + "],原订单金额[" + instOrder.getAmount()
                                + "]");
                    returnInfo.setReturnCode("-1");
                    returnInfo.setReturnMsg("cmf处理失败[" + instOrder.getInstOrderNo() + "],金额校验不通过");
                    return returnInfo;
                }
                InstOrderResultConverter.convert(instOrder, instResult);
                ChannelPayOrder channelPayOrder = channelPayOrderService
                    .loadByInstOrderId(instOrder.getInstOrderId());
                BaseResult baseResult = institutionResultService.process(channelPayOrder, instOrder, instResult, false);
                if(baseResult.isSuccess()){
                    returnInfo.setReturnCode("0");
                    returnInfo.setReturnMsg(baseResult.getResultMessage());
                    return returnInfo;
                }else{
                    returnInfo.setReturnCode("-1");
                    returnInfo.setReturnMsg(baseResult.getResultMessage());
                    return returnInfo;
                }
            }
        } catch (Exception e) {
            logger.error("通知失败", e);
            returnInfo.setReturnCode("-1");
            returnInfo.setReturnMsg("处理失败," + e.getMessage());
            return returnInfo;
        }
        returnInfo.setReturnCode("0");
        returnInfo.setReturnMsg("处理成功");
        return returnInfo;
    }

    /**
     * 校验金额判断是否通过
     * @param instResult
     * @param instOrder
     * @return
     */
    private boolean validteAmount(PayInstOrderResult instResult, PayInstOrder instOrder) {
        String sourceCode = instResult.getExtension().get(ExtensionKey.SOURCE_CODE.key);
        boolean isNeedValidate = ("COUNTER".equalsIgnoreCase(sourceCode));
        if (isNeedValidate) {
            return (instResult.getRealAmount() != null
                    && instResult.getRealAmount().getAmount() != null && instResult.getRealAmount()
                .compareTo(instOrder.getAmount()) == 0);
        }
        return true;
    }

	@Override
	public String notifyBiz(String url, String data) {
         logger.info("[ChannelPayOrder-Biz]异步通知业务系统，通知地址："+url+",通知数据："+data);
		String responseJson = null;
		try {
			responseJson = WebHttpClient.postRequest(data,url,HttpEncoding.UTF8, null, null);
		} catch (Exception e) {
			responseJson = "HTTP请求发送失败，可能地址不通";
		}
		logger.info("渠道响应结果:"+responseJson);
		return responseJson;
	}

	@Override
	public void notifyBiz(String instOrderNo) {
		PayInstOrder instOrder = payInstOrderService.loadByInstOrderNo(instOrderNo);
		ChannelPayOrder channelPayOrder = channelPayOrderService.loadByInstOrderId(instOrder.getInstOrderId());
		try {
			String machineIp = InetAddress.getLocalHost().getHostAddress();
			String machineHostName = InetAddress.getLocalHost().getHostName();
			//判断该订单是否发送过异步通知，成功过就不在发送异步通知，失败并重试次数小于30次
			PayResultNotify notify = payResultNotifyService.selectPayResultNotifyByPaymentSeqNo(channelPayOrder.getPaymentSeqNo());
			if(notify != null){
				if(("PAY_AWAIT".equals(notify.getEventCode()) && notify.getRetryCount()<=30) || 
						("PAY_FAIL".equals(notify.getEventCode())&& !"S".equals(notify.getNotifyStatus()) && notify.getRetryCount()<=30)){
					//通知事件编码(根据结果状态判断)
					if(InstOrderStatus.SUCCESSFUL == instOrder.getStatus()){
						notify.setEventCode(NotifyEventCode.PAY_SUCCESS.getCode());
					}else if(InstOrderStatus.IN_PROCESS == instOrder.getStatus()){
						notify.setEventCode(NotifyEventCode.PAY_AWAIT.getCode());
					}else if(InstOrderStatus.FAILURE == instOrder.getStatus()){
						notify.setEventCode(NotifyEventCode.PAY_FAIL.getCode());
					}
					
					//通知数据
					String notifyData = "";
					Map<String,String> dataMap = new HashMap<String,String>();
					dataMap.put("gmt_notify", DateUtil.format(new Date(), "yyyyMMddhhmmss"));
					//支付流水号 （业务系统订单号）
					dataMap.put("order_id", channelPayOrder.getPaymentSeqNo());
					dataMap.put("inst_order_no", instOrderNo);
					dataMap.put("fund_channel_code", instOrder.getFundChannelCode());
					dataMap.put("trade_amount", instOrder.getAmount().getAmount().toString());
					dataMap.put("trade_status", notify.getEventCode());
					notifyData = JSON.toJSONString(dataMap);
					logger.info("异步通知数据:"+notifyData);
					notify.setNotifyData(notifyData);
					notify.setRetryCount(notify.getRetryCount()+1);
					//2.通知业务系统
					//通知地址
					String url = channelPayOrder.getExtension().get("pageUrl");
					String bizReturn = notifyBiz(url, notifyData);
					if(!StringUtil.isBlank(bizReturn)){
						Map<String, String> map =new HashMap<String, String>();
						try {
							map = MapUtil.jsonToMap(bizReturn);
							if("0000".equals(map.get("ret_code"))){//业务系统收到通知后返回成功
								notify.setNotifyStatus(NotifyStatus.SUCCESSFUL.getCode());
								//更新ChannelPayOrder通知状态
								channelPayOrder.setPaymentNotifyStatus(NotifyStatus.SUCCESSFUL);
							}
						} catch (Exception e) {//不是json数据，不能转换map 状态设置处理中
							notify.setNotifyStatus(NotifyStatus.IN_PROCESS.getCode());
							channelPayOrder.setPaymentNotifyStatus(NotifyStatus.IN_PROCESS);
						}
					}else{
						notify.setNotifyStatus(NotifyStatus.IN_PROCESS.getCode());
						//更新ChannelPayOrder通知状态
						channelPayOrder.setPaymentNotifyStatus(NotifyStatus.IN_PROCESS);
					}
					payResultNotifyService.updatePayResultNotify(notify);
					PayResultNotifyLog notifyLog = new PayResultNotifyLog();
					notifyLog.setNotifyId(notify.getNotifyId());
					notifyLog.setNotifyReturnData(bizReturn);
					notifyLog.setServerIdentity(machineIp);
					payResultNotifyLogService.insertPayResultNotifyLog(notifyLog);
					//更新ChannelPayOrder通知状态
					channelPayOrderService.updatePaymentNotifyStatusById(channelPayOrder.getPaymentNotifyStatus().getCode(),channelPayOrder.getPaySeqNo());
				}
				
			}else{
				//1.通知数据落地
				PayResultNotify resultNotify = new PayResultNotify();
				String notifyId = UUID.randomUUID().toString().replace("-", "");
				resultNotify.setNotifyId(notifyId);
				//机构订单号（原始订单号）
				resultNotify.setInstOrderId(channelPayOrder.getOrgiPaymentSeqNo());
				logger.info("机构订单状态："+instOrder.getStatus());
				//通知事件编码(根据结果状态判断)
				if(InstOrderStatus.SUCCESSFUL == instOrder.getStatus()){
					resultNotify.setEventCode(NotifyEventCode.PAY_SUCCESS.getCode());
				}else if(InstOrderStatus.IN_PROCESS == instOrder.getStatus()){
					resultNotify.setEventCode(NotifyEventCode.PAY_AWAIT.getCode());
				}else if(InstOrderStatus.FAILURE == instOrder.getStatus()){
					resultNotify.setEventCode(NotifyEventCode.PAY_FAIL.getCode());
				}
				//通知地址
				String url = channelPayOrder.getExtension().get("pageUrl");
				resultNotify.setNotifyTarget(url);
				//通知数据
				String notifyData = "";
				Map<String,String> dataMap = new HashMap<String,String>();
				dataMap.put("gmt_notify", DateUtil.format(new Date(), "yyyyMMddhhmmss"));
				//支付流水号 （业务系统订单号）
				dataMap.put("order_id", channelPayOrder.getPaymentSeqNo());
				dataMap.put("inst_order_no", instOrderNo);
				dataMap.put("fund_channel_code", instOrder.getFundChannelCode());
				dataMap.put("trade_amount", instOrder.getAmount().getAmount().toString());
				dataMap.put("trade_status", resultNotify.getEventCode());
				notifyData = JSON.toJSONString(dataMap);
				logger.info("异步通知数据:"+notifyData);
				resultNotify.setNotifyData(notifyData);
				resultNotify.setInputCharset("UTF-8");
				resultNotify.setPartnerId(instOrder.getExtension().get("partnerId"));
				resultNotify.setServerIdentity(machineIp);
				resultNotify.setClientId(machineHostName);
				resultNotify.setRetryingFlag("N");
				resultNotify.setRetryCount(0);
				//通知状态
				//2.通知业务系统
				String bizReturn = notifyBiz(url, notifyData);
				if(!StringUtil.isBlank(bizReturn)){
					Map<String, String> map =new HashMap<String, String>();
					try {
						map = MapUtil.jsonToMap(bizReturn);
						if("0000".equals(map.get("ret_code"))){//业务系统收到通知后返回成功
							resultNotify.setNotifyStatus(NotifyStatus.SUCCESSFUL.getCode());
							//更新ChannelPayOrder通知状态
							channelPayOrder.setPaymentNotifyStatus(NotifyStatus.SUCCESSFUL);
						}
					} catch (Exception e) {//不是json数据，不能转换map 状态设置处理中
						resultNotify.setNotifyStatus(NotifyStatus.IN_PROCESS.getCode());
						channelPayOrder.setPaymentNotifyStatus(NotifyStatus.IN_PROCESS);
					}
				}else{
					resultNotify.setNotifyStatus(NotifyStatus.IN_PROCESS.getCode());
					//更新ChannelPayOrder通知状态
					channelPayOrder.setPaymentNotifyStatus(NotifyStatus.IN_PROCESS);
				}
				//3.通知结果落地
				payResultNotifyService.insertPayResultNotify(resultNotify);
				PayResultNotifyLog notifyLog = new PayResultNotifyLog();
				notifyLog.setNotifyId(notifyId);
				notifyLog.setNotifyReturnData(bizReturn);
				notifyLog.setServerIdentity(machineIp);
				payResultNotifyLogService.insertPayResultNotifyLog(notifyLog);
				//更新ChannelPayOrder通知状态
				channelPayOrderService.updatePaymentNotifyStatusById(channelPayOrder.getPaymentNotifyStatus().getCode(),channelPayOrder.getPaySeqNo());
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

}

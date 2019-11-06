package com.yiran.payorder.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.domain.Extension;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.validate.Validator;
import com.netfinworks.validate.exception.ValidationException;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.domain.TmFundChannelExt;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ChannelInfoExtKey;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.paychannel.utils.FundChannelSignUtil;
import com.yiran.payorder.converter.OrderConverter;
import com.yiran.payorder.converter.PayFundResultConverter;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.ChannelPayRequestConverter;
import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.domain.PayBankCardInfo;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.PayMonitorLog;
import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.MonitorItem;
import com.yiran.payorder.enums.PayFundResultCode;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.exception.AppRuntimeException;
import com.yiran.payorder.exception.DuplicateKeyException;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.InstOrderResultRequest;
import com.yiran.payorder.request.PayOrderRequest;
import com.yiran.payorder.response.InstOrderResultResponse;
import com.yiran.payorder.response.PayFundResult;
import com.yiran.payorder.service.IBankCardService;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IChannelSendService;
import com.yiran.payorder.service.IFundChannelLoader;
import com.yiran.payorder.service.IFundRequestService;
import com.yiran.payorder.service.IPayInstOrderResultService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IPayMonitorLogService;
import com.yiran.payorder.service.IPolicyService;
import com.yiran.payorder.utils.BizUtil;
import com.yiran.payorder.validator.ValidatorFactory;
@Service
public class FundRequestService implements IFundRequestService {
	private Logger             logger = LoggerFactory.getLogger(FundRequestService.class);
	/** 校验器工厂 */
	@Autowired
    private ValidatorFactory      validatorFactory;
	@Autowired
	private IBankCardService bankCardService;
	@Autowired
	private IChannelPayOrderService channelPayOrderService;
	@Autowired
	private IPayInstOrderService payInstOrderService;
	@Autowired
	private IPayInstOrderResultService payInstOrderResultService;
	@Autowired
	private IPayMonitorLogService payMonitorLogService;
	/** 资金渠道加载器 */
    @Autowired
    private IFundChannelLoader fundChannelLoader;
    
    @Autowired
    private IChannelSendService channelSendService;
	/** 提交机构服务 */
	@Autowired
    private IPolicyService         policyService;
	@Override
	public PayFundResult apply(PayOrderRequest request) {
		try {

	           if (logger.isInfoEnabled()) {
	               logger.info("[入/出款]请求:" + request);
	           }

	           PayFundResult result = applyInnerProcess(request);
	           
	           if (logger.isInfoEnabled()) {
	               logger.info("[入/出款]请求结果:" + result);
	           }
	           return result;
	       } catch (Exception e) {
	           logger.error("[入款/出款]处理异常(paymentSeqNo=" + request.getPaymentSeqNo() + "):", e);
	           return fail(null, InstOrderProcessStatus.FAILURE,
	               (null == request) ? null : request.getBizType(), e);
	       }
	}

	@Override
	public PayFundResult refund(PayOrderRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InstOrderResultResponse findInstOrderResult(InstOrderResultRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 /**
	    * 内部处理
	    * @param request
	    * @return
	    */
		private PayFundResult applyInnerProcess(PayOrderRequest request) {
			// 1. 请求验证
	       try {
	           Validator validator = validatorFactory.load(request.getBizType());
	           if (validator != null) {
	               validator.validate(request);
	           }
	       } catch (ValidationException e) {
	           return buildFundResult(PayFundResultCode.PARAMETER_INVALID, e.getMessage());
	       }
	       // 2. 转换成ChannelPayOrder
	       ChannelPayOrder channelPayOrder = ChannelPayRequestConverter.convert(request);
	       
	       String smsSender=getSmsSender(request.getExtension());
	       channelPayOrder.getExtension().put("realSmsSender",smsSender);
	       if ("merchant".equalsIgnoreCase(smsSender)){
	       	channelPayOrder.getExtension().put(ChannelInfoExtKey.SMS_SENDER.getCode(),"PLATFORM");
	       }else if ("platform".equalsIgnoreCase(smsSender)){
	       	channelPayOrder.getExtension().put(ChannelInfoExtKey.SMS_SENDER.getCode(),"PLATFORM,BANK");
	       }
	       //根据签约标记保存，已签约渠道到扩展参数中
	       if (BizType.FUNDIN.equals(request.getBizType())
	               &&(PayMode.QUICKPAY.equals(request.getPayMode()))
	               ){
	           logger.info("查询签约信息表数据，cardNo:"+channelPayOrder.getExtension().get("cardNo")+",memberId:"+channelPayOrder.getMemberId());
	           List<PaySignInfo> signInfos=null;
	           String bankCardId=channelPayOrder.getExtension().get(FundChannelSignUtil.getBankCardIdKey());
	           if (StringUtil.isNotEmpty(bankCardId)){
	               signInfos = bankCardService.fetchFinishSignInfo(bankCardId,null,null);
	           }else {
	        	   PayBankCardInfo bankCardInfo = getBankCardInfo(channelPayOrder);
	               if(bankCardService.isExistsBankCardInfo(bankCardInfo)){
	                   bankCardId=String.valueOf(bankCardInfo.getBankCardId());
	                   signInfos=bankCardService.fetchFinishSignInfo(String.valueOf(bankCardInfo.getBankCardId()),null,null);
	               }else {
	                   logger.error("[入款/出款]处理异常(paymentSeqNo=" + request.getPaymentSeqNo() + "),根据4要素查询银行卡信息表为空");
	                   return fail(null, InstOrderProcessStatus.FAILURE, channelPayOrder.getBizType(), null);
	               }
	           }
	           channelPayOrder.getExtension().put(FundChannelSignUtil.getBankCardIdKey(),bankCardId);
	           StringBuilder sb=new StringBuilder();
	           if (CollectionUtils.isNotEmpty(signInfos)){
	               for (PaySignInfo signInfo:signInfos){
	                   sb.append(signInfo.getFundChannelCode()).append(",");
	               }
	               //channelPayOrder.getExtension().put("signed","Y");
	               FundChannelSignUtil.setIncludeMatchSignedChannelsFlag(channelPayOrder.getExtension());
	           }else {
	               //channelPayOrder.getExtension().put("signed","N");
	           }
	           channelPayOrder.getExtension().put(FundChannelSignUtil.getSignedChannelsKey(),sb.toString());
	       }
	       
	       if (BizType.REFUND.equals(request.getBizType()) && request.getPayMode() == null) {
	    	   ChannelPayOrder origCmfOrder = channelPayOrderService.loadByPaymentSeqNo(
	    			   channelPayOrder.getOrgiPaymentSeqNo(), channelPayOrder.getOrgiSettlementId());
	    	   channelPayOrder.setPayMode(origCmfOrder.getPayMode());
	       }
	       //3. 保存channlePayOrder订单
	       try {
	           saveChannelPayOrder(channelPayOrder);
	       } catch (Exception e) {
	           logger.error("[入款/出款]处理异常(paymentSeqNo=" + request.getPaymentSeqNo() + "):", e);
	           return queryDuplicateOrder(channelPayOrder.getPaymentSeqNo(), channelPayOrder.getSettlementId(),
	        		   channelPayOrder.getBizType());
	       }
	       
	       // 4. 处理CmfOrder(入款/出款)
	        PayFundResult result = null;
	        PayInstOrderResult instResult = null;
	        try {
	            if (BizUtil.isDeterminedFundin(channelPayOrder.getPaymentCode())) {
	            	logger.info("PaymentCode="+channelPayOrder.getPaymentCode());
	                //确定性入款逻辑
	                instResult = processDeterminedFundin(channelPayOrder);
	            } else {
	                instResult = policyService.apply(channelPayOrder);
	                instResult.getExtension().put(ExtensionKey.PAYMENT_SEQ_NO.key,
	                		channelPayOrder.getPaymentSeqNo());
	            }

	            ChannelPayOrder dbCmfOrder = channelPayOrderService
	                .loadByPaySeqNo(channelPayOrder.getPaySeqNo());
	            PayInstOrder dbInstOrder = payInstOrderService.loadById(channelPayOrder.getInstOrderId());
	            result = PayFundResultConverter.convert(dbCmfOrder, dbInstOrder, instResult);
	            if ("merchant".equalsIgnoreCase(smsSender)){
	                result.getExtension().add(ChannelInfoExtKey.SMS_SENDER.getCode(),"MERCHANT");
	            }else if ("platform".equalsIgnoreCase(smsSender)){
	                if (dbInstOrder!=null){
	                    String sender=getRealSmsSend(dbInstOrder.getFundChannelApi());
	                    result.getExtension().add(ChannelInfoExtKey.SMS_SENDER.getCode(),sender);
	                }
	            }
	            if (dbInstOrder!=null&&BizType.FUNDIN.equals(request.getBizType())){
	                result.getExtension().add("fundsChannel",dbInstOrder.getFundChannel().getFundChannelCode());
	            }
	            //end
	        } catch (Exception e) {
	            logger.error("[入款/出款]处理异常(paymentSeqNo=" + request.getPaymentSeqNo() + "):", e);
	            result = fail(instResult, InstOrderProcessStatus.FAILURE, channelPayOrder.getBizType(), e);
	        }

	        if (logger.isInfoEnabled()) {
	            logger.info("[入/出款]处理结果(paymentSeqNo=" + request.getPaymentSeqNo() + "):" + result);
	        }

	        // 5. 若处理时间过长，就MQ方式通知PE
	        //notifyIfTimeout(channelPayOrder, instResult);
	       
	       
			return result;
		}
		
		
		private String getRealSmsSend(TmFundChannelApi fundChannelApi) {
			if (fundChannelApi!=null){
	            List<TmFundChannelExt> exts=fundChannelApi.getExts();
	            if (CollectionUtils.isNotEmpty(exts)){
	                for (TmFundChannelExt ext:exts){
	                    if(ChannelInfoExtKey.SMS_SENDER.getCode().equalsIgnoreCase(ext.getAttrKey())){
	                        return ext.getAttrValue();
	                    }
	                }
	            }
	        }
	        return "";
		}

		private PayInstOrderResult processDeterminedFundin(ChannelPayOrder channelPayOrder) {

			//1. 查找INST_ORDER_NO
			PayInstOrderResult result = null;
	        String instOrderNo = channelPayOrder.getExtension().get(ExtensionKey.INST_ORDER_NO.key);
	        if (!StringUtil.isEmpty(instOrderNo)) {
	            PayInstOrder dbInstOrder = payInstOrderService.loadByInstOrderNo(instOrderNo);
	            if (null != dbInstOrder) {
	                //更新cmfOrder.instOrderId
	            	channelPayOrder.setInstOrderId(dbInstOrder.getInstOrderId());
	            	channelPayOrderService.updateInstById(dbInstOrder.getInstOrderId(),
	                		channelPayOrder.getPaySeqNo());
	                result = payInstOrderResultService.loadRealResultByOrder(dbInstOrder
	                    .getInstOrderId());
	            }
	        }

	        if (null == result) {
	            result = new PayInstOrderResult();
	            result.setRealAmount(channelPayOrder.getAmount());
	            result.setOrderType(channelPayOrder.getBizType());
	            result.setStatus(InstOrderResultStatus.AWAITING);
	            result.setProcessStatus(InstOrderProcessStatus.UNKNOW_EXCEPTION);
	            result.setMemo("找不到原机构订单号[" + instOrderNo + "]");
	            return result;
	        }

	        //1. cmfOrder直接跃迁为成功
	        channelPayOrder.setStatus(PayOrderStatus.SUCCESSFUL);
	        channelPayOrderService.updateCmfOrderStatus(channelPayOrder, PayOrderStatus.AWAITING);

	        result.setStatus(InstOrderResultStatus.SUCCESSFUL);
	        result.setProcessStatus(InstOrderProcessStatus.SUCCESS);

	        return result;
		}

		private String saveChannelPayOrder(ChannelPayOrder channelPayOrder) {
			
            String paySeqNo = "";
            try {
                paySeqNo = channelPayOrderService.store(channelPayOrder);
            } catch (DuplicateKeyException e) {
            	payMonitorLogService.insertPayMonitorLog(new PayMonitorLog(channelPayOrder.getPaymentSeqNo(),
                        MonitorItem.DUPLIATE_REQUEST_EXCEPTION, "重复订单请求"
                                                                + channelPayOrder.getPaymentSeqNo(), e));
                    throw new AppRuntimeException("重复订单请求" + channelPayOrder.getPaymentSeqNo());
			}
            return paySeqNo;
		
		}

		/**
	     * 重复请求---查询订单.
	     *
	     * @param paymentSeqNo
	     * @return
	     */
	    private PayFundResult queryDuplicateOrder(String paymentSeqNo, String settlementId,
	                                              BizType bizType) {
	        //机构订单
	        try {
	        	ChannelPayOrder channelPayOrder = channelPayOrderService.loadByPaymentSeqNo(paymentSeqNo, settlementId);
	            if (null == channelPayOrder) {
	                logger.warn("无法找到重复订单:" + paymentSeqNo);
	                return fail(null, InstOrderProcessStatus.FAILURE, bizType, null);
	            }

	            //如果没有机构订单,依据ChannnelPayOrder订单返回
	            if (null == channelPayOrder.getInstOrderId()) {
	                return OrderConverter.convert(channelPayOrder);
	            } else {
	                PayInstOrder instOrder = payInstOrderService.loadById(channelPayOrder.getInstOrderId());
	                if (null == instOrder) {
	                    return fail(null, InstOrderProcessStatus.FAILURE, channelPayOrder.getBizType(), null);
	                }
	            }

	            //机构订单结果
	            PayInstOrder instOrder = payInstOrderService.loadById(channelPayOrder.getInstOrderId());
	            PayInstOrderResult instResult = payInstOrderResultService.loadRealResultByOrder(channelPayOrder.getInstOrderId());
	            if (null == instResult) {
	                return fail(null, InstOrderProcessStatus.FAILURE, bizType, null);
	            }
	            instResult.setProcessStatus(InstOrderProcessStatus.SUCCESS);
	            PayFundResult result = OrderConverter.convert(channelPayOrder, instOrder, instResult);
	            return result;
	        } catch (Exception e) {
	            logger.error("[订单查询]处理异常", e);
	            return fail(null, InstOrderProcessStatus.FAILURE, bizType, e);
	        }
	    }


	   private PayBankCardInfo getBankCardInfo(ChannelPayOrder channelPayOrder) {
		   String idNo=channelPayOrder.getExtension().get("idNo");
	       String accountName=channelPayOrder.getExtension().get("accountName");
	       String cardNo=channelPayOrder.getExtension().get("cardNo");
	       String mobileNo=channelPayOrder.getExtension().get("mobileNo");
	       PayBankCardInfo bankCardInfo=new PayBankCardInfo();
	       bankCardInfo.setCardHolder(accountName);
	       bankCardInfo.setCertNo(idNo);
	       bankCardInfo.setMobileNo(mobileNo);
	       bankCardInfo.setCardNo(cardNo);
	       return bankCardInfo;
	}

	/**
	    * 获取短信发送方
	    * @param extension
	    * @return
	    */
		private String getSmsSender(Extension extension) {
			String smsSender="";
	       if(extension!=null) {
	           smsSender = extension.getValue(ChannelInfoExtKey.SMS_SENDER.getCode());
	       }
	       return smsSender;
		}

		/**
	    * 创建一个请求处理结果.
	    * @param resultCode
	    * @param resultMessage
	    * @return
	    */
		private PayFundResult buildFundResult(PayFundResultCode resultCode, String resultMessage) {
			PayFundResult result = new PayFundResult();
	       result.setResultCode(resultCode);
	       result.setResultMessage(resultMessage);
	       boolean isSuccess = (PayFundResultCode.SUCCESS == resultCode || PayFundResultCode.REQUEST_SUCCESS == resultCode);
	       result.setSuccess(isSuccess);

	       return result;
		}
		
		/**
	     * 处理失败的结果.
	     * 
	     * @param failure
	     * @param bizType
	     * @param e
	     * @return
	     */
	    private PayFundResult fail(PayInstOrderResult instResult, InstOrderProcessStatus failure,
	                              BizType bizType, Throwable e) {
	        PayFundResult result = new PayFundResult();
	        result.setSuccess(false);
	        PayFundResultCode resultCode = (instResult != null && InstOrderResultStatus.FAILURE
	            .equals(instResult.getStatus())) ? PayFundResultCode.FAILED
	            : PayFundResultCode.IN_PROCESS;
	        result.setResultCode(resultCode);
	        if (e != null) {
	            result.setResultMessage(e.getMessage());
	        }
	        return result;
	    }

		@Override
		public ChannelResult download(ChannelRequest channelRequest) {
			try {

		           if (logger.isInfoEnabled()) {
		               logger.info("[对账文件下载]请求:" + channelRequest);
		           }

		           ChannelResult result = billDownProcess(channelRequest);
		           
		           if (logger.isInfoEnabled()) {
		               logger.info("[对账文件下载]请求结果:" + result);
		           }
		           return result;
		       } catch (Exception e) {
		           logger.error("[对账文件下载]处理异常(paymentSeqNo=" + channelRequest.getInstOrderNo()+ "):", e);
		           return fail(e);
		       }
		}

		private ChannelResult billDownProcess(ChannelRequest channelRequest) {
			ChannelResult channelResult = new ChannelResult();
			//1.选择路由
			TmFundChannel fundChannel = fundChannelLoader.loadFundChannel(channelRequest);
			//若没有可用通道，直接返回上层处理中
	        if (fundChannel == null || fundChannel.getApi() == null) {
	            logger.info("[]路由完成，没有可用资金渠道或接口，"+ "直接返回失败");
	            return fail(new Exception());
			}
	        String apiUrl = "";
	        List<TmFundChannelApi> apiList = fundChannel.getApiList();
	        for (TmFundChannelApi api : apiList) {
				if(FundChannelApiType.FILE_DOWN.getCode().equals(api.getApiType())){
					apiUrl = api.getApiUri();
					break;
				}
			}
	        channelRequest.setApiUrl(apiUrl);
			//2.发送给渠道，返回结果
			ChannelFundResult channelFundResult = channelSendService.download(channelRequest);
	        if (logger.isInfoEnabled()) {
	            logger.info("[对账单下载],发送渠道完成，结果：" + channelFundResult);
	        }
	        channelResult = bulidResult(channelFundResult);
			return channelResult;
		}

		private ChannelResult bulidResult(ChannelFundResult fr) {
			ChannelResult result = new ChannelResult();
			result.setResultCode(fr.getResultCode());
			result.setApiResultCode(fr.getApiResultCode());
			result.setApiResultMessage(fr.getApiResultMessage());
			result.setSuccess(fr.isSuccess());
			result.setExtension(fr.getExtension());
			return result;
		}

		private ChannelResult fail(Exception e) {
			ChannelResult result = new ChannelResult();
			result.setSuccess(false);
	        result.setResultCode("E0003");
	        if (e != null) {
	            result.setResultMessage(e.getMessage());
	        }else{
	        	result.setResultMessage("对账文件下载失败");
	        }
	        return result;
		}

		
	    

}

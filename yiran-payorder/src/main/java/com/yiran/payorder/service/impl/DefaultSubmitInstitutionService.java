package com.yiran.payorder.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApiParam;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ControlRequestType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.RequestType;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.paychannel.exception.RouteChannelException;
import com.yiran.paychannel.utils.FundChannelSignUtil;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.converter.InstOrderConverter;
import com.yiran.payorder.converter.InstOrderResultConverter;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.InstBaseOrder;
import com.yiran.payorder.domain.InstBaseResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.enums.ApiParamScene;
import com.yiran.payorder.enums.InstOrderCommunicateType;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.InstOrderType;
import com.yiran.payorder.enums.SignStatus;
import com.yiran.payorder.service.IBankCardService;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IChannelSendService;
import com.yiran.payorder.service.IFundChannelLoader;
import com.yiran.payorder.service.IPayInstOrderResultService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.ISubmitInstitutionService;
import com.yiran.payorder.service.InstOrderNoGenerator;
import com.yiran.payorder.service.InstitutionResultService;


/**
 * <p>提交机构服务默认实现</p>
 */
@Service
public class DefaultSubmitInstitutionService implements ISubmitInstitutionService, BasicConstant {
    private static final Logger                     logger            = LoggerFactory.getLogger(DefaultSubmitInstitutionService.class);

    /** 类型关系 */
    private static final Map<String, InstOrderType> typeMap           = new HashMap<String, InstOrderType>();

    private static final String                     SOURCE_ORDER_INST = "inst";

    @Autowired
	private IChannelPayOrderService channelPayOrderService;
    
    @Autowired
	private IPayInstOrderService payInstOrderService;
    
    @Autowired
	private IPayInstOrderResultService payInstOrderResultService;
    
    /** 资金渠道加载器 */
    @Autowired
    private IFundChannelLoader fundChannelLoader;
    @Autowired
    private IChannelSendService channelSendService;
    /**
     * 订单号生成器
     */
    @Autowired
    private InstOrderNoGenerator instOrderNoGenerator;
    @Autowired
    private IBankCardService bankCardService;
    @Autowired
    private InstitutionResultService institutionResultService;
    // 初始化
    static {
    	typeMap.put(RequestType.FUND_IN.name(), InstOrderType.CONTROL);
        typeMap.put(RequestType.PRE_AUTH_DONE.name(), InstOrderType.CONTROL);
        typeMap.put(RequestType.REFUND.name(), InstOrderType.FUND);
        typeMap.put(ControlRequestType.PRE_AUTH_REVERSAL.name(), InstOrderType.CONTROL);
        typeMap.put(ControlRequestType.PRE_AUTH_REVERSAL_UNDO.name(), InstOrderType.CONTROL);
        typeMap.put(ControlRequestType.PRE_AUTH_UNDO.name(), InstOrderType.CONTROL);
        typeMap.put(ControlRequestType.REVERSAL.name(), InstOrderType.FUND);
        typeMap.put(ControlRequestType.REVERSAL_UNDO.name(), InstOrderType.CONTROL);
        typeMap.put(ControlRequestType.UNDO.name(), InstOrderType.FUND);
        typeMap.put(ControlRequestType.DEBIT_ADVANCE.name(), InstOrderType.FUND);
        typeMap.put(ControlRequestType.AUTH_ADVANCE.name(), InstOrderType.CONTROL);
        typeMap.put(ControlRequestType.SEND_MESSAGE.name(), InstOrderType.CONTROL);
    }

    @Override
    public PayInstOrderResult  submit(ChannelPayOrder channelPayOrder, boolean isSync) throws RouteChannelException {
         logger.info("提交PayChannelOrder订单请求PayChannelOrder: " + channelPayOrder);
        // *******************
        // 1、获取资金渠道
        // *******************
        String preRequestNo = channelPayOrder.getOrgiPaymentSeqNo();
        if (StringUtil.isBlank(preRequestNo)) {
        	preRequestNo = channelPayOrder.getExtension().get(ExtensionKey.PRE_REQUEST_NO.key);
		}
        InstBaseOrder preOrder = loadPreOrder(channelPayOrder.getRequestType().name(),
        		preRequestNo, channelPayOrder.getOrgiSettlementId(), null);
        TmFundChannel fundChannel = null;
        String oriSigned=channelPayOrder.getExtension().get("signed");
        fundChannel=fundChannelLoader.load(channelPayOrder, preOrder);

        //若没有可用通道，直接返回上层处理中
        if (fundChannel == null || fundChannel.getApi() == null) {
            logger.info("PaySeqNo[" + channelPayOrder.getPaySeqNo() + "]路由完成，没有可用资金渠道或接口，"
                    + "直接返回处理中");
            return awaiting(channelPayOrder);
		}
        if (logger.isInfoEnabled()) {
            logger.info("PaySeqNo[" + channelPayOrder.getPaySeqNo() + "]路由完成，资金渠道编码["
                        + fundChannel.getFundChannelCode() + "]");
        }
        if ("y".equalsIgnoreCase(oriSigned)){
            if(!FundChannelSignUtil.fundChannelIsSigned(fundChannel,channelPayOrder.getExtension())){
                logger.info("PaySeqNo[" + channelPayOrder.getPaySeqNo() + "]，资金渠道编码["
                        + fundChannel.getFundChannelCode() + "],未签约 ");
                channelPayOrder.getExtension().put("signed","N");
            }else {
                logger.info("PaySeqNo[" + channelPayOrder.getPaySeqNo() + "]，资金渠道编码["
                        + fundChannel.getFundChannelCode() + "],已签约 ");
                channelPayOrder.getExtension().put("signed","Y");
            }
        }
        if ("y".equalsIgnoreCase(channelPayOrder.getExtension().get("signed"))){
            handlerSignNo(channelPayOrder, fundChannel);
        }
        //end

        // *******************
        // 2、组装机构订单
        // *******************
        InstBaseResult baseResult = null;
        if (preOrder != null && preOrder instanceof PayInstOrder) {
            baseResult = payInstOrderResultService.loadRealResultByOrder(((PayInstOrder) preOrder)
                .getInstOrderId());
        }
        PayInstOrder instOrder = InstOrderConverter.convert(channelPayOrder, preOrder, baseResult,
            fundChannel);

        // *******************
        // 3、订单完善并仓储
        // *******************
        List<PayInstOrder> instOrderList = orderComplement(channelPayOrder, instOrder, preOrder, fundChannel);
        payInstOrderService.store(instOrderList);

        // *******************
        // 4、更新CMF订单的机构订单ID
        // *******************
        if (instOrderList.size() == 1) {
            channelPayOrder.setInstOrderId(instOrderList.get(0).getInstOrderId());
            channelPayOrderService.updateInstById(channelPayOrder.getInstOrderId(), channelPayOrder.getPaySeqNo());
        }

        logger.info("PaySeqNo[" + channelPayOrder.getPaySeqNo() + "]保存完成 ");

        // 如果非实时发送或者经过拆分，则直接返回结果
        if (instOrder.getCommunicateType() == InstOrderCommunicateType.BATCH
            || instOrderList.size() > 1) {
            return InstOrderResultConverter.buildAwaiting(instOrder);
        }

        // *******************
        // 5、发送渠道
        // *******************
        ChannelFundResult channelFundResult = channelSendService.send(instOrder);
        if (logger.isInfoEnabled()) {
            logger.info("PaySeqNo[" + channelPayOrder.getPaySeqNo() + "],instOrderNo["
                        + instOrder.getInstOrderNo() + "]发送渠道完成，结果：" + channelFundResult);
        }

        // *******************
        // 6、结果推进
        // *******************
        return institutionResultService.process(instOrder, channelFundResult, isSync);
    }


	/**
     * 订单补全/拆分
     * @param instOrder
     * @return
     */
    private List<PayInstOrder> orderComplement(ChannelPayOrder channelPayOrder, PayInstOrder instOrder,
			InstBaseOrder preOrder, TmFundChannel fundChannel) {
    	//api使用参数 
        if (preOrder != null && !CollectionUtils.isEmpty(preOrder.getExtension())) {
            for (TmFundChannelApiParam apiParam : fundChannel.getApi().getParamList()) {
                if (ApiParamScene.REQUEST_CHANNEL.equals(apiParam.getScene())
                    && YesNo.YES.equals(apiParam.getIsOrgin())
                    && StringUtil.isNotEmpty(preOrder.getExtension().get(apiParam.getParamName()))) {
                    instOrder.getExtension().put(apiParam.getParamName(),
                        preOrder.getExtension().get(apiParam.getParamName()));
                }
            }
        }

        // 2、拆分（出款且金额大于限额）
        List<PayInstOrder> instOrderList = new ArrayList<PayInstOrder>();
        if (instOrder.getBizType() == BizType.FUNDOUT) {
            // 获取渠道API最大限额
            Money maxAmountLimit = (instOrder.getFundChannelApi().getAmountLimit() != null) ? instOrder
                .getFundChannelApi().getAmountLimit().getMaxAmount()
                : null;

            // 根据限额拆分订单
            if (maxAmountLimit != null && maxAmountLimit.greaterThan(ZERO_MONEY)
                && instOrder.getAmount().greaterThan(maxAmountLimit)) {
                Money remainingAmount = new Money(instOrder.getAmount().toString());
                while (remainingAmount.greaterThan(maxAmountLimit)) {
                    instOrderList.add(buildSplitOrder(channelPayOrder, instOrder, maxAmountLimit));

                    remainingAmount.subtractFrom(maxAmountLimit);
                }
                if (remainingAmount.greaterThan(ZERO_MONEY)) {
                    instOrderList.add(buildSplitOrder(channelPayOrder, instOrder, remainingAmount));
                }

                if (logger.isInfoEnabled()) {
                    logger.info("paySeqNo[" + instOrder.getPaySeqNo() + "]被拆分为["
                                + instOrderList.size() + "]笔订单");
                }
            }
        }

        // 未被拆分直接使用原始订单
        if (CollectionUtils.isEmpty(instOrderList)) {
            instOrderList.add(instOrder);
        }

        // 3、设置提交机构订单号
        for (PayInstOrder tempOrder : instOrderList) {
            tempOrder.setInstOrderNo(instOrderNoGenerator.genInstOrderNo(instOrder
                .getFundChannelApi().getApiNoMode()));
        }

        return instOrderList;
    
	}

	private void handlerSignNo(ChannelPayOrder channelPayOrder, TmFundChannel fundChannel) {
        if((PayMode.QUICKPAY.equals(fundChannel.getPayMode())||PayMode.TRUSTCOLLECT.equals(fundChannel.getPayMode()))&&
                "y".equalsIgnoreCase(channelPayOrder.getExtension().get("signed"))){
            if (StringUtil.isNotEmpty(channelPayOrder.getExtension().get(FundChannelSignUtil.getBankCardIdKey()))){
                List<PaySignInfo> signInfos=bankCardService.fetchSignInfoByBankCardIdAndChannel(
                       channelPayOrder.getExtension().get(FundChannelSignUtil.getBankCardIdKey()),
                        fundChannel.getFundChannelCode());
                for (PaySignInfo signInfo:signInfos){
                    if(SignStatus.SUCCESS.equals(signInfo.getStatus())){
                        logger.info("签约号:"+ ExtensionKey.SIGN_NO.key+":"+signInfo.getChannelSignNo());
                        channelPayOrder.getExtension().put(ExtensionKey.SIGN_NO.key,signInfo.getChannelSignNo());
                        channelPayOrder.setMemberId(signInfo.getMemberId());
                        break;
                    }
                }
            }
        }
    }

    


    /**
     * 组件拆分订单
     * @param preInstOrder
     * @param amount
     * @return
     */
    private PayInstOrder buildSplitOrder(ChannelPayOrder channelPayOrder, PayInstOrder preInstOrder, Money amount) {
        try {
        	PayInstOrder newOrder = preInstOrder.clone();
            newOrder.setAmount(amount);
            newOrder.setIsSplit(YesNo.YES);
            newOrder.setPaySeqNo(channelPayOrder.getPaySeqNo());

            return newOrder;
        } catch (CloneNotSupportedException e) {
            throw new IllegalArgumentException("[" + preInstOrder.getPaySeqNo() + "]克隆对象异常", e);
        }
    }

    /**
     * 获取原订单信息
     * @param requestType
     * @param requestNo
     * @return
     */
    private InstBaseOrder loadPreOrder(String requestType, String requestNo, String settlementId,
                                       String sourceOrder) {
    	if (StringUtils.isBlank(requestNo)) {
			return null;
		}
        if (!typeMap.containsKey(requestType)) {
            return null;
        }
        InstOrderType orderType = typeMap.get(requestType);

        // 资金类
        if (orderType == InstOrderType.FUND) {
            if (SOURCE_ORDER_INST.equals(sourceOrder)) {
                return payInstOrderService.loadByInstOrderNo(requestNo);
            } else {
                ChannelPayOrder origchannelPayOrder = channelPayOrderService.loadByPaymentSeqNo(requestNo,
                    settlementId);
                if (origchannelPayOrder != null) {
                	PayInstOrder instOrder = payInstOrderService.loadById(origchannelPayOrder.getInstOrderId());
                	return instOrder;
                }
            }
        } else {
            if (SOURCE_ORDER_INST.equals(sourceOrder)) {
                return payInstOrderService.loadByInstOrderNo(requestNo);
            }
        }
		return null;
    }

    
    /**
     * 返回处理中结果
     * @return
     */
    private PayInstOrderResult awaiting(ChannelPayOrder channelPayOrder ) {
        PayInstOrderResult result = new PayInstOrderResult();
        result.setOrderType(channelPayOrder.getBizType());
        result.setRealAmount(channelPayOrder.getAmount());
        result.setGmtCreate(new Date());
        result.setInstOrderNo(channelPayOrder.getPaymentSeqNo());
        //处理中
        result.setProcessStatus(InstOrderProcessStatus.AWAITING);
        result.setStatus(InstOrderResultStatus.AWAITING);

        result.setMemo("受理中，无通道可用，请等待.");
        return result;
    }
    
    
}

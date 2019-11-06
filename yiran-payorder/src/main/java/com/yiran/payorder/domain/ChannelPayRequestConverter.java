package com.yiran.payorder.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.netfinworks.common.domain.Kvp;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.ControlRequestType;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.RequestType;
import com.yiran.paychannel.utils.CommonConverter;
import com.yiran.payorder.enums.PayOrderConfirmStatus;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.request.PayOrderRequest;

/**
 * 对象转换类
 * @author pandaa
 *
 */
public class ChannelPayRequestConverter {
	/** 控制请求类型与接口类型MAP */
    private static Map<ControlRequestType, FundChannelApiType> apiTypeMap = new HashMap<ControlRequestType, FundChannelApiType>();

    //初始化
    static {
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REQUEST, FundChannelApiType.PRE_AUTH);
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REVERSAL, FundChannelApiType.PRE_AUTH_REVERSAL);
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REVERSAL_UNDO,
            FundChannelApiType.PRE_AUTH_UNDO_REVERSAL);
        apiTypeMap.put(ControlRequestType.PRE_AUTH_UNDO, FundChannelApiType.PRE_AUTH_UNDO);
        apiTypeMap.put(ControlRequestType.REVERSAL, FundChannelApiType.PRE_AUTH_DONE_REVERSAL);
        apiTypeMap.put(ControlRequestType.REVERSAL_UNDO,
            FundChannelApiType.PRE_AUTH_DONE_UNDO_REVERSAL);
        apiTypeMap.put(ControlRequestType.UNDO, FundChannelApiType.PRE_AUTH_DONE_UNDO);
        apiTypeMap.put(ControlRequestType.AUTHENTICATE, FundChannelApiType.AUTHENTICATE);
        apiTypeMap.put(ControlRequestType.AUTH_ADVANCE, FundChannelApiType.AUTH_ADVANCE);
        apiTypeMap.put(ControlRequestType.TERMINATE, FundChannelApiType.TERMINATE);
        apiTypeMap.put(ControlRequestType.DEBIT_ADVANCE, FundChannelApiType.DEBIT_ADVANCE);
        apiTypeMap.put(ControlRequestType.SEND_MESSAGE, FundChannelApiType.SEND_MESSAGE);
        apiTypeMap.put(ControlRequestType.UPDATE_REPAYMENT_PLAN, FundChannelApiType.UPDATE_REPAYMENT_PLAN);
    }
    
    /**
     * 请求转换为CMF订单
     * @param request
     * @return
     */
    public static ChannelPayOrder convert(PayOrderRequest request) {
        if (request == null) {
            return null;
        }

        ChannelPayOrder order = new ChannelPayOrder();
        order.setStatus(PayOrderStatus.AWAITING);

        //1. 转换基本属性.
        convertBaseFields(request, order);
        copyExtension(request, order);

        //2. 转换不同业务类型的基本属性.
        switch (order.getBizType()) {
            case FUNDIN: {
                order.setRequestType(RequestType.FUND_IN);
                convertFundinFields(request, order);
                break;
            }
            case REFUND: {
                order.setRequestType(RequestType.REFUND);
                convertReFundFields(request, order);
                break;
            }
            case FUNDOUT: {
                order.setRequestType(RequestType.FUND_OUT);
                convertFundoutFields(request, order);
                break;
            }
        }
        return order;
    }

    private static void convertFundoutFields(PayOrderRequest request, ChannelPayOrder order) {
		
    	if (!StringUtils.isEmpty(request.getProductCode())) {
            order.getExtension().put(ExtensionKey.PRODUCT_CODE.key, request.getProductCode());
        }
       
        if (!StringUtils.isEmpty(request.getMemberId())) {
            order.getExtension().put(ExtensionKey.MEMBER_ID.key, request.getMemberId());
        }
		
	}

	private static void convertReFundFields(PayOrderRequest request, ChannelPayOrder order) {
		
    	//充退不须提交审核
        order.setConfirmStatus(PayOrderConfirmStatus.NOT_NEED);
        //充退时，memberId需要使用入款订单的memberId，在instOrder扩展属性里会设置上.
        order.setOrgiPaymentSeqNo(order.getExtension().get(ExtensionKey.ORGI_FUNDIN_ORDER_NO.key));
        if (StringUtil.isNotEmpty(order.getExtension().get(ExtensionKey.ORGI_SETTLEMENT_ID.key))
            && !"null".equals(order.getExtension().get(ExtensionKey.ORGI_SETTLEMENT_ID.key))) {
            order.setOrgiSettlementId(order.getExtension().get(ExtensionKey.ORGI_SETTLEMENT_ID.key));
        }
        order.setMemberId(null);
		
	}

	private static void convertFundinFields(PayOrderRequest request, ChannelPayOrder order) {
    	
    	 //充退不须提交审核
        order.setConfirmStatus(PayOrderConfirmStatus.NOT_NEED);

        //手机卡入款，需要用到productCode
        if (!StringUtils.isEmpty(request.getProductCode())) {
            order.getExtension().put(ExtensionKey.PRODUCT_CODE.key, request.getProductCode());
        }
		
	}

	/**
     * 拷贝所有request的扩展信息给ChannelPayOrder.
     *
     * @param request
     * @param order
     */
    private static void copyExtension(PayOrderRequest request, ChannelPayOrder order) {
    	if (request.getExtension() != null && request.getExtension().getEntryList() != null) {
            for (Kvp kvp : request.getExtension().getEntryList()) {
            	order.getExtension().put(CommonConverter.convertKey(kvp.getKey()), kvp.getValue());
            }
        }
	}

	/**
     * 复制基本字段信息.
     *
     * @param request
     * @param order
     */
	private static void convertBaseFields(PayOrderRequest request, ChannelPayOrder order) {
		order.setBizType(request.getBizType());
        order.setPayMode(request.getPayMode());

        order.setAmount(new Money(request.getAmount()));
        order.setCurrency(CurrencyType.getByCode(request.getCurrencyCode()));
        order.setBizDate(request.getBizTime());

        //渠道编号：存入到扩展属性中；
        order.setFundChannelCode(request.getFundsChannel());
        order.setGmtCreate(new Date()); //
        order.setMemberId(request.getMemberId());
        order.setOperator(request.getOperator());
        order.setPaymentCode(request.getPaymentCode());
        order.setPaymentSeqNo(request.getPaymentSeqNo());
        order.setSettlementId(request.getSettlementId());
        order.setProductCode(request.getProductCode());
        order.setRequestBatchNo(request.getRequestBatchNo());
        order.setConfirmStatus(PayOrderConfirmStatus.NOT_NEED);
        order.setInstCode(request.getInstCode());
        //原始地订单号-支付流水号
        order.setOrgiPaymentSeqNo(request.getPaymentSeqNo());
	}

}

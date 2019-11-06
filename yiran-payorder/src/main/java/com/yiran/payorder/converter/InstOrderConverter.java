package com.yiran.payorder.converter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map.Entry;

import org.springframework.util.CollectionUtils;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.paychannel.utils.CommonConverter;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.InstBaseOrder;
import com.yiran.payorder.domain.InstBaseResult;
import com.yiran.payorder.domain.InstFundinOrder;
import com.yiran.payorder.domain.InstFundoutOrder;
import com.yiran.payorder.domain.InstOrderInfo;
import com.yiran.payorder.domain.InstRefundOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domaindo.PayInstOrderDO;
import com.yiran.payorder.enums.CommunicateStatus;
import com.yiran.payorder.enums.InstOrderArchiveStatus;
import com.yiran.payorder.enums.InstOrderCommunicateType;
import com.yiran.payorder.enums.InstOrderExtensionMapping;
import com.yiran.payorder.enums.InstOrderReversalStatus;
import com.yiran.payorder.enums.OrderFlag;
import com.yiran.payorder.enums.OrderRiskStatus;
import com.yiran.payorder.utils.PropertyValueUtil;

public class InstOrderConverter {

	public static PayInstOrder convert(PayInstOrderDO orderDO) {
		PayInstOrder instOrder = new PayInstOrder();
		instOrder.setInstOrderId(orderDO.getInstOrderId());
        instOrder.setInstCode(orderDO.getInstCode());
        instOrder.setInstOrderNo(orderDO.getInstOrderNo());
        instOrder.setBizType(BizType.getByCode(orderDO.getOrderType()));
        instOrder.setPayMode(PayMode.getByCode(orderDO.getPayMode()));
        instOrder.setFundChannelCode(orderDO.getFundChannelCode());
        instOrder.setAmount(new Money(new BigDecimal(orderDO.getAmount())));
        instOrder.setCommunicateType(InstOrderCommunicateType.getByCode(orderDO
            .getCommunicateType()));
        instOrder.setCommunicateStatus(CommunicateStatus.getByCode(orderDO
            .getCommunicateStatus()));
        instOrder.setArchiveBatchId(orderDO.getArchiveBatchId());
        instOrder.setGmtBookingSubmit(orderDO.getGmtBookingSubmit());
        instOrder.setGmtSubmit(orderDO.getGmtSubmit());
        instOrder.setGmtCreate(orderDO.getGmtCreate());
        instOrder.setGmtModified(orderDO.getGmtModified());
        instOrder.setMemo(orderDO.getMemo());
        instOrder.setProductCode(orderDO.getProductCode());
        instOrder.setPaymentCode(orderDO.getPaymentCode());
        instOrder.setStatus(InstOrderStatus.getByCode(orderDO.getStatus()));
        instOrder.setCurrency(CurrencyType.getByCode(orderDO.getCurrency()));
        instOrder.setRetryTimes(orderDO.getRetryTimes());
        instOrder.setGmtNextRetry(orderDO.getGmtNextRetry());
        instOrder.setArchiveTemplateId(orderDO.getArchiveTemplateId());
        instOrder.setArchiveBatchId(orderDO.getArchiveBatchId());
        instOrder.setRiskStatus(OrderRiskStatus.getByCode(orderDO.getRiskStatus()));
        instOrder.setRouteVersion(orderDO.getRouteVersion());
        instOrder.setReversalStatus(InstOrderReversalStatus.getByCode(orderDO.getReversalStatus()));
        instOrder.setIsSplit(YesNo.getByCode(orderDO.getIsSplit()));
        instOrder.setPaySeqNo(orderDO.getPaySeqNo());
        instOrder.setExpectTime(orderDO.getExpectTime());
        if (StringUtil.isNotEmpty(orderDO.getExtension())) {
            instOrder.setExtension(CommonConverter.convertFromDb(orderDO.getExtension()));
        }
        return instOrder;
	}

	
	public static PayInstOrder convert(ChannelPayOrder channelPayOrder, InstBaseOrder preOrder,
			InstBaseResult baseResult, TmFundChannel fundChannel) {

        PayInstOrder instOrder = null;

        // 组装个性化信息
        switch (channelPayOrder.getBizType()) {
            case FUNDIN:
                instOrder = new InstFundinOrder();
                for (InstOrderExtensionMapping mapping : InstOrderExtensionMapping
                    .getMappingSet(InstFundinOrder.class)) {
                    PropertyValueUtil.setValue(instOrder, mapping,
                    		channelPayOrder.getExtension().get(mapping.getExtensionKey()));
                }

                break;
            case FUNDOUT:
                InstFundoutOrder instFundoutOrder = new InstFundoutOrder();
                instFundoutOrder.setPtId(channelPayOrder.getMemberId());

                for (InstOrderExtensionMapping mapping : InstOrderExtensionMapping
                    .getMappingSet(InstFundoutOrder.class)) {
                    PropertyValueUtil.setValue(instFundoutOrder, mapping, channelPayOrder.getExtension()
                        .get(mapping.getExtensionKey()));
                }

                instOrder = instFundoutOrder;

                break;
            case REFUND:
                InstRefundOrder instRefundOrder = new InstRefundOrder();
                instRefundOrder.setFundinOrderNo(preOrder.getInstOrderNo());
                instRefundOrder.setFundinRealAmount(preOrder.getAmount());
                instRefundOrder.setFundinDate(DateUtil.getLongDateString(preOrder.getGmtCreate()));
                if (baseResult != null) {
                    instRefundOrder
                        .setFundinInstSeqNo(((PayInstOrderResult) baseResult).getInstSeqNo());
                    instRefundOrder.getExtension().put(ExtensionKey.ORGI_INST_SEQ_NO.key,
                        instRefundOrder.getFundinInstSeqNo());//保存使用
                }

                instOrder = instRefundOrder;

                break;
            default:
                throw new RuntimeException("此业务类型不支持");
        }

        // 组装主信息
        buildCommon(instOrder, channelPayOrder, fundChannel);

        return instOrder;
    
	}


	private static void buildCommon(PayInstOrder instOrder, ChannelPayOrder channelPayOrder,
			TmFundChannel fundChannel) {

        instOrder.setFundChannel(fundChannel);
        instOrder.setFundChannelCode(fundChannel.getFundChannelCode());
        instOrder.setFundChannelApi(fundChannel.getApi());
        instOrder
            .setCommunicateType(FundChannelApiType.isBatch(FundChannelApiType.getByCode(fundChannel.getApi().getApiType())) ? InstOrderCommunicateType.BATCH
                : InstOrderCommunicateType.SINGLE);

        instOrder.setPayMode(channelPayOrder.getPayMode());
        instOrder.setInstCode(channelPayOrder.getInstCode());
        instOrder.setCommunicateStatus(CommunicateStatus.AWAITING);
        instOrder.setArchiveStatus(InstOrderArchiveStatus.AWAITING);
        instOrder.setStatus(InstOrderStatus.IN_PROCESS);
        instOrder.setBizType(channelPayOrder.getBizType());
        instOrder.setProductCode(channelPayOrder.getProductCode());
        instOrder.setPaymentCode(channelPayOrder.getPaymentCode());
        instOrder.setGmtBookingSubmit(new Date());
        instOrder.setAmount(channelPayOrder.getAmount());
        instOrder.setCurrency(channelPayOrder.getCurrency());
        instOrder.setFlag(OrderFlag.DEFAULT);

        // 期望到帐时间
        String expectTimeStr = channelPayOrder.getExtension().get(ExtensionKey.EXPECT_TIME.key);
        Date expectTime = StringUtil.isBlank(expectTimeStr) ? new Date() : DateUtil
            .parseDateLongFormat(expectTimeStr);
        instOrder.setExpectTime(expectTime);

        // 拷贝扩展信息
        if (!CollectionUtils.isEmpty(channelPayOrder.getExtension())) {
            for (Entry<String, String> entry : channelPayOrder.getExtension().entrySet()) {
                // 值不为空且不作为属性的扩展信息复制
                if (StringUtil.isNotBlank(entry.getValue())
                    && !InstOrderExtensionMapping.getKeySet(instOrder.getClass()).contains(entry.getKey())) {
                    instOrder.getExtension().put(entry.getKey(), entry.getValue());
                }
            }
        }
        instOrder.getExtension().put(ExtensionKey.MEMBER_ID.key,channelPayOrder.getMemberId());
        //end
    
		
	}


	public static PayInstOrderDO convert(PayInstOrder instOrder) {
		PayInstOrderDO instOrderDO = new PayInstOrderDO();
        instOrderDO.setInstOrderId(instOrder.getInstOrderId());
        instOrderDO.setInstCode(instOrder.getInstCode());
        instOrderDO.setInstOrderNo(instOrder.getInstOrderNo());
        instOrderDO.setOrderType(instOrder.getBizType().getCode());
        instOrderDO.setPayMode(instOrder.getPayMode().getCode());
        instOrderDO.setFundChannelCode(instOrder.getFundChannelCode());
        instOrderDO.setFundChannelApi(instOrder.getFundChannelApi().getApiCode());
        instOrderDO.setAmount(instOrder.getAmount().getAmount().doubleValue());
        instOrderDO.setCommunicateType(instOrder.getCommunicateType().getCode());
        instOrderDO.setCommunicateStatus(instOrder.getCommunicateStatus().getCode());
        instOrderDO.setArchiveBatchId(instOrder.getArchiveBatchId());
        instOrderDO.setGmtBookingSubmit(instOrder.getGmtBookingSubmit());
        instOrderDO.setGmtSubmit(instOrder.getGmtSubmit());
        instOrderDO.setGmtCreate(instOrder.getGmtCreate());
        instOrderDO.setGmtModified(instOrder.getGmtModified());
        instOrderDO.setMemo(instOrder.getMemo());
        instOrderDO.setProductCode(instOrder.getProductCode());
        instOrderDO.setPaymentCode(instOrder.getPaymentCode());
        instOrderDO.setStatus(instOrder.getStatus().getCode());
        instOrderDO.setCurrency(instOrder.getCurrency().getCode());
        instOrderDO.setRetryTimes(instOrder.getRetryTimes());
        instOrderDO.setGmtNextRetry(instOrder.getGmtNextRetry());
        instOrderDO.setArchiveTemplateId(instOrder.getArchiveTemplateId());
        instOrderDO.setArchiveBatchId(instOrder.getArchiveBatchId());
        instOrderDO.setRouteVersion(instOrder.getRouteVersion());
        instOrderDO.setPaySeqNo(instOrder.getPaySeqNo());
        if (instOrder.getRiskStatus() != null) {
            instOrderDO.setRiskStatus(instOrder.getRiskStatus().getCode());
        }
        if (instOrder.getReversalStatus() != null) {
            instOrderDO.setReversalStatus(instOrder.getReversalStatus().getCode());
        }
        if (instOrder.getIsSplit() != null) {
            instOrderDO.setIsSplit(instOrder.getIsSplit().getCode());
        }
        if (instOrder.getFlag() != null) {
            instOrderDO.setFlag(instOrder.getFlag().getCode());
        }
        instOrderDO.setExpectTime(instOrder.getExpectTime());
        if (instOrder.getExtension() != null) {
            instOrderDO.setExtension(CommonConverter.convertToDb(instOrder.getExtension()));
        }
        return instOrderDO;
	}


	public static InstOrderInfo convertToInstOrderInfo(PayInstOrder instOrder) {
		InstOrderInfo instOrderInfo = new InstOrderInfo();
        instOrderInfo.setInstOrderId(instOrder.getInstOrderId());
        instOrderInfo.setInstCode(instOrder.getInstCode());
        instOrderInfo.setInstOrderNo(instOrder.getInstOrderNo());
        instOrderInfo.setBizType(instOrder.getBizType());
        instOrderInfo.setPayMode(instOrder.getPayMode());
        instOrderInfo.setFundChannelCode(instOrder.getFundChannelCode());
        instOrderInfo.setAmount(instOrder.getAmount());
        instOrderInfo.setGmtCreate(instOrder.getGmtCreate());
        instOrderInfo.setGmtModified(instOrder.getGmtModified());
        instOrderInfo.setProductCode(instOrder.getProductCode());
        instOrderInfo.setPaymentCode(instOrder.getPaymentCode());
        instOrderInfo.setStatus(convert(instOrder.getStatus()));
        if (instOrder.getExtension() != null) {
            instOrderInfo.setExtension(MapUtil.mapToJson(instOrder.getExtension()));
        }
        return instOrderInfo;
	}
	private static InstOrderStatus convert(InstOrderStatus instOrderStatus) {
        switch (instOrderStatus) {
            case SUCCESSFUL:
                return InstOrderStatus.SUCCESSFUL;
            case FAILURE:
                return InstOrderStatus.FAILURE;
            default:
                return InstOrderStatus.IN_PROCESS;
        }
    }

}

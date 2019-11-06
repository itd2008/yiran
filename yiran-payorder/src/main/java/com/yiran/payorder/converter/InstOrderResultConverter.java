package com.yiran.payorder.converter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.CardType;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.utils.CommonConverter;
import com.yiran.payorder.domain.InstFundinOrder;
import com.yiran.payorder.domain.InstFundoutOrder;
import com.yiran.payorder.domain.InstRefundOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domaindo.PayInstOrderResultDO;
import com.yiran.payorder.enums.BatchType;
import com.yiran.payorder.enums.InstOrderCompareStatus;
import com.yiran.payorder.enums.InstOrderGlideStatus;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.InstResultOperateStatus;
import com.yiran.payorder.enums.NotifyBankOrderStatus;
import com.yiran.payorder.enums.RiskFlag;

public class InstOrderResultConverter {

	public static List<PayInstOrderResult> convert(List<PayInstOrderResultDO> results) {
		PayInstOrderResult order = null;
        List<PayInstOrderResult> orders = new ArrayList<PayInstOrderResult>();

        for (PayInstOrderResultDO resultDO : results) {
            order = convert(resultDO);
            orders.add(order);
        }

        return orders;
	}

	/**
     * DO-->机构订单结果
     *
     * @param resultDO
     * @return
     */
    public static PayInstOrderResult convert(PayInstOrderResultDO resultDO) {
    	PayInstOrderResult order = new PayInstOrderResult();

        order.setFundChannelCode(resultDO.getFundChannelCode());
        order.setResultId(resultDO.getResultId());
        order.setBatchResultId(resultDO.getBatchResultId());
        order.setInstOrderId(resultDO.getInstOrderId());
        order.setInstSeqNo(resultDO.getInstSeqNo());
        order.setOrderType(BizType.getByCode(resultDO.getOrderType()));
        order.setRealAmount(new Money(new BigDecimal(resultDO.getRealAmount())));
        order.setAccountName(resultDO.getAccountName());
        order.setAccountNo(resultDO.getAccountNo());
        order.setCardType(CardType.getByCode(resultDO.getCardType()));
        order.setInstOrderNo(resultDO.getOrgiInstOrderNo());
        order.setCompareStatus(InstOrderCompareStatus.getByCode(resultDO.getCompareStatus()));
        order.setStatus(InstOrderResultStatus.getByCode(resultDO.getInstStatus()));
        order.setGlideStatus(InstOrderGlideStatus.getByCode(resultDO.getGlideStatus()));
        order.setInstResultCode(resultDO.getInstResultCode());
        order.setGmtModified(resultDO.getGmtModified());
        order.setGmtCreate(resultDO.getGmtCreate());
        order.setMemo(resultDO.getMemo());
        order.setRealCurrency(CurrencyType.getByCode(resultDO.getRealCurrency()));
        order.setInstOrderNo(resultDO.getInstOrderNo());
        order.setArchiveBatchId(resultDO.getArchiveBatchId());
        order.setBatchType(BatchType.getByCode(resultDO.getBatchType()));
        order.setOperateStatus(InstResultOperateStatus.getByCode(resultDO.getOperateStatus()));
        order.setDiffMsg(resultDO.getDiffMsg());
        order.setFundinOrgiInstOrderNo(resultDO.getFundinOrgiInstOrderNo());
        if (StringUtil.isNotEmpty(resultDO.getExtension()))
            order.setExtension(CommonConverter.convertFromDb(resultDO.getExtension()));

        order.setApiResultCode(resultDO.getApiResultCode());
        order.setApiResultSubCode(resultDO.getApiResultSubCode());

        if (!StringUtil.isBlank(resultDO.getApiType())) {
            order.setApiType(FundChannelApiType.getByCode(resultDO.getApiType()));
        }
        if (!StringUtil.isBlank(resultDO.getRiskFlag())) {
            order.setRiskFlag(RiskFlag.getByCode(resultDO.getRiskFlag()));
        }
        if (!StringUtil.isBlank(resultDO.getNotifyBankorderStatus())) {
            order.setNotifyBankOrderStatus(NotifyBankOrderStatus.getByCode(resultDO
                .getNotifyBankorderStatus()));
        }
        return order;
    }

    /**
     * 构建待处理机构结果
     * @param instOrder
     * @return
     */
	public static PayInstOrderResult buildAwaiting(PayInstOrder instOrder) {
		PayInstOrderResult result = new PayInstOrderResult();
        result.setFundChannelCode(instOrder.getFundChannelCode());
        result.setApiType(FundChannelApiType.getByCode(instOrder.getFundChannelApi().getApiType()));
        result.setProcessStatus(InstOrderProcessStatus.AWAITING);
        result.setOrderType(instOrder.getBizType());
        result.setInstOrderNo(instOrder.getInstOrderNo());
        result.setMemo("受理中，请等待.");
        return result;
	}

	public static void convert(PayInstOrder from, PayInstOrderResult to) {
		from.setMemo(to.getResultMessage());
        to.setInstOrderId(from.getInstOrderId());
        to.setInstOrderNo(from.getInstOrderNo());
        to.setCompareStatus(InstOrderCompareStatus.AWAITING);
        to.setFundChannelCode(from.getFundChannelCode());
        to.setOrderType(from.getBizType());
        switch (from.getBizType()) {
            case FUNDIN: {
                //convertFundin((InstFundinOrder) from, to);
                break;
            }
            case REFUND: {
                //convertRefund((InstRefundOrder) from, to);
                break;
            }
            case FUNDOUT: {
                convertFundOut((InstFundoutOrder) from, to);
                break;
            }
        }
		
	}
	private static void convertFundOut(InstFundoutOrder from, PayInstOrderResult to) {
        to.setInstOrderId(from.getInstOrderId());
        to.setInstOrderNo(from.getInstOrderNo());
        to.setAccountName(from.getAccountName());
        to.setAccountNo(from.getAccountNo());
        to.setCardType(from.getCardType());
        to.setOrderType(from.getBizType());
        to.setCompareStatus(InstOrderCompareStatus.AWAITING);
    }

    private static void convertRefund(InstRefundOrder from, PayInstOrderResult to) {
        //TODO: set refund convert only
    }

    private static void convertFundin(InstFundinOrder from, PayInstOrderResult to) {
        //TODO: set findin convert only
    }

	public static PayInstOrderResultDO convert(PayInstOrderResult result) {
		PayInstOrderResultDO orderDO = new PayInstOrderResultDO();
        if (result.getResultId() != null) {
            orderDO.setResultId(result.getResultId());
        }
        if (result.getBatchResultId() != null) {
            orderDO.setBatchResultId(result.getBatchResultId());
        }

        orderDO.setInstOrderId(result.getInstOrderId());
        orderDO.setInstSeqNo(result.getInstSeqNo());
        if (result.getOrderType() != null && result.getOrderType().getCode() != null)
            orderDO.setOrderType(result.getOrderType().getCode());
        orderDO.setFundChannelCode(result.getFundChannelCode());
        orderDO.setRealAmount(result.getRealAmount().getAmount().doubleValue());
        orderDO.setRealCurrency(result.getRealCurrency().getCode());
        orderDO.setAccountName(result.getAccountName());
        orderDO.setAccountNo(result.getAccountNo());
        if (result.getCardType() != null) {
            orderDO.setCardType(result.getCardType().getCode());
        }
        orderDO.setOrgiInstOrderNo(result.getInstOrderNo());
        orderDO.setCompareStatus(result.getCompareStatus().getCode());
        if (result.getStatus() != null) {
            orderDO.setInstStatus(result.getStatus().getCode());
        }
        if (result.getGlideStatus() != null) {
            orderDO.setGlideStatus(result.getGlideStatus().getCode());
        }
        orderDO.setGmtModified(result.getGmtModified());
        orderDO.setGmtCreate(result.getGmtCreate());
        //保存前面64位字符,防止保存数据库字符超长
        String memo = result.getMemo();
        if (memo == null || memo.trim().equals("")) {
			memo = result.getResultMessage();
		}
        if (memo!=null && memo.length() > 64) {
			memo = memo.substring(0,64);
		}
        orderDO.setMemo(memo);
        orderDO.setInstOrderNo(result.getInstOrderNo());
        orderDO.setArchiveBatchId(result.getArchiveBatchId());
        if (result.getOperateStatus() != null) {
            orderDO.setOperateStatus(result.getOperateStatus().getCode());
        }
        if (result.getBatchType() != null) {
            orderDO.setBatchType(result.getBatchType().getCode());
        }
        orderDO.setDiffMsg(result.getDiffMsg());
        orderDO.setFundinOrgiInstOrderNo(result.getFundinOrgiInstOrderNo());
        if (result.getExtension() != null) {
            orderDO.setExtension(CommonConverter.convertToDb(result.getExtension()));
        }

        orderDO.setApiResultCode(result.getApiResultCode());
        orderDO.setApiResultSubCode(result.getApiResultSubCode());
        orderDO.setInstResultCode(result.getInstResultCode());
        if (result.getApiType() != null) {
            orderDO.setApiType(result.getApiType().getCode());
        }
        if (result.getRiskFlag() != null) {
            orderDO.setRiskFlag(result.getRiskFlag().getCode());
        }
        if (result.getNotifyBankOrderStatus() != null) {
            orderDO.setNotifyBankorderStatus(result.getNotifyBankOrderStatus().getCode());
        }
        return orderDO;
	}
}

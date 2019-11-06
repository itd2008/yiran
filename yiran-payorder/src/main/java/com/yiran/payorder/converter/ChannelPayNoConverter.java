package com.yiran.payorder.converter;

import org.apache.commons.lang.StringUtils;

import com.netfinworks.common.domain.Extension;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.paychannel.utils.CommonConverter;
import com.yiran.payorder.domain.InstRefundOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.ReturnResultNo;

public class ChannelPayNoConverter {

	public static String getReturnInstOrderNo(PayInstOrder instOrder, PayInstOrderResult result) {
		String resultNo = "";
        if (result != null && instOrder != null && instOrder.getFundChannelApi() != null) {
        	Extension e = CommonConverter.convertExtensionWithoutConvertKey(
        			CommonConverter.convertFromDb(instOrder.getFundChannelApi().getExtension()));
            ReturnResultNo returnResultNo = ReturnResultNo.getByCode(e.getValue(ExtensionKey.RETURN_CHANNEL_RESULT_NO.key));
            if (returnResultNo != null) {
                switch (returnResultNo) {
                    case CHANNEL_RESULT:
                        resultNo = result.getInstSeqNo();
                        break;
                    case INST_ORDER_NO:
                        resultNo = instOrder.getInstOrderNo();
                        break;
                    case GATE_ORDER_NO:
                        resultNo = instOrder.getExtension().get(ExtensionKey.GATE_ORDER_NO.key) != null ? instOrder
                            .getExtension().get(ExtensionKey.GATE_ORDER_NO.key) : instOrder
                            .getExtension().get(ExtensionKey.MAS_ORDER_NO.key);
                        break;
                    case ORGI_INST_ORDER_NO:
                        if(instOrder instanceof InstRefundOrder){
                            resultNo = ((InstRefundOrder) instOrder).getFundinOrderNo(); 
                        }
                        break;
                    default:
                        resultNo = instOrder.getInstOrderNo();
                }
            }
        }
        if (StringUtils.isEmpty(resultNo) && instOrder != null) {
            resultNo = instOrder.getInstOrderNo();
        }
        if (instOrder != null && YesNo.YES.equals(instOrder.getIsSplit())) {
            resultNo = instOrder.getPaySeqNo(); //拆分的用payChannelOrder订单号返回
        }
        return resultNo;
	}

}

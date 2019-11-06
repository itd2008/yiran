package com.yiran.payorder.converter;

import java.util.Date;
import java.util.Map;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.paychannel.enums.CurrencyType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.InstOrderCompareStatus;
import com.yiran.payorder.enums.InstOrderGlideStatus;
import com.yiran.payorder.enums.InstResultOperateStatus;

/**
 * 
 * <p>渠道结果转换类</p>
 */
public class ChannelResultConverter {

    /**
     * 渠道结果转换
     * @param src
     * @return
     */
    public static PayInstOrderResult convert(ChannelFundResult src, PayInstOrder instOrder) {
        if (src == null) {
            return null;
        }
        PayInstOrderResult result = convert(src);
        result.setInstOrderId(instOrder.getInstOrderId());
        return result;
    }

    

    /**
     * 渠道结果转换,用于批量转换
     * @param src
     * @return
     */
    public static PayInstOrderResult convert(ChannelFundResult src) {
        if (src == null) {
            return null;
        }
        PayInstOrderResult result = new PayInstOrderResult();
        result.setInstOrderNo(src.getInstOrderNo());
        result.setInstSeqNo(src.getInstReturnOrderNo()); //银行生成的流水号
        result.setCompareStatus(InstOrderCompareStatus.AWAITING);
        if(src.isSuccess()) {
            result.setOperateStatus(InstResultOperateStatus.AWAITING);
        }else{
            result.setOperateStatus(InstResultOperateStatus.FAILURE);
        }
        result.setGlideStatus(InstOrderGlideStatus.NONEED);
        result.setInstResultCode(src.getResultCode());
        result.setApiResultCode(src.getApiResultCode());
        result.setApiResultSubCode(src.getApiResultSubCode());
        result.setApiType(src.getApiType());
        result.setFundChannelCode(src.getFundChannelCode());
        Date channelProcessTime = new Date();
        if (src.getInstSettleTime() != null) {
            channelProcessTime = src.getInstSettleTime();
        } else if (src.getProcessTime() != null) {
            channelProcessTime = src.getProcessTime();
        }
        result.getExtension().put(ExtensionKey.CHANNEL_TRANS_TIME.key,
            DateUtil.format(channelProcessTime, DateUtil.shortFormat));

        if (src.getRealAmount() != null && src.getRealAmount().getAmount() != null) {
            result.setRealAmount(src.getRealAmount());
            result.setRealCurrency(CurrencyType.getByCode(result.getRealCurrency().getCode()));
        }

        //渠道接口变更 20130221
        Map<String, String> extMap = MapUtil.jsonToMap(src.getExtension());
        result.getExtension().putAll(extMap);
        String msg = src.getApiResultMessage();
        if(StringUtil.isBlank(msg)){
        	msg = src.getResultMessage();
        }
        if (StringUtil.isNotBlank(msg) && msg.length() > 128) {
            msg = StringUtil.substring(msg, 0, 96);
        }
        result.setResultMessage(msg);
        result.setMemo(msg);
        result.setFromHtml(src.getFromHtml());
        return result;
    }
    
    
   
}

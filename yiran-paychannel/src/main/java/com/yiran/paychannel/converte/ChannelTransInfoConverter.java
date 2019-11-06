package com.yiran.paychannel.converte;

import java.util.ArrayList;
import java.util.List;

import com.yiran.paychannel.domain.TmChannelTransInfo;
import com.yiran.paychannel.domain.TmChannelTransInfoDO;
import com.yiran.paychannel.enums.ChannelTransInfoStatus;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.utils.CommonConverter;

/**
 * 
 * <p>渠道交易使用信息转换器</p>
 */
public class ChannelTransInfoConverter {

    /**
     * DO对象转换
     * @param from
     * @return
     */
    public static TmChannelTransInfo convert(TmChannelTransInfoDO from) {
        TmChannelTransInfo result = new TmChannelTransInfo();
        result.setApiType(FundChannelApiType.getByCode(from.getApiType()));
        result.setFundChannelCode(from.getFundChannelCode());
        result.setGmtCreate(from.getGmtCreate());
        result.setGmtModified(from.getGmtModified());
        result.setMemo(from.getMemo());
        result.setTransCode(from.getTransCode());
        result.setStatus(ChannelTransInfoStatus.getByCode(from.getStatus()));
        result.setTransId(from.getTransId());
        result.setExtension(CommonConverter.convertFromDb(from.getExtension()));
        return result;
    }
    
    public static List<TmChannelTransInfo> convert(List<TmChannelTransInfoDO> list) {
    	List<TmChannelTransInfo> toList = new ArrayList<TmChannelTransInfo>();
    	for (TmChannelTransInfoDO from : list) {
    		TmChannelTransInfo result = new TmChannelTransInfo();
            result.setApiType(FundChannelApiType.getByCode(from.getApiType()));
            result.setFundChannelCode(from.getFundChannelCode());
            result.setGmtCreate(from.getGmtCreate());
            result.setGmtModified(from.getGmtModified());
            result.setMemo(from.getMemo());
            result.setTransCode(from.getTransCode());
            result.setStatus(ChannelTransInfoStatus.getByCode(from.getStatus()));
            result.setTransId(from.getTransId());
            result.setExtension(CommonConverter.convertFromDb(from.getExtension()));
            toList.add(result);
		}
        
        return toList;
    }

    /**
     * 领域对象转换
     * @param from
     * @return
     */
    public static TmChannelTransInfoDO convert(TmChannelTransInfo from) {
        TmChannelTransInfoDO result = new TmChannelTransInfoDO();
        result.setFundChannelCode(from.getFundChannelCode());
        result.setGmtCreate(from.getGmtCreate());
        result.setGmtModified(from.getGmtModified());
        result.setMemo(from.getMemo());
        result.setExtension(CommonConverter.convertToDb(from.getExtension()));
        result.setTransCode(from.getTransCode());
        if (from.getStatus() != null) {
            result.setStatus(from.getStatus().getCode());
        }
        if (from.getApiType() != null) {
            result.setApiType(from.getApiType().getCode());
        }
        return result;
    }
}

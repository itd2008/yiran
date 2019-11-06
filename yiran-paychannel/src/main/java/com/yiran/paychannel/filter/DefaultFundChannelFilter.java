package com.yiran.paychannel.filter;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yiran.common.utils.DateUtils;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.FilterType;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.utils.FundChannelSignUtil;


/**
 * 默认渠道过滤由实现
 * <p>注释</p>
 */
@Service("fundChannelFilter")
public class DefaultFundChannelFilter extends AbstractFundChannelFilter<TmFundChannel> {

    private static final Logger logger = LoggerFactory.getLogger(DefaultFundChannelFilter.class);

    @Autowired
    private FundChannelFilter<TmFundChannelApi>   fundChannelApiFilter;

    @Override
    protected void addParam(Map<String, ?> param, TmFundChannel element) {

    }

    @Override
    protected FilterType getCuurentFileterType() {
        return FilterType.CHANNEL;
    }

    @Override
    public void filter(List<TmFundChannel> channelList, String requestType, Map<String, ?> param) {
        for (Iterator<TmFundChannel> it = channelList.iterator(); it.hasNext();) {
            TmFundChannel channel = it.next();
            //是否在有效期
            if(!isFundOutTime(channel)){//过期
            	it.remove();
                continue;
            }
            if (!match(channel.getFundChannelCode(), channel.getExtList(), param)) {
                it.remove();
                continue;
            }
            fundChannelApiFilter.filter(channel.getApiList(), requestType, param);
            if(CollectionUtils.isEmpty(channel.getApiList())){
                it.remove();
                continue;
            }
            if (needFilterChannel(channel,param)){
                it.remove();
                continue;
            }
        }
    }
    /**
     * 是否在有效期
     * @param channel
     * @return
     */
    private boolean isFundOutTime(TmFundChannel channel) {
    	boolean flag = true;//默认在有效期时间内
    	//服务开始时间
    	Date validFrom = channel.getValidFrom();
    	//服务结束时间
    	Date validTo = channel.getValidTo();
    	//当前时间
    	Date currentTime = new Date();
    	flag = DateUtils.belongCalendar(currentTime,validFrom,validTo);
    	if(!flag){
    		logger.info("渠道【{}】已经过期,不可用",channel.getFundChannelCode());
    	}
		return flag;
	}

	private boolean needFilterChannel(TmFundChannel channel,Map<String, ?> param){
        if (FundChannelSignUtil.notNeedMatchSignedChannels(param)){
            return false;
        }
        if (PayMode.QUICKPAY.equals(channel.getPayMode())
                ||PayMode.TRUSTCOLLECT.equals(channel.getPayMode())
                ){
            TmFundChannelApi api=channel.getApi();
            if (api!=null){
                if (api.getApiType().equals(FundChannelApiType.DEBIT)){
                    if(!FundChannelSignUtil.fundChannelIsSigned(channel,param)
                            &&FundChannelSignUtil.needFirstSigned(api)){
                        logger.info("渠道:" + channel.getFundChannelCode() + "调用函数needFilterChannel被过滤。");
                        return true;
                    }
                }
            }
        }
        return false;
    }


}

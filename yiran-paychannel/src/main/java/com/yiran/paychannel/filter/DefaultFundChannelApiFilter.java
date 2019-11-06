package com.yiran.paychannel.filter;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.ChannelInfoExtKey;
import com.yiran.paychannel.enums.FilterType;
import com.yiran.paychannel.enums.RequestType;
import com.yiran.paychannel.service.ITmFundChannelApiService;

@Service("fundChannelApiFilter")
public class DefaultFundChannelApiFilter extends AbstractFundChannelFilter<TmFundChannelApi> {

	@Autowired
    private ITmFundChannelApiService tmFundChannelApiService;


    @Override
    protected FilterType getCuurentFileterType() {
        return FilterType.API;
    }

    @Override
    public void filter(List<TmFundChannelApi> apiList, String requestType, Map<String, ?> param) {
        if (CollectionUtils.isEmpty(apiList)) {
            return;
        }
        String fundChannelCode = apiList.get(0).getFundChannelCode();
        if (!tmFundChannelApiService.filterApi(apiList, requestType)) {
            if (logger.isInfoEnabled()) {
                logger.info("渠道:" + fundChannelCode + "没有满足类型API,请求类型" + requestType + ";");
            }
            return;
        }
        for (Iterator<TmFundChannelApi> it = apiList.iterator(); it.hasNext();) {
            TmFundChannelApi api = it.next();
            if (!match(api.getApiCode(), api.getExts(), param)) {
                it.remove();
                continue;
            }
        }
    }

	@Override
	protected void addParam(Map<String, ?> param, TmFundChannelApi element) {
		 ((Map<String, Object>) param).put(ChannelInfoExtKey.API_INFO.getCode(),
		            element.getExtension());
	}
}

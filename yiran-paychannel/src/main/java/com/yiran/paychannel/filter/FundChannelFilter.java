package com.yiran.paychannel.filter;

import java.util.List;
import java.util.Map;

/**
 * 
 * <p>渠道路由过滤器</p>
 */
public interface FundChannelFilter<T> {

    /**
     * 采用责任链模式依据优先级依次向下过滤
     * @param channelList
     * @param requestType
     * @param param
     */
    public void filter(List<T> channelList, String requestType, Map<String, ?> param);

}

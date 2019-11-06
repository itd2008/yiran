package com.yiran.paychannel.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ChannelInfoExtKey;
import com.yiran.paychannel.enums.ControlRequestType;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.ManualRefundType;
import com.yiran.paychannel.enums.ProductCode;
import com.yiran.paychannel.enums.RequestType;
import com.yiran.paychannel.service.ChannelProperty;
import com.yiran.paychannel.service.IApiService;

/**
 * API过滤器实现
 */
@Service
public class ApiServiceImpl implements IApiService {
    protected static final Logger   logger = LoggerFactory.getLogger(ApiServiceImpl.class);

    /** 控制请求类型与接口类型MAP */
    public static Map<String, List<FundChannelApiType>>       apiTypeMap              = new HashMap<String, List<FundChannelApiType>>();

    public static List<FundChannelApiType>                    queryApiTypeList = new ArrayList<FundChannelApiType>();

    /** 查询类型与查询对应类型map */
    public static Map<FundChannelApiType, FundChannelApiType> queryApiTypeMap         = new HashMap<FundChannelApiType, FundChannelApiType>();

    public static List<FundChannelApiType> combine(FundChannelApiType... apiType) {
        List<FundChannelApiType> list = new ArrayList<FundChannelApiType>();
        for (int i = 0; i < apiType.length; i++) {
            list.add(apiType[i]);
        }
        return list;
    }

    //初始化
    static {
        apiTypeMap.put(RequestType.FUND_IN.getCode(),
            combine(FundChannelApiType.DEBIT, FundChannelApiType.SIGN));
        apiTypeMap.put(
            RequestType.FUND_OUT.getCode(),
            combine(FundChannelApiType.SINGLE_PAY, FundChannelApiType.BATCH_PAY,
                FundChannelApiType.BATCH_FILE_PAY));
        apiTypeMap.put(
            RequestType.REFUND.getCode(),
            combine(FundChannelApiType.SINGLE_REFUND, FundChannelApiType.MANUAL_REFUND,
                FundChannelApiType.BATCH_FILE_REFUND, FundChannelApiType.BATCH_REFUND));
        apiTypeMap.put(RequestType.PRE_AUTH_DONE.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REQUEST.getCode(),
            combine(FundChannelApiType.PRE_AUTH));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REVERSAL.getCode(),
            combine(FundChannelApiType.PRE_AUTH_REVERSAL));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REVERSAL_UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_UNDO_REVERSAL));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_UNDO));
        apiTypeMap.put(ControlRequestType.REVERSAL.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE_REVERSAL));
        apiTypeMap.put(ControlRequestType.REVERSAL_UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE_UNDO_REVERSAL));
        apiTypeMap.put(ControlRequestType.UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE_UNDO));
        apiTypeMap.put(ControlRequestType.AUTHENTICATE.getCode(),
            combine(FundChannelApiType.AUTHENTICATE));
        apiTypeMap.put(ControlRequestType.TERMINATE.getCode(),
            combine(FundChannelApiType.TERMINATE));
        apiTypeMap.put(ControlRequestType.DEBIT_ADVANCE.getCode(),
            combine(FundChannelApiType.DEBIT_ADVANCE));
        apiTypeMap.put(ControlRequestType.AUTH_ADVANCE.getCode(),
                combine(FundChannelApiType.AUTH_ADVANCE));
        apiTypeMap.put(ControlRequestType.SEND_MESSAGE.getCode(),
            combine(FundChannelApiType.SEND_MESSAGE));
        apiTypeMap.put(ControlRequestType.NOTIFY.getCode(), combine(FundChannelApiType.NOTIFY));
        apiTypeMap.put(ControlRequestType.UPDATE_REPAYMENT_PLAN.getCode(), combine(FundChannelApiType.UPDATE_REPAYMENT_PLAN));

        queryApiTypeList.add(FundChannelApiType.AUTHENTICATE_QUERY);
        queryApiTypeList.add(FundChannelApiType.TERMINATE_QUERY);
        queryApiTypeList.add(FundChannelApiType.DEBIT_ADVANCE_QUERY);
        queryApiTypeList.add(FundChannelApiType.SINGLE_QUERY);
        queryApiTypeList.add(FundChannelApiType.QUERY_BALANCE);
        queryApiTypeList.add(FundChannelApiType.SINGLE_REFUND);
        queryApiTypeList.add(FundChannelApiType.SINGLE_REFUND_QUERY);

        queryApiTypeMap.put(FundChannelApiType.AUTHENTICATE_QUERY, FundChannelApiType.AUTHENTICATE);
        queryApiTypeMap.put(FundChannelApiType.TERMINATE_QUERY, FundChannelApiType.TERMINATE);
        queryApiTypeMap.put(FundChannelApiType.DEBIT_ADVANCE_QUERY, FundChannelApiType.DEBIT_ADVANCE);
    }

    @Override
    public void sortFilterApi(TmFundChannel fundChannel, BizType bizType) {
        sortFilterApi(fundChannel.getApiList(), bizType);
    }

    @Override
    public void sortFilterApi(List<TmFundChannelApi> apis, BizType bizType) {
        if (apis == null || apis.size() == 0) {
            return;
        }
        Iterator<TmFundChannelApi> iterator = apis.iterator();
        while (iterator.hasNext()) {
        	TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.isMatchBizType(FundChannelApiType.getByCode(api.getApiType()), bizType))
                iterator.remove();
        }
        if (isExistApi(apis)) {
            Collections.sort(apis, new Comparator<TmFundChannelApi>() {
                public int compare(TmFundChannelApi arg0, TmFundChannelApi arg1) {
                    return -(arg0.getApiPriority() - arg1.getApiPriority());
                }
            });
        }

    }

    @Override
    public boolean selectSingleApi(TmFundChannel fundChannel) {
        return selectSingleApi(fundChannel.getApiList());
    }

    private boolean selectSingleApi(List<TmFundChannelApi> apis) {
        if (apis == null || apis.size() == 0) {
            return false;
        }
        Iterator<TmFundChannelApi> iterator = apis.iterator();
        while (iterator.hasNext()) {
        	TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.isSingle(FundChannelApiType.getByCode(api.getApiType())))
                iterator.remove();
        }
        return isExistApi(apis);
    }

    @Override
    public boolean selectBulkApi(TmFundChannel fundChannel) {
        if (fundChannel.getApiList() == null || fundChannel.getApiList().size() == 0) {
            return false;
        }
        Iterator<TmFundChannelApi> iterator = fundChannel.getApiList().iterator();
        while (iterator.hasNext()) {
            TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.isBatch(FundChannelApiType.getByCode(api.getApiType())))
                iterator.remove();
        }
        return (fundChannel.getApiList() != null && fundChannel.getApiList().size() > 0);
    }

    /*
     * (non-Javadoc)
     * @see com.netfinworks.cmf.fss.core.router.ApiService#selectBulkFileApi(com.netfinworks.cmf.common.core.domain.channel.FundChannel)
     */
    @Override
    public boolean selectBulkFileApi(TmFundChannel fundChannel) {
        if (fundChannel.getApiList() == null || fundChannel.getApiList().size() == 0) {
            return false;
        }
        Iterator<TmFundChannelApi> iterator = fundChannel.getApiList().iterator();
        while (iterator.hasNext()) {
            TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.isFile(FundChannelApiType.getByCode(api.getApiType())))
                iterator.remove();
        }
        return (fundChannel.getApiList() != null && fundChannel.getApiList().size() > 0);
    }

    /**
     * 
     */
    @Override
    public boolean selectSingleQueryApi(TmFundChannel fundChannel) {
        if (fundChannel.getApiList() == null || fundChannel.getApiList().size() == 0) {
            return false;
        }
        Iterator<TmFundChannelApi> iterator = fundChannel.getApiList().iterator();
        while (iterator.hasNext()) {
            TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.SINGLE_QUERY.equals(api.getApiType()))
                iterator.remove();
        }
        return (fundChannel.getApiList() != null && fundChannel.getApiList().size() > 0);
    }
    
    @Override
    public boolean selectBalanceQueryApi(TmFundChannel fundChannel) {
        if (fundChannel.getApiList() == null || fundChannel.getApiList().size() == 0) {
            return false;
        }
        Iterator<TmFundChannelApi> iterator = fundChannel.getApiList().iterator();
        while (iterator.hasNext()) {
            TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.QUERY_BALANCE.equals(api.getApiType()))
                iterator.remove();
        }
        return (fundChannel.getApiList() != null && fundChannel.getApiList().size() > 0);
    }

    @Override
    public boolean selectBulkQueryApi(TmFundChannel fundChannel) {
        if (fundChannel.getApiList() == null || fundChannel.getApiList().size() == 0) {
            return false;
        }
        Iterator<TmFundChannelApi> iterator = fundChannel.getApiList().iterator();
        while (iterator.hasNext()) {
            TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.BATCH_QUERY.equals(api.getApiType()))
                iterator.remove();
        }
        return (fundChannel.getApiList() != null && fundChannel.getApiList().size() > 0);
    }

    @Override
    public boolean filterApi(TmFundChannel fundChannel, BizType bizType, Map<String, ?> param) {
        if (fundChannel.getApiList() == null || fundChannel.getApiList().size() == 0) {
            return false;
        }
        if (logger.isInfoEnabled()) {
            logger.info("过滤API,渠道编码[" + fundChannel.getFundChannelCode() + "],过滤之前有["
                         + fundChannel.getApiList().size() + "]个接口");
        }
        if (param != null) {
            Object apiType = param.get(ChannelInfoExtKey.API_TYPE.getCode());
            if (apiType != null && StringUtil.isNotEmpty((String) apiType)
                && FundChannelApiType.getByCode((String) apiType) != null) {
                return filterWithApiType(fundChannel,
                    FundChannelApiType.getByCode((String) apiType));
            }
        }
        sortFilterApi(fundChannel, bizType);
        if (param != null && param.get(ChannelInfoExtKey.PRODUCT_CODE.getCode()) != null) {
            String productCode = (String) param.get(ChannelInfoExtKey.PRODUCT_CODE.getCode());
            if (ProductCode.FUNDTRANSFER.getCode().equals(productCode)) {
                if (logger.isInfoEnabled()) {
                    logger.info("资金调拨过滤API,产品编码["
                                 + param.get(ChannelInfoExtKey.PRODUCT_CODE.getCode()) + "]");
                }

                return selectSingleApi(fundChannel);
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("过滤API,渠道编码[" + fundChannel.getFundChannelCode() + "],过滤之后有["
                         + fundChannel.getApiList().size() + "]个接口");
        }

        return (fundChannel.getApiList() != null && fundChannel.getApiList().size() > 0);
    }

    public boolean filterApi(List<TmFundChannelApi> apis, BizType bizType, Map<String, ?> param) {
        if (apis == null || apis.size() == 0) {
            return false;
        }
        String fundChannelCode = apis.get(0).getFundChannelCode();
        if (logger.isInfoEnabled()) {
            logger.info("过滤API,渠道编码[" + fundChannelCode + "],过滤之前有[" + apis + "]个接口");
        }
        if (param != null) {
            Object apiType = param.get(ChannelInfoExtKey.API_TYPE.getCode());
            if (apiType != null && StringUtil.isNotEmpty((String) apiType)
                && FundChannelApiType.getByCode((String) apiType) != null) {
                return filterWithApiType(apis, FundChannelApiType.getByCode((String) apiType));
            }
        }
        sortFilterApi(apis, bizType);
        if (param != null && param.get(ChannelInfoExtKey.PRODUCT_CODE.getCode()) != null) {
            String productCode = (String) param.get(ChannelInfoExtKey.PRODUCT_CODE.getCode());
            if (ProductCode.FUNDTRANSFER.getCode().equals(productCode)) {
                if (logger.isInfoEnabled()) {
                    logger.info("资金调拨过滤API,产品编码["
                                 + param.get(ChannelInfoExtKey.PRODUCT_CODE.getCode()) + "]");
                }

                return selectSingleApi(apis);
            }
        }
        if (logger.isInfoEnabled()) {
            logger.info("过滤API,渠道编码[" + fundChannelCode + "],过滤之后有[" + apis.size() + "]个接口");
        }

        return isExistApi(apis);
    }

    /**
     * 过滤自动冲退接口
     * <p>1.校验时间限制是否在退款支持的退款有效期内</p>
     * <p>2.退款接口的金额是否有超限</p>
     * @param channel
     * @param param
     */
    public String filterRefundApi(TmFundChannel channel, Map<String, ?> param) {
        try {
            if (channel.getApiList().size() < 2) {//如果接口不超过两个,不需要过滤
                if (FundChannelApiType.MANUAL_REFUND.equals(channel.getApiList().get(0).getApiType())) {
                    return ManualRefundType.NON_SUPPORT_AUTO_REFUND.getCode();
                }
                return "";
            }
            Date currentDate = DateUtil.parseDateNoTime(
                DateUtil.format(new Date(), DateUtil.shortFormat), DateUtil.shortFormat);
            Date fundinDate = (Date) param.get(ChannelInfoExtKey.ORGI_FUNDIN_DATE.getCode());
            if (fundinDate.getTime() < currentDate.getTime() - DateUtil.ONE_DAY_MILL_SECONDS
                                       * getAutoRefundDateLimit(channel)) {
                filterWithApiType(channel, FundChannelApiType.MANUAL_REFUND);
                return ManualRefundType.REFUND_DELAY_DATE.getCode();
            }
            if (channel.getChannelProperty().get(
                ChannelInfoExtKey.AUTO_REFUND_AMOUNT_LIMIT.getCode()) != null
                && StringUtil.isNotEmpty(channel.getChannelProperty()
                    .get(ChannelInfoExtKey.AUTO_REFUND_AMOUNT_LIMIT.getCode()).getValue())) {
                BigDecimal refundAmountLimit = new BigDecimal(channel.getChannelProperty()
                    .get(ChannelInfoExtKey.AUTO_REFUND_AMOUNT_LIMIT.getCode()).getValue());
                if (refundAmountLimit.compareTo((BigDecimal) param.get(ChannelInfoExtKey.AMOUNT
                    .getCode())) < 0) {
                    filterWithApiType(channel, FundChannelApiType.MANUAL_REFUND);
                    return ManualRefundType.REFUND_MAX_THAN_AMOUNT_LIMIT.getCode();
                }
            }
        } catch (ParseException e) {
            logger.error("[充退API路由异常]: ", e);
        }

        return "";
    }

    /**
     * 获取自动充退天数限制参数，若无参数配置，默认使用30天
     *
     * @param channel
     * @return
     */
    private int getAutoRefundDateLimit(TmFundChannel channel) {
        final int DEFAULT_DAYS = 30;
        if (channel == null) {
            return DEFAULT_DAYS;
        }
        ChannelProperty property = channel.getChannelProperty().get(
            ChannelInfoExtKey.AUTO_REFUND_DATE_LIMIT.getCode());
        if (property == null) {
            return DEFAULT_DAYS;
        }
        try {
            return Integer.parseInt(property.getValue());
        } catch (NumberFormatException e) {
            return DEFAULT_DAYS;
        }
    }

    @Override
    public boolean selectSingleReversalApi(TmFundChannel fundChannel) {
        if (fundChannel.getApiList() == null || fundChannel.getApiList().size() == 0) {
            return false;
        }
        Iterator<TmFundChannelApi> iterator = fundChannel.getApiList().iterator();
        while (iterator.hasNext()) {
            TmFundChannelApi api = iterator.next();
            if (!FundChannelApiType.DEBIT_REVERSAL.equals(api.getApiType()))
                iterator.remove();
        }
        return (fundChannel.getApiList() != null && fundChannel.getApiList().size() > 0);
    }

    @Override
    public boolean filterWithApiType(TmFundChannel fundChannel, FundChannelApiType apiType) {
        return filterWithApiType(fundChannel.getApiList(), apiType);
    }

    private boolean filterWithApiType(List<TmFundChannelApi> apis, FundChannelApiType apiType) {
        if (apis == null || apis.size() == 0) {
            return false;
        }
        if (apiType != null) {
            Iterator<TmFundChannelApi> iterator = apis.iterator();
            while (iterator.hasNext()) {
                TmFundChannelApi api = iterator.next();
                if (!apiType.equals(api.getApiType()))
                    iterator.remove();
            }
        }
        return (isExistApi(apis));
    }

    private boolean isExistApi(List<TmFundChannelApi> apis) {
        return (apis != null && apis.size() > 0);
    }

    @Override
    public boolean isExistApi(TmFundChannel fundChannel) {
        return isExistApi(fundChannel.getApiList());
    }

    @Override
    public boolean filterApi(List<TmFundChannelApi> apis, String requestType) {
        if (apis == null || apis.size() == 0) {
            return false;
        }
        if (requestType != null) {
            Iterator<TmFundChannelApi> iterator = apis.iterator();
            while (iterator.hasNext()) {
                TmFundChannelApi api = iterator.next();
                if (!apiTypeMap.get(requestType).contains(api.getApiType())) {
                    iterator.remove();
                }
            }
        }
        return (isExistApi(apis));
    }

    @Override
    public List<FundChannelApiType> getQueryControlApiTypeList() {
        return queryApiTypeList;
    }

    @Override
    public Map<FundChannelApiType, FundChannelApiType> getQueryApiTypeMap() {
        return queryApiTypeMap;
    }

}

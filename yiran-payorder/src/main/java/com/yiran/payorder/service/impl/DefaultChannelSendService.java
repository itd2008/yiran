package com.yiran.payorder.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.SuccessFailure;
import com.yiran.paychannel.service.ITmChannelTransInfoService;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.converter.ChannelRequestConverter;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayMonitorLog;
import com.yiran.payorder.enums.MonitorItem;
import com.yiran.payorder.enums.PayFundResultCode;
import com.yiran.payorder.facade.ChannelFundFacade;
import com.yiran.payorder.request.ChannelFundRequest;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.QueryRequest;
import com.yiran.payorder.service.IChannelSendService;
import com.yiran.payorder.service.ICombineCallbackService;
import com.yiran.payorder.service.IPayMonitorLogService;

/**
 * <p>渠道发送服务</p>
 */
@Service("channelSendService")
public class DefaultChannelSendService implements BasicConstant, IChannelSendService {
    private static final Logger            logger = LoggerFactory.getLogger(DefaultChannelSendService.class);

    @Autowired
    private ChannelFundFacade channelFundFacade;
    /** 回调服务 */
    @Autowired
    private ICombineCallbackService         combineCallbackService;

    @Autowired
    private ITmChannelTransInfoService tmChannelTransInfoService;

    @Override
    public ChannelFundResult send(PayInstOrder instOrder) {
        if (logger.isDebugEnabled()) {
            logger.debug("[ChannelPayOrder-PayChannel]开始发送渠道请求: instOrderNo=" + instOrder.getInstOrderNo()
                         + ", apiInfo=" + instOrder.getFundChannelApi());
        }
        ChannelFundResult result = null;
        ChannelFundRequest request = ChannelRequestConverter.covert(instOrder, combineCallbackService,
        		tmChannelTransInfoService);
        try {
                result = channelFundFacade.apply(request);

        } catch (Exception e) {
            logger.error("[ChannelPayOrder-Core]发送渠道通讯异常: instOrderNo=" + instOrder.getInstOrderNo(), e);
            if (checkRetry(instOrder)) {
                try {
                        result = channelFundFacade.apply(request);
                } catch (Exception e1) {
                    logger.error(
                        "[ChannelPayOrder-Core]发送渠道通讯retry异常: instOrderNo=" + instOrder.getInstOrderNo(), e);
                    result = new ChannelFundResult(false,
                        PayFundResultCode.UNKNOW_EXCEPTION.getCode(), ERROR_COMM, FundChannelApiType.getByCode(instOrder
                            .getFundChannelApi().getApiType()));
                }
            } else {
                result = new ChannelFundResult(false, PayFundResultCode.UNKNOW_EXCEPTION.getCode(),
                    ERROR_COMM, FundChannelApiType.getByCode(instOrder.getFundChannelApi().getApiType()));
            }

        }
        if (logger.isDebugEnabled()) {
            logger.debug("[ChannelPayOrder-Core]发送渠道结果: instOrderNo=" + instOrder.getInstOrderNo()
                         + ", result=" + result);
        }
        return result;
    }



    /**
     *  检查是否需要重试
     * @param instOrder
     * @return
     */
    private boolean checkRetry(PayInstOrder instOrder) {
        FundChannelApiType type = FundChannelApiType.getByCode(instOrder.getFundChannelApi().getApiType());
        switch (type) {
            case SIGN:
                return true;
            default:
                return false;
        }

    }

    @Override
    public ChannelFundResult query(QueryRequest queryRequest) {
        if (logger.isDebugEnabled()) {
            logger.debug("[ChannelPayOrder-Core]开始发送渠道查询请求: queryRequest=" + queryRequest);
        }

        ChannelFundResult result = null;
        try {
            result = channelFundFacade.apply(queryRequest);
        } catch (Exception e) {
            logger.error("[ChannelPayOrder-Core]开始发送渠道查询请求: queryRequest=" + queryRequest, e);
            result = new ChannelFundResult(false, PayFundResultCode.UNKNOW_EXCEPTION.getCode(),
                ERROR_COMM, queryRequest.getApiType());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("[ChannelPayOrder-Core]发送渠道查询结果: queryRequest=" + queryRequest + ", result=" + result);
        }

        return result;
    }



	@Override
	public ChannelFundResult download(ChannelRequest channelRequest) {
		if (logger.isDebugEnabled()) {
            logger.debug("[对账文件下载]开始发送渠道查询请求: channelRequest=" + channelRequest);
        }

        ChannelFundResult result = null;
        try {
            result = channelFundFacade.apply(channelRequest);
        } catch (Exception e) {
            logger.error("[对账文件下载]开始发送渠道查询请求: queryRequest=" + channelRequest, e);
            result = new ChannelFundResult(false, PayFundResultCode.UNKNOW_EXCEPTION.getCode(),
                ERROR_COMM, channelRequest.getApiType());
        }

        if (logger.isDebugEnabled()) {
            logger.debug("[对账文件下载]发送渠道查询结果: queryRequest=" + channelRequest + ", result=" + result);
        }

        return result;
	}
    
    

}

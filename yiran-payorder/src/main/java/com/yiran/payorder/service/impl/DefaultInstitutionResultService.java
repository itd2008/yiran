package com.yiran.payorder.service.impl;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.netfinworks.biz.common.util.BaseResult;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.domain.TmFundChannelApiParam;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.segment.Segment;
import com.yiran.paychannel.service.IFundChannelRouter;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.converter.ChannelResultConverter;
import com.yiran.payorder.converter.InstOrderResultConverter;
import com.yiran.payorder.domain.ChannelFundResult;
import com.yiran.payorder.domain.ChannelNotifyResult;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.InstBaseResult;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.domain.PayMonitorLog;
import com.yiran.payorder.domain.PayResultNotify;
import com.yiran.payorder.domain.PayResultNotifyLog;
import com.yiran.payorder.enums.ApiParamScene;
import com.yiran.payorder.enums.CommunicateStatus;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;
import com.yiran.payorder.enums.InstResultOperateStatus;
import com.yiran.payorder.enums.MonitorItem;
import com.yiran.payorder.enums.NotifyEventCode;
import com.yiran.payorder.enums.NotifyStatus;
import com.yiran.payorder.enums.OrderRiskStatus;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.enums.RiskFlag;
import com.yiran.payorder.exception.AppRuntimeException;
import com.yiran.payorder.exception.WrongStateException;
import com.yiran.payorder.facade.IResultNotifyFacade;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IDuplicateResultProcessService;
import com.yiran.payorder.service.IPayInstOrderResultService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IPayMonitorLogService;
import com.yiran.payorder.service.IPayResultNotifyLogService;
import com.yiran.payorder.service.IPayResultNotifyService;
import com.yiran.payorder.service.IPaySignInfoService;
import com.yiran.payorder.service.IRefundProcessService;
import com.yiran.payorder.service.IRmsVerifyService;
import com.yiran.payorder.service.IUnityResultCodeService;
import com.yiran.payorder.service.InstOrderNoGenerator;
import com.yiran.payorder.service.InstitutionResultService;
import com.yiran.payorder.validator.ChannelResultValidator;

import cn.hutool.core.lang.UUID;

/**
 * 
 * <p>机构结果服务默认实现</p>
 */
@Service("institutionResultService")
public class DefaultInstitutionResultService implements InstitutionResultService {

    private Logger  logger = LoggerFactory.getLogger(DefaultInstitutionResultService.class);

    @Autowired
   	private IChannelPayOrderService channelPayOrderService;
       
    @Autowired
   	private IPayInstOrderService payInstOrderService;
       
    @Autowired
   	private IPayInstOrderResultService payInstOrderResultService;
    @Autowired
    private IUnityResultCodeService unityResultCodeService;
    @Autowired
    private ChannelResultValidator channelResultValidator;
    @Autowired
    private IDuplicateResultProcessService duplicateResultProcessService;
    @Autowired
    private IRmsVerifyService rmsVerifyService;
    @Autowired
    private IFundChannelRouter fundChannelRouter;
    @Autowired
    private InstOrderNoGenerator instOrderNoGenerator;
    @Autowired
    private IRefundProcessService refundProcessService;
    @Autowired
	private IPayMonitorLogService payMonitorLogService;
    @Override
    public ChannelNotifyResult process(ChannelFundResult channelFundResult, boolean isSync) {
        fixInstOrderNo(channelFundResult);
        PayInstOrder instOrder = payInstOrderService.loadByInstOrderNo(channelFundResult.getInstOrderNo());
        if (instOrder == null) {
            if (logger.isInfoEnabled()) {
                logger.info("机构订单不存在," + channelFundResult.toString());
            }
            payMonitorLogService.insertPayMonitorLog(new PayMonitorLog(channelFundResult.getInstOrderNo(),
                    MonitorItem.INST_ORDER_NON_EXIST, "银行通知返回机构订单号不存在", null));
            return buildReturnInfo(false, BasicConstant.FAILURE.toString(), "不存在订单",
                InstOrderStatus.IN_PROCESS);
        }
        try {
            PayInstOrderResult instResult = process(instOrder, channelFundResult, isSync);
            ChannelNotifyResult returnInfo = buildReturnInfo(instResult);

            if (PayMode.NETBANK == instOrder.getPayMode()
                || PayMode.BANKSIGNQUICKPAY == instOrder.getPayMode()
                || PayMode.QUICKPAY == instOrder.getPayMode()) {
                String pageUrl = instOrder.getExtension().get(ExtensionKey.PAGE_URL.key);
                Map<String, String> map = new HashMap<String, String>();
                map.put(ExtensionKey.PAGE_URL_FOR_SIGN.key, pageUrl == null ? "" : pageUrl);

                returnInfo.setExtension(JSON.toJSONString(map,
                    SerializerFeature.UseISO8601DateFormat));
            }

            return returnInfo;
        } catch (Exception e) {
            logger.error("机构订单[" + instOrder.getInstOrderNo() + "]结果返回处理异常", e);
            return buildReturnInfo(false, BasicConstant.FAILURE.toString(),
                "处理异常:" + e.getMessage(), InstOrderStatus.IN_PROCESS);
        }

    }

    @Override
    public PayInstOrderResult process(PayInstOrder instOrder, ChannelFundResult channelFundResult,
                                   boolean isSync) {

        PayInstOrderResult instResult = ChannelResultConverter.convert(channelFundResult, instOrder);

        //设置统一返回码
        unityResultCodeService.fillResultStatus(instResult, instOrder.getFundChannel()
            .getFundChannelCode());

        //用机构订单信息填充结果信息
        InstOrderResultConverter.convert(instOrder, instResult);

        ChannelPayOrder channelPayOrder = channelPayOrderService.loadByInstOrderId(instOrder.getInstOrderId());

        process(channelPayOrder, instOrder, instResult, isSync);

        return instResult;
    }


	/**
     * 审核后逻辑处理
     * @param cmfOrder
     * @param instOrder
     * @param instResult
     * @param isSync
     * @param riskStatus
     * @return
     * @throws ValidateException 
     */
    @Override
    public BaseResult process(ChannelPayOrder channelPayOrder, PayInstOrder instOrder, PayInstOrderResult instResult,
                              boolean isSync) {

        //风控审核中或审核拒绝的订单如果渠道继续通知结果不做任何处理
        //也不保存处理结果
        //后续处理风控结果返回时可能会渠道不匹配的结果数据
        if (OrderRiskStatus.FAILED_SEND.equals(instOrder.getRiskStatus())
            || OrderRiskStatus.IN_PROCESS.equals(instOrder.getRiskStatus())
            || OrderRiskStatus.REJECT.equals(instOrder.getRiskStatus())) {
            instResult.setProcessStatus(InstOrderProcessStatus.AWAITING);
            instResult.setStatus(InstOrderResultStatus.AWAITING);
            return new BaseResult(false, "订单正在风控审核中");
        }

        //TODO:下次优化,保存机构结果日志
        if (duplicateResultProcessService.checkHasProcessed(instOrder)) {
            logger.warn("[渠道回调通知]重复通知,机构订单号[" + instOrder.getInstOrderNo() + "],"
                        + instResult.toString());
            boolean duplicateCheckStatus = duplicateResultProcessService.duplicateRequestProcess(instOrder,
                instResult);
            if (duplicateCheckStatus) {
                return new BaseResult(true, "[渠道回调通知]重复通知,机构订单号[" + instOrder.getInstOrderNo()
                                            + "]");
            } else {
                return new BaseResult(false, "[渠道回调通知]重复通知且状态不一致,机构订单号["
                                             + instOrder.getInstOrderNo() + "]");
            }
        }

        //校验 
        BaseResult result = channelResultValidator.validate(instOrder, instResult);
        if (!result.isSuccess()) {
            if (logger.isInfoEnabled()) {
                logger.warn("机构订单[" + instOrder.getInstOrderNo() + "]校验失败,信息:"
                            + result.getResultMessage());
            }
            payMonitorLogService.insertPayMonitorLog(new PayMonitorLog(instOrder.getInstOrderNo(),
                    MonitorItem.CHANNEL_PARAMETER_INVALID, "参数验证异常[" + instOrder.getInstOrderNo()+"],"
                    		+result.getResultMessage(),null));
            buildFailResult(instResult);
        }

        //针对出/入/退  各自特殊逻辑处理
        //退款转人工必须要在保存结果之前完成这部操作,以免存在结果不一致的情况
        //必须在更新订单状态之前操作,一面退款失败更新通讯状态为R,转手工时无法转换
        specialProcess(instOrder, instResult);

        //更新通讯状态
        switch (instResult.getProcessStatus()) {
            case AWAITING:
                instOrder.setCommunicateStatus(CommunicateStatus.SENT);
                payInstOrderService.updateCommunicateStatusById(CommunicateStatus.SENT,
                    instOrder.getInstOrderId());
                break;
            case SUCCESS:
                instOrder.setCommunicateStatus(CommunicateStatus.RECEIVED);
                payInstOrderService.updateCommunicateStatusById(CommunicateStatus.RECEIVED,
                    instOrder.getInstOrderId());
                break;
            default:
                //只有当结果api与机构订单api相同时,才更新通讯失败状态
                //防止查询控制类接口操作异常更新订单通讯状态
                //注:任何通讯异常都必须返回接口类型,若没有,则需要加上
                if (instResult.getApiType() != null
                    && instOrder.getFundChannelApi().getApiType().equals(instResult.getApiType())) {
                    instOrder.setCommunicateStatus(CommunicateStatus.FAILURE);
                    payInstOrderService.updateCommunicateStatusById(CommunicateStatus.FAILURE,
                        instOrder.getInstOrderId());
                }
                break;

        }

        //保存扩展信息
        //POS/快捷需要 保存该部分信息
        saveExtension(instOrder, instResult);

        //保存机构订单结果
        payInstOrderResultService.store(instResult);

        if (!InstOrderProcessStatus.SUCCESS.equals(instResult.getProcessStatus())) {
            return new BaseResult(false, "结果不为最终状态");
        }

        //内部针对银行返回风险审核
        if (!rmsVerifyService.checkChannelResult(instOrder, instResult)) {
            instResult.setRiskFlag(RiskFlag.BANK_CHECK);
            payInstOrderResultService.updateRiskFlagById(instResult.getRiskFlag(),
                instResult.getResultId());
        }

        //风控审核 
        OrderRiskStatus riskStatus = rmsVerifyService.verify(channelPayOrder, instOrder, instResult);
        return process(channelPayOrder, instOrder, instResult, isSync, riskStatus);
    }

    /**
     * 审核后逻辑处理
     * @param cmfOrder
     * @param instOrder
     * @param instResult
     * @param isSync
     * @param riskStatus
     * @return
     * @throws ValidateException 
     */
    @Override
    public BaseResult process(ChannelPayOrder channelPayOrder, PayInstOrder instOrder, PayInstOrderResult instResult,
                              boolean isSync, OrderRiskStatus riskStatus) {
    	instOrder.setMemo(instResult.getMemo());
        //针对风控处理中和发送失败的依然认为是该笔订单结果对应的操作
        switch (riskStatus) {
            case IN_PROCESS:
                instResult.setOperateStatus(InstResultOperateStatus.SUCCESSFUL);
                payInstOrderResultService.updateOperateStatusById(instResult.getOperateStatus(),
                    instResult.getResultId());
                instResult.setProcessStatus(InstOrderProcessStatus.AWAITING);
                instResult.setStatus(InstOrderResultStatus.AWAITING);
            case FAILED_SEND:
                instResult.setOperateStatus(InstResultOperateStatus.SUCCESSFUL);
                payInstOrderResultService.updateOperateStatusById(instResult.getOperateStatus(),
                    instResult.getResultId());
                instResult.setProcessStatus(InstOrderProcessStatus.AWAITING);
                instResult.setStatus(InstOrderResultStatus.AWAITING);
                break;
            case REJECT:
                //如果银行返回有风险的,始终用银行风险标识
                if (!RiskFlag.BANK_CHECK.equals(instResult.getRiskFlag())) {
                    instResult.setRiskFlag(RiskFlag.RMS_CHECK);
                    payInstOrderResultService.updateRiskFlagById(instResult.getRiskFlag(),
                        instResult.getResultId());
                }
                //针对明确成功的风险拒绝按失败处理
                if (InstOrderResultStatus.SUCCESSFUL.equals(instResult.getStatus())) {
                    instResult.setStatus(InstOrderResultStatus.FAILURE);
                }
                break;
            default:
                break;
        }

        //风控暂停,直接返回,并且需要发送counter补单流水
        if (!InstOrderProcessStatus.SUCCESS.equals(instResult.getProcessStatus())) {
            buildFailResult(instResult);
            return new BaseResult(false, "结果状态不为处理完成");
        }

        //订单状态跃迁
        switch (instResult.getStatus()) {
            case SUCCESSFUL:
                instOrder.setStatus(InstOrderStatus.SUCCESSFUL);
                break;
            case FAILURE:
                instOrder.setStatus(InstOrderStatus.FAILURE);
                break;
            default:
                break;
        }
        try {
            boolean resultProcess = updateInstOrderStatus(instOrder);
            if (resultProcess) {
                instResult.setOperateStatus(InstResultOperateStatus.SUCCESSFUL);
                payInstOrderResultService.updateOperateStatusById(instResult.getOperateStatus(),
                    instResult.getResultId());
            } else {
                buildFailResult(instResult);
                return new BaseResult(false, "机构订单状态跃迁异常");
            }
        } catch (Exception e) {
        	payMonitorLogService.insertPayMonitorLog(new PayMonitorLog(instOrder.getInstOrderNo(),
                    MonitorItem.STATE_TRASICTION_EXCEPTION, "状态跃迁异常" + instOrder.getInstOrderNo(), e));
                buildFailResult(instResult);
            return new BaseResult(false, "机构订单状态跃迁异常");
        }

        //对于拆分订单需要判断是否所有订单都成功
        boolean isCompleteSuccess = false;
        try {
            if (payInstOrderService.isCompleteSuccess(instOrder)) {
                isCompleteSuccess = true;
            }
        } catch (WrongStateException e) {
            logger.error("订单[" + instOrder.getInstOrderNo() + "]拆分躲避状态不一致", e);
            buildFailResult(instResult);
            return new BaseResult(false, "拆分多次状态不一致");
        }

        if (isCompleteSuccess) {
            switch (instResult.getStatus()) {
                case SUCCESSFUL:
                	channelPayOrder.setStatus(PayOrderStatus.SUCCESSFUL);
                    break;
                case FAILURE:
                	channelPayOrder.setStatus(PayOrderStatus.FAILURE);
                    break;
                default:
                    break;
            }
            try {
            	channelPayOrderService.updateCmfOrderStatus(channelPayOrder, PayOrderStatus.IN_PROCESS);
            } catch (Exception e) {
                buildFailResult(instResult);
                return new BaseResult(false, "PayChannelOrder订单状态跃迁异常");
            }
        }


        return new BaseResult(true, "处理成功");

    }


   

    private boolean updateInstOrderStatus(final PayInstOrder instOrder) {

        //不是最终状态,不更新
        if (InstOrderStatus.IN_PROCESS.equals(instOrder.getStatus())) {
            return false;
        }
        PayInstOrder dbInstOrder = payInstOrderService.loadById(instOrder.getInstOrderId());
        //原订单是处理中,更新订单状态
        //如果原订单不为处理中并且与需要更新的状态不一致,则异常
        if (InstOrderStatus.IN_PROCESS.equals(dbInstOrder.getStatus())) {
         payInstOrderService.updateStatusById(instOrder.getStatus(),
                instOrder.getInstOrderId());
            return true;
        } else if (!dbInstOrder.getStatus().equals(instOrder.getStatus())) {
            logger.error("机构订单[" + instOrder.getInstOrderNo() + "]状态跃迁异常,原状态:"
                         + dbInstOrder.getStatus() + ",新状态" + instOrder.getStatus());
            throw new AppRuntimeException("机构订单[" + instOrder.getInstOrderNo() + "],原状态:"
                                          + dbInstOrder.getStatus() + ",新状态"
                                          + instOrder.getStatus());
        }
        return false;
	}

	/**
     * 针对出/入/退特殊逻辑处理
     * @param instOrder
     * @param instOrderResult
     */
    private void specialProcess(PayInstOrder instOrder, PayInstOrderResult instOrderResult) {
        if (BizType.REFUND.equals(instOrder.getBizType())
            && InstOrderProcessStatus.SUCCESS.equals(instOrderResult.getProcessStatus())) {
            refundProcessService.processRefund(instOrder, instOrderResult);
        }
        return;
    }

    /**
     * 补全机构订单号 
     * @param result
     */
    private void fixInstOrderNo(ChannelFundResult result) {
        //若不需要补全，直接返回.
        if (null == result || result.getFundChannelCode() == null || result.getApiType() == null) {
            return;
        }
        TmFundChannel channel = fundChannelRouter.routerFundChannel(result.getFundChannelCode(),
            result.getApiType());
        if (null == channel || channel.getApiList() == null || channel.getApiList().size() == 0) {
            return;
        }

        //查找订单号.
        String destInstOrderNo = findInstOrderNo(channel.getApiList().get(0), result.getInstOrderNo());

        logger.info("[回调通知]订单号补全, 原instOrderNo=" + result.getInstOrderNo() + ", 补后订单号="
                    + destInstOrderNo);

        result.setInstOrderNo(destInstOrderNo);
    }

    /**
     * 匹配机构订单号
     * @param api
     * @param sourceInstOrderNo
     * @return
     */
    private String findInstOrderNo(TmFundChannelApi api, String sourceInstOrderNo) {
        //不需要替换instOrderNo
        if (api.getApiNoMode() == null ) {
            return sourceInstOrderNo;
        }

        //查询订单号是否存在
        PayInstOrder dbOrder = payInstOrderService.loadByInstOrderNo(sourceInstOrderNo);
        if (null != dbOrder) {
            return sourceInstOrderNo;
        }

        //5天之内的订单
        String destInstOrderNo = null;
        List<Segment> segments = instOrderNoGenerator.getSegmentList(api.getApiNoMode());
        for (int i = 0; i < 5; i++) {
            Date date = DateUtil.addDays(new Date(), -i);
            destInstOrderNo = instOrderNoGenerator.populateInstOrderNo(segments, date,
                sourceInstOrderNo);
            dbOrder = payInstOrderService.loadByInstOrderNo(destInstOrderNo);
            if (null != dbOrder) {
                return destInstOrderNo;
            }
        }

        return sourceInstOrderNo;
    }

   

    /**
     * 针对渠道返回的结果以及接口定义保存渠道返回的扩展信息
     * 注:无磁无密冲正时在没有明确结果的情况下也需要保存
     * @param instOrder
     * @param instOrderResult
     */
    private void saveExtension(PayInstOrder instOrder, PayInstOrderResult instOrderResult) {

        if (CollectionUtils.isNotEmpty(instOrder.getFundChannelApi().getParamList())
            && instOrderResult.getExtension() != null) {
            boolean needStore = false;
            //按照接口定义保存数据
            for (TmFundChannelApiParam apiParam : instOrder.getFundChannelApi().getParamList()) {
                if (ApiParamScene.CHANNEL_RETUEN.equals(apiParam.getScene())
                    && StringUtil.isNotEmpty(instOrderResult.getExtension().get(
                        apiParam.getParamName()))) {
                    instOrder.getExtension().put(apiParam.getParamName(),
                        instOrderResult.getExtension().get(apiParam.getParamName()));
                    needStore = true;
                }
            }
            //如果有需要保存,则更新扩展信息
            if (needStore) {
            	payInstOrderService.storeExtension(instOrder);
            }
        }
    }

    /**
     * 构造错误结果信息
     * @param instResult
     */
    private PayInstOrderResult buildFailResult(PayInstOrderResult instResult) {
        instResult.setProcessStatus(InstOrderProcessStatus.UNKNOW_EXCEPTION);
        instResult.setStatus(InstOrderResultStatus.AWAITING);
        return instResult;
    }


    /**
     * 装载返回结果信息
     * @param result
     * @return
     */
    private ChannelNotifyResult buildReturnInfo(InstBaseResult result) {
        if (result != null && result.getStatus() != null) {
            switch (result.getStatus()) {
                case SUCCESSFUL:
                    return buildReturnInfo(true, BasicConstant.SUCCESS.toString(), "处理成功",
                        InstOrderStatus.SUCCESSFUL);
                case FAILURE:
                    return buildReturnInfo(true, BasicConstant.SUCCESS.toString(), "处理成功",
                        InstOrderStatus.FAILURE);
                default:
                    return buildReturnInfo(true, BasicConstant.FAILURE.toString(), "失败",
                        InstOrderStatus.IN_PROCESS);

            }
        }
        return buildReturnInfo(true, BasicConstant.FAILURE.toString(), "失败",
            InstOrderStatus.IN_PROCESS);
    }

    private ChannelNotifyResult buildReturnInfo(boolean succ, String returnCode,
                                                String returnMessage,
                                                InstOrderStatus status) {
        ChannelNotifyResult returnInfo = new ChannelNotifyResult();
        returnInfo.setSuccess(succ);
        returnInfo.setReturnCode(returnCode);
        returnInfo.setResultMessage(returnMessage);
        returnInfo.setInstOrderStatus(status);
        return returnInfo;
    }

}

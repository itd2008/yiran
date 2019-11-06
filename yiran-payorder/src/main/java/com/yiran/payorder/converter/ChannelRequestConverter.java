package com.yiran.payorder.converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.domain.TmFundChannelApiParam;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.ManualRefundType;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.paychannel.service.ITmChannelTransInfoService;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.domain.FundoutRequest;
import com.yiran.payorder.domain.InstFundoutOrder;
import com.yiran.payorder.domain.InstRefundOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.RefundRequest;
import com.yiran.payorder.enums.ApiParamScene;
import com.yiran.payorder.enums.ChannelRequestExtensionMapping;
import com.yiran.payorder.request.ChannelFundRequest;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.request.QueryRequest;
import com.yiran.payorder.service.ICombineCallbackService;
import com.yiran.payorder.utils.PropertyValueUtil;


/**
 * <p>资金渠道请求转换器</p>
 *
 */
public class ChannelRequestConverter implements BasicConstant {
	private static final Logger            logger = LoggerFactory.getLogger(ChannelRequestConverter.class);
    /**
     * 单笔资金类：根据不同API类型转换机构订单为渠道资金请求对象
     *
     * @param instOrder 机构订单
     * @return 渠道资金请求对象
     */
    public static ChannelFundRequest covert(PayInstOrder instOrder,
                                            ICombineCallbackService callbackService,
                                            ITmChannelTransInfoService channelTransInfoService) {
        ChannelFundRequest request = null;
        FundChannelApiType apiType = FundChannelApiType.getByCode(instOrder.getFundChannelApi().getApiType());
        switch (apiType) {
            case SIGN:
            case PRE_AUTH_DONE:
            case DEBIT:
                switch (instOrder.getPayMode()) {
                    case BANKSIGNQUICKPAY:
                    case QUICKPAY:
                    case TRUSTCOLLECT:
                        request = new ChannelFundRequest();
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.OPEN_ID.key))) {
                        	request.getExtension().put(ExtensionKey.OPEN_ID.key, 
                        			instOrder.getExtension().get(ExtensionKey.OPEN_ID.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.ORDER_NAME.key))) {
                        	request.getExtension().put(ExtensionKey.ORDER_NAME.key, 
                        			instOrder.getExtension().get(ExtensionKey.ORDER_NAME.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.ACCOUNT_NAME.key))) {
                        	request.getExtension().put(ExtensionKey.ACCOUNT_NAME.key, 
                        			instOrder.getExtension().get(ExtensionKey.ACCOUNT_NAME.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.CARD_NO.key))) {
                        	request.getExtension().put(ExtensionKey.CARD_NO.key, 
                        			instOrder.getExtension().get(ExtensionKey.CARD_NO.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.ID_NO.key))) {
                        	request.getExtension().put(ExtensionKey.ID_NO.key, 
                        			instOrder.getExtension().get(ExtensionKey.ID_NO.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.MOBILENO.key))) {
                        	request.getExtension().put(ExtensionKey.MOBILENO.key, 
                        			instOrder.getExtension().get(ExtensionKey.MOBILENO.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.MEMBER_ID.key))) {
                        	request.getExtension().put(ExtensionKey.MEMBER_ID.key, 
                        			instOrder.getExtension().get(ExtensionKey.MEMBER_ID.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.SIGN_NO.key))) {
                        	request.getExtension().put(ExtensionKey.SIGN_NO.key, 
                        			instOrder.getExtension().get(ExtensionKey.SIGN_NO.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.VALID_DATE.key))) {
                        	request.getExtension().put(ExtensionKey.VALID_DATE.key, 
                        			instOrder.getExtension().get(ExtensionKey.VALID_DATE.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.CVV2.key))) {
                        	request.getExtension().put(ExtensionKey.CVV2.key, 
                        			instOrder.getExtension().get(ExtensionKey.CVV2.key));
						}
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.VERIFICATION_CODE.key))) {
                        	request.getExtension().put(ExtensionKey.VERIFICATION_CODE.key, 
                        			instOrder.getExtension().get(ExtensionKey.VERIFICATION_CODE.key));
						}
                        
                        //Alipay参数
                        //=========================================================================================
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.BODY.key))) {
                        	request.getExtension().put(ExtensionKey.BODY.key, 
                        			instOrder.getExtension().get(ExtensionKey.BODY.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.TIME_EXPIRE.key))) {
                        	request.getExtension().put(ExtensionKey.TIME_EXPIRE.key, 
                        			instOrder.getExtension().get(ExtensionKey.TIME_EXPIRE.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.GOODS_DETAIL.key))) {
                        	request.getExtension().put(ExtensionKey.GOODS_DETAIL.key, 
                        			instOrder.getExtension().get(ExtensionKey.GOODS_DETAIL.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.PASSBACK_PARAMS.key))) {
                        	request.getExtension().put(ExtensionKey.PASSBACK_PARAMS.key, 
                        			instOrder.getExtension().get(ExtensionKey.PASSBACK_PARAMS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.EXTEND_PARAMS.key))) {
                        	request.getExtension().put(ExtensionKey.EXTEND_PARAMS.key, 
                        			instOrder.getExtension().get(ExtensionKey.EXTEND_PARAMS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.GOODS_TYPE.key))) {
                        	request.getExtension().put(ExtensionKey.GOODS_TYPE.key, 
                        			instOrder.getExtension().get(ExtensionKey.GOODS_TYPE.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.TIMEOUT_EXPRESS.key))) {
                        	request.getExtension().put(ExtensionKey.TIMEOUT_EXPRESS.key, 
                        			instOrder.getExtension().get(ExtensionKey.TIMEOUT_EXPRESS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.PROMO_PARAMS.key))) {
                        	request.getExtension().put(ExtensionKey.PROMO_PARAMS.key, 
                        			instOrder.getExtension().get(ExtensionKey.PROMO_PARAMS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.ROYALTY_INFO.key))) {
                        	request.getExtension().put(ExtensionKey.ROYALTY_INFO.key, 
                        			instOrder.getExtension().get(ExtensionKey.ROYALTY_INFO.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.SUB_MERCHANT.key))) {
                        	request.getExtension().put(ExtensionKey.SUB_MERCHANT.key, 
                        			instOrder.getExtension().get(ExtensionKey.SUB_MERCHANT.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.MERCHANT_ORDER_NO.key))) {
                        	request.getExtension().put(ExtensionKey.MERCHANT_ORDER_NO.key, 
                        			instOrder.getExtension().get(ExtensionKey.MERCHANT_ORDER_NO.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.ENABLE_PAY_CHANNELS.key))) {
                        	request.getExtension().put(ExtensionKey.ENABLE_PAY_CHANNELS.key, 
                        			instOrder.getExtension().get(ExtensionKey.ENABLE_PAY_CHANNELS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.STORE_ID.key))) {
                        	request.getExtension().put(ExtensionKey.STORE_ID.key, 
                        			instOrder.getExtension().get(ExtensionKey.STORE_ID.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.DISABLE_PAY_CHANNELS.key))) {
                        	request.getExtension().put(ExtensionKey.DISABLE_PAY_CHANNELS.key, 
                        			instOrder.getExtension().get(ExtensionKey.DISABLE_PAY_CHANNELS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.QR_PAY_MODE.key))) {
                        	request.getExtension().put(ExtensionKey.QR_PAY_MODE.key, 
                        			instOrder.getExtension().get(ExtensionKey.QR_PAY_MODE.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.QRCODE_WIDTH.key))) {
                        	request.getExtension().put(ExtensionKey.QRCODE_WIDTH.key, 
                        			instOrder.getExtension().get(ExtensionKey.QRCODE_WIDTH.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.SETTLE_INFO.key))) {
                        	request.getExtension().put(ExtensionKey.SETTLE_INFO.key, 
                        			instOrder.getExtension().get(ExtensionKey.SETTLE_INFO.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.INVOICE_INFO.key))) {
                        	request.getExtension().put(ExtensionKey.INVOICE_INFO.key, 
                        			instOrder.getExtension().get(ExtensionKey.INVOICE_INFO.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.AGREEMENT_SIGN_PARAMS.key))) {
                        	request.getExtension().put(ExtensionKey.AGREEMENT_SIGN_PARAMS.key, 
                        			instOrder.getExtension().get(ExtensionKey.AGREEMENT_SIGN_PARAMS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.INTEGRATION_TYPE.key))) {
                        	request.getExtension().put(ExtensionKey.INTEGRATION_TYPE.key, 
                        			instOrder.getExtension().get(ExtensionKey.INTEGRATION_TYPE.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.REQUEST_FROM_URL.key))) {
                        	request.getExtension().put(ExtensionKey.REQUEST_FROM_URL.key, 
                        			instOrder.getExtension().get(ExtensionKey.REQUEST_FROM_URL.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.BUSINESS_PARAMS.key))) {
                        	request.getExtension().put(ExtensionKey.BUSINESS_PARAMS.key, 
                        			instOrder.getExtension().get(ExtensionKey.BUSINESS_PARAMS.key));
						}
                        
                        if (StringUtil.isNotBlank(instOrder.getExtension().get(ExtensionKey.EXT_USER_INFO.key))) {
                        	request.getExtension().put(ExtensionKey.EXT_USER_INFO.key, 
                        			instOrder.getExtension().get(ExtensionKey.EXT_USER_INFO.key));
						}
                      //=========================================================================================
                        break;
                    default:
                        request = new ChannelFundRequest();
                        break;
                }
                break;
            case SINGLE_REFUND:
            case MANUAL_REFUND://TODO 需结算后台统一使用接口实现
                request = new RefundRequest();
                buildRefund((RefundRequest) request, (InstRefundOrder) instOrder);
                //设置机构和请求号信息
                for (ChannelRequestExtensionMapping mapping : ChannelRequestExtensionMapping
                    .getMappingSet(RefundRequest.class)) {
                    PropertyValueUtil.setValue(request, mapping,
                        instOrder.getExtension().get(mapping.getExtensionKey()));
                }
                break;
            case SINGLE_PAY:
                request = new FundoutRequest();
                buildFundout((FundoutRequest) request, (InstFundoutOrder) instOrder);
                //设置机构和请求号信息
                for (ChannelRequestExtensionMapping mapping : ChannelRequestExtensionMapping
                    .getMappingSet(FundoutRequest.class)) {
                    PropertyValueUtil.setValue(request, mapping,
                        instOrder.getExtension().get(mapping.getExtensionKey()));
                }
                break;
            default:
                request = new ChannelFundRequest();
                break;
        }

        //构建公共信息
        buildCommonReq(instOrder, request, callbackService);

        //获取扩展信息
        TmFundChannelApi channelApiInfo = instOrder.getFundChannelApi();
        Map<String,String> map =filterExtKey(instOrder.getExtension(), channelApiInfo.getApiCode(),
                instOrder.getFundChannelCode(), channelApiInfo.getParamList(),
                channelTransInfoService);
        request.getExtension().putAll(map);

        return request;
    }

    /**
     *转换退款特色属性：机构退款订单为渠道退款请求
     *
     * @param request 退款请求
     * @param order 退款机构订单
     */
    private static void buildRefund(RefundRequest request, InstRefundOrder order) {
        request.setOriginalOrderAmount(order.getFundinRealAmount());
        request.setOrignalInstOrderNo(order.getFundinOrderNo());
        request.setOrignalInstSeqNo(order.getFundinInstSeqNo());
        request.setOrignalOrderSettleTime(DateUtil.parseDateLongFormat(order.getFundinDate()));
        request.setOrignalOrderSubmitTime(DateUtil.parseDateLongFormat(order.getFundinDate()));
        if (FundChannelApiType.MANUAL_REFUND.equals(order.getFundChannelApi().getApiType())) {
            //手工退款需要指明转手工原因   
            if (StringUtil.isNotEmpty(ManualRefundType.replaceMessage(order.getMemo()))) {
                request.getExtension().put(ExtensionKey.MEMO.key,
                    ManualRefundType.replaceMessage(order.getMemo()));
            } else {
                request.getExtension().put(ExtensionKey.MEMO.key,
                    ManualRefundType.NON_SUPPORT_AUTO_REFUND.getMessage());
            }
        }

    }

    /**
     *转换出款特色属性：机构出款订单为渠道出款请求
     *
     * @param request 出款请求
     * @param order 出款机构订单
     */
    private static void buildFundout(FundoutRequest request, InstFundoutOrder order) {
        //会员卡相关
        request.setAccountName(order.getAccountName());
        request.setCardType(order.getCardType());
        request.setAccountNo(order.getAccountNo());//卡号
        request.setAccountType(order.getCompanyOrPersonal());
        request.setAgreementNo(order.getAgreementNo());
        //银行机构相关
        request.setBankCode(order.getBankCode());
        request.setBankName(order.getBankName());
        request.setBankBranch(order.getBankBranch());
        request.setBankBranchCode(order.getBankBranchCode());
        request.setBankProvince(order.getBankProvince());
        request.setBankCity(order.getBankCity());
        request.setAreaCode(order.getAreaCode());
        request.setPurpose(order.getPurpose());

        request.setProductCode(order.getProductCode());
        request.setPaymentCode(order.getPaymentCode());
        Boolean isSameBank = order.getInstCode().equalsIgnoreCase(
            order.getFundChannel().getInstCode());
        request.setInnerBank(isSameBank);//根据收单机构和目标机构比较判断是否是同行
    }

    /**
     *  过滤指定渠道需要的请求信息
     *
     * @param orgiMap 机构订单的扩展信息
     * @param apiCode api编码
     * @param filterList 过滤列表
     * @return 渠道请求特定扩展信息
     */
    private static Map<String, String> filterExtKey(Map<String, String> orgiMap, String apiCode,
                                                    String fundChannelCode,
                                                    List<TmFundChannelApiParam> filterList,
                                                    ITmChannelTransInfoService channelTransInfoService) {
        Map<String, String> targetMap = new ConcurrentHashMap<String, String>();
        if (CollectionUtils.isEmpty(filterList)) {
            return targetMap;
        }
        //遍历渠道接口参数
        for (Iterator<TmFundChannelApiParam> iterator = filterList.iterator(); iterator.hasNext();) {
            TmFundChannelApiParam apiParam = iterator.next();
            if (apiParam.getEnable().equals(YesNo.YES.getCode()) && apiParam.getApiCode().equals(apiCode)
                && apiParam.getScene().equals(ApiParamScene.REQUEST_CHANNEL.getCode())) {
            	//从请求放传入
                if (YesNo.YES.equals(apiParam.getIsChannelTrans())) {
                    Map<String, String> channelTransInfo = channelTransInfoService.getTransInfo(
                        fundChannelCode, apiParam.getTransCode());
                    if (channelTransInfo != null) {
                    	exchange(channelTransInfo, targetMap, apiParam.getParamName());
                    }
                    continue;
                }
                //从机构订单扩展信息中取
                if (orgiMap.containsKey(apiParam.getParamName())) {
                	exchange(orgiMap, targetMap, apiParam.getParamName());
                }
            }
        }
        return targetMap;
    }

    private static void exchange(Map<String, String> orgiMap,Map<String, String> targetMap,String key){
    	String value = orgiMap.get(key)==null?"":orgiMap.get(key);
    	try {
   		 	targetMap.put(key, value);
		} catch (Exception e) {
			throw new IllegalArgumentException(key+"="+value+"参数异常：", e);
		}
    }
    /**
     * 根据机构订单组装渠道请求通用信息
     * @param instOrder
     * @param request
     */
    private static void buildCommonReq(PayInstOrder instOrder, ChannelRequest request,
                                       ICombineCallbackService callbackService) {
        request.setInstOrderNo(instOrder.getInstOrderNo());
        if (request instanceof ChannelFundRequest) {
            ((ChannelFundRequest) request).setAmount(instOrder.getAmount());
            ((ChannelFundRequest) request).setTargetInstCode(instOrder.getInstCode());//资金渠道编码
            ((ChannelFundRequest) request).setInstOrderSubmitTime(instOrder.getGmtBookingSubmit());
        }

        request.setInstCode(instOrder.getFundChannel().getInstCode());
        request.setFundChannelCode(instOrder.getFundChannel().getFundChannelCode());
        request.setApiType(FundChannelApiType.getByCode(instOrder.getFundChannelApi().getApiType()));
        request.setApiUrl(instOrder.getFundChannelApi().getApiUri());
        if (callbackService != null) {
            if (request.getApiType().equals(FundChannelApiType.SINGLE_REFUND)){
                request.setCallbackServerUrl(callbackService.getCallBackUrl(instOrder.getFundChannel(),
                        SERVER_URL, FundChannelApiType.REFUND_NOTIFY));
                request.setCallbackPageUrl(callbackService.getCallBackUrl(instOrder.getFundChannel(),
                        PAGE_URL, FundChannelApiType.REFUND_NOTIFY));
            }else if (request.getApiType().equals(FundChannelApiType.DEBIT)){
                request.setCallbackServerUrl(callbackService.getCallBackUrl(instOrder.getFundChannel(),
                        SERVER_URL, FundChannelApiType.NOTIFY));
                request.setCallbackPageUrl(callbackService.getCallBackUrl(instOrder.getFundChannel(),
                        PAGE_URL, FundChannelApiType.NOTIFY));
            }else {
                request.setCallbackServerUrl(callbackService.getCallBackUrl(instOrder.getFundChannel(),
                        SERVER_URL, FundChannelApiType.VERIFY_SIGN));
                request.setCallbackPageUrl(callbackService.getCallBackUrl(instOrder.getFundChannel(),
                        PAGE_URL, FundChannelApiType.VERIFY_SIGN));
            }
            //end
        }
    }

    
    

   
    
    

    
    

    /**
     * 查询类： 根据不同API类型转换机构订单为渠道资金请求对象
     * @param instOrder 机构订单
     * @return
     */
    public static QueryRequest covertQuery(PayInstOrder instOrder,
                                           ITmChannelTransInfoService channelTransInfoService) {
        QueryRequest request = new QueryRequest();
        request.setAmount(instOrder.getAmount());
        request.setQueryTime(new Date());
        request.setInstOrderSubmitTime(instOrder.getGmtCreate());
        if (instOrder instanceof InstRefundOrder) {
        	InstRefundOrder instRefundOrder = (InstRefundOrder) instOrder;
            request.setOriginalInstOrderNo(instRefundOrder.getFundinOrderNo());
            request.setOriginalInstOrderAmount(instRefundOrder.getFundinRealAmount());
            request.setOriginalInstSeqNo(instRefundOrder.getFundinInstSeqNo());
            request.setOriginalInstOrderSettleTime(DateUtil
                .parseDateLongFormat(instRefundOrder.getFundinDate()));
            request.setOriginalInstOrderSubmitTime(DateUtil
                .parseDateLongFormat(instRefundOrder.getFundinDate()));
        }

        //构建公共信息
        buildCommonReq(instOrder, request, null);

        //获取扩展信息
        TmFundChannelApi channelApiInfo = instOrder.getFundChannelApi();
        Map<String,String> map = filterExtKey(instOrder.getExtension(), channelApiInfo.getApiCode(),
                instOrder.getFundChannelCode(), channelApiInfo.getParamList(), channelTransInfoService);
        request.setExtension(map);
        return request;
    }
}

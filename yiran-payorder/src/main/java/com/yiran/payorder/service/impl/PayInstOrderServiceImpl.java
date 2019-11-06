package com.yiran.payorder.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.yiran.payorder.mapper.PayInstOrderMapper;
import com.yiran.payorder.mapper.PayInstOrderResultMapper;
import com.yiran.payorder.converter.InstOrderConverter;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.InstOrderVO;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domaindo.ChannelPayOrderDO;
import com.yiran.payorder.domaindo.PayInstOrderDO;
import com.yiran.payorder.domaindo.PayInstOrderResultDO;
import com.yiran.payorder.enums.CommunicateStatus;
import com.yiran.payorder.enums.OrderFlag;
import com.yiran.payorder.enums.OrderRiskStatus;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.exception.WrongStateException;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.common.support.Convert;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ErrorCode;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.enums.InstOrderStatus;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.paychannel.service.ITmFundChannelApiService;
import com.yiran.paychannel.service.ITmFundChannelService;
import com.yiran.paychannel.utils.CommonConverter;

/**
 * 提交机构订单 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class PayInstOrderServiceImpl implements IPayInstOrderService 
{
	@Autowired
	private PayInstOrderMapper payInstOrderMapper;
	@Autowired
	private ITmFundChannelService tmFundChannelService;
	@Autowired
	private ITmFundChannelApiService tmFundChannelApiService;
	@Autowired
	private IChannelPayOrderService channelPayOrderService;
	
	@Autowired
	private PayInstOrderResultMapper payInstOrderResultMapper;

	/**
     * 查询提交机构订单信息
     * 
     * @param instOrderId 提交机构订单ID
     * @return 提交机构订单信息
     */
    @Override
	public PayInstOrderDO selectPayInstOrderById(Integer instOrderId)
	{
	    return payInstOrderMapper.selectPayInstOrderById(instOrderId);
	}
	
	/**
     * 查询提交机构订单列表
     * 
     * @param payInstOrder 提交机构订单信息
     * @return 提交机构订单集合
     */
	@Override
	public List<PayInstOrderDO> selectPayInstOrderList(PayInstOrderDO payInstOrder)
	{
	    return payInstOrderMapper.selectPayInstOrderList(payInstOrder);
	}
	
    /**
     * 新增提交机构订单
     * 
     * @param payInstOrder 提交机构订单信息
     * @return 结果
     */
	@Override
	public int insertPayInstOrder(PayInstOrderDO payInstOrder)
	{
	    return payInstOrderMapper.insertPayInstOrder(payInstOrder);
	}
	
	/**
     * 修改提交机构订单
     * 
     * @param payInstOrder 提交机构订单信息
     * @return 结果
     */
	@Override
	public int updatePayInstOrder(PayInstOrderDO payInstOrder)
	{
	    return payInstOrderMapper.updatePayInstOrder(payInstOrder);
	}

	/**
     * 删除提交机构订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deletePayInstOrderByIds(String ids)
	{
		return payInstOrderMapper.deletePayInstOrderByIds(Convert.toStrArray(ids));
	}

	@Override
	public PayInstOrder loadById(Integer instOrderId) {
		PayInstOrderDO instOrderDO = payInstOrderMapper.selectPayInstOrderById(instOrderId);
        return buildInstOrder(instOrderDO);
	}
	
	/**
     * 组装机构订单领域对象
     * @param instOrderDO
     * @return
     */
    private PayInstOrder buildInstOrder(PayInstOrderDO instOrderDO) {
        if (instOrderDO == null) {
            return null;
        }
        PayInstOrder instOrder = null;
        BizType bizType = BizType.getByCode(instOrderDO.getOrderType());
        switch (bizType) {
            case FUNDIN:
                instOrder = InstOrderConverter.convert(instOrderDO);
                break;
            case FUNDOUT:
                instOrder = InstOrderConverter.convert(instOrderDO);
                break;
            case REFUND:
                instOrder = InstOrderConverter.convert(instOrderDO);
                break;
            default:
                throw new RuntimeException("不支持此业务类型" + bizType.getMessage());
        }

        TmFundChannel channel = tmFundChannelService.getFundChannel(instOrderDO.getFundChannelCode());
        instOrder.setFundChannel(channel);
        TmFundChannelApi api = tmFundChannelApiService.getFundChannelApi(instOrderDO.getFundChannelApi());
        instOrder.setFundChannelApi(api);
        return instOrder;
    }

	@Override
	public PayInstOrder loadByInstOrderNo(String instOrderNo) {
		PayInstOrderDO instOrderDO = payInstOrderMapper.loadByInstOrderNo(instOrderNo);
		return buildInstOrder(instOrderDO);
	}

	@Override
	public int updateRiskStatus(OrderRiskStatus riskStatus, Integer instOrderId) {
		
		return payInstOrderMapper.updateRiskStatus(riskStatus.getCode(), instOrderId);
	}

	@Override
	public void store(List<PayInstOrder> instOrderList) {
		if (CollectionUtils.isEmpty(instOrderList)) {
            return;
        }
		
		for (PayInstOrder payInstOrder : instOrderList) {
			PayInstOrderDO payInstOrderDO = InstOrderConverter.convert(payInstOrder);
			int flag = insertPayInstOrder(payInstOrderDO);
			payInstOrder.setInstOrderId(payInstOrderDO.getInstOrderId());
		}
		
	}

	@Override
	public int updateCommunicateStatusById(CommunicateStatus communicateStatus, Integer instOrderId) {
		
		return payInstOrderMapper.updateCommunicateStatusById(communicateStatus.getCode(),instOrderId);
	}

	@Override
	public void storeExtension(PayInstOrder instOrder) {
		 if (instOrder.getExtension() != null) {
			 payInstOrderMapper.updateExtensionById(CommonConverter.convertToDb(instOrder.getExtension()),
					 instOrder.getInstOrderId());
	        }
	}

	@Override
	public int updateStatusById(InstOrderStatus status, Integer instOrderId) {
		return payInstOrderMapper.updateStatusById(status.getCode(),instOrderId);
	}

	@Override
	public boolean isCompleteSuccess(PayInstOrder instOrder) throws WrongStateException {


        if (!YesNo.YES.equals(instOrder.getIsSplit())) {
            return true;
        }

        if (!isReturnStatus(instOrder.getStatus())) {
            throw new WrongStateException(ErrorCode.WRONG_STATE_EXCEPTION,
                "当前订单状态不对,不为成功或失败[" + instOrder.getInstOrderNo() + "]");
        }

        boolean result = true;
        List<PayInstOrder> list = loadByPaySeqNo(instOrder.getPaySeqNo());

        Money sumAmount = new Money(instOrder.getAmount().getAmount());

        for (PayInstOrder instOrderTemp : list) {
            if (!instOrder.getInstOrderNo().equals(instOrderTemp.getInstOrderNo())
                && !instOrder.getStatus().equals(instOrderTemp.getStatus())) {
                if (isLastStatus(instOrderTemp.getStatus()) && isLastStatus(instOrder.getStatus())) {
                    throw new WrongStateException(ErrorCode.WRONG_STATE_EXCEPTION,
                        "同一批订单状态不一致,当前订单[" + instOrder.getInstOrderNo() + "]");
                } else {
                    result = false;
                }
            }
            if (!instOrder.getInstOrderNo().equals(instOrderTemp.getInstOrderNo())) {
                sumAmount = sumAmount.add(instOrderTemp.getAmount());
            }
        }
        if (result) {
        	ChannelPayOrder channelPayOrder = channelPayOrderService.loadByPaySeqNo(instOrder.getPaySeqNo());
            if (!sumAmount.equals(channelPayOrder.getAmount())) {
                result = false;
            }
        }

        return result;
    
	}
	 public List<PayInstOrder> loadByPaySeqNo(String paySeqNo) {
		List<PayInstOrder> instOrderList = new ArrayList<PayInstOrder>();
        List<PayInstOrderDO> instOrderDOList = payInstOrderMapper.loadByPaySeq(paySeqNo);
        if (CollectionUtils.isEmpty(instOrderDOList)) {
            return instOrderList;
        }

        for (PayInstOrderDO instOrderDO : instOrderDOList) {
            instOrderList.add(buildInstOrder(instOrderDO));
        }

        return instOrderList;
	}

	private boolean isReturnStatus(InstOrderStatus status) {
	        return (InstOrderStatus.SUCCESSFUL.equals(status) || InstOrderStatus.FAILURE.equals(status) || InstOrderStatus.RISK
	            .equals(status));
	    }

	    private boolean isLastStatus(InstOrderStatus status) {
	        return (InstOrderStatus.SUCCESSFUL.equals(status) || InstOrderStatus.FAILURE.equals(status));
	    }

		@Override
		public int updateMemoById(String memo, Integer instOrderId) {
			return payInstOrderMapper.updateMemoById(memo,instOrderId);
		}

		@Override
		public int updateChannelInfoById(String fundChannelCode, String apiCode, Integer instOrderId) {
			return payInstOrderMapper.updateChannelInfoById(fundChannelCode,apiCode,instOrderId);
		}

		@Override
		public InstOrderVO queryFundoutOrder(String instOrderNo) {
	        PayInstOrderDO payInstOrderDO = payInstOrderMapper.loadByInstOrderNo(instOrderNo);
			return this.convertInstOrderVO(payInstOrderDO);
		}
	
		private InstOrderVO convertInstOrderVO(PayInstOrderDO payInstOrderDO) {
			InstOrderVO vo = new InstOrderVO();
	        vo.setAmount(new BigDecimal(String.valueOf(payInstOrderDO.getAmount())));
	        // 归档批次号
	        Object archiveBatchNo = payInstOrderDO.getArchiveBatchId();
	        if (null != archiveBatchNo && !StringUtil.isEmpty(String.valueOf(archiveBatchNo))) {
	            vo.setArchiveBatchNo(Long.parseLong(String.valueOf(archiveBatchNo)));
	        }
	        vo.setFundChannelCode(String.valueOf(payInstOrderDO.getFundChannelCode()));
	        vo.setFundChannelApi(String.valueOf(payInstOrderDO.getFundChannelApi()));
	        vo.setArchiveStatus(String.valueOf(payInstOrderDO.getCommunicateStatus()));
	        vo.setProductCode(String.valueOf(payInstOrderDO.getProductCode()));
	        vo.setPaymentCode(String.valueOf(payInstOrderDO.getPaymentCode()));
	        vo.setInstCode(String.valueOf(payInstOrderDO.getInstCode()));
	        String gmtCreate = String.valueOf(payInstOrderDO.getGmtCreate());
	        if (!StringUtil.isEmpty(gmtCreate)) {
	            vo.setGmtCreate(DateUtil.parseDateNewFormat(gmtCreate));
	        }
	        String gmtBookingSubmit = String.valueOf(payInstOrderDO.getGmtBookingSubmit());
	        if (!StringUtil.isEmpty(gmtBookingSubmit)) {
	            vo.setGmtBookingSubmit(DateUtil.parseDateNewFormat(gmtBookingSubmit));
	        }
	        vo.setInstOrderNo(String.valueOf(payInstOrderDO.getInstOrderNo()));
	        if (payInstOrderDO.getInstOrderId() != null) {
	            vo.setInstOrderId(payInstOrderDO.getInstOrderId());
	        }
	        Object archiveTemplateId = payInstOrderDO.getArchiveTemplateId();
	        if (null != archiveTemplateId && !StringUtil.isEmpty(String.valueOf(archiveTemplateId))) {
	            vo.setArchiveTemplateId(Long.parseLong(String.valueOf(archiveTemplateId)));
	        }
	        vo.setInstStatus(String.valueOf(payInstOrderDO.getStatus()));

	        Map<String, String> extension = CommonConverter.convertFromDb((String) payInstOrderDO.getExtension());
	        String geteOrderNo = extension.get(ExtensionKey.GATE_ORDER_NO.key);

	        vo.setGateOrderNo(geteOrderNo);
	        vo.setMemo(String.valueOf(payInstOrderDO.getMemo()));
	        return vo;
		}

		@Override
		public List<PayInstOrder> loadByInstOrderNos(List<String> instOrderNos) {
			List<PayInstOrder> instOrderList = new ArrayList<PayInstOrder>();
	        List<PayInstOrderDO> instOrderDOList = payInstOrderMapper.loadByInstOrderNos(instOrderNos);
	        if (CollectionUtils.isEmpty(instOrderDOList)) {
	            return instOrderList;
	        }

	        for (PayInstOrderDO instOrderDO : instOrderDOList) {
	            instOrderList.add(buildInstOrder(instOrderDO));
	        }

	        return instOrderList;
		}

		@Override
		public List<PayInstOrderDO> selectPayInstOrderListByDate(Date gmtCreateBegin, Date gmtCreateEnd) {
	        List<PayInstOrderDO> instOrderDOList = payInstOrderMapper.selectPayInstOrderListByDate(gmtCreateBegin,gmtCreateEnd);
	        return instOrderDOList;
		}

		@Override
		@Transactional
		public int updateFlag(Date endTime, Date startTime, OrderFlag newFlag, OrderFlag oldFlag,
				String fundChannelCode, List<String> communicateStatusList) {
			return payInstOrderMapper.updateFlag(endTime, startTime, newFlag.getCode(),  oldFlag.getCode(),
					fundChannelCode, communicateStatusList);
		}

		@Override
		public List<PayInstOrder> loadOrderIdForDurationQueryResultPaging(String fundChannelCode,
				List<String> communicateStatusList, Date startTime, Date endTime, OrderFlag flag, int pageSize) {
			List<PayInstOrder> instOrderList = new ArrayList<PayInstOrder>();
	        List<PayInstOrderDO> instOrderDOList = payInstOrderMapper.loadOrderIdForDurationQueryResultPaging(fundChannelCode,communicateStatusList, startTime, endTime, flag.getCode(), pageSize);
	        if (CollectionUtils.isEmpty(instOrderDOList)) {
	            return instOrderList;
	        }

	        for (PayInstOrderDO instOrderDO : instOrderDOList) {
	            instOrderList.add(buildInstOrder(instOrderDO));
	        }

	        return instOrderList;
		}

		@Override
		@Transactional
		public int updateFlagWithOrderId(Integer InstOrderId, OrderFlag flag) {
			return payInstOrderMapper.updateFlagWithOrderId(InstOrderId,flag.getCode());
		}

		@Override
		public PayInstOrder loadWithOrderId(Integer InstOrderId) {
			return loadById(InstOrderId);
		}

		@Override
		public List<PayInstOrder> listPaymentRecord(Map<String, Object> paramMap) {
			List<PayInstOrder> instOrderList = new ArrayList<PayInstOrder>();
	        List<PayInstOrderDO> instOrderDOList = payInstOrderMapper.listPaymentRecord(paramMap);
	        if (CollectionUtils.isEmpty(instOrderDOList)) {
	            return instOrderList;
	        }

	        for (PayInstOrderDO instOrderDO : instOrderDOList) {
	        	PayInstOrderResultDO payInstOrderResult = new PayInstOrderResultDO();
	        	payInstOrderResult.setInstOrderNo(instOrderDO.getInstOrderNo());
	        	payInstOrderResult.setInstStatus(PayOrderStatus.SUCCESSFUL.getCode());;
	        	List<PayInstOrderResultDO> list = payInstOrderResultMapper.selectPayInstOrderResultList(payInstOrderResult);
	        	ChannelPayOrderDO channelPayOrder = new ChannelPayOrderDO();
	        	channelPayOrder.setInstOrderId(instOrderDO.getInstOrderId());
	        	List<ChannelPayOrderDO> payOrderList = channelPayOrderService.selectChannelPayOrderList(channelPayOrder);
	        	instOrderList.add(buildInstOrder(instOrderDO,list.get(0),payOrderList.get(0)));
	        }

	        return instOrderList;
		}


		private PayInstOrder buildInstOrder(PayInstOrderDO instOrderDO, PayInstOrderResultDO payInstOrderResultDO,ChannelPayOrderDO channelPayOrderDO) {
			if (instOrderDO == null && payInstOrderResultDO == null && channelPayOrderDO == null) {
	            return null;
	        }
	        PayInstOrder instOrder = null;
	        BizType bizType = BizType.getByCode(instOrderDO.getOrderType());
	        switch (bizType) {
	            case FUNDIN:
	                instOrder = InstOrderConverter.convert(instOrderDO);
	                break;
	            case FUNDOUT:
	                instOrder = InstOrderConverter.convert(instOrderDO);
	                break;
	            case REFUND:
	                instOrder = InstOrderConverter.convert(instOrderDO);
	                break;
	            default:
	                throw new RuntimeException("不支持此业务类型" + bizType.getMessage());
	        }

	        TmFundChannel channel = tmFundChannelService.getFundChannel(instOrderDO.getFundChannelCode());
	        instOrder.setFundChannel(channel);
	        TmFundChannelApi api = tmFundChannelApiService.getFundChannelApi(instOrderDO.getFundChannelApi());
	        instOrder.setFundChannelApi(api);
	        //流水号
	        instOrder.setInstSeqNo(payInstOrderResultDO.getInstSeqNo());
	        //支付流水号 
	        instOrder.setPaymentSeqNo(channelPayOrderDO.getPaymentSeqNo());
	        return instOrder;
		}


		

		
}

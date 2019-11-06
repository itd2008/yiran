package com.yiran.payorder.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.yiran.payorder.mapper.ChannelPayOrderMapper;
import com.yiran.payorder.converter.OrderConverter;
import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayRequest;
import com.yiran.payorder.domaindo.ChannelPayOrderDO;
import com.yiran.payorder.enums.PayOrderConfirmStatus;
import com.yiran.payorder.enums.PayOrderStatus;
import com.yiran.payorder.exception.AppRuntimeException;
import com.yiran.payorder.exception.DuplicateKeyException;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IPayRequestService;
import com.yiran.payorder.utils.BizUtil;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.common.support.Convert;
import com.yiran.paychannel.enums.YesNo;

/**
 * 渠道支付订单 服务层实现
 * 
 * @author yiran
 * @date 2019-07-13
 */
@Service
public class ChannelPayOrderServiceImpl implements IChannelPayOrderService 
{
	private Logger             logger = LoggerFactory.getLogger(ChannelPayOrderServiceImpl.class);
	private static final String   defaultSettlementId = "0";
	@Autowired
	private IPayInstOrderService payInstOrderService;
	@Autowired
	private ChannelPayOrderMapper channelPayOrderMapper;
	@Autowired
	private IPayRequestService payRequestService;
	/**
     * 查询渠道支付订单信息
     * 
     * @param paySeqNo 渠道支付订单ID
     * @return 渠道支付订单信息
     */
    @Override
	public ChannelPayOrderDO selectChannelPayOrderById(String paySeqNo)
	{
	    return channelPayOrderMapper.selectChannelPayOrderById(paySeqNo);
	}
	
	/**
     * 查询渠道支付订单列表
     * 
     * @param channelPayOrder 渠道支付订单信息
     * @return 渠道支付订单集合
     */
	@Override
	public List<ChannelPayOrderDO> selectChannelPayOrderList(ChannelPayOrderDO channelPayOrder)
	{
	    return channelPayOrderMapper.selectChannelPayOrderList(channelPayOrder);
	}
	
    /**
     * 新增渠道支付订单
     * 
     * @param channelPayOrder 渠道支付订单信息
     * @return 结果
     */
	@Override
	public int insertChannelPayOrder(ChannelPayOrderDO channelPayOrder)
	{
	    return channelPayOrderMapper.insertChannelPayOrder(channelPayOrder);
	}
	
	/**
     * 修改渠道支付订单
     * 
     * @param channelPayOrder 渠道支付订单信息
     * @return 结果
     */
	@Override
	public int updateChannelPayOrder(ChannelPayOrderDO channelPayOrder)
	{
	    return channelPayOrderMapper.updateChannelPayOrder(channelPayOrder);
	}

	/**
     * 删除渠道支付订单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteChannelPayOrderByIds(String ids)
	{
		return channelPayOrderMapper.deleteChannelPayOrderByIds(Convert.toStrArray(ids));
	}

	@Override
	public ChannelPayOrder loadByPaymentSeqNo(String paymentSeqNo, String settlementId) {
		
		return OrderConverter.convert(channelPayOrderMapper.loadByPaymentSeqNo(paymentSeqNo,settlementId));
	}

	@Override
	public String store(ChannelPayOrder channelPayOrder) throws DuplicateKeyException {
		PayRequest req = new PayRequest();
        req.setPaymentSeqNo(channelPayOrder.getPaymentSeqNo());
        req.setSettlementId(getSettlementId(channelPayOrder.getSettlementId()));
        if (!canStore(req)) { // 判断是否重复提交 
            throw new DuplicateKeyException("支付流水号重复");
        }
        //paySeqNo 由yyyyMMddhhmmss+6位随机数
        StringBuffer bf = new StringBuffer(DateUtil.format(new Date(), "yyyyMMddHHmmss"));
        bf.append(RandomUtil.randomNumbers(6));
        String paySeqNo =  bf.toString();
        channelPayOrder.setPaySeqNo(paySeqNo);
        logger.info("-------PayChannelOrder:"+channelPayOrder);
        insertChannelPayOrder(OrderConverter.convertChannelPayOrderDO(channelPayOrder));
        return paySeqNo;
	}


	private boolean canStore(PayRequest request) throws DuplicateKeyException {
        String paymentSeqNo = request.getPaymentSeqNo();
        request.setCanRetry("N");
        try {
        	payRequestService.insertPayRequest(request);
            return true;
        } catch (DataIntegrityViolationException e) {
            // 支付流水重复
        	PayRequest dbdo = payRequestService.lockedById(paymentSeqNo, request.getSettlementId());
            if ("Y".equals(dbdo.getCanRetry())) { // 如果能重复则锁定
            	payRequestService.updateStatusById("N", paymentSeqNo, request.getSettlementId());
                return true;
            }

            throw new DuplicateKeyException("支付流水号重复");
        }
	}

	private String getSettlementId(String settlementId) {
		 return StringUtil.isEmpty(settlementId) ? defaultSettlementId : settlementId;
	}

	@Override
	public void updateInstById(Integer instOrderId, String paySeqNo) {

		channelPayOrderMapper.updateInstById(instOrderId, paySeqNo);
	}

	@Override
	public boolean updateCmfOrderStatus(final ChannelPayOrder channelPayOrder, final PayOrderStatus preStatus) {
		//不是最终状态,不更新状态,只更新审核状态
        if (preStatus.equals(channelPayOrder.getStatus())) {
            updateConfirmStatusById(channelPayOrder.getConfirmStatus(), channelPayOrder.getPaySeqNo());
            return true;
        }
        ChannelPayOrder dbCmfOrder = loadByCmfSeqNo(channelPayOrder.getPaySeqNo());

        if (preStatus.equals(dbCmfOrder.getStatus())) {
            updateStatusById(channelPayOrder.getStatus(), channelPayOrder.getPaySeqNo());
            updateConfirmStatusById(channelPayOrder.getConfirmStatus(), channelPayOrder.getPaySeqNo());
            return true;
        } else if (!dbCmfOrder.getStatus().equals(channelPayOrder.getStatus())) {
            logger.error("cmf订单[" + channelPayOrder.getPaymentSeqNo() + "]状态跃迁异常,原状态:"
                    + dbCmfOrder.getStatus() + ",新状态" + channelPayOrder.getStatus());
            throw new AppRuntimeException("支付流水号[" + channelPayOrder.getPaymentSeqNo() + "],原状态:"
                    + dbCmfOrder.getStatus() + ",新状态"
                    + channelPayOrder.getStatus());
        }
		return false;
		
	}

	
	private int updateStatusById(PayOrderStatus status, String paySeqNo) {

		return channelPayOrderMapper.updateStatusById(status.getCode(),paySeqNo);
	}

	private ChannelPayOrder loadByCmfSeqNo(String paySeqNo) {
		ChannelPayOrderDO channelPayOrderDO = channelPayOrderMapper.selectChannelPayOrderById(paySeqNo);
		return OrderConverter.convert(channelPayOrderDO);
	}
	@Override
	public int updateConfirmStatusById(PayOrderConfirmStatus confirmStatus, String paySeqNo) {
		
		return channelPayOrderMapper.updateConfirmStatusById(confirmStatus.getCode(),paySeqNo);
		
	}

	@Override
	public ChannelPayOrder loadByPaySeqNo(String paySeqNo) {
		ChannelPayOrderDO channelPayOrderDO = channelPayOrderMapper.selectChannelPayOrderById(paySeqNo);
		return OrderConverter.convert(channelPayOrderDO);
	}

	@Override
	public ChannelPayOrder loadByInstOrderId(Integer instOrderId) {
		List<ChannelPayOrderDO> channelPayOrderDOList = channelPayOrderMapper.loadByInstOrderId(instOrderId);
		ChannelPayOrderDO channelPayOrderDO =null;
		if (null == channelPayOrderDOList || channelPayOrderDOList.size() == 0) {
            PayInstOrder instOrderDo = payInstOrderService.loadById(instOrderId);
            if (instOrderDo != null && YesNo.YES.getCode().equals(instOrderDo.getIsSplit())) {
                return loadByPaySeqNo(instOrderDo.getPaySeqNo());
            } else {
                return null;
            }
        }
		//取第一条. 仅风险资金确定性入款会出现2条cmfOrder(此时，取不是确定性入款的那笔订单)
        if (channelPayOrderDOList.size() > 1) {
            for (ChannelPayOrderDO tmpOrderDO : channelPayOrderDOList) {
                if (!BizUtil.isDeterminedFundin(tmpOrderDO.getPaymentCode())) {
                	channelPayOrderDO = tmpOrderDO;
                }
            }
        } else {
        	channelPayOrderDO = channelPayOrderDOList.get(0);
        }
        
		return OrderConverter.convert(channelPayOrderDO);
	}

	@Override
	public ChannelPayOrder loadByPaymentSeqNo(String paymentSeqNo) {
		
		return OrderConverter.convert(channelPayOrderMapper.selectByPaymentSeqNo(paymentSeqNo));
	}

	@Override
	public ChannelPayOrder loadByOrgiPaymentSeqNo(String orgiPaymentSeqNo) {
		return OrderConverter.convert(channelPayOrderMapper.loadByOrgiPaymentSeqNo(orgiPaymentSeqNo));
	}

	@Override
	public int updatePaymentNotifyStatusById(String paymentNotifyStatus ,String paySeqNo) {
		return channelPayOrderMapper.updatePaymentNotifyStatusById(paymentNotifyStatus ,paySeqNo);
	}

	@Override
	public List<ChannelPayOrderDO> selectChannelPayOrderListIsNotS() {
		return channelPayOrderMapper.selectChannelPayOrderListIsNotS();
	}
	
}

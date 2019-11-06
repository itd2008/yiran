package com.yiran.quartz.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiran.payorder.domain.ChannelPayOrder;
import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayResultNotify;
import com.yiran.payorder.domaindo.ChannelPayOrderDO;
import com.yiran.payorder.facade.IResultNotifyFacade;
import com.yiran.payorder.service.IChannelPayOrderService;
import com.yiran.payorder.service.IPayInstOrderService;
import com.yiran.payorder.service.IPayResultNotifyService;

@Component("instOrderNotifyTask")
public class InstOrderNotifyTask {
	
	@Autowired
   	private IPayInstOrderService payInstOrderService;

	@Autowired
   	private IChannelPayOrderService channelPayOrderService;
	
	@Autowired
    private IPayResultNotifyService payResultNotifyService;
	
	@Autowired
	private IResultNotifyFacade resultNotifyFacade;
	
	public void notifyTask(){
		//1.把所有没有发送通知的发下通知
		List<ChannelPayOrderDO> channelPayOrderList = channelPayOrderService.selectChannelPayOrderListIsNotS();
		for (ChannelPayOrderDO channelPayOrder : channelPayOrderList) {
			PayInstOrder payInstOrder = payInstOrderService.loadById(channelPayOrder.getInstOrderId());
			resultNotifyFacade.notifyBiz(payInstOrder.getInstOrderNo());
		}
		//2.将通知表中处理中或者发送失败的重新发送
		List<PayResultNotify> payResultNotifyList = payResultNotifyService.selectPayResultNotifyListIsFail();
		for (PayResultNotify payResultNotify : payResultNotifyList) {
			ChannelPayOrder channelPayOrder = channelPayOrderService.loadByPaymentSeqNo(payResultNotify.getInstOrderId());
			PayInstOrder payInstOrder = payInstOrderService.loadById(channelPayOrder.getInstOrderId());
			resultNotifyFacade.notifyBiz(payInstOrder.getInstOrderNo());
		}
		
	}
}

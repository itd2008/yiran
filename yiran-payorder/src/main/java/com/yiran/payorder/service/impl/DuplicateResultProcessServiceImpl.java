package com.yiran.payorder.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;
import com.yiran.payorder.enums.CommunicateStatus;
import com.yiran.payorder.service.IDuplicateResultProcessService;
import com.yiran.payorder.service.IPayInstOrderResultService;
import com.yiran.payorder.service.IPayInstOrderService;


/**
 *
 * <p>处理结果，包括实时调用的结果，包括实时返回，或者异步回调</p>
 *
 * 1. 核对返回的结果，金额是否匹配，账户信息是否一致等
 * 2. 调用状态模块更新状态
 */
@Service("duplicateResultProcessService")
public class DuplicateResultProcessServiceImpl implements IDuplicateResultProcessService {

    private static final Logger logger = LoggerFactory.getLogger(DuplicateResultProcessServiceImpl.class);

    @Autowired
   	private IPayInstOrderService payInstOrderService;

    @Autowired
   	private IPayInstOrderResultService payInstOrderResultService;

    @Override
    public boolean checkHasProcessed(PayInstOrder instOrder) {
        if (instOrder == null) {
            return false;
        }
        PayInstOrder dbInstOrder = payInstOrderService.loadById(instOrder.getInstOrderId());
        return CommunicateStatus.RECEIVED.equals(dbInstOrder.getCommunicateStatus());
    }

    @Override
    public boolean duplicateRequestProcess(PayInstOrder dbInstOrder, PayInstOrderResult comingResult) {
        //不是重复请求
        if (!checkHasProcessed(dbInstOrder) || comingResult == null) {
            return true;
        }

        //结果.  理论上，CommunicateStatus为"R数据已返回"，dbResult就有数据。
        PayInstOrderResult dbResult = payInstOrderResultService.loadRealResultByOrder(dbInstOrder
            .getInstOrderId());
        if (dbResult == null || dbResult.getStatus() == comingResult.getStatus()) {
            logger.warn("[重复请求处理]: dbResult:" + dbResult + ", 当前请求的result:" + comingResult);
            return true;
        } else {

            //重复请求，并且状态不一致，发送给监控.
            logger
                .warn("[重复请求处理，两次的状态不一致]: dbResult:" + dbResult + ", 当前请求的result:" + comingResult);
            return false;
        }
    }

}

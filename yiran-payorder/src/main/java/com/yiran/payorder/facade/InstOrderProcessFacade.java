package com.yiran.payorder.facade;

import java.util.List;

import com.yiran.payorder.domain.InstOrderInfo;
import com.yiran.payorder.domain.InstOrderVO;
import com.yiran.payorder.domain.OrderQuery;
import com.yiran.payorder.domain.OrderQueryResult;
import com.yiran.payorder.domain.QueryOrderResult;

/**
 * <p>CMF机构订单处理服务.</p>
 */
public interface InstOrderProcessFacade {
    


    /**
     * 查询机构订单记录
     * @param query
     * @return
     */
    OrderQueryResult getOrders(OrderQuery query);


    /**
     * 根据机构订单号查询一条机构订单明细.
     *
     * @param instOrderNo
     * @return
     */
    InstOrderVO getInstOrder(String instOrderNo);

    /**
     * 根据机构订单号查询一条机构订单明细，包括网关订单号.
     *
     * @param instOrderNo
     * @return
     */
    InstOrderVO getInstOrderWithGateOrderNo(String instOrderNo);



    /**
     * 查询渠道订单结果
     * @param instOrderNo
     * @return
     */
    QueryOrderResult queryChannelResult(String instOrderNo);


    /**
     * 查询并修改订单结果(主要方法)
     * @param instOrderNo
     * @return
     */
    QueryOrderResult queryInstOrderResult(String instOrderNo);


    /**
     * 依据机构订单信息查询订单
      * @param instOrderNos
     * @return
     */
    public List<InstOrderInfo> qurey(List<String> instOrderNos);

}

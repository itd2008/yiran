package com.yiran.payorder.facade;

import com.yiran.payorder.domain.ReturnInfo;
import com.yiran.payorder.notify.OrderResult;
/**
 * 结果通知
 * @author pandaa
 *
 */
public interface IResultNotifyFacade {

    public ReturnInfo notify(OrderResult result);
    /**
     * 模拟业务系统响应成功
     * @param url
     * @param data
     * @return
     */
    public String notifyBiz(String url,String data);
    
    public void notifyBiz(String instOrderNo);
}

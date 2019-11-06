package com.yiran.payorder.domain;

import com.netfinworks.biz.common.util.BaseResult;
import com.yiran.paychannel.enums.InstOrderStatus;

/**
 *
 * <p>处理完'渠道通知'返回给渠道的结果对象</p>
 */
public class ChannelNotifyResult extends BaseResult {
    private static final long serialVersionUID = 1L;

    /** 结果代码 */
    // 返回码. 可以定义0为成功，其他失败
    private String            returnCode;
    /** 结果 */
    private InstOrderStatus   instOrderStatus;
    /** 扩展信息 */
    private String            extension;

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    

    public InstOrderStatus getInstOrderStatus() {
        return instOrderStatus;
    }

    public void setInstOrderStatus(InstOrderStatus instOrderStatus) {
        this.instOrderStatus = instOrderStatus;
    }

    @Override
    public String toString() {
        return "ChannelNotifyResult [extension=" + extension + ", instOrderStatus="
               + instOrderStatus + ", returnCode=" + returnCode + "]";
    }

}

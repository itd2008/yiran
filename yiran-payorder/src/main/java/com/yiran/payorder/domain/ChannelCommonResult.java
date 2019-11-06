package com.yiran.payorder.domain;

import java.io.Serializable;

/**
 * <p>渠道通用结果</p>
 * 用于适应渠道结果的多样性
 */
public class ChannelCommonResult implements Serializable {
    private static final long serialVersionUID = -1137050045481432344L;

    /** 结果对象CLASS全路径名称 */
    private String            resultClass;
    /** 结果JASON串 */
    private String            resultJason;

    public String getResultClass() {
        return resultClass;
    }

    public void setResultClass(String resultClass) {
        this.resultClass = resultClass;
    }

    public String getResultJason() {
        return resultJason;
    }

    public void setResultJason(String resultJason) {
        this.resultJason = resultJason;
    }

    public String toString() {
        return "ChannelCommonResult-->resultClass:" + resultClass + ",resultInfo:" + resultJason;
    }
}

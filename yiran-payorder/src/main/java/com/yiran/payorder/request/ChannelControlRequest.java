package com.yiran.payorder.request;

/**
 * 公用控制类参数
 *
 */
public class ChannelControlRequest extends ChannelFundRequest {

    private static final long serialVersionUID = -6149680257169635951L;
    /** 请求订单号 */
    private String            requestNo;
    /** 原机构订单号 */
    private String            preInstOrderNo;
    /** 原交易日期 */
    private String            preTransDate;
    /** 原请求订单号 */
    private String            preRequestNo;

    public String getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(String requestNo) {
        this.requestNo = requestNo;
    }

    public String getPreInstOrderNo() {
        return preInstOrderNo;
    }

    public void setPreInstOrderNo(String preInstOrderNo) {
        this.preInstOrderNo = preInstOrderNo;
    }

    public String getPreTransDate() {
        return preTransDate;
    }

    public void setPreTransDate(String preTransDate) {
        this.preTransDate = preTransDate;
    }

    public String getPreRequestNo() {
        return preRequestNo;
    }

    public void setPreRequestNo(String preRequestNo) {
        this.preRequestNo = preRequestNo;
    }

}

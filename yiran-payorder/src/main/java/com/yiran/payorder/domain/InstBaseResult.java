package com.yiran.payorder.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.enums.InstOrderProcessStatus;
import com.yiran.payorder.enums.InstOrderResultStatus;

/**
 * <p>机构基础结果</p>
 */
public class InstBaseResult implements BasicConstant {
    /** 机构订单号 */
    protected String                instOrderNo;
    /** 接口类型 */
    protected FundChannelApiType    apiType;
    /** 结果状态 */
    protected InstOrderResultStatus status;
    /** 统一结果编码 */
    protected String                instResultCode;
    /** 结果信息 */
    protected String                resultMessage;
    /** 渠道API结果码 */
    protected String                apiResultCode;
    /** 渠道API结果子码 */
    protected String                apiResultSubCode;
    /** 扩展信息 */
    protected Map<String, String>   extension = new HashMap<String, String>();
    /** 创建时间 */
    protected Date                  gmtCreate;
    /** 最后修改时间 */
    protected Date                  gmtModified;
    /** 备注 */
    protected String                memo;
    /**
     * 机构订单处理状态。该字段表示CMF与外围系统交互时的状态,不须持久化到数据库.
     */
    private InstOrderProcessStatus  processStatus;

    public String getInstOrderNo() {
        return instOrderNo;
    }

    public void setInstOrderNo(String instOrderNo) {
        this.instOrderNo = instOrderNo;
    }

    public FundChannelApiType getApiType() {
        return apiType;
    }

    public void setApiType(FundChannelApiType apiType) {
        this.apiType = apiType;
    }

    public InstOrderResultStatus getStatus() {
        return status;
    }

    public void setStatus(InstOrderResultStatus status) {
        this.status = status;
    }

    public String getInstResultCode() {
        return instResultCode;
    }

    public void setInstResultCode(String instResultCode) {
        this.instResultCode = instResultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getApiResultCode() {
        return apiResultCode;
    }

    public void setApiResultCode(String apiResultCode) {
        this.apiResultCode = apiResultCode;
    }

    public String getApiResultSubCode() {
        return apiResultSubCode;
    }

    public void setApiResultSubCode(String apiResultSubCode) {
        this.apiResultSubCode = apiResultSubCode;
    }

    public Map<String, String> getExtension() {
        return extension;
    }

    public void setExtension(Map<String, String> extension) {
        this.extension = extension;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public InstOrderProcessStatus getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(InstOrderProcessStatus processStatus) {
        this.processStatus = processStatus;
    }

    /**
     * 判断是否使用返回码
     * @return
     */
    public boolean isReturnCodeRefacted() {
        if (StringUtil.isBlank(this.getApiResultCode()) || this.getApiType() == null) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

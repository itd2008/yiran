package com.yiran.paychannel.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.enums.Dbcr;

/**
 * 
 * <p>Api 金额限制</p>
 */
public class ApiAmountLimit implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    /** 主键ID */
    private long              limitId;
    /** API编码  */
    private String            apiCode;
    /** 借贷标识 */
    private Dbcr              dbcr;
    /** 机构编码 */
    private String            instCode;
    /** 最小金额限制 */
    private Money             minAmount;
    /** 最大金额限制 */
    private Money             maxAmount;
    /** 面值限制,主要用于卡类支付使用 */
    private List<String>      faceLimit;
    /** match条件限制,扩展使用 */
    private String            matchCondition;
    /** 用于前端展示表达式 */
    private String            showExpression;
    /** 创建时间 */
    private Date              gmtCreate;
    /** 修改时间 */
    private Date              gmtModified;

    public long getLimitId() {
        return limitId;
    }

    public void setLimitId(long limitId) {
        this.limitId = limitId;
    }

    public String getApiCode() {
        return apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public Dbcr getDbcr() {
        return dbcr;
    }

    public void setDbcr(Dbcr dbcr) {
        this.dbcr = dbcr;
    }

    public String getInstCode() {
        return instCode;
    }

    public void setInstCode(String instCode) {
        this.instCode = instCode;
    }

    public Money getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Money minAmount) {
        this.minAmount = minAmount;
    }

    public Money getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Money maxAmount) {
        this.maxAmount = maxAmount;
    }

    public List<String> getFaceLimit() {
        return faceLimit;
    }

    public void setFaceLimit(List<String> faceLimit) {
        this.faceLimit = faceLimit;
    }

    public String getMatchCondition() {
        return matchCondition;
    }

    public void setMatchCondition(String matchCondition) {
        this.matchCondition = matchCondition;
    }

    public String getShowExpression() {
        return showExpression;
    }

    public void setShowExpression(String showExpression) {
        this.showExpression = showExpression;
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

    @Override
    public ApiAmountLimit clone() {
        ApiAmountLimit o = null;
        try {
            o = (ApiAmountLimit) super.clone();
        } catch (CloneNotSupportedException e) {
        }
        List<String> cloneFaceLimits = new ArrayList<String>();
        if (faceLimit != null && faceLimit.size() > 0) {
            for (String cloneFaceLimit : faceLimit) {
                cloneFaceLimits.add(cloneFaceLimit);
            }
        }
        o.setFaceLimit(cloneFaceLimits);
        return o;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

/**
 * 
 */
package com.yiran.member.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * <p>加密字段配置与值</p>
 */
public class SecurityFieldValue {

    private String columnName;      //列名

    private String encipheredType;  //加密方式

    private String summaryRule;     //摘要规则
    
    private String value;           //列值
    
    private String ticketSummaryRule; //ticket摘要规则

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getEncipheredType() {
        return encipheredType;
    }

    public void setEncipheredType(String encipheredType) {
        this.encipheredType = encipheredType;
    }

    public String getSummaryRule() {
        return summaryRule;
    }

    public void setSummaryRule(String summaryRule) {
        this.summaryRule = summaryRule;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    public String getTicketSummaryRule() {
        return ticketSummaryRule;
    }
    
    public void setTicketSummaryRule(String ticketSummaryRule) {
        this.ticketSummaryRule = ticketSummaryRule;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}

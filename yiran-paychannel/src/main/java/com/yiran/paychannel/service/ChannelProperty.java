package com.yiran.paychannel.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.netfinworks.common.util.money.Money;

/**
 * 
 * <p>渠道属性</p>
 * 如渠道支持的交易金额大小限制，面值，支持的卡种等等，某个属性往往是针对特定机构而言的，
 * 如手机充值卡渠道支持的面值和相应的目标机构相关，网银金额的限制往往和卡种，所属银行相关，
 * <br>
 * 渠道支持的目标机构是通过数据库表相关联的，不需要在渠道属性里面提现
 *
 */
public interface ChannelProperty extends Serializable {
    /**
     * 用于获取关联的属性，如金额的限制往往和目标机构或者卡片类型相关联
     * @param propertyName
     * @return
     */
    public ChannelProperty get(String propertyName);
    
    /**
     * 检查时候具有某个子属性
     * @param propertyName
     * @return
     */
    public boolean has(String propertyName);
    
    
    public boolean hasNo(String propertyName);
    
    /**
     * 新增子属性
     * @param domain
     * @return
     */
    public ChannelProperty addProperty(String domain);
    
    /**
     * 新增子属性
     * @param channelProperty
     * @return
     */
    public void addProperty(ChannelProperty channelProperty);
    
    /**
     * 设置属性值
     * @param values
     */
    public void setValues(String values);
    /**
     * 获取该属性的值，一般情况下不需要调用此方法
     * @return
     * 
     */
    public String getValue();
    /**
     * 返回属性值，如该属性是一个列表，如支持的所有面值
     * 一般情况下不需要调用此方法
     * @return
     *
     */
    public String[] getValues();
    
    
    public String getName();
    
    /**
     * 大于
     * @param value
     * @return
     */
    public boolean greatThan(BigDecimal value);
    
    public boolean greatThan(Money value);
    
    public boolean equals(BigDecimal value);
    
    public boolean equals(Money value);
    
    public boolean lessThan(BigDecimal value);
    
    public boolean lessThan(Money value);
    
    public boolean contains(BigDecimal value);
    
    public boolean contains(Money value);

    public boolean after(Date value);
    
    public boolean before(Date value);
    
    public boolean equal(Date value);

    /**
     * 小于或等于
     * @param value
     * @return
     */
    public boolean lessEqual(BigDecimal value);
    
    public boolean lessEqual(Money value);
    /**
     * 大于或等于
     * @param value
     * @return
     */
    public boolean greatEqual(BigDecimal value);
    
    public boolean greatEqual(Money value);
    
    public boolean greatThan(String value);
    
    public boolean equals(String value);
    
    public boolean lessThan(String value);
    
    public boolean contains(String value);
    
    public boolean any(String[] values);
    
    public boolean lessEqual(String value);
    
    public boolean greatEqual(String value);
    /**
     * 给定的值在属性范围以内，该属性必须包含两个可比较的值
     * min &lt= value &lt= max
     * @param value
     * @return
     */
    public boolean cover(BigDecimal value);
    
    public boolean cover(Money value);
    
    
    public boolean isEmpty(String value);
    
    public boolean isEmpty(Money value);
    
    public boolean isEmpty(BigDecimal value);
}

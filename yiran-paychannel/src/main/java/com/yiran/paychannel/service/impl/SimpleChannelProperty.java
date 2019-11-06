package com.yiran.paychannel.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.netfinworks.common.util.DateUtil;
import com.netfinworks.common.util.money.Money;
import com.yiran.paychannel.service.ChannelProperty;

/**
 * 
 * <p>渠道属性</p>
 */
public class SimpleChannelProperty implements ChannelProperty{
    
    /**
     * 
     */
    private static final long serialVersionUID = -7245202052552205503L;

    private static final String SP = ",";
    
    /**
     * 属性名称，最顶层属性名称可以用渠道编号
     */
    protected String name;
    
    protected Map<String, ChannelProperty> subProperties
        = new HashMap<String, ChannelProperty>();        //
    
    protected String[] values;
    
    @Override
    public SimpleChannelProperty addProperty(String domain){
        
        SimpleChannelProperty subProperty = new SimpleChannelProperty(domain);
        subProperties.put(domain, subProperty);
        return subProperty;
    }
    
    @Override
    public void addProperty(ChannelProperty channelProperty) {
        if(channelProperty==null)return;
        subProperties.put(channelProperty.getName(), channelProperty);
    }
    
    public SimpleChannelProperty(String name){
        this.name = name;
    }
    
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ChannelProperty> getSubProperties() {
        return subProperties;
    }

    public void setSubProperties(Map<String, ChannelProperty> subProperties) {
        this.subProperties = subProperties;
    }
    
    @Override
    public void setValues(String values) {
        if(values!=null){
            this.values = values.split(SP);
            if(this.values!=null){
                for(int i=0;i<this.values.length;i++){
                    this.values[i] = this.values[i].trim();
                }
            }
        }
    }
    
    @Override
    public ChannelProperty get(String propertyName) {
        return subProperties.get(propertyName);
    }
    
    @Override
    public boolean has(String propertyName) {
        return subProperties.get(propertyName)!=null;
    }
    
    
    @Override
    public boolean hasNo(String propertyName) {
        return subProperties.get(propertyName)==null;
    }

    @Override
    public String getValue() {
        if(this.values==null)return null;
        return this.values[0];
    }

    @Override
    public String[] getValues() {        
        return this.values;
    }

    @Override
    public boolean greatThan(BigDecimal value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return new BigDecimal(this.values[0]).compareTo(value)>0;
    }

    @Override
    public boolean equals(BigDecimal value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return new BigDecimal(this.values[0]).compareTo(value)==0;
    }

    @Override
    public boolean lessThan(BigDecimal value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return new BigDecimal(this.values[0]).compareTo(value)<0;
    }

    @Override
    public boolean contains(BigDecimal value) {
        
        if(this.values==null  || value==null)return false;
        
        for(String str : this.values){
            BigDecimal temp = new BigDecimal(str);
            if(temp.compareTo(value)==0)return true;
        }
        
        return false;
    }

    @Override
    public boolean lessEqual(BigDecimal value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return new BigDecimal(this.values[0]).compareTo(value)<=0;
    }

    @Override
    public boolean greatEqual(BigDecimal value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return new BigDecimal(this.values[0]).compareTo(value)>=0;
    }

    @Override
    public boolean greatThan(String value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return this.values[0].compareTo(value.trim())>0;
    }

    @Override
    public boolean equals(String value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return this.values[0].equals(value.trim());
    }

    @Override
    public boolean lessThan(String value) {
        if(this.values==null || this.values[0]==null || value==null)return false;
        return this.values[0].compareTo(value.trim())<0;
    }

    @Override
    public boolean contains(String value) {
        if(values==null || value==null)return false;
        
        if(values!=null){
            for(String str : this.values){
                if(str.equals(value.trim()))return true;
            }
        }
        
        return false;
    }

    @Override
    public boolean lessEqual(String value) {
        return equals(value) || lessThan(value);
    }

    @Override
    public boolean greatEqual(String value) {
        return equals(value) || greatThan(value);
    }

    @Override
    public boolean cover(BigDecimal value) {
        if(this.values==null
                ||this.values.length<1
                ||value==null)return false;
        
        BigDecimal max = new BigDecimal(this.values[1]);
        BigDecimal min = new BigDecimal(this.values[0]);
        
        return min.compareTo(value)<0 && max.compareTo(value)>=0;
    }

    @Override
    public boolean cover(Money value) {
        if(value==null)return false;
        return cover(value.getAmount());
    }

    @Override
    public boolean greatThan(Money value) {
        if(value==null)return false;
        return greatThan(value.getAmount());
    }

    @Override
    public boolean equals(Money value) {
        if(value==null)return false;
        return equals(value.getAmount());
    }

    @Override
    public boolean lessThan(Money value) {
        if(value==null)return false;
        return lessThan(value.getAmount());
    }

    @Override
    public boolean contains(Money value) {
        if(value==null)return false;
        return contains(value.getAmount());
    }

    @Override
    public boolean lessEqual(Money value) {
        if(value==null)return false;
        return lessEqual(value.getAmount());
    }

    @Override
    public boolean greatEqual(Money value) {
        if(value==null)return false;
        return greatEqual(value.getAmount());
    }

    @Override
    public boolean isEmpty(String value){
        return value==null || value.trim().length()<1;
    }
    
    @Override
    public boolean after(Date value) {
        if(value==null)return false;
        if(this.values==null || this.values[0]==null || value==null)return false;
        return DateUtil.addMinutes(new Date(), Long.valueOf(this.values[0])).after(value);
    }

    @Override
    public boolean before(Date value) {
        if(value==null)return false;
        if(this.values==null || this.values[0]==null || value==null)return false;
        return DateUtil.addMinutes(new Date(), Long.valueOf(this.values[0])).before(value);
    }

    @Override
    public boolean equal(Date value){
    	if(this.values==null || this.values[0]==null || value==null)return false;
    	if(this.values==null || this.values[0]==null || value==null)return false;
        return value.equals(DateUtil.addMinutes(new Date(), Long.valueOf(this.values[0])));
    }
    
    @Override
    public boolean isEmpty(Money value){
        return value==null || value.getAmount()==null;
    }
    
    @Override
    public boolean isEmpty(BigDecimal value){
        return value==null;
    }

    @Override
    public boolean any(String[] values) {
        if(values==null || this.values==null)return false;
        
        for(String from : values){
            for(String to : this.values){
                if(to.equals(from))return true;
            }
        }
        return false;
    }

}

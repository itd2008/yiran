package com.yiran.wechat.domain;

import org.apache.commons.lang3.StringUtils;

public class GuiGeVO {
	
	private String price;
	
	private String productSpecificationId;
	
	private String specificationValue;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductSpecificationId() {
		return productSpecificationId;
	}

	public void setProductSpecificationId(String productSpecificationId) {
		this.productSpecificationId = productSpecificationId;
	}

	public String getSpecificationValue() {
		return specificationValue;
	}

	public void setSpecificationValue(String specificationValue) {
		this.specificationValue = specificationValue;
	}
	
	@Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;//地址相等
        }

        if(obj == null){
            return false;//非空性：对于任意非空引用x，x.equals(null)应该返回false。
        }

        if(obj instanceof GuiGeVO){
        	GuiGeVO other = (GuiGeVO) obj;
            //需要比较的字段相等，则这两个对象相等
            if(equalsStr(this.price, other.price)
                    && equalsStr(this.productSpecificationId, other.productSpecificationId)
                    && equalsStr(this.specificationValue, other.specificationValue)){
                return true;
            }
        }

        return false;
    }
	private boolean equalsStr(String str1, String str2){
        if(StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)){
            return true;
        }
        if(!StringUtils.isEmpty(str1) && str1.equals(str2)){
            return true;
        }
        return false;
    }

	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (price == null ? 0 : price.hashCode());
        result = 31 * result + (productSpecificationId == null ? 0 : productSpecificationId.hashCode());
        result = 31 * result + (specificationValue == null ? 0 : specificationValue.hashCode());
        return result;
    }
}

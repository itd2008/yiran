
package com.yiran.payorder.notify;

import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.yiran.payorder.domain.PayInstOrderResult;

public class OrderResult implements Serializable {

    private static final long serialVersionUID = 5119361907320123458L;
    protected PayInstOrderResult orderResult;

    
    public PayInstOrderResult getOrderResult() {
		return orderResult;
	}


	public void setOrderResult(PayInstOrderResult orderResult) {
		this.orderResult = orderResult;
	}


	public String toString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}

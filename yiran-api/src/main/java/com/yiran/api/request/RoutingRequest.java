package com.yiran.api.request;

import java.io.Serializable;
import java.util.Map;


public class RoutingRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5002950891120325532L;
	
	protected String requestType;
    protected String payMode;
    protected String targetInst;
    protected Map<String,String> extensions;
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public String getPayMode() {
		return payMode;
	}
	public void setPayMode(String payMode) {
		this.payMode = payMode;
	}
	public String getTargetInst() {
		return targetInst;
	}
	public void setTargetInst(String targetInst) {
		this.targetInst = targetInst;
	}
	public Map<String, String> getExtensions() {
		return extensions;
	}
	public void setExtensions(Map<String, String> extensions) {
		this.extensions = extensions;
	}
    
    

}

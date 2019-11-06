package com.yiran.paychannel.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

import com.yiran.paychannel.enums.AccessChannel;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.CompanyOrPersonal;
import com.yiran.paychannel.enums.Dbcr;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.enums.RequestType;
/**
 * 路由请求参数
 * @author pandaa
 *
 */
public class RoutingRequestIner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3897610327401966812L;
	/**
	 * 业务类型
	 */
	protected RequestType requestType;
    protected PayMode payMode;
    protected String targetInst;
    protected String userId;
    protected BigDecimal amount;
    protected Dbcr dbcr;
    protected CompanyOrPersonal companyOrPersonal;
    protected AccessChannel channelSource;
    protected Map<String,String> extensions;
    
	public RequestType getRequestType() {
		return requestType;
	}
	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}
	public PayMode getPayMode() {
		return payMode;
	}
	public void setPayMode(PayMode payMode) {
		this.payMode = payMode;
	}
	public String getTargetInst() {
		return targetInst;
	}
	public void setTargetInst(String targetInst) {
		this.targetInst = targetInst;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Dbcr getDbcr() {
		return dbcr;
	}
	public void setDbcr(Dbcr dbcr) {
		this.dbcr = dbcr;
	}
	public CompanyOrPersonal getCompanyOrPersonal() {
		return companyOrPersonal;
	}
	public void setCompanyOrPersonal(CompanyOrPersonal companyOrPersonal) {
		this.companyOrPersonal = companyOrPersonal;
	}
	public AccessChannel getChannelSource() {
		return channelSource;
	}
	public void setChannelSource(AccessChannel channelSource) {
		this.channelSource = channelSource;
	}
	public Map<String, String> getExtensions() {
		return extensions;
	}
	public void setExtensions(Map<String, String> extensions) {
		this.extensions = extensions;
	}
    
}

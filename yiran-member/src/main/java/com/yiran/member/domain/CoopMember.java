package com.yiran.member.domain;

import java.io.Serializable;
import java.util.Date;

/**
 *合作商户会员信息
 */
public class CoopMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6415234543339234982L;

	/**
	 * 合作商户的会员ID
	 */
	private String coopMemberId;
	/**
	 * 登陆名称(手机号码或邮箱)
	 */
	private String loginName;
	/**
	 * 登陆名称的来源类型(1邮箱 2手机)
	 */
	private int sourceType;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 登录密码
	 */
	private String password;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 身份证号码
	 */
	private String idCard;
	/**
	 * 建立时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 建立人
	 */
	private String createUser;
	/**
	 * 更新人
	 */
	private String updateUser;
	/**
	 * 备注信息
	 */
	private String memo;
	
	
	/**
	 * 快捷通ID
	 */
	private String kjtId;
	
	
	/**
	 * 快捷通账户
	 */
	private String kjtAccount;
	
	/**
	 * 加密的手机号码
	 */
	private String encryptMobile;
	/**
	 * 加密的邮箱
	 */
	private String encryptEmail;
	
	/**
	 * 状态：1.正常，2.已修改登录名就代笔这个账户失效了，默认为1
	 */
	private int status;
	
	/**
	 * 会员类型，1:个人会员 2：企业会员
	 */
	private Integer memberType;
	
	
	public String getCoopMemberId() {
		return coopMemberId;
	}
	public void setCoopMemberId(String coopMemberId) {
		this.coopMemberId = coopMemberId;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public int getSourceType() {
		return sourceType;
	}
	public void setSourceType(int sourceType) {
		this.sourceType = sourceType;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getKjtId() {
		return kjtId;
	}
	public void setKjtId(String kjtId) {
		this.kjtId = kjtId;
	}
	public String getKjtAccount() {
		return kjtAccount;
	}
	public void setKjtAccount(String kjtAccount) {
		this.kjtAccount = kjtAccount;
	}
	public String getEncryptMobile() {
		return encryptMobile;
	}
	public void setEncryptMobile(String encryptMobile) {
		this.encryptMobile = encryptMobile;
	}
	public String getEncryptEmail() {
		return encryptEmail;
	}
	public void setEncryptEmail(String encryptEmail) {
		this.encryptEmail = encryptEmail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer getMemberType() {
		return memberType;
	}
	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}
}

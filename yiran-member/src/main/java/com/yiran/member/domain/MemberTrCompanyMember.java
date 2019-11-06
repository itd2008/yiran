package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import com.yiran.member.enums.MemberStatusEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 企业会员表 member_tr_company_member
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTrCompanyMember extends  MemberTmMember
{
	private static final long serialVersionUID = 1L;
	
	/** 会员ID */
	private String memberId;
	/** 行业类型 */
	private String industry;
	/** 商户营业执照 */
	private String licenseNo;
	/** 企业营业执照失效时间(营业期限) */
	private Date licenseExpireDate;
	/** 企业编号 */
	private String companyNo;
	/** 法人代表 */
	private String legalPerson;
	/** 企业规模 */
	private Integer scale;
	/** 建立时间 */
	private Date createTime;
	/** 更新时间 */
	private Date updateTime;
	/** 建立人 */
	private String createUser;
	/** 更新人 */
	private String updateUser;
	/** 备注信息 */
	private String memo;
	/** 企业网址 */
	private String website;
	/** 企业名称 */
	private String companyName;
	/** 企业地址 */
	private String address;
	/** 营业执照所在地 */
	private String licenseAddress;
	/** 营业范围 */
	private String businessScope;
	/** 联系电话 */
	private String telephone;
	/** 组织机构代码 */
	private String organizationNo;
	/** 企业简介 */
	private String summary;
	/** 企业营业执照附件url */
	private String licenseNoPath;
	/** 组织机构代码证附件url */
	private String organizationNoPath;
	/** 税务登记证附件url */
	private String taxNoPath;
	/** 单位银行结算账户开户许可证附件url */
	private String clearingAccountPath;
	/** 机构信用代码证附件url */
	private String institutionLicensePath;
	/** ICP备案许可附件url */
	private String icpLicensePath;
	/** 行业许可证附件url */
	private String industryLicensePath;
	/** 法人手机号码 */
	private String legalPersonPhone;
	/** 登记证号名称 */
	private String licenseName;
	/** 法人身份证有效期 */
	private Date legalPersonIdValidDate;
	/** 经营网址 */
	private String businessWebsite;
	/** 代理人名称 */
	private String proxyPerson;
	/** 企业类型(0企业 1其他 2事业单位 3个体工商户 4民办非企业) */
	private Integer companyType;
	/** 代理人证件有效期 */
	private Date proxyPersonIdValidDate;
	/** 商户简称 */
	private String shortName;
	 /** 会员名称 */
    private String              memberName;
    /** 会员状态 */
    private Integer    status;
    /** 会员锁定状态 */
    private Integer    lockStatus;
    
    /** 会员类型 */
    private Integer      memberType;
	
	 /**
     * 会员标识信息集合
     */
    List<MemberIdentity>        identitys;

    /**
     * 会员的认证信息
     */
    List<MemberTrVerifyEntity>                verifys;

    /**
     * 账户信息
     */
    private List<AccountDomain> accounts;
    
    /**
     * 会员的默认操作员
     */
    private MemberTmOperator            defaultOperator;
    

	public void setMemberId(String memberId) 
	{
		this.memberId = memberId;
	}

	public String getMemberId() 
	{
		return memberId;
	}
	public void setIndustry(String industry) 
	{
		this.industry = industry;
	}

	public String getIndustry() 
	{
		return industry;
	}
	public void setLicenseNo(String licenseNo) 
	{
		this.licenseNo = licenseNo;
	}

	public String getLicenseNo() 
	{
		return licenseNo;
	}
	public void setLicenseExpireDate(Date licenseExpireDate) 
	{
		this.licenseExpireDate = licenseExpireDate;
	}

	public Date getLicenseExpireDate() 
	{
		return licenseExpireDate;
	}
	public void setCompanyNo(String companyNo) 
	{
		this.companyNo = companyNo;
	}

	public String getCompanyNo() 
	{
		return companyNo;
	}
	public void setLegalPerson(String legalPerson) 
	{
		this.legalPerson = legalPerson;
	}

	public String getLegalPerson() 
	{
		return legalPerson;
	}
	public void setScale(Integer scale) 
	{
		this.scale = scale;
	}

	public Integer getScale() 
	{
		return scale;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}
	public void setUpdateTime(Date updateTime) 
	{
		this.updateTime = updateTime;
	}

	public Date getUpdateTime() 
	{
		return updateTime;
	}
	public void setCreateUser(String createUser) 
	{
		this.createUser = createUser;
	}

	public String getCreateUser() 
	{
		return createUser;
	}
	public void setUpdateUser(String updateUser) 
	{
		this.updateUser = updateUser;
	}

	public String getUpdateUser() 
	{
		return updateUser;
	}
	public void setMemo(String memo) 
	{
		this.memo = memo;
	}

	public String getMemo() 
	{
		return memo;
	}
	public void setWebsite(String website) 
	{
		this.website = website;
	}

	public String getWebsite() 
	{
		return website;
	}
	public void setCompanyName(String companyName) 
	{
		this.companyName = companyName;
	}

	public String getCompanyName() 
	{
		return companyName;
	}
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getAddress() 
	{
		return address;
	}
	public void setLicenseAddress(String licenseAddress) 
	{
		this.licenseAddress = licenseAddress;
	}

	public String getLicenseAddress() 
	{
		return licenseAddress;
	}
	public void setBusinessScope(String businessScope) 
	{
		this.businessScope = businessScope;
	}

	public String getBusinessScope() 
	{
		return businessScope;
	}
	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}

	public String getTelephone() 
	{
		return telephone;
	}
	public void setOrganizationNo(String organizationNo) 
	{
		this.organizationNo = organizationNo;
	}

	public String getOrganizationNo() 
	{
		return organizationNo;
	}
	public void setSummary(String summary) 
	{
		this.summary = summary;
	}

	public String getSummary() 
	{
		return summary;
	}
	public void setLicenseNoPath(String licenseNoPath) 
	{
		this.licenseNoPath = licenseNoPath;
	}

	public String getLicenseNoPath() 
	{
		return licenseNoPath;
	}
	public void setOrganizationNoPath(String organizationNoPath) 
	{
		this.organizationNoPath = organizationNoPath;
	}

	public String getOrganizationNoPath() 
	{
		return organizationNoPath;
	}
	public void setTaxNoPath(String taxNoPath) 
	{
		this.taxNoPath = taxNoPath;
	}

	public String getTaxNoPath() 
	{
		return taxNoPath;
	}
	public void setClearingAccountPath(String clearingAccountPath) 
	{
		this.clearingAccountPath = clearingAccountPath;
	}

	public String getClearingAccountPath() 
	{
		return clearingAccountPath;
	}
	public void setInstitutionLicensePath(String institutionLicensePath) 
	{
		this.institutionLicensePath = institutionLicensePath;
	}

	public String getInstitutionLicensePath() 
	{
		return institutionLicensePath;
	}
	public void setIcpLicensePath(String icpLicensePath) 
	{
		this.icpLicensePath = icpLicensePath;
	}

	public String getIcpLicensePath() 
	{
		return icpLicensePath;
	}
	public void setIndustryLicensePath(String industryLicensePath) 
	{
		this.industryLicensePath = industryLicensePath;
	}

	public String getIndustryLicensePath() 
	{
		return industryLicensePath;
	}
	public void setLegalPersonPhone(String legalPersonPhone) 
	{
		this.legalPersonPhone = legalPersonPhone;
	}

	public String getLegalPersonPhone() 
	{
		return legalPersonPhone;
	}
	public void setLicenseName(String licenseName) 
	{
		this.licenseName = licenseName;
	}

	public String getLicenseName() 
	{
		return licenseName;
	}
	public void setLegalPersonIdValidDate(Date legalPersonIdValidDate) 
	{
		this.legalPersonIdValidDate = legalPersonIdValidDate;
	}

	public Date getLegalPersonIdValidDate() 
	{
		return legalPersonIdValidDate;
	}
	public void setBusinessWebsite(String businessWebsite) 
	{
		this.businessWebsite = businessWebsite;
	}

	public String getBusinessWebsite() 
	{
		return businessWebsite;
	}
	public void setProxyPerson(String proxyPerson) 
	{
		this.proxyPerson = proxyPerson;
	}

	public String getProxyPerson() 
	{
		return proxyPerson;
	}
	public void setCompanyType(Integer companyType) 
	{
		this.companyType = companyType;
	}

	public Integer getCompanyType() 
	{
		return companyType;
	}
	public void setProxyPersonIdValidDate(Date proxyPersonIdValidDate) 
	{
		this.proxyPersonIdValidDate = proxyPersonIdValidDate;
	}

	public Date getProxyPersonIdValidDate() 
	{
		return proxyPersonIdValidDate;
	}
	public void setShortName(String shortName) 
	{
		this.shortName = shortName;
	}

	public String getShortName() 
	{
		return shortName;
	}

    public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(Integer lockStatus) {
		this.lockStatus = lockStatus;
	}

	public Integer getMemberType() {
		return memberType;
	}

	public void setMemberType(Integer memberType) {
		this.memberType = memberType;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("memberId", getMemberId())
            .append("industry", getIndustry())
            .append("licenseNo", getLicenseNo())
            .append("licenseExpireDate", getLicenseExpireDate())
            .append("companyNo", getCompanyNo())
            .append("legalPerson", getLegalPerson())
            .append("scale", getScale())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("website", getWebsite())
            .append("companyName", getCompanyName())
            .append("address", getAddress())
            .append("licenseAddress", getLicenseAddress())
            .append("businessScope", getBusinessScope())
            .append("telephone", getTelephone())
            .append("organizationNo", getOrganizationNo())
            .append("summary", getSummary())
            .append("licenseNoPath", getLicenseNoPath())
            .append("organizationNoPath", getOrganizationNoPath())
            .append("taxNoPath", getTaxNoPath())
            .append("clearingAccountPath", getClearingAccountPath())
            .append("institutionLicensePath", getInstitutionLicensePath())
            .append("icpLicensePath", getIcpLicensePath())
            .append("industryLicensePath", getIndustryLicensePath())
            .append("legalPersonPhone", getLegalPersonPhone())
            .append("licenseName", getLicenseName())
            .append("legalPersonIdValidDate", getLegalPersonIdValidDate())
            .append("businessWebsite", getBusinessWebsite())
            .append("proxyPerson", getProxyPerson())
            .append("companyType", getCompanyType())
            .append("proxyPersonIdValidDate", getProxyPersonIdValidDate())
            .append("shortName", getShortName())
            .toString();
    }
	
	public List<AccountDomain> getAccounts() {
        return accounts;
    }

    /**
     * 获得基本户
     * @return
     */
    public AccountDomain getBaseAccount() {
        if (!(this.accounts == null || this.accounts.isEmpty())) {
            return this.accounts.get(0);
        }
        return null;
    }

    public void setAccounts(List<AccountDomain> accounts) {
        this.accounts = accounts;
    }
    
    public void addAccount(AccountDomain account) {
        if(this.accounts == null){
            this.accounts = new ArrayList<AccountDomain>();
        }
        this.accounts.add(account);
    }

    public List<MemberIdentity> getIdentitys() {
        return identitys;
    }

    public void setIdentitys(List<MemberIdentity> identitys) {
        this.identitys = identitys;
    }
    
    public void addIdentity(MemberIdentity identity){
        if(this.identitys == null){
            this.identitys = new ArrayList<MemberIdentity>();
        }
        this.identitys.add(identity);
    }

    public void addVerify(MemberTrVerifyEntity verify){
        if(this.verifys==null){
            this.verifys=new ArrayList<MemberTrVerifyEntity>();
        }
        this.verifys.add(verify);
    }

    public  void cleanIdentity(){
        if(this.identitys!=null){
            identitys.clear();
        }
    }

    public List<MemberTrVerifyEntity> getVerifys() {
        return verifys;
    }

    public void setVerifys(List<MemberTrVerifyEntity> verifys) {
        this.verifys = verifys;
    }

	public MemberTmOperator getDefaultOperator() {
		return defaultOperator;
	}

	public void setDefaultOperator(MemberTmOperator defaultOperator) {
		this.defaultOperator = defaultOperator;
	}

	
	
}

package com.yiran.member.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.yiran.common.base.BaseEntity;
import com.yiran.member.enums.BasicAccountType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员表 member_tm_member
 * 
 * @author yiran
 * @date 2019-03-30
 */
public class MemberTmMember extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 会员ID(由系统生成, 个人1开头，企业2开头) */
	private String memberId;
	/** 会员名称 */
	private String memberName;
	/** 会员缩略名,已注销的会被添加后缀 */
	private String memberShortName;
	/** 会员类型(1个人 2 公司 3 组织 9 虚拟商户) */
	private Integer memberType;
	/** 会员状态(0未激活 1正常 2休眠 3注销) */
	private Integer status;
	/** 会员锁定状态(0未锁定 1已锁定) */
	private Integer lockStatus;
	/** IP地址 */
	private String fromIp;
	/** 激活时间 */
	private Date activeTime;
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
	/** 注册来源 0：浙江永达 1：手机端 2：海融易 3：人人创客 99：其他 */
	private Integer registerSource;
	/** 认证等级 0:未认证 1：实名校验  2:实名认证V1 3:实名认证V2 */
	private Integer verifyLevel;
	/** 邀请码 */
	private String invitCode;
	/** 注册来源扩展json */
	private String registerSourceExt;
	
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
	public void setMemberName(String memberName) 
	{
		this.memberName = memberName;
	}

	public String getMemberName() 
	{
		return memberName;
	}
	public void setMemberShortName(String memberShortName) 
	{
		this.memberShortName = memberShortName;
	}

	public String getMemberShortName() 
	{
		return memberShortName;
	}
	public void setMemberType(Integer memberType) 
	{
		this.memberType = memberType;
	}

	public Integer getMemberType() 
	{
		return memberType;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setLockStatus(Integer lockStatus) 
	{
		this.lockStatus = lockStatus;
	}

	public Integer getLockStatus() 
	{
		return lockStatus;
	}
	public void setFromIp(String fromIp) 
	{
		this.fromIp = fromIp;
	}

	public String getFromIp() 
	{
		return fromIp;
	}
	public void setActiveTime(Date activeTime) 
	{
		this.activeTime = activeTime;
	}

	public Date getActiveTime() 
	{
		return activeTime;
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
	public void setRegisterSource(Integer registerSource) 
	{
		this.registerSource = registerSource;
	}

	public Integer getRegisterSource() 
	{
		return registerSource;
	}
	public void setVerifyLevel(Integer verifyLevel) 
	{
		this.verifyLevel = verifyLevel;
	}

	public Integer getVerifyLevel() 
	{
		return verifyLevel;
	}
	public void setInvitCode(String invitCode) 
	{
		this.invitCode = invitCode;
	}

	public String getInvitCode() 
	{
		return invitCode;
	}
	public void setRegisterSourceExt(String registerSourceExt) 
	{
		this.registerSourceExt = registerSourceExt;
	}

	public String getRegisterSourceExt() 
	{
		return registerSourceExt;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("memberId", getMemberId())
            .append("memberName", getMemberName())
            .append("memberShortName", getMemberShortName())
            .append("memberType", getMemberType())
            .append("status", getStatus())
            .append("lockStatus", getLockStatus())
            .append("fromIp", getFromIp())
            .append("activeTime", getActiveTime())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createUser", getCreateUser())
            .append("updateUser", getUpdateUser())
            .append("memo", getMemo())
            .append("registerSource", getRegisterSource())
            .append("verifyLevel", getVerifyLevel())
            .append("invitCode", getInvitCode())
            .append("registerSourceExt", getRegisterSourceExt())
            .toString();
    }
    
    public BasicAccountType getBaseAccountType() {
        return null;
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

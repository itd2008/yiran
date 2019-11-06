/**
 *
 */
package com.yiran.member.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.netfinworks.common.util.money.Money;
import com.yiran.member.domain.Account;
import com.yiran.member.domain.AccountBalance;
import com.yiran.member.domain.AccountDomain;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.domain.MemberTrMemberAccount;
import com.yiran.member.domain.MemberTrMemberAccountDetail;
import com.yiran.member.enums.AccountAttributeEnum;
import com.yiran.member.enums.AccountCategoryEnum;
import com.yiran.member.enums.ActivateStatusEnum;
import com.yiran.member.enums.BasicAccountType;
import com.yiran.member.enums.OrderModeEnum;
import com.yiran.member.enums.TxnTypeEnum;
import com.yiran.member.request.AccBalanceListRequest;
import com.yiran.member.request.GetBalanceListReq;
import com.yiran.member.request.GetBalanceListRequestParam;
import com.yiran.member.request.OpenAccountRequest;
import com.yiran.member.request.UpdateAccountFreezeStatusRequest;
import com.yiran.member.response.AccountBalanceListResp;
import com.yiran.member.response.AccountInfo;

import cn.hutool.core.util.RandomUtil;


/**
 * <p>账户域工具类</p>
 */
public class AccountDomainUtil {

    /**
     * 构建开基本户请求对象
     * @param member
     * @param accountStatus
     * @return
     */
    public static AccountDomain buildOpenBaseAccountRequest(MemberTmMember member,
                                                            ActivateStatusEnum accountStatus) {
        AccountDomain account = new AccountDomain();
        BasicAccountType baseAccType = member.getBaseAccountType();
        String alias = member.getMemberId() + baseAccType.getMessage();
        account.setAccountType(baseAccType.getCode());
        account.setMemberId(member.getMemberId());
        account.setAccountName(alias);
        account.setAccountAttribute(baseAccType.getAccAttrEnum());
        account.setCreateUser(member.getCreateUser());
        account.setActivateStatus(accountStatus.getCode());
        account.setCateEnum(AccountCategoryEnum.DPM);
        account.setAccountTypeId(String.valueOf(baseAccType.getCode()));
        return account;
    }

    /**
     * 转化余额明细查询条件
     * @param request
     * @return
     */
    public static GetBalanceListReq convertToAccountListRequest(AccBalanceListRequest request) {
        GetBalanceListReq req = new GetBalanceListReq();
        req.setAccountId(request.getAccountId());
        req.setAccountType(request.getAccountType());
        req.setMemberId(request.getMemberId());
        GetBalanceListRequestParam param = request.getBalanceRequest();
        if (null != param) {
            req.setEndTime(param.getEndTime());
            req.setStartTime(param.getStartTime());
            req.setOrderMode(OrderModeEnum.getByCode(param.getOrderMode()));
            req.setPageId(param.getPageId());
            req.setPageSize(param.getPageSize());
            req.setTxnType(TxnTypeEnum.getByCode(param.getTxnType()));
            if(param.getNeedSummary()!= null){
                req.setNeedSummary(param.getNeedSummary());
            }else{
                req.setNeedSummary(false);
            }
        }
        return req;

    }

    /**
     * 转化账户余额明细
     * @param request
     * @return
     */
    public static AccountBalanceListResp convertToBalanceListResp(AccountBalance request) {

        AccountBalanceListResp resp = new AccountBalanceListResp();
        resp.setAfterAmt(request.getAfterAmt());
        resp.setAfterAvailAmt(request.getAfterAvailAmt());
        resp.setPayCode(request.getPayCode());
        resp.setProductCode(request.getProductCode());
        resp.setRemark(request.getRemark());
        resp.setSummary(request.getSummary());
        resp.setSysTraceNo(request.getSysTraceNo());
        resp.setTxnAmt(request.getTxnAmt());
        resp.setTxnTime(request.getTxnTime());
        resp.setTxnType(request.getTxnType());
        return resp;

    }

    /**
     * 重新构建开基本户请求对象
     * @param account
     * @param member
     * @return
     */
    public static AccountDomain reBuildOpenBaseAccountRequest(AccountDomain account, MemberTmMember member) {
        BasicAccountType baseAccType = member.getBaseAccountType();
        String alias = member.getMemberId() + baseAccType.getMessage();
        account.setMemberId(member.getMemberId());
        account.setAccountName(alias);
        return account;
    }

    /**
     * 更改账户状态请求对象转换成账户状态信息对象
     * @param request
     * @return
     */
    public static AccountDomain convert(UpdateAccountFreezeStatusRequest request) {
        AccountDomain info = new AccountDomain();
        info.setAccountId(request.getAccountId());
        info.setAccountType(request.getAccountType());
        info.setAccountTypeId(String.valueOf(request.getAccountType()));
        info.setMemberId(request.getMemberId());
        info.setFreezeStatus(request.getFreezeStatus());
        return info;
    }



    /**
     * 账户领域对象转换成账户关系响应对象
     * @param acct
     * @return
     */
    public static AccountInfo convertToAccountRef(AccountDomain acct) {
        AccountInfo info = new AccountInfo();
        AccountAttributeEnum attr = acct.getAccountAttribute();
        info.setAccountAttribute(attr == null ? null : attr.getCode());
        info.setAccountId(acct.getAccountId());
        info.setAccountType(acct.getAccountType());
        info.setAlias(acct.getAccountName());
        info.setMemberId(acct.getMemberId());

        return info;
    }

    public static AccountDomain convertToAccount(MemberTrMemberAccount acctDO) {
        AccountDomain acct = new AccountDomain();
        acct.setAccountAttribute(AccountAttributeEnum.getByCode(acctDO.getAccountAttribute().longValue()));
        acct.setAccountId(acctDO.getAccountId());
        acct.setAccountType(acctDO.getAccountType().longValue());
        acct.setAccountName(acctDO.getAlias());
        acct.setMemberId(acctDO.getMemberId());
        acct.setOriginalRequestNo(acctDO.getOriginalRequestNo());
        acct.setCateEnum(AccountCategoryEnum.getByCode(acctDO.getCategory()));
        acct.setAccountTypeId(acctDO.getTypeId());
        return acct;
    }
    /**
     * 账户领域对象转换为对外接口对象
     * @param acct
     * @return
     */
    public static Account convertToAccount(AccountDomain acct) {
        Account account = new Account();
        account.setAccountId(acct.getAccountId());
        account.setAccountName(acct.getAccountName());
        account.setAccountType(acct.getAccountType());
        account.setActivateStatus(acct.getActivateStatus());
        account.setAvailableBalance(acct.getAvailableBalance());
        account.setBalance(acct.getBalance());
        account.setCurrencyCode(acct.getCurrencyCode());
        account.setFreezeStatus(acct.getFreezeStatus());
        account.setFrozenBalance(acct.getFrozenBalance());
        account.setLastUpdatetime(acct.getLastUpdatetime());
        account.setLifeCycleStatus(acct.getLifeCycleStatus());
        account.setMemberId(acct.getMemberId());
        account.setWithdrawBalance(acct.getWithdrawBalance());
        account.setAccountAttribute(acct.getAccountAttribute().getCode());
        return account;
    }
    
    /**
     * 账户领域对象转换为会员账户关系
     * @param acct
     * @return
     */
    public static AccountInfo convertToAccountInfo(AccountDomain acct) {
        AccountInfo account = new AccountInfo();
        account.setAccountId(acct.getAccountId());
        account.setAccountType(acct.getAccountType());
        account.setAlias(acct.getAccountName());
        account.setMemberId(acct.getMemberId());
        account.setAccountAttribute(acct.getAccountAttribute().getCode());
        return account;
    }
    public static List<AccountInfo> convertToAccountInfoList(List<AccountDomain> accts) {
    	List<AccountInfo> list = new ArrayList<AccountInfo>();
    	for (AccountDomain a : accts) {
    		AccountInfo account = convertToAccountInfo(a);
    		list.add(account);
    	}
    	return list;
    }

    /**
     * 构建开户对象
     * @param request
     * @return
     */
    public static AccountDomain buildOpenAccountRequest(OpenAccountRequest request) {
        AccountDomain account = new AccountDomain();
        account.setAccountType(request.getAccountType());
        account.setMemberId(request.getMemberId());
        account.setAccountName(request.getAlias());
        account.setActivateStatus(new Long(request.getActivateStatus()));
        account.setCateEnum(AccountCategoryEnum.DPM);
        account.setAccountTypeId(String.valueOf(request.getAccountType()));
        return account;
    }

	public static MemberTrMemberAccount convertToMemberAccount(AccountDomain ba) {
		MemberTrMemberAccount account = new MemberTrMemberAccount();
		account.setMemberId(ba.getMemberId());
		account.setOriginalRequestNo(String.valueOf(RandomUtil.randomInt(10000000)));
		account.setAccountType(ba.getAccountType().intValue());
		if(ba.getAccountType().intValue() == 101){
			account.setAccountAttribute(AccountAttributeEnum.PERSONAL_ACCOUNT.getCode().intValue());
		}else if(ba.getAccountType().intValue() == 201){
			account.setAccountAttribute(AccountAttributeEnum.INSTUTION_ACCOUNT.getCode().intValue());
		}else if(ba.getAccountType().intValue() == 301){
			account.setAccountAttribute(AccountAttributeEnum.INTENAL_ACCOUNT.getCode().intValue());
		}
		account.setAccountId(BankNumberUtil.getBrankNumber("8"));
		account.setStatus(ba.getActivateStatus().intValue());
		account.setAlias(ba.getAccountName());
		account.setCategory(AccountCategoryEnum.DPM.getCode());
		account.setTypeId(String.valueOf(ba.getAccountType()));
		return account;
	}

	public static MemberTrMemberAccount convertToMemberTrAccount(AccountDomain ba) {
		MemberTrMemberAccount account = new MemberTrMemberAccount();
		account.setMemberId(ba.getMemberId());
		account.setOriginalRequestNo(ba.getOriginalRequestNo());
		account.setAccountType(ba.getAccountType().intValue());
		account.setAccountId(ba.getAccountId());
		account.setAccountAttribute(ba.getAccountAttribute().getCode().intValue());
		account.setStatus(ba.getActivateStatus().intValue());
		account.setAlias(ba.getAccountName());
		account.setCategory(ba.getCateEnum().getCode());
		account.setTypeId(String.valueOf(ba.getAccountType()));
		return account;
	}
	
	public static AccountDomain convertToAccount(MemberTrMemberAccount acctDO,
			MemberTrMemberAccountDetail mad) {
		AccountDomain acct = new AccountDomain();
        acct.setAccountAttribute(AccountAttributeEnum.getByCode(acctDO.getAccountAttribute().longValue()));
        acct.setAccountId(acctDO.getAccountId());
        acct.setAccountType(acctDO.getAccountType().longValue());
        acct.setAccountName(acctDO.getAlias());
        acct.setMemberId(acctDO.getMemberId());
        acct.setOriginalRequestNo(acctDO.getOriginalRequestNo());
        acct.setCateEnum(AccountCategoryEnum.getByCode(acctDO.getCategory()));
        acct.setAccountTypeId(acctDO.getTypeId());
        acct.setBalance(new Money(mad.getBalance()));
        acct.setAvailableBalance(new Money(mad.getAvailableBalance()));
        acct.setFrozenBalance(new Money(mad.getFrozenBalance()));
        acct.setWithdrawBalance(new Money(mad.getWithdrawBalance()));
        acct.setActivateStatus(acctDO.getStatus().longValue());
        acct.setLastUpdatetime(acctDO.getUpdateTime());
        acct.setCurrencyCode(mad.getCurrencyCode());
        acct.setCreateTime(acctDO.getCreateTime());
        return acct;
	}

    
}

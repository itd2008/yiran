/**
 * 
 */
package com.yiran.member.validator.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.domain.AccountDomain;
import com.yiran.member.domain.MemberTrMemberAccount;
import com.yiran.member.enums.AccountCategoryEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.mapper.MemberTrMemberAccountMapper;
import com.yiran.member.utils.AccountDomainUtil;
import com.yiran.member.validator.AccountValidator;


/**
 * <p>账户验证器</p>
 */
@Service
public class AccountValidatorImpl implements AccountValidator {
	@Autowired
    private MemberTrMemberAccountMapper memberTrMemberAccountMapper;

    @Override
    public AccountDomain validateAccountRelationExists(String accountId,AccountCategoryEnum cat) throws MaBizException {
    	MemberTrMemberAccount account = memberTrMemberAccountMapper.getAccountByAccountId(accountId,cat.getCode());
        if (null == account) {
            throw new MaBizException(ResponseCode.ACCOUNT_NOT_EXIST);
        }
        return AccountDomainUtil.convertToAccount(account);
    }

    @Override
    public List<AccountDomain> validateAccountRelationExists(String memberId, String accountType,
                                                             AccountCategoryEnum cat) throws MaBizException {
    	List<String> accountTypes = null;
        if (StringUtil.isNotBlank(accountType)) {
            accountTypes = new ArrayList<String>(1);
            accountTypes.add(accountType);
        }
        List<MemberTrMemberAccount> accountDomains = memberTrMemberAccountMapper.queryByMemberAndTypeIds(memberId, cat.getCode(), accountTypes);
        if (null == accountDomains || accountDomains.isEmpty()) {
            throw new MaBizException(ResponseCode.ACCOUNT_NOT_EXIST);
        }
        List<AccountDomain> list = new ArrayList<AccountDomain>();
        for (MemberTrMemberAccount account : accountDomains) {
			list.add(AccountDomainUtil.convertToAccount(account));
		}
        return list;
    }

   
}

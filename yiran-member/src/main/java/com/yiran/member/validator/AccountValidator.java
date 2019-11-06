/**
 * 
 */
package com.yiran.member.validator;

import java.util.List;

import com.yiran.member.domain.AccountDomain;
import com.yiran.member.enums.AccountCategoryEnum;
import com.yiran.member.exception.MaBizException;


/**
 * <p>账户验证器</p>
 */
public interface AccountValidator {
    /**
     * 验证会员账户关系是否存在
     * @param accountId
     * @throws MaBizException
     */
    AccountDomain validateAccountRelationExists(String accountId,AccountCategoryEnum cat)throws MaBizException;
    
    
    /** 
     * 根据会员号，账户类型验证账户是否存在
     * @param memberId
     * @param accountType
     * @param cat
     * @throws MaBizException
     */
    List<AccountDomain> validateAccountRelationExists(String memberId,String accountType, AccountCategoryEnum cat)throws MaBizException;
}

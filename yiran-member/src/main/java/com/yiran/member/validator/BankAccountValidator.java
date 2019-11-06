package com.yiran.member.validator;

import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.exception.MaBizException;

/**
 * <p>银行卡信息验证接口</p>
 */
public interface BankAccountValidator {
     
    /**
     * 根据bankId验证银行卡绑定信息是否存在
     * @param bankId
     * @param memberId 如果不为空是否与卡匹配
     * @return
     * @throws MaBizException
     */
	MemberTrBankAccount validateBankAccountExist(Long bankId,String memberId)throws MaBizException;
    
    
    /**
     * 验证银行卡绑定信息是否存在
     * @param bankInfo
     * @return
     * @throws MaBizException
     */
	MemberTrBankAccount validateBankAccountExist(MemberTrBankAccount bankInfo)throws MaBizException;

    /**
     * 验证银行卡绑定信息是否存在并且状态为正常
     * @param bankInfo
     * @return
     * @throws MaBizException
     */
	MemberTrBankAccount validateBankAccountExistAndNormal(MemberTrBankAccount bankInfo)throws MaBizException;


}

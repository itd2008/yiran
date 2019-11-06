package com.yiran.member.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.enums.BankAccountStatusEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.mapper.MemberTrBankAccountMapper;
import com.yiran.member.validator.BankAccountValidator;

/**
 * 
 * <p>银行卡校验</p>
 */
@Service
public class BankAccountValidatorImpl implements BankAccountValidator {
    private Logger                logger = LoggerFactory.getLogger(BankAccountValidatorImpl.class);

   @Autowired
   private MemberTrBankAccountMapper memberTrBankAccountMapper;

    @Override
    public MemberTrBankAccount validateBankAccountExist(Long bankId, String memberId) throws MaBizException {
    	MemberTrBankAccount bankAccount = memberTrBankAccountMapper.selectMemberTrBankAccountById(bankId.intValue());
        if (null == bankAccount) {
            throw new MaBizException(ResponseCode.BANK_ACCOUNT_NOT_EXISTS);
        }

        if (StringUtil.isNotBlank(memberId)) {
            if (!memberId.equals(bankAccount.getMemberId())) {
                throw new MaBizException(ResponseCode.MEMBER_BANK_ACCOUNT_NOT_MATCH);
            }
        }

        return bankAccount;
    }

    @Override
    public MemberTrBankAccount validateBankAccountExist(MemberTrBankAccount bankInfo) throws MaBizException {
    	MemberTrBankAccount bankAccount = validateBankAccountExist(Long.valueOf(bankInfo.getId()),
            bankInfo.getMemberId());
        //卡已经失效直接返回
        if (BankAccountStatusEnum.DISABLED.getCode().equals(bankAccount.getStatus())) {
            throw new MaBizException(ResponseCode.BANK_ACCOUNT_NOT_EXISTS);
        }
        if (StringUtil.isBlank(bankInfo.getMemberId())) {
            bankInfo.setMemberId(bankAccount.getMemberId());
        }
        /*if (StringUtil.isNotBlank(bankAccount.getBankAccountNo())
            && StringUtil.isNotBlank(bankInfo.getBankAccountNumMask())) {
            //数据库中卡号存在，不能去更新它的掩码。掩码是系统生成的
            if (!bankInfo.getBankAccountNumMask().equals(bankAccount.getBankAccountSummary())) {
                throw new MaBizException(ResponseCode.BANK_ACCOUNT_NO_SUMMARY_CONFLICT);
            }
        }*/
        //如果传过来的卡号与数据库中的掩码一致，卡号传错了不作更新。
        /*if (StringUtil.isNotBlank(bankInfo.getBankAccountNum())
            && bankInfo.getBankAccountNum().equals(bankAccount.getBankAccountSummary())) {
            logger.warn("会员号：{}传入的卡号：{}与数据库中的掩码一致。", new Object[] { bankAccount.getMemberId(),
                    bankInfo.getBankAccountNum() });
            throw new MaBizException(ResponseCode.SET_BANK_ACCOUNT_FAIL, "传入的卡号与库中掩码一致");
        }*/
        return bankAccount;
    }

    @Override
    public MemberTrBankAccount validateBankAccountExistAndNormal(MemberTrBankAccount bankInfo)
                                                                               throws MaBizException {
    	MemberTrBankAccount bank = this.validateBankAccountExist(bankInfo);
        if (!BankAccountStatusEnum.NORMAL.getCode().equals(bank.getStatus())) {
            throw new MaBizException(ResponseCode.SET_BANK_ACCOUNT_FAIL, "只有状态为正常的卡才能调用");
        }
        return bank;
    }

}

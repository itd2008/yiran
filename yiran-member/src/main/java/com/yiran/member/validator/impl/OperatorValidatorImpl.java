package com.yiran.member.validator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.enums.AccountCategoryEnum;
import com.yiran.member.enums.LockEnum;
import com.yiran.member.enums.OperatorStatusEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.mapper.MemberTmOperatorMapper;
import com.yiran.member.validator.OperatorValidator;

/**
 * 
 * <p>操作员验证</p>
 */
@Service
public class OperatorValidatorImpl implements OperatorValidator {

	@Autowired
	private MemberTmOperatorMapper memberTmOperatorMapper;

    @Override
    public MemberTmOperator validateOperatorIdExists(String operatorId) throws MaBizException {
    	MemberTmOperator operator = memberTmOperatorMapper.selectMemberTmOperatorById(operatorId);
        if (null == operator) {
            throw new MaBizException(ResponseCode.OPERATOR_NOT_EXIST);
        }
        return operator;
    }

    @Override
    public MemberTmOperator validateOperatorStatusNormal(String operatorId) throws MaBizException {
    	MemberTmOperator operator = validateOperatorIdExists(operatorId);
        validateOperatorStatus(operator);
        return operator;
    }

    /**
     * 验证操作员状态，非正常都抛异常
     * @param operatorStatus
     * @throws MaBizException
     */
    private void validateOperatorStatus(MemberTmOperator operator) throws MaBizException {
        switch (OperatorStatusEnum.getByCode(operator.getStatus().longValue())) {
            case UNACTIVE:
                throw new MaBizException(ResponseCode.OPERATOR_UNACTIVE, "operatorId为"
                                                                         + operator.getOperatorId()
                                                                         + "的操作员未激活");
            case CANCEL:
                throw new MaBizException(ResponseCode.OPERATOR_CANCEL, "operatorId为"
                                                                       + operator.getOperatorId()
                                                                       + "的操作员已注销");
            default:
                break;
        }
    }
    /*
    @Override
    public MemberTmOperator validateOperatorNoramlAndPaypwdExists(String operatorId, String accountId)
                                                                                              throws MaBizException {
        Operator operator = this.validateOperatorAndAccountExists(operatorId, accountId);
        //验证操作员状态，非正常都抛异常
        validateOperatorStatus(operator);
        //密码是否为空
        String passward = payPwdRepository.getPasswordSha(new PayPwdQuery(operatorId, accountId));
        if (StringUtil.isBlank(passward)) {
            throw new MaBizException(ResponseCode.PASSWORD_NOT_EXISTS);
        }
        PayPassWord password = new PayPassWord();
        password.setAccountId(accountId);
        password.setPassWord(passward);
        operator.addPayPwd(password);

        return operator;
    }

    @Override
    public MemberTmOperator validateOperatorAndAccountExists(String operatorId, String accountId)
                                                                                         throws MaBizException {
        Operator operator = this.validateOperatorIdExists(operatorId);
        AccountDomain account = accountRepository.getAccountByAccountId(accountId,AccountCategoryEnum.DPM);
        if (null == account) {
            throw new MaBizException(ResponseCode.ACCOUNT_ID_NOT_EXIST);
        }
        if (!operator.getMemberId().equalsIgnoreCase(account.getMemberId())) {
            throw new MaBizException(ResponseCode.OPERATOR_ACCOUNT_ID_NOT_MATCH);
        }
        return operator;
    }*/

    @Override
    public MemberTmOperator validateOperatorExistAndNormal(String memberId) throws MaBizException {
        MemberTmOperator operator = memberTmOperatorMapper.selectMemberTmOperatorByMemberId(memberId);
        if (null == operator) {
            throw new MaBizException(ResponseCode.OPERATOR_NOT_EXIST);
        }
        validateOperatorStatus(operator);
        return operator;
    }
    
    @Override
    public MemberTmOperator validateDefaultOperatorNormal(String memberId) throws MaBizException {
    	MemberTmOperator operator = memberTmOperatorMapper.selectMemberTmOperatorByMemberId(memberId);
        if (null == operator) {
            throw new MaBizException(ResponseCode.OPERATOR_NOT_EXIST);
        }
        validateOperatorStatus(operator);
        return operator;
    }
     
    public void validateLoginPassWord(String passWord) throws MaBizException {
        //判断登陆密码是否存在
        if (StringUtil.isBlank(passWord)) {
            throw new MaBizException(ResponseCode.LOGIN_PASSWORD_NOT_EXISTS);
        }
    }
    
    @Override
    public void validateOperatorLockStatus(MemberTmOperator operator) throws MaBizException {
        switch (LockEnum.getByCode(operator.getLockStatus().longValue())){
        	case LOCKED:
        		throw new MaBizException(ResponseCode.OPERATOR_LOCKED, "operatorId为"
                                                                     + operator.getOperatorId()
                                                                     + "的操作员已锁定");
        	default:
                break;
        }
    }
/*
	@Override
	public MemberTmOperator validateOperatorNoramlAndMPaypwdExists(String operatorId, String accountId) throws MaBizException {
		MemberTmOperator operator = this.validateOperatorAndAccountExists(operatorId, accountId);
        //验证操作员状态，非正常都抛异常
        validateOperatorStatus(operator);
        //app端支付密码是否为空
        String mpassward = payPwdRepository.getMPasswordSha(new PayPwdQuery(operatorId, accountId));
        if (StringUtil.isBlank(mpassward)) {
            throw new MaBizException(ResponseCode.MPASSWORD_NOT_EXISTS);
        }
        PayPassWord password = new PayPassWord();
        password.setAccountId(accountId);
        password.setMpassWord(mpassward);
        operator.addPayPwd(password);

        return operator;
	}
*/

}

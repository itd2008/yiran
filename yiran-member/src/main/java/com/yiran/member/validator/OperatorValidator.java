package com.yiran.member.validator;

import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.exception.MaBizException;

/**
 * <p>操作员验证类</p>
 */
public interface OperatorValidator {
	
	
    /**
     * 验证操作员，账户是否存在，验证操作员，账户是否属于同一会员
     * @param operatorId
     * @param accountId
     * @return
     * @throws MaBizException
     */
    //public MemberTmOperator validateOperatorAndAccountExists(String operatorId, String accountId) throws MaBizException;

    /**
     * 验证操作员是否存在
     * @param operatorId
     * @return
     * @throws MaBizException
     */
    public MemberTmOperator validateOperatorIdExists(String operatorId) throws MaBizException;

    /**
     * 验证操作员存在，且状态正常
     * @param operatorId
     * @return
     * @throws MaBizException
     */
    public MemberTmOperator validateOperatorStatusNormal(String operatorId) throws MaBizException;

    /**
     * 根据会员号，登陆名，平台类型查询操作员信息并验证状态是否正常
     * @param memberId
     * @param loginName
     * @param platFormType
     * @return
     * @throws MaBizException
     */
    public MemberTmOperator validateOperatorExistAndNormal(String memberId) throws MaBizException;

    /**
     *  验证个人会员的操作员信息，状态是否正常
     * @param memberId
     * @return
     * @throws MaBizException
     */
    public MemberTmOperator validateDefaultOperatorNormal(String memberId) throws MaBizException;

    /**
     * 验证登陆密码是否存在
     * @param passWord
     * @throws MaBizException
     */
    public void validateLoginPassWord(String passWord) throws MaBizException;

    /**
     * <li>验证操作员,账户,支付密码是否存在</li>
     * <li>操作员状态是否正常</li>
     * <li>验证支付密码与解锁支付密码使用</li>
     * @param operatorId
     * @param accountId
     * @return
     * @throws MaBizException
     */
    //public MemberTmOperator validateOperatorNoramlAndPaypwdExists(String operatorId, String accountId) throws MaBizException;
    
    public void validateOperatorLockStatus(MemberTmOperator operator) throws MaBizException ;
    
    /**
     * <li>验证操作员,账户,支付密码是否存在</li>
     * <li>操作员状态是否正常</li>
     * <li>验证app端支付密码与解锁支付密码使用</li>
     * @param operatorId
     * @param accountId
     * @return
     * @throws MaBizException
     */
    //public MemberTmOperator validateOperatorNoramlAndMPaypwdExists(String operatorId, String accountId) throws MaBizException;
}

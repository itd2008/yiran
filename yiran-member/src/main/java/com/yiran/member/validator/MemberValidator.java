/**
 * 
 */
package com.yiran.member.validator;

import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.exception.MaBizException;

/**
 * <p>会员业务状态验证器</p>
 */
public interface MemberValidator {

    /**
     * 验证会员是否存在
     * @param memberId
     * @return
     * @throws MaBizException
     */
	MemberTmMember  validateMemberExist(String memberId) throws MaBizException;

	MemberTmMember validateMemberExistByIdentity(String identity, int pid) throws MaBizException;

	MemberTmMember validateMemberExistAndNotCancelled(String memberId) throws MaBizException;

	/**
     * 查询会员是否存在并且状态是否正常
     * @param identity
     * @param platformType
     * @return
     * @throws MaBizException
     */
	MemberTmMember validateMemberExistAndNormal(String identity,int platformType) throws MaBizException;
    
}

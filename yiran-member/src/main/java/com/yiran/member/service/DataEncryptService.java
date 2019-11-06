/**
 * 
 */
package com.yiran.member.service;

import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.domain.PersonalMember;

/**
 * <p>数据加密服务接口</p>
 */
public interface DataEncryptService {

    /**
     * 创建个人会员时，敏感数据进行加密
     * @param member
     * @param operator
     */
    public void encrypt(PersonalMember member,MemberTmOperator operator)throws Exception;
}

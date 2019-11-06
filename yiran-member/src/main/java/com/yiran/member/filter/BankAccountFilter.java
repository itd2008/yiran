package com.yiran.member.filter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.exception.MaBizException;


public interface BankAccountFilter {

    /**
     * 过滤相似的银行卡信息
     * @param list
     * @param request
     * @param lists
     * @return
     * @throws MaBizException 
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    boolean doFilter(List<MemberTrBankAccount> list, MemberTrBankAccount request,List<MemberTrBankAccount> lists) throws MaBizException;
}

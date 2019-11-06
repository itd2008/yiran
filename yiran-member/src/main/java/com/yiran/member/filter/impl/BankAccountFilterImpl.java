package com.yiran.member.filter.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.enums.BankAccountStatusEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.filter.BankAccountFilter;

/**
 * <p>银行卡过滤</p>
 */
@Service
public class BankAccountFilterImpl implements BankAccountFilter {

    private Logger logger = LoggerFactory.getLogger(BankAccountFilterImpl.class);

    /**
     * 过滤属性不同的银行卡
     * @param list
     * @param request
     * @throws MaBizException 
     * @throws NoSuchMethodException 
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    @Override
    public boolean doFilter(List<MemberTrBankAccount> list, MemberTrBankAccount request, List<MemberTrBankAccount> newList)
                                                                                                   throws MaBizException {
        boolean isLock = false;
        try {
            if (CollectionUtils.isEmpty(list)) {
                return isLock;
            }
            for (MemberTrBankAccount account : list) {
                if (BankAccountStatusEnum.LOCKED.getCode().equals(account.getStatus())) {
                    isLock = true;
                    break;
                }
               
                newList.add(account);
                
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("过滤属性异常,请求" + request, e);
            throw new MaBizException(ResponseCode.UNKNOWN, "过滤属性异常");
        }
        return isLock;
    }

    private boolean compare(List<String> propertieNames, MemberTrBankAccount request, MemberTrBankAccount orgAccount)
                                                                                                     throws IllegalAccessException,
                                                                                                     InvocationTargetException,
                                                                                                     NoSuchMethodException {
        boolean result = true;
        for (String name : propertieNames) {
            Object value;
            value = PropertyUtils.getProperty(request, name);
            Object orgValue = PropertyUtils.getProperty(orgAccount, name);
            if (!compare(value, orgValue)) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean compare(Object o, Object o1) {
        if (o instanceof String) {
            String a = StringUtil.defaultIfBlank((String) o, "");
            String b = StringUtil.defaultIfBlank((String) o1, "");
            return StringUtil.equals(a, b);
        } else if (o instanceof Integer) {
            return (Integer) o == (Integer) o1;
        } else {
            return false;
        }

    }
}

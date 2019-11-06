package com.yiran.member.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.enums.PayAttributeEnum;
import com.yiran.member.request.BankAccountRequest;

public class BankAcctDomainUtil {

    /**
     * 银行卡账户转换 银行卡领域对象-> 银行卡Do对象
     * @param bankAccount
     * @return
     */
    /*public static TrBankAccountDO convertBankAccountDo(BankAccount bankAccount) {
        TrBankAccountDO bankAccountDO = new TrBankAccountDO();
        if (null != bankAccount.getBankcardId()) {
            bankAccountDO.setId(bankAccount.getBankcardId());
        }
        bankAccountDO.setAccountNoSummary(bankAccount.getBankAccountSummary());
        bankAccountDO.setAlias(bankAccount.getAlias());
        //银行账户名称
        bankAccountDO.setBankAccountName(bankAccount.getRealName());
        bankAccountDO.setBankAccountNo(bankAccount.getBankAccountNo());
        bankAccountDO.setBankBranch(bankAccount.getBankBranch());
        bankAccountDO.setBankId(bankAccount.getBankCode());
        bankAccountDO.setBankName(bankAccount.getBankName());
        if (null != bankAccount.getCardAttribute()) {
            bankAccountDO.setCardAttribute(bankAccount.getCardAttribute());
        }
        bankAccountDO.setCardSkin(bankAccount.getCardSkin());
        if (null != bankAccount.getCardType()) {
            bankAccountDO.setCardType(bankAccount.getCardType());
        }
        bankAccountDO.setCity(bankAccount.getCity());
        bankAccountDO.setIsSigning(bankAccount.getIsSigning());
        bankAccountDO.setMemberId(bankAccount.getMemberId());
        if (null != bankAccount.getIsVerified()) {
            bankAccountDO.setIsVerified(bankAccount.getIsVerified());
        }
        //       bankAccountDO.setMemo(bankAccount.get)
        bankAccountDO.setProvince(bankAccount.getProvince());
        if (null != bankAccount.getStatus()) {
            bankAccountDO.setStatus(bankAccount.getStatus());
        }
        bankAccountDO.setExtendable(bankAccount.getExtention());

        bankAccountDO.setSignNo(bankAccount.getSignNo());
        bankAccountDO.setSignId(bankAccount.getSignId());
        if (null != bankAccount.getCertType()) {
            bankAccountDO.setCertType(bankAccount.getCertType().getCode());
        }

        bankAccountDO.setCertNo(bankAccount.getCertNo());
        bankAccountDO.setCvNo(bankAccount.getCvNo());
        bankAccountDO.setCardValidDate(bankAccount.getCardValidDate());
        bankAccountDO.setAgreementValidDate(bankAccount.getAgreementValidDate());
        bankAccountDO.setMobileNo(bankAccount.getMobileNo());
        if (null != bankAccount.getPayAttribute())
            bankAccountDO.setPayAttribute(bankAccount.getPayAttribute().getCode());
        if (null != bankAccount.getIsFillCertNo()) {
            bankAccountDO.setIsFillCertNo(bankAccount.getIsFillCertNo().getCode());
        }
        bankAccountDO.setChannelCode(bankAccount.getChannelCode());
        bankAccountDO.setActivateDate(bankAccount.getActivateDate());
        bankAccountDO.setBranchNo(bankAccount.getBranchNo());
        bankAccountDO.setFinancialCard(bankAccount.getFinancialCard());
        return bankAccountDO;
    }

    public static BankAccount convertBankAccount(TrBankAccountDO bankAccountDO) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setBankcardId(bankAccountDO.getId());
        bankAccount.setBankAccountSummary(bankAccountDO.getAccountNoSummary());
        bankAccount.setAlias(bankAccountDO.getAlias());
        //银行账户名称
        bankAccount.setRealName(bankAccountDO.getBankAccountName());
        bankAccount.setBankAccountNo(bankAccountDO.getBankAccountNo());
        bankAccount.setBankBranch(bankAccountDO.getBankBranch());
        bankAccount.setBankCode(bankAccountDO.getBankId());
        bankAccount.setBankName(bankAccountDO.getBankName());
        bankAccount.setCardAttribute(bankAccountDO.getCardAttribute());
        bankAccount.setCardSkin(bankAccountDO.getCardSkin());
        bankAccount.setCardType(bankAccountDO.getCardType());
        bankAccount.setCity(bankAccountDO.getCity());
        bankAccount.setIsSigning(bankAccountDO.getIsSigning());
        bankAccount.setMemberId(bankAccountDO.getMemberId());
        bankAccount.setIsVerified(bankAccountDO.getIsVerified());
        bankAccount.setProvince(bankAccountDO.getProvince());
        bankAccount.setStatus(bankAccountDO.getStatus());
        bankAccount.setExtention(bankAccountDO.getExtendable());

        bankAccount.setSignNo(bankAccountDO.getSignNo());
        bankAccount.setSignId(bankAccountDO.getSignId());
        if (null != bankAccountDO.getCertType()) {
            bankAccount.setCertType(CertTypeEnum.getByCode(bankAccountDO.getCertType()));
        }

        bankAccount.setCertNo(bankAccountDO.getCertNo());
        bankAccount.setCertNoMask(SecurityUtil.getMask(bankAccountDO.getCertNo()));
        bankAccount.setCertNoTicket(SecurityUtil.getTicket(bankAccountDO.getCertNo()));

        bankAccount.setCvNo(bankAccountDO.getCvNo());
        bankAccount.setCardValidDate(bankAccountDO.getCardValidDate());
        bankAccount.setAgreementValidDate(bankAccountDO.getAgreementValidDate());
        bankAccount.setMobileNo(bankAccountDO.getMobileNo());
        bankAccount.setMobileNoMask(SecurityUtil.getMask(bankAccountDO.getMobileNo()));
        bankAccount.setMobileNoTicket(SecurityUtil.getTicket(bankAccountDO.getMobileNo()));
        if (null != bankAccountDO.getPayAttribute()) {
            bankAccount
                .setPayAttribute(PayAttributeEnum.getByCode(bankAccountDO.getPayAttribute()));
        }
        if (null != bankAccountDO.getIsFillCertNo()) {
            bankAccount.setIsFillCertNo(YesNoEnum.getByCode(bankAccountDO.getIsFillCertNo()));
        }
        bankAccount.setChannelCode(bankAccountDO.getChannelCode());
        bankAccount.setActivateDate(bankAccountDO.getActivateDate());
        bankAccount.setBranchNo(bankAccountDO.getBranchNo());
        
        bankAccount.setUpdateTime(bankAccountDO.getUpdateTime());
        bankAccount.setFinancialCard(bankAccountDO.getFinancialCard());
        
        return bankAccount;
    }

    public static BankAccount convert(BankAcctInfo bankAccountDO) {
        BankAccount bankAccount = new BankAccount();
        if (StringUtil.isNotBlank(bankAccountDO.getBankcardId())) {
            bankAccount.setBankcardId(Long.valueOf(bankAccountDO.getBankcardId()));
        }
        bankAccount.setBankAccountSummary(bankAccountDO.getBankAccountNumMask());
        bankAccount.setAlias(bankAccountDO.getAlias());
        //银行账户名称
        bankAccount.setRealName(bankAccountDO.getRealName());
        bankAccount.setBankAccountNo(bankAccountDO.getBankAccountNum());
        bankAccount.setBankBranch(bankAccountDO.getBankBranch());
        bankAccount.setBankCode(bankAccountDO.getBankCode());
        bankAccount.setBankName(bankAccountDO.getBankName());
        bankAccount.setCardAttribute(bankAccountDO.getCardAttribute());
        bankAccount.setCardSkin(bankAccountDO.getCardSkin());
        bankAccount.setCardType(bankAccountDO.getCardType());
        bankAccount.setCity(bankAccountDO.getCity());
        bankAccount.setIsSigning(bankAccountDO.getIsSigning());
        bankAccount.setMemberId(bankAccountDO.getMemberId());
        bankAccount.setIsVerified(bankAccountDO.getIsVerified());
        bankAccount.setProvince(bankAccountDO.getProvince());
        bankAccount.setStatus(bankAccountDO.getStatus());
        bankAccount.setExtention(bankAccountDO.getExtention());

        bankAccount.setSignNo(bankAccountDO.getSignNum());
        bankAccount.setSignId(bankAccountDO.getSignId());
        bankAccount.setCertType(CertTypeEnum.getByCode(bankAccountDO.getCertType()));
        bankAccount.setCertNo(bankAccountDO.getCertNum());
        bankAccount.setCvNo(bankAccountDO.getCvNum());
        bankAccount.setCardValidDate(bankAccountDO.getCardValidDate());
        bankAccount.setAgreementValidDate(bankAccountDO.getAgreementValidDate());
        bankAccount.setMobileNo(bankAccountDO.getMobileNum());
        bankAccount.setPayAttribute(PayAttributeEnum.getByCode(bankAccountDO.getPayAttribute()));
        bankAccount.setIsFillCertNo(YesNoEnum.getByCode(bankAccountDO.getIsFillCertNum()));
        bankAccount.setChannelCode(bankAccountDO.getChannelCode());
        bankAccount.setBranchNo(bankAccountDO.getBranchNo());
        
        bankAccount.setFinancialCard(bankAccountDO.getFinancialCard());
        
        return bankAccount;
    }*/
    
    public static MemberTrBankAccount convertBankAccount(BankAccountRequest request) {
    	MemberTrBankAccount bankAccount = new MemberTrBankAccount();
        bankAccount.setMemberId(request.getMemberId());
        bankAccount.setCardType(request.getCardType());
        bankAccount.setCardAttribute(request.getCardAttribute());
        bankAccount.setIsSigning(request.getIsSigning());
        bankAccount.setIsVerified(request.getIsVerified());
        bankAccount.setStatus(request.getStatus());
        bankAccount.setPayAttribute(PayAttributeEnum.getByCode(request.getPayAttribute()).getCode());
        bankAccount.setBankAccountNo(request.getBankAccountNum());
        bankAccount.setFinancialCard(request.getFinancialCard());
        return bankAccount;
    }

    /*public static BankAccount convertBankAccount(BankQPayAccountRequest request) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setMemberId(request.getMemberId());
        bankAccount.setCardType(request.getCardType());
        bankAccount.setCardAttribute(request.getCardAttribute());
        bankAccount.setIsSigning(request.getIsSigning());
        bankAccount.setIsVerified(request.getIsVerified());
        bankAccount.setStatus(request.getStatus());
        bankAccount.setPayAttribute(PayAttributeEnum.getByCode(request.getPayAttribute()));
        bankAccount.setBankAccountNo(request.getBankAccountNum());
        bankAccount.setChannelCode(request.getChannelCode());
        bankAccount.setCertNo(request.getCertNo());
        bankAccount.setRealName(request.getBankAccountName());
        bankAccount.setMobileNo(request.getMobileNo());
        bankAccount.setCertType(CertTypeEnum.ID_CARD);
        return bankAccount;
    }*/

    /*public static BankAccountInfo convertBankAccountInfo(BankAccount bankAccount) {
        if (bankAccount == null) {
            return null;
        }
        BankAccountInfo toBank = convertBankAccountInfo(bankAccount, new BankAccountInfo());
        //FIXME:如果手机号没有，取会员绑定的手机号的掩码
        toBank.setMobileNum(bankAccount.getMobileNoMask());
        if (bankAccount.getCertType() != null) {
            toBank.setCertType(bankAccount.getCertType().getCode());
        }
        //取证件号的掩码
        toBank.setCertNum(bankAccount.getCertNoMask());
        return toBank;
    }*/
    
    /*private static BankAccountInfo convertBankAccountInfo(BankAccount bankAccount,
                                                          BankAccountInfo toBank) {
        toBank.setBankcardId(String.valueOf(bankAccount.getBankcardId()));
        toBank.setAlias(bankAccount.getAlias());
        toBank.setRealName(bankAccount.getRealName());
        toBank.setBankAccountNumMask(bankAccount.getBankAccountSummary());
        toBank.setBankBranch(bankAccount.getBankBranch());
        toBank.setBankCode(bankAccount.getBankCode());
        toBank.setBankName(bankAccount.getBankName());
        toBank.setCardAttribute(bankAccount.getCardAttribute());
        toBank.setCardSkin(bankAccount.getCardSkin());
        toBank.setCardType(bankAccount.getCardType());
        toBank.setCity(bankAccount.getCity());
        toBank.setIsSigning(bankAccount.getIsSigning());
        toBank.setMemberId(bankAccount.getMemberId());
        toBank.setIsVerified(bankAccount.getIsVerified());
        toBank.setProvince(bankAccount.getProvince());
        toBank.setStatus(bankAccount.getStatus());
        if (bankAccount.getPayAttribute() != null) {
            toBank.setPayAttribute(bankAccount.getPayAttribute().getCode());
        }
        toBank.setExtention(bankAccount.getExtention());
        toBank.setActivateDate(bankAccount.getActivateDate());
        toBank.setChannelCode(bankAccount.getChannelCode());
        toBank.setBranchNo(bankAccount.getBranchNo());
        toBank.setCardValidDate(bankAccount.getCardValidDate());
        
        // 将手机号的ticket放入扩展字段
        JSONObject json = JSONObject.parseObject(toBank.getExtention());
        if(json == null) {
            json = new JSONObject();
        }
        json.put(MaConstant.MOBILENO_TICKET, bankAccount.getMobileNoTicket());
        toBank.setExtention(json.toJSONString());
        
        toBank.setUpdateTime(bankAccount.getUpdateTime());
        toBank.setFinancialCard(bankAccount.getFinancialCard());
        return toBank;

    }*/

    /*public static BankAcctDetailInfo convertBankAcctInfo(BankAccount srcBank) {
        if (srcBank == null) {
            return null;
        }
        BankAcctDetailInfo destBank = (BankAcctDetailInfo) convertBankAccountInfo(srcBank,
            new BankAcctDetailInfo());
        destBank.setBankAccountNum(srcBank.getBankAccountNo());
        destBank.setSignNum(srcBank.getSignNo());
        destBank.setSignId(srcBank.getSignId());
        if (srcBank.getCertType() != null) {
            destBank.setCertType(srcBank.getCertType().getCode());
        }
        //证件号的ticket
        destBank.setCertNum(srcBank.getCertNoTicket());
        destBank.setCvNum(srcBank.getCvNo());
        destBank.setCardValidDate(srcBank.getCardValidDate());
        destBank.setAgreementValidDate(srcBank.getAgreementValidDate());
        //FIXME:如果手机号没有，取会员绑定的手机号的ticket
        destBank.setMobileNum(srcBank.getMobileNoTicket());
        destBank.setChannelCode(srcBank.getChannelCode());
        destBank.setActivateDate(srcBank.getActivateDate());
        destBank.setBranchNo(srcBank.getBranchNo());
        
        destBank.setFinancialCard(srcBank.getFinancialCard());
        return destBank;
    }*/

    /**
     * 是否查询会员下银行卡信息.
     * 如果查询条件为memberId + status=null 缓存结果
     * @param bank
     * @return
     */
    public static boolean isQueryMemberBank(MemberTrBankAccount bank) {
        if (StringUtil.isNotEmpty(bank.getMemberId()) && bank.getStatus() == null) {

            if (StringUtil.isNotEmpty(bank.getBankAccountNo())) {
                return false;
            }
            if (bank.getCardType() != null) {
                return false;
            }
            if (bank.getCardAttribute() != null) {
                return false;
            }
            if (bank.getIsVerified() != null) {
                return false;
            }
            if (StringUtil.isNotEmpty(bank.getIsSigning())) {
                return false;
            }
            if (bank.getPayAttribute() != null) {
                return false;
            }
            return true;
        }
        return false;
    }

    /**
     * 根据查询条件过滤银行卡
     * @return
     */
    public static List<MemberTrBankAccount> filterBankAccount(List<MemberTrBankAccount> banks, MemberTrBankAccount query) {
        List<MemberTrBankAccount> rightBanks = new ArrayList<MemberTrBankAccount>(banks.size());
        for (MemberTrBankAccount item : banks) {
            if (StringUtil.isNotEmpty(query.getBankAccountNo())) {
                if (!query.getBankAccountNo().equals(item.getBankAccountNo())) {
                    continue;
                }
            }
            if (query.getPayAttribute() != null) {
                if (!query.getPayAttribute().equals(item.getPayAttribute())) {
                    continue;
                }
            }

            if (query.getCardType() != null) {
                if (!query.getCardType().equals(item.getCardType())) {
                    continue;
                }
            }
            if (query.getCardAttribute() != null) {
                if (!query.getCardAttribute().equals(item.getCardAttribute())) {
                    continue;
                }
            }
            if (query.getIsVerified() != null) {
                if (!query.getIsVerified().equals(item.getIsVerified())) {
                    continue;
                }
            }
            if (StringUtil.isNotEmpty(query.getIsSigning())) {
                if (!query.getIsSigning().equals(item.getIsSigning())) {
                    continue;
                }
            }
            if (query.getStatus() != null) {
                if (!query.getStatus().equals(item.getStatus())) {
                    continue;
                }
            }
            
            if (StringUtils.isNotBlank(query.getFinancialCard()))
            {
            	if (!query.getFinancialCard().equals(item.getFinancialCard()))
            	{
            		continue;
            	}
            }
            rightBanks.add(item);
        }
        return rightBanks;
    }

   /* public static BankAccount convert(BankAccUnbindRequest req) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setSignNo(req.getSignNum());
        if (StringUtil.isBlank(req.getSignNum())) {
            //以传入的signNum 为做准
            bankAccount.setSignId(req.getSignId());
        }
        bankAccount.setChannelCode(req.getChannelCode());
        return bankAccount;
    }

    public static BankAccount convert(BankAccDetailRequest req) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setSignNo(req.getSignNum());
        if (StringUtil.isBlank(req.getSignNum())) {
            //以传入的signNum 为做准
            bankAccount.setSignId(req.getSignId());
        }
        bankAccount.setMemberId(req.getMemberId());
        bankAccount.setChannelCode(req.getChannelCode());
        return bankAccount;
    }
    
    public static BankAccountSample convert(TrBankAccountDO ba) {
    	BankAccountSample bas = new BankAccountSample();
    	BeanUtils.copyProperties(ba, bas);
    	bas.setRealName(ba.getBankAccountName());
    	return bas;
    }
    
    public static List<BankAccountSample> convertToList(List<TrBankAccountDO> list) {
    	List<BankAccountSample> rstLsit = new ArrayList<BankAccountSample>();
    	if (list != null && list.size() > 0) {
    		for (TrBankAccountDO ba : list) {
    			BankAccountSample convertObj = convert(ba); 
    			rstLsit.add(convertObj);
    		}
    	}
    	return rstLsit;
    }*/
}

package com.yiran.payorder.converter;

import java.util.ArrayList;
import java.util.List;

import com.yiran.paychannel.enums.CardType;
import com.yiran.paychannel.enums.CertType;
import com.yiran.payorder.domain.PayBankCardInfo;
import com.yiran.payorder.domaindo.PayBankCardInfoDO;
import com.yiran.payorder.enums.BankCardStatus;

/**
 * 银行卡信息转换
 * @author pandaa
 *
 */
public class BankCardInfoConverter {

	public static List<PayBankCardInfo> convert(List<PayBankCardInfoDO> bankCardInfoDOList) {
		if (bankCardInfoDOList == null) {
            return null;
        }
        List<PayBankCardInfo> bankCardInfoList=new ArrayList<PayBankCardInfo>();
        for (PayBankCardInfoDO bankCardInfoDO:bankCardInfoDOList) {
            bankCardInfoList.add(convert(bankCardInfoDO));
        }
        return bankCardInfoList;
	}
	public static PayBankCardInfo convert(PayBankCardInfoDO bankCardInfoDO) {
        if (bankCardInfoDO == null) {
            return null;
        }
        PayBankCardInfo to = new PayBankCardInfo();
        to.setBankCardId(bankCardInfoDO.getBankCardId());
        to.setTargetInstCode(bankCardInfoDO.getTargetInstCode());
        to.setCardHolder(bankCardInfoDO.getCardHolder());
        to.setCvv2(bankCardInfoDO.getCvv2());
        to.setBankBranch(bankCardInfoDO.getBankBranch());
        to.setCardNo(bankCardInfoDO.getCardNo());
        to.setValidDate(bankCardInfoDO.getValidDate());
        to.setCertNo(bankCardInfoDO.getCertNo());
        to.setMobileNo(bankCardInfoDO.getMobileNo());
        to.setStatus(BankCardStatus.getByCode(bankCardInfoDO.getStatus()));
        to.setCardType(CardType.getByCode(bankCardInfoDO.getCardType()));
        to.setCertType(CertType.getByCode(bankCardInfoDO.getCertType()));
        return to;
    }
	
}

package com.yiran.payorder.service;

import java.util.List;

import com.yiran.payorder.domain.PayBankCardInfo;
import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.request.BankCardRequest;
import com.yiran.payorder.response.BankCardResponse;

public interface IBankCardService {

	/**
     * 根据银行卡id，完成签约的信息
     * @param bankCardId
     * @param memberId
     * @param signSource
     * @return
     */
    public List<PaySignInfo> fetchFinishSignInfo(String bankCardId,String memberId, String signSource);
    
    public boolean isExistsBankCardInfo(PayBankCardInfo bankCardInfo);

    /**
     * 根据银行卡id，渠道编码获取签约信息
     * @param bankCardId
     * @param fundChannelCode
     * @return
     */
	public List<PaySignInfo> fetchSignInfoByBankCardIdAndChannel(String bankCardId, String fundChannelCode);

	public String findBankCardInfo(String request);


}

package com.yiran.payorder.converter;

import java.util.ArrayList;
import java.util.List;

import com.yiran.paychannel.enums.AccessChannel;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.domaindo.PaySignInfoDO;
import com.yiran.payorder.enums.SignStatus;

/**
 * 签约信息转换
 * @author pandaa
 *
 */
public class SignInfoConverter {

	public static List<PaySignInfo> convert(List<PaySignInfoDO> signInfoDOList) {
		if (signInfoDOList == null) {
            return null;
        }
        List<PaySignInfo> SignInfoList=new ArrayList<PaySignInfo>();
        for (PaySignInfoDO signInfoDO:signInfoDOList) {
            SignInfoList.add(convert(signInfoDO));
        }
        return SignInfoList;
	}
	
	public static PaySignInfo convert(PaySignInfoDO signInfoDO) {
        if (signInfoDO == null) {
            return null;
        }
        PaySignInfo to = new PaySignInfo();
        to.setBankCardId(signInfoDO.getBankCardId());
        to.setCardNo(signInfoDO.getCardNo());
        to.setSignReqId(signInfoDO.getSignReqId());
        to.setSignSource(signInfoDO.getSignSource());
        to.setChannelExtInfo(signInfoDO.getChannelExtInfo());
        to.setChannelSignNo(signInfoDO.getChannelSignNo());
        to.setSignSmsSender(signInfoDO.getSignSmsSender());
        to.setChannelSignTime(signInfoDO.getChannelSignTime());
        to.setCmfSeqNo(signInfoDO.getCmfSeqNo());
        to.setChannelSignValid(signInfoDO.getChannelSignValid());
        to.setFailReason(signInfoDO.getFailReason());
        to.setMemberId(signInfoDO.getMemberId());
        to.setSourceExtInfo(signInfoDO.getSourceExtInfo());
        to.setSourceSeqNo(signInfoDO.getSourceSeqNo());
        to.setTargetInstCode(signInfoDO.getTargetInstCode());
        to.setFundChannelCode(signInfoDO.getFundChannelCode());
        to.setStatus(SignStatus.getByCode(signInfoDO.getStatus()));
        to.setPayMode(PayMode.getByCode(signInfoDO.getPayMode()));
        AccessChannel accessChannel=AccessChannel.getByCode(signInfoDO.getAccessChannel());
        if (accessChannel==null){
            to.setAccessChannel(AccessChannel.WEB);
        }else {
            to.setAccessChannel(accessChannel);
        }
        return to;
    }
}

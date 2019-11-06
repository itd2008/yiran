package com.yiran.payorder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yiran.payorder.converter.BankCardInfoConverter;
import com.yiran.payorder.converter.SignInfoConverter;
import com.yiran.payorder.domain.PayBankCardInfo;
import com.yiran.payorder.domain.PaySignInfo;
import com.yiran.payorder.domain.ReturnInfo;
import com.yiran.payorder.domaindo.PayBankCardInfoDO;
import com.yiran.payorder.domaindo.PaySignInfoDO;
import com.yiran.payorder.enums.BankCardStatus;
import com.yiran.payorder.enums.SignStatus;
import com.yiran.payorder.mapper.PayBankCardInfoMapper;
import com.yiran.payorder.mapper.PaySignInfoMapper;
import com.yiran.payorder.request.BankCardRequest;
import com.yiran.payorder.response.BankCardResponse;
import com.yiran.payorder.service.IBankCardService;
@Service
public class BankCardService implements IBankCardService {
	private Logger logger = LoggerFactory.getLogger(BankCardService.class);
	public static final String TRUE="true";
	public static final String FALSE="false";
	public static final String STATUS="S";
	@Autowired
	private PaySignInfoMapper paySignInfoMapper;
	
	@Autowired
	private PayBankCardInfoMapper payBankCardInfoMapper;
	
	@Override
	public List<PaySignInfo> fetchFinishSignInfo(String bankCardId, String memberId, String signSource) {
		List<PaySignInfo> result=new ArrayList<PaySignInfo>();
		List<PaySignInfoDO> list = paySignInfoMapper.loadSignInfoByBankCardId(bankCardId,memberId,signSource);
        List<PaySignInfo> signInfoList = SignInfoConverter.convert(list);
        if (signInfoList!=null){
            for (PaySignInfo signInfo:signInfoList){
                if (SignStatus.SUCCESS.equals(signInfo.getStatus())){
                    result.add(signInfo);
                }
            }
        }
        return result;
	}

	/**
     * 银行卡信息是否存在
     * @param bankCardInfo
     * @return
     */
	@Override
	public boolean isExistsBankCardInfo(PayBankCardInfo bankCardInfo) {
		boolean isExists=false;
		List<PayBankCardInfoDO> list = payBankCardInfoMapper.loadByCardNo(bankCardInfo.getCardNo());
        List<PayBankCardInfo> bankCardInfoList = BankCardInfoConverter.convert(list);
        if (CollectionUtils.isEmpty(bankCardInfoList)){
            return isExists;
        }
        if(matchBankCardInfo(bankCardInfo,bankCardInfoList)){
            isExists=true;
        }
        return isExists;
	}
	
	/**
     * 银行卡信息匹配,匹配要素：银行卡号，持卡人姓名、预留手机、证件号码
     * 暂时不比较信用卡的安全码、有效期等信息
     * @param bankCardInfo
     * @param bankCardInfoList
     * @return
     */
    private boolean matchBankCardInfo(PayBankCardInfo bankCardInfo,List<PayBankCardInfo> bankCardInfoList){
        boolean isMatch=false;
        for (PayBankCardInfo bci:bankCardInfoList) {
            //无效状态
            if(BankCardStatus.INVALID.equals(bci.getStatus())){
                continue;
            }
            //卡号是否相等
            if (!bci.getCardNo().equals(bankCardInfo.getCardNo())){
                continue;
            }
            //姓名是否相等
            if (!bci.getCardHolder().equals(bankCardInfo.getCardHolder())){
                continue;
            }
            //预留手机是否相等
            if (!bci.getMobileNo().equals(bankCardInfo.getMobileNo())){
                continue;
            }
            //证件号是否相等
            if (!bci.getCertNo().equals(bankCardInfo.getCertNo())){
                continue;
            }
            isMatch=true;
            bankCardInfo.setBankCardId(bci.getBankCardId());
            break;
        }
        return  isMatch;
    }

	@Override
	public List<PaySignInfo> fetchSignInfoByBankCardIdAndChannel(String bankCardId, String fundChannelCode) {
		List<PaySignInfo> returnList=new ArrayList<PaySignInfo>();
        List<PaySignInfo> signInfoList=fetchFinishSignInfo(bankCardId,null,null);
        if (signInfoList!=null){
            for (PaySignInfo signInfo:signInfoList){
                if (fundChannelCode.equals(signInfo.getFundChannelCode())){
                    returnList.add(signInfo);
                }
            }
        }
        return returnList;
	}

	@Override
	public String findBankCardInfo(String req) {
		BankCardRequest request =  JSON.parseObject(req, BankCardRequest.class);
		BankCardResponse result = new BankCardResponse();
		ReturnInfo returnInfo=new ReturnInfo();
		logger.info("【Channel->PayOrder】渠道调CMF查询签约信息->参数："+JSON.toJSONString(request));
		if(validateIsNull(result, request, returnInfo)){//验证输入参数
			return JSON.toJSONString(result);
		}
		String fundChannelCode =request.getFundChannelCode();
		String cardNo = request.getBankCard();
		String cardName = request.getCardName();
		String idNo = request.getIdNo();
		
        logger.info("银行卡号："+cardNo);
		//根据渠道号，银行卡号，状态获取签约号，会员ID
		PaySignInfo  signInfo= SignInfoConverter.convert(paySignInfoMapper.fetchFinishSignInfoByFundChannelCode(fundChannelCode, cardNo, BankCardService.STATUS));
		logger.info("根据渠道编号，银行卡号，状态获取签约对象"+JSON.toJSONString(signInfo));
		if(signInfo!=null){
			//获取银行卡ID，根据银行卡ID查询银行卡信息
			PayBankCardInfo bankCardInfo = BankCardInfoConverter.convert(payBankCardInfoMapper.selectPayBankCardInfoById(Integer.parseInt(signInfo.getBankCardId())));
			logger.info("通过根据渠道编号卡号状态获取签约对象，在根据签约对象获取银行卡信息："+JSON.toJSONString(bankCardInfo));
			if(cardName.equals(bankCardInfo.getCardHolder()) && idNo.equals(bankCardInfo.getCertNo())){
				result.setChannelSignNo(signInfo.getChannelSignNo());
				result.setMemberId(signInfo.getMemberId());
				result.setBankCardId(String.valueOf(bankCardInfo.getBankCardId()));
				result.setMobileNo(bankCardInfo.getMobileNo());
				result.setCertNo(bankCardInfo.getCertNo());
				result.setCardNo(bankCardInfo.getCardNo());
				result.setCardHolder(bankCardInfo.getCardHolder());
				returnInfo.setReturnCode(BankCardService.TRUE);
				returnInfo.setReturnMsg("卡号:"+cardNo+",已经签约");
			}else{
				result.setBankCardId(String.valueOf(bankCardInfo.getBankCardId()));
				result.setMobileNo(bankCardInfo.getMobileNo());
				result.setCertNo(bankCardInfo.getCertNo());
				result.setCardNo(bankCardInfo.getCardNo());
				result.setCardHolder(bankCardInfo.getCardHolder());
				returnInfo.setReturnCode(BankCardService.FALSE);
				returnInfo.setReturnMsg("未找到用户"+cardName+",身份证"+idNo+"卡号:"+cardNo+"的签约信息");
			}
		}else{
			returnInfo.setReturnCode(BankCardService.FALSE);
			returnInfo.setReturnMsg("未找到用户"+cardName+",身份证"+idNo+"卡号:"+cardNo+"的签约信息");
		}
		result.setReturnInfo(returnInfo);
		return JSON.toJSONString(result);
	}

	private boolean validateIsNull(BankCardResponse result,BankCardRequest request,ReturnInfo returnInfo){
		String cardNo = request.getBankCard();
		String cardName = request.getCardName();
		String idNo = request.getIdNo();
		if(StringUtils.isBlank(cardNo)){
			returnInfo.setReturnCode(BankCardService.FALSE);
			returnInfo.setReturnMsg("银行卡号不能为空!");
			result.setReturnInfo(returnInfo);
			return true;
		}else if(StringUtils.isBlank(cardName)){
			returnInfo.setReturnCode(BankCardService.FALSE);
			returnInfo.setReturnMsg("银行卡姓名不能为空!");
			result.setReturnInfo(returnInfo);
			return true;
		}else if(StringUtils.isBlank(idNo)){
			returnInfo.setReturnCode(BankCardService.FALSE);
			returnInfo.setReturnMsg("身份证号不能为空!");
			result.setReturnInfo(returnInfo);
			return true;
		}
		return false;
	}

}

package com.yiran.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.member.mapper.MemberTmMemberMapper;
import com.yiran.member.mapper.MemberTrBankAccountMapper;
import com.yiran.member.request.BankAccInfoRequest;
import com.yiran.member.request.BankAccountRequest;
import com.yiran.member.response.BankAccInfoResponse;
import com.yiran.member.response.BankAccountInfoResponse;
import com.yiran.member.response.BankAccountResponse;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.domain.MemberTrVerifyEntity;
import com.yiran.member.domain.MemberTrVerifyRef;
import com.yiran.member.domain.Verify;
import com.yiran.member.domain.VerifyQuery;
import com.yiran.member.enums.BankAccountStatusEnum;
import com.yiran.member.enums.MethodType;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.enums.VerifyTypeEncryptMappingEnum;
import com.yiran.member.enums.YesNoEnum;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.filter.BankAccountFilter;
import com.yiran.member.service.IMemberTrBankAccountService;
import com.yiran.member.service.IMemberTrVerifyEntityService;
import com.yiran.member.service.IMemberTrVerifyRefService;
import com.yiran.member.utils.BankAcctDomainUtil;
import com.yiran.member.utils.MaPatternUtil;
import com.yiran.member.utils.ResponseUtil;
import com.yiran.member.validator.BankAccountFacadeValidator;
import com.yiran.member.validator.BankAccountValidator;
import com.yiran.member.validator.MemberValidator;
import com.yiran.system.service.UesServiceClient;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.common.enums.EncryptType;
import com.yiran.common.support.Convert;

/**
 * 个人银行卡 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrBankAccountServiceImpl implements IMemberTrBankAccountService 
{
	private Logger             logger = LoggerFactory.getLogger(MemberTrBankAccountServiceImpl.class);
	@Autowired
	private MemberTrBankAccountMapper memberTrBankAccountMapper;
	@Autowired
	private IMemberTrVerifyRefService memberTrVerifyRefService;
	@Autowired
	private IMemberTrVerifyEntityService memberTrVerifyEntityService;
	@Autowired
	private MemberValidator memberValidator;
	@Autowired
	private UesServiceClient uesServiceClient;
	@Autowired
	private MemberTmMemberMapper memberTmMemberMapper;
	@Autowired
	private BankAccountFilter bankAccountFilter;
	@Autowired
	private BankAccountValidator bankAccountValidator;
	/**
     * 查询个人银行卡信息
     * 
     * @param id 个人银行卡ID
     * @return 个人银行卡信息
     */
    @Override
	public MemberTrBankAccount selectMemberTrBankAccountById(Integer id)
	{
	    return memberTrBankAccountMapper.selectMemberTrBankAccountById(id);
	}
	
	/**
     * 查询个人银行卡列表
     * 
     * @param memberTrBankAccount 个人银行卡信息
     * @return 个人银行卡集合
     */
	@Override
	public List<MemberTrBankAccount> selectMemberTrBankAccountList(MemberTrBankAccount memberTrBankAccount)
	{
	    return memberTrBankAccountMapper.selectMemberTrBankAccountList(memberTrBankAccount);
	}
	
    /**
     * 新增个人银行卡
     * 
     * @param memberTrBankAccount 个人银行卡信息
     * @return 结果
     */
	@Override
	public int insertMemberTrBankAccount(MemberTrBankAccount memberTrBankAccount)
	{
	    return memberTrBankAccountMapper.insertMemberTrBankAccount(memberTrBankAccount);
	}
	
	/**
     * 修改个人银行卡
     * 
     * @param memberTrBankAccount 个人银行卡信息
     * @return 结果
     */
	@Override
	public int updateMemberTrBankAccount(MemberTrBankAccount memberTrBankAccount)
	{
	    return memberTrBankAccountMapper.updateMemberTrBankAccount(memberTrBankAccount);
	}

	/**
     * 删除个人银行卡对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrBankAccountByIds(String ids)
	{
		return memberTrBankAccountMapper.deleteMemberTrBankAccountByIds(Convert.toStrArray(ids));
	}

	@Override
	public BankAccountResponse queryBankAccount(BankAccountRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询会员银行卡绑定信息请求:request={}", request);
        }
        BankAccountResponse response = new BankAccountResponse();
        try {
            BankAccountFacadeValidator.validator(request);
            List<MemberTrBankAccount> bankAccounts = queryBankAccounts(BankAcctDomainUtil
                    .convertBankAccount(request));
            if (CollectionUtils.isEmpty(bankAccounts)) {
                response.setBankAccountInfos(null);
                response.setResponseCode(ResponseCode.NO_QUERY_RESULT.getCode());
                response.setResponseMessage(ResponseCode.NO_QUERY_RESULT.getMessage());
            } else {
                response.setBankAccountInfos(bankAccounts);
                ResponseUtil.setSuccessResponse(response);
            }

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询会员银行卡绑定信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询会员银行卡绑定信息");
        }

        return response;
	}

	private List<MemberTrBankAccount> queryBankAccounts(MemberTrBankAccount bankAccount) throws MaBizException {
		 //如果查询条件为 member + status=null 进行缓存
        List<MemberTrBankAccount> banks = memberTrBankAccountMapper.selectMemberTrBankAccountByMemberId(bankAccount.getMemberId());
        if (CollectionUtils.isEmpty(banks)) {
            return null;
        }

        fillMobileNum(banks, bankAccount.getMemberId());
        if (BankAcctDomainUtil.isQueryMemberBank(bankAccount)) {
            return banks;
        }
        //过滤
        return BankAcctDomainUtil.filterBankAccount(banks, bankAccount);
	}
	

	/**
     * 填充手机号
     * @param banks
     * @param memberId
     * @throws MaBizException
     */
    private void fillMobileNum(List<MemberTrBankAccount> banks, String memberId) throws MaBizException {
    	 //填充手机号
        MemberTrVerifyRef memberTrVerifyRef = memberTrVerifyRefService.selectMemberTrVerifyRefByMemberId(memberId);
        if (memberTrVerifyRef == null) {
            return;
        }
        MemberTrVerifyEntity memberTrVerifyEntity = memberTrVerifyEntityService.selectMemberTrVerifyEntityById(memberTrVerifyRef.getVerifyEntityId(),VerifyTypeEncryptMappingEnum.CELL_PHONE.getCode());
        //手机号解密
        String verifyEntity = memberTrVerifyEntity.getVerifyEntity();
        String[] split = verifyEntity.split(MaConstant.SECURITY_TICKET_SUMMARY_SPLIT_CHAR);
        String mobileNo = uesServiceClient.getDataByTicket(split[0], EncryptType.AES);
        for (MemberTrBankAccount item : banks) {
            if (StringUtil.isBlank(item.getMobileNo())) {
                item.setMobileNo(mobileNo);
            }
        }
    }

	@Override
	public BankAccountInfoResponse queryBankAccountDetail(String bankcardId) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询银行卡详细信息请求:bankcardId={}", bankcardId);
        }
        BankAccountInfoResponse response = new BankAccountInfoResponse();
        try {
            Long id = BankAccountFacadeValidator.checkBankId(bankcardId);
            MemberTrBankAccount bankAccount = memberTrBankAccountMapper.queryBankAccountByBankcardId(bankcardId);
            if (bankAccount == null
                    || BankAccountStatusEnum.DISABLED.getCode().equals(bankAccount.getStatus())) {
                response.setResponseCode(ResponseCode.NO_QUERY_RESULT.getCode());
                response.setResponseMessage(ResponseCode.NO_QUERY_RESULT.getMessage());
            } else {
               //银行卡解密
            	String bankAccountNo = bankAccount.getBankAccountNo();
            	String[] split = bankAccountNo.split("-");
            	bankAccountNo = uesServiceClient.getDataByTicket(split[0], EncryptType.AES);
            	bankAccount.setBankAccountNo(bankAccountNo);
               //手机号解密
            	String mobileNo = bankAccount.getMobileNo();
            	mobileNo = uesServiceClient.getDataByTicket(mobileNo, EncryptType.AES);
            	bankAccount.setMobileNo(mobileNo);
            	response.setBankAcctInfo(bankAccount);
                ResponseUtil.setSuccessResponse(response);
            }
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询银行卡详细信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询银行卡详细信息");
        }

        return response;
	}

	@Override
	public BankAccInfoResponse addBankAccount(BankAccInfoRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]新增会员银行卡绑定信息请求:request={}", request);
        }
        BankAccInfoResponse response = new BankAccInfoResponse();
        try {
            BankAccountFacadeValidator.validatorAddBankAccount(request);
            MemberTrBankAccount bank = request.getBankInfo();
            memberValidator.validateMemberExistAndNotCancelled(bank.getMemberId());

            MemberTrBankAccount bankAccount = createOrUpdateBankAccount(bank,MethodType.INSERT, false);
            response.setBankcardId(String.valueOf(bankAccount.getId()));
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]新增会员银行卡绑定信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "绑定会员银行卡");
        }

        return response;
	}

	private MemberTrBankAccount createOrUpdateBankAccount(MemberTrBankAccount bankAccount, MethodType type, boolean deleteOther) throws Exception {
		//1.判断新增还是修改
        //1.1新增
        //1.1.1查询数据库是否有相似的记录存在，匹配条件 memberId,卡号,payAttribute 状态 in ('1','2')

        //1.1.2 根据手机号码等信息做筛选

        //1.1.3 找到数据则返回没有找到则新增

        //1.2修改
        //1.2.1 判断是否为修改签约标识，如果是则同时更新其他的匹配条件的卡号为失效

        //1.2.2 如果不是修改签约标识，则修改对应字段

        fillCertNo(bankAccount);

        //会员锁定
        memberTmMemberMapper.lockMemberById(bankAccount.getMemberId());

        if (MethodType.INSERT == type) {
            if(deleteOther){
                //如果是用户唯一卡则先失效原卡
                disabledAllBankAccount(bankAccount.getMemberId(), bankAccount.getPayAttribute());
            }
            return addBankAccount(bankAccount);

        } else {
            return updateBankAct(bankAccount);

        }
	}
	
	private MemberTrBankAccount addBankAccount(MemberTrBankAccount bankAccount) throws MaBizException {
        if (BankAccountStatusEnum.NORMAL.getCode().equals(bankAccount.getStatus())) {
            bankAccount.setActivateDate(new Date());
        }

        //渠道编码为空，signNo或者signId不为空
        if (StringUtil.isNotBlank(bankAccount.getChannelCode())
            && (StringUtil.isNotBlank(bankAccount.getSignId()) || StringUtil.isNotBlank(bankAccount
                .getSignNo()))) {
            List<MemberTrBankAccount> list = null;
            if (StringUtil.isNotBlank(bankAccount.getSignId())
                && StringUtil.isNotBlank(bankAccount.getSignNo())) {
                list = memberTrBankAccountMapper.queryBySignNo(bankAccount);
            } else {
                list = memberTrBankAccountMapper.queryBySign(bankAccount);

            }
            if (CollectionUtils.isNotEmpty(list)) {
                throw new MaBizException(ResponseCode.BANK_ACCOUNT_TOO_MANY, "此协议号对应的银行卡已存在");
            }
        }
        if (StringUtil.isBlank(bankAccount.getBankAccountNo())) {
        	int trBankAccount = memberTrBankAccountMapper.insertMemberTrBankAccount(bankAccount);
            return bankAccount;
        }


        List<MemberTrBankAccount> bankAccountList = memberTrBankAccountMapper.selectMemberTrBankAccountList(bankAccount);
        List<MemberTrBankAccount> filterResultlist = new ArrayList<MemberTrBankAccount>();
        boolean isLock = bankAccountFilter.doFilter(bankAccountList, bankAccount, filterResultlist);
        //判断如有锁定卡报错
        if (isLock) {
            //TODO 此处是否需要加邮件报警
            throw new MaBizException(ResponseCode.BANK_ACCOUNT_LOCK, "卡片被锁定");
        }
        if (CollectionUtils.isEmpty(filterResultlist)) {
            //特殊逻辑如果卡过来是激活的
            if (BankAccountStatusEnum.NORMAL.getCode().equals(bankAccount.getStatus())
                && CollectionUtils.isNotEmpty(bankAccountList)) {
                disabledBankCard(bankAccount);
            }
            //没有相似数据新建一条
            int trBankAccount = memberTrBankAccountMapper.insertMemberTrBankAccount(bankAccount);
        } else if (filterResultlist.size() == 1) {
        	bankAccount = filterResultlist.get(0);
        } else {
            //TODO 此处是否需要加邮件报警
            throw new MaBizException(ResponseCode.BANK_ACCOUNT_TOO_MANY, "银行卡大于1张");
        }
        return bankAccount;
    
	}
	/**
     * 失效银行卡
     * @param bnkAccount
     * @throws MaBizException
     */
    private void disabledBankCard(MemberTrBankAccount bnkAccount) throws MaBizException {
    	bnkAccount.setStatus(BankAccountStatusEnum.DISABLED.getCode());
    	memberTrBankAccountMapper.updateStatus(bnkAccount);
    }

	private void disabledAllBankAccount(String memberId, String payAttribute) {
		memberTrBankAccountMapper.disabledAllBankAccount(memberId,payAttribute);
	}

	private void fillCertNo(MemberTrBankAccount bankAccount) throws Exception {
        if (YesNoEnum.YES.getCode() .equals(bankAccount.getIsFillCertNo())) {
            //填充证件号 "[0-9]{2}([0-9]*)[0-9]{2}"
        	String ticket = uesServiceClient.encryptData(bankAccount.getBankAccountNo(), EncryptType.AES);
        	String summary = MaPatternUtil.getFiledMask(bankAccount.getBankAccountNo(), "[0-9]{2}([0-9]*)[0-9]{2}");
        	bankAccount.setCertNo(ticket + MaConstant.SECURITY_TICKET_SUMMARY_SPLIT_CHAR + summary);
        }
    }
	
	
	private MemberTrBankAccount updateBankAct(MemberTrBankAccount bankAccount) throws MaBizException {

        //更新数据
		MemberTrBankAccount bnkAccount = memberTrBankAccountMapper.selectMemberTrBankAccountById(bankAccount.getId());

        //如数据库银行卡为空允许修改
        if (StringUtil.isNotBlank(bnkAccount.getBankAccountNo())) {
            if (StringUtil.isNotBlank(bankAccount.getBankAccountNo())
                && !StringUtil
                    .equals(bankAccount.getBankAccountNo(), bnkAccount.getBankAccountNo())) {
                throw new MaBizException(ResponseCode.BANK_ACCOUNT_PAY_DONT_UPDATE, "银行卡号和支付属性不能修改");

            }
        }

        if (bankAccount.getPayAttribute() != null
            && bankAccount.getPayAttribute() != bnkAccount.getPayAttribute()) {
            throw new MaBizException(ResponseCode.BANK_ACCOUNT_PAY_DONT_UPDATE, "银行卡号和支付属性不能修改");
        }
        if (StringUtil.isNotBlank(bankAccount.getSignId())
            || StringUtil.isNotBlank(bankAccount.getSignNo())
            || StringUtil.isNotBlank(bankAccount.getChannelCode())) {

        	MemberTrBankAccount queryBankAccount = new MemberTrBankAccount();

            putBankAccount(queryBankAccount, bnkAccount);
            putBankAccount(queryBankAccount, bankAccount);

            if (StringUtil.isNotBlank(queryBankAccount.getChannelCode())
                && (StringUtil.isNotBlank(queryBankAccount.getSignNo()) || StringUtil
                    .isNotBlank(queryBankAccount.getSignId()))) {

                List<MemberTrBankAccount> list = memberTrBankAccountMapper.queryBySign(queryBankAccount);
                if (!CollectionUtils.isEmpty(list)) {
                    for (MemberTrBankAccount act : list) {
                        if (!StringUtil.equals(act.getId().toString(), bankAccount.getId().toString())) {
                            throw new MaBizException(ResponseCode.BANK_ACCOUNT_TOO_MANY,
                                "此协议号对应的银行卡已存在");
                        }
                    }
                }

            }
        }
        //判断是否为激活
        boolean isActiveCard = isActiveCardStatus(bankAccount.getStatus(), bnkAccount.getStatus());
        if (isActiveCard) {
            //设置激活时间
            bankAccount.setActivateDate(new Date());
        }

        memberTrBankAccountMapper.updateMemberTrBankAccount(bankAccount);

        if (isActiveCard) {
        	memberTrBankAccountMapper.disabledBankAccount(memberTrBankAccountMapper.selectMemberTrBankAccountById(bankAccount.getId()));
        }
        return bnkAccount;

    }
	
	/**
     * 协议号覆盖
     * @param orgBankAccount
     * @param newBankAccount
     */
    private void putBankAccount(MemberTrBankAccount orgBankAccount, MemberTrBankAccount newBankAccount) {
        if (StringUtil.isNotBlank(newBankAccount.getSignId())) {
            orgBankAccount.setSignId(newBankAccount.getSignId());
        }
        if (StringUtil.isNotBlank(newBankAccount.getSignNo())) {
            orgBankAccount.setSignNo(newBankAccount.getSignNo());
        }
        if (StringUtil.isNotBlank(newBankAccount.getChannelCode())) {
            orgBankAccount.setChannelCode(newBankAccount.getChannelCode());
        }
    }
    
    /**
     * 是否为激活银行卡
     * @param newStatus
     * @param orgStatus
     * @return
     */
    private boolean isActiveCardStatus(Integer newStatus, Integer orgStatus) {
        if (newStatus == null) {
            return false;
        }
        if (BankAccountStatusEnum.UNACTIVE.getCode().equals(orgStatus)
            && BankAccountStatusEnum.NORMAL.getCode().equals(newStatus)) {
            return true;
        }
        return false;

    }

	@Override
	public BankAccInfoResponse updateBankAccount(BankAccInfoRequest request) {
	 if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]更新会员银行卡绑定信息请求:request={}", request);
        }
        BankAccInfoResponse response = new BankAccInfoResponse();
        try {
            BankAccountFacadeValidator.validatorUpdateBankAccount(request);
            MemberTrBankAccount bankAccount = bankAccountValidator.validateBankAccountExist(request.getBankInfo());
            createOrUpdateBankAccount(request.getBankInfo(), MethodType.UPDATE, false);
            response.setBankcardId(String.valueOf(request.getBankInfo().getId()));
            ResponseUtil.setSuccessResponse(response);
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]更新会员银行卡绑定信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "更新会员银行卡绑定信息");
        }
        return response;
	}
	
}

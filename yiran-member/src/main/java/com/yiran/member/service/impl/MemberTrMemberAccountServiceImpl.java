package com.yiran.member.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiran.member.mapper.MemberTrMemberAccountDetailMapper;
import com.yiran.member.mapper.MemberTrMemberAccountMapper;
import com.yiran.member.request.AccBalanceListRequest;
import com.yiran.member.request.AccRelationRequest;
import com.yiran.member.request.AccountRequest;
import com.yiran.member.request.GetBalanceListReq;
import com.yiran.member.request.OpenAccountRequest;
import com.yiran.member.request.QueryBasicAccountRequest;
import com.yiran.member.request.RemoteAccountBalanceListResult;
import com.yiran.member.request.UpdateAccountFreezeStatusRequest;
import com.yiran.member.response.AccBalanceListResponse;
import com.yiran.member.response.AccRelationResponse;
import com.yiran.member.response.AccountBalanceListResp;
import com.yiran.member.response.AccountInfo;
import com.yiran.member.response.AccountInfoResponse;
import com.yiran.member.response.AccountResponse;
import com.yiran.member.response.OpenAccountResponse;
import com.yiran.member.base.Response;
import com.yiran.member.domain.Account;
import com.yiran.member.domain.AccountBalance;
import com.yiran.member.domain.AccountDomain;
import com.yiran.member.domain.MemberAndAccount;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.domain.MemberTrMemberAccount;
import com.yiran.member.domain.MemberTrMemberAccountDetail;
import com.yiran.member.enums.AccountCategoryEnum;
import com.yiran.member.enums.BasicAccountType;
import com.yiran.member.enums.MemberTypeEnum;
import com.yiran.member.enums.PlatFormTypeEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.service.IMemberTrMemberAccountDetailService;
import com.yiran.member.service.IMemberTrMemberAccountService;
import com.yiran.member.utils.AccountDomainUtil;
import com.yiran.member.utils.MemberTypeUtil;
import com.yiran.member.utils.ResponseUtil;
import com.yiran.member.validator.AccountFacadeValidator;
import com.yiran.member.validator.AccountValidator;
import com.yiran.member.validator.CommonFacadeValidator;
import com.yiran.member.validator.MemberValidator;
import com.alibaba.fastjson.JSON;
import com.netfinworks.common.domain.OperationEnvironment;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.common.support.Convert;

/**
 * 会员账户 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrMemberAccountServiceImpl implements IMemberTrMemberAccountService 
{
	private Logger        logger = LoggerFactory.getLogger(MemberTrMemberAccountServiceImpl.class);
	@Autowired
	private MemberTrMemberAccountMapper memberTrMemberAccountMapper;
	@Autowired
	private MemberTrMemberAccountDetailMapper memberTrMemberAccountDetailMapper;
	@Autowired
	private AccountValidator accountValidator;
	@Autowired
	private MemberValidator memberValidator;
	@Autowired
	private IMemberTrMemberAccountDetailService memberTrMemberAccountDetailService;
	/**
     * 查询会员账户信息
     * 
     * @param id 会员账户ID
     * @return 会员账户信息
     */
    @Override
	public MemberTrMemberAccount selectMemberTrMemberAccountById(Integer id)
	{
	    return memberTrMemberAccountMapper.selectMemberTrMemberAccountById(id);
	}
	
	/**
     * 查询会员账户列表
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 会员账户集合
     */
	@Override
	public List<MemberTrMemberAccount> selectMemberTrMemberAccountList(MemberTrMemberAccount memberTrMemberAccount)
	{
	    return memberTrMemberAccountMapper.selectMemberTrMemberAccountList(memberTrMemberAccount);
	}
	
    /**
     * 新增会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	@Override
	public int insertMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount)
	{
	    return memberTrMemberAccountMapper.insertMemberTrMemberAccount(memberTrMemberAccount);
	}
	
	/**
     * 修改会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	@Override
	public int updateMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount)
	{
	    return memberTrMemberAccountMapper.updateMemberTrMemberAccount(memberTrMemberAccount);
	}

	/**
     * 删除会员账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrMemberAccountByIds(String ids)
	{
		return memberTrMemberAccountMapper.deleteMemberTrMemberAccountByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<AccountDomain> getAccounts(String memberId, String accountType, AccountCategoryEnum dpm) {
		List<String> accountTypes = null;
        if (StringUtils.isNotBlank(accountType)) {
            accountTypes = new ArrayList<String>(1);
            accountTypes.add(accountType);
        }
        List<MemberTrMemberAccount> accounts = memberTrMemberAccountMapper.queryByMemberAndTypeIds(
            memberId, dpm.getCode(), accountTypes);
        if (!(accounts == null || accounts.isEmpty())) {
            List<AccountDomain> rest = new ArrayList<AccountDomain>(accounts.size());
            for (MemberTrMemberAccount item : accounts) {
                rest.add(AccountDomainUtil.convertToAccount(item));
            }
            return rest;
        }
        return null;
	}

	@Override
	public List<AccountDomain> getAccounts(String memberId, List accountTypes) {
		List<MemberTrMemberAccount> accounts = memberTrMemberAccountMapper.queryByMemberAndTypeIds(
	            memberId, AccountCategoryEnum.DPM.getCode(), accountTypes);
	        if (!(accounts == null || accounts.isEmpty())) {
	            List<AccountDomain> rest = new ArrayList<AccountDomain>(accounts.size());
	            for (MemberTrMemberAccount item : accounts) {
	                rest.add(AccountDomainUtil.convertToAccount(item));
	            }
	            return rest;
	        }
	        return null;
	}

	@Override
	public String openAccount(AccountDomain baseAccount) {
		MemberTrMemberAccount account = AccountDomainUtil.convertToMemberAccount(baseAccount);
		int memberAccountId = memberTrMemberAccountMapper.insertMemberTrMemberAccount(account);
		logger.info("个人会员集成创建->开户->账户ID:{}",account.getId());
		MemberTrMemberAccountDetail mad = new MemberTrMemberAccountDetail();
		mad.setMemberAccId(account.getId());
		mad.setBalance(baseAccount.getBalance()==null?"0":baseAccount.getBalance().toString());
		mad.setAvailableBalance(baseAccount.getAvailableBalance()==null?"0":baseAccount.getAvailableBalance().toString());
		mad.setFrozenBalance(baseAccount.getFrozenBalance()==null?"0":baseAccount.getFrozenBalance().toString());
		mad.setWithdrawBalance(baseAccount.getWithdrawBalance()==null?"0":baseAccount.getWithdrawBalance().toString());
		mad.setCurrencyCode(baseAccount.getCurrencyCode());
		mad.setExtention(JSON.toJSONString(baseAccount.getExtention()));
		memberTrMemberAccountDetailMapper.insertMemberTrMemberAccountDetail(mad);
		return account.getAccountId();
	}

	@Override
	public int updateAccountId(String accountId, String accountName, String memberId) {
		// TODO Auto-generated method stub
		return memberTrMemberAccountMapper.updateAccountId( accountId,  accountName,  memberId);
	}

	@Override
	public void insertMemberTrMemberAccount(AccountDomain baseAccount) {
		MemberTrMemberAccount account = AccountDomainUtil.convertToMemberAccount(baseAccount);
		int memberAccountId = memberTrMemberAccountMapper.insertMemberTrMemberAccount(account);
		MemberTrMemberAccountDetail mad = new MemberTrMemberAccountDetail();
		mad.setMemberAccId(memberAccountId);
		mad.setBalance(baseAccount.getBalance()==null?"0":baseAccount.getBalance().toString());
		mad.setAvailableBalance(baseAccount.getAvailableBalance()==null?"0":baseAccount.getAvailableBalance().toString());
		mad.setFrozenBalance(baseAccount.getFrozenBalance()==null?"0":baseAccount.getFrozenBalance().toString());
		mad.setWithdrawBalance(baseAccount.getWithdrawBalance()==null?"0":baseAccount.getWithdrawBalance().toString());
		mad.setCurrencyCode(baseAccount.getCurrencyCode());
		mad.setExtention(JSON.toJSONString(baseAccount.getExtention()));
		memberTrMemberAccountDetailMapper.insertMemberTrMemberAccountDetail(mad);
		
	}

	@Override
	public List<MemberTrMemberAccount> queryAllByMemberAndTypeId(String memberId, String typeId, String category) {
		return memberTrMemberAccountMapper.queryAllByMemberAndTypeId( memberId,  typeId,  category) ;
	}

	@Override
	public AccRelationResponse queryAccountRelation(AccRelationRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询会员账户信息请求:request={}", request);
        }
        AccRelationResponse response = new AccRelationResponse();
        try {
            AccountFacadeValidator.validator(request);
            List<AccountDomain> accountDomains = null;
            if (StringUtil.isNotBlank(request.getAccountId())) {
                AccountDomain accountDomain = queryAccountRelationsByAccountId(request.getAccountId());
                accountDomains = new ArrayList<AccountDomain>(1);
                accountDomains.add(accountDomain);
            } else {
                accountDomains = queryAccountRelations(request.getMemberId(),
                    request.getAccountType());
            }
            List<AccountInfo> accountInfos = new ArrayList<AccountInfo>(accountDomains.size());
            for (AccountDomain accountDomain : accountDomains) {
                accountInfos.add(AccountDomainUtil.convertToAccountInfo(accountDomain));
            }
            response.setAccountRelations(accountInfos);
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询会员账户信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询会员账户信息");
        }

        return response;
	}

	private List<AccountDomain> queryAccountRelations(String memberId, Long accountType) throws MaBizException {
	    String type = accountType == null ? null : String.valueOf(accountType);
	    List<String> accountTypes = null;
        if (StringUtil.isNotBlank(type)) {
            accountTypes = new ArrayList<String>(1);
            accountTypes.add(type);
        }
        List<MemberTrMemberAccount>	memberAccounts =memberTrMemberAccountMapper.queryByMemberAndTypeIds(memberId,AccountCategoryEnum.DPM.getCode(),accountTypes);
        List<AccountDomain> accounts = new ArrayList<AccountDomain>();
        if (null == memberAccounts || memberAccounts.isEmpty()) {
            throw new MaBizException(ResponseCode.NO_QUERY_RESULT);
        }
        for (MemberTrMemberAccount account : memberAccounts) {
        	accounts.add(AccountDomainUtil.convertToAccount(account));
		}
        return accounts;
	}

	private AccountDomain queryAccountRelationsByAccountId(String accountId) throws MaBizException {
		MemberTrMemberAccount memberAccount = memberTrMemberAccountMapper.getAccountByAccountId(accountId,AccountCategoryEnum.DPM.getCode());
		AccountDomain accountDomain = AccountDomainUtil.convertToAccount(memberAccount);
		if (null == memberAccount) {
            throw new MaBizException(ResponseCode.NO_QUERY_RESULT);
        }
        return accountDomain;
	}

	/**
	 * 根据账户id 查询会员账户信息
	 */
	@Override
	public AccountInfoResponse queryAccountById(String accountId) {
		 if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询会员账户信息请求:request={}", accountId);
        }
        AccountInfoResponse response = new AccountInfoResponse();
        try {
            CommonFacadeValidator.checkAccountId(accountId, true);
            accountValidator.validateAccountRelationExists(accountId,AccountCategoryEnum.DPM);
            MemberTrMemberAccount account = memberTrMemberAccountMapper.getAccountById(accountId);
            //TODO:获取账户扩展信息
            MemberTrMemberAccountDetail memberAccountDetail =null;
            if(account!=null){
            	memberAccountDetail = memberTrMemberAccountDetailService.selectMemberTrMemberAccountDetailByMemberAccountId(account.getId());
            }
        	
            AccountDomain accountDomain = null;
            if(memberAccountDetail!=null){
            	accountDomain = AccountDomainUtil.convertToAccount(account,memberAccountDetail);
            }else{
            	accountDomain = AccountDomainUtil.convertToAccount(account);
            }
            
            //填充账户类型
            fillAccountType(accountDomain);

            if (null != accountDomain) {
                response.setAccount(AccountDomainUtil.convertToAccount(accountDomain));
            }
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询会员账户信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询会员账户信息");
        }
        return response;
	}

	private void fillAccountType(AccountDomain account) throws MaBizException {
		// TODO Auto-generated method stub
        if (account.getAccountType() != null) {
            return;
        }
        String accountType = String.valueOf(account.getAccountType());
        List<String> accountTypes = null;
        if (StringUtils.isNotBlank(accountType)) {
            accountTypes = new ArrayList<String>(1);
            accountTypes.add(accountType);
        }
        String memberId = account.getMemberId();
        List<MemberTrMemberAccount>	memberAccounts =memberTrMemberAccountMapper.queryByMemberAndTypeIds(memberId,AccountCategoryEnum.DPM.getCode(),accountTypes);
        List<AccountDomain> accounts = new ArrayList<AccountDomain>();
        if (null == memberAccounts || memberAccounts.isEmpty()) {
            throw new MaBizException(ResponseCode.NO_QUERY_RESULT);
        }
        for (MemberTrMemberAccount maccount : memberAccounts) {
        	accounts.add(AccountDomainUtil.convertToAccount(maccount));
		}
        for (AccountDomain domain : accounts) {
            if (StringUtil.equals(account.getAccountId(), domain.getAccountId())) {
                account.setAccountType(domain.getAccountType());
                break;
            }
        }
	}

	/**
	 * 根据会员号和账户类型查询账户信息
	 */
	@Override
	public AccountResponse queryAccountByMemberId(AccountRequest request) {
		 if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]根据会员号和账户类型查询账户信息请求:request={}", request);
        }
        AccountResponse response = new AccountResponse();
        try {
            AccountFacadeValidator.validator(request);
            String type = request.getAccountType() == null ? null : String.valueOf(request
                .getAccountType());
            accountValidator.validateAccountRelationExists(request.getMemberId(), type,
                AccountCategoryEnum.DPM);
            List<AccountDomain> accountDomains = getAccountsByMemberId(request.getMemberId(), request.getAccountType());

            if (null != accountDomains && accountDomains.size() > 0) {
                List<Account> accounts = new ArrayList<Account>(accountDomains.size());
                for (AccountDomain accountDomain : accountDomains) {
                    accounts.add(AccountDomainUtil.convertToAccount(accountDomain));
                }
                response.setAccounts(accounts);
            }
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]根据会员号和账户类型查询账户信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "根据会员号和账户类型查询账户信息");
        }

        return response;
	}

	private List<AccountDomain> getAccountsByMemberId(String memberId, Long accountType) throws MaBizException {
		List<MemberTrMemberAccount> list = null;
        //账户类型为空，查询该会员所有的储值账号
        if (accountType == null) {
            list = memberTrMemberAccountMapper.getAccountsByMemberId(memberId);
        } else {
           
                list = new ArrayList<MemberTrMemberAccount>(1);
                List<AccountDomain> accountDomains = queryAccountRelations(memberId, accountType);
                list.add(memberTrMemberAccountMapper.getAccountById(accountDomains.get(0).getAccountId()));
            
        }
        
        List<AccountDomain> accounts =new ArrayList<AccountDomain>();
        if(list !=null && list.size()>0){
            for (MemberTrMemberAccount accountDomain : list) {
    			//根据id获取明细
            	MemberTrMemberAccountDetail memberAccountDetail = memberTrMemberAccountDetailMapper.selectMemberTrMemberAccountDetailByMemberAccountId(accountDomain.getId());
            	accounts.add(AccountDomainUtil.convertToAccount(accountDomain, memberAccountDetail));
            }

        }
        fillAccountType(accounts);
        return accounts;
	}
	private void fillAccountType(List<AccountDomain> accounts) throws MaBizException {
        if (CollectionUtils.isEmpty(accounts))
            return;
        for (AccountDomain domain : accounts) {
            fillAccountType(domain);
        }

    }

	@Override
	public Response updateAccountFreezeStatus(UpdateAccountFreezeStatusRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]更新储值账户冻结状态请求:request={}", request);
        }
        Response response = new Response();
        try {
            AccountFacadeValidator.validator(request);
            updateAccountStatus(AccountDomainUtil.convert(request));
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]更新储值账户冻结状态结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "更新储值账户冻结状态");
        }

        return response;
	}

	private void updateAccountStatus(AccountDomain account) throws MaBizException {
		if (StringUtil.isNotBlank(account.getAccountId())) {
            accountValidator.validateAccountRelationExists(account.getAccountId(),AccountCategoryEnum.DPM);
        } else {
            String memberId = account.getMemberId();
            String accountType = account.getAccountTypeId();
            List<AccountDomain> accounts = accountValidator.validateAccountRelationExists(memberId,
                accountType, AccountCategoryEnum.DPM);
            if (accounts.size() > 1) {
                throw new MaBizException(ResponseCode.ACCOUNT_SET_STATUS_FAIL,
                    "编号为" + memberId + "的会员存在多个类型为" + accountType
                            + "的账户; 一次只能修改一个账户的状态，请传accountId！");
            }
            String accountId = accounts.get(0).getAccountId();
            account.setAccountId(accountId);
        }
        if (account.getFreezeStatus() != null) {
        	memberTrMemberAccountMapper.changeDenyAccountStatus(AccountDomainUtil.convertToMemberTrAccount(account));
        }
	}

	@Override
	public OpenAccountResponse openAccount(OpenAccountRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]创建储值账户请求:request={}", request);
        }
        OpenAccountResponse response = new OpenAccountResponse();
        try {
            AccountFacadeValidator.validator(request);
             String accountId = openAccount(AccountDomainUtil
                .buildOpenAccountRequest(request));
            response.setAccountId(accountId);
            response.setCreateTime(new Date());
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]创建储值账户结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "创建储值账户");
        }

        return response;
	}

	@Override
	public AccountInfoResponse queryBasicAccountByMember(QueryBasicAccountRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询会员基本账户信息请求:request={}", request);
        }
        AccountInfoResponse response = new AccountInfoResponse();
        try {
            //验证请求参数
            AccountFacadeValidator.validator(request);
            AccountDomain domain = getBaseAccountByMember(request.getMemberId(),
                    StringUtil.toLowerCase(StringUtil.trim(request.getMemberIdentity())),request.getPlatformType());
            response.setAccount(AccountDomainUtil.convertToAccount(domain));
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询会员基本账户信息请求:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询会员账户信息");
        }
        return response;
	}

	private AccountDomain getBaseAccountByMember(String memberId, String identity, String platformType) throws MaBizException {
		MemberTmMember member = null;
        //验证会员存在未注销
        if(!StringUtils.isEmpty(memberId)){
            member = memberValidator.validateMemberExist(memberId);
        } else {
            int pid = PlatFormTypeEnum.DEFAULT.getCode();
            if (StringUtil.isNotBlank(platformType)) {
                pid = Integer.parseInt(StringUtil.trim(platformType));
            }
            member = memberValidator.validateMemberExistByIdentity(identity,pid);
        }
       
        MemberTypeEnum memberType = MemberTypeEnum.getByCode(member.getMemberType().longValue());
        BasicAccountType accountType = BasicAccountType.getByCode(BasicAccountType.COMPANY_BASIC_ACCOUNT.getCode());
        if(MemberTypeUtil.isSpecialMemberType(memberType.getCode().intValue())){
            accountType = BasicAccountType.SPECIAL_BASIC_ACCOUNT;
        }
		if(MemberTypeUtil.isVirtualMemberType(memberType.getCode().intValue())){
            accountType = BasicAccountType.SPECIAL_BASIC_ACCOUNT;
        }
        if(MemberTypeUtil.isPersonMemberType(memberType.getCode().intValue())){
            accountType = BasicAccountType.PERSONAL_BASIC_ACCOUNT;
        }
       
        List<AccountDomain> accountDomains = getAccountsByMemberId(member.getMemberId(), accountType.getCode());
        if (null != accountDomains && accountDomains.size() > 0) {
            return accountDomains.get(0);
        } else {
            throw new MaBizException(ResponseCode.ACCOUNT_NOT_EXIST, "会员编号["+member.getMemberId()+"]基本户不存在");
        }
	}

	
	
}

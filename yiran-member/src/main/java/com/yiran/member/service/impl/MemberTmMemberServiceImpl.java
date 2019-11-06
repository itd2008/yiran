package com.yiran.member.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.yiran.member.mapper.MemberTmMemberIdentityMapper;
import com.yiran.member.mapper.MemberTmMemberMapper;
import com.yiran.member.mapper.MemberTmMerchantMapper;
import com.yiran.member.mapper.MemberTmOperatorMapper;
import com.yiran.member.mapper.MemberTrCompanyMemberMapper;
import com.yiran.member.mapper.MemberTrPersonalMemberMapper;
import com.yiran.member.request.ActivatePersonalRequest;
import com.yiran.member.request.CreateMemberInfoRequest;
import com.yiran.member.request.IntegratedCompanyRequest;
import com.yiran.member.request.IntegratedPersonalRequest;
import com.yiran.member.request.MemberIntegratedIdRequest;
import com.yiran.member.request.MemberIntegratedRequest;
import com.yiran.member.request.PersonalMemberInfoRequest;
import com.yiran.member.request.UpdateMemberLockStatusRequest;
import com.yiran.member.response.ActivatePersonalResponse;
import com.yiran.member.response.CreateMemberInfoResponse;
import com.yiran.member.response.IntegratedCompanyResponse;
import com.yiran.member.response.IntegratedPersonalResponse;
import com.yiran.member.response.MemberIntegratedResponse;
import com.yiran.member.base.Response;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.domain.AccountDomain;
import com.yiran.member.domain.MemberAndAccount;
import com.yiran.member.domain.MemberIntegratedQuery;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.domain.MemberTmMemberIdentity;
import com.yiran.member.domain.MemberTmMerchant;
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.domain.MemberTrCompanyMember;
import com.yiran.member.domain.MemberTrMemberAccount;
import com.yiran.member.domain.MemberTrPersonalMember;
import com.yiran.member.domain.MemberTrVerifyEntity;
import com.yiran.member.domain.PayPassWord;
import com.yiran.member.domain.PersonalMember;
import com.yiran.member.domain.Verify;
import com.yiran.member.enums.AccountCategoryEnum;
import com.yiran.member.enums.ActivateStatusEnum;
import com.yiran.member.enums.LockEnum;
import com.yiran.member.enums.MemberAccountStatusEnum;
import com.yiran.member.enums.MemberStatusEnum;
import com.yiran.member.enums.MemberTypeEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.service.DataEncryptService;
import com.yiran.member.service.IMemberTmMemberService;
import com.yiran.member.service.IMemberTmOperatorService;
import com.yiran.member.service.IMemberTrMemberAccountService;
import com.yiran.member.service.IMemberTrPasswordService;
import com.yiran.member.service.IMemberTrVerifyEntityService;
import com.yiran.member.service.IMemberTrVerifyRefService;
import com.yiran.member.utils.AccountDomainUtil;
import com.yiran.member.utils.MemberDomainUtil;
import com.yiran.member.utils.MemberTypeUtil;
import com.yiran.member.utils.OperatorDomainUtil;
import com.yiran.member.utils.ResponseUtil;
import com.yiran.member.utils.SQLExceptionUtil;
import com.yiran.member.utils.Utils;
import com.yiran.member.validator.MemberFacadeValidator;
import com.yiran.member.validator.MemberValidator;
import com.yiran.system.service.UesServiceClient;

import cn.hutool.core.util.RandomUtil;

import com.alibaba.fastjson.JSON;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.common.enums.EncryptType;
import com.yiran.common.support.Convert;

/**
 * 会员 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTmMemberServiceImpl implements IMemberTmMemberService 
{
	private Logger        logger = LoggerFactory.getLogger(MemberTmMemberServiceImpl.class);
	@Autowired
	private MemberTmMemberMapper memberTmMemberMapper;
	@Autowired
	private MemberTmMemberIdentityMapper memberTmMemberIdentityMapper;
	@Autowired
	private IMemberTmOperatorService memberTmOperatorService;
	@Autowired
	private UesServiceClient uesServiceClient;
	@Autowired
	private IMemberTrMemberAccountService memberTrMemberAccountService;
	@Autowired
	IMemberTrVerifyEntityService memberTrVerifyEntityService;
	@Autowired
	private DataEncryptService dataEncryptService;
	@Autowired
	private MemberTrPersonalMemberMapper memberTrPersonalMemberMapper;
	@Autowired
	private MemberValidator memberValidator;
	@Autowired
	private IMemberTrPasswordService memberTrPasswordService;
	@Autowired
	private MemberTmMerchantMapper memberTmMerchantMapper;
	@Autowired
	private MemberTrCompanyMemberMapper memberTrCompanyMemberMapper;
	/**
     * 查询会员信息
     * 
     * @param memberId 会员ID
     * @return 会员信息
     */
    @Override
	public MemberTmMember selectMemberTmMemberById(String memberId)
	{
	    return memberTmMemberMapper.selectMemberTmMemberById(memberId);
	}
	
	/**
     * 查询会员列表
     * 
     * @param memberTmMember 会员信息
     * @return 会员集合
     */
	@Override
	public List<MemberTmMember> selectMemberTmMemberList(MemberTmMember memberTmMember)
	{
	    return memberTmMemberMapper.selectMemberTmMemberList(memberTmMember);
	}
	
    /**
     * 新增会员
     * 
     * @param memberTmMember 会员信息
     * @return 结果
     */
	@Override
	public int insertMemberTmMember(MemberTmMember memberTmMember)
	{
	    return memberTmMemberMapper.insertMemberTmMember(memberTmMember);
	}
	
	/**
     * 修改会员
     * 
     * @param memberTmMember 会员信息
     * @return 结果
     */
	@Override
	public int updateMemberTmMember(MemberTmMember memberTmMember)
	{
	    return memberTmMemberMapper.updateMemberTmMember(memberTmMember);
	}

	/**
     * 删除会员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmMemberByIds(String ids)
	{
		return memberTmMemberMapper.deleteMemberTmMemberByIds(Convert.toStrArray(ids));
	}

	@Override
	public CreateMemberInfoResponse createMemberInfo(CreateMemberInfoRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]创建会员请求:request={}", request);
        }
        CreateMemberInfoResponse response = new CreateMemberInfoResponse();
        try {
            //验证请求参数
            MemberFacadeValidator.validator(request);
            //参数转换
            MemberTmMember member = MemberDomainUtil.convertReqToMember(request);
            logger.info("转换后的会员信息:{}",JSON.toJSONString(member));
            MemberTmOperator operator = OperatorDomainUtil.convertReqToDefaultOperator(request);
            if (StringUtils.isNotBlank(request.getLoginPassword())) {
                String ticket = uesServiceClient.encryptData(StringUtils.trim(request.getLoginPassword()), EncryptType.AES);
                operator.setPassword(ticket);
            } else {
                operator.setPassword(null);
            }
            MemberAndAccount ma = createMember(member, operator);
            
            response.setMemberId(ma.getMemberId());
            response.setOperatorId(ma.getOperatorId());
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]创建会员结果:response={}", response);
            }
        } catch (Exception e) {
        	ResponseUtil.fillResponse(response, e, "创建会员");
        }
        return response;
	}

	/**
	 * 创建会员
	 * @param member
	 * @param operator
	 * @return
	 */
	private MemberAndAccount createMember(MemberTmMember member, MemberTmOperator operator) {
		String memberId = genMemberId(member.getMemberType());
        member.setMemberId(memberId);
        operator.setMemberId(memberId);
        
        try {
            MemberAndAccount ma = store(member, operator);
            return ma;
        } catch (MaBizException e) {
            /*if (ResponseCode.MEMBER_IDENTITY_EXIST.equals(e.getCode())) {
                //memberRepository.checkIdentityException(member.getIdentitys());
                //作幂等modify 2013-12-09
                return reStore(member, operator);
            }else{
                throw e;
            }*/
        }
        
		return null;
	}

	private MemberAndAccount store(MemberTmMember member, MemberTmOperator operator) throws MaBizException {
		MemberAndAccount ma = new MemberAndAccount();
        //创建会员标识
        try {
            this.createIdentity(member);
        } catch (Exception e) {
            logger.warn("创建会员标志异常", e);
            if (SQLExceptionUtil.isUniqueException(e)) {
                throw new MaBizException(ResponseCode.MEMBER_IDENTITY_EXIST);
            } else {
                throw new MaBizException(ResponseCode.MEMBER_CREATE_FAIL, e.getMessage());
            }
        }
        //创建会员
        memberTmMemberMapper.insertMemberTmMember(member);
        //创建操作员（登录名密码）
        memberTmOperatorService.store(operator);
 
        ma.setMemberId(member.getMemberId());
        ma.setOperatorId(operator.getOperatorId());
        return ma;
	}

	private void createIdentity(MemberTmMember member) {
		List<MemberTmMemberIdentity> identitys = MemberDomainUtil.convertToMemberIdentityDO(member);
        for (MemberTmMemberIdentity item : identitys) {
        	memberTmMemberIdentityMapper.insertMemberTmMemberIdentity(item);
        }
		
	}

	/**
	 * 根据类型获取会员ID
	 * @param memberType
	 * @return
	 */
	private String genMemberId(Integer memberType) {

        String pre = null;
        String seq = String.valueOf(RandomUtil.randomInt(10000000));

        if (MemberTypeUtil.isCompanyMemberType(memberType)) {
            pre = MaConstant.PRE_MEMBER_COMPANY_ID;
        } else if (MemberTypeUtil.isPersonMemberType(memberType)) {
            pre = MaConstant.PRE_MEMBER_PERSONAL_ID;
        } else if (MemberTypeUtil.isVirtualMemberType(memberType)){
        	pre = MaConstant.PRE_MEMBER_VIRUTLMERCHANT_ID;
        } else {
            pre = MaConstant.PRE_MEMBER_INSTITUTION_ID;
        }
        String memberId = pre
                          + StringUtil.alignRight(seq, MaConstant.MEMBER_ID_SEQ_LENGTH,
                              MaConstant.ID_FIX_CHAR);

        return memberId;
    }

	@Override
	public MemberIntegratedResponse queryMemberIntegratedInfo(MemberIntegratedRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询会员综合信息请求:request={},environment={}", request);
        }
        MemberIntegratedResponse response = new MemberIntegratedResponse();
        try {
            //验证请求参数
            MemberFacadeValidator.validator(request);
            MemberTmMember member = queryMember(MemberDomainUtil.convertReqToMemberIntegratedQuery(request));
            if (member == null) {
                response.setResponseCode(ResponseCode.MEMBER_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.MEMBER_NOT_EXIST.getMessage());
            } else {
                response = MemberDomainUtil.convertToMemberIntegratedResponse(member);
                ResponseUtil.setSuccessResponse(response);
            }

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询会员综合信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询会员综合信息");

        }
        return response;
	}

	private MemberTmMember queryMember(MemberIntegratedQuery query) {
		// query 方法掩码显示
		MemberTmMember member = null;
        if (StringUtil.isNotEmpty(query.getMemberId())) {
            member = memberTmMemberMapper.selectMemberTmMemberById(query.getMemberId());
        } else {
            String memberId = memberTmMemberIdentityMapper.queryMemberId(query.getMemberIdentity(), query.getPlatformType());
            if (StringUtil.isNotEmpty(memberId)) {
                member = memberTmMemberMapper.selectMemberTmMemberById(memberId);
            }
        }
        if (member == null) {
            return null;
        }
        if (query.isRequireAccountInfos()) {
            List<AccountDomain> accounts = null;
            if (CollectionUtils.isEmpty(query.getAccountTypes())) {
                accounts = memberTrMemberAccountService.getAccounts(
                		member.getMemberId(), 
                		null, 
                		AccountCategoryEnum.DPM
                );
            } else if (query.getAccountTypes().size() == 1) {
                String type = String.valueOf(query.getAccountTypes().get(0));
                accounts 	= memberTrMemberAccountService.getAccounts(
                		member.getMemberId(), 
                		type,
                		AccountCategoryEnum.DPM
                );
            } else {
                accounts = memberTrMemberAccountService.getAccounts(member.getMemberId(), query.getAccountTypes());
            }
            member.setAccounts(accounts);
        }
        if (query.isRequireVerifyInfos()) {
            //敏感数据掩码显示切面
            List<MemberTrVerifyEntity> verifys = memberTrVerifyEntityService.queryByMember(member.getMemberId(),null);
            member.setVerifys(verifys);
        }
        if (query.isRequireDefaultOperator()) {
            member.setDefaultOperator(memberTmOperatorService.selectMemberTmOperatorByMemberId(member.getMemberId()));
        }
        return member;
	}

	@Override
	public MemberIntegratedResponse queryMemberIntegratedInfoById(MemberIntegratedIdRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询会员综合信息请求:request={},environment={}", request);
        }
        MemberIntegratedResponse response = new MemberIntegratedResponse();
        try {
            //验证请求参数
            MemberFacadeValidator.validator(request);
            MemberTmMember member = queryMember(MemberDomainUtil.convertReqToMemberIntegratedQuery(request));
            if (member == null) {
                response.setResponseCode(ResponseCode.MEMBER_NOT_EXIST.getCode());
                response.setResponseMessage(ResponseCode.MEMBER_NOT_EXIST.getMessage());
            } else {
                response = MemberDomainUtil.convertToMemberIntegratedResponse(member);
                ResponseUtil.setSuccessResponse(response);
            }

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询会员综合信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询会员综合信息");
        }
        return response;
	}

	@Override
	public IntegratedPersonalResponse createIntegratedPersonalMember(IntegratedPersonalRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]集成创建个人会员请求:request={}", request);
        }
        IntegratedPersonalResponse response = new IntegratedPersonalResponse();

        try {
            //1 验证请求参数
            MemberFacadeValidator.validator(request);
            //2 请求参数转换成域对象
            PersonalMember pm = MemberDomainUtil.convertReqToPersonalMember(request);
            MemberTmOperator op = OperatorDomainUtil.convertReqToDefaultOperator(request);
            MemberAccountStatusEnum status = MemberAccountStatusEnum.getByCode(request.getMemberAccountFlag());
            status = status == null ? MemberAccountStatusEnum.NOTACTIVATED : status;
            //3 调用集成创建个人服务
            MemberAndAccount ma = integratedCreatePersonalMember(pm, op, status);
            response.setAccountId(ma.getAccountId());
            response.setCreateTime(new Date());
            response.setMemberId(ma.getMemberId());
            response.setOperatorId(ma.getOperatorId());
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]集成创建个人会员结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "集成创建个人会员");
        }
        return response;
	}

	/**
	 * 集成创建个人会员
	 * @param pm
	 * @param op
	 * @param status
	 * @return
	 * @throws Exception 
	 */
	private MemberAndAccount integratedCreatePersonalMember(PersonalMember member, MemberTmOperator operator,
			MemberAccountStatusEnum statusEnum) throws Exception {
		 //1 敏感数据加密
        dataEncryptService.encrypt(member, operator);
        //2 创建个人会员
        String memberId = genMemberId(member.getMemberType());
        member.setMemberId(memberId);
        operator.setMemberId(memberId);
        //创建会员基本户信息
        if (needBasicAccount(statusEnum)) {
            generateBasicAccount(member,statusEnum);
        }

        MemberAndAccount ma = new MemberAndAccount();
        ma = personalMemberStore(member, operator);
        //更新账户信息
        if (needBasicAccount(statusEnum)) {
            doOpenBasicAccount(member, ma);
        }
        return ma;
	}
	private void doOpenBasicAccount(MemberTmMember member, MemberAndAccount ma) {
        //3 调用储值
        String accountId = memberTrMemberAccountService.openAccount(member.getBaseAccount());
        //4 更新账户关系，会员标识设置为激活
        ma.setAccountId(accountId);
        member.getBaseAccount().setAccountId(accountId);
        reStoreBaseAccount(member.getBaseAccount());
    }

	private void reStoreBaseAccount(AccountDomain baseAccount) {
		
		int i = memberTmMemberMapper.updateActiveTime(MemberStatusEnum.NORMAL.getCode().intValue(), baseAccount.getMemberId());
		 if (i == 0) {
	            try {
					throw new MaBizException(ResponseCode.MEMBER_ACTIVE_FAIL);
				} catch (MaBizException e) {
					e.printStackTrace();
				}
	        }
		/*int j= memberTrMemberAccountService.updateAccountId(baseAccount.getAccountId(),
				baseAccount.getAccountName(), baseAccount.getMemberId());
	        if (j == 0) {
	            try {
					throw new MaBizException(ResponseCode.MEMBER_ACTIVE_FAIL);
				} catch (MaBizException e) {
					e.printStackTrace();
				}
	        }*/
	}

	private MemberAndAccount personalMemberStore(PersonalMember member, MemberTmOperator operator) {
		MemberAndAccount ma = new MemberAndAccount();
        //创建会员标识
        try {
            this.createIdentity(member);
        } catch (Exception e) {
            if (SQLExceptionUtil.isUniqueException(e)) {
                try {
					throw new MaBizException(ResponseCode.MEMBER_IDENTITY_EXIST);
				} catch (MaBizException e1) {
					e1.printStackTrace();
				}
            } else {
                logger.warn("创建会员标志异常", e);
                try {
					throw new MaBizException(ResponseCode.MEMBER_CREATE_FAIL, e.getMessage());
				} catch (MaBizException e1) {
					e1.printStackTrace();
				}
            }
        }
        //创建会员
        memberTmMemberMapper.insertMemberTmMember(MemberDomainUtil.convertToMemberDO(member));
        MemberTrPersonalMember personalMemberDO = MemberDomainUtil.convertToPersonalMemberDO(member);
        //创建个人会员
        memberTrPersonalMemberMapper.insertMemberTrPersonalMember(personalMemberDO);
        //创建操作员（登录名密码）
        memberTmOperatorService.store(operator);
        //创建认证信息
        memberTrVerifyEntityService.addVerifys(member.getVerifys(), member.getMemberId());
        /*//创建账户关系
        if ((member.getAccounts() != null || member.getAccounts().size() > 0)) {
        	//新增一张账户明细表
        	memberTrMemberAccountService.insertMemberTrMemberAccount(member.getBaseAccount());
        }*/
        ma.setMemberId(member.getMemberId());
        ma.setOperatorId(operator.getOperatorId());
        return ma;
	}

	private boolean needBasicAccount(MemberAccountStatusEnum statusEnum) {
        return (MemberAccountStatusEnum.ACTIVATED == statusEnum) || (MemberAccountStatusEnum.ACTIVATED_ALL == statusEnum);
    }
	
	 private void generateBasicAccount(PersonalMember member, MemberAccountStatusEnum statusEnum) {
	        ActivateStatusEnum activate = null;
	        if(MemberAccountStatusEnum.ACTIVATED_ALL == statusEnum){
	            activate = ActivateStatusEnum.ACTIVATED;
	        }else{
	            activate = ActivateStatusEnum.NOTACTIVATED;
	        }
	        AccountDomain accountDomain = AccountDomainUtil.buildOpenBaseAccountRequest(member,activate);
	        member.addAccount(accountDomain);
	    }
	 
	 private void generateBasicAccount(MemberTrCompanyMember member, MemberAccountStatusEnum statusEnum) {
	        ActivateStatusEnum activate = null;
	        if(MemberAccountStatusEnum.ACTIVATED_ALL == statusEnum){
	            activate = ActivateStatusEnum.ACTIVATED;
	        }else{
	            activate = ActivateStatusEnum.NOTACTIVATED;
	        }
	        AccountDomain accountDomain = AccountDomainUtil.buildOpenBaseAccountRequest(member,activate);
	        member.addAccount(accountDomain);
	    }

	@Override
	public Response setPersonalMemberInfo(PersonalMemberInfoRequest request) {

        if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]设置个人会员信息请求:request={}", request);
        }
        Response response = new Response();
        try {
            //验证请求参数
            MemberFacadeValidator.validator(request);
            PersonalMember member = MemberDomainUtil.convertReqToPersonalMember(request);
            boolean rest = setPersonalMember(member);
            if (rest) {
                ResponseUtil.setSuccessResponse(response);
            } else {
                response.setResponseCode(ResponseCode.MEMBER_UPDATE_FAIL.getCode());
                response.setResponseMessage(ResponseCode.MEMBER_UPDATE_FAIL.getMessage());
            }

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]设置个人会员信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "设置个人会员信息");
        }
        return response;
	}

	private boolean setPersonalMember(PersonalMember member) throws MaBizException {
		 //敏感数据切面
		MemberTmMember m = memberValidator.validateMemberExist(member.getMemberId());
        if (!MemberTypeUtil.isPersonMemberType(m.getMemberType())) {
        	String str = "";
        	if(m.getMemberType() == 1){//1个人 2 公司 3 组织 9 虚拟商户
        		str = "个人";
        	}else if(m.getMemberType() == 2){
        		str = "公司";
        	}else if(m.getMemberType() == 3){
        		str = " 组织";
        	}else if(m.getMemberType() == 9){
        		str = "虚拟商户";
        	}
            throw new MaBizException(ResponseCode.MEMBER_TYPE_FAIL, "会员编号为："
                                                                    + m.getMemberId()
                                                                    + "会员类型为："
                                                                    + str);
        }
        int row = updatePersonMemberInfo(member);
        return row >= 1 ? true : false;
	}

	private int updatePersonMemberInfo(PersonalMember member) {
		 int row = 0;
        MemberTrPersonalMember personalMember = memberTrPersonalMemberMapper.selectMemberTrPersonalMemberById(member.getMemberId());
        MemberTmMember tmMemberDO = new MemberTmMember();
        tmMemberDO.setMemberId(member.getMemberId());
        tmMemberDO.setMemberName(member.getMemberName());
        tmMemberDO.setInvitCode(member.getInvitCode());
        
        row = memberTmMemberMapper.updateMember(tmMemberDO);
        MemberTrPersonalMember trPersonalMember = MemberDomainUtil.convertToPersonalMemberDO(member);
        if (personalMember == null) {
            //新增
            int memberId = memberTrPersonalMemberMapper.insertMemberTrPersonalMember(trPersonalMember);
            if (member.getMemberId().equals(memberId)) {
                row++;
            }
        } else {
            //修改
            row += memberTrPersonalMemberMapper.updateMemberTrPersonalMember(trPersonalMember);
        }
        return row;
	}

	/**
	 * 激活个人会员
	 */
	@Override
	public ActivatePersonalResponse activatePersonalMemberInfo(ActivatePersonalRequest request) {
		 if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]激活个人会员请求:request={}", request);
        }
        ActivatePersonalResponse response = new ActivatePersonalResponse();
        try {
            //验证请求参数
            MemberFacadeValidator.validator(request);
            //参数转换
            PersonalMember member = MemberDomainUtil.convertReqToPersonalMember(request);
            MemberTmOperator operator = new MemberTmOperator();
            operator.setMemberId(member.getMemberId());
            boolean isActivate = request.isActivateAccount();
            if (StringUtil.isNotBlank(request.getPayPassword())) {
                String ticket = uesServiceClient.encryptData(StringUtil.trim(request
                    .getPayPassword()),EncryptType.AES);
                PayPassWord payPassWord = new PayPassWord();
                payPassWord.setPassWord(ticket);
                operator.addPayPwd(payPassWord);
                isActivate = true;
            }
            MemberAndAccount ma = activatePersonalMember(member, operator, isActivate);
            response.setAccountId(ma.getAccountId());
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]激活个人会员结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "激活个人会员");
        }

        return response;
	}

	/**
	 * 激活个人会员
	 * @param member
	 * @param operator
	 * @param isActivate
	 * @return
	 * @throws MaBizException 
	 */
	private MemberAndAccount activatePersonalMember(PersonalMember member, MemberTmOperator operator,
			boolean isActivate) throws MaBizException {

        MemberAndAccount ma = new MemberAndAccount();
        //校验会员是否存在
        MemberTmMember m = memberValidator.validateMemberExist(member.getMemberId());
        //校验会员状态是否为未激活
        if (MemberStatusEnum.UNACTIVE.getCode().intValue() != m.getStatus()) {
        	String str = "";
        	if(m.getStatus() == 0){//UNACTIVE(0L, "未激活"), NORMAL(1L, "正常"), SLEEP(2L, "休眠"), CANCEL(3L, "销户");
        		str = "未激活";
        	}else if(m.getStatus() == 1){
        		str = "正常";
        	}else if(m.getStatus() == 2){
        		str = "休眠";
        	}else if(m.getStatus() == 3){
        		str = "销户";
        	}
            throw new MaBizException(ResponseCode.MEMBER_ALREADY_ACTIVE, "会员编号为"
                                                                         + member.getMemberId()
                                                                         + "的会员"
                                                                         +str);
        }
        MemberTypeEnum memberType = MemberTypeEnum.getByCode(m.getMemberType().longValue());
        if (MemberTypeUtil.isCompanyMemberType(memberType.getCode().intValue())) {
            throw new MaBizException(ResponseCode.MEMBER_TYPE_FAIL);
        } else if (MemberTypeUtil.isPersonMemberType(m.getMemberType()) && !(member instanceof PersonalMember)) {
            throw new MaBizException(ResponseCode.MEMBER_TYPE_FAIL);
        }

        PayPassWord paypwd = (operator == null ? null : operator.getBasePayPassword());
        ActivateStatusEnum status = null;
        if (!isActivate) {
            //开户但账户未不激活
            status = ActivateStatusEnum.NOTACTIVATED;
        } else {
            status = ActivateStatusEnum.ACTIVATED;
        }
        AccountDomain account = AccountDomainUtil.buildOpenBaseAccountRequest(member, status);
        //（1）设置创建会员账户关系
        storeForUpdate(account);
        if (StringUtil.isBlank(account.getAccountId())) {
            //（2）调用储值创建账户关系
            String accountId = memberTrMemberAccountService.openAccount(account);
            account.setAccountId(accountId);
        }
        if (member.getAccounts() != null) {
            member.getAccounts().clear();
        }
        member.addAccount(account);
        //（3）1.企业：更新会员状态，更新会员账户关系中的账号,设置支付密码
        //     2.个人：更新会员信息（状态），更新会员账户关系中的账号,设置支付密码
        MemberTmOperator defaultOperator = memberTmOperatorService.selectMemberTmOperatorByMemberId(member.getMemberId());
        if (!((paypwd == null) || (paypwd.getPassWord() == null))) {
            operator.getBasePayPassword().setAccountId(account.getAccountId());
            operator.setOperatorId(defaultOperator.getOperatorId());
            ma.setOperatorId(defaultOperator.getOperatorId());
        } else {
            operator = null;
        }
        activeMember(member, operator);
        ma.setAccountId(account.getAccountId());
        ma.setMemberId(member.getMemberId());
        ma.setOperatorId(defaultOperator.getOperatorId());
        return ma;
	}

	private void activeMember(PersonalMember member, MemberTmOperator operator) {
		reStoreBaseAccount(member.getBaseAccount());
        if (operator != null && operator.getBasePayPassword() != null) {
        	memberTrPasswordService.store(operator);
        }
	}

	public void storeForUpdate(AccountDomain account) throws MaBizException {
        //锁定会员
		memberTmMemberMapper.getMemberByIdForUpdate(account.getMemberId());

        Long accountType = account.getAccountType();
        // 第一步：根据会员，查询账户关系
        List<MemberTrMemberAccount> accountList = memberTrMemberAccountService.queryAllByMemberAndTypeId(
            account.getMemberId(), String.valueOf(accountType), AccountCategoryEnum.DPM.getCode());
        // 判断是否有空的账户关系存在，没有更新账户的记录，如有，则更新当前记录，不产生新的会员账户关系
        if (null != accountList && accountList.size() > 0) {
            for (MemberTrMemberAccount item : accountList) {
                /*
                if (StringUtil.isEmpty(item.getAccountId())) {
                    account.setOriginalRequestNo(item.getOriginalRequestNo());
                    return;
                }
                */
                //modify 20130619,会员、账户类型、账户名称相同幂等返回
                if (account.getAccountName().equals(item.getAlias())) {
                    account.setOriginalRequestNo(item.getOriginalRequestNo());
                    account.setAccountId(item.getAccountId());
                    return;
                }
            }
        }
        account.setOriginalRequestNo(String.valueOf(RandomUtil.randomInt(10000000)));
        // 第三步：新增会员账户关系信息
        memberTrMemberAccountService.insertMemberTrMemberAccount(account);
    }

	/**
	 * 更新会员锁定状态
	 */
	@Override
	public Response updateMemberLockStatus(UpdateMemberLockStatusRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]设置会员锁定状态请求:request={}", request);
        }
        Response response = new Response();
        try {
            //验证请求参数
            MemberFacadeValidator.validator(request);
            MemberTmMember member = MemberDomainUtil.convertReqToMember(request);
            boolean rest = updateMemberLockStatus(member);
            if (rest) {
                ResponseUtil.setSuccessResponse(response);
            } else {
                if(LockEnum.LOCKED.getCode().intValue() == member.getLockStatus()){
                    response.setResponseCode(ResponseCode.MEMBER_LOCK_FAIL.getCode());
                    response.setResponseMessage(ResponseCode.MEMBER_LOCK_FAIL.getMessage());
                }else{
                    response.setResponseCode(ResponseCode.MEMBER_UNLOCK_FAIL.getCode());
                    response.setResponseMessage(ResponseCode.MEMBER_UNLOCK_FAIL.getMessage());
                }
            }

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]设置会员锁定状态结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "设置会员锁定状态");
        }
        return response;
	}

	private boolean updateMemberLockStatus(MemberTmMember member) throws MaBizException {
		MemberTmMember memberDo = memberValidator.validateMemberExist(member.getMemberId());
        if (memberDo.getLockStatus().equals(member.getLockStatus())) {
            return true;
        }
        int row = memberTmMemberMapper.updateLockStatus(member);
        return row == 1 ? true : false;
	}

	@Override
	public IntegratedCompanyResponse createIntegratedCompanyMember(IntegratedCompanyRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]创建集成企业会员请求:request={}", request);
        }
         IntegratedCompanyResponse response = new IntegratedCompanyResponse();
         try {
         /* 企业会员集成创建
         * 1.创建会员标识-创建企业会员信息 createMemberInfo
         * 2.创建基础会员
         * 3.创建操作员
         * */
         //验证请求参数
         MemberFacadeValidator.validator(request);
         
         //参数转换
         //基础会员
         MemberTrCompanyMember companyMember = MemberDomainUtil.convertReqToMember(request);
         //操作员
         MemberTmOperator operator = OperatorDomainUtil.convertReqToDefaultOperator(request);
         if (StringUtil.isNotBlank(request.getLoginPassword())) {
             String ticket = uesServiceClient.encryptData(StringUtil.trim(request
                 .getLoginPassword()),EncryptType.AES);
             operator.setPassword(ticket);
         } else {
             operator.setPassword(null);
         } 
         //设置账户状态
         MemberAccountStatusEnum accountStatus = MemberAccountStatusEnum.getByCode(request
             .getMemberAccountFlag());
         accountStatus = accountStatus == null ? MemberAccountStatusEnum.NOTACTIVATED : accountStatus;
         //设置商户信息-设置商户信息
         MemberTmMerchant merchant = null;
         if(null != request.getMerchantInfo()){
             merchant = request.getMerchantInfo();
         }
         //设置企业信息-设置企业信息setCompanyMember
         boolean haveCompanyInfo = false;
         if(null != request.getCompanyInfo()){
            haveCompanyInfo = true;
         }
         MemberAndAccount ma  = integratedCreateCompanyMember(companyMember, operator, merchant, accountStatus, haveCompanyInfo);
         response.setAccountId(ma.getAccountId());
         response.setMemberId(ma.getMemberId());
         response.setOperatorId(ma.getOperatorId());
         response.setMerchantId(ma.getMerchantId());
         ResponseUtil.setSuccessResponse(response);
 
         if (logger.isInfoEnabled()) {
             logger.info("[APP<-MA_1]创建会员结果:response={}", response);
         }
         } catch (Exception e) {
             ResponseUtil.fillResponse(response, e, "设置个人会员信息");
         }
//         * 5.创建账户信息-激活会员信息activateCompanymember，账户类型更新
//         * 是否集成 6.创建商户信息-设置商户信息set
         return response;
	}

	private MemberAndAccount integratedCreateCompanyMember(MemberTrCompanyMember member,
			MemberTmOperator operator, MemberTmMerchant merchant, MemberAccountStatusEnum statusEnum,
			boolean haveCompanyInfo) throws MaBizException {
		String memberId = genMemberId(member.getMemberType());
        member.setMemberId(memberId);
        operator.setMemberId(memberId);
        //创建会员基本户信息
        if (needBasicAccount(statusEnum)) {
           generateBasicAccount(member,statusEnum);
        }
        MemberAndAccount ma = new MemberAndAccount();
        ma = integratedStore(member, operator, merchant,haveCompanyInfo);
        if (needBasicAccount(statusEnum)) {
            //开户并更新账户关系
            doOpenBasicAccount(member, ma);
        }
        return ma;
	}

	private MemberAndAccount integratedStore(MemberTrCompanyMember member, MemberTmOperator defaultOperator,
			MemberTmMerchant merchant, boolean haveCompanyInfo) throws MaBizException {
		MemberAndAccount ma = new MemberAndAccount();
        //创建会员标识
        try {
            this.createIdentity(member);
        } catch (Exception e) {
            logger.warn("创建会员标志异常", e);
            if (SQLExceptionUtil.isUniqueException(e)) {
                throw new MaBizException(ResponseCode.MEMBER_IDENTITY_EXIST);
            } else {
                throw new MaBizException(ResponseCode.MEMBER_CREATE_FAIL, e.getMessage());
            }
        }
        //创建会员
        memberTmMemberMapper.insertMemberTmMember(MemberDomainUtil.convertToMemberDO(member));
        //创建操作员（登录名密码）
        memberTmOperatorService.store(defaultOperator);
        //创建账户关系
        if ((member.getAccounts() != null || member.getAccounts().size() > 0)) {
        	//新增一张账户明细表
        	memberTrMemberAccountService.insertMemberTrMemberAccount(member.getBaseAccount());
        }
        //企业信息
        if(haveCompanyInfo){
            //保存企业信息
            //store(member) ;
        	memberTrCompanyMemberMapper.insertMemberTrCompanyMember(member);
        }
        //商户信息
        if(null != merchant){
            merchant.setMemberId(member.getMemberId());
            memberTmMerchantMapper.insertMemberTmMerchant(merchant);
            ma.setMerchantId(merchant.getMerchantId());
        }
        ma.setMemberId(member.getMemberId());
        ma.setOperatorId(defaultOperator.getOperatorId());
        return ma;
	}

	
	
}

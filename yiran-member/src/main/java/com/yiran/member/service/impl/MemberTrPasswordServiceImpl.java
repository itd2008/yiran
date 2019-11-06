package com.yiran.member.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTrPasswordMapper;
import com.yiran.member.request.LoginPwdRequest;
import com.yiran.member.request.OperatorLoginPwdByIdRequest;
import com.yiran.member.request.OperatorLoginPwdLockRequest;
import com.yiran.member.request.OperatorLoginPwdRequest;
import com.yiran.member.request.PersonalLoginPwdRequest;
import com.yiran.member.response.LoginPwdResponse;
import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.domain.MemberTrPassword;
import com.yiran.member.domain.PayPwdLockInfo;
import com.yiran.member.enums.PassWordLockFlagEnum;
import com.yiran.member.enums.PassWordStatusEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.service.IMemberTrPasswordService;
import com.yiran.member.utils.OperatorDomainUtil;
import com.yiran.member.utils.ResponseUtil;
import com.yiran.member.validator.LoginPwdFacadeValidator;
import com.yiran.member.validator.MemberValidator;
import com.yiran.member.validator.OperatorValidator;
import com.yiran.system.service.UesServiceClient;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.common.enums.EncryptType;
import com.yiran.common.support.Convert;

/**
 * 会员支付密码 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrPasswordServiceImpl implements IMemberTrPasswordService 
{
	private Logger logger = LoggerFactory.getLogger(MemberTrPasswordServiceImpl.class); 
	@Autowired
	private MemberTrPasswordMapper memberTrPasswordMapper;
	@Autowired
	private OperatorValidator operatorValidator;
	@Autowired
	private UesServiceClient uesServiceClient;
	@Autowired
	private MemberValidator memberValidator;
	/**
     * 查询会员支付密码信息
     * 
     * @param id 会员支付密码ID
     * @return 会员支付密码信息
     */
    @Override
	public MemberTrPassword selectMemberTrPasswordById(Integer id)
	{
	    return memberTrPasswordMapper.selectMemberTrPasswordById(id);
	}
	
	/**
     * 查询会员支付密码列表
     * 
     * @param memberTrPassword 会员支付密码信息
     * @return 会员支付密码集合
     */
	@Override
	public List<MemberTrPassword> selectMemberTrPasswordList(MemberTrPassword memberTrPassword)
	{
	    return memberTrPasswordMapper.selectMemberTrPasswordList(memberTrPassword);
	}
	
    /**
     * 新增会员支付密码
     * 
     * @param memberTrPassword 会员支付密码信息
     * @return 结果
     */
	@Override
	public int insertMemberTrPassword(MemberTrPassword memberTrPassword)
	{
	    return memberTrPasswordMapper.insertMemberTrPassword(memberTrPassword);
	}
	
	/**
     * 修改会员支付密码
     * 
     * @param memberTrPassword 会员支付密码信息
     * @return 结果
     */
	@Override
	public int updateMemberTrPassword(MemberTrPassword memberTrPassword)
	{
	    return memberTrPasswordMapper.updateMemberTrPassword(memberTrPassword);
	}

	/**
     * 删除会员支付密码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrPasswordByIds(String ids)
	{
		return memberTrPasswordMapper.deleteMemberTrPasswordByIds(Convert.toStrArray(ids));
	}

	@Override
	public int store(MemberTmOperator operator) {
		MemberTrPassword mp = OperatorDomainUtil.convertToPasswordDO(operator);
		return memberTrPasswordMapper.insertMemberTrPassword(mp);
	}

	@Override
	public Response setLoginPwd(LoginPwdRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]设置登陆密码请求:request={}", request);
        }
        Response response = new Response();
        try {
            LoginPwdFacadeValidator.validator(request);
            MemberTmOperator operator = operatorValidator.validateOperatorIdExists(request.getOperatorId());
            operator.setPassword(request.getPassword());
            operator.setPwdStatus(PassWordStatusEnum.getByCode(request.getStatus().intValue()).getCode());
            setLoginPassWord(operator);
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]设置登陆密码返回结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "设置登陆密码");
        }

        return response;
	}

	private void setLoginPassWord(MemberTmOperator operator) throws Exception {
		operator.setFlagEnum(PassWordLockFlagEnum.LOGIN_PASSWORD_FLAG.getCode());
       
        operator.setPassword(uesServiceClient.encryptData(operator.getPassword(), EncryptType.AES));
        //STEP 2 重置登陆密码
        updateLoginPassword(operator.getPassword(),operator.getPwdStatus(),operator.getOperatorId());
	}

	private void updateLoginPassword(String password, Integer pwdStatus, String operatorId) throws MaBizException {
		
		int existPassword = memberTrPasswordMapper.checkExistPassword(password, operatorId);
		// 验证登录密码是否和支付密码一致
        if(existPassword > 0){
        	//登录密码不能和支付密码一致
        	throw new MaBizException(ResponseCode.LOGIN_PASSWORD_EQUAL_PAY);
        }
    	if (memberTrPasswordMapper.updateLoginPassword(password,pwdStatus,operatorId) < 1) {
            throw new MaBizException(ResponseCode.LOGIN_PASSWORD_UPDATE_FAIL);
        }
	}

	@Override
	public LoginPwdResponse checkOperatorLoginPwd(OperatorLoginPwdRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]验证操作员登陆密码请求:request={}", request);
        }
        LoginPwdResponse response = new LoginPwdResponse();
        try {
            LoginPwdFacadeValidator.validator(request);
            Integer platFormType = Integer.valueOf(request.getPlatformType());
            Integer loginNamePlatformType = Integer.valueOf(request.getLoginNamePlatformType());
            MemberTmMember member = memberValidator.validateMemberExistAndNormal(request.getMemberIdentity(), platFormType);
            MemberTmOperator operator = operatorValidator.validateOperatorExistAndNormal(member.getMemberId());
            operatorValidator.validateOperatorLockStatus(operator);
            operatorValidator.validateLoginPassWord(operator.getPassword());
            boolean flag = checkLoginPwd(operator, request.getPassword(),request.isCountWhenWrong());
            response.setMemberId(member.getMemberId());
            response.setOperatorId(operator.getOperatorId());
            if(flag){
            	ResponseUtil.setSuccessResponse(response);
            }else{
            	response.setResponseCode(ResponseCode.LOGIN_PASSWORD_CHECK_FAIL.getCode());
            	response.setResponseMessage(ResponseCode.LOGIN_PASSWORD_CHECK_FAIL.getMessage());
            }
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]验证操作员登陆密码返回结果:response={}", response);
            }
        } catch (MaBizException e) {
            if (ResponseCode.LOGIN_PASSWORD_LOCKED == e.getCode()) {
                response.setRemainNum(Long.parseLong(e.getMessage()));
            } else if (ResponseCode.LOGIN_PASSWORD_CHECK_FAIL == e.getCode()) {
                response.setRemainNum(Long.parseLong(e.getMessage()));
            }
            ResponseUtil.fillResponse(response, e, "验证操作员登陆密码");
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "验证操作员登陆密码");
        }

        return response;
	}

	private boolean checkLoginPwd(MemberTmOperator operator, String password, boolean countWhenWrong) throws MaBizException {
		boolean flag =false;//失败
		if(StringUtil.isNotBlank(operator.getPassword())){
            String pwd = uesServiceClient.getDataByTicket(operator.getPassword(), EncryptType.AES);
            if(pwd.equals(password)){//登录密码和数据库密码不一致
            	//throw new MaBizException(ResponseCode.LOGIN_PASSWORD_CHECK_FAIL);
            	flag = true;
            }
        }
		return flag;
	}

	@Override
	public LoginPwdResponse checkOperatorLoginPwdById(OperatorLoginPwdByIdRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]验证操作员登陆密码请求:request={},environment={}", request);
        }
        LoginPwdResponse response = new LoginPwdResponse();
        try {
            LoginPwdFacadeValidator.validator(request);
            MemberTmOperator operator = operatorValidator.validateOperatorStatusNormal(request.getOperatorId());
            operatorValidator.validateOperatorLockStatus(operator);
            operatorValidator.validateLoginPassWord(operator.getPassword());
            checkLoginPwd(operator, request.getPassword(),request.isCountWhenWrong());
            response.setMemberId(operator.getMemberId());
            response.setOperatorId(operator.getOperatorId());
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]验证操作员登陆密码返回结果:response={}", response);
            }
        } catch (MaBizException e) {
            if (ResponseCode.LOGIN_PASSWORD_LOCKED == e.getCode()) {
                response.setRemainNum(Long.parseLong(e.getMessage()));
            } else if (ResponseCode.LOGIN_PASSWORD_CHECK_FAIL == e.getCode()) {
                response.setRemainNum(Long.parseLong(e.getMessage()));
            }
            ResponseUtil.fillResponse(response, e, "验证操作员登陆密码");
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "验证操作员登陆密码");
        }

        return response;
	}

	@Override
	public LoginPwdResponse checkPersonalLoginPwd(PersonalLoginPwdRequest request) {

        if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]验证个人会员登陆密码请求:request={}", request);
        }
        LoginPwdResponse response = new LoginPwdResponse();
        try {
            LoginPwdFacadeValidator.validator(request);
            Integer platFormType 	= Integer.valueOf(request.getPlatformType());
            MemberTmMember member 			= memberValidator.validateMemberExistAndNormal(request.getMemberIdentity(), platFormType);
            MemberTmOperator operator 		= operatorValidator.validateDefaultOperatorNormal(member.getMemberId());
            
            operatorValidator.validateLoginPassWord(operator.getPassword());
            
            checkLoginPwd(operator, request.getPassword(),request.isCountWhenWrong());
            response.setMemberId(member.getMemberId());
            response.setOperatorId(operator.getOperatorId());
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]验证个人会员登陆密码返回结果:response={}", response);
            }
        } catch (MaBizException e) {
            if (ResponseCode.LOGIN_PASSWORD_LOCKED == e.getCode()) {
                response.setRemainNum(Long.parseLong(e.getMessage()));
            } else if (ResponseCode.LOGIN_PASSWORD_CHECK_FAIL == e.getCode()) {
                response.setRemainNum(Long.parseLong(e.getMessage()));
            }
            ResponseUtil.fillResponse(response, e, "验证个人会员登陆密码");
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "验证个人会员登陆密码");
        }

        return response;
    
	}


	
	
}

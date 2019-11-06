/**
 * 
 */
package com.yiran.member.validator.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.domain.MemberIntegratedQuery;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.enums.MemberStatusEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.mapper.MemberTmMemberIdentityMapper;
import com.yiran.member.mapper.MemberTmMemberMapper;
import com.yiran.member.validator.MemberValidator;

/**
 * <p>会员业务状态验证器</p>
 */
@Service
public class MemberValidatorImpl implements MemberValidator {
	private Logger logger = LoggerFactory.getLogger(MemberValidatorImpl.class);
	@Autowired
	private MemberTmMemberMapper memberTmMemberMapper;
	
	@Autowired
	private MemberTmMemberIdentityMapper memberTmMemberIdentityMapper;
    /*
     * (non-Javadoc)
     * @see com.netfinworks.ma.core.service.validator.MemberValidator#validateMemberExist(java.lang.String)
     */
    @Override
    public MemberTmMember validateMemberExist(String memberId) throws MaBizException {
    	MemberTmMember  m = memberTmMemberMapper.selectMemberTmMemberById(memberId);
        if (m == null) {
            throw new MaBizException(ResponseCode.MEMBER_NOT_EXIST, "会员编号为" + memberId + "的会员不存在");
        }
        return m;
    }
	@Override
	public MemberTmMember validateMemberExistByIdentity(String identity, int pid) throws MaBizException {
		String memberId = memberTmMemberIdentityMapper.queryMemberId(identity, pid);
        if (memberId == null) {
            throw new MaBizException(ResponseCode.MEMBER_IDENTITY_NOT_EXIST, "会员标识为" + identity + "的会员不存在");
        }
        return validateMemberExist(memberId);
	}
	@Override
	public MemberTmMember validateMemberExistAndNotCancelled(String memberId) throws MaBizException {
		 //验证会员存在
		MemberTmMember member = validateMemberExist(memberId);
        //验证会员未注销
        if (member.getStatus() == MemberStatusEnum.CANCEL.getCode().intValue()) {
            throw new MaBizException(ResponseCode.MEMBER_CANCELLED, "会员编号为" + memberId + "的会员已注销。");
        }
        return member;
	}
	@Override
	public MemberTmMember validateMemberExistAndNormal(String identity, int platformType) throws MaBizException {
		MemberIntegratedQuery query = new MemberIntegratedQuery();
        query.setMemberIdentity(StringUtil.toLowerCase(StringUtil.trim(identity)));
        query.setPlatformType(platformType);
        query.setRequireAccountInfos(false);
        query.setRequireVerifyInfos(false);
        MemberTmMember m = queryBaseMember(query);
        
        if (m == null)
            throw new MaBizException(ResponseCode.MEMBER_NOT_EXIST, "会员identity为" + identity + "的会员不存在");
        
        checkMemberStatus(m);
        return m;
	}
	
	private MemberTmMember queryBaseMember(MemberIntegratedQuery query) {
		String memberId = memberTmMemberIdentityMapper.queryMemberId(query.getMemberIdentity(), query.getPlatformType());
        if (StringUtil.isEmpty(memberId)) {
            if (logger.isDebugEnabled()) {
                logger.debug("会员标识为: {},标识平台类型：{} 的会员不存在!",
                    new Object[] { query.getMemberIdentity(), query.getPlatformType() });
                return null;
            }
        }
	
	   return loadBaseMember(memberId);
	}
	
	public MemberTmMember loadBaseMember(String memberId) {
		MemberTmMember member = memberTmMemberMapper.selectMemberTmMemberById(memberId);
        if (member == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("{} 对应的会员不存在！", new Object[] { memberId });
            }
            return null;
        }
        return member;
    }
	/**
     * 验证会员状态
     * @param m
     * @throws MaBizException
     */
    public void checkMemberStatus(MemberTmMember m) throws MaBizException {
        switch (MemberStatusEnum.getByCode(m.getStatus().longValue())) {
            case NORMAL:
                break;
            case CANCEL:
                throw new MaBizException(ResponseCode.MEMBER_CANCELLED, "会员编号为" + m.getMemberId()
                                                                        + "的会员已注销。");
            case SLEEP:
                throw new MaBizException(ResponseCode.MEMBER_SLEEP, "会员编号为" + m.getMemberId()
                                                                    + "的会员已休眠。");
            case UNACTIVE:
                throw new MaBizException(ResponseCode.MEMBER_UNACTIVE, "会员编号为" + m.getMemberId()
                                                                       + "的会员未激活。");
            default:
                break;
        }
    }
    
   
}

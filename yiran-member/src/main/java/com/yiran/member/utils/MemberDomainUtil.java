/**
 *
 */
package com.yiran.member.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netfinworks.common.lang.StringUtil;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.domain.AccountDomain;
import com.yiran.member.domain.MemberIdentity;
import com.yiran.member.domain.MemberIntegratedQuery;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.domain.MemberTmMemberIdentity;
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.domain.MemberTrCompanyMember;
import com.yiran.member.domain.MemberTrPersonalMember;
import com.yiran.member.domain.MemberTrVerifyEntity;
import com.yiran.member.domain.PersonalMember;
import com.yiran.member.domain.Verify;
import com.yiran.member.domain.VerifyInfo;
import com.yiran.member.enums.CareerEnum;
import com.yiran.member.enums.GenderEnum;
import com.yiran.member.enums.IdentityStatusEnum;
import com.yiran.member.enums.IdentityTypeEnum;
import com.yiran.member.enums.LockEnum;
import com.yiran.member.enums.MemberAccountStatusEnum;
import com.yiran.member.enums.MemberStatusEnum;
import com.yiran.member.enums.MemberTypeEnum;
import com.yiran.member.enums.PlatFormTypeEnum;
import com.yiran.member.enums.PositionEnum;
import com.yiran.member.exception.MaIllegalArgumentException;
import com.yiran.member.request.AccountQueryRequest;
import com.yiran.member.request.ActivatePersonalRequest;
import com.yiran.member.request.BaseMemberInfo;
import com.yiran.member.request.CreateMemberInfoRequest;
import com.yiran.member.request.IntegratedCompanyRequest;
import com.yiran.member.request.IntegratedPersonalRequest;
import com.yiran.member.request.MemberIntegratedIdRequest;
import com.yiran.member.request.MemberIntegratedRequest;
import com.yiran.member.request.PersonalMemberInfoRequest;
import com.yiran.member.request.PersonalMemberRequest;
import com.yiran.member.request.UpdateMemberLockStatusRequest;
import com.yiran.member.response.AccountInfo;
import com.yiran.member.response.IdentityInfo;
import com.yiran.member.response.MemberIntegratedResponse;

/**
 * <p>会员对象转换工具类</p>
 */
public class MemberDomainUtil { 

    //******************************* 以下为请求对象转换成领域对象开始********************************//

	public static MemberTmMember convertReqToMember(CreateMemberInfoRequest request) {
		MemberTmMember member = new MemberTmMember();
        String loginName = StringUtil.toLowerCase(StringUtils.trim(request.getLoginName()));
        String identity = StringUtil.toLowerCase(StringUtils.trim(request.getPlatformUserId()));
        MemberTypeEnum memberTypeEnum = MemberTypeEnum.getByCode(request.getMemberType()
            .longValue());
        member.setMemberType(memberTypeEnum.getCode().intValue());
        member.setStatus(MemberStatusEnum.UNACTIVE.getCode().intValue());
        member.setLockStatus(LockEnum.UNLOCK.getCode().intValue());
        member.setMemberName(request.getMemberName());
        int pid = PlatFormTypeEnum.UID.getCode();
        if (StringUtil.isNotBlank(request.getPlatformType())) {
            pid = Integer.parseInt(StringUtil.trim(request.getPlatformType()));
        }

        //登录名平台类型
        int lpid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtil.isNotBlank(request.getLoginNamePlatformType())) {
            lpid = Integer.parseInt(StringUtil.trim(request.getLoginNamePlatformType()));
        }

        MemberIdentity identity1 = new MemberIdentity();
        identity1.setIdentity(loginName);
        identity1.setIdentityType(request.getLoginNameType());
        identity1.setPlatFormType(lpid);
        identity1.setStatus(IdentityStatusEnum.VALID);
        member.addIdentity(identity1);

        if (StringUtil.isNotBlank(identity) && (!loginName.equals(identity) || lpid != pid)) {
            MemberIdentity identity2 = new MemberIdentity();
            identity2.setIdentity(identity);
            identity2.setIdentityType(IdentityTypeEnum.PUID.getCode());
            identity2.setPlatFormType(pid);
            identity2.setStatus(IdentityStatusEnum.VALID);
            member.addIdentity(identity2);
        }
        
        member.setRegisterSource(Integer.parseInt(request.getRegisterSource()));

        String registerSource = getResisterSource(request.getExtention());
        if(StringUtils.isNotEmpty(registerSource)) {
            member.setRegisterSource(Integer.parseInt(registerSource));
        }
        member.setMemo(request.getExtention());
        return member;

    }
	
	
	/**
     * 获取注册来源
     * @param extension
     *          扩展字段  
     * @return
     */
    public static String getResisterSource(String extension) {
        if(StringUtils.isNotEmpty(extension)) {
            JSONObject json = JSONObject.parseObject(extension);
            String registerSource = json.getString(MaConstant.REGISTER_SOURCE);
            return registerSource;
        } else {
            return null;
        }
    }
    
    
    /**
     * 会员标识域--转换成-->DO 对象
     * @param member
     * @return
     */
    public static List<MemberTmMemberIdentity> convertToMemberIdentityDO(MemberTmMember member) {
        if (member == null || member.getIdentitys() == null || member.getIdentitys().isEmpty()) {
            return null;
        }
        List<MemberIdentity> identitys = member.getIdentitys();
        List<MemberTmMemberIdentity> result = new ArrayList<MemberTmMemberIdentity>(identitys.size());
        for (MemberIdentity identity : identitys) {
        	MemberTmMemberIdentity item = new MemberTmMemberIdentity();
            item.setCreateTime(identity.getCreateTime());
            item.setIdentity(StringUtils.lowerCase(identity.getIdentity()));
            item.setIdentityType(identity.getIdentityType());
            item.setIsRecvAddr(1);
            item.setMemberId(member.getMemberId());
            item.setMemo(identity.getMemo());
            item.setStatus(identity.getStatus().getCode());
            item.setUpdateTime(identity.getUpdateTime());
            item.setPid(identity.getPlatFormType());
            result.add(item);
        }

        return result;
    }
    
    /**
     * 会员综合查询请求对象--转换成-->查询对象
     * @param request
     * @return
     */
    public static MemberIntegratedQuery convertReqToMemberIntegratedQuery(MemberIntegratedRequest request) {
        MemberIntegratedQuery query = new MemberIntegratedQuery();
        AccountQueryRequest accReq = request.getAccountRequest();
        if (accReq != null) {
            query.setAccountTypes(accReq.getAccountTypes());
            query.setRequireAccountInfos(accReq.isRequireAccountInfos());
        } else {
            query.setRequireAccountInfos(false);
        }

        query.setMemberIdentity(StringUtil.toLowerCase(StringUtil.trim(request.getMemberIdentity())));

        int pid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtil.isNotBlank(request.getPlatformType())) {
            pid = Integer.parseInt(StringUtil.trim(request.getPlatformType()));
        }
        query.setPlatformType(pid);
        query.setRequireVerifyInfos(request.isRequireVerifyInfos());
        query.setRequireDefaultOperator(request.isRequireDefaultOperator());
        return query;
    }
    
    /**
     * 会员综合查询请求对象--转换成-->查询对象
     * @param request
     * @return
     */
    public static MemberIntegratedQuery convertReqToMemberIntegratedQuery(MemberIntegratedIdRequest request) {
        MemberIntegratedQuery query = new MemberIntegratedQuery();
        AccountQueryRequest accReq = request.getAccountRequest();
        if (accReq != null) {
            query.setAccountTypes(accReq.getAccountTypes());
            query.setRequireAccountInfos(accReq.isRequireAccountInfos());
        } else {
            query.setRequireAccountInfos(false);
        }
        query.setMemberId(request.getMemberId());
        query.setRequireVerifyInfos(request.isRequireVerifyInfos());
        query.setRequireDefaultOperator(request.isRequireDefaultOperator());
        return query;
    }
    
    /**
     * 会员领域对象--转换成-->会员综合查询响应对象
     * @param member
     * @return
     */
    public static MemberIntegratedResponse convertToMemberIntegratedResponse(MemberTmMember member) {
        MemberIntegratedResponse response = new MemberIntegratedResponse();
        BaseMemberInfo baseMemberInfo = new BaseMemberInfo();
    	fillBaseMemberInfo(baseMemberInfo, member);
    	response.setBaseMemberInfo(baseMemberInfo);

        List<AccountDomain> accounts = member.getAccounts();
        if (!(accounts == null || accounts.isEmpty())) {
            List<AccountInfo> accountInfos = new ArrayList<AccountInfo>(accounts.size());
            for (AccountDomain item : accounts) {
                accountInfos.add(AccountDomainUtil.convertToAccountRef(item));
            }
            response.setAccountInfos(accountInfos);
        }

        List<MemberTrVerifyEntity> verifys = member.getVerifys();
        if (!(verifys == null || verifys.isEmpty())) {
            response.setVerifyInfos(verifys);
        }

        MemberTmOperator operator = member.getDefaultOperator();
        if (operator != null) {
            response.setDefaultOperator(operator);
        }

        return response;
    }
    
    public static void fillBaseMemberInfo(BaseMemberInfo info, MemberTmMember member) {
        info.setCreateTime(member.getCreateTime());
        Long lock = member.getLockStatus().longValue();
        info.setLockStatus(lock == null ? null : lock);
        info.setMemberId(member.getMemberId());
        info.setMemberName(member.getMemberName());
        info.setRegisterSource(String.valueOf(member.getRegisterSource()));

        Long statusEnum = member.getStatus().longValue();
        info.setStatus(statusEnum == null ? null : statusEnum);
        Long type = member.getMemberType().longValue();
        info.setMemberType(type == null ? null : type);
        info.setActiveTime(member.getActiveTime());

        //扩展字段组装
        Map<String,String> ext = new HashMap<String, String>();
        if(StringUtils.isNotBlank(member.getMemo())){
        	ext.put("memo", member.getMemo());
        }
        if(StringUtils.isNotBlank(member.getRegisterSourceExt())){
        	ext.put("registerSourceExt", member.getRegisterSourceExt());
        }
        info.setExtention(JSON.toJSONString(ext));
        
        List<MemberIdentity> identitys = member.getIdentitys();
        if (!(identitys == null || identitys.isEmpty())) {
            List<IdentityInfo> list = new ArrayList<IdentityInfo>(identitys.size());
            for (MemberIdentity identity : identitys) {
                IdentityInfo item = new IdentityInfo();
                item.setIdentity(StringUtils.lowerCase(identity.getIdentity()));
                item.setPlatformType(String.valueOf(identity.getPlatFormType()));
                item.setIdentityType(identity.getIdentityType());
                item.setIsUnionAccount(identity.getIsUnionAccount());
                item.setUnionAccountStatus(identity.getUnionAccountStatus());
                list.add(item);
            }
            //个人用户注册时uid 与登录名相同解决方案：一条为uid ，一条为登录名 。
            /*//去掉新浪账户特殊逻辑
            if (identitys.size() == 1 && member instanceof PersonalMember) {
                IdentityInfo identityInfo = list.get(0);
                if (identityInfo.getIdentityType() == IdentityTypeEnum.PUID.getCode()) {
                    IdentityInfo _item = new IdentityInfo();
                    _item.setIdentity(identityInfo.getIdentity());
                    _item.setPlatformType(identityInfo.getPlatformType());
                    _item.setIdentityType(IdentityTypeEnum.COMMON_CHAR.getCode());
                    list.add(_item);
                }
            }*/
            info.setIdentitys(list);
        }
    }
    
    /**
     * 集成创建个人会员请求对象--转换成-->个人会员域对象
     * @param request 集成创建个人会员请求对象
     * @return 个人会员域对象
     */
    public static PersonalMember convertReqToPersonalMember(IntegratedPersonalRequest request) {
    	PersonalMember pm = new PersonalMember();
        PersonalMemberRequest input = request.getPersonalRequest();
        String loginName = StringUtil.toLowerCase(StringUtil.trim(input.getLoginName()));
        String identity = StringUtil.toLowerCase(StringUtil.trim(request.getPlatformUserId()));
        pm.setMemberName(StringUtil.trim(input.getMemberName()));
        pm.setDefaultLoginName(loginName);
        pm.setMemberType(MemberTypeUtil.getPersonMemberType().getCode().intValue());
        if(MemberAccountStatusEnum.getByCode(request.getMemberAccountFlag()) == MemberAccountStatusEnum.ACTIVATED_ALL ||
                MemberAccountStatusEnum.getByCode(request.getMemberAccountFlag()) == MemberAccountStatusEnum.ACTIVATED ) {
            pm.setStatus(MemberStatusEnum.NORMAL.getCode().intValue());
        } else {
            pm.setStatus(MemberStatusEnum.UNACTIVE.getCode().intValue());
        }
        pm.setLockStatus(LockEnum.UNLOCK.getCode().intValue());
        pm.setTrueName(input.getRealName());
        pm.setBirthDay(input.getBirthDay());
        if (input.getCareer() == null) {
            pm.setCareer(CareerEnum.DEFAULT);
        } else {
            CareerEnum career = CareerEnum.getByCode(input.getCareer());
            if (career == null) {
                throw new MaIllegalArgumentException("非法的职业类型:" + input.getCareer());
            }
            pm.setCareer(career);
        }
        if (input.getGender() == null) {
            pm.setGender(GenderEnum.UNKOWN);
        } else {
            GenderEnum gender = GenderEnum.getByCode(input.getGender());
            if (gender == null) {
                throw new MaIllegalArgumentException("非法的性别类型:" + input.getGender());
            }
            pm.setGender(gender);
        }
        if (input.getPosition() == null) {
            pm.setPosition(PositionEnum.DEFAULT);
        } else {
            PositionEnum position = PositionEnum.getByCode(input.getPosition());
            if (position == null) {
                throw new MaIllegalArgumentException("非法的职位类型:" + input.getPosition());
            }
            pm.setPosition(position);
        }
        //uid 平台类型
        int pid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtil.isNotBlank(request.getPlatformType())) {
            pid = Integer.parseInt(StringUtil.trim(request.getPlatformType()));
        }
        //登录名平台类型
        int lpid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtil.isNotBlank(input.getLoginNamePlatformType())) {
            lpid = Integer.parseInt(StringUtil.trim(input.getLoginNamePlatformType()));
        }

        //优先创建uid
        MemberIdentity uidIdentity = new MemberIdentity();
        if (StringUtil.isNotEmpty(identity)) {
            uidIdentity.setIdentity(identity);
            uidIdentity.setIdentityType(IdentityTypeEnum.PUID.getCode());
            uidIdentity.setPlatFormType(pid);
            uidIdentity.setStatus(IdentityStatusEnum.VALID);
            pm.addIdentity(uidIdentity);
        }

        if (!loginName.equals(identity) || lpid != pid) {
            MemberIdentity loginIdentity = new MemberIdentity();
            loginIdentity.setIdentity(loginName);
            loginIdentity.setIdentityType(input.getLoginNameType());
            loginIdentity.setPlatFormType(lpid);
            loginIdentity.setStatus(IdentityStatusEnum.VALID);
            pm.addIdentity(loginIdentity);
        }

        List<VerifyInfo> verifyInfos = request.getVerifys();
        if (!(verifyInfos == null || verifyInfos.isEmpty())) {
            List<MemberTrVerifyEntity> verifys = new ArrayList<MemberTrVerifyEntity>(verifyInfos.size());
            for (VerifyInfo item : verifyInfos) {
                verifys.add(VerifyDomainUtil.converReqToMemberTrVerifyEntity(item));
            }
            pm.setVerifys(verifys);
        }
        
        String registerSource = getResisterSource(request.getExtention());
        
        if (StringUtils.isNotBlank(request.getRegisterSource()))
    		pm.setRegisterSource(Integer.parseInt(request.getRegisterSource()));
        if(StringUtils.isNotEmpty(registerSource)) {
            pm.setRegisterSource(Integer.parseInt(registerSource));
        }
        pm.setMemo(input.getExtention());
        pm.setInvitCode(request.getInvitCode());
        pm.setRegisterSourceExt(request.getRegisterSourceExt());
        return pm;
    }
    //******************************以上为领域对象转换成响应对象结束**************************************************//


	public static MemberTmMember convertToMemberDO(PersonalMember member) {
		MemberTmMember tmMember = new MemberTmMember();
		tmMember.setMemberId(member.getMemberId());
        tmMember.setMemberName(member.getMemberName());
        tmMember.setMemberShortName(member.getMemberShortName());
        if(null!=member.getMemberType()){
        	tmMember.setMemberType(member.getMemberType());
        }
        if(null!=member.getStatus()){
        	tmMember.setStatus(member.getStatus());
        }
        if(null!=member.getLockStatus()){
        	tmMember.setLockStatus(member.getLockStatus());
        }
        tmMember.setFromIp(member.getFromIp());
        tmMember.setCreateUser(member.getCreateUser());
        if(null != member.getRegisterSource()) {
            tmMember.setRegisterSource(member.getRegisterSource());
        }
        if (member.getVerifyLevel() != null)
        	tmMember.setVerifyLevel(member.getVerifyLevel());
        tmMember.setMemo(member.getMemo());
        tmMember.setInvitCode(member.getInvitCode());
        tmMember.setRegisterSourceExt(member.getRegisterSourceExt());
		return tmMember;
	}

	public static MemberTmMember convertToMemberDO(MemberTrCompanyMember member) {
		MemberTmMember tmMember = new MemberTmMember();
		tmMember.setMemberId(member.getMemberId());
        tmMember.setMemberName(member.getMemberName());
        tmMember.setMemberShortName(member.getMemberShortName());
        if(null!=member.getMemberType()){
        	tmMember.setMemberType(member.getMemberType());
        }
        if(null!=member.getStatus()){
        	tmMember.setStatus(member.getStatus());
        }
        if(null!=member.getLockStatus()){
        	tmMember.setLockStatus(member.getLockStatus());
        }
        tmMember.setFromIp(member.getFromIp());
        tmMember.setCreateUser(member.getCreateUser());
        if(null != member.getRegisterSource()) {
            tmMember.setRegisterSource(member.getRegisterSource());
        }
        if (member.getVerifyLevel() != null)
        	tmMember.setVerifyLevel(member.getVerifyLevel());
        tmMember.setMemo(member.getMemo());
        tmMember.setInvitCode(member.getInvitCode());
        tmMember.setRegisterSourceExt(member.getRegisterSourceExt());
		return tmMember;
	}

	public static MemberTrPersonalMember convertToPersonalMemberDO(PersonalMember pm) {

		MemberTrPersonalMember trPersonalMember = new MemberTrPersonalMember();
        trPersonalMember.setMemberId(pm.getMemberId());
        trPersonalMember.setBirthday(pm.getBirthDay());
        trPersonalMember.setCareer(pm.getCareer().getCode().intValue());
        //trPersonalMember.setCertType(null);
        trPersonalMember.setDefaultLoginName(pm.getDefaultLoginName());
        trPersonalMember.setGender(pm.getGender().getCode().intValue());
        //trPersonalMember.setIdNo();
        trPersonalMember.setPostition(pm.getPosition().getCode().intValue());
        trPersonalMember.setTrueName(pm.getTrueName());
        trPersonalMember.setCreateUser(pm.getCreateUser());
		return trPersonalMember;
	}
	
	

    /**
     * 设置个人会员请求对象--转换成-->个人会员域对象
     * @param request 设置个人会员请求对象
     * @return 个人会员域对象
     */
    public static PersonalMember convertReqToPersonalMember(PersonalMemberInfoRequest request) {
        PersonalMember member = new PersonalMember();

        member.setMemberId(request.getMemberId());
        member.setMemberName(StringUtil.trim(request.getMemberName()));
        member.setMemberType(MemberTypeUtil.getPersonMemberType().getCode().intValue());
        member.setStatus(MemberStatusEnum.UNACTIVE.getCode().intValue());
        member.setTrueName(request.getRealName());
        member.setBirthDay(request.getBirthDay());
        if (request.getCareer() == null) {
            member.setCareer(CareerEnum.DEFAULT);
        } else {
            CareerEnum career = CareerEnum.getByCode(request.getCareer());
            if (career == null) {
                throw new MaIllegalArgumentException("非法的职业类型:" + request.getCareer());
            }
            member.setCareer(career);
        }
        if (request.getGender() == null) {
            member.setGender(GenderEnum.UNKOWN);
        } else {
            GenderEnum gender = GenderEnum.getByCode(request.getGender());
            if (gender == null) {
                throw new MaIllegalArgumentException("非法的性别类型:" + request.getGender());
            }
            member.setGender(gender);
        }
        if (request.getPosition() == null) {
            member.setPosition(PositionEnum.DEFAULT);
        } else {
            PositionEnum position = PositionEnum.getByCode(request.getPosition());
            if (position == null) {
                throw new MaIllegalArgumentException("非法的职位类型:" + request.getPosition());
            }
            member.setPosition(position);
        } 
        member.setInvitCode(request.getInvitCode());
        return member;
    }
    
    /**
     * 激活个人会员请求对象--转换成-->个人会员域对象
     * @param request 激活个人会员请求对象
     * @return 个人会员域对象
     */
    public static PersonalMember convertReqToPersonalMember(ActivatePersonalRequest request) {
        PersonalMemberInfoRequest req = request.getPersonalMemberInfo();
        return convertReqToPersonalMember(req);
    }
    
    /**
     * 更新会员状态请求对象---转换成--->会员对象
     * @param request
     * @return
     */
    public static MemberTmMember convertReqToMember(UpdateMemberLockStatusRequest request) {
    	MemberTmMember member = new MemberTmMember();
        member.setMemberId(request.getMemberId());
        member.setLockStatus(request.getLockStatus().intValue());
        return member;
    }
    
    /**
     * 创建会员请求对象--转换成-->会员域对象
     * @param request 创建会员请求对象
     * @return 会员域对象
     */
    public static MemberTrCompanyMember convertReqToMember(IntegratedCompanyRequest request) {
    	MemberTrCompanyMember member = new MemberTrCompanyMember();
        String loginName = StringUtil.toLowerCase(StringUtil.trim(request.getLoginName()));
        String identity = StringUtil.toLowerCase(StringUtil.trim(request.getPlatformUserId()));
        MemberTypeEnum memberTypeEnum = MemberTypeEnum.getByCode(request.getMemberType()
            .longValue());
        member.setMemberType(request.getMemberType().intValue());
        member.setStatus(MemberStatusEnum.UNACTIVE.getCode().intValue());
        member.setLockStatus(LockEnum.UNLOCK.getCode().intValue());
        member.setMemberName(request.getMemberName());
        int pid = PlatFormTypeEnum.UID.getCode();
        if (StringUtil.isNotBlank(request.getPlatformType())) {
            pid = Integer.parseInt(StringUtil.trim(request.getPlatformType()));
        }

        //登录名平台类型
        int lpid = PlatFormTypeEnum.DEFAULT.getCode();
        if (StringUtil.isNotBlank(request.getLoginNamePlatformType())) {
            lpid = Integer.parseInt(StringUtil.trim(request.getLoginNamePlatformType()));
        }

        MemberIdentity identity1 = new MemberIdentity();
        identity1.setIdentity(loginName);
        identity1.setIdentityType(request.getLoginNameType());
        identity1.setPlatFormType(lpid);
        identity1.setStatus(IdentityStatusEnum.VALID);
        member.addIdentity(identity1);

        if (StringUtil.isNotBlank(identity) && (!loginName.equals(identity) || lpid != pid)) {
            MemberIdentity identity2 = new MemberIdentity();
            identity2.setIdentity(identity);
            identity2.setIdentityType(IdentityTypeEnum.PUID.getCode());
            identity2.setPlatFormType(pid);
            identity2.setStatus(IdentityStatusEnum.VALID);
            member.addIdentity(identity2);
        }
        
        String registerSource = getResisterSource(request.getExtention());
        if(StringUtils.isNotEmpty(registerSource)) {
            member.setRegisterSource(Integer.parseInt(registerSource));
        }
        return member;
    }
    

}

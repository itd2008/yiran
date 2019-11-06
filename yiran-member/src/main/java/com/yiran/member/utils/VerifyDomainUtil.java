/**
 * 
 */
package com.yiran.member.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.domain.CoopMember;
import com.yiran.member.domain.MemberTrVerifyEntity;
import com.yiran.member.domain.MemberTrVerifyRef;
import com.yiran.member.domain.Verify;
import com.yiran.member.domain.VerifyInfo;
import com.yiran.member.domain.VerifyQuery;
import com.yiran.member.enums.VerifyStatusEnum;
import com.yiran.member.enums.VerifyTypeEnum;
import com.yiran.member.request.VerifyBindQueryRequest;
import com.yiran.member.request.VerifyQueryRequest;


/**
 * <p>认证信息领域对象工具</p>
 */
public class VerifyDomainUtil {
	
	
	public static MemberTrVerifyEntity converReqToMemberTrVerifyEntity(VerifyInfo verifyInfo){
		MemberTrVerifyEntity verify = new MemberTrVerifyEntity();
		verify.setExpireTime(verifyInfo.getExpireTime());
        verify.setVerifyImgPath(verifyInfo.getImgPath());
        verify.setVerifiedTime(verifyInfo.getVerifiedTime());
        verify.setVerifyEntity(replaceHrySign(verifyInfo.getVerifyEntity()));
        verify.setVerifyType(verifyInfo.getVerifyType());
        verify.setVerifyEntityId(verifyInfo.getVerifyId() == null ? -1 : verifyInfo.getVerifyId().intValue());
        Integer status = verifyInfo.getStatus();
        if (status != null) {
            verify.setStatus(status);
        } else {
            verify.setStatus(VerifyStatusEnum.UNAUTHENTICATED.getCode().intValue());
        }
        verify.setChannel(verifyInfo.getChannel());

        return verify;
    }
	
	
    /**
     * 认证请求信息转换成认证领域对象
     * @param verifyInfo 认证请求信息
     * @return 认证领域对象
     */
    public static Verify converReqToVerify(VerifyInfo verifyInfo){
        Verify verify = new Verify();
        verify.setExpireTime(verifyInfo.getExpireTime());
        verify.setImgPath(verifyInfo.getImgPath());
        verify.setMemberId(verifyInfo.getMemberId());
        verify.setVerifiedTime(verifyInfo.getVerifiedTime());
        verify.setVerifyEntity(replaceHrySign(verifyInfo.getVerifyEntity()));
        verify.setVerifyType(verifyInfo.getVerifyType());
        verify.setVerifyId(verifyInfo.getVerifyId() == null ? -1L : verifyInfo.getVerifyId());
        VerifyStatusEnum status = VerifyStatusEnum.getByCode(verifyInfo.getStatus());
        if (status != null) {
            verify.setStatus(status);
        } else {
            verify.setStatus(VerifyStatusEnum.UNAUTHENTICATED);
        }
        verify.setChannel(verifyInfo.getChannel());

        return verify;
    }
    public static Verify createVerify(String entity, String memberId, Integer verifyType) {
        Verify verify = new Verify();
        verify.setImgPath("");
        verify.setMemberId(memberId);
        verify.setVerifiedTime(new Date());
        verify.setVerifyEntity(replaceHrySign(entity));
        verify.setVerifyType(verifyType);
        verify.setVerifyId(-1L);
        verify.setStatus(VerifyStatusEnum.AUTHENTICATED);  
        
        return verify;
    }

    /**
     * 认证数据库对象转换成认证领域对象
     * @param vDO 认证数据库对象
     * @return 认证领域对象
     */
    public static Verify convertToVerify(MemberTrVerifyEntity vDO,MemberTrVerifyRef memberTrVerifyRef ) {
        Verify verify = new Verify();
        verify.setExpireTime(memberTrVerifyRef.getExpireTime());
        verify.setImgPath(memberTrVerifyRef.getVerifyImgPath());
        verify.setMemberId(memberTrVerifyRef.getMemberId());
        verify.setVerifiedTime(memberTrVerifyRef.getVerifiedTime());
        verify.setVerifyEntity(vDO.getVerifyEntity());
        verify.setVerifyType(vDO.getVerifyType());
        verify.setMemo(memberTrVerifyRef.getMemo());
        verify.setVerifyId(memberTrVerifyRef.getVerifyId());
        verify.setVerifySubType(vDO.getVerifySubType());
        VerifyStatusEnum status = VerifyStatusEnum.getByCode(vDO.getStatus());
        if (status != null) {
            verify.setStatus(status);
        } else {
            verify.setStatus(VerifyStatusEnum.UNAUTHENTICATED);
        }
        verify.setChannel(vDO.getChannel());

        return verify;
    }

    /**
     * 认证领域对象转换成认证响应对象
     * @param verify 认证领域对象
     * @return 认证响应对象
     */
    public static VerifyInfo convertToVerifyInfoResponse(Verify verify) {
        VerifyInfo info = new VerifyInfo();
        info.setExpireTime(verify.getExpireTime());
        info.setImgPath(verify.getImgPath());
        info.setMemberId(verify.getMemberId());
        info.setVerifiedTime(verify.getVerifiedTime());
        info.setVerifyEntity(verify.getVerifyEntity());
        info.setVerifyType(verify.getVerifyType());
        VerifyStatusEnum status = verify.getStatus();
        if (status != null) {
            info.setStatus(status.getCode());
        } else {
            info.setStatus(VerifyStatusEnum.UNAUTHENTICATED.getCode());
        }
        info.setChannel(verify.getChannel());
        info.setVerifyId(verify.getVerifyId());
        if(StringUtils.isNotBlank(verify.getVerifyEntityTicket())) {
            JSONObject json = new JSONObject();
            json.put(MaConstant.IDENTITY_TICKET, verify.getVerifyEntityTicket());
            info.setExtention(json.toJSONString());
        }
        return info;
        
    }
    
    public static List<VerifyInfo> convertToVerifyInfoList(List<Verify> verifyList) {
    	if (verifyList == null)
    		return null;
    	
    	List<VerifyInfo> verifyInfoList = new ArrayList<VerifyInfo>();
    	for (Verify v : verifyList) {
    		VerifyInfo vi = convertToVerifyInfoResponse(v);
    		verifyInfoList.add(vi);
    	}
    	
    	return verifyInfoList;
    }
    
    /**
     * 认证查询请求对象转换成 查询对象
     * @param request
     * @return
     */
    public static VerifyQuery convertReqToQuery(VerifyQueryRequest request){
        VerifyQuery query = new VerifyQuery();
        query.setMemberId(request.getMemberId());
        query.setVerifyType(request.getVerifyType());
        return query;
    }
    
    /**
     * 认证查询请求对象转换成 查询对象
     * @param request
     * @return
     */
    public static VerifyQuery convertReqToQuery(VerifyBindQueryRequest request){
        VerifyQuery query = new VerifyQuery();
        query.setVerifyEntity(request.getVerifyEntity());
        query.setVerifyType(request.getVerifyType());
        return query;
    }
	public static List<Verify> convertVerify(CoopMember coopMember) {
		List<Verify> list = new ArrayList<Verify>();
		String entity = coopMember.getLoginName();
//		if (StringUtils.isNotBlank(entity)) {
//			Verify verify = createVerifyInfo(entity, VerifyTypeEnum.LOGIN_NAME.getCode(), VerifyStatusEnum.AUTHENTICATED);
//			list.add(verify);
//		}
		entity = coopMember.getMobile();
		if (StringUtils.isNotBlank(entity)) {
			Verify verify = createVerifyInfo(entity, VerifyTypeEnum.CELL_PHONE.getCode(), VerifyStatusEnum.AUTHENTICATED);
			list.add(verify);
		}
		entity = coopMember.getEmail();
		if (StringUtils.isNotBlank(entity)) {
			Verify verify = createVerifyInfo(entity, VerifyTypeEnum.EMAIL.getCode(), VerifyStatusEnum.AUTHENTICATED);
			list.add(verify);
		}
		entity = coopMember.getIdCard();
		if (StringUtils.isNotBlank(entity)) {
			Verify verify = createVerifyInfo(entity, VerifyTypeEnum.ID_CARD.getCode(), VerifyStatusEnum.AUTHENTICATED);
			list.add(verify);
		}
		return list;
	}
	public static Verify createVerifyInfo(String verifyEntity, Integer verifyType, VerifyStatusEnum verifyStatusEnum) {
		Verify verifyInfo = new Verify();
		if (VerifyTypeEnum.ID_CARD.getCode() != verifyType) {
			verifyEntity 	  = StringUtils.lowerCase(StringUtils.trim(verifyEntity));
		}
		verifyInfo.setVerifyType(verifyType);
    	verifyInfo.setVerifyEntity(verifyEntity);
    	verifyInfo.setStatus(verifyStatusEnum);
    	verifyInfo.setVerifiedTime(new Date());
    	
    	return verifyInfo;
	}
	
	public static String replaceHrySign(String entity) {
		return entity == null ? "" : entity.replaceAll(MaConstant.HRY_SIGN+".*", "");
	}
}

package com.yiran.member.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTmContactMapper;
import com.yiran.member.request.ContactChangeRequest;
import com.yiran.member.request.ContactQuery;
import com.yiran.member.request.ContactRequest;
import com.yiran.member.request.QueryContactRequest;
import com.yiran.member.response.ContactResponse;
import com.yiran.member.response.QueryContactsResponse;
import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmContact;
import com.yiran.member.enums.ContactStatusEnum;
import com.yiran.member.enums.ContactTypeEnum;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.service.IMemberTmContactService;
import com.yiran.member.utils.ContactDomainUtil;
import com.yiran.member.utils.ResponseUtil;
import com.yiran.member.validator.ContactFacadeValidator;
import com.yiran.member.validator.MemberValidator;
import com.yiran.common.support.Convert;

/**
 * 联系人 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTmContactServiceImpl implements IMemberTmContactService 
{
	private Logger         logger = LoggerFactory.getLogger(MemberTmContactServiceImpl.class);
	@Autowired
	private MemberTmContactMapper memberTmContactMapper;
	@Autowired
	private MemberValidator   memberValidator;
	/**
     * 查询联系人信息
     * 
     * @param contactId 联系人ID
     * @return 联系人信息
     */
    @Override
	public MemberTmContact selectMemberTmContactById(Integer contactId)
	{
	    return memberTmContactMapper.selectMemberTmContactById(contactId);
	}
	
	/**
     * 查询联系人列表
     * 
     * @param memberTmContact 联系人信息
     * @return 联系人集合
     */
	@Override
	public List<MemberTmContact> selectMemberTmContactList(MemberTmContact memberTmContact)
	{
	    return memberTmContactMapper.selectMemberTmContactList(memberTmContact);
	}
	
    /**
     * 新增联系人
     * 
     * @param memberTmContact 联系人信息
     * @return 结果
     */
	@Override
	public int insertMemberTmContact(MemberTmContact memberTmContact)
	{
	    return memberTmContactMapper.insertMemberTmContact(memberTmContact);
	}
	
	/**
     * 修改联系人
     * 
     * @param memberTmContact 联系人信息
     * @return 结果
     */
	@Override
	public int updateMemberTmContact(MemberTmContact memberTmContact)
	{
	    return memberTmContactMapper.updateMemberTmContact(memberTmContact);
	}

	/**
     * 删除联系人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmContactByIds(String ids)
	{
		return memberTmContactMapper.deleteMemberTmContactByIds(Convert.toStrArray(ids));
	}

	@Override
	public ContactResponse createContact(ContactRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]创建联系信息请求:request={}", request);
        }
        ContactResponse response = new ContactResponse();
        try {
            //验证请求参数
            ContactFacadeValidator.validator(request);
            String contactId = createContact(request.getContactInfo());
            response.setContactId(contactId);
            ResponseUtil.setSuccessResponse(response);

            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]创建联系信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "创建联系信息");
        }
        return response;
	}

	private String createContact(MemberTmContact contact) throws MaBizException {
		if (ContactTypeEnum.MEMBER.getCode().intValue() == contact.getContactType()) {
            memberValidator.validateMemberExist(contact.getObjectId());
        } /*else if (ContactTypeEnum.OPERATOR.getCode().intValue() == contact.getContactType()) {
            operatorValidator.validateOperatorIdExists(contact.getObjectId());
        }*/
        int id = memberTmContactMapper.insertMemberTmContact(contact);
        if (id == 0) {
            throw new MaBizException(ResponseCode.CONTACT_SAVE_FAIL, "新增联系信息失败");
        }
        return String.valueOf(id);
	}

	@Override
	public Response updateContact(ContactChangeRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]修改联系信息请求:request={}", request);
        }
        Response response = new Response();
        try {
            //验证请求参数
            ContactFacadeValidator.validator(request);
            updateContact(request.getContactInfo());
            ResponseUtil.setSuccessResponse(response);
            
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]修改联系信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "修改联系信息");
        }
        return response;
	}

	private void updateContact(MemberTmContact contact) throws MaBizException {
		MemberTmContact bean = memberTmContactMapper.selectMemberTmContactById(contact.getContactId());
        if (bean == null || ContactStatusEnum.INVALID.getCode().equals(bean.getStatus())) {
            throw new MaBizException(ResponseCode.CONTACT_NOT_EXIST);
        }
        contact.setObjectId(bean.getObjectId());
        int count = memberTmContactMapper.updateMemberTmContact(contact);
        if (count == 0) {
            throw new MaBizException(ResponseCode.CONTACT_NOT_EXIST, "联系信息已经被删除");
        }
	}

	@Override
	public QueryContactsResponse queryContact(QueryContactRequest request) {
		if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]查询联系信息请求:request={}", request);
        }
        QueryContactsResponse response = new QueryContactsResponse();
        try {
            //验证请求参数
            ContactFacadeValidator.validator(request);
            List<MemberTmContact> rest =query(ContactDomainUtil.convertReqToQuery(request));
            if (rest == null || rest.isEmpty()) {
                response.setResponseCode(ResponseCode.NO_QUERY_RESULT.getCode());
                response.setResponseMessage(ResponseCode.NO_QUERY_RESULT.getMessage());
            } else {
                List<MemberTmContact> contacts = new ArrayList<MemberTmContact>(rest.size());
                for (MemberTmContact item : rest) {
                    contacts.add(item);
                }
                response.setContactInfo(contacts);
                ResponseUtil.setSuccessResponse(response);
            }
            
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]查询联系信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "查询联系信息");
        }
        return response;
	}

	private List<MemberTmContact> query(ContactQuery query) throws MaBizException {
		if (query.getContactType() != null) {
            if (ContactTypeEnum.MEMBER == query.getContactType()) {
                memberValidator.validateMemberExist(query.getObjectId());
            }
        }
        return memberTmContactMapper.queryMemberTmContact(query.getObjectId(), query.getContactType().getCode());
	}

	@Override
	public Response deleteContact(String contactId) {
	 if (logger.isInfoEnabled()) {
            logger.info("[APP->MA_1]删除联系信息请求:contactId={}", contactId);
        }
        Response response = new Response();
        try {
            //验证请求参数
            ContactFacadeValidator.checkContactId(contactId);
            memberTmContactMapper.deleteMemberTmContactById(Integer.parseInt(contactId));
            ResponseUtil.setSuccessResponse(response);
            
            if (logger.isInfoEnabled()) {
                logger.info("[APP<-MA_1]删除联系信息结果:response={}", response);
            }
        } catch (Exception e) {
            ResponseUtil.fillResponse(response, e, "删除联系信息");
        }
        return response;
	}
	
}

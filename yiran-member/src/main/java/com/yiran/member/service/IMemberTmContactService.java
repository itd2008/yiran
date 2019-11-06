package com.yiran.member.service;

import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmContact;
import com.yiran.member.request.ContactChangeRequest;
import com.yiran.member.request.ContactRequest;
import com.yiran.member.request.QueryContactRequest;
import com.yiran.member.response.ContactResponse;
import com.yiran.member.response.QueryContactsResponse;

import java.util.List;

/**
 * 联系人 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTmContactService 
{
	/**
     * 查询联系人信息
     * 
     * @param contactId 联系人ID
     * @return 联系人信息
     */
	public MemberTmContact selectMemberTmContactById(Integer contactId);
	
	/**
     * 查询联系人列表
     * 
     * @param memberTmContact 联系人信息
     * @return 联系人集合
     */
	public List<MemberTmContact> selectMemberTmContactList(MemberTmContact memberTmContact);
	
	/**
     * 新增联系人
     * 
     * @param memberTmContact 联系人信息
     * @return 结果
     */
	public int insertMemberTmContact(MemberTmContact memberTmContact);
	
	/**
     * 修改联系人
     * 
     * @param memberTmContact 联系人信息
     * @return 结果
     */
	public int updateMemberTmContact(MemberTmContact memberTmContact);
		
	/**
     * 删除联系人信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmContactByIds(String ids);

	public ContactResponse createContact(ContactRequest request);

	public Response updateContact(ContactChangeRequest request);

	public QueryContactsResponse queryContact(QueryContactRequest request);

	public Response deleteContact(String contactId);

	
}

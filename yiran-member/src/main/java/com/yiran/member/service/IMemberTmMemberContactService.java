package com.yiran.member.service;

import com.yiran.member.domain.MemberTmMemberContact;
import java.util.List;

/**
 * 会员账户 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTmMemberContactService 
{
	/**
     * 查询会员账户信息
     * 
     * @param contactId 会员账户ID
     * @return 会员账户信息
     */
	public MemberTmMemberContact selectMemberTmMemberContactById(Integer contactId);
	
	/**
     * 查询会员账户列表
     * 
     * @param memberTmMemberContact 会员账户信息
     * @return 会员账户集合
     */
	public List<MemberTmMemberContact> selectMemberTmMemberContactList(MemberTmMemberContact memberTmMemberContact);
	
	/**
     * 新增会员账户
     * 
     * @param memberTmMemberContact 会员账户信息
     * @return 结果
     */
	public int insertMemberTmMemberContact(MemberTmMemberContact memberTmMemberContact);
	
	/**
     * 修改会员账户
     * 
     * @param memberTmMemberContact 会员账户信息
     * @return 结果
     */
	public int updateMemberTmMemberContact(MemberTmMemberContact memberTmMemberContact);
		
	/**
     * 删除会员账户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmMemberContactByIds(String ids);
	
}

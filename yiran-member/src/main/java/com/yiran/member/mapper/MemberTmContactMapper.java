package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTmContact;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 联系人 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTmContactMapper 
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
     * 删除联系人
     * 
     * @param contactId 联系人ID
     * @return 结果
     */
	public int deleteMemberTmContactById(Integer contactId);
	
	/**
     * 批量删除联系人
     * 
     * @param contactIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmContactByIds(String[] contactIds);

	public List<MemberTmContact> queryMemberTmContact(@Param("objectId")String objectId,@Param("contactType") Long contactType);
	
}
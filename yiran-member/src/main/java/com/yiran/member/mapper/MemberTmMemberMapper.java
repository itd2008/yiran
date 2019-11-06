package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTmMember;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 会员 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTmMemberMapper 
{
	/**
     * 查询会员信息
     * 
     * @param memberId 会员ID
     * @return 会员信息
     */
	public MemberTmMember selectMemberTmMemberById(String memberId);
	
	/**
     * 查询会员列表
     * 
     * @param memberTmMember 会员信息
     * @return 会员集合
     */
	public List<MemberTmMember> selectMemberTmMemberList(MemberTmMember memberTmMember);
	
	/**
     * 新增会员
     * 
     * @param memberTmMember 会员信息
     * @return 结果
     */
	public int insertMemberTmMember(MemberTmMember memberTmMember);
	
	/**
     * 修改会员
     * 
     * @param memberTmMember 会员信息
     * @return 结果
     */
	public int updateMemberTmMember(MemberTmMember memberTmMember);
	
	/**
     * 删除会员
     * 
     * @param memberId 会员ID
     * @return 结果
     */
	public int deleteMemberTmMemberById(String memberId);
	
	/**
     * 批量删除会员
     * 
     * @param memberIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmMemberByIds(String[] memberIds);

	public int updateActiveTime(@Param("status") int status,  @Param("memberId") String memberId);

	public int updateMember(MemberTmMember tmMemberDO);

	/**
	 * 锁定会员
	 * @param memberId
	 * @return
	 */
	public int getMemberByIdForUpdate(String memberId);

	public int updateLockStatus(MemberTmMember member);

	/**
	 * 会员锁定
	 * @param memberId
	 */
	public void lockMemberById(String memberId);

	
}
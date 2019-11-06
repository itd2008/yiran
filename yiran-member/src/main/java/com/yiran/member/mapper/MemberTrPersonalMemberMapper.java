package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTrPersonalMember;
import java.util.List;	

/**
 * 个人会员 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTrPersonalMemberMapper 
{
	/**
     * 查询个人会员信息
     * 
     * @param memberId 个人会员ID
     * @return 个人会员信息
     */
	public MemberTrPersonalMember selectMemberTrPersonalMemberById(String memberId);
	
	/**
     * 查询个人会员列表
     * 
     * @param memberTrPersonalMember 个人会员信息
     * @return 个人会员集合
     */
	public List<MemberTrPersonalMember> selectMemberTrPersonalMemberList(MemberTrPersonalMember memberTrPersonalMember);
	
	/**
     * 新增个人会员
     * 
     * @param memberTrPersonalMember 个人会员信息
     * @return 结果
     */
	public int insertMemberTrPersonalMember(MemberTrPersonalMember memberTrPersonalMember);
	
	/**
     * 修改个人会员
     * 
     * @param memberTrPersonalMember 个人会员信息
     * @return 结果
     */
	public int updateMemberTrPersonalMember(MemberTrPersonalMember memberTrPersonalMember);
	
	/**
     * 删除个人会员
     * 
     * @param memberId 个人会员ID
     * @return 结果
     */
	public int deleteMemberTrPersonalMemberById(String memberId);
	
	/**
     * 批量删除个人会员
     * 
     * @param memberIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrPersonalMemberByIds(String[] memberIds);
	
}
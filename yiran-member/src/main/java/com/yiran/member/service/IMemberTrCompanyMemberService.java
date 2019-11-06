package com.yiran.member.service;

import com.yiran.member.domain.MemberTrCompanyMember;
import java.util.List;

/**
 * 企业会员 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTrCompanyMemberService 
{
	/**
     * 查询企业会员信息
     * 
     * @param memberId 企业会员ID
     * @return 企业会员信息
     */
	public MemberTrCompanyMember selectMemberTrCompanyMemberById(String memberId);
	
	/**
     * 查询企业会员列表
     * 
     * @param memberTrCompanyMember 企业会员信息
     * @return 企业会员集合
     */
	public List<MemberTrCompanyMember> selectMemberTrCompanyMemberList(MemberTrCompanyMember memberTrCompanyMember);
	
	/**
     * 新增企业会员
     * 
     * @param memberTrCompanyMember 企业会员信息
     * @return 结果
     */
	public int insertMemberTrCompanyMember(MemberTrCompanyMember memberTrCompanyMember);
	
	/**
     * 修改企业会员
     * 
     * @param memberTrCompanyMember 企业会员信息
     * @return 结果
     */
	public int updateMemberTrCompanyMember(MemberTrCompanyMember memberTrCompanyMember);
		
	/**
     * 删除企业会员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrCompanyMemberByIds(String ids);
	
}

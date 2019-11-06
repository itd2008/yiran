package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTmMemberIdentity;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 会员标识 数据层
 * 
 * @author yiran
 * @date 2019-03-31
 */
public interface MemberTmMemberIdentityMapper 
{
	/**
     * 查询会员标识信息
     * 
     * @param memberId 会员标识ID
     * @return 会员标识信息
     */
	public MemberTmMemberIdentity selectMemberTmMemberIdentityById(String memberId);
	
	/**
     * 查询会员标识列表
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 会员标识集合
     */
	public List<MemberTmMemberIdentity> selectMemberTmMemberIdentityList(MemberTmMemberIdentity memberTmMemberIdentity);
	
	/**
     * 新增会员标识
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 结果
     */
	public int insertMemberTmMemberIdentity(MemberTmMemberIdentity memberTmMemberIdentity);
	
	/**
     * 修改会员标识
     * 
     * @param memberTmMemberIdentity 会员标识信息
     * @return 结果
     */
	public int updateMemberTmMemberIdentity(MemberTmMemberIdentity memberTmMemberIdentity);
	
	/**
     * 删除会员标识
     * 
     * @param memberId 会员标识ID
     * @return 结果
     */
	public int deleteMemberTmMemberIdentityById(String memberId);
	
	/**
     * 批量删除会员标识
     * 
     * @param memberIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmMemberIdentityByIds(String[] memberIds);

	/**
	 * 根据会员标识，平台类型查询会员编号
	 * @param memberIdentity
	 * @param platformType
	 * @return
	 */
	public String queryMemberId(@Param("memberIdentity") String memberIdentity,  @Param("platformType") int platformType);
	
}
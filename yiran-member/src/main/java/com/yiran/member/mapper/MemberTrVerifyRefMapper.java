package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTrVerifyRef;
import java.util.List;	

/**
 * 认证关系 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTrVerifyRefMapper 
{
	/**
     * 查询认证关系信息
     * 
     * @param verifyId 认证关系ID
     * @return 认证关系信息
     */
	public MemberTrVerifyRef selectMemberTrVerifyRefById(Integer verifyId);
	
	/**
     * 查询认证关系列表
     * 
     * @param memberTrVerifyRef 认证关系信息
     * @return 认证关系集合
     */
	public List<MemberTrVerifyRef> selectMemberTrVerifyRefList(MemberTrVerifyRef memberTrVerifyRef);
	
	/**
     * 新增认证关系
     * 
     * @param memberTrVerifyRef 认证关系信息
     * @return 结果
     */
	public int insertMemberTrVerifyRef(MemberTrVerifyRef memberTrVerifyRef);
	
	/**
     * 修改认证关系
     * 
     * @param memberTrVerifyRef 认证关系信息
     * @return 结果
     */
	public int updateMemberTrVerifyRef(MemberTrVerifyRef memberTrVerifyRef);
	
	/**
     * 删除认证关系
     * 
     * @param verifyId 认证关系ID
     * @return 结果
     */
	public int deleteMemberTrVerifyRefById(Integer verifyId);
	
	/**
     * 批量删除认证关系
     * 
     * @param verifyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrVerifyRefByIds(String[] verifyIds);

	/**
	 * 根据会员ID查询认证关系对象
	 * @param memberId
	 * @return
	 */
	public List<MemberTrVerifyRef> selectMemberTrVerifyRefByMemberId(String memberId);

	public MemberTrVerifyRef selectMemberTrVerifyRefByverifyEntityId(Integer verifyEntityId);
	
}
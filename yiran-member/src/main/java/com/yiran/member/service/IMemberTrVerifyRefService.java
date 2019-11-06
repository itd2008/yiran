package com.yiran.member.service;

import com.yiran.member.domain.MemberTrVerifyRef;
import java.util.List;

/**
 * 认证关系 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTrVerifyRefService 
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
     * 删除认证关系信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrVerifyRefByIds(String ids);

	/**
	 * 根据会员id获取认证关系
	 * @param memberId
	 * @return
	 */
	public MemberTrVerifyRef selectMemberTrVerifyRefByMemberId(String memberId);
	
}

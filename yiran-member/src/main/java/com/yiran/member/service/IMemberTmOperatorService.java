package com.yiran.member.service;

import com.yiran.member.domain.MemberTmOperator;

import java.util.List;

/**
 * 操作员 服务层
 * 
 * @author yiran
 * @date 2019-03-31
 */
public interface IMemberTmOperatorService 
{
	/**
     * 查询操作员信息
     * 
     * @param operatorId 操作员ID
     * @return 操作员信息
     */
	public MemberTmOperator selectMemberTmOperatorById(String operatorId);
	
	/**
     * 查询操作员列表
     * 
     * @param memberTmOperator 操作员信息
     * @return 操作员集合
     */
	public List<MemberTmOperator> selectMemberTmOperatorList(MemberTmOperator memberTmOperator);
	
	/**
     * 新增操作员
     * 
     * @param memberTmOperator 操作员信息
     * @return 结果
     */
	public int insertMemberTmOperator(MemberTmOperator memberTmOperator);
	
	/**
     * 修改操作员
     * 
     * @param memberTmOperator 操作员信息
     * @return 结果
     */
	public int updateMemberTmOperator(MemberTmOperator memberTmOperator);
		
	/**
     * 删除操作员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmOperatorByIds(String ids);

	/**
	 * 保存操作员operator
	 * @param operator
	 * @return
	 */
	public int store(MemberTmOperator operator);

	/**
	 * 根据会员ID查询操作员信息
	 * @param memberId
	 * @return
	 */
	public MemberTmOperator selectMemberTmOperatorByMemberId(String memberId);

	

	
}

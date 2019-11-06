package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTmOperator;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 操作员 数据层
 * 
 * @author yiran
 * @date 2019-03-31
 */
public interface MemberTmOperatorMapper 
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
     * 删除操作员
     * 
     * @param operatorId 操作员ID
     * @return 结果
     */
	public int deleteMemberTmOperatorById(String operatorId);
	
	/**
     * 批量删除操作员
     * 
     * @param operatorIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmOperatorByIds(String[] operatorIds);

	public MemberTmOperator selectMemberTmOperatorByMemberId(String memberId);

	//public MemberTmOperator queryOperatorIdByPwssaord(@Param("memberId")String memberId);

	
	
}
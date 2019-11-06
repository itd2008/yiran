package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTrPassword;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 会员支付密码 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTrPasswordMapper 
{
	/**
     * 查询会员支付密码信息
     * 
     * @param id 会员支付密码ID
     * @return 会员支付密码信息
     */
	public MemberTrPassword selectMemberTrPasswordById(Integer id);
	
	/**
     * 查询会员支付密码列表
     * 
     * @param memberTrPassword 会员支付密码信息
     * @return 会员支付密码集合
     */
	public List<MemberTrPassword> selectMemberTrPasswordList(MemberTrPassword memberTrPassword);
	
	/**
     * 新增会员支付密码
     * 
     * @param memberTrPassword 会员支付密码信息
     * @return 结果
     */
	public int insertMemberTrPassword(MemberTrPassword memberTrPassword);
	
	/**
     * 修改会员支付密码
     * 
     * @param memberTrPassword 会员支付密码信息
     * @return 结果
     */
	public int updateMemberTrPassword(MemberTrPassword memberTrPassword);
	
	/**
     * 删除会员支付密码
     * 
     * @param id 会员支付密码ID
     * @return 结果
     */
	public int deleteMemberTrPasswordById(Integer id);
	
	/**
     * 批量删除会员支付密码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrPasswordByIds(String[] ids);

	public int checkExistPassword(@Param("password")String password, @Param("operatorId")String operatorId);

	public int updateLoginPassword(@Param("password")String password, @Param("pwdStatus")Integer pwdStatus, @Param("operatorId")String operatorId);
	
}
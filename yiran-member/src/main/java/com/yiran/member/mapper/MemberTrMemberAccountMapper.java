package com.yiran.member.mapper;

import com.yiran.member.domain.AccountDomain;
import com.yiran.member.domain.MemberTrMemberAccount;
import com.yiran.member.enums.AccountCategoryEnum;

import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 会员账户 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTrMemberAccountMapper 
{
	/**
     * 查询会员账户信息
     * 
     * @param id 会员账户ID
     * @return 会员账户信息
     */
	public MemberTrMemberAccount selectMemberTrMemberAccountById(Integer id);
	
	/**
     * 查询会员账户列表
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 会员账户集合
     */
	public List<MemberTrMemberAccount> selectMemberTrMemberAccountList(MemberTrMemberAccount memberTrMemberAccount);
	
	/**
     * 新增会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	public int insertMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount);
	
	/**
     * 修改会员账户
     * 
     * @param memberTrMemberAccount 会员账户信息
     * @return 结果
     */
	public int updateMemberTrMemberAccount(MemberTrMemberAccount memberTrMemberAccount);
	
	/**
     * 删除会员账户
     * 
     * @param id 会员账户ID
     * @return 结果
     */
	public int deleteMemberTrMemberAccountById(Integer id);
	
	/**
     * 批量删除会员账户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrMemberAccountByIds(String[] ids);

	/**
	 * 查询会员账户关系
	 * @param memberId
	 * @param code
	 * @param accountTypes
	 * @return
	 */
	public List<MemberTrMemberAccount> queryByMemberAndTypeIds(@Param("memberId") String memberId, @Param("code")String code, @Param("accountTypes")List accountTypes);

	public int updateAccountId(@Param("accountId") String accountId, @Param("accountName") String accountName, @Param("memberId") String memberId);
	public List<MemberTrMemberAccount> queryAllByMemberAndTypeId(@Param("memberId") String memberId, @Param("typeId") String typeId, @Param("category") String category);

	public MemberTrMemberAccount getAccountByAccountId(@Param("accountId") String accountId, @Param("category") String category);


	public MemberTrMemberAccount getAccountById(String accountId);

	public List<MemberTrMemberAccount> getAccountsByMemberId(String memberId);

	public int changeDenyAccountStatus(MemberTrMemberAccount memberTrMemberAccount);

	
	
}
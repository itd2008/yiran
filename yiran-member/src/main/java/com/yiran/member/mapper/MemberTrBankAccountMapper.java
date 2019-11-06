package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTrBankAccount;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 个人银行卡 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTrBankAccountMapper 
{
	/**
     * 查询个人银行卡信息
     * 
     * @param id 个人银行卡ID
     * @return 个人银行卡信息
     */
	public MemberTrBankAccount selectMemberTrBankAccountById(Integer id);
	
	/**
     * 查询个人银行卡列表
     * 
     * @param memberTrBankAccount 个人银行卡信息
     * @return 个人银行卡集合
     */
	public List<MemberTrBankAccount> selectMemberTrBankAccountList(MemberTrBankAccount memberTrBankAccount);
	
	/**
     * 新增个人银行卡
     * 
     * @param memberTrBankAccount 个人银行卡信息
     * @return 结果
     */
	public int insertMemberTrBankAccount(MemberTrBankAccount memberTrBankAccount);
	
	/**
     * 修改个人银行卡
     * 
     * @param memberTrBankAccount 个人银行卡信息
     * @return 结果
     */
	public int updateMemberTrBankAccount(MemberTrBankAccount memberTrBankAccount);
	
	/**
     * 删除个人银行卡
     * 
     * @param id 个人银行卡ID
     * @return 结果
     */
	public int deleteMemberTrBankAccountById(Integer id);
	
	/**
     * 批量删除个人银行卡
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrBankAccountByIds(String[] ids);

	public List<MemberTrBankAccount> selectMemberTrBankAccountByMemberId(String memberId);

	public MemberTrBankAccount queryBankAccountByBankcardId(String bankAccountNo);

	/**
	 * 如果是用户唯一卡则先失效原卡
	 * @param memberId
	 * @param payAttribute
	 */
	public void disabledAllBankAccount(@Param("memberId")String memberId, @Param("payAttribute")String payAttribute);

	/**
	 * 根据协议号，内部协议号，渠道编号查询
	 * @param bankAccount
	 * @return
	 */
	public List<MemberTrBankAccount> queryBySignNo(MemberTrBankAccount bankAccount);

	/**
	 * 根据协议号，渠道编号查询
	 * @param bankAccount
	 * @return
	 */
	public List<MemberTrBankAccount> queryBySign(MemberTrBankAccount bankAccount);

	/**
	 * 银行卡失效
	 * @param bnkAccount
	 */
	public void updateStatus(MemberTrBankAccount bnkAccount);

	public void disabledBankAccount(MemberTrBankAccount selectMemberTrBankAccountById);
	
}
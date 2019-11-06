package com.yiran.member.service;

import com.yiran.member.domain.MemberTrBankAccount;
import com.yiran.member.request.BankAccInfoRequest;
import com.yiran.member.request.BankAccountRequest;
import com.yiran.member.response.BankAccInfoResponse;
import com.yiran.member.response.BankAccountInfoResponse;
import com.yiran.member.response.BankAccountResponse;

import java.util.List;

/**
 * 个人银行卡 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTrBankAccountService 
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
     * 删除个人银行卡信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrBankAccountByIds(String ids);

	/**
	 * 查询会员账户关系信息
	 * @param request
	 * @return
	 */
	public BankAccountResponse queryBankAccount(BankAccountRequest request);

	/**
	 * 查询银行卡详细信息
	 * @param bankId
	 * @return
	 */
	public BankAccountInfoResponse queryBankAccountDetail(String bankId);

	/**
	 * 绑定会员银行卡信息
	 * @param request
	 * @return
	 */
	public BankAccInfoResponse addBankAccount(BankAccInfoRequest request);

	/**
	 * 修改会员银行卡信息
	 * @param request
	 * @return
	 */
	public BankAccInfoResponse updateBankAccount(BankAccInfoRequest request);
	
}

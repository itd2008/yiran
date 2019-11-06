package com.yiran.member.service;

import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.domain.MemberTrPassword;
import com.yiran.member.request.LoginPwdRequest;
import com.yiran.member.request.OperatorLoginPwdByIdRequest;
import com.yiran.member.request.OperatorLoginPwdLockRequest;
import com.yiran.member.request.OperatorLoginPwdRequest;
import com.yiran.member.request.PersonalLoginPwdRequest;
import com.yiran.member.response.LoginPwdResponse;

import java.util.List;

/**
 * 会员支付密码 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTrPasswordService 
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
     * 删除会员支付密码信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrPasswordByIds(String ids);

	public int store(MemberTmOperator operator);

	/**
	 * 设置登录密码
	 * @param request
	 * @return
	 */
	public Response setLoginPwd(LoginPwdRequest request);

	/**
	 * 验证操作员登陆密码，本接口只适用企业会员，个人商户
	 * @param request
	 * @return
	 */
	public LoginPwdResponse checkOperatorLoginPwd(OperatorLoginPwdRequest request);

	/**
	 * 验证操作员登陆密码，本接口只适用企业会员，个人商户
	 * @param request
	 * @return
	 */
	public LoginPwdResponse checkOperatorLoginPwdById(OperatorLoginPwdByIdRequest request);

	/**
	 * 验证个人会员登陆密码
	 * @param request
	 * @return
	 */
	public LoginPwdResponse checkPersonalLoginPwd(PersonalLoginPwdRequest request);

	
}

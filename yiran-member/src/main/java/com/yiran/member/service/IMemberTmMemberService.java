package com.yiran.member.service;

import com.yiran.member.base.Response;
import com.yiran.member.domain.MemberTmMember;
import com.yiran.member.request.ActivatePersonalRequest;
import com.yiran.member.request.CreateMemberInfoRequest;
import com.yiran.member.request.IntegratedCompanyRequest;
import com.yiran.member.request.IntegratedPersonalRequest;
import com.yiran.member.request.MemberIntegratedIdRequest;
import com.yiran.member.request.MemberIntegratedRequest;
import com.yiran.member.request.PersonalMemberInfoRequest;
import com.yiran.member.request.UpdateMemberLockStatusRequest;
import com.yiran.member.response.ActivatePersonalResponse;
import com.yiran.member.response.CreateMemberInfoResponse;
import com.yiran.member.response.IntegratedCompanyResponse;
import com.yiran.member.response.IntegratedPersonalResponse;
import com.yiran.member.response.MemberIntegratedResponse;

import java.util.List;

/**
 * 会员 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTmMemberService 
{
	/**
     * 查询会员信息
     * 
     * @param memberId 会员ID
     * @return 会员信息
     */
	public MemberTmMember selectMemberTmMemberById(String memberId);
	
	/**
     * 查询会员列表
     * 
     * @param memberTmMember 会员信息
     * @return 会员集合
     */
	public List<MemberTmMember> selectMemberTmMemberList(MemberTmMember memberTmMember);
	
	/**
     * 新增会员
     * 
     * @param memberTmMember 会员信息
     * @return 结果
     */
	public int insertMemberTmMember(MemberTmMember memberTmMember);
	
	/**
     * 修改会员
     * 
     * @param memberTmMember 会员信息
     * @return 结果
     */
	public int updateMemberTmMember(MemberTmMember memberTmMember);
		
	/**
     * 删除会员信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmMemberByIds(String ids);

	/**
	 * 创建会员
	 * @param request
	 * @return
	 */
	public CreateMemberInfoResponse createMemberInfo(CreateMemberInfoRequest request);

	/**
	 * 根据会员标识和平台类型查询会员综合信息
	 * @param request
	 * @return
	 */
	public MemberIntegratedResponse queryMemberIntegratedInfo(MemberIntegratedRequest request);

	/**
	 * 根据会员编号查询会员综合信息
	 * @param request
	 * @return
	 */
	public MemberIntegratedResponse queryMemberIntegratedInfoById(MemberIntegratedIdRequest request);

	/**
	 * 个人会员集成创建
	 * @param request
	 * @return
	 */
	public IntegratedPersonalResponse createIntegratedPersonalMember(IntegratedPersonalRequest request);

	/**
	 * 设置个人会员信息
	 * @param request
	 * @return
	 */
	public Response setPersonalMemberInfo(PersonalMemberInfoRequest request);

	/**
	 * 激活个人会员
	 * @param request
	 * @return
	 */
	public ActivatePersonalResponse activatePersonalMemberInfo(ActivatePersonalRequest request);

	/**
	 * 更新会员锁定状态
	 * @param request
	 * @return
	 */
	public Response updateMemberLockStatus(UpdateMemberLockStatusRequest request);

	/**
	 * 集成创建企业会员
	 * @param request
	 * @return
	 */
	public IntegratedCompanyResponse createIntegratedCompanyMember(IntegratedCompanyRequest request);
	
	
}

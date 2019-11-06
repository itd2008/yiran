package com.yiran.member.service;

import com.yiran.member.domain.MemberTrVerifyEntity;
import com.yiran.member.domain.Verify;

import java.util.List;

/**
 * 认证实体 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTrVerifyEntityService 
{
	/**
     * 查询认证实体信息
     * 
     * @param verifyEntityId 认证实体ID
     * @return 认证实体信息
     */
	public MemberTrVerifyEntity selectMemberTrVerifyEntityById(Integer verifyEntityId);
	
	/**
     * 查询认证实体列表
     * 
     * @param memberTrVerifyEntity 认证实体信息
     * @return 认证实体集合
     */
	public List<MemberTrVerifyEntity> selectMemberTrVerifyEntityList(MemberTrVerifyEntity memberTrVerifyEntity);
	
	/**
     * 新增认证实体
     * 
     * @param memberTrVerifyEntity 认证实体信息
     * @return 结果
     */
	public int insertMemberTrVerifyEntity(MemberTrVerifyEntity memberTrVerifyEntity);
	
	/**
     * 修改认证实体
     * 
     * @param memberTrVerifyEntity 认证实体信息
     * @return 结果
     */
	public int updateMemberTrVerifyEntity(MemberTrVerifyEntity memberTrVerifyEntity);
		
	/**
     * 删除认证实体信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrVerifyEntityByIds(String ids);

	/**
	 * 查询会员的认证信息
	 * @param memberId
	 * @param object
	 * @return
	 */
	public List<MemberTrVerifyEntity> queryByMember(String memberId, Integer verifyType);

	/**
	 * 添加认证信息，集成创建个人会员时调用。数据已经作加密处理
	 * @param verifys
	 * @param memberId
	 */
	public void addVerifys(List<MemberTrVerifyEntity> verifys, String memberId);

	public MemberTrVerifyEntity selectMemberTrVerifyEntityById(Integer verifyEntityId, Integer verifyType);
	
}

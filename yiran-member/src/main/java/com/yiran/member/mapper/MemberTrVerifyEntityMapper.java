package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTrVerifyEntity;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 认证实体 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTrVerifyEntityMapper 
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
     * 删除认证实体
     * 
     * @param verifyEntityId 认证实体ID
     * @return 结果
     */
	public int deleteMemberTrVerifyEntityById(Integer verifyEntityId);
	
	/**
     * 批量删除认证实体
     * 
     * @param verifyEntityIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTrVerifyEntityByIds(String[] verifyEntityIds);

	/**
	 * 根据verifyEntityId 和 verifyType查询
	 * @param verifyEntityId
	 * @param verifyType
	 * @return
	 */
	public List<MemberTrVerifyEntity> queryByMemberAndVerifyType(@Param("verifyEntityId") Integer verifyEntityId, @Param("verifyType")  Integer verifyType);

	public MemberTrVerifyEntity selectMemberTrVerifyEntityByVerifyEntity(String verifyEntity);


}
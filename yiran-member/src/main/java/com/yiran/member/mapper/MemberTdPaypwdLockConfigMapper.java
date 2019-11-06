package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTdPaypwdLockConfig;
import java.util.List;	

/**
 * 支付密码锁定配置 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTdPaypwdLockConfigMapper 
{
	/**
     * 查询支付密码锁定配置信息
     * 
     * @param id 支付密码锁定配置ID
     * @return 支付密码锁定配置信息
     */
	public MemberTdPaypwdLockConfig selectMemberTdPaypwdLockConfigById(Integer id);
	/**
	 * 根据锁策略查询
	 * @param lockStrategy
	 * @return
	 */
	public MemberTdPaypwdLockConfig selectMemberTdPaypwdLockConfigBylockStrategy(String lockStrategy);
	
	/**
     * 查询支付密码锁定配置列表
     * 
     * @param memberTdPaypwdLockConfig 支付密码锁定配置信息
     * @return 支付密码锁定配置集合
     */
	public List<MemberTdPaypwdLockConfig> selectMemberTdPaypwdLockConfigList(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig);
	
	/**
     * 新增支付密码锁定配置
     * 
     * @param memberTdPaypwdLockConfig 支付密码锁定配置信息
     * @return 结果
     */
	public int insertMemberTdPaypwdLockConfig(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig);
	
	/**
     * 修改支付密码锁定配置
     * 
     * @param memberTdPaypwdLockConfig 支付密码锁定配置信息
     * @return 结果
     */
	public int updateMemberTdPaypwdLockConfig(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig);
	
	/**
     * 删除支付密码锁定配置
     * 
     * @param id 支付密码锁定配置ID
     * @return 结果
     */
	public int deleteMemberTdPaypwdLockConfigById(Integer id);
	
	/**
     * 批量删除支付密码锁定配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTdPaypwdLockConfigByIds(String[] ids);
	
}
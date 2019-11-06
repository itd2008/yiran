package com.yiran.member.service;

import com.yiran.member.domain.MemberTdAccountConfig;
import java.util.List;

/**
 * 账户配置 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTdAccountConfigService 
{
	/**
     * 查询账户配置信息
     * 
     * @param id 账户配置ID
     * @return 账户配置信息
     */
	public MemberTdAccountConfig selectMemberTdAccountConfigById(Integer id);
	
	/**
     * 查询账户配置列表
     * 
     * @param memberTdAccountConfig 账户配置信息
     * @return 账户配置集合
     */
	public List<MemberTdAccountConfig> selectMemberTdAccountConfigList(MemberTdAccountConfig memberTdAccountConfig);
	
	/**
     * 新增账户配置
     * 
     * @param memberTdAccountConfig 账户配置信息
     * @return 结果
     */
	public int insertMemberTdAccountConfig(MemberTdAccountConfig memberTdAccountConfig);
	
	/**
     * 修改账户配置
     * 
     * @param memberTdAccountConfig 账户配置信息
     * @return 结果
     */
	public int updateMemberTdAccountConfig(MemberTdAccountConfig memberTdAccountConfig);
		
	/**
     * 删除账户配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTdAccountConfigByIds(String ids);
	
}

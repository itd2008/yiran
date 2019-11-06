package com.yiran.member.service;

import com.yiran.member.domain.MemberTdConfig;
import java.util.List;

/**
 * 配置 服务层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface IMemberTdConfigService 
{
	/**
     * 查询配置信息
     * 
     * @param id 配置ID
     * @return 配置信息
     */
	public MemberTdConfig selectMemberTdConfigById(Integer id);
	
	/**
     * 查询配置列表
     * 
     * @param memberTdConfig 配置信息
     * @return 配置集合
     */
	public List<MemberTdConfig> selectMemberTdConfigList(MemberTdConfig memberTdConfig);
	
	/**
     * 新增配置
     * 
     * @param memberTdConfig 配置信息
     * @return 结果
     */
	public int insertMemberTdConfig(MemberTdConfig memberTdConfig);
	
	/**
     * 修改配置
     * 
     * @param memberTdConfig 配置信息
     * @return 结果
     */
	public int updateMemberTdConfig(MemberTdConfig memberTdConfig);
		
	/**
     * 删除配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTdConfigByIds(String ids);
	
}

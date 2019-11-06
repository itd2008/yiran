package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTdConfig;
import java.util.List;	

/**
 * 配置 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTdConfigMapper 
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
     * 删除配置
     * 
     * @param id 配置ID
     * @return 结果
     */
	public int deleteMemberTdConfigById(Integer id);
	
	/**
     * 批量删除配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTdConfigByIds(String[] ids);
	
}
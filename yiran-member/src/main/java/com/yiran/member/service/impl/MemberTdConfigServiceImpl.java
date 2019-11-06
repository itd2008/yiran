package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTdConfigMapper;
import com.yiran.member.domain.MemberTdConfig;
import com.yiran.member.service.IMemberTdConfigService;
import com.yiran.common.support.Convert;

/**
 * 配置 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTdConfigServiceImpl implements IMemberTdConfigService 
{
	@Autowired
	private MemberTdConfigMapper memberTdConfigMapper;

	/**
     * 查询配置信息
     * 
     * @param id 配置ID
     * @return 配置信息
     */
    @Override
	public MemberTdConfig selectMemberTdConfigById(Integer id)
	{
	    return memberTdConfigMapper.selectMemberTdConfigById(id);
	}
	
	/**
     * 查询配置列表
     * 
     * @param memberTdConfig 配置信息
     * @return 配置集合
     */
	@Override
	public List<MemberTdConfig> selectMemberTdConfigList(MemberTdConfig memberTdConfig)
	{
	    return memberTdConfigMapper.selectMemberTdConfigList(memberTdConfig);
	}
	
    /**
     * 新增配置
     * 
     * @param memberTdConfig 配置信息
     * @return 结果
     */
	@Override
	public int insertMemberTdConfig(MemberTdConfig memberTdConfig)
	{
	    return memberTdConfigMapper.insertMemberTdConfig(memberTdConfig);
	}
	
	/**
     * 修改配置
     * 
     * @param memberTdConfig 配置信息
     * @return 结果
     */
	@Override
	public int updateMemberTdConfig(MemberTdConfig memberTdConfig)
	{
	    return memberTdConfigMapper.updateMemberTdConfig(memberTdConfig);
	}

	/**
     * 删除配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTdConfigByIds(String ids)
	{
		return memberTdConfigMapper.deleteMemberTdConfigByIds(Convert.toStrArray(ids));
	}
	
}

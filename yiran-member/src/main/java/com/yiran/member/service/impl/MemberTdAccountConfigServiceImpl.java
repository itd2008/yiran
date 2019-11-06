package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTdAccountConfigMapper;
import com.yiran.member.domain.MemberTdAccountConfig;
import com.yiran.member.service.IMemberTdAccountConfigService;
import com.yiran.common.support.Convert;

/**
 * 账户配置 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTdAccountConfigServiceImpl implements IMemberTdAccountConfigService 
{
	@Autowired
	private MemberTdAccountConfigMapper memberTdAccountConfigMapper;

	/**
     * 查询账户配置信息
     * 
     * @param id 账户配置ID
     * @return 账户配置信息
     */
    @Override
	public MemberTdAccountConfig selectMemberTdAccountConfigById(Integer id)
	{
	    return memberTdAccountConfigMapper.selectMemberTdAccountConfigById(id);
	}
	
	/**
     * 查询账户配置列表
     * 
     * @param memberTdAccountConfig 账户配置信息
     * @return 账户配置集合
     */
	@Override
	public List<MemberTdAccountConfig> selectMemberTdAccountConfigList(MemberTdAccountConfig memberTdAccountConfig)
	{
	    return memberTdAccountConfigMapper.selectMemberTdAccountConfigList(memberTdAccountConfig);
	}
	
    /**
     * 新增账户配置
     * 
     * @param memberTdAccountConfig 账户配置信息
     * @return 结果
     */
	@Override
	public int insertMemberTdAccountConfig(MemberTdAccountConfig memberTdAccountConfig)
	{
	    return memberTdAccountConfigMapper.insertMemberTdAccountConfig(memberTdAccountConfig);
	}
	
	/**
     * 修改账户配置
     * 
     * @param memberTdAccountConfig 账户配置信息
     * @return 结果
     */
	@Override
	public int updateMemberTdAccountConfig(MemberTdAccountConfig memberTdAccountConfig)
	{
	    return memberTdAccountConfigMapper.updateMemberTdAccountConfig(memberTdAccountConfig);
	}

	/**
     * 删除账户配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTdAccountConfigByIds(String ids)
	{
		return memberTdAccountConfigMapper.deleteMemberTdAccountConfigByIds(Convert.toStrArray(ids));
	}
	
}

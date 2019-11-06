package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTdPaypwdLockConfigMapper;
import com.yiran.member.domain.MemberTdPaypwdLockConfig;
import com.yiran.member.service.IMemberTdPaypwdLockConfigService;
import com.yiran.common.support.Convert;

/**
 * 支付密码锁定配置 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTdPaypwdLockConfigServiceImpl implements IMemberTdPaypwdLockConfigService 
{
	@Autowired
	private MemberTdPaypwdLockConfigMapper memberTdPaypwdLockConfigMapper;

	/**
     * 查询支付密码锁定配置信息
     * 
     * @param id 支付密码锁定配置ID
     * @return 支付密码锁定配置信息
     */
    @Override
	public MemberTdPaypwdLockConfig selectMemberTdPaypwdLockConfigById(Integer id)
	{
	    return memberTdPaypwdLockConfigMapper.selectMemberTdPaypwdLockConfigById(id);
	}
	
	/**
     * 查询支付密码锁定配置列表
     * 
     * @param memberTdPaypwdLockConfig 支付密码锁定配置信息
     * @return 支付密码锁定配置集合
     */
	@Override
	public List<MemberTdPaypwdLockConfig> selectMemberTdPaypwdLockConfigList(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig)
	{
	    return memberTdPaypwdLockConfigMapper.selectMemberTdPaypwdLockConfigList(memberTdPaypwdLockConfig);
	}
	
    /**
     * 新增支付密码锁定配置
     * 
     * @param memberTdPaypwdLockConfig 支付密码锁定配置信息
     * @return 结果
     */
	@Override
	public int insertMemberTdPaypwdLockConfig(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig)
	{
	    return memberTdPaypwdLockConfigMapper.insertMemberTdPaypwdLockConfig(memberTdPaypwdLockConfig);
	}
	
	/**
     * 修改支付密码锁定配置
     * 
     * @param memberTdPaypwdLockConfig 支付密码锁定配置信息
     * @return 结果
     */
	@Override
	public int updateMemberTdPaypwdLockConfig(MemberTdPaypwdLockConfig memberTdPaypwdLockConfig)
	{
	    return memberTdPaypwdLockConfigMapper.updateMemberTdPaypwdLockConfig(memberTdPaypwdLockConfig);
	}

	/**
     * 删除支付密码锁定配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTdPaypwdLockConfigByIds(String ids)
	{
		return memberTdPaypwdLockConfigMapper.deleteMemberTdPaypwdLockConfigByIds(Convert.toStrArray(ids));
	}
	
}

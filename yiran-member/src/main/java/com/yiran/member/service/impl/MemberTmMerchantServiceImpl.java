package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTmMerchantMapper;
import com.yiran.member.domain.MemberTmMerchant;
import com.yiran.member.service.IMemberTmMerchantService;
import com.yiran.common.support.Convert;

/**
 * 商户 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTmMerchantServiceImpl implements IMemberTmMerchantService 
{
	@Autowired
	private MemberTmMerchantMapper memberTmMerchantMapper;

	/**
     * 查询商户信息
     * 
     * @param merchantId 商户ID
     * @return 商户信息
     */
    @Override
	public MemberTmMerchant selectMemberTmMerchantById(String merchantId)
	{
	    return memberTmMerchantMapper.selectMemberTmMerchantById(merchantId);
	}
	
	/**
     * 查询商户列表
     * 
     * @param memberTmMerchant 商户信息
     * @return 商户集合
     */
	@Override
	public List<MemberTmMerchant> selectMemberTmMerchantList(MemberTmMerchant memberTmMerchant)
	{
	    return memberTmMerchantMapper.selectMemberTmMerchantList(memberTmMerchant);
	}
	
    /**
     * 新增商户
     * 
     * @param memberTmMerchant 商户信息
     * @return 结果
     */
	@Override
	public int insertMemberTmMerchant(MemberTmMerchant memberTmMerchant)
	{
	    return memberTmMerchantMapper.insertMemberTmMerchant(memberTmMerchant);
	}
	
	/**
     * 修改商户
     * 
     * @param memberTmMerchant 商户信息
     * @return 结果
     */
	@Override
	public int updateMemberTmMerchant(MemberTmMerchant memberTmMerchant)
	{
	    return memberTmMerchantMapper.updateMemberTmMerchant(memberTmMerchant);
	}

	/**
     * 删除商户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmMerchantByIds(String ids)
	{
		return memberTmMerchantMapper.deleteMemberTmMerchantByIds(Convert.toStrArray(ids));
	}
	
}

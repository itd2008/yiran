package com.yiran.member.mapper;

import com.yiran.member.domain.MemberTmMerchant;
import java.util.List;	

/**
 * 商户 数据层
 * 
 * @author yiran
 * @date 2019-03-30
 */
public interface MemberTmMerchantMapper 
{
	/**
     * 查询商户信息
     * 
     * @param merchantId 商户ID
     * @return 商户信息
     */
	public MemberTmMerchant selectMemberTmMerchantById(String merchantId);
	
	/**
     * 查询商户列表
     * 
     * @param memberTmMerchant 商户信息
     * @return 商户集合
     */
	public List<MemberTmMerchant> selectMemberTmMerchantList(MemberTmMerchant memberTmMerchant);
	
	/**
     * 新增商户
     * 
     * @param memberTmMerchant 商户信息
     * @return 结果
     */
	public int insertMemberTmMerchant(MemberTmMerchant memberTmMerchant);
	
	/**
     * 修改商户
     * 
     * @param memberTmMerchant 商户信息
     * @return 结果
     */
	public int updateMemberTmMerchant(MemberTmMerchant memberTmMerchant);
	
	/**
     * 删除商户
     * 
     * @param merchantId 商户ID
     * @return 结果
     */
	public int deleteMemberTmMerchantById(String merchantId);
	
	/**
     * 批量删除商户
     * 
     * @param merchantIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberTmMerchantByIds(String[] merchantIds);
	
}
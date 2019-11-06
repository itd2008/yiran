package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTmMemberContactMapper;
import com.yiran.member.domain.MemberTmMemberContact;
import com.yiran.member.service.IMemberTmMemberContactService;
import com.yiran.common.support.Convert;

/**
 * 会员账户 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTmMemberContactServiceImpl implements IMemberTmMemberContactService 
{
	@Autowired
	private MemberTmMemberContactMapper memberTmMemberContactMapper;

	/**
     * 查询会员账户信息
     * 
     * @param contactId 会员账户ID
     * @return 会员账户信息
     */
    @Override
	public MemberTmMemberContact selectMemberTmMemberContactById(Integer contactId)
	{
	    return memberTmMemberContactMapper.selectMemberTmMemberContactById(contactId);
	}
	
	/**
     * 查询会员账户列表
     * 
     * @param memberTmMemberContact 会员账户信息
     * @return 会员账户集合
     */
	@Override
	public List<MemberTmMemberContact> selectMemberTmMemberContactList(MemberTmMemberContact memberTmMemberContact)
	{
	    return memberTmMemberContactMapper.selectMemberTmMemberContactList(memberTmMemberContact);
	}
	
    /**
     * 新增会员账户
     * 
     * @param memberTmMemberContact 会员账户信息
     * @return 结果
     */
	@Override
	public int insertMemberTmMemberContact(MemberTmMemberContact memberTmMemberContact)
	{
	    return memberTmMemberContactMapper.insertMemberTmMemberContact(memberTmMemberContact);
	}
	
	/**
     * 修改会员账户
     * 
     * @param memberTmMemberContact 会员账户信息
     * @return 结果
     */
	@Override
	public int updateMemberTmMemberContact(MemberTmMemberContact memberTmMemberContact)
	{
	    return memberTmMemberContactMapper.updateMemberTmMemberContact(memberTmMemberContact);
	}

	/**
     * 删除会员账户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmMemberContactByIds(String ids)
	{
		return memberTmMemberContactMapper.deleteMemberTmMemberContactByIds(Convert.toStrArray(ids));
	}
	
}

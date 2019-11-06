package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTrPersonalMemberMapper;
import com.yiran.member.domain.MemberTrPersonalMember;
import com.yiran.member.service.IMemberTrPersonalMemberService;
import com.yiran.common.support.Convert;

/**
 * 个人会员 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrPersonalMemberServiceImpl implements IMemberTrPersonalMemberService 
{
	@Autowired
	private MemberTrPersonalMemberMapper memberTrPersonalMemberMapper;

	/**
     * 查询个人会员信息
     * 
     * @param memberId 个人会员ID
     * @return 个人会员信息
     */
    @Override
	public MemberTrPersonalMember selectMemberTrPersonalMemberById(String memberId)
	{
	    return memberTrPersonalMemberMapper.selectMemberTrPersonalMemberById(memberId);
	}
	
	/**
     * 查询个人会员列表
     * 
     * @param memberTrPersonalMember 个人会员信息
     * @return 个人会员集合
     */
	@Override
	public List<MemberTrPersonalMember> selectMemberTrPersonalMemberList(MemberTrPersonalMember memberTrPersonalMember)
	{
	    return memberTrPersonalMemberMapper.selectMemberTrPersonalMemberList(memberTrPersonalMember);
	}
	
    /**
     * 新增个人会员
     * 
     * @param memberTrPersonalMember 个人会员信息
     * @return 结果
     */
	@Override
	public int insertMemberTrPersonalMember(MemberTrPersonalMember memberTrPersonalMember)
	{
	    return memberTrPersonalMemberMapper.insertMemberTrPersonalMember(memberTrPersonalMember);
	}
	
	/**
     * 修改个人会员
     * 
     * @param memberTrPersonalMember 个人会员信息
     * @return 结果
     */
	@Override
	public int updateMemberTrPersonalMember(MemberTrPersonalMember memberTrPersonalMember)
	{
	    return memberTrPersonalMemberMapper.updateMemberTrPersonalMember(memberTrPersonalMember);
	}

	/**
     * 删除个人会员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrPersonalMemberByIds(String ids)
	{
		return memberTrPersonalMemberMapper.deleteMemberTrPersonalMemberByIds(Convert.toStrArray(ids));
	}
	
}

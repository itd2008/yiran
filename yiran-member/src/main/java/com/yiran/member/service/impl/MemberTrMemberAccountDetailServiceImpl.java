package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTrMemberAccountDetailMapper;
import com.yiran.member.domain.MemberTrMemberAccountDetail;
import com.yiran.member.service.IMemberTrMemberAccountDetailService;
import com.yiran.common.support.Convert;

/**
 * 会员账户明细 服务层实现
 * 
 * @author yiran
 * @date 2019-04-04
 */
@Service
public class MemberTrMemberAccountDetailServiceImpl implements IMemberTrMemberAccountDetailService 
{
	@Autowired
	private MemberTrMemberAccountDetailMapper memberTrMemberAccountDetailMapper;

	/**
     * 查询会员账户明细信息
     * 
     * @param id 会员账户明细ID
     * @return 会员账户明细信息
     */
    @Override
	public MemberTrMemberAccountDetail selectMemberTrMemberAccountDetailById(Integer id)
	{
	    return memberTrMemberAccountDetailMapper.selectMemberTrMemberAccountDetailById(id);
	}
	
	/**
     * 查询会员账户明细列表
     * 
     * @param memberTrMemberAccountDetail 会员账户明细信息
     * @return 会员账户明细集合
     */
	@Override
	public List<MemberTrMemberAccountDetail> selectMemberTrMemberAccountDetailList(MemberTrMemberAccountDetail memberTrMemberAccountDetail)
	{
	    return memberTrMemberAccountDetailMapper.selectMemberTrMemberAccountDetailList(memberTrMemberAccountDetail);
	}
	
    /**
     * 新增会员账户明细
     * 
     * @param memberTrMemberAccountDetail 会员账户明细信息
     * @return 结果
     */
	@Override
	public int insertMemberTrMemberAccountDetail(MemberTrMemberAccountDetail memberTrMemberAccountDetail)
	{
	    return memberTrMemberAccountDetailMapper.insertMemberTrMemberAccountDetail(memberTrMemberAccountDetail);
	}
	
	/**
     * 修改会员账户明细
     * 
     * @param memberTrMemberAccountDetail 会员账户明细信息
     * @return 结果
     */
	@Override
	public int updateMemberTrMemberAccountDetail(MemberTrMemberAccountDetail memberTrMemberAccountDetail)
	{
	    return memberTrMemberAccountDetailMapper.updateMemberTrMemberAccountDetail(memberTrMemberAccountDetail);
	}

	/**
     * 删除会员账户明细对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrMemberAccountDetailByIds(String ids)
	{
		return memberTrMemberAccountDetailMapper.deleteMemberTrMemberAccountDetailByIds(Convert.toStrArray(ids));
	}

	@Override
	public MemberTrMemberAccountDetail selectMemberTrMemberAccountDetailByMemberAccountId(Integer memberAccId) {
		return memberTrMemberAccountDetailMapper.selectMemberTrMemberAccountDetailByMemberAccountId(memberAccId);
	}
	
}

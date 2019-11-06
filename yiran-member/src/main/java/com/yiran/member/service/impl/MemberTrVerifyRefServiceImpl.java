package com.yiran.member.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTrVerifyRefMapper;
import com.yiran.member.domain.MemberTrVerifyRef;
import com.yiran.member.service.IMemberTrVerifyRefService;
import com.yiran.common.support.Convert;

/**
 * 认证关系 服务层实现
 * 
 * @author yiran
 * @date 2019-03-30
 */
@Service
public class MemberTrVerifyRefServiceImpl implements IMemberTrVerifyRefService 
{
	@Autowired
	private MemberTrVerifyRefMapper memberTrVerifyRefMapper;

	/**
     * 查询认证关系信息
     * 
     * @param verifyId 认证关系ID
     * @return 认证关系信息
     */
    @Override
	public MemberTrVerifyRef selectMemberTrVerifyRefById(Integer verifyId)
	{
	    return memberTrVerifyRefMapper.selectMemberTrVerifyRefById(verifyId);
	}
	
	/**
     * 查询认证关系列表
     * 
     * @param memberTrVerifyRef 认证关系信息
     * @return 认证关系集合
     */
	@Override
	public List<MemberTrVerifyRef> selectMemberTrVerifyRefList(MemberTrVerifyRef memberTrVerifyRef)
	{
	    return memberTrVerifyRefMapper.selectMemberTrVerifyRefList(memberTrVerifyRef);
	}
	
    /**
     * 新增认证关系
     * 
     * @param memberTrVerifyRef 认证关系信息
     * @return 结果
     */
	@Override
	public int insertMemberTrVerifyRef(MemberTrVerifyRef memberTrVerifyRef)
	{
	    return memberTrVerifyRefMapper.insertMemberTrVerifyRef(memberTrVerifyRef);
	}
	
	/**
     * 修改认证关系
     * 
     * @param memberTrVerifyRef 认证关系信息
     * @return 结果
     */
	@Override
	public int updateMemberTrVerifyRef(MemberTrVerifyRef memberTrVerifyRef)
	{
	    return memberTrVerifyRefMapper.updateMemberTrVerifyRef(memberTrVerifyRef);
	}

	/**
     * 删除认证关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTrVerifyRefByIds(String ids)
	{
		return memberTrVerifyRefMapper.deleteMemberTrVerifyRefByIds(Convert.toStrArray(ids));
	}

	@Override
	public MemberTrVerifyRef selectMemberTrVerifyRefByMemberId(String memberId) {
		List<MemberTrVerifyRef> list = memberTrVerifyRefMapper.selectMemberTrVerifyRefByMemberId(memberId);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}

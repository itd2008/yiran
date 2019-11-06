package com.yiran.member.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.member.mapper.MemberTmOperatorMapper;
import com.yiran.member.constant.FieldLength;
import com.yiran.member.constant.MaConstant;
import com.yiran.member.domain.LoginName;
import com.yiran.member.domain.MemberTmOperator;
import com.yiran.member.enums.ResponseCode;
import com.yiran.member.exception.MaBizException;
import com.yiran.member.service.IMemberTmOperatorService;

import cn.hutool.core.util.RandomUtil;

import com.netfinworks.common.lang.StringUtil;
import com.yiran.common.support.Convert;

/**
 * 操作员 服务层实现
 * 
 * @author yiran
 * @date 2019-03-31
 */
@Service
public class MemberTmOperatorServiceImpl implements IMemberTmOperatorService 
{
	@Autowired
	private MemberTmOperatorMapper memberTmOperatorMapper;

	/**
     * 查询操作员信息
     * 
     * @param operatorId 操作员ID
     * @return 操作员信息
     */
    @Override
	public MemberTmOperator selectMemberTmOperatorById(String operatorId)
	{
	    return memberTmOperatorMapper.selectMemberTmOperatorById(operatorId);
	}
	
	/**
     * 查询操作员列表
     * 
     * @param memberTmOperator 操作员信息
     * @return 操作员集合
     */
	@Override
	public List<MemberTmOperator> selectMemberTmOperatorList(MemberTmOperator memberTmOperator)
	{
	    return memberTmOperatorMapper.selectMemberTmOperatorList(memberTmOperator);
	}
	
    /**
     * 新增操作员
     * 
     * @param memberTmOperator 操作员信息
     * @return 结果
     */
	@Override
	public int insertMemberTmOperator(MemberTmOperator memberTmOperator)
	{
	    return memberTmOperatorMapper.insertMemberTmOperator(memberTmOperator);
	}
	
	/**
     * 修改操作员
     * 
     * @param memberTmOperator 操作员信息
     * @return 结果
     */
	@Override
	public int updateMemberTmOperator(MemberTmOperator memberTmOperator)
	{
	    return memberTmOperatorMapper.updateMemberTmOperator(memberTmOperator);
	}

	/**
     * 删除操作员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberTmOperatorByIds(String ids)
	{
		return memberTmOperatorMapper.deleteMemberTmOperatorByIds(Convert.toStrArray(ids));
	}

	@Override
	public int store(MemberTmOperator operator) {
		operator.setOperatorId(this.genOperatorId());
		 int flag = 0;
		 try {
			 flag = memberTmOperatorMapper.insertMemberTmOperator(operator);
	        if (flag == 0) {
	           
				throw new MaBizException(ResponseCode.OPERATOR_CREATE_FAIL);
	        }
		 } catch (MaBizException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	
	/*
     * 生成操作员id
     */
    private String genOperatorId() {
        String prefix = MaConstant.PRE_OPERATOR_ID;
        int seqLen = FieldLength.OPERATOR_ID - prefix.length();
        String operatorId = prefix
                            + StringUtil.alignRight(String.valueOf(RandomUtil.randomInt(10000)), seqLen,
                                MaConstant.ID_FIX_CHAR);
        return operatorId;
    }

	@Override
	public MemberTmOperator selectMemberTmOperatorByMemberId(String memberId) {
		return memberTmOperatorMapper.selectMemberTmOperatorByMemberId(memberId);
	}

}

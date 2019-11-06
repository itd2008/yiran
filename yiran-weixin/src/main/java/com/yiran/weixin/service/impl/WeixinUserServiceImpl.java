package com.yiran.weixin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.common.utils.StringUtils;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.mapper.WeixinUserMapper;
import com.yiran.weixin.service.IWeixinUserService;
import com.yiran.common.support.Convert;

/**
 * 公众号微信用户 服务层实现
 * 
 * @author yiran
 * @date 2018-08-19
 */
@Service
public class WeixinUserServiceImpl implements IWeixinUserService 
{
	@Autowired
	private WeixinUserMapper weixinUserMapper;

	/**
     * 查询公众号微信用户信息
     * 
     * @param id 公众号微信用户ID
     * @return 公众号微信用户信息
     */
    @Override
	public WeixinUser selectWeixinUserById(String id)
	{
	    return weixinUserMapper.selectWeixinUserById(id);
	}
	
	/**
     * 查询公众号微信用户列表
     * 
     * @param weixinUser 公众号微信用户信息
     * @return 公众号微信用户集合
     */
	@Override
	public List<WeixinUser> selectWeixinUserList(WeixinUser weixinUser)
	{
	    return weixinUserMapper.selectWeixinUserList(weixinUser);
	}
	
    /**
     * 新增公众号微信用户
     * 
     * @param weixinUser 公众号微信用户信息
     * @return 结果
     */
	@Override
	public int insertWeixinUser(WeixinUser weixinUser)
	{
	    return weixinUserMapper.insertWeixinUser(weixinUser);
	}
	
	/**
     * 修改公众号微信用户
     * 
     * @param weixinUser 公众号微信用户信息
     * @return 结果
     */
	@Override
	public int updateWeixinUser(WeixinUser weixinUser)
	{
	    return weixinUserMapper.updateWeixinUser(weixinUser);
	}
	
	/**
     * 保存公众号微信用户
     * 
     * @param weixinUser 公众号微信用户信息
     * @return 结果
     */
	@Override
	public int saveWeixinUser(WeixinUser weixinUser)
	{
	    String id = weixinUser.getId();
		int rows = 0;
		if (StringUtils.isNotNull(id))
        {
		    rows = weixinUserMapper.updateWeixinUser(weixinUser);
		}
		else
        {
		    rows = weixinUserMapper.insertWeixinUser(weixinUser);
		}
		return rows;
	}
	
	/**
     * 删除公众号微信用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWeixinUserByIds(String ids)
	{
		return weixinUserMapper.deleteWeixinUserByIds(Convert.toStrArray(ids));
	}

	@Override
	public WeixinUser selectWeixinUserByOpenId(String openid) {
		return weixinUserMapper.selectWeixinUserByOpenId(openid);
	}
	
}

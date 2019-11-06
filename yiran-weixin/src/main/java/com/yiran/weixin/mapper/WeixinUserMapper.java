package com.yiran.weixin.mapper;

import com.yiran.weixin.domain.WeixinUser;
import java.util.List;	

/**
 * 公众号微信用户 数据层
 * 
 * @author yiran
 * @date 2018-08-19
 */
public interface WeixinUserMapper 
{
	/**
     * 查询公众号微信用户信息
     * 
     * @param id 公众号微信用户ID
     * @return 公众号微信用户信息
     */
	public WeixinUser selectWeixinUserById(String id);
	/**
	 * 查询公众号微信用户信息
	 * @param openid 公众号微信用户OPENID
	 * @return 公众号微信用户信息
	 */
	public WeixinUser selectWeixinUserByOpenId(String openid);
	
	/**
     * 查询公众号微信用户列表
     * 
     * @param weixinUser 公众号微信用户信息
     * @return 公众号微信用户集合
     */
	public List<WeixinUser> selectWeixinUserList(WeixinUser weixinUser);
	
	/**
     * 新增公众号微信用户
     * 
     * @param weixinUser 公众号微信用户信息
     * @return 结果
     */
	public int insertWeixinUser(WeixinUser weixinUser);
	
	/**
     * 修改公众号微信用户
     * 
     * @param weixinUser 公众号微信用户信息
     * @return 结果
     */
	public int updateWeixinUser(WeixinUser weixinUser);
	
	/**
     * 删除公众号微信用户
     * 
     * @param id 公众号微信用户ID
     * @return 结果
     */
	public int deleteWeixinUserById(String id);
	
	/**
     * 批量删除公众号微信用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWeixinUserByIds(String[] ids);
	
}
package com.yiran.wechat.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatShopProductCategoryMapper;
import com.yiran.wechat.domain.WechatShopProductCategory;
import com.yiran.wechat.service.IWechatShopProductCategoryService;
import com.yiran.common.constant.UserConstants;
import com.yiran.common.support.Convert;
import com.yiran.common.utils.StringUtils;

/**
 * 商品类目 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatShopProductCategoryServiceImpl implements IWechatShopProductCategoryService 
{
	private final static String NAME_NOT_UNIQUE = "1";
	
	private final static String NAME_UNIQUE = "0";
	
	private final static String NORMAL = "0";
	
	@Autowired
	private WechatShopProductCategoryMapper wechatShopProductCategoryMapper;

	/**
     * 查询商品类目信息
     * 
     * @param id 商品类目ID
     * @return 商品类目信息
     */
    @Override
	public WechatShopProductCategory selectWechatShopProductCategoryById(Integer id)
	{
	    return wechatShopProductCategoryMapper.selectWechatShopProductCategoryById(id);
	}
	
	/**
     * 查询商品类目列表
     * 
     * @param wechatShopProductCategory 商品类目信息
     * @return 商品类目集合
     */
	@Override
	public List<WechatShopProductCategory> selectWechatShopProductCategoryList(WechatShopProductCategory wechatShopProductCategory)
	{
	    return wechatShopProductCategoryMapper.selectWechatShopProductCategoryList(wechatShopProductCategory);
	}
	
    /**
     * 新增商品类目
     * 
     * @param wechatShopProductCategory 商品类目信息
     * @return 结果
     */
	@Override
	public int insertWechatShopProductCategory(WechatShopProductCategory wechatShopProductCategory)
	{
	    return wechatShopProductCategoryMapper.insertWechatShopProductCategory(wechatShopProductCategory);
	}
	
	/**
     * 修改商品类目
     * 
     * @param wechatShopProductCategory 商品类目信息
     * @return 结果
     */
	@Override
	public int updateWechatShopProductCategory(WechatShopProductCategory wechatShopProductCategory)
	{
	    return wechatShopProductCategoryMapper.updateWechatShopProductCategory(wechatShopProductCategory);
	}

	/**
     * 删除商品类目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatShopProductCategoryByIds(String ids)
	{
		return wechatShopProductCategoryMapper.deleteWechatShopProductCategoryByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Map<String, Object>> selectChannelTree() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<WechatShopProductCategory> channelList = wechatShopProductCategoryMapper.selectChannelAll();

        for (WechatShopProductCategory channel : channelList)
        {
            if (NORMAL.equals(channel.getStatus().toString()))
            {
                Map<String, Object> channelMap = new HashMap<String, Object>();
                channelMap.put("id", channel.getId());
                channelMap.put("pId", channel.getPid());
                channelMap.put("name", channel.getName());
                channelMap.put("title", channel.getName());
                trees.add(channelMap);
            }
        }
		
		return trees;
	}

	@Override
	public String checkDeptNameUnique(WechatShopProductCategory productCategory) {
		if (productCategory.getId() == null)
        {
			productCategory.setId(-1);
        }
        Integer id = productCategory.getId();
        WechatShopProductCategory info = wechatShopProductCategoryMapper.checkDeptNameUnique(productCategory.getName());
        if (StringUtils.isNotNull(info) && StringUtils.isNotNull(info.getId())
                && info.getId() != id)
        {
            return NAME_NOT_UNIQUE;
        }
        return NAME_UNIQUE;
	}

	@Override
	public WechatShopProductCategory selectProductCategoryById(Integer id) {
		return wechatShopProductCategoryMapper.selectWechatShopProductCategoryById(id);
	}

	@Override
	public int selectChannelCount(Integer parentId) {
		WechatShopProductCategory pc = new WechatShopProductCategory();
		pc.setPid(parentId);
		return wechatShopProductCategoryMapper.selectChannelCount(pc);
	}

	@Override
	public int deleteWechatShopProductCategoryById(Integer id) {
		return wechatShopProductCategoryMapper.deleteWechatShopProductCategoryById(id);
	}

	@Override
	public List<Map<String, Object>> selectSecondChannelTree() {
		List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<WechatShopProductCategory> channelList = wechatShopProductCategoryMapper.selectSecondChannelTree();
        
        for (WechatShopProductCategory channel : channelList)
        {
            if (UserConstants.DEPT_NORMAL.equals(channel.getStatus().toString()))
            {
                Map<String, Object> channelMap = new HashMap<String, Object>();
                channelMap.put("id", channel.getId());
                channelMap.put("pId", channel.getPid());
                channelMap.put("name", channel.getName());
                channelMap.put("title", channel.getName());
                trees.add(channelMap);
            }
        }
		
		return trees;
	}
	
}

package com.yiran.wechat.service;

import com.yiran.wechat.domain.WechatShopProductCategory;
import java.util.List;
import java.util.Map;

/**
 * 商品类目 服务层
 * 
 * @author yiran
 * @date 2019-06-13
 */
public interface IWechatShopProductCategoryService 
{
	/**
     * 查询商品类目信息
     * 
     * @param id 商品类目ID
     * @return 商品类目信息
     */
	public WechatShopProductCategory selectWechatShopProductCategoryById(Integer id);
	
	/**
     * 查询商品类目列表
     * 
     * @param wechatShopProductCategory 商品类目信息
     * @return 商品类目集合
     */
	public List<WechatShopProductCategory> selectWechatShopProductCategoryList(WechatShopProductCategory wechatShopProductCategory);
	
	/**
     * 新增商品类目
     * 
     * @param wechatShopProductCategory 商品类目信息
     * @return 结果
     */
	public int insertWechatShopProductCategory(WechatShopProductCategory wechatShopProductCategory);
	
	/**
     * 修改商品类目
     * 
     * @param wechatShopProductCategory 商品类目信息
     * @return 结果
     */
	public int updateWechatShopProductCategory(WechatShopProductCategory wechatShopProductCategory);
		
	/**
     * 删除商品类目信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteWechatShopProductCategoryByIds(String ids);

	public List<Map<String, Object>> selectChannelTree();

	public String checkDeptNameUnique(WechatShopProductCategory productCategory);

	public WechatShopProductCategory selectProductCategoryById(Integer id);
	/**
     * 查询栏目人数
     * 
     * @param parentId 父部门ID
     * @return 结果
     */
    public int selectChannelCount(Integer parentId);

	public int deleteWechatShopProductCategoryById(Integer id);
	
	/**
     * 查询第二层菜单
     * @return
     */
	public List<Map<String, Object>> selectSecondChannelTree();
}

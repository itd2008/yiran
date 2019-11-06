package com.yiran.wechat.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.wechat.mapper.WechatProductAndSpecificationMapper;
import com.yiran.wechat.mapper.WechatProductSpecificationMapper;
import com.yiran.wechat.domain.SpecificationsVO;
import com.yiran.wechat.domain.WechatProductAndSpecification;
import com.yiran.wechat.domain.WechatProductSpecification;
import com.yiran.wechat.service.IWechatProductAndSpecificationService;
import com.alibaba.fastjson.JSON;
import com.yiran.common.support.Convert;
import com.yiran.system.domain.SysOperLog;

/**
 * 商品与商品规格关联 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductAndSpecificationServiceImpl implements IWechatProductAndSpecificationService 
{
	@Autowired
	private WechatProductAndSpecificationMapper wechatProductAndSpecificationMapper;
	@Autowired
	private WechatProductSpecificationMapper wechatProductSpecificationMapper;

	/**
     * 查询商品与商品规格关联信息
     * 
     * @param productAndSpecificationId 商品与商品规格关联ID
     * @return 商品与商品规格关联信息
     */
    @Override
	public WechatProductAndSpecification selectWechatProductAndSpecificationById(Integer productAndSpecificationId)
	{
	    return wechatProductAndSpecificationMapper.selectWechatProductAndSpecificationById(productAndSpecificationId);
	}
	
	/**
     * 查询商品与商品规格关联列表
     * 
     * @param wechatProductAndSpecification 商品与商品规格关联信息
     * @return 商品与商品规格关联集合
     */
	@Override
	public List<WechatProductAndSpecification> selectWechatProductAndSpecificationList(WechatProductAndSpecification wechatProductAndSpecification)
	{
	    return wechatProductAndSpecificationMapper.selectWechatProductAndSpecificationList(wechatProductAndSpecification);
	}
	
    /**
     * 新增商品与商品规格关联
     * 
     * @param wechatProductAndSpecification 商品与商品规格关联信息
     * @return 结果
     */
	@Override
	public int insertWechatProductAndSpecification(WechatProductAndSpecification wechatProductAndSpecification)
	{
	    return wechatProductAndSpecificationMapper.insertWechatProductAndSpecification(wechatProductAndSpecification);
	}
	
	/**
     * 修改商品与商品规格关联
     * 
     * @param wechatProductAndSpecification 商品与商品规格关联信息
     * @return 结果
     */
	@Override
	public int updateWechatProductAndSpecification(WechatProductAndSpecification wechatProductAndSpecification)
	{
	    return wechatProductAndSpecificationMapper.updateWechatProductAndSpecification(wechatProductAndSpecification);
	}

	/**
     * 删除商品与商品规格关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductAndSpecificationByIds(String ids)
	{
		return wechatProductAndSpecificationMapper.deleteWechatProductAndSpecificationByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<String[]> selectWechatProductAndSpecificationByProductId(Integer productId) {
		
		WechatProductAndSpecification wechatProductAndSpecification = new WechatProductAndSpecification();
		wechatProductAndSpecification.setProductId(String.valueOf(productId));
		List<WechatProductAndSpecification> list = wechatProductAndSpecificationMapper.selectWechatProductAndSpecificationList(wechatProductAndSpecification);
		List<SpecificationsVO> sList = new ArrayList<SpecificationsVO>();
		for (WechatProductAndSpecification ps : list) {
			String[] specificationIds = ps.getProductSpecificationId().split("_");
			for (String id : specificationIds) {
				SpecificationsVO vo = new SpecificationsVO();
				vo.setProductId(ps.getProductId());
				vo.setProductSpecificationId(ps.getProductSpecificationId());
				vo.setStock(ps.getStock());
				vo.setPrice(ps.getPrice());
				WechatProductSpecification productSpecification = wechatProductSpecificationMapper.selectWechatProductSpecificationById(Integer.parseInt(id));
				vo.setProductCategoryName(productSpecification.getProductCategoryName());
				vo.setSpecificationType(productSpecification.getSpecificationType());
				vo.setSpecificationCode(productSpecification.getSpecificationCode());
				vo.setSpecificationValue(productSpecification.getSpecificationValue());
				sList.add(vo);
			}
		}
		
		List<String[]> guigeList = new ArrayList<String[]>();
		//去重得到规格
		List<SpecificationsVO> removeDuplicateList = removeDuplicateVOSpecificationType(sList);
		for (int i = removeDuplicateList.size()-1; i >=0; i--) {
			SpecificationsVO vo = removeDuplicateList.get(i);
			String[] temp = new String[2];
			temp[0] = vo.getSpecificationType();
			Set<String> tempSet = new HashSet<String>();
			for (int j = 0; j < sList.size(); j++) {
				SpecificationsVO vo2 = sList.get(j);
				if(vo.getSpecificationType().equals(vo2.getSpecificationType())){
					tempSet.add(vo2.getSpecificationValue());
				}
			}
			//将set转换String
			StringBuffer sb = new StringBuffer();
			for (String s : tempSet) {
				sb.append(s);
				sb.append(",");
			}
			temp[1] = sb.toString();
			guigeList.add(temp);
		}
		
		return guigeList;
	}
	
	/**
     * 去重
     * 根据SpecificationType去重
     * @param orderList
     * @return
     */
    private static List<SpecificationsVO> removeDuplicateVOSpecificationType(List<SpecificationsVO> sList) {
        Set<SpecificationsVO> set = new TreeSet<SpecificationsVO>(new Comparator<SpecificationsVO>() {
            @Override
            public int compare(SpecificationsVO a, SpecificationsVO b) {
                // 字符串则按照asicc码升序排列
                return a.getSpecificationType().compareTo(b.getSpecificationType());
            }
        });
        
        set.addAll(sList);
        return new ArrayList<SpecificationsVO>(set);
    }
	
    /**
     * 去重
     * 根据getSpecificationValue去重
     * @param orderList
     * @return
     */
    private static List<SpecificationsVO> removeDuplicateVOSpecificationValue(List<SpecificationsVO> sList) {
        Set<SpecificationsVO> set = new TreeSet<SpecificationsVO>(new Comparator<SpecificationsVO>() {
            @Override
            public int compare(SpecificationsVO a, SpecificationsVO b) {
                // 字符串则按照asicc码升序排列
                return a.getSpecificationValue().compareTo(b.getSpecificationValue());
            }
        });
        
        set.addAll(sList);
        return new ArrayList<SpecificationsVO>(set);
    }

	@Override
	public WechatProductAndSpecification selectWechatProductAndSpecificationByProductIdAndSpecificationId(
			Integer productId, String specificationIds) {
		
		return wechatProductAndSpecificationMapper.selectWechatProductAndSpecificationByProductIdAndSpecificationId(String.valueOf(productId),specificationIds);
	}
}

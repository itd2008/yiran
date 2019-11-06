package com.yiran.wechat.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yiran.wechat.mapper.WechatProductAlbumMapper;
import com.yiran.wechat.mapper.WechatProductAndAttributeMapper;
import com.yiran.wechat.mapper.WechatProductAndSpecificationMapper;
import com.yiran.wechat.mapper.WechatProductDescriptionMapper;
import com.yiran.wechat.mapper.WechatProductInventoryMapper;
import com.yiran.wechat.mapper.WechatProductMapper;
import com.yiran.wechat.domain.PriceAndQuantity;
import com.yiran.wechat.domain.WechatProduct;
import com.yiran.wechat.domain.WechatProductAlbum;
import com.yiran.wechat.domain.WechatProductAndAttribute;
import com.yiran.wechat.domain.WechatProductAndSpecification;
import com.yiran.wechat.domain.WechatProductDescription;
import com.yiran.wechat.domain.WechatProductInventory;
import com.yiran.wechat.service.IWechatProductService;
import com.alibaba.fastjson.JSON;
import com.yiran.common.config.FastDFSConfig;
import com.yiran.common.support.Convert;
import com.yiran.common.utils.fastdft.FastDFSHelper;

/**
 * 商品 服务层实现
 * 
 * @author yiran
 * @date 2019-06-13
 */
@Service
public class WechatProductServiceImpl implements IWechatProductService 
{
	private Logger             logger = LoggerFactory.getLogger(WechatProductServiceImpl.class);
	@Autowired
	private WechatProductMapper wechatProductMapper;
    @Autowired
   	private FastDFSHelper fastDFSHelper;
    @Autowired
	private FastDFSConfig fastDFSTConfig;

    @Autowired
	private WechatProductAlbumMapper wechatProductAlbumMapper;
    @Autowired
	private WechatProductDescriptionMapper wechatProductDescriptionMapper;
    @Autowired
	private WechatProductAndAttributeMapper wechatProductAndAttributeMapper;
    @Autowired
	private WechatProductAndSpecificationMapper wechatProductAndSpecificationMapper;

	/**
     * 查询商品信息
     * 
     * @param productId 商品ID
     * @return 商品信息
     */
    @Override
	public WechatProduct selectWechatProductById(Integer productId)
	{
	    return wechatProductMapper.selectWechatProductById(productId);
	}
	
	/**
     * 查询商品列表
     * 
     * @param wechatProduct 商品信息
     * @return 商品集合
     */
	@Override
	public List<WechatProduct> selectWechatProductList(WechatProduct wechatProduct)
	{
	    return wechatProductMapper.selectWechatProductList(wechatProduct);
	}
	
    /**
     * 新增商品
     * 
     * @param wechatProduct 商品信息
     * @return 结果
     */
	@Override
	public int insertWechatProduct(MultipartFile[] file,WechatProduct wechatProduct)
	{
		WechatProduct product = convertProduct(wechatProduct);
		//1.上传图片
		System.out.println(">>>>>>>批量图片上传操作<<<<<<<<");
   		String[] urls = new String[8];
		MultipartFile multipartFile = null;
        try {
			System.out.println("图片数量:"+file.length);
			for (int i = 0; i < file.length; i++) {
                if (!file[i].isEmpty()) {
			    multipartFile = file[i];
			    String oldName = multipartFile.getOriginalFilename();
				System.out.println("上传图片"+i+"："+oldName);
				//上传
				String uploadFilePath = fastDFSHelper.uploadFile(multipartFile);
				System.out.println("上传图片"+i+"新名字："+uploadFilePath);
				//后缀
				String suffix = FilenameUtils.getExtension(oldName);
				String url = fastDFSTConfig.getInterNetHttpHost()+"/"+uploadFilePath;
				urls[i] = url;
				}
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}
        //商品图片url 
        product.setPictureUrl(urls[0]);
        logger.info("转换后的商品对象:{}",JSON.toJSONString(product));
        //2.保存商品信息
       int flag = wechatProductMapper.insertWechatProduct(product);
        int productId = product.getProductId();
        logger.info("添加商品信息返回商品ID={}",product.getProductId());
       
        //3.保存商品相册
        for (int i = 0; i < urls.length; i++) {
        	String url = urls[i];
        	if(!StringUtil.isBlank(url)){
        		WechatProductAlbum pa = new WechatProductAlbum();
            	pa.setProductId(productId);
            	pa.setImageUrl(url);
            	pa.setSort(String.valueOf((i+1)));
            	pa.setStatus("1");
                wechatProductAlbumMapper.insertWechatProductAlbum(pa);
        	}
        	
		}
        
        //4.保存商品详情信息
        WechatProductDescription pd = new WechatProductDescription();
        pd.setProductId(productId);
        pd.setTitle(wechatProduct.getTitle());
        pd.setContent(wechatProduct.getDescription());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("productType", wechatProduct.getProductType());
        map.put("isAdvanceSale", wechatProduct.getIsAdvanceSale());
        map.put("deliveryTime", wechatProduct.getDeliveryTime());
        map.put("free7days", wechatProduct.getFree7days());
        map.put("oneFalsePaysTen", wechatProduct.getOneFalsePaysTen());
        pd.setCode(JSON.toJSONString(map));
        wechatProductDescriptionMapper.insertWechatProductDescription(pd);
        
        //5.添加属性
        String[] attrs = wechatProduct.getAttrs().split(",");
        for (int i = 0; i < attrs.length; i++) {
        	WechatProductAndAttribute pattr = new WechatProductAndAttribute();
        	pattr.setProductId(productId);
        	pattr.setProductAttributeId(Integer.parseInt(attrs[i]));
        	wechatProductAndAttributeMapper.insertWechatProductAndAttribute(pattr);
		}
        
        //6添加商品规格
        List<PriceAndQuantity> list = getPriceAndQuantityList(wechatProduct);
        for (PriceAndQuantity p : list) {
        	WechatProductAndSpecification ps = new WechatProductAndSpecification();
        	ps.setProductId(String.valueOf(productId));
        	ps.setProductSpecificationId(p.getSpecification());
        	ps.setPrice(String.valueOf(p.getDiscountPrice()));
        	ps.setStock(String.valueOf(p.getInventory()));
        	wechatProductAndSpecificationMapper.insertWechatProductAndSpecification(ps);
		}
	    return 1;
	}
	
	private WechatProduct convertProduct(WechatProduct p) {
		WechatProduct product = new WechatProduct();
		product.setProductName(p.getProductName());
		product.setMarque(p.getMarque());
		product.setCategoryId(p.getCategoryId());
		product.setBrandId(p.getBrandId());
		//获取价格
		List<PriceAndQuantity> list = getPriceAndQuantityList(p);
		product.setPrice(getMinPriceAndMaxPrice(list));
		//库存数量
		product.setStock(getTotalInventory(list).toString());
		//是否上架1表示上架
		product.setStatus("1");
		product.setP1(p.getP1());
		product.setP2(p.getP2());
		product.setP3(p.getP3());
		//最低价格
		product.setP4(getMinPrice(list));
		return product;
	}

	private List<PriceAndQuantity> getPriceAndQuantityList(WechatProduct p){
		List<PriceAndQuantity> list =new ArrayList<PriceAndQuantity>();
		//价格数组
		String[] prices = p.getPrice().split(",");
		//折扣数组
		String[] discounts = p.getDiscount().split(",");
		//折扣价数组
		String[] discountPrices = p.getDiscountPrice().split(",");
		//库存数组
		String[] inventorys = p.getInventory().split(",");
		//规格组数组
		String[] specifications = p.getSpecificationIds().split(","); 
		for(int i=0;i<prices.length;i++){
			PriceAndQuantity pq = new PriceAndQuantity();
			pq.setPrice(Double.parseDouble(prices[i]));	
			pq.setDiscount(discounts[i]);
			pq.setDiscountPrice(Double.parseDouble(discountPrices[i]));
			pq.setInventory(Integer.parseInt(inventorys[i]));
			pq.setSpecification(specifications[i].substring(1));
			list.add(pq);
		}
		logger.info("规格价格数量集合:{}",JSON.toJSONString(list));
		return list;
	}
	
	private String getMinPriceAndMaxPrice(List<PriceAndQuantity> list){
		logger.info(">>>>>>>根据价格排序前的集合：{}",JSON.toJSONString(list));
		Collections.sort(list);
		logger.info("根据价格排序后的集合>>>>>>>：{}",JSON.toJSONString(list));
		StringBuffer bf =new StringBuffer();
		bf.append(list.get(0).getDiscountPrice());
		bf.append("-");
		bf.append(list.get(list.size()-1).getDiscountPrice());
		logger.info("价格区间：{}",bf.toString());
		return bf.toString();
	}
	
	private String getMinPrice(List<PriceAndQuantity> list){
		logger.info(">>>>>>>根据价格排序前的集合：{}",JSON.toJSONString(list));
		Collections.sort(list);
		logger.info("根据价格排序后的集合>>>>>>>：{}",JSON.toJSONString(list));
		StringBuffer bf =new StringBuffer();
		bf.append(list.get(0).getDiscountPrice());
		logger.info("最低价格：{}",bf.toString());
		return bf.toString();
	}
	
	private Integer getTotalInventory(List<PriceAndQuantity> list){
		Integer totalInventory = 0;
		for (PriceAndQuantity p : list) {
			totalInventory += p.getInventory();
		}
		return totalInventory;
	}
	
	/**
     * 修改商品
     * 
     * @param wechatProduct 商品信息
     * @return 结果
     */
	@Override
	public int updateWechatProduct(WechatProduct wechatProduct)
	{
	    return wechatProductMapper.updateWechatProduct(wechatProduct);
	}

	/**
     * 删除商品对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteWechatProductByIds(String ids)
	{
		return wechatProductMapper.deleteWechatProductByIds(Convert.toStrArray(ids));
	}
	
}

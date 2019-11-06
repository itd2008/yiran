package com.yiran.api.facade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiran.common.base.ResultWrapper;
import com.yiran.wechat.domain.WechatIconSort;
import com.yiran.wechat.domain.WechatIndexColumn;
import com.yiran.wechat.domain.WechatIndexPic;
import com.yiran.wechat.domain.WechatProduct;
import com.yiran.wechat.domain.WechatShoppingCart;
import com.yiran.wechat.service.IWechatIconSortService;
import com.yiran.wechat.service.IWechatIndexColumnService;
import com.yiran.wechat.service.IWechatIndexPicService;
import com.yiran.wechat.service.IWechatProductService;
import com.yiran.wechat.service.IWechatShopProductCategoryService;
import com.yiran.wechat.service.IWechatShoppingCartService;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.service.IWeixinUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/yiran/weChatMall")
@Api(value="微信商城接口",description="微信商城接口")
public class WechatMallFacade {
	private static final Logger logger = LoggerFactory.getLogger(WechatMallFacade.class);
	@Autowired
	private IWechatIndexPicService wechatIndexPicService;
	@Autowired
	private IWechatIconSortService wechatIconSortService;
	@Autowired
	private IWechatIndexColumnService wechatIndexColumnService;
	@Autowired
	private IWechatShopProductCategoryService wechatShopProductCategoryService;
	@Autowired
	private IWechatShoppingCartService wechatShoppingCartService;
	@Autowired
	private IWechatProductService wechatProductService;
	
	@Autowired
	private IWeixinUserService weixinUserService;
	
	@PostMapping("/queryRotaryPictures")
    @ApiOperation(value = "查询首页轮播图片",notes="查询首页轮播图片")
	public ResultWrapper<Map<String,Object>> queryRotaryPictures(){
		WechatIndexPic wechatIndexPic = new WechatIndexPic();
		wechatIndexPic.setStatus(1);
		List<WechatIndexPic> list = wechatIndexPicService.selectWechatIndexPicList(wechatIndexPic);
		return ResultWrapper.ok().putData(list);
	}
	
	@PostMapping("/queryIcon")
    @ApiOperation(value = "查询首页图标",notes="查询首页图标")
	public ResultWrapper<Map<String,Object>> queryIcon(){
		WechatIconSort wechatIconSort = new WechatIconSort();
		wechatIconSort.setIsShow(1);
		List<WechatIconSort> list = wechatIconSortService.selectWechatIconSortList(wechatIconSort);
		return ResultWrapper.ok().putData(list);
	}

	@PostMapping("/queryIndexColumn")
    @ApiOperation(value = "查询首页栏目",notes="查询首页栏目")
	public ResultWrapper<Map<String,Object>> queryIndexColumn(){
		WechatIndexColumn wechatIndexColumn = new WechatIndexColumn();
		wechatIndexColumn.setIsShow(1);
		List<WechatIndexColumn> list = wechatIndexColumnService.selectWechatIndexColumnList(wechatIndexColumn);
		return ResultWrapper.ok().putData(list);
	}
	
	@GetMapping("/productCategorySecondList")
    @ApiOperation("获取商品类目列表信息")
    public ResultWrapper<Map<String,Object>> productCategorySecondList(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>>  categoryList = wechatShopProductCategoryService.selectSecondChannelTree();
		map.put("categoryList", categoryList);
		logger.info("获取商品类目列表信息:" + JSON.toJSONString(categoryList));
		List<List<WechatProduct>> list = new ArrayList<List<WechatProduct>>();
		for (Map<String, Object> m : categoryList) {
			WechatProduct wechatProduct =new WechatProduct();
			Integer categoryId = (Integer) m.get("id");
			wechatProduct.setCategoryId(String.valueOf(categoryId));
			logger.info("获取商品类目列表信息-->类目ID:" + categoryId);
			List<WechatProduct> plist = wechatProductService.selectWechatProductList(wechatProduct);
			logger.info("根据类目【"+categoryId+"】查询集合列表："+JSON.toJSONString(plist));
			list.add(plist);
		}
		map.put("product", list);
        return ResultWrapper.ok().putData(map);
    }
	
	@PostMapping("/queryCarProduct/{openId}")
    @ApiOperation("获取购物车产品列表信息")
	public ResultWrapper<Map<String,Object>> queryCarProduct(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		
		System.out.println("【微信用户openId】：" + openId);
		List<WechatShoppingCart> list = wechatShoppingCartService.selectWechatShoppingCartByOpenId(openId);
		
		return ResultWrapper.ok().putData(list);
	}
	
	@PostMapping("/getUserInfo/{openId}")
    @ApiOperation(value = "查询微信用户信息",notes="查询微信用户信息")
	public ResultWrapper<Map<String,Object>> getUserInfo(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		System.out.println("【微信用户openId】：" + openId);
		WeixinUser weixinUser = weixinUserService.selectWeixinUserByOpenId(openId);
		return ResultWrapper.ok().putData(weixinUser);
	}
	
	
}

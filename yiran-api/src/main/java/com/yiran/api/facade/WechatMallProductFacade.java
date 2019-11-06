package com.yiran.api.facade;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiran.common.base.ResultWrapper;
import com.yiran.wechat.domain.WechatOrderProductComment;
import com.yiran.wechat.domain.WechatProduct;
import com.yiran.wechat.domain.WechatProductAlbum;
import com.yiran.wechat.domain.WechatProductAndSpecification;
import com.yiran.wechat.domain.WechatProductCoupon;
import com.yiran.wechat.domain.WechatProductDescription;
import com.yiran.wechat.domain.WechatProductSpecification;
import com.yiran.wechat.domain.WechatShoppingCart;
import com.yiran.wechat.domain.WechatUserProductCoupon;
import com.yiran.wechat.service.IWechatOrderProductCommentService;
import com.yiran.wechat.service.IWechatProductAlbumService;
import com.yiran.wechat.service.IWechatProductAndSpecificationService;
import com.yiran.wechat.service.IWechatProductCouponService;
import com.yiran.wechat.service.IWechatProductDescriptionService;
import com.yiran.wechat.service.IWechatProductService;
import com.yiran.wechat.service.IWechatProductSpecificationService;
import com.yiran.wechat.service.IWechatShoppingCartService;
import com.yiran.wechat.service.IWechatUserProductCouponService;
import com.yiran.weixin.domain.WeixinUser;
import com.yiran.weixin.service.IWeixinUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/yiran/weChatMall/product")
@Api(value="微信商城商品接口",description="微信商城商品接口")
public class WechatMallProductFacade {
	private static final Logger logger = LoggerFactory.getLogger(WechatMallProductFacade.class);
	@Autowired
	private IWechatProductService wechatProductService;
	@Autowired
	private IWechatProductAlbumService wechatProductAlbumService;
	@Autowired
	private IWechatProductDescriptionService wechatProductDescriptionService;
	@Autowired
	private IWechatProductCouponService wechatProductCouponService;
	@Autowired
	private IWechatProductAndSpecificationService wechatProductAndSpecificationService;
	@Autowired
	private IWechatOrderProductCommentService wechatOrderProductCommentService;
	@Autowired
	private IWechatProductSpecificationService wechatProductSpecificationService;
	@Autowired
	private IWechatShoppingCartService wechatShoppingCartService;
	@Autowired
	private IWechatUserProductCouponService wechatUserProductCouponService;
	@Autowired
	private IWeixinUserService weixinUserService;
	@PostMapping("/queryIndexProduct")
    @ApiOperation(value = "查询首页产品列表",notes="查询首页产品列表")
	public ResultWrapper<Map<String,Object>> queryRotaryPictures(){
		Map<String,Object> map = new HashMap<String,Object>();
		WechatProduct wechatProduct = new WechatProduct();
		//限时秒杀
//		wechatProduct.setP1("1");
//		List<WechatProduct> seckillList = wechatProductService.selectWechatProductList(wechatProduct);
		//新品推荐
//		wechatProduct.setP2("1");
//		wechatProduct.setP1(null);
//		List<WechatProduct> recommendList = wechatProductService.selectWechatProductList(wechatProduct);
		//猜你喜欢
		wechatProduct.setP3("1");
//		wechatProduct.setP2(null);
//		wechatProduct.setP1(null);
		List<WechatProduct> loveList = wechatProductService.selectWechatProductList(wechatProduct);
		
//		map.put("seckillList", seckillList);
//		map.put("recommendList", recommendList);
		map.put("loveList", loveList);
		return ResultWrapper.ok().putData(map);
	}
	
	@PostMapping("/queryWheelPlantPic/{productId}")
    @ApiOperation(value = "查询产品详情主图轮播列表",notes="查询产品详情主图轮播列表")
	public ResultWrapper<Map<String,Object>> queryWheelPlantPic(@ApiParam(required = true, name = "productId", value = "产品ID") @PathVariable Integer productId){
		List<WechatProductAlbum> list = wechatProductAlbumService.selectWechatProductAlbumByProductId(productId);
		return ResultWrapper.ok().putData(list);
	}
	
	@PostMapping("/queryProduct/{productId}")
    @ApiOperation(value = "查询产品信息",notes="查询产品信息")
	public ResultWrapper<Map<String,Object>> queryProduct(@ApiParam(required = true, name = "productId", value = "产品ID") @PathVariable Integer productId){
		Map<String,Object> map = new HashMap<String,Object>();
		//产品基本信息
		WechatProduct product = wechatProductService.selectWechatProductById(productId);
		map.put("product", product);
		//详细信息
		WechatProductDescription productDes = wechatProductDescriptionService.selectWechatProductDescriptionByProductId(productId);
		map.put("productDes", productDes);
		//获取优惠券
		List<WechatProductCoupon> productCouponList = wechatProductCouponService.selectWechatEffectiveProductCouponList();
		map.put("productCouponList", productCouponList);
		//获取规格
		List<String[]> productSpecification = wechatProductAndSpecificationService.selectWechatProductAndSpecificationByProductId(productId);
		map.put("productSpecification", productSpecification);
		//获取评论
		List<WechatOrderProductComment> productComment = wechatOrderProductCommentService.selectWechatOrderProductCommentByProductId(productId);
		map.put("productComment", productComment);
		return ResultWrapper.ok().putData(map);
	}
	
	@PostMapping("/queryCoupon")
    @ApiOperation(value = "查询所有优惠券",notes="查询所有优惠券")
	public ResultWrapper<Map<String,Object>> queryCoupon(){
		//获取优惠券
		List<WechatProductCoupon> productCouponList = wechatProductCouponService.selectWechatEffectiveProductCouponList();
		return ResultWrapper.ok().putData(productCouponList);
	}
	
	@PostMapping("/queryCouponBySerialNo/{serialNo}")
    @ApiOperation(value = "根据优惠券序号查询优惠券",notes="根据优惠券序号查询优惠券")
	public ResultWrapper<Map<String,Object>> queryCouponBySerialNo(@ApiParam(required = true, name = "serialNo", value = "优惠券序号") @PathVariable String serialNo){
		//获取优惠券
		WechatProductCoupon productCoupon = wechatProductCouponService.selectWechatProductCouponBySerialNo(serialNo);
		return ResultWrapper.ok().putData(productCoupon);
	}
	
	@PostMapping("/queryCouponByOpenId/{openId}")
    @ApiOperation(value = "查询当前用户领取的优惠券",notes="查询当前用户领取的优惠券")
	public ResultWrapper<Map<String,Object>> queryCouponByOpenId(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		WechatUserProductCoupon wechatUserProductCoupon = new WechatUserProductCoupon();
		wechatUserProductCoupon.setOpenId(openId);
		List<WechatUserProductCoupon> userProductCouponList = wechatUserProductCouponService.selectWechatUserProductCouponList(wechatUserProductCoupon);
		//未使用
		List<String> notUseds = new ArrayList<String>();
		//已使用
		List<String> useds = new ArrayList<String>();
		//已过期
		List<String> expireds = new ArrayList<String>();
		for (WechatUserProductCoupon coupon : userProductCouponList) {
			if(coupon.getStatus() == 0){//未使用
				notUseds.add(coupon.getCouponSerialNo());
			}else if(coupon.getStatus() == 1){//已使用
				useds.add(coupon.getCouponSerialNo());
			}else if(coupon.getStatus() == 2){//已过期
				expireds.add(coupon.getCouponSerialNo());
			}
		}
		logger.info("未使用:"+JSON.toJSONString(notUseds));
		logger.info("已使用:"+JSON.toJSONString(useds));
		logger.info("已过期:"+JSON.toJSONString(expireds));
		Map<String,Object> map = new HashMap<String,Object>();
		List<WechatProductCoupon> notUsedList = new ArrayList<WechatProductCoupon>();
		if(!notUseds.isEmpty() || notUseds.size() != 0){
			notUsedList = wechatProductCouponService.selectWechatProductCouponBySerialNos(notUseds);
		}
		List<WechatProductCoupon> usedList = new ArrayList<WechatProductCoupon>();
		if(!useds.isEmpty() || useds.size() != 0){
			usedList = wechatProductCouponService.selectWechatProductCouponBySerialNos(useds);
		}
		List<WechatProductCoupon> expiredList = new ArrayList<WechatProductCoupon>();
		if(!expireds.isEmpty() || expireds.size() != 0){
			expiredList = wechatProductCouponService.selectWechatProductCouponBySerialNos(expireds);
		}
		
		map.put("notUsedList", notUsedList);
		map.put("usedList", usedList);
		map.put("expiredList", expiredList);
		
		return ResultWrapper.ok().putData(map);
	}
	
	@PostMapping("/saveCouponBySerialNo/{serialNo}/{openId}")
    @ApiOperation(value = "领取优惠券",notes="领取优惠券")
	public ResultWrapper<Map<String,Object>> saveCouponBySerialNo(@ApiParam(required = true, name = "serialNo", value = "优惠券序号") @PathVariable String serialNo,
			@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		//获取优惠券
		WechatProductCoupon productCoupon = wechatProductCouponService.selectWechatProductCouponBySerialNo(serialNo);
		WechatUserProductCoupon wechatUserProductCoupon = new WechatUserProductCoupon();
		wechatUserProductCoupon.setOpenId(openId);
		wechatUserProductCoupon.setCouponSerialNo(serialNo);
		wechatUserProductCoupon.setStatus(0);
		wechatUserProductCouponService.insertWechatUserProductCoupon(wechatUserProductCoupon);
		//领取数量
		Integer receiveNum = Integer.parseInt(productCoupon.getReceiveNum()) + 1;
		productCoupon.setReceiveNum(String.valueOf(receiveNum));
		//剩余数量 = 总数量 - 领取数量
		//总数量
		Integer quantity = Integer.parseInt(productCoupon.getQuantity());
		Integer residualQuantity = quantity - receiveNum;
		productCoupon.setResidualQuantity(String.valueOf(residualQuantity));
		wechatProductCouponService.updateWechatProductCouponNumber(productCoupon);
		return ResultWrapper.ok();
	}
	
	@PostMapping("/updateCouponBySerialNo/{serialNo}")
    @ApiOperation(value = "使用优惠券",notes="使用优惠券")
	public ResultWrapper<Map<String,Object>> updateCouponBySerialNo(@ApiParam(required = true, name = "serialNo", value = "优惠券序号") @PathVariable String serialNo){
		WechatUserProductCoupon wechatUserProductCoupon = new WechatUserProductCoupon();
		wechatUserProductCoupon.setStatus(1);
		wechatUserProductCoupon.setCouponSerialNo(serialNo);
		wechatUserProductCouponService.updateWechatUserProductCouponBySerialNo(wechatUserProductCoupon);
		
		return ResultWrapper.ok();
	}
	
	@PostMapping("/queryCouponIsGet/{openId}")
    @ApiOperation(value = "查询产品信息如果用户已经领取那么显示领取，否则显示未领取",notes="查询产品信息如果用户已经领取那么显示领取，否则显示未领取")
	public ResultWrapper<Map<String,Object>> queryCouponIsGet(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		//获取优惠券
		List<WechatProductCoupon> productCouponList = wechatProductCouponService.selectWechatEffectiveProductCouponList();
		//获取当前用户领取过的优惠券
		WechatUserProductCoupon wechatUserProductCoupon = new WechatUserProductCoupon();
		wechatUserProductCoupon.setOpenId(openId);
		List<WechatUserProductCoupon> couponList = wechatUserProductCouponService.selectWechatUserProductCouponList(wechatUserProductCoupon);
		for (WechatUserProductCoupon userProductCoupon : couponList) {
			for (WechatProductCoupon coupon : productCouponList) {
				if(userProductCoupon.getCouponSerialNo().equals(coupon.getSerialNo())){
					coupon.setIsGet("1");
				}
			}
		}
		return ResultWrapper.ok().putData(productCouponList);
	}
	
	@PostMapping("/queryPicBySpecification/{productId}/{specification}")
    @ApiOperation(value = "根据产品ID选中规格查询价格",notes="根据产品ID选中规格查询价格")
	public ResultWrapper<Map<String,Object>> queryPicBySpecification(
			@ApiParam(required = true, name = "productId", value = "产品ID") 
			@PathVariable Integer productId,
			@ApiParam(required = true, name = "specification", value = "产品规格") 
			@PathVariable String specification) throws UnsupportedEncodingException{
		logger.info("产品ID="+productId);
		logger.info("选中产品规格="+URLDecoder.decode(specification, "UTF-8"));
		specification = URLDecoder.decode(specification, "UTF-8");
		WechatProductAndSpecification productAndSpecification = getSelectPriceBySpecification(productId, specification);
		
 		return ResultWrapper.ok().putData(productAndSpecification);
	}

	private WechatProductAndSpecification getSelectPriceBySpecification(Integer productId, String specification) {
		String[] temp = specification.split("_");
		List<String> ggList = new ArrayList<String>();
		for (String str : temp) {
			ggList.add(str);
		}
		//产品基本信息
		WechatProduct product = wechatProductService.selectWechatProductById(productId);
		//根据类目id和规格查询规格组合
		List<WechatProductSpecification> list = wechatProductSpecificationService.selectWechatProductSpecificationListByCategoryIdAndSpecifications(product.getProductCategory().getId(),ggList);
		String specificationIds = "";
		for (WechatProductSpecification s : list) {
			specificationIds += s.getProductSpecification() +"_";
		}
		specificationIds = specificationIds.substring(0, specificationIds.length()-1);
		//获取价格
		WechatProductAndSpecification productAndSpecification = wechatProductAndSpecificationService.selectWechatProductAndSpecificationByProductIdAndSpecificationId(productId,specificationIds);
		return productAndSpecification;
	}
	
	@PostMapping("/saveShopCat/{productId}/{specification}/{price}/{openId}")
    @ApiOperation(value = "选中的保存到购物车",notes="选中的保存到购物车")
	public ResultWrapper<Map<String,Object>> saveShopCat(
			@ApiParam(required = true, name = "productId", value = "产品ID") 
			@PathVariable Integer productId,
			@ApiParam(required = true, name = "specification", value = "产品规格") 
			@PathVariable String specification,
			@ApiParam(required = true, name = "price", value = "价格") 
			@PathVariable String price,
			@ApiParam(required = true, name = "openId", value = "微信openId") 
			@PathVariable String openId) throws UnsupportedEncodingException{
		logger.info("产品ID="+productId);
		logger.info("选中产品规格="+URLDecoder.decode(specification, "UTF-8"));
		logger.info("价格="+price);
		specification = URLDecoder.decode(specification, "UTF-8");
		
		WechatShoppingCart shopCat = wechatShoppingCartService.selectWechatShoppingCartBypProductId(productId);
		if(shopCat !=null){
			shopCat.setNumber(shopCat.getNumber()+1);
			wechatShoppingCartService.updateWechatShoppingCart(shopCat);
		}else{
			//产品基本信息
			WechatProduct product = wechatProductService.selectWechatProductById(productId);
			WechatShoppingCart wechatShoppingCart = new WechatShoppingCart();
			//TODO: userId 和昵称待修改
			wechatShoppingCart.setUserId(openId);
			WeixinUser weixinUser = weixinUserService.selectWeixinUserByOpenId(openId);
			wechatShoppingCart.setNickName(weixinUser.getNickname());
			wechatShoppingCart.setProductTitle(product.getTitle());
			wechatShoppingCart.setProductId(productId);
			wechatShoppingCart.setIsProductExists(1);
			wechatShoppingCart.setNumber(1);
			wechatShoppingCart.setAttr(specification);
			wechatShoppingCart.setPrice(price);
			wechatShoppingCartService.insertWechatShoppingCart(wechatShoppingCart);
		}
 		return ResultWrapper.ok();
	}
	
	@PostMapping("/deleteShopCat/{productId}")
    @ApiOperation(value = "根据产品ID删除购物车中的产品",notes="根据产品ID删除购物车中的产品")
	public ResultWrapper<Map<String,Object>> deleteShopCat(@ApiParam(required = true, name = "productId", value = "产品ID") 
	@PathVariable Integer productId){
		logger.info("产品ID="+productId);
		wechatShoppingCartService.deleteWechatShoppingByProductId(productId);
		return ResultWrapper.ok();
	}
}

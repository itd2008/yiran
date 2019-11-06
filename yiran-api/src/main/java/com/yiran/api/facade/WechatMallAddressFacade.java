package com.yiran.api.facade;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yiran.api.enums.VerifyResultEnum;
import com.yiran.api.vo.VerifyResult;
import com.yiran.common.base.ResultWrapper;
import com.yiran.wechat.domain.WechatProductAlbum;
import com.yiran.wechat.domain.WechatReceivingAddress;
import com.yiran.wechat.service.IWechatReceivingAddressService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/yiran/weChatMall/address")
@Api(value="微信商城地址管理接口",description="微信商城地址管理接口")
public class WechatMallAddressFacade {
	private static final Logger logger = LoggerFactory.getLogger(WechatMallAddressFacade.class);
	@Autowired
	private IWechatReceivingAddressService wechatReceivingAddressService;
	
	@PostMapping("/queryAddressList/{openId}")
    @ApiOperation(value = "获取当前用户所有收件地址列表",notes="获取当前用户所有收件地址列表")
	public ResultWrapper<Map<String,Object>> queryRotaryPictures(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		WechatReceivingAddress wechatReceivingAddress = new WechatReceivingAddress();
		wechatReceivingAddress.setUserId(openId);
		List<WechatReceivingAddress> list = wechatReceivingAddressService.selectWechatReceivingAddressList(wechatReceivingAddress);
		return ResultWrapper.ok().putData(list);
	}
	
	@PostMapping("/saveRealnameAddress")
    @ApiOperation(value = "保存收件人地址",notes="【请求参数】:  \n"
			+ "realname:收件人姓名  \n"
			+ "telephone:联系电话   \n"
			+ "isDefaultAddress:是否是默认地址，0表示否，1表示是   \n"
			+ "province:省份   \n"
			+ "provinceCode:省份Code \n"
			+ "city:市   \n"
			+ "cityCode:市Code \n"
			+ "area:区   \n"
			+ "areaCode:联系电话   \n"
			+ "detailedAddress:地址详情   \n"
			+ "userId:微信openId   \n"
			)
    public ResultWrapper<Map<String,Object>> saveRealnameAddress(@RequestBody @ApiParam(name="addressJson",value="请求参数json数据")String addressJson) {
        try {
        	logger.info("保存收件人地址参数:{}",addressJson);
        	WechatReceivingAddress receivingAddress = JSON.parseObject(addressJson, WechatReceivingAddress.class);
        	logger.info("json转换WechatReceivingAddress对象后数据:"+JSON.toJSONString(receivingAddress));
        	//验证参数是否为空
        	VerifyResult verifyResult = checkParameter(receivingAddress);
        	if(!verifyResult.isSuccess()){
        		return ResultWrapper.newInstance(verifyResult.getCode(), verifyResult.getMsg());
        	}
        	if(receivingAddress.getIsDefaultAddress() == 1){//是默认地址   默认只有一个
        		//查询是否有存在的默认的，若果存在则需要去除
        		WechatReceivingAddress wechatReceivingAddress = new WechatReceivingAddress();
        		List<WechatReceivingAddress> addressList = wechatReceivingAddressService.selectWechatReceivingAddressList(wechatReceivingAddress);
        		if(addressList.size() > 0){
        			for (WechatReceivingAddress address : addressList) {
						if(address.getIsDefaultAddress() == 1){//说明已经存在默认
							wechatReceivingAddressService.updateIsDefaultAddress(address.getId());
							break;
						}
					}
        		}
        	}
    		wechatReceivingAddressService.insertWechatReceivingAddress(receivingAddress);
            return ResultWrapper.ok();
        } catch (Exception e) {
            logger.error("保存收件人地址信息异常 : ", e);
            throw new RuntimeException("调用saveRealnameAddress接口异常", e);
        }

    }
	
	@PostMapping("/getAddressById/{addressId}")
    @ApiOperation(value = "根据ID获取地址信息",notes="根据ID获取地址信息")
	public ResultWrapper<Map<String,Object>> queryWheelPlantPic(@ApiParam(required = true, name = "addressId", value = "收件人地址ID") @PathVariable Integer addressId){
		WechatReceivingAddress receivingAddress = wechatReceivingAddressService.selectWechatReceivingAddressById(addressId);
		return ResultWrapper.ok().putData(receivingAddress);
	}

	private VerifyResult checkParameter(WechatReceivingAddress receivingAddress) {
		VerifyResult verifyResult = new VerifyResult();
		if(StringUtils.isBlank(receivingAddress.getRealname())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("收款人信息为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		
		if(StringUtils.isBlank(receivingAddress.getTelephone())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("联系电话为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		
		if(StringUtils.isBlank(receivingAddress.getProvince()) 
				|| StringUtils.isBlank(receivingAddress.getCity()) 
				|| StringUtils.isBlank(receivingAddress.getArea())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("省市区为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		if(StringUtils.isBlank(receivingAddress.getDetailedAddress())){
			verifyResult.setCode(VerifyResultEnum.ERROR_01.getCode());
			verifyResult.setMsg("地址详情为空");
			verifyResult.setSuccess(false);
			return verifyResult;
		}
		verifyResult.setSuccess(true);
		return verifyResult;
	}
	
	@PostMapping("/editRealnameAddress")
    @ApiOperation(value = "修改收件人地址",notes="【请求参数】:  \n"
			+ "realname:收件人姓名  \n"
			+ "telephone:联系电话   \n"
			+ "isDefaultAddress:是否是默认地址，0表示否，1表示是   \n"
			+ "province:省份   \n"
			+ "provinceCode:省份Code \n"
			+ "city:市   \n"
			+ "cityCode:市Code \n"
			+ "area:区   \n"
			+ "areaCode:联系电话   \n"
			+ "detailedAddress:地址详情   \n"
			+ "userId:微信openId   \n"
			)
    public ResultWrapper<Map<String,Object>> editRealnameAddress(@RequestBody @ApiParam(name="addressJson",value="请求参数json数据")String addressJson) {
        try {
        	logger.info("修改收件人地址参数:{}",addressJson);
        	WechatReceivingAddress receivingAddress = JSON.parseObject(addressJson, WechatReceivingAddress.class);
        	logger.info("json转换WechatReceivingAddress对象后数据:"+JSON.toJSONString(receivingAddress));
        	//验证参数是否为空
        	VerifyResult verifyResult = checkParameter(receivingAddress);
        	if(!verifyResult.isSuccess()){
        		return ResultWrapper.newInstance(verifyResult.getCode(), verifyResult.getMsg());
        	}
        	if(receivingAddress.getIsDefaultAddress() == 1){//是默认地址   默认只有一个
        		//查询是否有存在的默认的，若果存在则需要去除
        		WechatReceivingAddress wechatReceivingAddress = new WechatReceivingAddress();
        		List<WechatReceivingAddress> addressList = wechatReceivingAddressService.selectWechatReceivingAddressList(wechatReceivingAddress);
        		if(addressList.size() > 0){
        			for (WechatReceivingAddress address : addressList) {
						if(address.getIsDefaultAddress() == 1){//说明已经存在默认
							wechatReceivingAddressService.updateIsDefaultAddress(address.getId());
							break;
						}
					}
        		}
        	}
    		wechatReceivingAddressService.updateWechatReceivingAddress(receivingAddress);
            return ResultWrapper.ok();
        } catch (Exception e) {
            logger.error("保存收件人地址信息异常 : ", e);
            throw new RuntimeException("调用saveRealnameAddress接口异常", e);
        }

    }
	
	@PostMapping("/deleteRealnameAddress/{addressId}")
    @ApiOperation(value = "根据ID删除地址信息",notes="根据ID获取地址信息")
	public ResultWrapper<Map<String,Object>> deleteRealnameAddress(@ApiParam(required = true, name = "addressId", value = "收件人地址ID") @PathVariable Integer addressId){
		wechatReceivingAddressService.deleteWechatReceivingAddressById(addressId);
		return ResultWrapper.ok();
	}
	
	
	@PostMapping("/queryDefaultAddress/{openId}")
    @ApiOperation(value = "查询当前用户默认收货人地址",notes="查询当前用户默认收货人地址")
	public ResultWrapper<Map<String,Object>> queryDefaultAddress(@ApiParam(required = true, name = "openId", value = "微信openId") @PathVariable String openId){
		WechatReceivingAddress receivingAddress = wechatReceivingAddressService.queryDefaultAddress(openId);
		if(receivingAddress == null){//为空说明该用户没有默认地址，从地址列表张
			WechatReceivingAddress addressQuery = new WechatReceivingAddress();
			addressQuery.setUserId(openId);
			List<WechatReceivingAddress>  list = wechatReceivingAddressService.selectWechatReceivingAddressList(addressQuery);
			if(list.size()>0){
				receivingAddress = list.get(0);
			}else{
				receivingAddress = null;
			}
		}
		return ResultWrapper.ok().putData(receivingAddress);
	}
	
}

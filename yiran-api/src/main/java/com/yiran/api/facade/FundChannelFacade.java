/*package com.yiran.api.facade;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yiran.api.request.RoutingRequest;
import com.yiran.common.base.ResultWrapper;
import com.yiran.common.exception.base.BaseException;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.enums.PayMode;
import com.yiran.paychannel.service.IFundChannelRouter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/yiran/fundChannel")
@Api(value="渠道路由接口",description="渠道路由接口")
public class FundChannelFacade {
	private Logger             logger = LoggerFactory.getLogger(FundChannelFacade.class);
	
	@Autowired
	private IFundChannelRouter fundChannelRouter;
	
	*//**
	 * {
		  "extensions": {"partnerId":"20000126504"},
		  "payMode": "QPAY",
		  "requestType": "FUND_IN",
		  "targetInst": "ICBC"
	  }
	  
	 * @param request
	 * @return
	 *//*
	@PostMapping("/routerFundChannel")
    @ApiOperation(value = "依据目标机构和请求类型路由渠道",notes="请求参数】:  \n"
			+ "payMode:支付方式【QPAY】  \n"
			+ "requestType:请求类型【FUND_IN】 \n"
			+ "targetInst:目标机构【ICBC】  \n"
			+ "extensions：扩展参数【 {\"partnerId\":\"20000126504\"}】 \n")
    public ResultWrapper<Map<String,Object>> routerFundChannel(@RequestBody RoutingRequest request) {
        try {
        	if(StringUtils.isBlank(request.getTargetInst())){
        		throw new BaseException("资金渠道模块", "依据目标机构和请求类型路由渠道-目标参数不能为空");
        	}
        	
        	if(StringUtils.isBlank(request.getPayMode())){
        		throw new BaseException("资金渠道模块", "依据目标机构和请求类型路由渠道-支付类型不能为空");
        	}
        	if(StringUtils.isBlank(request.getRequestType())){
        		throw new BaseException("资金渠道模块", "依据目标机构和请求类型路由渠道-请求类型不能为空");
        	}
        	TmFundChannel routerFundChannel = fundChannelRouter.routerFundChannel(request.getTargetInst(), PayMode.getByCode(request.getPayMode()), request.getRequestType(), request.getExtensions());
            if (logger.isInfoEnabled()) {
                logger.info("依据目标机构和请求类型路由渠道返回对象 : " + routerFundChannel.toString());
            }
            return ResultWrapper.ok().putData(routerFundChannel);
        } catch (Exception e) {
            logger.error("依据目标机构和请求类型路由渠道信息异常 : ", e);
            throw new RuntimeException("调用routerFundChannel接口异常", e);
        }

    }

}
*/
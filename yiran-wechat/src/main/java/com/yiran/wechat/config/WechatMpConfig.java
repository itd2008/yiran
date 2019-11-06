package com.yiran.wechat.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.yiran.weixin.domain.WeixinSetting;
import com.yiran.weixin.service.IWeixinSettingService;
import com.yiran.weixin.service.impl.WeixinSettingServiceImpl;


/**
 * 微信公众号开发配置
 * @author pandaa
 *
 */
@Component
public class WechatMpConfig {

	@Autowired
	private IWeixinSettingService weixinSettingService;
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        String appId = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPID).getWeixinValue();
        //logger.info("【微信支付配置】->【微信appID】："+appId);
        String appSecret = weixinSettingService.getValueByKey(WeixinSetting.KEY_WEIXIN_APPSECRET).getWeixinValue();
        //logger.info("【微信支付配置】->【微信秘钥appSecret】："+appSecret);
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(appId);
        wxMpConfigStorage.setSecret(appSecret);
        return wxMpConfigStorage;
    }
}

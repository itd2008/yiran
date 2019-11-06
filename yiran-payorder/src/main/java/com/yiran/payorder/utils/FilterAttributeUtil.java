package com.yiran.payorder.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.DateUtil;
import com.yiran.paychannel.enums.AccessChannel;
import com.yiran.paychannel.enums.BizType;
import com.yiran.paychannel.enums.ChannelInfoExtKey;
import com.yiran.paychannel.enums.CompanyOrPersonal;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.payorder.domain.ChannelPayOrder;


/**
 * 依据CMF订单与数据库中配置的属性名称mapping获取数据
 */
@Service("filterAttributeUtil")
public class FilterAttributeUtil {
    protected static final Logger            logger             = LoggerFactory.getLogger(FilterAttributeUtil.class);

    private static final String              ROUTER_FUND_KEY    = "fundKey";
    private static final String              ROUTER_CONTROL_KEY = "fundControlKey";
    private static final String              ROUTER_POS_KEY     = "fundPosKey";

    private static Map<String, List<String>> keyMap             = new HashMap<String, List<String>>();

    public static List<String> combine(String... key) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < key.length; i++) {
            list.add(key[i]);
        }
        return list;
    }

    static {
        keyMap.put(
            ROUTER_FUND_KEY,
            combine(ExtensionKey.DBCR.key, ExtensionKey.PRODUCT_TYPE.key,
                ExtensionKey.CARD_TYPE.key, ExtensionKey.COMPANY_OR_PERSONAL.key,
                ExtensionKey.ACCESS_CHANNEL.key, ExtensionKey.CHANNEL_TYPE.key,
                ExtensionKey.CARD_PRICE.key, ExtensionKey.PRODUCT_CODE.key,
                ExtensionKey.SIGN_NO.key));
        keyMap.put(
            ROUTER_CONTROL_KEY,
            combine(ExtensionKey.DBCR.key, ExtensionKey.PRODUCT_TYPE.key,
                ExtensionKey.CARD_TYPE.key, ExtensionKey.COMPANY_OR_PERSONAL.key,
                ExtensionKey.ACCESS_CHANNEL.key, ExtensionKey.CHANNEL_TYPE.key,
                ExtensionKey.CARD_PRICE.key, ExtensionKey.PRODUCT_CODE.key,
                ExtensionKey.SIGN_NO.key));
        keyMap.put(
            ROUTER_POS_KEY,
            combine(ExtensionKey.DBCR.key, ExtensionKey.PRODUCT_TYPE.key,
                ExtensionKey.CARD_TYPE.key, ExtensionKey.COMPANY_OR_PERSONAL.key,
                ExtensionKey.ACCESS_CHANNEL.key, ExtensionKey.CHANNEL_TYPE.key,
                ExtensionKey.CARD_PRICE.key, ExtensionKey.PRODUCT_CODE.key,
                ExtensionKey.SIGN_NO.key));
    }

    private static Integer getCurrentTime() {
        Date date = new Date();
        String str = new Integer(date.getHours()).toString()
                     + new Integer(date.getMinutes()).toString();
        return Integer.valueOf(str);
    }

    /**
     * 依据机构订单转换为map
     * @param cmfOrder
     * @return
     */
    public static Map<String, ?> convert(ChannelPayOrder channelPayOrder) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (BizType.FUNDIN.equals(channelPayOrder.getBizType())) {
            String dbcr = channelPayOrder.getExtension().get(ExtensionKey.DBCR.key);
            if (dbcr != null && !"".equals(dbcr)) {
                // String[] array = dbcr.split(",");
                map.put(ChannelInfoExtKey.DBCR.getCode(), dbcr);
            }
        }
        String productType = channelPayOrder.getExtension().get(ExtensionKey.PRODUCT_TYPE.key);
        if (productType != null && !"".equals(productType)) {
            map.put(ChannelInfoExtKey.PRODUCT_TYPE.getCode(), productType);
        }
        if (BizType.FUNDOUT.equals(channelPayOrder.getBizType())
            && channelPayOrder.getExtension().get(ExtensionKey.CARD_TYPE.key) != null) {
            map.put(ChannelInfoExtKey.CARD_TYPE.getCode(),
            		channelPayOrder.getExtension().get(ExtensionKey.CARD_TYPE.key));
        }
        String companyOrPersonal = channelPayOrder.getExtension().get(ExtensionKey.COMPANY_OR_PERSONAL.key);
        if (companyOrPersonal == null || "".equals(companyOrPersonal)) {
            map.put(ChannelInfoExtKey.COMPANY_OR_PERSONAL.getCode(),
                CompanyOrPersonal.PERSONAL.getCode());
        } else {
            map.put(ChannelInfoExtKey.COMPANY_OR_PERSONAL.getCode(), companyOrPersonal);
        }
       
        String accessChannel = channelPayOrder.getExtension().get(ExtensionKey.ACCESS_CHANNEL.key);
        if (accessChannel == null || "".equals(accessChannel)) {
            map.put(ChannelInfoExtKey.SUPPORTED_ACCESS_CHANNELS.getCode(),
                AccessChannel.WEB.getCode());
        } else {
            map.put(ChannelInfoExtKey.SUPPORTED_ACCESS_CHANNELS.getCode(), accessChannel);
        }
        if (null == channelPayOrder.getAmount() || null == channelPayOrder.getAmount().getAmount()) {
            map.put(ChannelInfoExtKey.AMOUNT.getCode(), new String("0.00"));
        } else {
            map.put(ChannelInfoExtKey.AMOUNT.getCode(), channelPayOrder.getAmount().getAmount().toString());
        }
        map.put(ChannelInfoExtKey.TIME.getCode(), getCurrentTime());
        //TODO 这一行不需要有。order.getInstCode()就存了该值.
        map.put(ChannelInfoExtKey.CHANNEL_TYPE.getCode(),
        		channelPayOrder.getExtension().get(ExtensionKey.CHANNEL_TYPE.key));

        map.put(ChannelInfoExtKey.BIZ_TYPE.getCode(), channelPayOrder.getBizType());

        String cardPrice = channelPayOrder.getExtension().get(ExtensionKey.CARD_PRICE.key);
        if (cardPrice == null || "".equals(cardPrice)) {
            map.put(ChannelInfoExtKey.CARD_PRICE.getCode(), null);
        } else {
            map.put(ChannelInfoExtKey.CARD_PRICE.getCode(), new BigDecimal(cardPrice));
        }
        map.put(ChannelInfoExtKey.TARGET_INST.getCode(), channelPayOrder.getInstCode());
        map.put(ChannelInfoExtKey.PRODUCT_CODE.getCode(),
        		channelPayOrder.getExtension().get(ExtensionKey.PRODUCT_CODE.key));
        map.put(ChannelInfoExtKey.SIGN_NO.getCode(),
        		channelPayOrder.getExtension().get(ExtensionKey.SIGN_NO.key));
        //map.put(IS_SUPPORT_INTER_BANK, cmfOrder.getInstCode().equals(fundsSource.getInstCode()));

        String expectTime = channelPayOrder.getExtension().get(ChannelInfoExtKey.EXPECT_TIME.getCode());
        if (StringUtil.isNotEmpty(expectTime)) {
            map.put(ChannelInfoExtKey.EXPECT_TIME.getCode(),
                DateUtil.parseDateLongFormat(expectTime));
        }
        String isSynchronized = channelPayOrder.getExtension().get(
            ChannelInfoExtKey.IS_SYNCHRONIZED.getCode());
        if (StringUtil.isNotEmpty(isSynchronized)) {
            map.put(ChannelInfoExtKey.IS_SYNCHRONIZED.getCode(), isSynchronized);
        }
        
        //填充其它参数
        Set<String> keySet = channelPayOrder.getExtension().keySet();
        for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
            String key = (String) it.next();
            if(!map.containsKey(key) && !keyMap.get(ROUTER_FUND_KEY).contains(key)){
                map.put(key, channelPayOrder.getExtension().get(key));
            }
        }

        if (logger.isInfoEnabled()) {
            logger.info("请求流水号[" + channelPayOrder.getPaymentSeqNo() + "],路由参数：" + map);
        }

        return map;
    }

}

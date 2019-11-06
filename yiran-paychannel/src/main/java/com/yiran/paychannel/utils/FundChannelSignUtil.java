package com.yiran.paychannel.utils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.domain.TmFundChannelExt;
import com.yiran.paychannel.enums.ExtensionKey;
import com.yiran.paychannel.vo.Extension;

import java.util.List;
import java.util.Map;

public class FundChannelSignUtil {
    private static final Logger logger = LoggerFactory.getLogger(FundChannelSignUtil.class);
    private static final String SIGNED_CHANNELS="signedChannels";
    private static final String MATCH_SIGNED_CHANNELS="matchSignedChannels";
    private static final String BANK_CARD_ID="bankCardId";
    private static final String SIGN_SOURCE="signSource";
    private static final String SUPPORT_MEMBER_ID="supportMemberId";
    /**
     * 资金渠道是否已签约
     * @param channel
     * @param param
     * @return
     */
    public static boolean fundChannelIsSigned(TmFundChannel channel, Map<String, ?> param){
        boolean signed=false;
        String signedChannels=(String) param.get(SIGNED_CHANNELS);
        if (StringUtils.isNotEmpty(signedChannels)){
            String[] channelCodes=signedChannels.split(",");
            for (String channelCode:channelCodes) {
                if (StringUtils.isNotEmpty(channelCode)
                        && channelCode.equalsIgnoreCase(channel.getFundChannelCode())){
                    signed=true;
                    break;
                }
            }
        }
        return signed;
    }

    public static String getSignedChannelsKey(){
        return SIGNED_CHANNELS;
    }
    public static String getBankCardIdKey(){
        return BANK_CARD_ID;
    }

    /**
     * 不需要进行对签约渠道匹配
     * @param param
     * @return
     */
    public static boolean notNeedMatchSignedChannels(Map<String, ?> param){
        if (StringUtils.isEmpty((String) param.get(MATCH_SIGNED_CHANNELS))){
            return true;
        }
        if ("n".equalsIgnoreCase((String) param.get(MATCH_SIGNED_CHANNELS))){
            return true;
        }
        return false;
    }
    public static void setNotNeedMatchSignedChannelsFlag(Extension extension){
        extension.add(MATCH_SIGNED_CHANNELS,"n");
    }
    public static void setNotNeedMatchSignedChannelsFlag(Map<String,String> extension){
        extension.put(MATCH_SIGNED_CHANNELS,"n");
    }
    /**
     * 是否对签约渠道进行包含匹配
     * @param param
     * @return
     */
    public static boolean includeMatchSignedChannels(Map<String, ?> param){
        if ("include".equalsIgnoreCase((String) param.get(MATCH_SIGNED_CHANNELS))){
            return true;
        }
        return false;
    }
    public static void setIncludeMatchSignedChannelsFlag(Extension extension){
        extension.add(MATCH_SIGNED_CHANNELS,"include");
    }
    public static void setIncludeMatchSignedChannelsFlag(Map<String, String> extension){
        extension.put(MATCH_SIGNED_CHANNELS,"include");
    }
    /**
     * 是否对签约渠道进行排查匹配
     * @param param
     * @return
     */
    public static boolean excludeMatchSignedChannels(Map<String, ?> param){
        if ("exclude".equalsIgnoreCase((String) param.get(MATCH_SIGNED_CHANNELS))){
            return true;
        }
        return false;
    }
    public static void setExcludeMatchSignedChannelsFlag(Extension extension){
        extension.add(MATCH_SIGNED_CHANNELS,"exclude");
    }

    /**
     * 是否异步签约
     * @param extension
     * @return
     */
    public static boolean isAsynSigned(Extension extension){
        if ("system".equalsIgnoreCase(extension.getValue(SIGN_SOURCE))){
            return true;
        }
        return false;
    }
    public static String getSignSourceKey(){
        return SIGN_SOURCE;
    }
    public static String getSupportMemberIdKey(){
        return SUPPORT_MEMBER_ID;
    }
    public static  boolean isBindFinish(Map<String, String> param){
        boolean isBindFinish=false;
        if ("true".equalsIgnoreCase(param.get(ExtensionKey.BIND_FINISH.key))){
            return true;
        }
        return isBindFinish;
    }
    public static boolean needFirstSigned(TmFundChannelApi api){
        if (api==null){
            return false;
        }
        List<TmFundChannelExt> exts=api.getExts();
        if (CollectionUtils.isEmpty(exts)){
            return false;
        }
        for (TmFundChannelExt ext:exts) {
            if ("signed".equalsIgnoreCase(ext.getAttrKey())){
                if ("y".equalsIgnoreCase(ext.getAttrValue())){
                    return true;
                }
            }
        }
        return false;
    }
}

package com.yiran.paychannel.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelMapper;
import com.yiran.paychannel.domain.TmApiNoMode;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.domain.TmFundChannelApiParam;
import com.yiran.paychannel.domain.TmFundChannelExt;
import com.yiran.paychannel.domain.TmFundChannelInst;
import com.yiran.paychannel.domain.TmFundChannelMaintain;
import com.yiran.paychannel.domain.TmFundChannelSettle;
import com.yiran.paychannel.domain.TrFcTargetInstRelation;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.service.ITmApiNoModeService;
import com.yiran.paychannel.service.ITmFundChannelApiParamService;
import com.yiran.paychannel.service.ITmFundChannelApiService;
import com.yiran.paychannel.service.ITmFundChannelExtService;
import com.yiran.paychannel.service.ITmFundChannelInstService;
import com.yiran.paychannel.service.ITmFundChannelMaintainService;
import com.yiran.paychannel.service.ITmFundChannelService;
import com.yiran.paychannel.service.ITmFundChannelSettleService;
import com.yiran.paychannel.service.ITrFcTargetInstRelationService;

import cn.hutool.json.JSONUtil;

import com.yiran.common.constant.RedisConstantsKey;
import com.yiran.common.support.Convert;
import com.yiran.common.utils.RedisUtils;

/**
 * 资金渠道 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmFundChannelServiceImpl implements ITmFundChannelService 
{
	private static final Logger logger = LoggerFactory.getLogger(TmFundChannelServiceImpl.class);
	@Autowired
	private TmFundChannelMapper tmFundChannelMapper;
	@Autowired
	private ITrFcTargetInstRelationService trFcTargetInstRelationService;
	@Autowired
	private ITmFundChannelApiService tmFundChannelApiService;
	@Autowired
	private ITmFundChannelApiParamService tmFundChannelApiParamService;
	@Autowired
	private ITmFundChannelExtService tmFundChannelExtService;
	@Autowired
	private ITmFundChannelMaintainService tmFundChannelMaintainService;
	@Autowired
	private ITmFundChannelInstService tmFundChannelInstService;
	@Autowired
	private ITmFundChannelSettleService tmFundChannelSettleService;
	@Autowired
	private ITmApiNoModeService tmApiNoModeService;
	/**
     * 查询资金渠道信息
     * 
     * @param fundChannelCode 资金渠道ID
     * @return 资金渠道信息
     */
    @Override
	public TmFundChannel selectTmFundChannelById(String fundChannelCode)
	{
    	TmFundChannel fc = tmFundChannelMapper.selectTmFundChannelById(fundChannelCode);
    	/** 目标机构 **/
        fc.setInstList(getSupportedTargetInst(fc.getFundChannelCode()));
        /** 资金源所属机构 **/
        fc.setInstitution(getInstitutionInfo(fc.getInstCode()));
        setChannelProperty(fc);
        /** 渠道维护时间配置 **/
        fc.setMaintainList(getMaintainInfo(fc.getFundChannelCode()));
        /** 结算信息 **/
        fc.setSettleInfo(getSettleInfo(fc.getFundChannelCode()));
        /** 接口配置信息 **/
        fc.setApiList(getApisInfo(fc.getFundChannelCode()));
        /** 是否支持查询，是否支持充退等 **/
        updateFlags(fc);
	    return fc;
	}
	
	/**
     * 查询资金渠道列表
     * 
     * @param tmFundChannel 资金渠道信息
     * @return 资金渠道集合
     */
	@Override
	public List<TmFundChannel> selectTmFundChannelList(TmFundChannel tmFundChannel)
	{
		List<TmFundChannel> list =new ArrayList<TmFundChannel>();
		/*//从缓存获取
		Object obj = RedisUtils.get(RedisConstantsKey.CHANNEL_LIST);
		if(obj != null){
			list = JSONUtil.toList(JSONUtil.parseArray(RedisUtils.get(RedisConstantsKey.CHANNEL_LIST)), TmFundChannel.class);
		}
		if(list.isEmpty()){//缓存没有
*/			list = tmFundChannelMapper.selectTmFundChannelList(tmFundChannel);
			//logger.info("资金渠道信息加入redis缓存:{}"+JSONUtil.toJsonPrettyStr(list));
			//RedisUtils.set(RedisConstantsKey.CHANNEL_LIST, JSONUtil.toJsonPrettyStr(list));
			//logger.info("资金渠道信息加入redis缓存完毕....");
		//}
	    return list;
	}
	
    /**
     * 新增资金渠道
     * 
     * @param tmFundChannel 资金渠道信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannel(TmFundChannel tmFundChannel)
	{
	    return tmFundChannelMapper.insertTmFundChannel(tmFundChannel);
	}
	
	/**
     * 修改资金渠道
     * 
     * @param tmFundChannel 资金渠道信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannel(TmFundChannel tmFundChannel)
	{
	    return tmFundChannelMapper.updateTmFundChannel(tmFundChannel);
	}

	/**
     * 删除资金渠道对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelByIds(String ids)
	{
		return tmFundChannelMapper.deleteTmFundChannelByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<TmFundChannel> getAvailableFundChannels(String targetInst, List<String> payModeList) {
		
		List<TmFundChannel> fundChannelList = tmFundChannelMapper.getAvailableFundChannels(targetInst,payModeList);
		loadFundChannelsConfigure(fundChannelList);
		return fundChannelList;
	}

	private void loadFundChannelsConfigure(List<TmFundChannel> fundChannelList) {
		loadFundChannelsConfigure(fundChannelList, true);
	}

	private void loadFundChannelsConfigure(List<TmFundChannel> fundChannelList, boolean full) {
		if (fundChannelList == null)
            return;
        for (TmFundChannel fs : fundChannelList) {
            loadFundChannelConfigure(fs, full);
        }
	}

	private void loadFundChannelConfigure(TmFundChannel fc, boolean full) {
		if (null == fc) {
            return;
        }
        /** 目标机构 **/
        fc.setInstList(getSupportedTargetInst(fc.getFundChannelCode()));
        /** 资金源所属机构 **/
        fc.setInstitution(getInstitutionInfo(fc.getInstCode()));
        setChannelProperty(fc);
        /** 渠道维护时间配置 **/
        fc.setMaintainList(getMaintainInfo(fc.getFundChannelCode()));
        if (full) {
            /** 结算信息 **/
            fc.setSettleInfo(getSettleInfo(fc.getFundChannelCode()));
            /** 接口配置信息 **/
            fc.setApiList(getApisInfo(fc.getFundChannelCode()));
            /** 是否支持查询，是否支持充退等 **/
            updateFlags(fc);
        }
		
	}

	private void updateFlags(TmFundChannel fc) {
		if (fc.getApiList()!= null) {
            for (TmFundChannelApi api : fc.getApiList()) {
                /** 是否支持查询*/
                if (!fc.isQuerySupported()
                    && (FundChannelApiType.BATCH_QUERY.equals(api.getApiType()) || FundChannelApiType.SINGLE_QUERY
                        .equals(api.getApiType()))) {
                    fc.setQuerySupported(true);
                }

                /** 是否支持充退 **/
                if (!fc.isRefundSupported()
                    && (FundChannelApiType.BATCH_REFUND.equals(api.getApiType())
                        || FundChannelApiType.SINGLE_REFUND.equals(api.getApiType())
                        || FundChannelApiType.BATCH_FILE_REFUND.equals(api.getApiType()) || FundChannelApiType.MANUAL_REFUND
                            .equals(api.getApiType()))) {
                    fc.setRefundSupported(true);
                }

                /** 是否支持手工充退 **/
                if (!fc.isManualRefundSupported()
                    && (FundChannelApiType.MANUAL_REFUND.equals(api.getApiType()))) {
                    fc.setManualRefundSupported(true);
                }
            }
        }
	}

	private List<TmFundChannelApi> getApisInfo(String fundChannelCode) {
		TmFundChannelApi tmFundChannelApi = new TmFundChannelApi();
		tmFundChannelApi.setFundChannelCode(fundChannelCode);
		List<TmFundChannelApi> fundChannelApiList = tmFundChannelApiService.selectTmFundChannelApiList(tmFundChannelApi);
		loadApisConfigure(fundChannelApiList);
		return fundChannelApiList;
	}

	private void loadApisConfigure(List<TmFundChannelApi> fundChannelApis) {
        if (fundChannelApis == null)
            return;
        for (TmFundChannelApi api : fundChannelApis) {
            loadApiConfigure(api);
        }
    }

    private void loadApiConfigure(TmFundChannelApi api) {
        /** 机构订单生成方式 **/
        api.setApiNoMode(getOrderNoMode(api.getApiNoModeId()));
        TmFundChannelExt tmFundChannelExt =new TmFundChannelExt();
		tmFundChannelExt.setApiCode(api.getApiCode());
		List<TmFundChannelExt> extList = tmFundChannelExtService.selectTmFundChannelExtList(tmFundChannelExt);
        api.setExts(extList);
        //添加api参数
        TmFundChannelApiParam tmFundChannelApiParam = new TmFundChannelApiParam();
        tmFundChannelApiParam.setApiCode(api.getApiCode());
        List<TmFundChannelApiParam> apiParamList = tmFundChannelApiParamService.selectTmFundChannelApiParamList(tmFundChannelApiParam);
        api.setParamList(apiParamList);
    }
    

	private TmApiNoMode getOrderNoMode(Integer apiNoModeId) {
		return tmApiNoModeService.selectTmApiNoModeById(apiNoModeId);
	}

	private TmFundChannelSettle getSettleInfo(String fundChannelCode) {
		TmFundChannelSettle tmFundChannelSettle = new TmFundChannelSettle();
		tmFundChannelSettle.setFundChannelCode(fundChannelCode);
		List<TmFundChannelSettle> settleList = tmFundChannelSettleService.selectTmFundChannelSettleList(tmFundChannelSettle);
		if(settleList.size()>0){
			return settleList.get(0);
		}
		return null;
	}

	private List<TmFundChannelMaintain> getMaintainInfo(String fundChannelCode) {
		TmFundChannelMaintain tmFundChannelMaintain = new TmFundChannelMaintain();
		tmFundChannelMaintain.setFundChannelCode(fundChannelCode);
		return tmFundChannelMaintainService.selectTmFundChannelMaintainList(tmFundChannelMaintain);
	}

	private void setChannelProperty(TmFundChannel fc) {
		TmFundChannelExt tmFundChannelExt =new TmFundChannelExt();
		tmFundChannelExt.setFundChannelCode(fc.getFundChannelCode());
		List<TmFundChannelExt> extList = tmFundChannelExtService.selectTmFundChannelExtList(tmFundChannelExt);
		fc.setExtList(extList);
	}

	private TmFundChannelInst getInstitutionInfo(String instCode) {
		
		return tmFundChannelInstService.selectTmFundChannelInstById(instCode);
	}

	private List<TrFcTargetInstRelation> getSupportedTargetInst(String fundChannelCode) {
		TrFcTargetInstRelation trFcTargetInstRelation = new TrFcTargetInstRelation();
		trFcTargetInstRelation.setFundChannelCode(fundChannelCode);
		return trFcTargetInstRelationService.selectTrFcTargetInstRelationList(trFcTargetInstRelation);
	}

	/**
	 * 根据渠道编号获取渠道信息
	 */
	@Override
	public TmFundChannel getFundChannel(String fundChannelCode) {
		return selectTmFundChannelById(fundChannelCode);
	}

	@Override
	public List<TmFundChannel> loadAllFundChannels() {
        List<TmFundChannel> result = tmFundChannelMapper.loadAll();
        loadFundChannelsConfigure(result, true);
        return result;
	}

	
	
	
}

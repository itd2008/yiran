package com.yiran.paychannel.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.paychannel.mapper.TmFundChannelApiMapper;
import com.yiran.paychannel.domain.TmApiNoMode;
import com.yiran.paychannel.domain.TmFundChannel;
import com.yiran.paychannel.domain.TmFundChannelApi;
import com.yiran.paychannel.domain.TmFundChannelApiParam;
import com.yiran.paychannel.domain.TmFundChannelExt;
import com.yiran.paychannel.enums.ControlRequestType;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.enums.RequestType;
import com.yiran.paychannel.service.ITmApiNoModeService;
import com.yiran.paychannel.service.ITmFundChannelApiParamService;
import com.yiran.paychannel.service.ITmFundChannelApiService;
import com.yiran.paychannel.service.ITmFundChannelExtService;
import com.yiran.common.support.Convert;

/**
 * 资金渠道接口 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmFundChannelApiServiceImpl implements ITmFundChannelApiService 
{
	@Autowired
	private TmFundChannelApiMapper tmFundChannelApiMapper;
	@Autowired
	private ITmFundChannelApiParamService tmFundChannelApiParamService;
	@Autowired
	private ITmFundChannelExtService tmFundChannelExtService;
	@Autowired
	private ITmApiNoModeService tmApiNoModeService;
	/** 控制请求类型与接口类型MAP */
    public static Map<String, List<String>>       apiTypeMap              = new HashMap<String, List<String>>();

    public static List<FundChannelApiType>                    queryControlApiTypeList = new ArrayList<FundChannelApiType>();

    /** 查询类型与查询对应类型map */
    public static Map<FundChannelApiType, FundChannelApiType> queryApiTypeMap         = new HashMap<FundChannelApiType, FundChannelApiType>();
    public static List<String> combine(String... apiType) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < apiType.length; i++) {
            list.add(apiType[i]);
        }
        return list;
    }
    //初始化
    static {
        apiTypeMap.put(RequestType.FUND_IN.getCode(),
            combine(FundChannelApiType.DEBIT.getCode(), FundChannelApiType.SINGLE_PAY.getCode()));
        apiTypeMap.put(
            RequestType.FUND_OUT.getCode(),
            combine(FundChannelApiType.SINGLE_PAY.getCode(), FundChannelApiType.BATCH_PAY.getCode(),
                FundChannelApiType.BATCH_FILE_PAY.getCode()));
        apiTypeMap.put(
            RequestType.REFUND.getCode(),
            combine(FundChannelApiType.SINGLE_REFUND.getCode(), FundChannelApiType.MANUAL_REFUND.getCode(),
                FundChannelApiType.BATCH_FILE_REFUND.getCode(), FundChannelApiType.BATCH_REFUND.getCode()));
        apiTypeMap.put(RequestType.PRE_AUTH_DONE.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE.getCode()));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REQUEST.getCode(),
            combine(FundChannelApiType.PRE_AUTH.getCode()));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REVERSAL.getCode(),
            combine(FundChannelApiType.PRE_AUTH_REVERSAL.getCode()));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_REVERSAL_UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_UNDO_REVERSAL.getCode()));
        apiTypeMap.put(ControlRequestType.PRE_AUTH_UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_UNDO.getCode()));
        apiTypeMap.put(ControlRequestType.REVERSAL.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE_REVERSAL.getCode()));
        apiTypeMap.put(ControlRequestType.REVERSAL_UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE_UNDO_REVERSAL.getCode()));
        apiTypeMap.put(ControlRequestType.UNDO.getCode(),
            combine(FundChannelApiType.PRE_AUTH_DONE_UNDO.getCode()));
        apiTypeMap.put(ControlRequestType.AUTHENTICATE.getCode(),
            combine(FundChannelApiType.AUTHENTICATE.getCode()));
        apiTypeMap.put(ControlRequestType.TERMINATE.getCode(),
            combine(FundChannelApiType.TERMINATE.getCode()));
        apiTypeMap.put(ControlRequestType.DEBIT_ADVANCE.getCode(),
            combine(FundChannelApiType.DEBIT_ADVANCE.getCode()));
        apiTypeMap.put(ControlRequestType.AUTH_ADVANCE.getCode(),
                combine(FundChannelApiType.AUTH_ADVANCE.getCode()));
        apiTypeMap.put(ControlRequestType.SEND_MESSAGE.getCode(),
            combine(FundChannelApiType.SEND_MESSAGE.getCode()));
        apiTypeMap.put(ControlRequestType.NOTIFY.getCode(), combine(FundChannelApiType.NOTIFY.getCode()));
        //add by zhanghs 2016-05-09
        apiTypeMap.put(ControlRequestType.UPDATE_REPAYMENT_PLAN.getCode(), combine(FundChannelApiType.UPDATE_REPAYMENT_PLAN.getCode()));

        queryControlApiTypeList.add(FundChannelApiType.AUTHENTICATE_QUERY);
        queryControlApiTypeList.add(FundChannelApiType.TERMINATE_QUERY);
        queryControlApiTypeList.add(FundChannelApiType.DEBIT_ADVANCE_QUERY);

        queryApiTypeMap.put(FundChannelApiType.AUTHENTICATE_QUERY, FundChannelApiType.AUTHENTICATE);
        queryApiTypeMap.put(FundChannelApiType.TERMINATE_QUERY, FundChannelApiType.TERMINATE);
        queryApiTypeMap.put(FundChannelApiType.DEBIT_ADVANCE_QUERY, FundChannelApiType.DEBIT_ADVANCE);
    }

	/**
     * 查询资金渠道接口信息
     * 
     * @param apiCode 资金渠道接口ID
     * @return 资金渠道接口信息
     */
    @Override
	public TmFundChannelApi selectTmFundChannelApiById(String apiCode)
	{
    	TmFundChannelApi api = tmFundChannelApiMapper.selectTmFundChannelApiById(apiCode);
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
	    return api;
	}
    
    private TmApiNoMode getOrderNoMode(Integer apiNoModeId) {
		return tmApiNoModeService.selectTmApiNoModeById(apiNoModeId);
	}
	
	/**
     * 查询资金渠道接口列表
     * 
     * @param tmFundChannelApi 资金渠道接口信息
     * @return 资金渠道接口集合
     */
	@Override
	public List<TmFundChannelApi> selectTmFundChannelApiList(TmFundChannelApi tmFundChannelApi)
	{
	    return tmFundChannelApiMapper.selectTmFundChannelApiList(tmFundChannelApi);
	}
	
    /**
     * 新增资金渠道接口
     * 
     * @param tmFundChannelApi 资金渠道接口信息
     * @return 结果
     */
	@Override
	public int insertTmFundChannelApi(TmFundChannelApi tmFundChannelApi)
	{
	    return tmFundChannelApiMapper.insertTmFundChannelApi(tmFundChannelApi);
	}
	
	/**
     * 修改资金渠道接口
     * 
     * @param tmFundChannelApi 资金渠道接口信息
     * @return 结果
     */
	@Override
	public int updateTmFundChannelApi(TmFundChannelApi tmFundChannelApi)
	{
	    return tmFundChannelApiMapper.updateTmFundChannelApi(tmFundChannelApi);
	}

	/**
     * 删除资金渠道接口对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmFundChannelApiByIds(String ids)
	{
		return tmFundChannelApiMapper.deleteTmFundChannelApiByIds(Convert.toStrArray(ids));
	}

	@Override
	public boolean filterApi(List<TmFundChannelApi> apis, String requestType) {
		if (apis == null || apis.size() == 0) {
            return false;
        }
        if (requestType != null) {
            Iterator<TmFundChannelApi> iterator = apis.iterator();
            while (iterator.hasNext()) {
                TmFundChannelApi api = iterator.next();
                if (!apiTypeMap.get(requestType).contains(api.getApiType())) {
                    iterator.remove();
                }
            }
        }
		return (isExistApi(apis));
	}
	
	private boolean isExistApi(List<TmFundChannelApi> apis) {
        return (apis != null && apis.size() > 0);
    }

	@Override
	public boolean filterWithApiType(TmFundChannel fundChannel, FundChannelApiType apiType) {
		return filterWithApiType(fundChannel.getApiList(), apiType);
	}

	private boolean filterWithApiType(List<TmFundChannelApi> apis, FundChannelApiType apiType) {
		if (apis == null || apis.size() == 0) {
            return false;
        }
        if (apiType != null) {
            Iterator<TmFundChannelApi> iterator = apis.iterator();
            while (iterator.hasNext()) {
                TmFundChannelApi api = iterator.next();
                if (!apiType.getCode().equals(api.getApiType()))
                    iterator.remove();
            }
        }
        return (isExistApi(apis));
	}

	@Override
	public TmFundChannelApi getFundChannelApi(String apiCode) {
		return tmFundChannelApiMapper.selectTmFundChannelApiById(apiCode);
	}
}

package com.yiran.paychannel.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yiran.paychannel.mapper.TmUnityResultCodeMapper;
import com.yiran.paychannel.domain.TmApiResultCode;
import com.yiran.paychannel.domain.TmUnityResultCode;
import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.service.ITmApiResultCodeService;
import com.yiran.paychannel.service.ITmUnityResultCodeService;
import com.yiran.paychannel.utils.CommonUtil;
import com.netfinworks.common.lang.StringUtil;
import com.netfinworks.common.util.VelocityUtil;
import com.netfinworks.validate.exception.ValidationException;
import com.yiran.common.support.Convert;

/**
 * 统一结果代码 服务层实现
 * 
 * @author yiran
 * @date 2019-04-19
 */
@Service
public class TmUnityResultCodeServiceImpl implements ITmUnityResultCodeService 
{
	 private Logger           logger = LoggerFactory.getLogger(TmUnityResultCodeServiceImpl.class);
	private final static String EXISTENCE = "0";
	
	private final static String NON_EXISTENT = "1";
	
	@Autowired
	private TmUnityResultCodeMapper tmUnityResultCodeMapper;
	@Autowired
	private ITmApiResultCodeService tmApiResultCodeService;

	/**
     * 查询统一结果代码信息
     * 
     * @param unityResultCode 统一结果代码ID
     * @return 统一结果代码信息
     */
    @Override
	public TmUnityResultCode selectTmUnityResultCodeById(String unityResultCode)
	{
	    return tmUnityResultCodeMapper.selectTmUnityResultCodeById(unityResultCode);
	}
	
	/**
     * 查询统一结果代码列表
     * 
     * @param tmUnityResultCode 统一结果代码信息
     * @return 统一结果代码集合
     */
	@Override
	public List<TmUnityResultCode> selectTmUnityResultCodeList(TmUnityResultCode tmUnityResultCode)
	{
	    return tmUnityResultCodeMapper.selectTmUnityResultCodeList(tmUnityResultCode);
	}
	
    /**
     * 新增统一结果代码
     * 
     * @param tmUnityResultCode 统一结果代码信息
     * @return 结果
     */
	@Override
	public int insertTmUnityResultCode(TmUnityResultCode tmUnityResultCode)
	{
	    return tmUnityResultCodeMapper.insertTmUnityResultCode(tmUnityResultCode);
	}
	
	/**
     * 修改统一结果代码
     * 
     * @param tmUnityResultCode 统一结果代码信息
     * @return 结果
     */
	@Override
	public int updateTmUnityResultCode(TmUnityResultCode tmUnityResultCode)
	{
	    return tmUnityResultCodeMapper.updateTmUnityResultCode(tmUnityResultCode);
	}

	/**
     * 删除统一结果代码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmUnityResultCodeByIds(String ids)
	{
		return tmUnityResultCodeMapper.deleteTmUnityResultCodeByIds(Convert.toStrArray(ids));
	}

	@Override
	public String checkUnityResultCode(String unityResultCode) {
		int count = tmUnityResultCodeMapper.checkUnityResultCode(unityResultCode);
		if(count>0){
			return EXISTENCE;//存在
		}else{
			return NON_EXISTENT;//不存在
		}
	}

	@Override
	public TmApiResultCode parse(String fundChannelCode, FundChannelApiType apiType, String resultCode,
			String resultSubCode, Map<String, Object> extension, String memo) {
		Assert.notNull(resultCode, "结果代码不能为空");
		try {
            // 1、根据正常查找
            TmApiResultCode apiResultCode = load(fundChannelCode, apiType, resultCode, resultSubCode,
                extension);

            if (apiResultCode != null) {
                return apiResultCode;
            }

            // 2、结果为空则保存
            apiResultCode = new TmApiResultCode(fundChannelCode, apiType.getCode(), resultCode, resultSubCode);
            apiResultCode.setMemo(memo);
            tmApiResultCodeService.store(apiResultCode);

            return apiResultCode;
        } catch (ValidationException e) {
            logger.error(
                "[" + fundChannelCode + "][" + resultCode + "][" + resultSubCode + "]校验异常", e);
            return new TmApiResultCode(fundChannelCode, apiType.getCode(), resultCode, resultSubCode);
        }
	}
	
	/**
     * 根据
     * @param fundChannelCode
     * @param apiType
     * @param resultCode
     * @param resultSubCode
     * @param extension
     * @return
     */
    @SuppressWarnings("unchecked")
    private TmApiResultCode load(final String fundChannelCode, final FundChannelApiType apiType,
                               final String resultCode, String resultSubCode,
                               Map<String, Object> extension) throws ValidationException {
        // 1、获取条件队列
        final String tempResultSubCode = StringUtil.isBlank(resultSubCode) ? null : resultSubCode;
        String key = CommonUtil.key(fundChannelCode, apiType.getCode(), resultCode,
            tempResultSubCode);
        List<TmApiResultCode> codeList = tmApiResultCodeService.loadByChannelAndResult(fundChannelCode, apiType, resultCode,
                tempResultSubCode);

        // 2、为空则直接返回空
        if (CollectionUtils.isEmpty(codeList)) {
            logger.warn("[" + key + "]无渠道结果代码配置");
            return null;
        }

        // 3、结果过滤
        TmApiResultCode filterResult = filter(codeList, apiType, extension);

        return filterResult;
    }

    /**
     * 过滤结果
     * @param codeList
     * @param apiType
     * @param extension
     * @return
     * @throws ValidationException
     */
    private TmApiResultCode filter(List<TmApiResultCode> codeList, FundChannelApiType apiType,
                                 Map<String, Object> extension) throws ValidationException {
        List<TmApiResultCode> tempCodeList = new ArrayList<TmApiResultCode>();
        for (TmApiResultCode code : codeList) {
            if (code.getApiType() == null || code.getApiType().equals(apiType.getCode())) {
                if (StringUtil.isBlank(code.getExpression())
                    || VelocityUtil.isTrue(code.getExpression(), extension)) {
                    tempCodeList.add(code);
                }
            }
        }

        // 1、过滤结果为空则作为未知返回
        if (CollectionUtils.isEmpty(tempCodeList)) {
            return null;
        }

        // 2、多义性则作为未知返回
        if (tempCodeList.size() > 1) {
            throw new ValidationException("存在多义性");
        }

        return tempCodeList.get(0);
    }
	
}

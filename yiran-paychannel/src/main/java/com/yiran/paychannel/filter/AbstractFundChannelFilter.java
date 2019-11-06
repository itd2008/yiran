package com.yiran.paychannel.filter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.paychannel.constants.BasicConstant;
import com.yiran.paychannel.domain.TmFundChannelExt;
import com.yiran.paychannel.enums.FilterType;
import com.yiran.paychannel.enums.MatchType;
import com.yiran.paychannel.enums.YesNo;
import com.yiran.paychannel.service.ITmFundChannelService;


/**
 * 
 * <p>过滤抽象方法</p>
 */
public abstract class AbstractFundChannelFilter<T> implements FundChannelFilter<T>, BasicConstant {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractFundChannelFilter.class);

    @Autowired
	private ITmFundChannelService tmFundChannelService;

    /**
     * 获取当前过滤类型
     * @return
     */
    protected abstract FilterType getCuurentFileterType();

    /**
     * 扩充参数信息
     * @param param
     * @param t
     */
    protected abstract void addParam(Map<String, ?> param, T element);

    /**
     * 判断传入参数是否匹配特性定义
     * @param mainCode
     * @param exts
     * @param param
     * @return
     */
    protected boolean match(String mainCode, List<TmFundChannelExt> exts, Map<String, ?> param) {
        for (TmFundChannelExt ext : exts) {

            if (!YesNo.YES.getCode().equals(ext.getNeedMatch())) {
                continue;
            }

            //配置出错,直接返回路由不通过
            if (YesNo.YES.getCode().equals(ext.getNeedMatch()) && ext.getMatchType() == null) {
                logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                    getValue(ext.getAttrKey(), param), "配置出错,直接返回路由不通过");
                return false;
            }

            switch (MatchType.getByCode(ext.getMatchType())) {
                case IN:
                    if (!any(ext.getAttrValue(), getValue(ext.getAttrKey(), param))) {
                        logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                            getValue(ext.getAttrKey(), param), ext.getMatchType());
                        return false;
                    }
                    break;
                case NOTIN:
                    if (any(ext.getAttrValue(), getValue(ext.getAttrKey(), param))) {
                        logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                            getValue(ext.getAttrKey(), param), ext.getMatchType());
                        return false;
                    }
                    break;
                case BETWEEN:
                    if (!between(ext.getAttrValue(), getValue(ext.getAttrKey(), param))) {
                        logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                            getValue(ext.getAttrKey(), param), ext.getMatchType());
                        return false;
                    }
                    break;
                case GREETER:
                    if (!greeter(ext.getAttrValue(), getValue(ext.getAttrKey(), param))) {
                        logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                            getValue(ext.getAttrKey(), param), ext.getMatchType());
                        return false;
                    }
                    break;
                case LOWER:
                    if (!lower(ext.getAttrValue(), getValue(ext.getAttrKey(), param))) {
                        logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                            getValue(ext.getAttrKey(), param), ext.getMatchType());
                        return false;
                    }
                    break;
                case NOTNULL:
                    if (StringUtils.isEmpty(getValue(ext.getAttrKey(), param))) {
                        logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                            getValue(ext.getAttrKey(), param), ext.getMatchType());
                        return false;
                    }
                    break;
                case NULL:
                    if (StringUtils.isNotEmpty(getValue(ext.getAttrKey(), param))) {
                        logger(mainCode, ext.getAttrKey(), ext.getAttrValue(),
                            getValue(ext.getAttrKey(), param), ext.getMatchType());
                        return false;
                    }
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    private String getValue(String key, Map<String, ?> param) {
        return (String) (param.get(key) == null ? "" : param.get(key));
    }

    /**
     * 输出日志
     * @param mainCode
     * @param key
     * @param value
     * @param compareValue
     */
    private void logger(String mainCode, String key, String value, String compareValue,
                        String matchType) {
        if (logger.isInfoEnabled()) {
            String str = "过滤" + mainCode + "不匹配,特性:" + key + ",特性值:" + value + ",比较值"
                         + compareValue + ",比较类型" + matchType + ";";
            logger.info(str);
        }
    }

    /**
     * 比较包含
     * @param values
     * @param compareValues
     * @return
     */
    private boolean any(String values, String compareValues) {
        if (StringUtils.isEmpty(values) || StringUtils.isEmpty(compareValues)) {
            return false;
        }

        String[] compareValuesList = compareValues.split(CHAR_COMMA);
        String[] valuesList = values.split(CHAR_COMMA);

        for (String from : valuesList) {
            for (String to : compareValuesList) {
                if (to.equals(from))
                    return true;
            }
        }
        return false;
    }
    
    /**
     * 比较值大于指定值
     * @param values
     * @param compareValues
     * @return
     */
    private boolean greeter(String values, String compareValues) {
        if (StringUtils.isEmpty(values) || StringUtils.isEmpty(compareValues)) {
            return false;
        }

        BigDecimal value = new BigDecimal(values);
        BigDecimal compare = new BigDecimal(compareValues);

        return compare.compareTo(value) >= 0;
    }

    /**
     * 比较值小于指定值
     * @param values
     * @param compareValues
     * @return
     */
    private boolean lower(String values, String compareValues) {
        if (StringUtils.isEmpty(values) || StringUtils.isEmpty(compareValues)) {
            return false;
        }

        BigDecimal value = new BigDecimal(values);
        BigDecimal compare = new BigDecimal(compareValues);

        return compare.compareTo(value) <= 0;
    }

    /**
     * 比较值在指定值之间
     * @param values
     * @param compareValues
     * @return
     */
    private boolean between(String values, String compareValues) {
        if (StringUtils.isEmpty(values) || StringUtils.isEmpty(compareValues)) {
            return false;
        }

        String[] valuesList = values.split(CHAR_COMMA);
        BigDecimal lower = new BigDecimal(valuesList[0]);
        BigDecimal greeter = new BigDecimal(valuesList[1]);
        BigDecimal compare = new BigDecimal(compareValues);
        return compare.compareTo(lower) >= 0 && compare.compareTo(greeter) <= 0;
    }

}

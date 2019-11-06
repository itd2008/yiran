package com.yiran.payorder.common;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.payorder.constant.BasicConstant;
import com.yiran.payorder.exception.AppRuntimeException;


/**
 * <p>CXF远程访问代理</p>
 */
public abstract class AbstractRemoteProxy<T> implements BasicConstant {
    protected static final Logger        logger         = LoggerFactory.getLogger(AbstractRemoteProxy.class);

    /** 超时时间，默认15秒 */
    private long                         defaultTimeout = 60000;

    /** 缓存映射列表 */
    protected static Map<String, Object> cachedMapping  = new HashMap<String, Object>();

    /**
     * 获取访问对象
     * @return
     */
    @SuppressWarnings("unchecked")
    protected T getTarget(String fundChannelCode,FundChannelApiType apiType,String apiUrl, Long timeout) {
        String compApiUrl = complentApiUrl(apiUrl);
        String cachedKey = complentApiKey(fundChannelCode,apiType,compApiUrl);
        if (cachedMapping.containsKey(cachedKey)) {
            return (T) cachedMapping.get(cachedKey);
        }

        // 获取对象泛型class
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0];
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(entityClass);
        factory.setAddress(compApiUrl);
        T remoteService = (T) factory.create();
        if (remoteService == null) {
            throw new AppRuntimeException("远程服务创建失败:apiUrl=" + compApiUrl);
        }
        configPolicy(remoteService, timeout);

        //缓存服务实例
        cachedMapping.put(cachedKey, remoteService);
        return remoteService;
    }

    /**
     * 配置远程服务对象
     * @param service
     */
    private void configPolicy(Object service, Long timeout) {
        Client clientP = ClientProxy.getClient(service);
        clientP.setThreadLocalRequestContext(true);
        HTTPConduit http = (HTTPConduit) clientP.getConduit();
        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
        if (timeout == null) {
            httpClientPolicy.setConnectionTimeout(defaultTimeout);
            httpClientPolicy.setReceiveTimeout(defaultTimeout);
        } else {
            httpClientPolicy.setConnectionTimeout(timeout);
            httpClientPolicy.setReceiveTimeout(timeout);
        }
        httpClientPolicy.setAllowChunking(false);
        http.setClient(httpClientPolicy);
    }

    /**
     *  补足地址前缀
     * @param apiUrl
     * @return
     */
    protected String complentApiUrl(String apiUrl) {
        if (apiUrl.startsWith(PREFIX_API_URI)) {
            return apiUrl;
        }
        return PREFIX_API_URI + apiUrl;
    }

    /**
     * 补全缓存urlkey值
     * 返回: ICBC10101_DB_http://.../singleApply
     * @param apiCode
     * @param apiUrl
     * @return
     */
    protected String complentApiKey(String fundChannelCode, FundChannelApiType apiType,
                                    String apiUrl) {
        return fundChannelCode + SPLIT_TAG + apiType.getCode() + SPLIT_TAG + apiUrl;
    }

    public void setDefaultTimeout(long defaultTimeout) {
        this.defaultTimeout = defaultTimeout;
    }

}

package com.yiran.api.server.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yiran.api.server.PropertyHelper;
import com.yiran.common.config.Global;
@Service("propertyHelper")
public class DefaultPropertyHelper implements PropertyHelper {
	private Logger             logger = LoggerFactory.getLogger(DefaultPropertyHelper.class);
	 /** 配置文件根路径地址 */
    private String                  configFileRootPath = Global.getConfigFileRootPath();
    /** 配置文件名 */
    private static final String     BASE_FILE_NAME = "app.properties";

    private static final String     SPLIT_CHARATER = "/";

    private Map<String, Properties> properties     = new HashMap<String, Properties>();

    public Properties getProperties(String fundChannelCode) {
        if (properties.get(fundChannelCode) != null) {
            return properties.get(fundChannelCode);
        }
        Properties prop = initProperty(fundChannelCode);
        properties.put(fundChannelCode, prop);
        return prop;

    }

    private Properties initProperty(String fundChannelCode) {
        logger.info("init" + fundChannelCode);
        Properties props = new Properties();
        synchronized (properties) {
            try {
                if (properties.get(fundChannelCode) != null)
                    return properties.get(fundChannelCode);

                File f = new File(configFileRootPath + SPLIT_CHARATER + fundChannelCode
                                  + SPLIT_CHARATER + BASE_FILE_NAME);
                InputStreamReader read = new InputStreamReader(new FileInputStream(f), "UTF-8");
                props.load(read);
            } catch (Exception e) {
                throw new RuntimeException("获取配置文件失败:" + configFileRootPath + SPLIT_CHARATER
                                           + fundChannelCode + SPLIT_CHARATER + BASE_FILE_NAME, e);
            }
        }
        return props;
    }

    public String getConfigFileRootPath() {
        return configFileRootPath;
    }

    public void setConfigFileRootPath(String configFileRootPath) {
        this.configFileRootPath = configFileRootPath;
    }

	@Override
	public Properties getProperties(String fundChannelCode,String defaultChannelCode) {
		Properties proper = null;
		try{
			proper =getProperties(fundChannelCode);
		}catch (RuntimeException e){
			proper =getProperties(defaultChannelCode);
		}catch (Exception e){
			proper =getProperties(defaultChannelCode);
		}
		
		if(proper==null){
			proper =getProperties(defaultChannelCode);
		}
		return proper;
	}

}

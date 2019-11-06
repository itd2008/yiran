package com.yiran.weixin.utils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
/**
 * ApplicationContextUtil工具类，获取bean对象
 * @author pandaa
 *
 */
@Component
public class  ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> cls){
        return applicationContext.getBean(cls);
    }

    public static Object getBeanObj(String beanName){
        return applicationContext.getBean(beanName);
    }
}
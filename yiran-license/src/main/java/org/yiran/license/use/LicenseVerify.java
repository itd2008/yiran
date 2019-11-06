package org.yiran.license.use;

import de.schlichtherle.license.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yiran.license.common.CustomKeyStoreParam;

import java.io.File;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.prefs.Preferences;

/**
 * License校验类
 *
 * @date 2018/4/20
 * @since 1.0.0
 */
public class LicenseVerify {
    private static Logger logger = LogManager.getLogger(LicenseVerify.class);

    /**
     * 安装License证书
     * @author zifangsky
     * @date 2018/4/20 16:26
     * @since 1.0.0
     */
    public synchronized LicenseContent install(LicenseVerifyParam param){
        LicenseContent result = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //1. 安装证书
        try{
            LicenseManager licenseManager = LicenseManagerHolder.getInstance(initLicenseParam(param));
            licenseManager.uninstall();

            result = licenseManager.install(new File(param.getLicensePath()));
            logger.info(MessageFormat.format("证书安装成功，证书有效期：{0} - {1}",format.format(result.getNotBefore()),format.format(result.getNotAfter())));
        }catch (Exception e){
            logger.error("证书安装失败！",e);
        }

        return result;
    }

    /**
     * 校验License证书
     * @author zifangsky
     * @date 2018/4/20 16:26
     * @since 1.0.0
     * @return boolean
     */
    public boolean verify(){
        LicenseManager licenseManager = LicenseManagerHolder.getInstance(null);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //2. 校验证书
        try {
            LicenseContent licenseContent = licenseManager.verify();
//            System.out.println(licenseContent.getSubject());

            logger.info(MessageFormat.format("证书校验通过，证书有效期：{0} - {1}",format.format(licenseContent.getNotBefore()),format.format(licenseContent.getNotAfter())));
            return true;
        }catch (Exception e){
            logger.error("证书校验失败！",e);
            return false;
        }
    }

    /**
     * 初始化证书生成参数
     * @author zifangsky
     * @date 2018/4/20 10:56
     * @since 1.0.0
     * @param param License校验类需要的参数
     * @return de.schlichtherle.license.LicenseParam
     */
    private LicenseParam initLicenseParam(LicenseVerifyParam param){
        Preferences preferences = Preferences.userNodeForPackage(LicenseVerify.class);

        CipherParam cipherParam = new DefaultCipherParam(param.getStorePass());

        KeyStoreParam publicStoreParam = new CustomKeyStoreParam(LicenseVerify.class
                ,param.getPublicKeysStorePath()
                ,param.getPublicAlias()
                ,param.getStorePass()
                ,null);

        return new DefaultLicenseParam(param.getSubject()
                ,preferences
                ,publicStoreParam
                ,cipherParam);
    }

}

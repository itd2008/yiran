package org.yiran.license.service;

import de.schlichtherle.license.CipherParam;
import de.schlichtherle.license.DefaultCipherParam;
import de.schlichtherle.license.DefaultLicenseParam;
import de.schlichtherle.license.KeyStoreParam;
import de.schlichtherle.license.LicenseContent;
import de.schlichtherle.license.LicenseManager;
import de.schlichtherle.license.LicenseParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.yiran.license.common.CustomKeyStoreParam;
import org.yiran.license.common.CustomLicenseManager;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.text.MessageFormat;
import java.util.prefs.Preferences;

/**
 * License生成类
 *
 * @date 2018/4/19
 * @since 1.0.0
 */
public class LicenseCreator {
    private static Logger logger = LogManager.getLogger(LicenseCreator.class);
    private final static X500Principal DEFAULT_HOLDER_AND_ISSUER = new X500Principal("CN=localhost, OU=localhost, O=localhost, L=SH, ST=SH, C=CN");
    private LicenseCreatorParam param;

    public LicenseCreator(LicenseCreatorParam param) {
        this.param = param;
    }

    /**
     * 生成License证书
     * @author zifangsky
     * @date 2018/4/20 10:58
     * @since 1.0.0
     * @return boolean
     */
    public boolean generateLicense(){
        try {
            LicenseManager licenseManager = new CustomLicenseManager(initLicenseParam());
            LicenseContent licenseContent = initLicenseContent();

            licenseManager.store(licenseContent,new File(param.getLicensePath()));

            return true;
        }catch (Exception e){
            logger.error(MessageFormat.format("证书生成失败：{0}",param),e);
            return false;
        }
    }

    /**
     * 初始化证书生成参数
     * @author zifangsky
     * @date 2018/4/20 10:56
     * @since 1.0.0
     * @return de.schlichtherle.license.LicenseParam
     */
    private LicenseParam initLicenseParam(){
        Preferences preferences = Preferences.userNodeForPackage(LicenseCreator.class);

        //设置对证书内容加密的秘钥
        CipherParam cipherParam = new DefaultCipherParam(param.getStorePass());

        KeyStoreParam privateStoreParam = new CustomKeyStoreParam(LicenseCreator.class
                ,param.getPrivateKeysStorePath()
                ,param.getPrivateAlias()
                ,param.getStorePass()
                ,param.getKeyPass());

        LicenseParam licenseParam = new DefaultLicenseParam(param.getSubject()
                ,preferences
                ,privateStoreParam
                ,cipherParam);

        return licenseParam;
    }

    /**
     * 设置证书生成正文信息
     * @author zifangsky
     * @date 2018/4/20 10:57
     * @since 1.0.0
     * @return de.schlichtherle.license.LicenseContent
     */
    private LicenseContent initLicenseContent(){
        LicenseContent licenseContent = new LicenseContent();
        licenseContent.setHolder(DEFAULT_HOLDER_AND_ISSUER);
        licenseContent.setIssuer(DEFAULT_HOLDER_AND_ISSUER);

        licenseContent.setSubject(param.getSubject());
        licenseContent.setIssued(param.getIssuedTime());
        licenseContent.setNotBefore(param.getIssuedTime());
        licenseContent.setNotAfter(param.getExpiryTime());
        licenseContent.setConsumerType(param.getConsumerType());
        licenseContent.setConsumerAmount(param.getConsumerAmount());
        licenseContent.setInfo(param.getDescription());

        //扩展校验服务器硬件信息
        licenseContent.setExtra(param.getLicenseCheckModel());

        return licenseContent;
    }

}

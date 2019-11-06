/*package com.yiran.activiti;

import org.junit.Test;
import org.yiran.license.use.LicenseVerify;
import org.yiran.license.use.LicenseVerifyParam;

import com.alibaba.fastjson.JSON;
import com.yiran.base.BaseJunit;

public class LicenseTest extends BaseJunit{

	//@Test
	public  void test() {
		
		System.out.println("++++++++ 开始安装证书 ++++++++");

        LicenseVerifyParam param = new LicenseVerifyParam();
        param.setSubject("license_demo");
        param.setPublicAlias("publicCert");
        param.setStorePass("public_yiran_password1234");
        param.setLicensePath("D:/license/license.lic");
        param.setPublicKeysStorePath("D:/license/keys/publicCerts.keystore");
        System.out.println("安装证书 参数:"+JSON.toJSONString(param));
        LicenseVerify licenseVerify = new LicenseVerify();
        //安装证书
        licenseVerify.install(param);
        
        System.out.println("++++++++ 证书安装结束 ++++++++");
	}
}
*/
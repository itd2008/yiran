package api.test;

import java.util.Properties;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.yiran.api.server.PropertyHelper;

import api.base.ApiBaseJunit;

public class ProperTest extends ApiBaseJunit{
	@Autowired
	private PropertyHelper propertyHelper;
	
	@Test
	public void propertest(){
		Properties properties = propertyHelper.getProperties("LLPAY60502");
		String merchant_no = properties.getProperty("MERCHANT_NO");
		System.out.println("商户号"+merchant_no);
		String sign_create_bill_address = properties.getProperty("sign_create_bill_address");
		System.out.println("银行卡签约申请接口："+sign_create_bill_address);
		String bind_bandcardverfy_url = properties.getProperty("BIND_BANDCARDVERFY_URL");
		System.out.println("银行卡签约验证："+bind_bandcardverfy_url);
	}

}

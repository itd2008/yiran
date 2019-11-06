
package com.yiran.api.filedown;

import com.alibaba.druid.util.StringUtils;
import com.yiran.api.utils.FileDownLoadUtils;
import com.yiran.api.utils.SignHelper;
import com.yiran.api.utils.WeiXinBaseUtils;
import com.yiran.api.utils.https.HttpClientUtil;
import com.yiran.api.utils.https.HttpResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 微信文件下载类
 */
@Component
public class WinXinFileDown {

	private static final Log LOG = LogFactory.getLog(WinXinFileDown.class);

	/**
	 * 文件下载类
	 *
	 * @param billDate
	 *            账单日
	 * @param dir
	 *            账单保存路径
	 * 
	 */

	public File fileDown(Map<String,String> map) throws IOException {
		String bill_date = map.get("billDate");
		HttpResponse response = null;
		try {
			// 生成xml文件
			String xml = this.generateXml(map);
			LOG.info(xml);
			String bill_dowload_url = map.get("bill_dowload_url");
			response = HttpClientUtil.httpsRequest(bill_dowload_url, "POST", xml);

			// String dir = "/home/roncoo/app/accountcheck/billfile/weixin";
			String billDirPath = map.get("billDirPath");
			// 对账类型：
			// ALL，返回当日所有订单信息，默认值
			// SUCCESS，返回当日成功支付的订单
			// REFUND，返回当日退款订单
			String bill_type = map.get("bill_type");
			
			File file = new File(billDirPath, bill_date + "_" + bill_type.toLowerCase() + ".txt");
			int index = 1;

			// 判断文件是否已经存在
			while (file.exists()) {
				file = new File(billDirPath, bill_date + "_" + bill_type.toLowerCase() + index + ".txt");
				index++;
			}
			return FileDownLoadUtils.saveFile(response, file);

		} catch (IOException e) {
			throw new IOException("下载微信账单失败", e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				LOG.error("关闭下载账单的流/连接失败", e);
			}
		}
	}

	/**
	 * 根据微信接口要求，生成xml文件
	 * 
	 * @param appId
	 *            必填
	 * @param mchId
	 *            必填
	 * @param billDate
	 *            必填, 下载对账单的日期(最小单位天)
	 * @param billType
	 *            下载单类型
	 * @param appSecret
	 *            必填, 供签名使用
	 * @return
	 */
	public String generateXml(Map<String,String> map) {
		String bill_date = map.get("billDate");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("appid", map.get("appid"));
		params.put("mch_id", map.get("mch_id"));
		params.put("bill_date", bill_date);
		params.put("bill_type", map.get("bill_type"));
		// 随机字符串，不长于32，调用随机数函数生成，将得到的值转换为字符串
		params.put("nonce_str", WeiXinBaseUtils.createNoncestr());

		// 过滤空值
		for (Iterator<Entry<String, String>> it = params.entrySet().iterator(); it.hasNext();) {
			Entry<String, String> entry = it.next();
			if (StringUtils.isEmpty(entry.getValue())) {
				it.remove();
			}
		}

		String sign = SignHelper.getSign(params, map.get("appSecret"));
		params.put("sign", sign.toUpperCase());
		return WeiXinBaseUtils.arrayToXml(params);
	}

}

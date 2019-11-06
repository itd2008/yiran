package com.yiran.quartz.biz;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiran.paychannel.enums.FundChannelApiType;
import com.yiran.paychannel.utils.MapUtil;
import com.yiran.payorder.domain.ChannelResult;
import com.yiran.payorder.request.ChannelRequest;
import com.yiran.payorder.service.IFundRequestService;

import java.io.File;
import java.util.Date;
import java.util.Map;

/**
 * 对账文件下载业务逻辑.
 */
@Component("reconciliationFileDownBiz")
public class ReconciliationFileDownBiz {

	private static final Log LOG = LogFactory.getLog(ReconciliationFileDownBiz.class);
	private static final int DOWNLOAD_TRY_TIMES = 3;// 下载尝试次数
	@Autowired
	private IFundRequestService fundRequestService;

	/**
	 * 请求下载对账文件 .
	 * 
	 * @param interfaceCode
	 *            支付渠道
	 * @param billDate
	 *            账单日
	 * @return
	 */
	public File downReconciliationFile(String fundChannelCode, String billDate) {

		// 支付渠道编码
		if (StringUtils.isEmpty(fundChannelCode)) {
			LOG.info("支付渠道编码为空");
			return null;
		}

		// 对账单下载
		return this.downFile(fundChannelCode, billDate);
	}

	/**
	 * 下载文件
	 * 
	 * @param interfaceCode
	 *            接口编码
	 * @param tradeGainCheckFileTime
	 *            业务对账文件的获取时间
	 */
	private File downFile(String fundChannelCode, String billDate) {

		LOG.info("银行渠道编号[" + fundChannelCode + "],进入下载业务对账文件操作>>>");
		ChannelRequest channelRequest = new ChannelRequest();
		channelRequest.setFundChannelCode(fundChannelCode);
		channelRequest.setApiType(FundChannelApiType.FILE_DOWN);
		channelRequest.getExtension().put("billDate", billDate);
		try {
			File file = null;
			int downloadTrytimes = 0;
			// 默认尝试三次
			while (file == null && downloadTrytimes < DOWNLOAD_TRY_TIMES) {
				try {
					downloadTrytimes++;
					ChannelResult result = fundRequestService.download(channelRequest);
					if(result.isSuccess()){
						Map<String, String> map = MapUtil.jsonToMap(result.getExtension());
						file = new File(map.get("bill_file"));
					}
					
				} catch (Exception e) {
					LOG.error("下载账单文件失败", e);
					Thread.sleep(10000);
				}
			}
			return file;
		} catch (Exception e) {
			LOG.error("下载微信账单文件失败", e);
		}
		return null;
	}
}

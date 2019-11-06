package com.yiran.quartz.task;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yiran.quartz.biz.ReconciliationCheckBiz;
import com.yiran.quartz.biz.ReconciliationFileDownBiz;
import com.yiran.quartz.biz.ReconciliationFileParserBiz;
import com.yiran.quartz.biz.ReconciliationValidateBiz;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiran.reconciliation.domain.ReconciliationChannelSetting;
import com.yiran.reconciliation.enums.BatchStatusEnum;
import com.yiran.reconciliation.enums.PublicStatusEnum;
import com.yiran.reconciliation.enums.ReleaseStatusEnum;
import com.yiran.reconciliation.service.IBuildNoService;
import com.yiran.reconciliation.service.IReconciliationAccountCheckBatchService;
import com.yiran.reconciliation.service.IReconciliationChannelSettingService;
import com.yiran.reconciliation.utils.ReconciliationDateUtils;
import com.yiran.reconciliation.vo.ReconciliationEntityVo;

/**
 * 对账处理(包括下载对账文件、转换对账文件、对账)
 * @author pandaa
 */
@Component("reconciliationTask")
public class ReconciliationTask {
	private static final Logger LOG = LoggerFactory.getLogger(ReconciliationTask.class);
	
	@Autowired
	private ReconciliationValidateBiz validateBiz;
	@Autowired
	private ReconciliationFileDownBiz fileDownBiz;
	@Autowired
	private ReconciliationFileParserBiz parserBiz;
	@Autowired
	private ReconciliationCheckBiz checkBiz;
	@Autowired
	private IReconciliationChannelSettingService reconciliationChannelSettingService;
	@Autowired
	private IReconciliationAccountCheckBatchService batchService;
	@Autowired
	private IBuildNoService buildNoService;
	
	public void taskRun() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			
			@SuppressWarnings("rawtypes")
			// 获取全部有效的对账接口
			ReconciliationChannelSetting rcs = new ReconciliationChannelSetting();
			rcs.setStatus(PublicStatusEnum.ACTIVE.name());
			List<ReconciliationChannelSetting> reconciliationInterList = reconciliationChannelSettingService.selectReconciliationChannelSettingList(rcs);
			// 根据不同的渠道发起对账
			for (int num = 0; num < reconciliationInterList.size(); num++) {
				// 判断接口是否正确
				ReconciliationChannelSetting reconciliationInter =  reconciliationInterList.get(num);
				if (reconciliationInter == null) {
					LOG.info("对账接口信息" + reconciliationInter + "为空");
					continue;
				}
				// 获取需要对账的对账单时间
				//Date billDate = ReconciliationDateUtils.addDay(new Date(), -reconciliationInter.getBillDay());
				//TODO:模拟有账单
				Date billDate = sdf.parse("20190919");
				// 获取对账渠道编号
				String fundChannelCode = reconciliationInter.getFundChannelCode();
				/** step1:判断是否对过账 **/
				ReconciliationAccountCheckBatch batch = new ReconciliationAccountCheckBatch();
				Boolean checked = validateBiz.isChecked(fundChannelCode, billDate);
				if (checked) {
					LOG.info("账单日[" + sdf.format(billDate) + "],渠道[" + fundChannelCode + "],已经对过账，不能再次发起自动对账。");
					continue;
				}
				
				// 创建对账批次
				batch.setCreater("reconciliationSystem");
				batch.setCreateTime(new Date());
				batch.setBillDate(billDate);
				String batchNo = buildNoService.buildReconciliationNo();
				LOG.info("对账批次号【{}】",batchNo);
				batch.setBatchNo(batchNo);
				batch.setBankType(fundChannelCode);
				batch.setBillType(reconciliationInter.getInstCode());
				
				/** step2:对账文件下载 **/
				File file = null;
				try {
					LOG.info("ReconciliationFileDownBiz,对账文件下载开始");
					file = fileDownBiz.downReconciliationFile(fundChannelCode, sdf.format(billDate));
					if (file == null) {
						continue;
					}
					batch.setOrgCheckFilePath(file.getCanonicalPath());
					LOG.info("对账文件下载结束");
				} catch (Exception e) {
					LOG.error("对账文件下载异常:", e);
					batch.setStatus(BatchStatusEnum.FAIL.name());
					batch.setRemark("对账文件下载异常");
					batchService.insertReconciliationAccountCheckBatch(batch);
					continue;
				}
				
				/** step3:解析对账文件 **/
				List<ReconciliationEntityVo> bankList = null;
				try {
					LOG.info("=ReconciliationFileParserBiz=>对账文件解析开始>>>");

					// 解析文件
					bankList = parserBiz.parser(batch, file, billDate,reconciliationInter.getInstCode());
					// 如果下载文件异常，退出
					if (BatchStatusEnum.ERROR.name().equals(batch.getStatus())) {
						continue;
					}
					batch.setReleaseStatus(ReleaseStatusEnum.SUCCESS.name());
					LOG.info("对账文件解析结束");
				} catch (Exception e) {
					LOG.error("对账文件解析异常:", e);
					batch.setStatus(BatchStatusEnum.FAIL.name());
					batch.setReleaseStatus(ReleaseStatusEnum.FAIL.name());
					batch.setRemark("对账文件解析异常");
					batchService.insertReconciliationAccountCheckBatch(batch);
					continue;
				}
				
				/** step4:对账流程 **/
				try {
					checkBiz.check(bankList, fundChannelCode, batch);
				} catch (Exception e) {
					LOG.error("对账异常:", e);
					batch.setStatus(BatchStatusEnum.FAIL.name());
					batch.setRemark("对账异常");
					batchService.insertReconciliationAccountCheckBatch(batch);
					continue;
				}
				
				/** step5:清理缓冲池 **/
				// 如果缓冲池中有三天前的数据就清理掉并记录差错
				validateBiz.validateScratchPool();
				
			}
			
		} catch (Exception e) {
			LOG.error("roncoo-app-reconciliation error:", e);
		}

	}
	
}

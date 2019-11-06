
package com.yiran.quartz.biz;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

import com.yiran.reconciliation.domain.ReconciliationAccountCheckBatch;
import com.yiran.reconciliation.parser.ParserInterface;
import com.yiran.reconciliation.vo.ReconciliationEntityVo;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 对账文件解析业务逻辑.
 */
@Component("reconciliationFileParserBiz")
public class ReconciliationFileParserBiz implements BeanFactoryAware {

	// 加载beanfactory
	private BeanFactory beanFactory;

	public Object getService(String payInterface) {
		return beanFactory.getBean(payInterface);
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	private static final Log LOG = LogFactory.getLog(ReconciliationFileParserBiz.class);

	/**
	 * 解析file文件
	 * 
	 * @param batch
	 *            对账批次实体
	 * @param file
	 *            下载的对账文件
	 * @param billDate
	 *            下载对账单的日期
	 * 
	 * @param interfaceCode
	 *            具体的支付方式
	 * 
	 * @return 转换之后的vo对象
	 * @throws IOException
	 */
	public List<ReconciliationEntityVo> parser(ReconciliationAccountCheckBatch batch, File file, Date billDate, String interfaceCode) throws IOException {

		// 解析成 ReconciliationEntityVo 对象
		List<ReconciliationEntityVo> rcVoList = null;

		// 根据支付方式得到解析器的名字
		String parserClassName = interfaceCode + "Parser";
		LOG.info("根据支付方式得到解析器的名字[" + parserClassName + "]");
		ParserInterface service = null;
		try {
			// 根据名字获取相应的解析器
			service = (ParserInterface) this.getService(parserClassName);
		} catch (NoSuchBeanDefinitionException e) {
			LOG.error("根据解析器的名字[" + parserClassName + "]，没有找到相应的解析器");
			return null;
		}
		// 使用相应的解析器解析文件
		rcVoList = service.parser(file, billDate, batch);

		return rcVoList;

	}
}

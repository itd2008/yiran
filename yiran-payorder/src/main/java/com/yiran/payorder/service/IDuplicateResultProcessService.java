package com.yiran.payorder.service;

import com.yiran.payorder.domain.PayInstOrder;
import com.yiran.payorder.domain.PayInstOrderResult;

/**
 * 
 * <p>
 * 处理结果，包括实时调用的结果，包括实时返回，或者异步回调
 * </p>
 * 
 * 1. 核对返回的结果，金额是否匹配，账户信息是否一致等 2. 调用状态模块更新状态
 * 
 */
public interface IDuplicateResultProcessService {

	/**
	 * 检查机构订单：通讯状态是否已返回.
	 * 
	 * @param result
	 */
	public boolean checkHasProcessed(PayInstOrder instOrder);

	/**
	 * 重复请求处理.
	 * 
	 * @param dbInstOrder
	 * @param comingResult
	 */
	public boolean duplicateRequestProcess(PayInstOrder dbInstOrder,
			PayInstOrderResult comingResult);
}

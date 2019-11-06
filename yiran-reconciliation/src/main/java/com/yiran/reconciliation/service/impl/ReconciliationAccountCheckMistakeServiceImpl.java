package com.yiran.reconciliation.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.reconciliation.mapper.ReconciliationAccountCheckMistakeMapper;
import com.yiran.reconciliation.domain.ReconciliationAccountCheckMistake;
import com.yiran.reconciliation.service.IReconciliationAccountCheckMistakeService;
import com.yiran.common.support.Convert;

/**
 * 对账差错 服务层实现
 * 
 * @author yiran
 * @date 2019-09-20
 */
@Service
public class ReconciliationAccountCheckMistakeServiceImpl implements IReconciliationAccountCheckMistakeService 
{
	@Autowired
	private ReconciliationAccountCheckMistakeMapper reconciliationAccountCheckMistakeMapper;

	/**
     * 查询对账差错信息
     * 
     * @param id 对账差错ID
     * @return 对账差错信息
     */
    @Override
	public ReconciliationAccountCheckMistake selectReconciliationAccountCheckMistakeById(Integer id)
	{
	    return reconciliationAccountCheckMistakeMapper.selectReconciliationAccountCheckMistakeById(id);
	}
	
	/**
     * 查询对账差错列表
     * 
     * @param reconciliationAccountCheckMistake 对账差错信息
     * @return 对账差错集合
     */
	@Override
	public List<ReconciliationAccountCheckMistake> selectReconciliationAccountCheckMistakeList(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{
	    return reconciliationAccountCheckMistakeMapper.selectReconciliationAccountCheckMistakeList(reconciliationAccountCheckMistake);
	}
	
    /**
     * 新增对账差错
     * 
     * @param reconciliationAccountCheckMistake 对账差错信息
     * @return 结果
     */
	@Override
	public int insertReconciliationAccountCheckMistake(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{
	    return reconciliationAccountCheckMistakeMapper.insertReconciliationAccountCheckMistake(reconciliationAccountCheckMistake);
	}
	
	/**
     * 修改对账差错
     * 
     * @param reconciliationAccountCheckMistake 对账差错信息
     * @return 结果
     */
	@Override
	public int updateReconciliationAccountCheckMistake(ReconciliationAccountCheckMistake reconciliationAccountCheckMistake)
	{
	    return reconciliationAccountCheckMistakeMapper.updateReconciliationAccountCheckMistake(reconciliationAccountCheckMistake);
	}

	/**
     * 删除对账差错对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteReconciliationAccountCheckMistakeByIds(String ids)
	{
		return reconciliationAccountCheckMistakeMapper.deleteReconciliationAccountCheckMistakeByIds(Convert.toStrArray(ids));
	}

	@Override
	public void saveListDate(List<ReconciliationAccountCheckMistake> mistakeList) {
		for (ReconciliationAccountCheckMistake mistake : mistakeList) {
			insertReconciliationAccountCheckMistake(mistake);
		}
	}

	
	
}

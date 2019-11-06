package com.yiran.paychannel.mapper;

import com.yiran.paychannel.domain.Sequence;
import java.util.List;

import org.apache.ibatis.annotations.Param;	

/**
 * 序列 数据层
 * 
 * @author yiran
 * @date 2019-07-26
 */
public interface SequenceMapper 
{
	/**
     * 查询序列信息
     * 
     * @param name 序列ID
     * @return 序列信息
     */
	public Sequence selectSequenceById(String name);
	
	/**
     * 查询序列列表
     * 
     * @param sequence 序列信息
     * @return 序列集合
     */
	public List<Sequence> selectSequenceList(Sequence sequence);
	
	/**
     * 新增序列
     * 
     * @param sequence 序列信息
     * @return 结果
     */
	public int insertSequence(Sequence sequence);
	
	/**
     * 修改序列
     * 
     * @param sequence 序列信息
     * @return 结果
     */
	public int updateSequence(Sequence sequence);
	
	/**
     * 删除序列
     * 
     * @param name 序列ID
     * @return 结果
     */
	public int deleteSequenceById(String name);
	
	/**
     * 批量删除序列
     * 
     * @param names 需要删除的数据ID
     * @return 结果
     */
	public int deleteSequenceByIds(String[] names);

	/**
	 * 设置指定sequence的初始值  
	 * @param name
	 * @param currentValue
	 */
	public int setval(@Param("name") String name, @Param("currentValue") int currentValue);

	/**
	 * 查询指定sequence的当前值
	 * @param name
	 * @return
	 */
	public int currval(String name);

	/**
	 * 查询指定sequence的下一个值
	 * @param name
	 * @return
	 */
	public int nextval(String name);
	
}
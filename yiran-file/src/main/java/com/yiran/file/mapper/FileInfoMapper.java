package com.yiran.file.mapper;

import com.yiran.file.domain.FileInfo;
import java.util.List;	

/**
 * 文件 数据层
 * 
 * @author yiran
 * @date 2019-03-27
 */
public interface FileInfoMapper 
{
	/**
     * 查询文件信息
     * 
     * @param id 文件ID
     * @return 文件信息
     */
	public FileInfo selectFileInfoById(Integer id);
	
	/**
     * 查询文件列表
     * 
     * @param fileInfo 文件信息
     * @return 文件集合
     */
	public List<FileInfo> selectFileInfoList(FileInfo fileInfo);
	
	/**
     * 新增文件
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
	public int insertFileInfo(FileInfo fileInfo);
	
	/**
     * 修改文件
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
	public int updateFileInfo(FileInfo fileInfo);
	
	/**
     * 删除文件
     * 
     * @param id 文件ID
     * @return 结果
     */
	public int deleteFileInfoById(Integer id);
	
	/**
     * 批量删除文件
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteFileInfoByIds(String[] ids);

	
}
package com.yiran.file.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yiran.file.mapper.FileInfoMapper;
import com.yiran.file.domain.FileInfo;
import com.yiran.file.service.IFileInfoService;
import com.yiran.common.support.Convert;

/**
 * 文件 服务层实现
 * 
 * @author yiran
 * @date 2019-03-27
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService 
{
	@Autowired
	private FileInfoMapper fileInfoMapper;

	/**
     * 查询文件信息
     * 
     * @param id 文件ID
     * @return 文件信息
     */
    @Override
	public FileInfo selectFileInfoById(Integer id)
	{
	    return fileInfoMapper.selectFileInfoById(id);
	}
	
	/**
     * 查询文件列表
     * 
     * @param fileInfo 文件信息
     * @return 文件集合
     */
	@Override
	public List<FileInfo> selectFileInfoList(FileInfo fileInfo)
	{
	    return fileInfoMapper.selectFileInfoList(fileInfo);
	}
	
    /**
     * 新增文件
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
	@Override
	public int insertFileInfo(FileInfo fileInfo)
	{
	    return fileInfoMapper.insertFileInfo(fileInfo);
	}
	
	/**
     * 修改文件
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
	@Override
	public int updateFileInfo(FileInfo fileInfo)
	{
	    return fileInfoMapper.updateFileInfo(fileInfo);
	}

	/**
     * 删除文件对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteFileInfoByIds(String ids)
	{
		return fileInfoMapper.deleteFileInfoByIds(Convert.toStrArray(ids));
	}

	@Override
	public int deleteFileInfoById(Integer id) {
		return fileInfoMapper.deleteFileInfoById(id);
	}
	
}

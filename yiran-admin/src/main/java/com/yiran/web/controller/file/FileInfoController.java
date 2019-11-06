package com.yiran.web.controller.file;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.yiran.common.annotation.Log;
import com.yiran.common.enums.BusinessType;
import com.yiran.common.utils.fastdft.FastDFSHelper;
import com.yiran.file.domain.FileInfo;
import com.yiran.file.service.IFileInfoService;
import com.yiran.framework.web.base.BaseController;
import com.yiran.common.base.AjaxObj;
import com.yiran.common.base.AjaxResult;
import com.yiran.common.config.FastDFSConfig;

/**
 * 文件 信息操作处理
 * 
 * @author yiran
 * @date 2019-03-27
 */
@Controller
@RequestMapping("/file/fileInfo")
public class FileInfoController extends BaseController
{
    private String prefix = "file/fileInfo";
	
    @Autowired
   	private FastDFSHelper fastDFSHelper;
    @Autowired
	private FastDFSConfig fastDFSTConfig;
   	@Autowired
   	private IFileInfoService fileInfoService;
   	@RequiresPermissions("file:info:view")
   	@GetMapping()
   	public String info()
   	{
   	    return prefix + "/info";
   	}
   	
   	/**
   	 * 查询文件列表
   	 */
   	@RequiresPermissions("file:info:view")
   	@GetMapping("/filemanager/{type}")
   	public String list(@PathVariable("type") String type,ModelMap model)
   	{
   		FileInfo info = new FileInfo();
   		if(!"0".equals(type)){
   			info.setType(type);
   		}
   		String file_top_all_class="file-control";
   		String file_top_file_class="file-control";
   		String file_top_picture_class="file-control";
   		String file_top_audio_class="file-control";
   		String file_top_video_class="file-control";
   		
   		String file_b_file_class="fa fa-folder";
   		String file_b_picture_class="fa fa-folder";
   		String file_b_audio_class="fa fa-folder";
   		String file_b_video_class="fa fa-folder";
   		String file_b_Otherclass="fa fa-folder";
   		if("0".equals(type)){
   			file_top_all_class="file-control active";
   			
   		}else if("1".equals(type)){
   			file_top_file_class="file-control active";
   			file_b_file_class="fa fa-folder-open";
   			
   		}else if("2".equals(type)){
   			file_top_picture_class="file-control active";
   			file_b_picture_class="fa fa-folder-open";
   		}else if("4".equals(type)){
   			file_top_audio_class="file-control active";
   			file_b_audio_class="fa fa-folder-open";
   		}else if("3".equals(type)){
   			file_top_video_class="file-control active";
   			file_b_video_class="fa fa-folder-open";
   		}else if("5".equals(type)){
   			
   			file_b_Otherclass="fa fa-folder-open";
   		}
   		
   		
   		//获取所有的文件
   		List<FileInfo> list = fileInfoService.selectFileInfoList(info);
   		model.put("list", list);
   		model.put("file_top_all_class", file_top_all_class);
   		model.put("file_top_file_class", file_top_file_class);
   		model.put("file_top_picture_class", file_top_picture_class);
   		model.put("file_top_audio_class", file_top_audio_class);
   		model.put("file_top_video_class", file_top_video_class);
   		model.put("file_b_file_class", file_b_file_class);
   		model.put("file_b_picture_class", file_b_picture_class);
   		model.put("file_b_audio_class", file_b_audio_class);
   		model.put("file_b_video_class", file_b_video_class);
   		model.put("file_b_Otherclass", file_b_Otherclass);
   		return prefix + "/file_manager";
   	}
   	
   	/**
   	 * 上传文件页面
   	 */
   	@GetMapping("/uploadMode")
   	public String uploadMode(ModelMap model)
   	{
   		
   	    return prefix + "/upload";
   	}

   	/**
   	 * 修改文件
   	 */
   	@RequiresPermissions("file:info:edit")
   	@Log(title = "文件", businessType = BusinessType.UPDATE)
   	@GetMapping("/edit/{id}")
   	public String edit(@PathVariable("id") Integer id, ModelMap model)
   	{
   		FileInfo info = fileInfoService.selectFileInfoById(id);
   		model.put("info", info);
   	    return prefix + "/edit";
   	}
   	
   	/**
   	 * 保存文件
   	 * @throws UnsupportedEncodingException 
   	 */
   	@PostMapping("/save")
   	@ResponseBody
   	public AjaxResult save(FileInfo fileInfo) 
   	{
   		if (fileInfoService.insertFileInfo(fileInfo) > 0)
   		{
   			return success();
   		}
   		return error();
   	}
   	
   	
   	@GetMapping("/show/{id}")
   	public String show(@PathVariable("id") Integer id, ModelMap model)
   	{
   		FileInfo info = fileInfoService.selectFileInfoById(id);
   		model.put("info", info);
   		String url =prefix;
   		if("jpg".equals(info.getSuffix())){//图片
   			url = url+"/img_show";
   		}else if("pdf".equals(info.getSuffix())){
   			url = url+"/pdf_show";
   		}else if("doc".equals(info.getSuffix()) ||"xls".equals(info.getSuffix()) ||"ppt".equals(info.getSuffix()) 
   				|| "docx".equals(info.getSuffix())||"xlsx".equals(info.getSuffix())||"pptx".equals(info.getSuffix())){//文档
   			url = url+"/file_show";
   		}else if("mp3".equals(info.getSuffix()) ||"wav".equals(info.getSuffix())){//音乐
   			url = url+"/audio_show";
   		}else if("mp4".equals(info.getSuffix())||"avi".equals(info.getSuffix())||"3gp".equals(info.getSuffix())
   				||"webm".equals(info.getSuffix())||"mpg".equals(info.getSuffix())){//视频
   			url = url+"/video_show";
   		}
   	    return url;
   	}
   	
   	@GetMapping("/dowload/{id}")
   	public void dowload(@PathVariable("id") Integer id, ModelMap model,HttpServletRequest request, HttpServletResponse response) 
   	{
   		response.setCharacterEncoding(request.getCharacterEncoding());
   		FileInfo info = fileInfoService.selectFileInfoById(id);
   		
   		response.setHeader("content-type", "application/octet-stream");
   		response.setContentType("application/octet-stream");
   		response.setHeader("Content-Disposition", "attachment;filename=" + info.getNewName());  //3.设置content-disposition响应头控制浏览器以下载的形式打开文件
   		InputStream in = null;
   		try {
   			int len = 0;
   			// 5.创建数据缓冲区
   			byte[] buffer = new byte[1024];
   			// 6.通过response对象获取OutputStream流
   			OutputStream out = response.getOutputStream();
   			// 7.将FileInputStream流写入到buffer缓冲区
   			while ((len = in.read(buffer)) > 0) {
   				// 8.使用OutputStream将缓冲区的数据输出到客户端浏览器
   				out.write(buffer, 0, len);
   			}

   		} catch (IOException e) {
   			e.printStackTrace();
   		} finally {
   			try {
   				// 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
   				in.close();
   			} catch (IOException e) {
   				e.printStackTrace();
   			}
   		}
   	}
   	
   	@GetMapping( "/delete/{id}")
   	@ResponseBody
   	public AjaxResult delete(@PathVariable("id") Integer id)
   	{
   		try {
   			FileInfo info = fileInfoService.selectFileInfoById(id);
   			//删除数据库中的
   			fileInfoService.deleteFileInfoById(id);
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   			 return error();
   		}
           return success();
   	}
   	
   	@PostMapping("/uploadFile")
   	@ResponseBody
   	public void uploadFile(HttpServletRequest request,HttpServletResponse resp) 
   	{
   		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
   		//转换为multipartrequest
   		String newName = multipartRequest.getParameter("newName");;
   		System.out.println("新上传文件名："+newName);
   		FileInfo info = new FileInfo();
   		System.out.println(">>>>>>>文件管理文件上传操作<<<<<<<<");
   		AjaxObj ao = new AjaxObj();
   		PrintWriter out = null;
   		try {
   			out = resp.getWriter();
   			request.setCharacterEncoding("UTF-8");
   			resp.setContentType("application/json;charset=utf-8");
   			MultipartFile multipartFile = null;
   	        Map map =multipartRequest.getFileMap();
   	         for (Iterator i = map.keySet().iterator(); i.hasNext();) {
   	            Object obj = i.next();
   	            multipartFile=(MultipartFile) map.get(obj);
   	          }
   			
   			String oldName = multipartFile.getOriginalFilename();
   			info.setOldName(oldName);
   			System.out.println("上传图片名："+oldName);
   			//上传
   			String uploadFilePath = fastDFSHelper.uploadFile(multipartFile);
   			System.out.println("上传文件新名字："+uploadFilePath);
   			//后缀
   			String suffix = FilenameUtils.getExtension(oldName);
   			System.out.println("上传文件后缀："+suffix);
   			info.setNewName(newName+"."+suffix);
   			info.setSuffix(suffix);
   			long size = multipartFile.getSize()/1024;
   			String fileSize = size +"KB";
   			System.out.println("上传文件文件大小："+ fileSize);
   			info.setSize(size);
   			//根据文件后缀 设置CSS样式 、文件夹名称 、OSS文件路径 
   			if("jpg".equals(suffix)){//图片
   				info.setCssStyle("img-responsive");
   				info.setFileName("picture");
   				info.setType("2");
   			}else if("doc".equals(suffix) ||"xls".equals(suffix) ||"ppt".equals(suffix) ||"pdf".equals(suffix) 
   					||"txt".equals(suffix) || "docx".equals(suffix)||"xlsx".equals(suffix)||"pptx".equals(suffix)
   					||"sql".equals(suffix)){//文档
   				if("xls".equals(suffix)||"xlsx".equals(suffix)){
   					info.setCssStyle("fa fa-bar-chart-o");
   				}else{
   					info.setCssStyle("fa fa-file");
   				}
   				info.setType("1");
   				info.setFileName("file");
   				
   			}else if("mp3".equals(suffix) ||"wav".equals(suffix)){//音乐
   				info.setCssStyle("fa fa-music");
   				info.setFileName("audio");
   				info.setType("4");
   				
   			}else if("mp4".equals(suffix)||"avi".equals(suffix)||"3gp".equals(suffix)
   					||"webm".equals(suffix)||"mpg".equals(suffix)){//视频
   				info.setCssStyle("img-responsive fa fa-film");
   				info.setFileName("video");
   				info.setType("3");
   			}else{
   				info.setCssStyle("");
   				info.setFileName("Other");
   				info.setType("5");
   			}
   			String url = fastDFSTConfig.getInterNetHttpHost()+"/"+uploadFilePath;
   			info.setOssUrl(url);
   			fileInfoService.insertFileInfo(info);
   			ao.setResult(1);
   		} catch (IOException e) {
	   		ao.setResult(0);
	   		ao.setMsg(e.getMessage());
	   	}
   		resp.reset();
		System.out.println("文件上传成功，返回结果："+JSON.toJSON(ao));
		out.println(JSON.toJSON(ao));
		out.flush();
   		
   	}
   	
   	/**
   	 * 删除文件
   	 */
   	@RequiresPermissions("file:info:remove")
   	@Log(title = "文件", businessType = BusinessType.DELETE)
   	@PostMapping( "/remove")
   	@ResponseBody
   	public AjaxResult remove(String ids)
   	{
   		int rows = fileInfoService.deleteFileInfoByIds(ids);
   		if (rows > 0)
           {
               return success();
           }
           return error();
   	}
	
   	
   	@PostMapping("/uploadImage")
   	@ResponseBody
   	public void uploadImage(HttpServletRequest request,HttpServletResponse resp) 
   	{
   		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
   		System.out.println(">>>>>>>图片上传操作<<<<<<<<");
   		AjaxObj ao = new AjaxObj();
   		PrintWriter out = null;
   		try {
   			out = resp.getWriter();
   			request.setCharacterEncoding("UTF-8");
   			resp.setContentType("application/json;charset=utf-8");
   			MultipartFile multipartFile = null;
   	        Map map =multipartRequest.getFileMap();
   	         for (Iterator i = map.keySet().iterator(); i.hasNext();) {
   	            Object obj = i.next();
   	            multipartFile=(MultipartFile) map.get(obj);
   	          }
   			
   			String oldName = multipartFile.getOriginalFilename();
   			System.out.println("上传图片名："+oldName);
   			//上传
   			String uploadFilePath = fastDFSHelper.uploadFile(multipartFile);
   			System.out.println("上传文件新名字："+uploadFilePath);
   			//后缀
   			String suffix = FilenameUtils.getExtension(oldName);
   			String url = fastDFSTConfig.getInterNetHttpHost()+"/"+uploadFilePath;
   			ao.setObj(url);
   			ao.setResult(1);
   		} catch (IOException e) {
	   		ao.setResult(0);
	   		ao.setMsg(e.getMessage());
	   	}
   		resp.reset();
		System.out.println("文件上传成功，返回结果："+JSON.toJSON(ao));
		out.println(JSON.toJSON(ao));
		out.flush();
   		
   	}
   	@PostMapping("/deleteImage")
   	@ResponseBody
   	public void deleteImage(HttpServletRequest request,HttpServletResponse resp) 
   	{
   		AjaxObj ao = new AjaxObj();
   		PrintWriter out = null;
		try {
			out = resp.getWriter();
			//转换为multipartrequest
	   		String fileUrl = request.getParameter("id");;
	   		System.out.println("删除文件URL路径地址："+fileUrl);
			fastDFSHelper.deleteFile(fileUrl);
			ao.setResult(1);
			resp.reset();
			System.out.println("图片删除成功，返回结果："+JSON.toJSON(ao));
			out.println(JSON.toJSON(ao));
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
   	}
   	
   	@PostMapping("/uploadBatchImage")
   	@ResponseBody
   	public void uploadBatchImage(HttpServletRequest request,HttpServletResponse resp) 
   	{
   		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
   		System.out.println(">>>>>>>批量图片上传操作<<<<<<<<");
   		AjaxObj ao = new AjaxObj();
   		PrintWriter out = null;
   		String[] urls = new String[8];
   		try {
   			out = resp.getWriter();
   			request.setCharacterEncoding("UTF-8");
   			resp.setContentType("application/json;charset=utf-8");
   			MultipartFile multipartFile = null;
   	        Map map =multipartRequest.getFileMap();
   	        System.out.println("图片数量:"+map.keySet().size());
   	        int j = 0;
   	         for (Iterator i = map.keySet().iterator(); i.hasNext();) {
   	            Object obj = i.next();
   	            multipartFile=(MultipartFile) map.get(obj);
   	            String oldName = multipartFile.getOriginalFilename();
    			System.out.println("上传图片"+(j+1)+"："+oldName);
    			//上传
    			String uploadFilePath = fastDFSHelper.uploadFile(multipartFile);
    			System.out.println("上传图片"+(j+1)+"新名字："+uploadFilePath);
    			//后缀
    			String suffix = FilenameUtils.getExtension(oldName);
    			String url = fastDFSTConfig.getInterNetHttpHost()+"/"+uploadFilePath;
    			urls[j] = url;
    			j++;
   	          }
   			ao.setObj(urls);
   			ao.setResult(1);
   		} catch (IOException e) {
	   		ao.setResult(0);
	   		ao.setMsg(e.getMessage());
	   	}
   		resp.reset();
		System.out.println("文件上传成功，返回结果："+JSON.toJSON(ao));
		out.println(JSON.toJSON(ao));
		out.flush();
   		
   	}
}

package com.yiran.common.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.yiran.common.support.Convert;

/**
 * 客户端工具类
 * 
 * @author yiran
 */
public class ServletUtils
{
	private static final Logger logger = LoggerFactory.getLogger(ServletUtils.class);
    /**
     * 获取String参数
     */
    public static String getParameter(String name)
    {
        return getRequest().getParameter(name);
    }

    /**
     * 获取String参数
     */
    public static String getParameter(String name, String defaultValue)
    {
        return Convert.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name)
    {
        return Convert.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取Integer参数
     */
    public static Integer getParameterToInt(String name, Integer defaultValue)
    {
        return Convert.toInt(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取request
     */
    public static HttpServletRequest getRequest()
    {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取response
     */
    public static HttpServletResponse getResponse()
    {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取session
     */
    public static HttpSession getSession()
    {
        return getRequest().getSession();
    }

    public static ServletRequestAttributes getRequestAttributes()
    {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     * 
     * @param response 渲染对象
     * @param string 待渲染的字符串
     * @return null
     */
    public static String renderString(HttpServletResponse response, String string)
    {
        try
        {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 是否是Ajax异步请求
     * 
     * @param request
     */
    public static boolean isAjaxRequest(HttpServletRequest request)
    {

        String accept = request.getHeader("accept");
        if (accept != null && accept.indexOf("application/json") != -1)
        {
            return true;
        }

        String xRequestedWith = request.getHeader("X-Requested-With");
        if (xRequestedWith != null && xRequestedWith.indexOf("XMLHttpRequest") != -1)
        {
            return true;
        }

        String uri = request.getRequestURI();
        if (StringUtils.inStringIgnoreCase(uri, ".json", ".xml"))
        {
            return true;
        }

        String ajax = request.getParameter("__ajax");
        if (StringUtils.inStringIgnoreCase(ajax, "json", "xml"))
        {
            return true;
        }

        return false;
    }
    /**
     * 设置文件下载的 response 响应参数
     * @param response
     * @param fileName 文件名
     * @param reques
     */
    public static void setFileDownloadResponseHeader( HttpServletRequest reques, HttpServletResponse response, String fileName) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + getFileDownloadName(reques,fileName));
    }
    /**
     * 设置文件下载的 response 响应参数
     * @param response
     * @param fileName 文件名
     */
    public static void setFileDownloadResponseHeader(HttpServletResponse response, String fileName) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + getFileDownloadName(fileName));
    }
    /**
     * 获取文件下载的 response
     * @param fileName 文件名
     */
    public static HttpServletResponse getFileDownloadResponse(String fileName) {
        HttpServletResponse response = getResponse();
        setFileDownloadResponseHeader(response,fileName);
        return response;
    }
    /**
     * 获取编码后的 文件名
     * @param request
     * @param fileName
     * @return
     */
    public static String getFileDownloadName(HttpServletRequest request, String fileName) {
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        try {
            if (agent.contains("MSIE"))
            {
                // IE浏览器
                filename = URLEncoder.encode(filename, "utf-8");
                filename = filename.replace("+", " ");
            }
            else if (agent.contains("Firefox"))
            {
                // 火狐浏览器
                filename = new String(fileName.getBytes(), "ISO8859-1");
            }
            else if (agent.contains("Chrome"))
            {
                // google浏览器
                filename = URLEncoder.encode(filename, "utf-8");
            }
            else
            {
                // 其它浏览器
                filename = URLEncoder.encode(filename, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("下载文件名转码失败 ",e);
        }
        return filename;
    }
    /**
     * 获取编码后的 文件名<br>限单线程使用
     * @param fileName
     * @return
     */
    public static String getFileDownloadName( String fileName) {
        HttpServletRequest request = getRequest();
        final String agent = request.getHeader("USER-AGENT");
        String filename = fileName;
        try {
            if (agent.contains("MSIE"))
            {
                // IE浏览器
                filename = URLEncoder.encode(filename, "utf-8");
                filename = filename.replace("+", " ");
            }
            else if (agent.contains("Firefox"))
            {
                // 火狐浏览器
                filename = new String(fileName.getBytes(), "ISO8859-1");
            }
            else if (agent.contains("Chrome"))
            {
                // google浏览器
                filename = URLEncoder.encode(filename, "utf-8");
            }
            else
            {
                // 其它浏览器
                filename = URLEncoder.encode(filename, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("下载文件名转码失败 ",e);
        }
        return filename;
    }
}

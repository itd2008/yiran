package com.yiran.weixin.servlet;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yiran.weixin.kit.WeixinBasicKit;

public class InitServlet extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(InitServlet.class);
	private static final long serialVersionUID = 1L;
	private static WebApplicationContext wc;
	private static String realpath;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		logger.info(">>>>>>>>>>>>>>>服务启动执行，初始化相关数据<<<<<<<<<<<<<");
		wc = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		realpath = config.getServletContext().getRealPath("");
		logger.info("===============realpaths初始化地址============"+realpath);
		//BeanFactoryContext.setWc(wc);
		WeixinBasicKit.setWeixinContext();
        logger.info(">>>>>>>>>>>>>>>服务启动执行，初始化相关数据完成<<<<<<<<<<<<<");
	}
	

	public static String getRealpath() {
		return realpath;
	}
	
	public static WebApplicationContext getWc() {
		return wc;
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        resp.setContentType("text/html");  
        PrintWriter out = resp.getWriter();  
        out.println("<html>");  
        out.println("<head>");  
        out.println("<title>Hello World</title>");  
        out.println("</head>");  
        out.println("<body>");  
        out.println("<h1>大家好，我的名字叫XXX</h1>");  
        out.println("</body>");  
        out.println("</html>"); 
	}

	
}
package com.test.annotationservlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AnnotationHandlerFilter implements Filter{
	
	private ServletContext servletcontext;

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AnnotationHandlerFilter过滤器初始化开始---");
		servletcontext = filterConfig.getServletContext();
		Map<String,Class<?>> classMap = new HashMap<String, Class<?>>();
		//获取web.xml中配置的要扫描的包
		String basePackage = filterConfig.getInitParameter("basePackage");
		//如果配置了多个包，例如：<param-value>me.gacl.web.controller,me.gacl.web.UI</param-value>
		if(basePackage.indexOf(",")>0)
		{
			//按照逗号分隔
			String[] packageNameArr = basePackage.split(",");
			for(String packageName:packageNameArr)
			{
				
			}
		}else
		{
			
		}
		System.out.println("---AnnotationHandlerFilter过滤器初始化结束---");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void addServletClassToServletContext(String packageName,Map<String,Class<?>> classMap)
	{
		
	}

}

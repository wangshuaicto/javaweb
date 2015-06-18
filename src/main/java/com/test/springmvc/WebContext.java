package com.test.springmvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebContext {
	
	public static ThreadLocal<HttpServletResponse> resposeHolder = new ThreadLocal<HttpServletResponse>();
	public static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
	
	
	public static HttpServletRequest getRequest()
	{
		return requestHolder.get();
	}
	
	public static HttpServletResponse getResponse()
	{
		return resposeHolder.get();
	}
	
	public static HttpSession getSession()
	{
		return requestHolder.get().getSession();
	}

	public static ServletContext getServletContext()
	{
		return requestHolder.get().getSession().getServletContext();
	}
}

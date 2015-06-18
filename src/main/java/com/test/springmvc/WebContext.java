package com.test.springmvc;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WebContext {
	
	public static ThreadLocal<HttpServletResponse> resposeHolder = new ThreadLocal<HttpServletResponse>();
	public static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<HttpServletRequest>();
	
	
	public HttpServletRequest getRequest()
	{
		return requestHolder.get();
	}
	
	public HttpServletResponse getResponse()
	{
		return resposeHolder.get();
	}
	
	public HttpSession getSession()
	{
		return requestHolder.get().getSession();
	}

	public ServletContext getServletContext()
	{
		return requestHolder.get().getSession().getServletContext();
	}
}

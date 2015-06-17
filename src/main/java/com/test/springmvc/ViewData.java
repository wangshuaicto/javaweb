package com.test.springmvc;

import javax.servlet.http.HttpServletRequest;

public class ViewData {
	private HttpServletRequest request;
	public ViewData()
	{
		initRequest();
	}
	
	public void initRequest()
	{
		//从requestHolder中获取request对象
		this.request = WebContext.requestHolder.get();
	}
	
	public void put(String name,Object value)
	{
		this.request.setAttribute(name, value);
	}
}

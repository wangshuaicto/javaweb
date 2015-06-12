package com.test.filterdemo;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyCharacterServletWrapper extends HttpServletRequestWrapper{

	private HttpServletRequest request;
	
	public MyCharacterServletWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request=request;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		try
		{
			String value = request.getParameter(name);
			if(value == null)
			{
				return null;
			}
			
			if(!request.getMethod().equalsIgnoreCase("get")) //不是get方式的请求，直接返回
			{
				return value;
			}else //get方式请求处理
			{
				value = new String(value.getBytes("utf-8"), request.getCharacterEncoding());
				return value;
			}
		}catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}

}

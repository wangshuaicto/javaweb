package com.test.filterdemo;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter{
	
	private FilterConfig filterconfig = null;
	
	private String defaultEncoder = "UTF-8";

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		this.filterconfig=filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String charset = filterconfig.getInitParameter("charset");
		if(charset == null)
		{
			charset=defaultEncoder;
		}
		req.setCharacterEncoding(charset);
		res.setCharacterEncoding(charset);
		res.setContentType("text/html;charset="+charset);
		System.out.println("characterfilter running......");
		MyCharacterServletWrapper requestWrapper = new MyCharacterServletWrapper(req);
		chain.doFilter(requestWrapper, response);
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}

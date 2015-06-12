package com.test.jsptaglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;



public class ViewIpTag implements Tag{
	
	private PageContext pageContext; //接收传过来的pageContext对象
	
	private String url=null;

	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		System.out.println("调用doEndTag()方法");
		return 0;
	}

	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		System.out.println("调用doStartTag()方法");
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		JspWriter writer = pageContext.getOut();
		String ip = request.getRemoteAddr();
		if(url != null)
		{
			System.out.println("传入的url属性："+url);
		}
		try {
			writer.write(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

	public Tag getParent() {
		// TODO Auto-generated method stub
		return null;
	}

	public void release() {
		// TODO Auto-generated method stub
		System.out.println("调用release()方法");
	}

	public void setPageContext(PageContext pageContext) {
		// TODO Auto-generated method stub
		System.out.println("调用setPageContext()方法，接收到页面的pageContext");
		this.pageContext=pageContext;
	}

	public void setParent(Tag arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUrl(String value)
	{
		this.url=value;
	}
	
	public String getUrl()
	{
		return this.url;
	}
}

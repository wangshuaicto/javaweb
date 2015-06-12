package com.test.simpletag;

import java.io.IOException;
import java.net.HttpRetryException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.SkipPageException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class ReferTag extends SimpleTagSupport{
	
	private String site;
	private String page;
	
	
	public void setSite(String site)
	{
		this.site=site;
	}
	
	public void setPage(String page)
	{
		this.page=page;
	}

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		PageContext pageContext = (PageContext) this.getJspContext();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String refer = request.getHeader("referer");
		if(refer==null || !refer.startsWith(site))
		{
			HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
			String webRoot = request.getContextPath();
			if(page.startsWith(webRoot))
			{
				response.sendRedirect(page);
			}else
			{
				response.sendRedirect(webRoot+page);
			}
			throw new SkipPageException();
		}
	}

}

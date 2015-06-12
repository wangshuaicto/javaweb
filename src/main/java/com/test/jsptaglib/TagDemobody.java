package com.test.jsptaglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

public class TagDemobody extends BodyTagSupport{

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		BodyContent content = this.getBodyContent();
		try {
			this.pageContext.getOut().write(content.getString()+"标签体重写了这句话");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_BODY_BUFFERED;  //标签体缓冲
	}

}

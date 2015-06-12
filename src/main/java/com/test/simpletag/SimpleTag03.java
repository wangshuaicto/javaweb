package com.test.simpletag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTag03 extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspFragment jspfragment = this.getJspBody();
		StringWriter sw = new StringWriter();
		jspfragment.invoke(sw); //将标签体的内容写入到sw流中，这样就能通过sw获取到标签体
		//获取sw流中的标签体
		String content = sw.getBuffer().toString();
		PageContext pageContext = (PageContext) this.getJspContext();
		pageContext.getOut().write(content+"重写的标签体");
	}

}

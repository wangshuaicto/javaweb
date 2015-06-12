package com.test.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTag04 extends SimpleTagSupport{

	private int count;
	
	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspFragment jspfragment = this.getJspBody();
		for(int i=0;i<count;i++)
		{
			jspfragment.invoke(null);
		}
	}
	
	public void setCount(int count)
	{
		this.count=count;
	}
}

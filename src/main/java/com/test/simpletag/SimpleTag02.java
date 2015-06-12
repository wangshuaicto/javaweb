package com.test.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTag02 extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspFragment jspfragment = this.getJspBody();
		for(int i=0;i<5;i++)
		{
			jspfragment.invoke(null); //循环5次输出标签体
		}
	}

}

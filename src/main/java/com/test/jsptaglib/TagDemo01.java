package com.test.jsptaglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagDemo01 extends TagSupport {

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
//		return SKIP_BODY;   //不显示标签体的内容
		return EVAL_BODY_INCLUDE;  //显示标签体的内容
	}
	

}

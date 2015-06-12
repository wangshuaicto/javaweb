package com.test.jsptaglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagDemo03 extends TagSupport{

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
//		return SKIP_PAGE;//跳过后面的jsp不执行
		return EVAL_PAGE;  //执行标签后面的jsp
	}

}

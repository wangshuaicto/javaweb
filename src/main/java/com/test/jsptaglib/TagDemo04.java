package com.test.jsptaglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class TagDemo04 extends TagSupport{

	int x=5;
	
	@Override
	public int doAfterBody() throws JspException {
		// TODO Auto-generated method stub
		x--;
		if(x<=0)
		{
			//由于Tag初始化后就会常驻内存，直到WebContext容器推出。所以要及时复位x的值
			x=5;
			return SKIP_BODY; //跳过标签体执行标签后面的jsp页面
		}else
		{
			return EVAL_BODY_AGAIN;// 循环标签体执行
		}
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		return EVAL_BODY_INCLUDE;   //显示标签体
	}

}

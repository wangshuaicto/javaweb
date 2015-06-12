package com.test.simpletag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class SimpleTag01 extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		// TODO Auto-generated method stub
		JspFragment jspfragment = this.getJspBody();
		/**如果不调用下面这句invoke，那么标签体就不会显示到浏览器 */
		jspfragment.invoke(null); //将标签体输出到浏览器
	}

}

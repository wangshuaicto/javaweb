package com.test.servletcontextattributelistener;

import java.text.MessageFormat;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * servletattrlistener.jsp测试页面
 * */

public class MyServletContextAttributeListener implements ServletContextAttributeListener{

	public void attributeAdded(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		String msg = MessageFormat.format("ServletContext增加了属性{0},属性值是:{1}", scae.getName(),scae.getValue());
		System.out.println(msg);
	}

	public void attributeRemoved(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		String msg = MessageFormat.format("ServletContext删除了属性{0},属性值是:{1}", scae.getName(),scae.getValue());
		System.out.println(msg);
	}

	public void attributeReplaced(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		String msg = MessageFormat.format("ServletContext替换了属性{0},替换前属性值是:{1}", scae.getName(),scae.getValue());
		System.out.println(msg);
	}

}

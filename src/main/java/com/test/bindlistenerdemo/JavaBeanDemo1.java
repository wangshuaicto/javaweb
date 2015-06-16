package com.test.bindlistenerdemo;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


/**
 * bindtest1.jsp测试页面
 * @author wangshuai
 *
 */

public class JavaBeanDemo1 implements HttpSessionBindingListener{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println(name+"被添加到Session中");
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		System.out.println(name+"被Session移除了");
	}

}

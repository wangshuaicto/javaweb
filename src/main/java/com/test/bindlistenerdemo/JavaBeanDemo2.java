package com.test.bindlistenerdemo;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;


public class JavaBeanDemo2 implements HttpSessionActivationListener{
	
	private String name;
	
	public JavaBeanDemo2(String name)
	{
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void sessionWillPassivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println(name+"和session一起序列化到硬盘,session的id："+se.getSession().getId());
	}

	public void sessionDidActivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("*****************************");
		System.out.println(name+"和session一起反序列化到内存 ,session的id："+se.getSession().getId());
		
	}

}

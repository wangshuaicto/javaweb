package com.test.servletcontextlistener;

import java.util.Map;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener{

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		
		System.out.println("ServletContext创建");
		Map<String,? extends FilterRegistration> t_map = sce.getServletContext().getFilterRegistrations();
		System.out.println(t_map);
		
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("ServletContext销毁");
	}

}

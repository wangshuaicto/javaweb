package com.test.selfsessiontimer;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionScanerListener implements HttpSessionListener,ServletContextListener{
	
	private List<HttpSession> list = Collections.synchronizedList(new LinkedList<HttpSession>());
	
	//定义一把锁
	private Object lock = new Object();

	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("Web应用初始化");
		//创建定时器
		Timer timer = new Timer();
		//暂时注释
		timer.schedule(new MyTask(list, lock),0, 1000*30);
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		System.out.println("Web应用关闭");
	}

	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("session扫描器：session创建");
		synchronized (lock) {
			list.add(se.getSession());
		}
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("session被销毁了");
	}

}

package com.test.selfsessiontimer;

import java.util.List;
import java.util.ListIterator;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

public class MyTask extends TimerTask{
	
	private List list;
	private Object lock;
	
	public MyTask(List list,Object lock)
	{
		this.list=list;
		this.lock=lock;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized (lock) {
			System.out.println("定时器执行...");
			ListIterator<HttpSession> it = list.listIterator();
			while(it.hasNext())
			{
				HttpSession session = it.next();
				if(System.currentTimeMillis()-session.getLastAccessedTime()>= 1000*30)
				{
					session.invalidate(); //session失效
					it.remove(); //移除session
				}
			}
			System.out.println("Session个数："+list.size());
		}
	}

}

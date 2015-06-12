package com.test.threadlocal;

public class ConnectionContext {
	
	private static ConnectionContext context = new ConnectionContext();
	
	private static ThreadLocal<String> threadlocal = new ThreadLocal<String>();
	
	public static ConnectionContext getInstance()
	{
		return context;
	}
	
	public static void bind(String str)
	{
		threadlocal.set(str);
	}
	
	public static String getStr()
	{
		return threadlocal.get();
	}

}

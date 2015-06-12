package com.test.proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class HelloWorldHandler implements InvocationHandler{
	
	private Object obj;
	
	public HelloWorldHandler(Object obj)
	{
		super();
		this.obj=obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		Object result = null;
		doBefore();
		result = method.invoke(obj, args);
		doAfter();
		return result;
	}
	
	public void doBefore(){
		
		System.out.println("before method invoke");
	}
	
	public void doAfter()
	{
		System.out.println("after method invoke");
	}

	public static void main(String[] args)
	{
		HelloWorld hellowrld = new HelloWorldImpl();
		HelloWorldHandler handler = new HelloWorldHandler(hellowrld);
		
		//创建代理对象
		HelloWorld helloProxy = (HelloWorld) Proxy.newProxyInstance(HelloWorld.class.getClassLoader(), hellowrld.getClass().getInterfaces()	, handler);
//		HelloWorld helloProxy = (HelloWorld) Proxy.newProxyInstance(HelloWorld.class.getClassLoader(), new Class[]{HelloWorld.class}	, handler);
		helloProxy.sayHello();
	}
	
}

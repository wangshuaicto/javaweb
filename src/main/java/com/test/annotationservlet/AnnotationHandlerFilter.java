package com.test.annotationservlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnnotationHandlerFilter implements Filter{
	
	private ServletContext servletcontext;

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("AnnotationHandlerFilter过滤器初始化开始---");
		servletcontext = filterConfig.getServletContext();
		Map<String,Class<?>> classMap = new HashMap<String, Class<?>>();
		//获取web.xml中配置的要扫描的包
		String basePackage = filterConfig.getInitParameter("basePackage");
		//如果配置了多个包，例如：<param-value>me.gacl.web.controller,me.gacl.web.UI</param-value>
		if(basePackage.indexOf(",")>0)
		{
			//按照逗号分隔
			String[] packageNameArr = basePackage.split(",");
			for(String packageName:packageNameArr)
			{
				addServletClassToServletContext(packageName, classMap);
			}
		}else
		{
			addServletClassToServletContext(basePackage, classMap);
		}
		System.out.println("---AnnotationHandlerFilter过滤器初始化结束---");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("---------进入注解处理过滤器------");
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		Map<String,Class<?>> classMap = (Map<String, Class<?>>) servletcontext.getAttribute("servletClassMap");
		//获取contextPath
		String contextPath = req.getContextPath();
		//获取用户请求的uri资源
		String uri = req.getRequestURI();
		//如果没有指明要调用Servlet类中的哪个方法
		if(uri.indexOf('!')==-1)
		{
			//获取用户的请求方式
			String reqMethod = req.getMethod();
			//获取请求的Servlet路径
			String requestServletName = uri.substring(contextPath.length(),uri.lastIndexOf('.'));
			//获取要使用的类
			Class<?> clazz = classMap.get(requestServletName);
			//创建类的实例
			Object obj = null;
			try {
				obj = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Method targetMethod = null;
			
			if(reqMethod.equalsIgnoreCase("get"))
			{
				try {
					targetMethod = clazz.getDeclaredMethod("doGet", HttpServletRequest.class,HttpServletResponse.class);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else
			{
				try {
					targetMethod = clazz.getDeclaredMethod("doPost", HttpServletRequest.class,HttpServletResponse.class);
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				targetMethod.invoke(obj, req,res);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					
		}else
		{
			//获取请求的servlet路径
			String requestServletName = uri.substring(contextPath.length(),uri.lastIndexOf("!"));
			//获取调用的servlet方法
			String invokeMethodName = uri.substring(uri.lastIndexOf("!")+1,uri.lastIndexOf("."));
			//获取要使用的类
			Class<?> clazz = classMap.get(requestServletName);
			//创建类的实例
			Object obj = null;
			try {
				obj = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//获取clazz类所有定义的方法
			Method[] methods = clazz.getDeclaredMethods();
			//获取WebServlet这个注解实例
			WebServlet annotationInstance = clazz.getAnnotation(WebServlet.class);
			//获得注解上配置的初始化参数数组
			WebInitParam[] initParamArr = annotationInstance.initParams();
			Map<String,String> initParamMap = new HashMap<String, String>();
			for(WebInitParam param:initParamArr)
			{
				initParamMap.put(param.paramName(), param.paramValue());
			}
			
			//遍历clazz中的方法
			
			for(Method method:methods)
			{
				//该方法的返回类型
				Class<?> retType = method.getReturnType();
				//获得方法名
				String methodName = method.getName();
				//打印方法修饰符
				System.out.print(Modifier.toString(method.getModifiers()));
				System.out.print(" "+retType.getName() + " " + methodName +"(");
				//获得一个方法参数数组（getparameterTypes用于返回一个描述参数类型的Class对象数组）
				Class<?>[] paramTypes = method.getParameterTypes();
				for(int i=0 ;i<paramTypes.length;i++)
				{
					if(i>0)
					{
						System.out.print(",");
					}
					System.out.print(paramTypes[i].getName());
				}
				System.out.println(");");
				if(method.getName().equalsIgnoreCase("init"))
				{
					try {
						method.invoke(obj, initParamMap);
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
			System.out.println("invokeMethodName:"+invokeMethodName);
			try {
				Method targetMethod = clazz.getDeclaredMethod(invokeMethodName, HttpServletRequest.class,HttpServletResponse.class);
				try {
					targetMethod.invoke(obj, req,res);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		System.out.println("---------注解处理过滤器执行完毕------");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public void addServletClassToServletContext(String packageName,Map<String,Class<?>> classMap)
	{
		Set<Class<?>> setClasses = ScanClassUtil.getClasses(packageName);
		for(Class<?> clazz:setClasses)
		{
			if(clazz.isAnnotationPresent(WebServlet.class))
			{
				//获取WebServlet注解的类
				WebServlet annotationInstance = clazz.getAnnotation(WebServlet.class);
				//获取Annotation的实力的value的属性值
				String annotationAttrValue = annotationInstance.value();
				if(!annotationAttrValue.equals(""))
				{
					classMap.put(annotationAttrValue, clazz);
				}
				
				//获取annotation的实力的urlPatterns属性的值
				String[] urlPatterns = annotationInstance.urlPatterns();
				for(String pattern:urlPatterns)
				{
					classMap.put(pattern, clazz);
				}
				servletcontext.setAttribute("servletClassMap", classMap);
				System.out.println("AnnotationAttrValue:"+annotationAttrValue);
				String targetClassName = annotationAttrValue.substring(annotationAttrValue.lastIndexOf("/")+1);
				System.out.println("targetClassName："+targetClassName);
				System.out.println(clazz);
			}
		}
		
	}

}

package com.test.springmvc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnnotationHandlerServlet
 */
public class AnnotationHandlerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnnotationHandlerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.execute(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.execute(request, response);
	}

	private String prepareRequestURI(HttpServletRequest request) {
		String path = request.getContextPath() + "/";
		String requestUri = request.getRequestURI();
		String midurl = requestUri.replace(path, "");
		String lasturl = midurl.substring(0, midurl.lastIndexOf('.'));
		return lasturl;
	}

	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 将当前线程中HttpServletRequest对象存储到ThreadLocal中，以便在Controller类中使用
		WebContext.requestHolder.set(request);
		// 将当前线程中HttpServletResponse对象存储到ThreadLocal中，以便在Controller类中使用
		WebContext.resposeHolder.set(response);

		// 解析URL
		String lasturl = prepareRequestURI(request);
		// 获取要使用的类
		Class<?> clazz = RequestMappingMap.getRequestMap().get(lasturl);
		//创建类的实例
		Object classInstance = BeanUtil.instanceClass(clazz);
		//获取类中定义的方法
		Method[] methods = BeanUtil.findDeclaredMethods(clazz);
		Method method = null;
		for(Method m:methods)
		{
			if(m.isAnnotationPresent(RequestMapping.class))
			{
				String annoPath = m.getAnnotation(RequestMapping.class).value();
				if(annoPath!=null&&!"".equals(annoPath.trim())&&lasturl.equals(annoPath.trim()))
				{
					//找到要执行的目标方法
					method = m;
					break;
				}
			}
		}
		try
		{
			if(method!=null)
			{
				//执行目标方法处理请求
				Object retObject = method.invoke(classInstance);
				//如果方法有返回值，那么表示用户需要返回视图
				if(retObject!=null)
				{
					View view = (View) retObject;
					if(view.getDispatchAction().equals(DispatchActionConstant.FORWARD))
					{
						//使用服务器端跳转
						request.getRequestDispatcher(view.getUrl()).forward(request, response);
					}else if(view.getDispatchAction().equals(DispatchActionConstant.REDIRECT))
					{
						//使用客户端跳转
						response.sendRedirect(request.getContextPath()+view.getUrl());
					}else
					{
						request.getRequestDispatcher(view.getUrl()).forward(request, response);
					}
				}
			}
		}catch(Exception e)
		{
			
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		System.out.println("==============AnnotationHandlerServlet初始化开始==============");
		//获取web.xml中配置的要扫描的包
		String basePackage = config.getInitParameter("basePackage");
		if(basePackage.indexOf(',')>0)
		{
			String[] packageNameArr = basePackage.split(",");
			for(String packageName:packageNameArr)
			{
				initRequestMappingMap(packageName);
			}
		}else
		{
			initRequestMappingMap(basePackage);
		}
		System.out.println("==============AnnotationHandlerServlet初始化结束==============");
	}
	
	public void initRequestMappingMap(String packageName)
	{
		Set<Class<?>> setClasses = ScanClassUtil.getClasses(packageName);
		
		for(Class<?> clazz:setClasses)
		{
			if(clazz.isAnnotationPresent(Controller.class))
			{
				Method[] methods = BeanUtil.findDeclaredMethods(clazz);
				//循环方法，找到匹配的方法执行
				for(Method method:methods)
				{
					if(method.isAnnotationPresent(RequestMapping.class))
					{
						//获取annotation实例
						RequestMapping annotationInstance = method.getAnnotation(RequestMapping.class);
						//配置路径
						String annoPath = annotationInstance.value();
						if(RequestMappingMap.getRequestMap().containsKey(annoPath))
						{
							throw new RuntimeException("RequestMapping映射的地址不允许重复！");
						}
						RequestMappingMap.put(annoPath, clazz);
					}
				}
			}
		}
	}

	
}

package com.test.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.annotationservlet.WebInitParam;
import com.test.annotationservlet.WebServlet;


//测试  http://127.0.0.1:8080/javaweb/servlet1/logincomplex!loginhandler.do

@WebServlet(value="/servlet1/logincomplex",
			urlPatterns={"/ws/login","/ws1/login"},
			initParams={
				@WebInitParam(paramName="gacl",paramValue="哈哈"),
				@WebInitParam(paramName="xdp",paramValue="成功了")
			},
			name="loginservlet",
			description="处理用户登录的Servlet"
		)
public class LoginServeletComplex {
	
	
	public void loginhandler(HttpServletRequest request,HttpServletResponse response)
	{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("成功执行了复杂的注解Servlet");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//初始化参数
	public void init(Map<String,String> initParamMap)
	{
		System.out.println("注解Servlet初始化数据"+initParamMap.get("gacl"));
		System.out.println("注解Servlet初始化数据"+initParamMap.get("xdp"));
	}
}

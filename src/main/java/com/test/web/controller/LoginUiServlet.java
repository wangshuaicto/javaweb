package com.test.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.annotationservlet.WebServlet;

@WebServlet("/servlet/login")
public class LoginUiServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		System.out.println("注解配置servlet执行 Get");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("你好啊");
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		System.out.println("注解配置servlet执行Post");
		doGet(request, response);
	}
}

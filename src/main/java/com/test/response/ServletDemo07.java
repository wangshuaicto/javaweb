package com.test.response;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo07
 */
public class ServletDemo07 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ServletConfig config;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo07() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		this.config=config;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String contextpath = this.getServletContext().getContextPath();
		String data = (String) this.getServletContext().getAttribute("data");
		String realPath = this.getServletContext().getRealPath("/jsp");
		//获取Servlet配置的初始化参数
		String paranamebythis = this.getServletConfig().getInitParameter("name");
		String name = this.config.getInitParameter("name");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.getWriter().print(name);
		response.getWriter().print("<br/>");
		Enumeration<String> params = this.config.getInitParameterNames();
		while(params.hasMoreElements())
		{
			String paraname = params.nextElement();
			String value = this.config.getInitParameter(paraname);
			response.getWriter().print(paraname+" = "+value+"<br/>");
		}
		
		response.getWriter().print("contextpath："+contextpath+"<br/>");
		response.getWriter().print("contextData："+data+"<br/>");
		response.getWriter().print("realPath："+realPath+"<br/>");
		response.getWriter().print("paranamebythis："+paranamebythis+"<br/>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

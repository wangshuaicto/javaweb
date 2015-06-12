package com.test.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemoRequest03
 */
public class ServletDemoRequest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemoRequest03() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		Cookie[] cookies = request.getCookies();
		if(cookies!=null)
		{
			for(Cookie cookie:cookies)
			{
				if(cookie.getName().equals("lastAcessTime"))
				{
					Long longtime = Long.parseLong(cookie.getValue());
					Date date = new Date(longtime);
					pw.write("您上次访问时间是："+ date.toLocaleString());
				}
			}
		}else
		{
			pw.write("这是您第一次访问");
		}
		
		//创建Cookie
		Cookie cookie = new Cookie("lastAcessTime", System.currentTimeMillis()+"");
		cookie.setMaxAge(24*60*60); //设置expries 到期时间
		response.addCookie(cookie);
	}

}

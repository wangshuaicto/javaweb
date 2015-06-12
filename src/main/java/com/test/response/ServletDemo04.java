package com.test.response;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo04
 */
public class ServletDemo04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo04() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.setHeader("refresh", "3;http://www.baidu.com");
				/**
		         * response.setContentType("text/html;charset=UTF-8");目的是控制浏览器用UTF-8进行解码；
		         * 这样就不会出现中文乱码了
		         */
		response.setHeader("content-type","text/html;charset=UTF-8");
		response.setHeader("refresh", "5;/javaweb/ServletDemo01");
		response.getWriter().write("5秒后跳转到ServletDemo01");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

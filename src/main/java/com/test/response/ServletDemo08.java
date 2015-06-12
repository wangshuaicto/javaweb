package com.test.response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.threadlocal.ConnectionContext;

/**
 * Servlet implementation class ServletDemo08
 */
public class ServletDemo08 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo08() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**获取web应用的初始化参数*/
		String url = this.getServletContext().getInitParameter("url");
		String threadLocalstr = ConnectionContext.getInstance().getStr();
		String username = request.getParameter("username");
		System.out.println(threadLocalstr);
		PrintWriter outer = response.getWriter();
		outer.write(url+"<br/>");
		outer.write(username+"");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

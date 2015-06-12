package com.test.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo06
 */
public class ServletDemo06 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int i=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo06() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//线程安全的
		synchronized (this) {
			i++;
			try
			{
				Thread.sleep(1000*4);
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			response.getWriter().write(i+"");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

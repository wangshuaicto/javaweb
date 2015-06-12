package com.test.request.form;

import java.io.IOException;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DoFormServlet
 */
public class DoFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoFormServlet() {
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
		boolean isReapet = isReapetSubmit(request);
		if(isReapet)
		{
			System.out.println("请勿重复提交表单");
		}else
		{
			System.out.println("处理表单");
			request.getSession().removeAttribute("token");
		}
	}
	
	private boolean isReapetSubmit(HttpServletRequest request)
	{
		String client_token = request.getParameter("token");
		if(null == client_token)
		{
			return true;
		}
		HttpSession session = request.getSession();
		String server_token = (String) session.getAttribute("token");
		if(server_token == null)
		{
			return true;
		}
		if(!server_token.equals(client_token))
		{
			return true;
		}
		return false;
	}

}

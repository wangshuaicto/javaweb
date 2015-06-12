package com.test.response;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletDemo14
 */
public class ServletDemo14 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo14() {
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
		String formcheckcode = request.getParameter("checkcode");
		HttpSession session = request.getSession(true);
		String checkcode = (String) session.getAttribute("checkcode");
		if(checkcode !=null)
		{
			System.out.println("Session-"+session.getId()+" 服务器验证码是 " +checkcode);
		}else{
			System.out.println("Session-"+session.getId()+"服务器无验证码");
		}
		System.out.println("浏览器传过来的验证码："+formcheckcode);
		if(null != formcheckcode && formcheckcode.equals(checkcode))
		{
			System.out.println("验证成功");
		}else
		{
			System.out.println("验证失败");
		}
	}

}

package com.test.response;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo10
 */
public class ServletDemo10 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo10() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("content-type", "text/html;charset=utf-8");
		/**类装载器*/
		//默认的类加载器  从/WEB-INF/classes下查找
		ClassLoader classloader = ServletDemo10.class.getClassLoader();
		InputStream is = classloader.getResourceAsStream("db1.properties");
		Properties pro = new Properties();
		pro.load(is);
		String name = pro.getProperty("name");
		//ServletContext加载
		//是默认从ServletContext上下文加载  webapp/
		InputStream sis = this.getServletContext().getResourceAsStream("/WEB-INF/classes/db1.properties");
		InputStream anotertest = this.getServletContext().getResourceAsStream("/jsp/location/2.jsp");
		InputStream imagestream = this.getServletContext().getResourceAsStream("/img/a.jpg");
		Properties pros = new Properties();
		pros.load(sis);
		String name2 = pro.getProperty("name");
		response.getWriter().print(name+"<br/>");
		response.getWriter().print(name2+"<br/>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

package com.test.request;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemoRequest02
 */
public class ServletDemoRequest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemoRequest02() {
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
		pw.write("获取到浏览器客户端的headers信息：");
		pw.write("<br/>");
		Enumeration<String> heardernames = request.getHeaderNames();
		while(heardernames.hasMoreElements())
		{
			String headername = heardernames.nextElement();
			String value = request.getHeader(headername);
			pw.write(headername+"="+value);
			pw.write("<br/>");
		}
		
		String values = request.getHeader("Accept-Encoding");
		pw.write(values);
		pw.write("<br/>");
		/**以Map方式获取**/
		Map<String,String[]> paramap = request.getParameterMap();
		pw.write("-----------------------ParameterMap----------------------<br/>");
		
		for(Entry<String, String[]> entry:paramap.entrySet())
		{
			String key = entry.getKey();
			String[] paramapvalues = entry.getValue();
			StringBuffer sb = new StringBuffer();
			for(String a:paramapvalues)
			{
				sb.append(a);
				sb.append(",");
			}
			pw.write(key+"="+sb.substring(0, sb.length()-1));
			pw.write("<br/>");
		}
		
	}

}

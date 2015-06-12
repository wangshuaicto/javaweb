package com.test.request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemoRequest01
 */
public class ServletDemoRequest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemoRequest01() {
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
		String  requestUrl = request.getRequestURL().toString(); //方法返回客户端发出请求时的完整URL
		String requestUri = request.getRequestURI();
		String queryString = request.getQueryString();
		String remoteAddr = request.getRemoteAddr();
		String remoteHost = request.getRemoteHost();
		int remotePort = request.getRemotePort();
		String remoteUser = request.getRemoteUser();
		String method = request.getMethod();//请求使用的方法
		String pathinfo = request.getPathInfo();//请求得到路径信息
		String serverIp = request.getLocalAddr();
		String serverhost = request.getLocalName();
		String serverport = request.getLocalPort()+"";
		
		
		response.setCharacterEncoding("utf-8");
		response.setHeader("content-type", "text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.write("获取到的客户机信息如下:");
		pw.write("<br/>");
		pw.write("请求的URL地址："+requestUrl);
		pw.write("<br/>");
		pw.write("请求的URI："+requestUri);
		pw.write("<br/>");
		pw.write("请求的URl附带的参数信息："+queryString);
		pw.write("<br/>");
		pw.write("得到客户端ip地址："+remoteAddr);
		pw.write("<br/>");
		pw.write("得到客户端主机名："+remoteHost);
		pw.write("<br/>");
		pw.write("得到客户端端口号："+remotePort);
		pw.write("<br/>");
		pw.write("得到客户端端用户："+remoteUser);
		pw.write("<br/>");
		pw.write("请求使用的方式："+method);
		pw.write("<br/>");
		pw.write("路径信息："+pathinfo);
		pw.write("<br/>");
		pw.write("服务器信息："+serverhost+" : "+serverIp+" : "+serverport);
		pw.write("<br/>");
		pw.flush();
		pw.close();
	}

}

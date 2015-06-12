package com.test.response;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo11
 */
public class ServletDemo11 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo11() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String data = "中国";
		/**对于不经常变的请求设置Servlet缓存输出        按浏览器F5强制刷新 **/
		response.setDateHeader("expires", System.currentTimeMillis()+24*60*60*1000);
//		response.setHeader("expires", new Date(System.currentTimeMillis()+24*60*60*1000).toGMTString()+"");
		System.out.println("=================执行了===================");
//		response.getOutputStream().write(data.getBytes());
		/**服务端像客户端写入编码数据,那么就必须设置浏览器响应头的content-type属性来告诉浏览器响应数据的编码 否则不一致就会中文乱码*/
//		response.setHeader("content-type", "text/html;charset=GBK");
//		response.getOutputStream().write(data.getBytes("GBK"));
		
		response.setHeader("content-type", "text/html;charset=utf-8");
		response.getOutputStream().write(data.getBytes("utf-8"));
		
		
		
		/**
		 	以下是当使用PrintWriter时设置编码的方法
		 	1.先设置服务器字符的编码通过
		 		response.setCharacterEncoding("utf-8");
		 		response.setCharacterEncoding("gbk");
		 	2.设置浏览器响应头的content-type属性
		 		response.setHeader("content-type", "text/html;charset=utf-8");
		 		response.setHeader("content-type", "text/html;charset=gbk");
		 	3.开始写入数据
		 		response.getWriter().write();
		 		
		 		注意： 1、3顺序不能颠倒  必须先设置字符编码然后在写入数据
		 */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

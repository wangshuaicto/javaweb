package com.test.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo02
 */
public class ServletDemo02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public ServletDemo02() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.getServletConfig().getServletContext().setAttribute("data", "HAHA");
		
		String data = "sdfasdfasdddddddddddddddddddd"+
					"sadvasdddddddddddddddddddddddddd"+
				"asdddddddddddddddddddddddddddddddddddd"+
					"sdfasdddddddddddddddddddddddddddddddd"+
				"sssssssssssssssssssssssssssssssssssssssssssss"+
					"sdfasdf";
		System.out.println("原数据的大小："+data.getBytes().length);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzipos = new GZIPOutputStream(baos);
		gzipos.write(data.getBytes());
		gzipos.close();
		//得到压缩后的数据
		byte g[]=baos.toByteArray();
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("Content-Length", g.length+"");
		response.getOutputStream().write(g);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}

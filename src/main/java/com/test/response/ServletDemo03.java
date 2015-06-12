package com.test.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo03
 */
public class ServletDemo03 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo03() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		InputStream in = this.getServletContext().getResourceAsStream("/img/a.jpg");
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		GZIPOutputStream gzipos  = new GZIPOutputStream(baos);
		
		int len=0;
		
		byte[] buffer = new byte[1024];
		
		System.out.println("原始图片大小："+in.available());
		
		while((len=in.read(buffer))!=-1)
		{
			gzipos.write(buffer, 0, len);
		}
		
		gzipos.close();
		byte[] g = baos.toByteArray();
		response.setHeader("Content-type", "image/jpeg");
		response.setHeader("Content-Encoding", "gzip");
		response.setHeader("content-length", g.length+"");
		
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
